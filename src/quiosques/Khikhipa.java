package quiosques;

import java.util.List;

import suporte.Responsavel;
import utilizadores.Morada;

public class Khikhipa extends QuiosqueBase {
    private List<Compartimento> compartimentos;
    private Responsavel manutencao;
    private Responsavel higiene;

    public Khikhipa(String id, String nome, Morada morada,
                    List<Compartimento> compartimentos, Responsavel manutencao, Responsavel higiene) {
        super(id, nome, morada);
        this.compartimentos = compartimentos;
        this.manutencao = manutencao;
        this.higiene = higiene;
    }

    public List<Compartimento> getCompartimentos() { return compartimentos; }
    public void setCompartimentos(List<Compartimento> compartimentos) { this.compartimentos = compartimentos; }

    public Responsavel getManutencao() { return manutencao; }
    public void setManutencao(Responsavel manutencao) { this.manutencao = manutencao; }

    public Responsavel getHigiene() { return higiene; }
    public void setHigiene(Responsavel higiene) { this.higiene = higiene; }
}