package areas;
import java.io.Serializable;

public class Item implements Serializable {
    private String nome;
    private boolean operacional;

    public Item(String nome, boolean operacional) {
        this.nome = nome;
        this.operacional = operacional;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isOperacional() { return operacional; }
    public void setOperacional(boolean operacional) { this.operacional = operacional; }
}