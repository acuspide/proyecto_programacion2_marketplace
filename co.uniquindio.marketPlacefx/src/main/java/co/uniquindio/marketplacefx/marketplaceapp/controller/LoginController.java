package co.uniquindio.marketplacefx.marketplaceapp.controller;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.UsuarioDto;

import java.util.List;

public class LoginController {
    private ModelFactory modelFactory;
    public LoginController() {
        modelFactory = ModelFactory.getInstance();
    }
    public List<UsuarioDto> obtenerListaUsuarios() {
        return modelFactory.obtenerListaUsuarioDto();
    }

    public String obtenerUsuario(UsuarioDto usuario) {
        return modelFactory.obtenerUsuario(usuario);
    }

    public boolean validarUsuario(UsuarioDto usuario) {
        return modelFactory.validaUsuario(usuario);
    }

    public void iniciarVista(String nickname) {
        modelFactory.iniciarVista(nickname);
    }
}
