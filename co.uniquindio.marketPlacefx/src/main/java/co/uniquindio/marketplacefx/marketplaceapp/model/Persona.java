package co.uniquindio.marketplacefx.marketplaceapp.model;

public class Persona {
    public String nombre;
    public String apellido;
    public String cedula;
    public String direccion;
    public Usuario usuario;

    public Persona(String nombre, String apellido, String direccion, String cedula,Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.cedula = cedula;
        this.usuario = usuario;
    }
    public Persona(){
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) { this.direccion = direccion;
    }
}
