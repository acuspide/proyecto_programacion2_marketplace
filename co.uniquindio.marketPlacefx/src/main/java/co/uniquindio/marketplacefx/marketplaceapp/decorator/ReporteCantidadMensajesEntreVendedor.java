package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

public class ReporteCantidadMensajesEntreVendedor extends ReporteDecorator{

    public ReporteCantidadMensajesEntreVendedor(IReporte reporte) {
        super(reporte);
    }

    @Override
    public String getReporte() {

        return super.getReporte()+modelFactory.cantidadMensajesEntreVendedores();
    }
}
