package quiosques;

import java.io.Serializable;

public class Compartimento implements Serializable {
    private String id; // gerado automaticamente
    private int linha;
    private int coluna;
    private boolean disponivel;
    private boolean modoEntrega;
    private String chavePublica;
    private String chavePrivada;
    private boolean clipeAberto;

    // Construtor vazio necessário para serialização
    public Compartimento() {
        this.id = "";
        this.linha = 0;
        this.coluna = 0;
        this.disponivel = true;
        this.modoEntrega = false;
        this.chavePublica = "";
        this.chavePrivada = "";
        this.clipeAberto = false;
    }

    public Compartimento(String idQuiosque, int linha, int coluna, boolean modoEntrega, String chavePublica,
            String chavePrivada) {
        this.id = gerarID(idQuiosque, linha, coluna);
        this.linha = linha;
        this.coluna = coluna;
        this.modoEntrega = modoEntrega;
        this.disponivel = true;
        this.clipeAberto = false;
        this.chavePublica = chavePublica;
        this.chavePrivada = chavePrivada;
    }

    public Compartimento(String idC, boolean disponivel2, boolean modoEntrega2, String chavePublica2,
            String chavePrivada2) {
    }

    private String gerarID(String idQuiosque, int linha, int coluna) {
        return idQuiosque + String.format("%02d%02d", linha, coluna);
    }

    public String getId() {
        return id;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isModoEntrega() {
        return modoEntrega;
    }

    public void setModoEntrega(boolean modoEntrega) {
        this.modoEntrega = modoEntrega;
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(String chavePublica) {
        this.chavePublica = chavePublica;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(String chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public boolean abrirClipe(String chavePrivadaInput) {
        if (clipeAberto || !chavePrivada.equals(chavePrivadaInput)) {
            return false;
        }
        clipeAberto = true;
        return true;
    }

    public boolean fecharClipe(String chavePublicaInput) {
        if (!clipeAberto || !chavePublica.equals(chavePublicaInput)) {
            return false;
        }
        clipeAberto = false;
        return true;
    }

    public boolean isClipeAberto() {
        return clipeAberto;
    }
}
