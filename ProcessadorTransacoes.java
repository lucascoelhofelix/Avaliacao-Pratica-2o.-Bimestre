import java.util.Map;

/**
 * ProcessadorTransacoes.java
 *
 * Interface Moderna (Target): A interface que o nosso novo sistema espera utilizar.
 *
 * Padrão de Projeto: Target (Interface Desejada)
 */
public interface ProcessadorTransacoes {
    /**
     * Autoriza uma transação com o formato moderno e tipos de dados otimizados.
     * @param cartao Número do cartão.
     * @param valor Valor da transação.
     * @param moeda Código da moeda (e.g., "USD", "EUR", "BRL").
     * @return O mapa de resposta da transação no formato moderno.
     */
    Map<String, Object> autorizar(String cartao, double valor, String moeda);
}
