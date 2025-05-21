package quiosques;

import suporte.Funcionario;
import utilizadores.Morada;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Khikhita extends QuiosqueBase {
    private Funcionario funcionario;
    private Map<String, Integer> maximoPorTipo; // Máximo permitido por tipo de caixa
    private Map<String, Integer> atuaisPorTipo; // Quantidade atual por tipo

    private LocalTime abertura;
    private LocalTime encerramento;

    private String codigoPostal;
    private int capacidade;
    private String id;

    public Khikhita(String nome, Morada morada, Funcionario funcionario,
            Map<String, Integer> maximoPorTipo, String codigoPostal, int capacidade,
            LocalTime abertura, LocalTime encerramento) {
        super(null, nome, morada); // ID será gerado e depois definido
        this.funcionario = funcionario;
        this.maximoPorTipo = maximoPorTipo;
        this.atuaisPorTipo = new HashMap<>();
        this.codigoPostal = codigoPostal;
        this.capacidade = capacidade;
        this.abertura = abertura;
        this.encerramento = encerramento;

        // Inicializar contadores a 0
        for (String tipo : maximoPorTipo.keySet()) {
            atuaisPorTipo.put(tipo, 0);
        }

        this.id = gerarID();
        setId(this.id); // atualiza o ID no QuiosqueBase
    }

    public Khikhita(String id2, String nome, Morada morada, Funcionario funcionario2, Map<String, Integer> limites) {
        super(id2, nome, morada); // Call the appropriate constructor of QuiosqueBase
        this.funcionario = funcionario2;
        this.maximoPorTipo = limites;
        this.atuaisPorTipo = new HashMap<>();
        this.codigoPostal = ""; // Set appropriately if needed
        this.capacidade = 0; // Set appropriately if needed
        this.abertura = null; // Set appropriately if needed
        this.encerramento = null; // Set appropriately if needed

        // Inicializar contadores a 0
        for (String tipo : limites.keySet()) {
            atuaisPorTipo.put(tipo, 0);
        }

        this.id = id2;
        setId(this.id); // atualiza o ID no QuiosqueBase
    }

    private String gerarID() {
        return codigoPostal + String.format("%02d", capacidade);
    }

    public void setMaximoPorTipo(Map<String, Integer> maximoPorTipo) {
        this.maximoPorTipo = maximoPorTipo;

        // Garante que os atuais continuam consistentes (sem valores acima do novo
        // máximo)
        for (String tipo : maximoPorTipo.keySet()) {
            int max = maximoPorTipo.get(tipo);
            int atual = atuaisPorTipo.getOrDefault(tipo, 0);
            if (atual > max) {
                atuaisPorTipo.put(tipo, max); // ajusta se necessário
            } else {
                atuaisPorTipo.putIfAbsent(tipo, 0); // adiciona se não existir
            }
        }
    }

    public void setAbertura(LocalTime abertura) {
        this.abertura = abertura;
    }

    public void setEncerramento(LocalTime encerramento) {
        this.encerramento = encerramento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Map<String, Integer> getMaximoPorTipo() {
        return maximoPorTipo;
    }

    public Map<String, Integer> getAtuaisPorTipo() {
        return atuaisPorTipo;
    }

    public LocalTime getAbertura() {
        return abertura;
    }

    public LocalTime getEncerramento() {
        return encerramento;
    }

    public boolean podeReceberCaixa(String tipo) {
        int atual = atuaisPorTipo.getOrDefault(tipo, 0);
        int maximo = maximoPorTipo.getOrDefault(tipo, 0);
        return atual < maximo;
    }

    public void registarEntradaCaixa(String tipo) {
        atuaisPorTipo.put(tipo, atuaisPorTipo.getOrDefault(tipo, 0) + 1);
    }

    public void registarSaidaCaixa(String tipo) {
        atuaisPorTipo.put(tipo, Math.max(0, atuaisPorTipo.getOrDefault(tipo, 0) - 1));
    }

    public boolean isAberto(LocalTime agora) {
        return !agora.isBefore(abertura) && !agora.isAfter(encerramento);
    }
}
