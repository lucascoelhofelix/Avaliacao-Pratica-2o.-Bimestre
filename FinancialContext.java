// FinancialContext.java
/**
 * Contexto Complexo de Dados: Armazena os múltiplos parâmetros financeiros.
 *
 * Princípio SOLID:
 * - SRP: Tem a responsabilidade única de agregar e gerenciar os dados de entrada
 * para os cálculos de risco.
 */
public class FinancialContext {
    private double portfolioValue;
    private double confidenceLevel; // Nível de confiança (e.g., 0.95, 0.99)
    private int historicalWindowDays; // Janela de dados históricos

    // Construtor e Getters/Setters (omissos para brevidade)

    public FinancialContext(double portfolioValue, double confidenceLevel, int historicalWindowDays) {
        this.portfolioValue = portfolioValue;
        this.confidenceLevel = confidenceLevel;
        this.historicalWindowDays = historicalWindowDays;
    }

    public double getPortfolioValue() { return portfolioValue; }
    public double getConfidenceLevel() { return confidenceLevel; }
    public int getHistoricalWindowDays() { return historicalWindowDays; }

    @Override
    public String toString() {
        return "Contexto Financeiro [Valor Portfólio: " + portfolioValue + 
               ", Nível Confiança: " + confidenceLevel + 
               ", Janela Histórica: " + historicalWindowDays + " dias]";
    }
}
