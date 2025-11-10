import java.util.HashMap;
import java.util.Map;

/**
 * SistemaBancarioLegado.java
 *
 * Sistema Legado (Adaptee): Possui a interface incompatível e lógica obsoleta.
 *
 * Restrições: Usa HashMap<String, Object> e espera campos específicos.
 */
public class SistemaBancarioLegado {
    
    /**
     * Método do legado com assinatura incompatível e tipos obsoletos.
     * Espera um mapa com keys específicas e valores (e.g., 'moeda_cod', 'status').
     * @param parametros HashMap contendo todos os dados (cartao, valor, moeda_cod, etc.).
     * @return Um HashMap com a resposta do processamento (e.g., 'cod_retorno', 'mensagem').
     */
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("\n--- Chamada ao Sistema Legado ---");
        System.out.println("Parâmetros recebidos no formato legado: " + parametros);
        
        // 1. Verificação de Campos Obrigatórios do Legado
        if (!parametros.containsKey("ID_SESSAO")) {
            System.err.println("ERRO LEGADO: Campo obrigatório 'ID_SESSAO' ausente!");
            HashMap<String, Object> erro = new HashMap<>();
            erro.put("cod_retorno", 99);
            erro.put("mensagem", "FALHA: ID_SESSAO faltando.");
            return erro;
        }

        // 2. Lógica de Processamento Dummy do Legado
        Double valor = (Double) parametros.get("valor");
        Integer moedaCod = (Integer) parametros.get("moeda_cod");
        
        HashMap<String, Object> respostaLegado = new HashMap<>();
        
        if (valor != null && valor > 10000.0) {
            System.out.println("Transação de alto valor detectada. Rejeitando no legado.");
            respostaLegado.put("cod_retorno", 401);
            respostaLegado.put("mensagem", "REJEITADO: Limite Excedido (Moeda: " + moedaCod + ")");
        } else {
            System.out.println("Transação de R$" + String.format("%.2f", valor) + " APROVADA no Legado.");
            respostaLegado.put("cod_retorno", 200);
            respostaLegado.put("mensagem", "OK: Transação Autorizada (ID: " + System.currentTimeMillis() + ")");
        }
        
        return respostaLegado;
    }
}
