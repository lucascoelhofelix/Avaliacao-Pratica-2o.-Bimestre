// AlertaAmareloState.java
public class AlertaAmareloState implements ReactorState {
    @Override
    public String getNomeEstado() { return "ALERTA_AMARELO"; }

    @Override
    public void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes) {
        System.out.println("Alerta Amarelo Ativo. T: " + condicoes.temperaturaC + "°C");
        
        // 1. Regra de Reversão (Bidirecional)
        if (condicoes.temperaturaC <= 300.0) {
            condicoes.tempoAlertaAmareloSegundos = 0; // Resetar contador
            context.setState(new OperacaoNormalState());
            return;
        }

        // 2. Regra para próximo Alerta
        // Regra: ALERTA_AMARELO -> ALERTA_VERMELHO: se temperatura > 400°C por mais de 30 segundos
        if (condicoes.temperaturaC > 400.0) {
            condicoes.tempoAlertaAmareloSegundos += 10; // Simula 10s de tempo de verificação
            System.out.println("Temp > 400°C por " + condicoes.tempoAlertaAmareloSegundos + "s.");
            
            if (condicoes.tempoAlertaAmareloSegundos >= 30) {
                context.setState(new AlertaVermelhoState());
            }
        } else {
            condicoes.tempoAlertaAmareloSegundos = 0; // Resetar se cair abaixo de 400°C
        }
    }

    @Override
    public void desligar(ReactorContext context) {
        // Transição bidirecional segura
        context.setState(new DesligadaState());
    }
}
