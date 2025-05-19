package suporte;
import java.io.Serializable;

import utilizadores.Morada;

public class Funcionario implements Serializable {
    private String nome;
    private Morada morada;

    public Funcionario(String nome, Morada morada) {
        this.nome = nome;
        this.morada = morada;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Morada getMorada() { return morada; }
    public void setMorada(Morada morada) { this.morada = morada; }
}