package Soporte;

import Negocio.Agrupacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArchivoDeTexto {
    private File archivo;

    public ArchivoDeTexto (String ruta){
        archivo = new File(ruta);
    }

    public TSBHashtable seleccionarAgrupaciones() {
        String linea = "", campos[];
        TSBHashtable tabla = new TSBHashtable(10);
        try {
            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNext()){
                linea = entrada.nextLine();
                campos = linea.split("\\|");

                if (campos[0].compareTo("000100000000000") == 0) {
                    Agrupacion agrupacion = new Agrupacion(campos[2], campos[3]);
                    tabla.put(agrupacion.getCodigo(), agrupacion);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tabla;
    }

    public void sumarVotosAgrupaciones(TSBHashtable tabla) {
        String linea = "", campos[];
        Agrupacion agrupacion;
        int votos;

        try {
            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNext()){
                linea = entrada.nextLine();
                campos = linea.split("\\|");

                if (campos[4].compareTo("000100000000000") == 0) {
                    agrupacion = (Agrupacion) tabla.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.sumarVotos(votos);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
