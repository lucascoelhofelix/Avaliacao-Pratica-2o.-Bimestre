/**
 * VaRStrategy.java
 *
 * Estratégia Concreta: Implementa o cálculo de Value at Risk (VaR).
 *
 * Padrão de Projeto: Strategy (Componente Concreto)
 *
 * Princípios SOLID:
 * - SRP (Responsabilidade Única): Responsabilidade única de calcular o VaR.
 * - LSP (Substituição de Liskov): Pode substituir a interface RiskCalculationStrategy.
 */
public class VaRStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cálculo de VaR
        double vaRResult = context.getPortfolioValue() * (1 - context.getConfidenceLevel()) * 10;
        return "Cálculo de VaR concluído: O VaR a " + (context.getConfidenceLevel() * 100) +
               "% é de R$" + String.format("%,.2f", vaRResult);
    }
}
