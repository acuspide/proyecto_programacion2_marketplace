package co.uniquindio.marketplacefx.marketplaceapp.model.builder;

import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;


public class VendedorBuilder {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected Usuario usuario;


    public VendedorBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }


    public VendedorBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public VendedorBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public VendedorBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public VendedorBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    public Vendedor build(){
        return new Vendedor(nombre,apellido,cedula,direccion,usuario);
    }
}
