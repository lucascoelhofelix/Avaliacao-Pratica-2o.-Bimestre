import java.util.concurrent.*;

/**
 * AbstractValidator.java
 *
 * Implementa a interface Handler (Chain of Responsibility) e define o esqueleto
 * do algoritmo (Template Method) para lidar com timeout e o circuit breaker.
 *
 * Princípios: DIP (Depende da interface), Template Method, OCP/SRP.
 */
public abstract class AbstractValidator implements NFeValidator {
    
    // Chain of Responsibility: Próximo Handler na cadeia
    private NFeValidator nextValidator;
    
    // Circuit Breaker: Estado compartilhado (melhor gerenciado em uma classe Contexto, 
    // mas simplificado aqui para demonstração do mecanismo)
    private static final int MAX_FAILURES = 3;
    private static int failureCount = 0;
    
    // Template Method: Define o tempo máximo de execução para este validador
    protected abstract long getTimeoutSeconds();

    // Template Method: A lógica real de validação que cada subclasse deve implementar
    protected abstract boolean performValidation(NFeDocument doc);

    // Template Method: Lógica de Rollback
    public void rollback(NFeDocument doc) {
        // Nada por padrão. Subclasses que alteram o estado devem sobrescrever.
    }
    
    // Setter do Chain
    @Override
    public NFeValidator setNext(NFeValidator next) {
        this.nextValidator = next;
        return next;
    }

    // Método principal que coordena a validação e o fluxo
    @Override
    public boolean handle(NFeDocument doc) {
        if (failureCount >= MAX_FAILURES) {
            doc.isValid = false;
            doc.motivoFalha = "Circuit Breaker ativado: Limite de " + MAX_FAILURES + " falhas atingido.";
            System.err.println("!!! CIRCUIT BREAKER ATIVADO. Validação interrompida. !!!");
            return false;
        }

        // Lógica de Timeout (Usando ExecutorService para simulação)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> performValidation(doc));

        boolean success;
        try {
            // Executa a validação com o timeout individual
            success = future.get(getTimeoutSeconds(), TimeUnit.SECONDS); 
        } catch (TimeoutException e) {
            future.cancel(true);
            success = false;
            doc.motivoFalha = getNome() + " FALHOU: Timeout de " + getTimeoutSeconds() + "s excedido.";
        } catch (Exception e) {
            success = false;
            doc.motivoFalha = getNome() + " FALHOU: Erro interno.";
        } finally {
            executor.shutdownNow();
        }

        if (success) {
            System.out.println("✅ " + getNome() + " PASSED.");
        } else {
            System.out.println("❌ " + getNome() + " FAILED. Motivo: " + doc.motivoFalha);
            failureCount++;
        }

        // Fluxo Condicional da Cadeia de Responsabilidade
        if (success && nextValidator != null) {
            // Repassa para o próximo se for sucesso
            boolean chainSuccess = nextValidator.handle(doc);
            
            // Lógica de Rollback (Se a cadeia subsequente falhar, executa o rollback)
            if (!chainSuccess) {
                System.out.println("⏪ " + getNome() + ": Rollback acionado.");
                rollback(doc);
            }
            return chainSuccess;
        }

        return success;
    }

    // Permite que os validadores concretos se identifiquem na saída
    public abstract String getNome(); 
    
    // Método para resetar o circuit breaker (para fins de teste)
    public static void resetCircuitBreaker() {
        failureCount = 0;
    }
}
