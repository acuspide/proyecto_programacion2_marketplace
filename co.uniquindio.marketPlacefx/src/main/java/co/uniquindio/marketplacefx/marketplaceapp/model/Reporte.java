package co.uniquindio.marketplacefx.marketplaceapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporte {
    private String nombre;
    public Reporte(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String mensaje;
}
