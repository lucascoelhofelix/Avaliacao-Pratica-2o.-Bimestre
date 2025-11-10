/**
 * ExpectedShortfallStrategy.java
 *
 * Estratégia Concreta: Implementa o cálculo de Expected Shortfall (ES).
 *
 * Padrão de Projeto: Strategy (Componente Concreto)
 *
 * Princípios SOLID:
 * - SRP (Responsabilidade Única): Responsabilidade única de calcular o ES.
 */
public class ExpectedShortfallStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cálculo de ES (geralmente maior que o VaR)
        double esResult = context.getPortfolioValue() * (1 - context.getConfidenceLevel()) * 15;
        return "Cálculo de Expected Shortfall concluído: O ES é de R$" + String.format("%,.2f", esResult) +
               " (Média das perdas piores que o VaR).";
    }
}
