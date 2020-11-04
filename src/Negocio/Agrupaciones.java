package Negocio;

import Soporte.ArchivoDeTexto;
import Soporte.TSBHashtable;

public class Agrupaciones {
    private ArchivoDeTexto archivoAgrupaciones;
    private ArchivoDeTexto archivoMesas;
    private TSBHashtable tabla;

    public Agrupaciones (String ruta){
        archivoAgrupaciones = new ArchivoDeTexto(ruta + "\\descripcion_postulaciones.dsv");
        archivoMesas = new ArchivoDeTexto(ruta + "\\mesas_totales_agrp_politica.dsv");
        tabla = archivoAgrupaciones.seleccionarAgrupaciones();
    }

    public String sumarVotos(){
        archivoMesas.sumarVotosAgrupaciones(tabla);
        return toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (Object o : tabla.values())
            sb.append("\n" + o);
            return sb.toString();
    }
}
