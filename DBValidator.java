// DBValidator.java
public class DBValidator extends AbstractValidator {
    @Override public String getNome() { return "Validador de Banco de Dados"; }
    @Override protected long getTimeoutSeconds() { return 1; }

    @Override
    protected boolean performValidation(NFeDocument doc) {
        // Simulação de verificação de duplicidade e inserção temporária
        if (doc.id.equals("DUP-FAIL")) {
            doc.motivoFalha = "Documento já registrado no DB.";
            return false;
        }
        
        // Simula a inserção de um registro provisório no DB (ação que exige rollback)
        doc.dbRecordCreated = true;
        System.out.println("DB Action: Registro temporário inserido para NF-e: " + doc.id);
        
        return true;
    }

    // Rollback obrigatório se validações posteriores falharem
    @Override
    public void rollback(NFeDocument doc) {
        if (doc.dbRecordCreated) {
            // Simula a exclusão do registro temporário
            doc.dbRecordCreated = false;
            System.out.println("DB Rollback: Registro temporário da NF-e " + doc.id + " DELETADO.");
        }
    }
}
