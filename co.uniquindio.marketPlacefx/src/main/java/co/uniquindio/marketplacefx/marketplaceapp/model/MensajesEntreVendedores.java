package co.uniquindio.marketplacefx.marketplaceapp.model;

public class MensajesEntreVendedores {
    public String remitente;

    public MensajesEntreVendedores(String remitente, String destinatario, int cantidad) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.cantidad = cantidad;
    }

    public String destinatario;
    int cantidad;
}
