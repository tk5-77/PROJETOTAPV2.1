package quiosques;

import java.io.Serializable;

public class Compartimento implements Serializable {
    private String id;
    private boolean disponivel;
    private boolean modoEntrega;
    private String chavePublica;
    private String chavePrivada;

    public Compartimento(String id, boolean disponivel, boolean modoEntrega, String chavePublica, String chavePrivada) {
        this.id = id;
        this.disponivel = disponivel;
        this.modoEntrega = modoEntrega;
        this.chavePublica = chavePublica;
        this.chavePrivada = chavePrivada;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public boolean isModoEntrega() { return modoEntrega; }
    public void setModoEntrega(boolean modoEntrega) { this.modoEntrega = modoEntrega; }

    public String getChavePublica() { return chavePublica; }
    public void setChavePublica(String chavePublica) { this.chavePublica = chavePublica; }

    public String getChavePrivada() { return chavePrivada; }
    public void setChavePrivada(String chavePrivada) { this.chavePrivada = chavePrivada; }

    public boolean abrirComChave(String chaveDigitada) {
        return chaveDigitada.equals(chavePrivada);
    }

    public boolean fecharComChave(String chaveDigitada) {
        return chaveDigitada.equals(chavePublica);
    }
}