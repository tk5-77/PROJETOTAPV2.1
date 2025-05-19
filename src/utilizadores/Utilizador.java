package utilizadores;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import caixas.Caixa;

public class Utilizador implements Serializable {
    private String nif;
    private String nome;
    private String email;
    private Morada morada;
    private String contacto;
    private Atividade atividade; // pode ser null
    private List<Caixa> caixas;
    private List<Utilizador> clientes;
    private List<Utilizador> parceiros;

    public Utilizador(String nif, String nome, String email, Morada morada, String contacto, Atividade atividade) {
        this.nif = nif;
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.contacto = contacto;
        this.atividade = atividade;
        this.caixas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.parceiros = new ArrayList<>();
    }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Morada getMorada() { return morada; }
    public void setMorada(Morada morada) { this.morada = morada; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public Atividade getAtividade() { return atividade; }
    public void setAtividade(Atividade atividade) { this.atividade = atividade; }

    public List<Caixa> getCaixas() { return caixas; }
    public List<Utilizador> getClientes() { return clientes; }
    public List<Utilizador> getParceiros() { return parceiros; }
}