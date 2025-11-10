// SefazValidator.java
public class SefazValidator extends AbstractValidator {
    @Override public String getNome() { return "Validador de Serviço SEFAZ"; }
    @Override protected long getTimeoutSeconds() { return 5; } // Timeout maior

    @Override
    protected boolean performValidation(NFeDocument doc) {
        // Simulação de falha por lentidão/timeout
        if (doc.id.equals("TIMEOUT-SEFAZ")) {
            // Simula uma operação que leva 10 segundos, excedendo o timeout de 5s
            try { Thread.sleep(10000); } catch (InterruptedException ignored) {}
        }
        
        // Simulação de falha por rejeição da SEFAZ
        if (doc.id.equals("SEFAZ-REJECT")) {
            doc.motivoFalha = "Rejeição na consulta online SEFAZ.";
            return false;
        }
        return true;
    }
}
