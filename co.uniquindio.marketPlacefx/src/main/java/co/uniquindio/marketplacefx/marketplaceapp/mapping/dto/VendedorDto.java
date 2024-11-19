package co.uniquindio.marketplacefx.marketplaceapp.mapping.dto;

import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;

public record VendedorDto (String nombre,
                           String apellido,
                           String cedula,
                           String direccion,
                           Usuario usuario){
}
