package suporte;
import java.io.Serializable;

public class Responsavel implements Serializable {
    private String nome;
    private String contacto;

    public Responsavel(String nome, String contacto) {
        this.nome = nome;
        this.contacto = contacto;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
}