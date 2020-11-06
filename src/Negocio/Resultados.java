package Negocio;

import Soporte.ArchivoDeTexto;
import Soporte.TSBHashtable;
import java.util.Collection;

public class Resultados {
    private TSBHashtable tabla;

    public Resultados(String ruta) {
        tabla = new TSBHashtable();
        ArchivoDeTexto archivoMesas = new ArchivoDeTexto(ruta + "\\mesas_totales_agrp_politica.dsv");
        archivoMesas.sumarVotosPorRegion(this);
    }

    public void sumarVotos(String codigoRegion, String codigoAgrupacion, int votos) {
        if (tabla.get(codigoRegion) == null) {
            tabla.put(codigoRegion, new Agrupaciones());
        }
        Agrupaciones agrupaciones = (Agrupaciones) tabla.get(codigoRegion);
        agrupaciones.getAgrupacion(codigoAgrupacion).sumarVotos(votos);
    }

    public Collection getResultadosRegion(String codigoRegion) {
        Agrupaciones agrupaciones = (Agrupaciones) tabla.get(codigoRegion);
        return agrupaciones.getResultados();
    }
}