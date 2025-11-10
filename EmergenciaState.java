// EmergenciaState.java
public class EmergenciaState implements ReactorState {
    @Override
    public String getNomeEstado() { return "EMERGENCIA"; }

    @Override
    public void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes) {
        System.err.println("!!! MODO EMERGÊNCIA ATIVO. Todos os sistemas PARALISADOS. Ação manual necessária. !!!");
        // Previna transições circulares perigosas: O estado de emergência não deve ter transições automáticas.
    }

    @Override
    public void desligar(ReactorContext context) {
        // Única saída segura: Transição para Desligada (Ação manual após emergência)
        System.out.println("Ação manual de segurança concluída. Desligando o reator.");
        context.setState(new DesligadaState());
    }
}
