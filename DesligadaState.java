// DesligadaState.java
public class DesligadaState implements ReactorState {
    @Override
    public String getNomeEstado() { return "DESLIGADA"; }

    @Override
    public void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes) {
        System.out.println("Usina desligada. Iniciando não é uma transição automática.");
        // Transição possível: DESLIGADA -> OPERACAO_NORMAL (Manual)
    }

    @Override
    public void desligar(ReactorContext context) {
        System.out.println("Já estamos desligados.");
    }
}
