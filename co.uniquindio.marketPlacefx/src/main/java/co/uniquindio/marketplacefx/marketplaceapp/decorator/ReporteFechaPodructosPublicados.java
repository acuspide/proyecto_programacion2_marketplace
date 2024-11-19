package co.uniquindio.marketplacefx.marketplaceapp.decorator;

import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

import java.time.LocalDate;

public class ReporteFechaPodructosPublicados extends ReporteDecorator{
    public LocalDate fechaPublicacion;
    public ReporteFechaPodructosPublicados(IReporte reporte, LocalDate fechaPublicacion) {
        super(reporte);
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String getReporte() {
        return super.getReporte()+modelFactory.obtenerPublicacionesFecha(fechaPublicacion);
    }
}
