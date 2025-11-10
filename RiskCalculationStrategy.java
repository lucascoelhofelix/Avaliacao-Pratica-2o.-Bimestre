// RiskCalculationStrategy.java
/**
 * Interface Strategy (Estratégia): Define o contrato comum para todos os algoritmos de cálculo de risco.
 *
 * Princípios SOLID:
 * - OCP/DIP: O Contexto irá depender desta abstração, permitindo novas estratégias
 * sem modificar o Contexto (OCP) e invertendo a dependência (DIP).
 */
public interface RiskCalculationStrategy {
    String calculateRisk(FinancialContext context);
}

// VaRStrategy.java
/**
 * Estratégia Concreta: Implementa o cálculo de Value at Risk (VaR).
 *
 * Princípio SOLID:
 * - SRP: Responsabilidade única de calcular o VaR.
 * - LSP: Pode substituir a interface RiskCalculationStrategy.
 */
public class VaRStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cálculo de VaR
        double vaRResult = context.getPortfolioValue() * (1 - context.getConfidenceLevel()) * 10;
        return "Cálculo de VaR concluído: O VaR a " + (context.getConfidenceLevel() * 100) +
               "% é de R$" + String.format("%.2f", vaRResult);
    }
}

// ExpectedShortfallStrategy.java
/**
 * Estratégia Concreta: Implementa o cálculo de Expected Shortfall (ES).
 *
 * Princípio SOLID:
 * - SRP: Responsabilidade única de calcular o ES.
 */
public class ExpectedShortfallStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cálculo de ES (geralmente maior que o VaR)
        double esResult = context.getPortfolioValue() * (1 - context.getConfidenceLevel()) * 15;
        return "Cálculo de Expected Shortfall concluído: O ES é de R$" + String.format("%.2f", esResult) +
               " (Média das perdas piores que o VaR).";
    }
}

// StressTestingStrategy.java
/**
 * Estratégia Concreta: Implementa o cálculo de Stress Testing.
 *
 * Princípio SOLID:
 * - SRP: Responsabilidade única de calcular o Stress Testing.
 */
public class StressTestingStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cenário de estresse (e.g., crise de 2008)
        double stressLoss = context.getPortfolioValue() * 0.35; // Perda de 35% no cenário de estresse
        return "Cálculo de Stress Testing concluído: Perda Máxima no Cenário de Estresse: R$" +
               String.format("%.2f", stressLoss);
    }
}
