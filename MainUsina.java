public class MainUsina {
    public static void main(String[] args) {
        // Iniciar com condições seguras
        CondicoesOperacionais condicoes = new CondicoesOperacionais(250.0, 5.0, 1.0);
        
        // Iniciar o reator (Contexto) no estado normal de operação
        ReactorContext reator = new ReactorContext(condicoes, new OperacaoNormalState());
    }
}
