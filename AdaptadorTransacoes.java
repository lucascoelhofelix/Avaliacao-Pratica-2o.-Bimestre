import java.util.HashMap;
import java.util.Map;

/**
 * AdaptadorTransacoes.java
 *
 * Adaptador (Adapter): Implementa a interface moderna (Target) e usa uma instância
 * do legado (Adaptee) para tradução bidirecional (chamada e resposta).
 *
 * Padrão de Projeto: Adapter (Objeto)
 *
 * Princípios SOLID:
 * - SRP: Responsabilidade única de adaptação/tradução.
 */
public class AdaptadorTransacoes implements ProcessadorTransacoes {
    
    // Composição: O adaptador contém uma instância do legado (Adapter de Objeto)
    private final SistemaBancarioLegado legado;

    // Mapeamento exigido pelo legado: USD=1, EUR=2, BRL=3
    private static final Map<String, Integer> CODIFICACAO_MOEDA = Map.of(
        "USD", 1,
        "EUR", 2,
        "BRL", 3
    );

    public AdaptadorTransacoes(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    /**
     * Implementa o método moderno e adapta a chamada para o legado.
     */
    @Override
    public Map<String, Object> autorizar(String cartao, double valor, String moeda) {
        // --- 1. ADAPTAÇÃO DA CHAMADA (MODERNO -> LEGADO) ---
        HashMap<String, Object> parametrosLegado = new HashMap<>();
        
        // A. Conversão de tipos/nomes
        parametrosLegado.put("cartao_hash", cartao); // Exemplo de renomear campo
        parametrosLegado.put("valor", valor);       // Tipo 'double' se torna 'Double'
        
        // B. Codificação Específica (Regra do Legado)
        Integer moedaCod = CODIFICACAO_MOEDA.get(moeda.toUpperCase());
        if (moedaCod == null) {
            throw new IllegalArgumentException("Moeda '" + moeda + "' não suportada pelo Legado.");
        }
        parametrosLegado.put("moeda_cod", moedaCod);
        
        // C. Tratamento de Campos Obrigatórios (O legado exige ID_SESSAO, que não existe no moderno)
        // O Adaptador deve gerar ou buscar este campo antes de chamar o legado.
        parametrosLegado.put("ID_SESSAO", "SES-" + System.currentTimeMillis()); 
        
        // 2. Chamada ao Sistema Legado
        HashMap<String, Object> respostaLegado = legado.processarTransacao(parametrosLegado);
        
        // --- 3. ADAPTAÇÃO DA RESPOSTA (LEGADO -> MODERNO) ---
        return adaptarRespostaLegado(respostaLegado);
    }

    /**
     * Converte o mapa de resposta do legado para o formato de resposta moderno.
     */
    private Map<String, Object> adaptarRespostaLegado(HashMap<String, Object> respostaLegado) {
        Map<String, Object> respostaModerna = new HashMap<>();
        
        Integer codRetorno = (Integer) respostaLegado.get("cod_retorno");
        String mensagem = (String) respostaLegado.get("mensagem");
        
        // Regras de conversão: Cod_retorno 200 no legado significa SUCESSO.
        if (codRetorno != null && codRetorno == 200) {
            respostaModerna.put("status", "APROVADA");
            respostaModerna.put("codigo_resposta", "00"); // Código moderno de sucesso
        } else {
            respostaModerna.put("status", "REJEITADA");
            respostaModerna.put("codigo_resposta", String.valueOf(codRetorno));
        }
        
        // Inclui a mensagem para rastreamento
        respostaModerna.put("detalhe_legado", mensagem); 
        
        return respostaModerna;
    }
}
