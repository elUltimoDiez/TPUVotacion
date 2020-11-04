package Interfaz;

import Negocio.Agrupacion;
import Negocio.Agrupaciones;
import Soporte.ArchivoDeTexto;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class PrincipalController {
    public Label lblUbicacion;
    public TextArea txtAgrupaciones;

    public void cambiarUbicacion(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar archivo");
        dc.setInitialDirectory(new File(lblUbicacion.getText()));
        File archivo = dc.showDialog(null);

        if(archivo != null){
            lblUbicacion.setText(archivo.getPath());
        }
    }

    public void cargarDatos(ActionEvent actionEvent) {
        Agrupaciones agrupaciones = new Agrupaciones(lblUbicacion.getText());
        txtAgrupaciones.setText(agrupaciones.toString());

        ArchivoDeTexto regiones = new ArchivoDeTexto(lblUbicacion.getText() + "\\descripcion_regiones.dsv");
        ArchivoDeTexto mesas = new ArchivoDeTexto(lblUbicacion.getText() + "\\mesas_totales_agrp_politica.dsv");
    }


}
