package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.model.builder.ProductoBuilder;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String nombre;
    private String id;
    private String categoria;
    private double precio;
    private String rutaImagen;
    private Image imagen;
    private String descripcion;



    public Producto (String nombre, String id, String categoria, double precio, String imagen,
                    String descripcion) {
        this.id = id;
        this.categoria = categoria;
        this.rutaImagen = imagen;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = new Image("file:" + rutaImagen);
    }
    public Producto(){}



    public Image getImagen() { return imagen;}


    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }


    public static ProductoBuilder builder(){
        return new ProductoBuilder();
    }
}
