/**
 * CondicoesOperacionais.java
 *
 * Contém os dados complexos (temperatura, pressão) que regem as transições.
 * Simula a leitura dos sensores em tempo real.
 */
public class CondicoesOperacionais {
    public double temperaturaC;
    public double pressaoMPa;
    public double nivelRadiacao;
    public long tempoAlertaAmareloSegundos = 0; // Para regra de 30 segundos
    public boolean falhaResfriamento = false;

    public CondicoesOperacionais(double temp, double press, double rad) {
        this.temperaturaC = temp;
        this.pressaoMPa = press;
        this.nivelRadiacao = rad;
    }
}
