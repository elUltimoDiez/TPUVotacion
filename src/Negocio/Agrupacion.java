package Negocio;

public class Agrupacion {
    private String codigo;
    private String nombre;
    private int votos;

    public Agrupacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        votos = 0;
    }

    public void sumarVotos(int votos){
        this.votos += votos;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Agrupacion{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", votos=").append(votos);
        sb.append('}');
        return sb.toString();
    }

    public String getNombre() {
        return nombre;
    }
}
