// AlertaVermelhoState.java
public class AlertaVermelhoState implements ReactorState {
    @Override
    public String getNomeEstado() { return "ALERTA_VERMELHO"; }

    @Override
    public void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes) {
        System.out.println("*** ALERTA CRÍTICO VERMELHO! Preparando desligamento de emergência. ***");
        
        // Regra de Transição Unidirecional: ALERTA_VERMELHO -> EMERGENCIA
        // O estado EMERGENCIA só pode ser ativado após passar por ALERTA_VERMELHO (Regra cumprida aqui)
        if (condicoes.falhaResfriamento) {
            context.setState(new EmergenciaState());
        } 
        // Previna transições circulares: Não há transição VERMELHO -> VERMELHO automática
    }

    @Override
    public void desligar(ReactorContext context) {
        // Transição bidirecional segura
        context.setState(new DesligadaState());
    }
}
