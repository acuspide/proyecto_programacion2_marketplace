package co.uniquindio.marketplacefx.marketplaceapp.controller;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.SugerenciaDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;

import java.util.Collection;
import java.util.List;

public class MarketPlaceController {
    private static ModelFactory modelFactory;

    public MarketPlaceController() {
        modelFactory = ModelFactory.getInstance();
    }


    public List<Usuario> obtenerListaUsuariosVendedores() {
        return modelFactory.obtenerListaUsuariosVendedores();
    }

    public String obtenerUsuarioActual() {
       return modelFactory.obtenerUsuarioActual();
    }


    public List<SugerenciaDto> obtenerUsuarioContacto(String usuarioActual) {
        return modelFactory.obtenerUsuarioContacto(usuarioActual);
    }
}
