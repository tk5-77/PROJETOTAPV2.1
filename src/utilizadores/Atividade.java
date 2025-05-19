package utilizadores;
import java.io.Serializable;

public class Atividade implements Serializable {
    private String nome;
    private String definicao;
    private double tempoMedioHoras;
    private double custoMedioHora;

    public Atividade(String nome, String definicao, double tempoMedioHoras, double custoMedioHora) {
        this.nome = nome;
        this.definicao = definicao;
        this.tempoMedioHoras = tempoMedioHoras;
        this.custoMedioHora = custoMedioHora;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDefinicao() { return definicao; }
    public void setDefinicao(String definicao) { this.definicao = definicao; }

    public double getTempoMedioHoras() { return tempoMedioHoras; }
    public void setTempoMedioHoras(double tempoMedioHoras) { this.tempoMedioHoras = tempoMedioHoras; }

    public double getCustoMedioHora() { return custoMedioHora; }
    public void setCustoMedioHora(double custoMedioHora) { this.custoMedioHora = custoMedioHora; }
}