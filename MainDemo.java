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

        // --- Execução 1: Usando VaR ---
        System.out.println("\n######################################");
        System.out.println("## Execução 1: Value at Risk (VaR) ##");
        System.out.println("######################################");
        String result1 = processor.processRisk();
        System.out.println("Resultado Final: " + result1);


        // --- Execução 2: Troca Dinâmica para Expected Shortfall (ES) ---
        // Troca de algoritmo em tempo de execução, sem mudar a lógica do 'processor'.
        processor.setStrategy(new ExpectedShortfallStrategy());
        System.out.println("\n#################################################");
        System.out.println("## Execução 2: Expected Shortfall (Troca Din.) ##");
        System.out.println("#################################################");
        String result2 = processor.processRisk();
        System.out.println("Resultado Final: " + result2);


        // --- Execução 3: Troca Dinâmica para Stress Testing ---
        processor.setStrategy(new StressTestingStrategy());
        System.out.println("\n############################################");
        System.out.println("## Execução 3: Stress Testing (Troca Din.) ##");
        System.out.println("############################################");
        String result3 = processor.processRisk();
        System.out.println("Resultado Final: " + result3);
    }
}
