package quiosques;
import java.io.Serializable;

import utilizadores.Morada;

public abstract class QuiosqueBase implements Serializable {
    protected String id;
    protected String nome;
    protected Morada morada;

    public QuiosqueBase(String id, String nome, Morada morada) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Morada getMorada() { return morada; }
    public void setMorada(Morada morada) { this.morada = morada; }
}