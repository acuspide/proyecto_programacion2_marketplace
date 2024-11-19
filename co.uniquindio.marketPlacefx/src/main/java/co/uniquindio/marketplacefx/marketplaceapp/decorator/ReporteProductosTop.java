package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

public class ReporteProductosTop extends ReporteDecorator{
    public ReporteProductosTop(IReporte reporte) {
        super(reporte);
    }

    @Override
    public String getReporte() {
        return super.getReporte()+modelFactory.obtenerProductosTop();
    }
}
