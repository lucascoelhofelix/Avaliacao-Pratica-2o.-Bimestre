/**
 * RiskCalculationStrategy.java
 *
 * Interface Strategy (Estratégia): Define o contrato comum para todos os algoritmos de cálculo de risco.
 *
 * Padrão de Projeto: Strategy (Componente Interface)
 *
 * Princípios SOLID:
 * - OCP (Aberto/Fechado): Permite adicionar novas estratégias sem modificar esta interface.
 * - DIP (Inversão de Dependência): O Contexto irá depender desta abstração, não das implementações concretas.
 */
public interface RiskCalculationStrategy {
    /**
     * Executa o cálculo de risco específico e retorna o resultado.
     * @param context Os dados financeiros complexos necessários para o cálculo.
     * @return Uma string representando o resultado do cálculo de risco.
     */
    String calculateRisk(FinancialContext context);
}
