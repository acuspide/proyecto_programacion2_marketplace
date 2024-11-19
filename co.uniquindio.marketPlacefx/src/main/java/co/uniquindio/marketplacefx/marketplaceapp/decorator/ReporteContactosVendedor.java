package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;
import co.uniquindio.marketplacefx.marketplaceapp.viewcontroller.AdministradorViewController;

public class ReporteContactosVendedor extends ReporteDecorator{


    public ReporteContactosVendedor(IReporte reporte) {
        super(reporte);
    }

    @Override
    public String getReporte() {

        return super.getReporte()+modelFactory.contactosPorVendedor();
    }
}
