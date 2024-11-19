package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;

import java.util.List;

public interface IProducto {
    String getNombre();
    String getId();
    String getCategoria();
    double getPrecio();
    String getRutaImagen();
    String getDescripcion();
    String getEstado();

}