/**
 * ReactorContext.java
 *
 * Contexto (Context): Mantém uma referência ao objeto de estado atual e delega as transições.
 *
 * Padrão de Projeto: Context (State Pattern)
 * Princípio DIP: Depende da abstração ReactorState.
 */
public class ReactorContext {
    private ReactorState currentState;
    private final CondicoesOperacionais condicoes;
    private boolean emManutencao = false;

    public ReactorContext(CondicoesOperacionais condicoes, ReactorState initialState) {
        this.condicoes = condicoes;
        this.currentState = initialState;
        System.out.println("Usina iniciada. Estado Inicial: " + currentState.getNomeEstado());
    }

    // Método principal que aciona a lógica de transição no estado atual
    public void verificarSistema() {
        System.out.println("\n--- Verificação de Sistema ---");
        System.out.println("Estado Atual: " + currentState.getNomeEstado());
        
        if (emManutencao) {
            System.out.println("[MODO MANUTENÇÃO ATIVO] Transições normais ignoradas.");
            return;
        }

        currentState.verificarEReagir(this, condicoes);
    }

    // Permite transição do contexto para um novo estado
    public void setState(ReactorState newState) {
        System.out.println(">> Transição: " + currentState.getNomeEstado() + " -> " + newState.getNomeEstado());
        this.currentState = newState;
    }

    // Simulação do botão de desligamento (regra de segurança)
    public void desligar() {
        // O estado atual decide como processar o desligamento
        currentState.desligar(this);
    }

    // Getters e Setters para o modo Manutenção (parte do Adapter/Override)
    public boolean isEmManutencao() { return emManutencao; }
    public void setEmManutencao(boolean emManutencao) { 
        this.emManutencao = emManutencao;
        System.out.println("\n*** MODO MANUTENÇÃO " + (emManutencao ? "ATIVADO" : "DESATIVADO") + " ***");
    }
    
    public ReactorState getCurrentState() { return currentState; }
    public CondicoesOperacionais getCondicoes() { return condicoes; }
}
