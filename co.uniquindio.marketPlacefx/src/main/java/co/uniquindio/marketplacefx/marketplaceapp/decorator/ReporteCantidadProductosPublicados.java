package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.model.Reporte;
import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

public class ReporteCantidadProductosPublicados extends ReporteDecorator{
    public String nickName;
    public ReporteCantidadProductosPublicados(IReporte reporte,String nickName) {
        super(reporte);
        this.nickName = nickName;
    }


    @Override
    public String getReporte() {
        return super.getReporte()+modelFactory.cantidadProductosPublicados(nickName);
    }

}
