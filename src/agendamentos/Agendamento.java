package agendamentos;
import java.io.Serializable;
import java.time.LocalDate;

public class Agendamento implements Serializable {
    private TicketAgendamento ticket;
    private LocalDate dataAgendada;
    private boolean concluido;

    public Agendamento(TicketAgendamento ticket, LocalDate dataAgendada, boolean concluido) {
        this.ticket = ticket;
        this.dataAgendada = dataAgendada;
        this.concluido = concluido;
    }

    public TicketAgendamento getTicket() { return ticket; }
    public void setTicket(TicketAgendamento ticket) { this.ticket = ticket; }

    public LocalDate getDataAgendada() { return dataAgendada; }
    public void setDataAgendada(LocalDate dataAgendada) { this.dataAgendada = dataAgendada; }

    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}