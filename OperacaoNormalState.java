// OperacaoNormalState.java
public class OperacaoNormalState implements ReactorState {
    @Override
    public String getNomeEstado() { return "OPERACAO_NORMAL"; }

    @Override
    public void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes) {
        System.out.println("Verificação Normal. T: " + condicoes.temperaturaC + "°C");
        
        // Regra: OPERACAO_NORMAL -> ALERTA_AMARELO: se temperatura > 300°C
        if (condicoes.temperaturaC > 300.0) {
            context.setState(new AlertaAmareloState());
        } 
        // Previna transições circulares: Não há transição NORMAL -> NORMAL automática aqui
    }

    @Override
    public void desligar(ReactorContext context) {
        // Transição bidirecional segura
        context.setState(new DesligadaState());
    }
}
