package suporte;

import java.io.Serializable;
import utilizadores.Morada;

public class Funcionario implements Serializable {
    private String nome;
    private String contacto;

    public Funcionario(String nome, String contacto) {
        this.nome = nome;
        this.contacto = contacto;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public Morada getMorada() {
        throw new UnsupportedOperationException("Unimplemented method 'getMorada'");
    }
}
