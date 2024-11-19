package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

public abstract class ReporteDecorator implements IReporte {
    protected IReporte reporte;
    public ModelFactory modelFactory=ModelFactory.getInstance();

    public ReporteDecorator(IReporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public String getReporte() {
        return reporte.getReporte();
    }

}
