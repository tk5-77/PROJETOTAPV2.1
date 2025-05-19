package caixas;
import java.io.Serializable;

public class Caixa implements Serializable {
    private String id;
    private TipoCaixa tipo;
    private boolean ativa;

    public Caixa(String id, TipoCaixa tipo, boolean ativa) {
        this.id = id;
        this.tipo = tipo;
        this.ativa = ativa;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public TipoCaixa getTipo() { return tipo; }
    public void setTipo(TipoCaixa tipo) { this.tipo = tipo; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}