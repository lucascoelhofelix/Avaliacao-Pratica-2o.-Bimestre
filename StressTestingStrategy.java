/**
 * StressTestingStrategy.java
 *
 * Estratégia Concreta: Implementa o cálculo de Stress Testing.
 *
 * Padrão de Projeto: Strategy (Componente Concreto)
 *
 * Princípios SOLID:
 * - SRP (Responsabilidade Única): Responsabilidade única de calcular o Stress Testing.
 */
public class StressTestingStrategy implements RiskCalculationStrategy {
    @Override
    public String calculateRisk(FinancialContext context) {
        // Cálculo dummy: Simula um cenário de estresse (e.g., crise de 2008)
        // Usa a janela histórica como um fator de volatilidade fictício para o cálculo
        double volatilityFactor = context.getHistoricalWindowDays() / 365.0; 
        double stressLoss = context.getPortfolioValue() * 0.35 * volatilityFactor; // Perda simulada
        
        return "Cálculo de Stress Testing concluído: Perda Máxima no Cenário de Estresse (Fator Vol. " + 
               String.format("%.2f", volatilityFactor) + "): R$" +
               String.format("%,.2f", stressLoss);
    }
}
