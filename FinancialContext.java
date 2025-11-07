// FinancialContext.java
/**
 * DTO que encapsula os parâmetros financeiros complexos (Contexto)
 * para serem compartilhados entre os diferentes algoritmos de risco.
 */
public class FinancialContext {
    private final double portfolioValue;
    private final double confidenceLevel;
    private final int timeHorizonDays;

    public FinancialContext(double portfolioValue, double confidenceLevel, int timeHorizonDays) {
        this.portfolioValue = portfolioValue;
        this.confidenceLevel = confidenceLevel;
        this.timeHorizonDays = timeHorizonDays;
    }

    // Getters para acessar os dados (omissos para brevidade, mas necessários)
    public double getPortfolioValue() { return portfolioValue; }
    public double getConfidenceLevel() { return confidenceLevel; }
    public int getTimeHorizonDays() { return timeHorizonDays; }
}
