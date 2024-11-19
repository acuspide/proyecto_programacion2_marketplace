package co.uniquindio.marketplacefx.marketplaceapp.mapping.dto;

import java.time.LocalDate;

public record ProductoDto (String nombre,
                           String id,
                           String categoria,
                           double precio,
                           String rutaImagen,
                           String descripcion){



    @Override
    public String toString() {
        return ""+nombre+" precio: "+precio+"-> Descripcion"+descripcion;
    }

}
