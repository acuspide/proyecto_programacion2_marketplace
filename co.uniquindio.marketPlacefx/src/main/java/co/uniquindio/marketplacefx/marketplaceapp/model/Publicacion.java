package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.service.IPublicacionObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    public String nombre;
    public String idProducto;
    public String descripcion;
    public LocalDate fecha;
    public List<Comentario> comentarios= new ArrayList<>();
    public List<String> likes = new ArrayList<>();
    public List<IPublicacionObserver>observadores = new ArrayList<>();
    public String nickName;

    public Publicacion(String nombre,String idProducto, String descripcion, List<String> likes, LocalDate fecha, List<Comentario> comentarios, String nickName) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.comentarios = comentarios;
        this.nickName = nickName;

    }
    public Publicacion() {}

    public List<IPublicacionObserver> getObservadores() {
        return observadores;
    }

    public String getNickName() {
        return nickName;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLikes() {
        return likes.size();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }


    public void reportarPublicacion() {
        for (IPublicacionObserver observador : observadores) {
            observador.notificar(this);
        }
    }
}