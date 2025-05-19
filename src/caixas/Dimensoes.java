package caixas;
import java.io.Serializable;

public class Dimensoes implements Serializable {
    private double largura;
    private double profundidade;
    private double altura;

    public Dimensoes(double largura, double profundidade, double altura) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.altura = altura;
    }

    public double getLargura() { return largura; }
    public void setLargura(double largura) { this.largura = largura; }

    public double getProfundidade() { return profundidade; }
    public void setProfundidade(double profundidade) { this.profundidade = profundidade; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
}