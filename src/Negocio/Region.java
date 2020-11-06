package Negocio;

import Soporte.TSBHashtable;
import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSBHashtable subregiones;

    public Region(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
        subregiones = new TSBHashtable();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarSubregion(Region region) {
        subregiones.put(region.codigo, region);
    }

    public Collection getSubregiones() {
        return subregiones.values();
    }

    public Region getSubregion(String codigo){
        return (Region) subregiones.get(codigo);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(").append(codigo).append(") ").append(nombre);
        return sb.toString();
    }

    public Region getOrPutSubregion(String codigo) {
        Region subregion = (Region) subregiones.get(codigo);
        if(subregion == null){
            subregiones.put(codigo, new Region(codigo, ""));
            return (Region) subregiones.get(codigo);
        } else{
            return subregion;
        }
    }
}
