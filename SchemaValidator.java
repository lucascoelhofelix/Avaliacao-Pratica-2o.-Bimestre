// SchemaValidator.java
public class SchemaValidator extends AbstractValidator {
    @Override public String getNome() { return "Validador Schema XML"; }
    @Override protected long getTimeoutSeconds() { return 2; }

    @Override
    protected boolean performValidation(NFeDocument doc) {
        // Simulação de validação (sempre passa a menos que seja o ID "XML-FAIL")
        if (doc.id.equals("XML-FAIL")) {
            doc.motivoFalha = "Estrutura XML inválida (XSD).";
            return false;
        }
        return true;
    }
}
