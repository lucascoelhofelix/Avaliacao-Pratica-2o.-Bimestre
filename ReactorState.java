/**
 * ReactorState.java
 *
 * Interface State (Estado): Define os métodos de transição para todos os estados.
 */
public interface ReactorState {
    String getNomeEstado();
    void verificarEReagir(ReactorContext context, CondicoesOperacionais condicoes);
    
    // Método de transição bidirecional para Desligar (Segurança)
    void desligar(ReactorContext context); 
}
