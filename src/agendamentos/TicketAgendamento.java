package agendamentos;
import java.io.Serializable;
import java.time.LocalDate;

public class TicketAgendamento implements Serializable {
    private String idQuiosque;
    private String idCompartimentoOuArea;
    private String idSolicitante;
    private String idPrestador;
    private String tipoCaixa; // ou nome da área, se for Khikhivi
    private LocalDate dataEntrega1;
    private LocalDate dataLevantamento1;
    private LocalDate dataEntrega2;
    private LocalDate dataLevantamento2;

    public TicketAgendamento(String idQuiosque, String idCompartimentoOuArea, String idSolicitante,
                              String idPrestador, String tipoCaixa,
                              LocalDate dataEntrega1, LocalDate dataLevantamento1,
                              LocalDate dataEntrega2, LocalDate dataLevantamento2) {
        this.idQuiosque = idQuiosque;
        this.idCompartimentoOuArea = idCompartimentoOuArea;
        this.idSolicitante = idSolicitante;
        this.idPrestador = idPrestador;
        this.tipoCaixa = tipoCaixa;
        this.dataEntrega1 = dataEntrega1;
        this.dataLevantamento1 = dataLevantamento1;
        this.dataEntrega2 = dataEntrega2;
        this.dataLevantamento2 = dataLevantamento2;
    }
    public String getIdQuiosque() {
        return idQuiosque;
    }

    public void setIdQuiosque(String idQuiosque) {
        this.idQuiosque = idQuiosque;
    }

    public String getIdCompartimentoOuArea() {
        return idCompartimentoOuArea;
    }

    public void setIdCompartimentoOuArea(String idCompartimentoOuArea) {
        this.idCompartimentoOuArea = idCompartimentoOuArea;
    }

    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(String idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getTipoCaixa() {
        return tipoCaixa;
    }

    public void setTipoCaixa(String tipoCaixa) {
        this.tipoCaixa = tipoCaixa;
    }

    public LocalDate getDataEntrega1() {
        return dataEntrega1;
    }

    public void setDataEntrega1(LocalDate dataEntrega1) {
        this.dataEntrega1 = dataEntrega1;
    }

    public LocalDate getDataLevantamento1() {
        return dataLevantamento1;
    }

    public void setDataLevantamento1(LocalDate dataLevantamento1) {
        this.dataLevantamento1 = dataLevantamento1;
    }

    public LocalDate getDataEntrega2() {
        return dataEntrega2;
    }

    public void setDataEntrega2(LocalDate dataEntrega2) {
        this.dataEntrega2 = dataEntrega2;
    }

    public LocalDate getDataLevantamento2() {
        return dataLevantamento2;
    }

    public void setDataLevantamento2(LocalDate dataLevantamento2) {
        this.dataLevantamento2 = dataLevantamento2;
    }
}