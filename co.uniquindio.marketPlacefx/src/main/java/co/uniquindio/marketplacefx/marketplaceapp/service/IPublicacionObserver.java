package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.model.Publicacion;

public interface IPublicacionObserver {
    void notificar(Publicacion publicacion);
}
