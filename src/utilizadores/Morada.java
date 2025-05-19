package utilizadores;
import java.io.Serializable;

public class Morada implements Serializable {
    private String rua;
    private int numeroPorta;
    private String codigoPostal;
    private String localidade;

    public Morada(String rua, int numeroPorta, String codigoPostal, String localidade) {
        this.rua = rua;
        this.numeroPorta = numeroPorta;
        this.codigoPostal = codigoPostal;
        this.localidade = localidade;
    }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public int getNumeroPorta() { return numeroPorta; }
    public void setNumeroPorta(int numeroPorta) { this.numeroPorta = numeroPorta; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }
}