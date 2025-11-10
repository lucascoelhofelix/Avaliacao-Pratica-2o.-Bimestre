/**
 * MainDemo.java
 *
 * Cliente que utiliza o sistema. Demonstra a troca dinâmica de algoritmos.
 */
public class MainDemo {
    public static void main(String[] args) {
        // 1. Criar o Contexto Financeiro com os parâmetros de entrada
        // Valor do Portfólio: R$ 1.000.000,00 | Nível de Confiança: 99% | Janela Histórica: 252 dias (1 ano útil)
        FinancialContext complexContext = new FinancialContext(1000000.00, 0.99, 252);

        // 2. Criar o Contexto do Processador com uma estratégia inicial (VaR)
        RiskProcessorContext processor = new RiskProcessorContext(
            complexContext,
            new VaRStrategy() // Estratégia inicial
        );
}
