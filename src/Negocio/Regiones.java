package Negocio;

import Soporte.ArchivoDeTexto;

import java.util.Collection;

public class Regiones {
    private ArchivoDeTexto archivoRegiones;
    private Region pais;

    public Regiones (String ruta){
        archivoRegiones = new ArchivoDeTexto(ruta + "\\descripcion_regiones.dsv");
        //archivoMesas = new ArchivoDeTexto(ruta + "\\mesas_totales_agrp_politica.dsv");
         pais = archivoRegiones.seleccionarRegiones();
    }

    public Collection getDistritos() {
        return  pais.getSubregiones();
    }
}
