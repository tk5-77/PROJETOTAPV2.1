package quiosques;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import suporte.Responsavel;
import utilizadores.Morada;

public class Khikhipa implements Serializable {
    private String codigoPostal;
    private int capacidade;
    private String id;
    private String nome;
    private Morada morada;
    private List<Compartimento> compartimentos;
    private Responsavel manutencao;
    private Responsavel higiene;

    // Horário de funcionamento fixo
    private final LocalTime abertura = LocalTime.of(6, 0);
    private final LocalTime encerramento = LocalTime.of(23, 0);

    // ✅ CONSTRUTOR AJUSTADO
    public Khikhipa(String nome, Morada morada, List<Compartimento> compartimentos,
                    Responsavel manutencao, Responsavel higiene,
                    String codigoPostal, int capacidade) {

        this.codigoPostal = codigoPostal;
        this.capacidade = capacidade;
        this.id = gerarID(); // ✅ ID gerado automaticamente

        this.nome = nome;
        this.morada = morada;
        this.compartimentos = compartimentos;
        this.manutencao = manutencao;
        this.higiene = higiene;
    }

    public Khikhipa(String id2, String nome2, Morada morada2, List<Compartimento> compartimentos2,
            Responsavel manutencao2, Responsavel higiene2) {
        //TODO Auto-generated constructor stub
    }

    // ✅ Geração automática do ID
    private String gerarID() {
        return codigoPostal + String.format("%02d", capacidade);
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public Morada getMorada() { return morada; }
    public List<Compartimento> getCompartimentos() { return compartimentos; }
    public Responsavel getManutencao() { return manutencao; }
    public Responsavel getHigiene() { return higiene; }

    // Método que retorna o horário de funcionamento formatado
    public String horarioFuncionamento() {
        return String.format("Aberto todos os dias do ano das %02dH%02d às %02dH%02d",
                abertura.getHour(), abertura.getMinute(),
                encerramento.getHour(), encerramento.getMinute());
    }
}
