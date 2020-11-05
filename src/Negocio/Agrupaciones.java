package Negocio;

import Soporte.ArchivoDeTexto;
import Soporte.TSBHashtableDA;
import java.util.Collection;

public class Agrupaciones {
    private static TSBHashtableDA inicial;
    private TSBHashtableDA tablaVotacion;

    public Agrupaciones (){
        tablaVotacion = new TSBHashtableDA();
        for (Object o : inicial.values()) {
            Agrupacion agrupacion = (Agrupacion) o;
            tablaVotacion.put(agrupacion.getCodigo(), new Agrupacion(agrupacion.getCodigo(), agrupacion.getNombre()));
        }
    }

    public static void leerAgrupaciones(String ruta){
        ArchivoDeTexto archivoAgrupaciones = new ArchivoDeTexto(ruta + "\\descripcion_postulaciones.dsv");
        inicial = archivoAgrupaciones.seleccionarAgrupaciones();
    }

    public Agrupacion getAgrupacion(String codigoAgrupacion){
        return (Agrupacion) tablaVotacion.get(codigoAgrupacion);
    }

    public Collection getResultados() {
        return tablaVotacion.values();
    }
}
