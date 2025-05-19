package recibos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import agendamentos.TicketAgendamento;

public class Recibo implements Serializable {
    private String id;
    private LocalDate dataEmissao;
    private TicketAgendamento ticket;
    private Map<String, Double> valores; // item -> valor em euros
    private double iva;
    private double total;

    public Recibo(String id, LocalDate dataEmissao, TicketAgendamento ticket,
                  Map<String, Double> valores, double iva, double total) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.ticket = ticket;
        this.valores = valores;
        this.iva = iva;
        this.total = total;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    public TicketAgendamento getTicket() { return ticket; }
    public void setTicket(TicketAgendamento ticket) { this.ticket = ticket; }

    public Map<String, Double> getValores() { return valores; }
    public void setValores(Map<String, Double> valores) { this.valores = valores; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}