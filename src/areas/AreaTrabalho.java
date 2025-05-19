package areas;
import java.io.Serializable;
import java.util.List;

public class AreaTrabalho implements Serializable {
    private String nome;
    private List<Item> itens;

    public AreaTrabalho(String nome, List<Item> itens) {
        this.nome = nome;
        this.itens = itens;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Item> getItens() { return itens; }
    public void setItens(List<Item> itens) { this.itens = itens; }
}