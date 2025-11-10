/**
 * NFeDocument.java
 * Contém o estado do documento sendo validado.
 */
public class NFeDocument {
    public final String id;
    public boolean isValid = true; // Status de validade geral
    public String motivoFalha = "";
    
    // Status que o DBValidator irá alterar
    public boolean dbRecordCreated = false; 

    public NFeDocument(String id) {
        this.id = id;
    }
}
