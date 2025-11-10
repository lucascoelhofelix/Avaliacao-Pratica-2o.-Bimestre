import java.util.Map;

/**
 * MainClient.java
 *
 * Cliente que utiliza a interface moderna, sem saber que está usando um Adaptador
 * para se comunicar com um sistema legado.
 */
public class MainClient {
    public static void main(String[] args) {
        // 1. Instanciar o Sistema Legado (o Adaptee)
        SistemaBancarioLegado legado = new SistemaBancarioLegado();

        // 2. Instanciar o Adaptador, passando a instância do Legado
        // O Adaptador implementa a interface que o cliente espera (ProcessadorTransacoes)
        ProcessadorTransacoes processadorModerno = new AdaptadorTransacoes(legado);
    }
}
