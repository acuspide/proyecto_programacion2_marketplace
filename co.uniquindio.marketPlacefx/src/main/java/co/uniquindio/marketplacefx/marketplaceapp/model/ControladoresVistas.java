package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.viewcontroller.VendedorViewController;

import java.util.List;

public class ControladoresVistas {
    public VendedorViewController controlador;
    public String nickName;
    public ControladoresVistas(VendedorViewController controlador, String nickName) {
        this.controlador = controlador;
        this.nickName = nickName;
    }

}
