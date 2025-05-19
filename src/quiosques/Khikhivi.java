package quiosques;
import java.util.List;

import areas.AreaTrabalho;
import suporte.Responsavel;
import utilizadores.Morada;

public class Khikhivi extends QuiosqueBase {
    private List<AreaTrabalho> areasTrabalho;
    private Responsavel manutencao;
    private Responsavel higiene;

    public Khikhivi(String id, String nome, Morada morada,
                    List<AreaTrabalho> areasTrabalho, Responsavel manutencao, Responsavel higiene) {
        super(id, nome, morada);
        this.areasTrabalho = areasTrabalho;
        this.manutencao = manutencao;
        this.higiene = higiene;
    }

    public List<AreaTrabalho> getAreasTrabalho() { return areasTrabalho; }
    public void setAreasTrabalho(List<AreaTrabalho> areasTrabalho) { this.areasTrabalho = areasTrabalho; }

    public Responsavel getManutencao() { return manutencao; }
    public void setManutencao(Responsavel manutencao) { this.manutencao = manutencao; }

    public Responsavel getHigiene() { return higiene; }
    public void setHigiene(Responsavel higiene) { this.higiene = higiene; }
}