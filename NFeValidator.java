/**
 * NFeValidator.java
 * Interface Handler do Chain of Responsibility.
 */
public interface NFeValidator {
    NFeValidator setNext(NFeValidator next);
    boolean handle(NFeDocument doc);
}
