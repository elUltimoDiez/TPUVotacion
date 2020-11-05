package Soporte;

import Negocio.Agrupacion;
import Negocio.Region;
import Negocio.Resultados;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArchivoDeTexto {
    private File archivo;

    public ArchivoDeTexto (String ruta){
        archivo = new File(ruta);
    }

    public TSBHashtableDA seleccionarAgrupaciones() {
        String linea = "", campos[];
        TSBHashtableDA tabla = new TSBHashtableDA(10);
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

    public Region seleccionarRegiones() {
        String linea = "", campos[], codigo, nombre;
        Region pais = new Region("00", "Argentina");
        Region distrito, seccion;
        try {
            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNext()){
                linea = entrada.nextLine();
                campos = linea.split("\\|");
                codigo = campos[0];
                nombre = campos[1];
                switch (codigo.length()){
                    case 2:
                        //Distrito
                        distrito = pais.getOrPutSubregion(codigo);
                        distrito.setNombre(nombre);
                        break;
                    case 5:
                        //Seccion
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo);
                        seccion.setNombre(nombre);
                        break;
                    case 11:
                        //Circuito
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo.substring(0, 5));
                        seccion.agregarSubregion(new Region(codigo, nombre));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pais;
    }

    public void sumarVotosAgrupaciones(TSBHashtableDA tabla) {
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

    public void sumarVotosPorRegion(Resultados resultados) {
        String linea = "", campos[];
        int votos;
        try {
            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNext()){
                linea = entrada.nextLine();
                campos = linea.split("\\|");
                if (campos[4].compareTo("000100000000000") == 0) {
                    votos = Integer.parseInt(campos[6]);
                    //Votos del pais
                    resultados.sumarVotos("00", campos[5], votos);
                    //Votos del distrito, seccion y circuito
                    for (int i=0; i<3; i++){
                        resultados.sumarVotos(campos[i], campos[5], votos);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
