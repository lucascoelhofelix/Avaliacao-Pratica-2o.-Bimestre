/**
 * RiskProcessorContext.java
 *
 * Contexto do Processador de Risco: Classe principal que usa a Estratégia.
 * Permite a troca do algoritmo em tempo de execução.
 *
 * Padrão de Projeto: Strategy (Componente Contexto)
 *
 * Princípios SOLID:
 * - DIP: Depende da abstração RiskCalculationStrategy (via composição).
 * - OCP: O método processRisk() está fechado para modificação; a adição de novos
 * algoritmos ocorre por extensão (novas classes Strategy).
 */
public class RiskProcessorContext {
    private RiskCalculationStrategy currentStrategy;
    private final FinancialContext financialContext;

    public RiskProcessorContext(FinancialContext context, RiskCalculationStrategy initialStrategy) {
        this.financialContext = context;
        this.currentStrategy = initialStrategy;
        System.out.println("Processor Context criado com estratégia inicial: " + initialStrategy.getClass().getSimpleName());
    }

    /**
     * Permite a troca dinâmica do algoritmo de risco em tempo de execução.
     * Atende ao requisito de intercambialidade.
     * @param newStrategy A nova estratégia de cálculo de risco.
     */
    public void setStrategy(RiskCalculationStrategy newStrategy) {
        System.out.println("\n--- Algoritmo de Risco Trocado Dinamicamente para: " + newStrategy.getClass().getSimpleName() + " ---");
        this.currentStrategy = newStrategy;
    }

    /**
     * Executa o cálculo de risco usando a estratégia atualmente configurada.
     * @return O resultado do cálculo.
     */
    public String processRisk() {
        System.out.println("\nIniciando cálculo de risco...");
        System.out.println("Usando: " + currentStrategy.getClass().getSimpleName());
        // O Contexto delega a execução para a Estratégia.
        return currentStrategy.calculateRisk(financialContext);
    }
}
