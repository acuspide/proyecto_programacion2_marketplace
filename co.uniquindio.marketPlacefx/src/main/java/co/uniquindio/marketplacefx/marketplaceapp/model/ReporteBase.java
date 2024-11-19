package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.service.IReporte;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReporteBase implements IReporte {

    @Override
    public String getReporte() {
        return "Reporte estadisticas MarketPlace\n"+
                "Fecha: "+(new SimpleDateFormat("dd/MM/yyyy").format(new Date()))+"\n"+
                "Reporte realizado por: Raul\n\nInformación del reporte:\n";
    }
}
