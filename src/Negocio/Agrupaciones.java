package Negocio;

import Soporte.ArchivoDeTexto;
import Soporte.TSBHashtable;

import java.util.Collection;

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

    public Collection getResultados() {
        return tabla.values();
    }
}
