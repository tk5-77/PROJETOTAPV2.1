package caixas;
import java.io.Serializable;

public class TipoCaixa implements Serializable {
    private String id; // S, M, L, XL
    private double tara;
    private double carga;
    private double volume;
    private Dimensoes dimensoes;
    private double custo;
    private double custoPorHora;

    public TipoCaixa(String id, double tara, double carga, double volume, Dimensoes dimensoes, double custo, double custoPorHora) {
        this.id = id;
        this.tara = tara;
        this.carga = carga;
        this.volume = volume;
        this.dimensoes = dimensoes;
        this.custo = custo;
        this.custoPorHora = custoPorHora;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getTara() { return tara; }
    public void setTara(double tara) { this.tara = tara; }

    public double getCarga() { return carga; }
    public void setCarga(double carga) { this.carga = carga; }

    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }

    public Dimensoes getDimensoes() { return dimensoes; }
    public void setDimensoes(Dimensoes dimensoes) { this.dimensoes = dimensoes; }

    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }

    public double getCustoPorHora() { return custoPorHora; }
    public void setCustoPorHora(double custoPorHora) { this.custoPorHora = custoPorHora; }
}