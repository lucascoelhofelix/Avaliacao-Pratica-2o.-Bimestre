import java.util.Map;

public class MainValidation {
    public static void main(String[] args) {
        // Resetar o contador de falhas para o início de cada teste
        AbstractValidator.resetCircuitBreaker();
        
        // 1. Configurar a Cadeia de Responsabilidade
        // Segue a ordem: Schema -> DB -> Regras Fiscais -> SEFAZ
        // Os validadores de Certificado Digital e de Regras Fiscais são executados
        // apenas se os anteriores passarem, o que é garantido pela lógica do handle().
        
        NFeValidator schema = new SchemaValidator();
        NFeValidator db = new DBValidator();
        NFeValidator fiscal = new RegrasFiscaisValidator();
        NFeValidator sefaz = new SefazValidator();
        
        // A lógica de setNext monta a cadeia.
        schema.setNext(db).setNext(fiscal).setNext(sefaz);
    }
}
