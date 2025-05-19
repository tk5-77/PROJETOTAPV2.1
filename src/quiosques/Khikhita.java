package quiosques;
import java.util.Map;

import suporte.Funcionario;
import utilizadores.Morada;

public class Khikhita extends QuiosqueBase {
    private Funcionario funcionario;
    private Map<String, Integer> maximoPorTipo; // ID tipo de caixa -> quantidade

    public Khikhita(String id, String nome, Morada morada, Funcionario funcionario, Map<String, Integer> maximoPorTipo) {
        super(id, nome, morada);
        this.funcionario = funcionario;
        this.maximoPorTipo = maximoPorTipo;
    }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Map<String, Integer> getMaximoPorTipo() { return maximoPorTipo; }
    public void setMaximoPorTipo(Map<String, Integer> maximoPorTipo) { this.maximoPorTipo = maximoPorTipo; }
}