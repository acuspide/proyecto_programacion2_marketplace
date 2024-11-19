package co.uniquindio.marketplacefx.marketplaceapp.model;

public class Usuario {
    private String nickUsuario;
    private String contrasena;
    private String cedulaAsociada;

    public Usuario(String nickUsuario, String contrasena, String cedulaAsociada) {
        this.nickUsuario = nickUsuario;
        this.contrasena = contrasena;
        this.cedulaAsociada=cedulaAsociada;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getCedulaAsociada() {
        return cedulaAsociada;
    }

    public void setCedulaAsociada(String cedulaAsociada) {
        this.cedulaAsociada = cedulaAsociada;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
