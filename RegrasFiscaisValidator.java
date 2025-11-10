// RegrasFiscaisValidator.java
public class RegrasFiscaisValidator extends AbstractValidator {
    @Override public String getNome() { return "Validador de Regras Fiscais"; }
    @Override protected long getTimeoutSeconds() { return 3; }

    @Override
    protected boolean performValidation(NFeDocument doc) {
        // Simulação: Falha se o ID for "FISCAL-FAIL"
        if (doc.id.equals("FISCAL-FAIL")) {
            doc.motivoFalha = "Cálculo de ICMS/IPI inválido.";
            return false;
        }
        return true;
    }
}
