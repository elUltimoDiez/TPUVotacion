package Interfaz;

import Negocio.Agrupacion;
import Negocio.Agrupaciones;
import Negocio.Region;
import Negocio.Regiones;
import Soporte.ArchivoDeTexto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class PrincipalController {
    public Label lblUbicacion;
    public ListView lvwResultados;
    public ComboBox cmbDistrito;
    public ComboBox cmbSeccion;
    public ComboBox cmbCircuito;

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
        ObservableList ol;
        ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        lvwResultados.setItems(ol);

        Regiones regiones = new Regiones(lblUbicacion.getText());
        ol = FXCollections.observableArrayList(regiones.getDistritos());
        cmbDistrito.setItems(ol);
    }


    public void filtrarSecciones(ActionEvent actionEvent) {
        Region distrito = (Region) cmbDistrito.getValue();
        ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cmbSeccion.setItems(ol);
    }

    public void filtrarCircuitos(ActionEvent actionEvent) {
        if (cmbSeccion.getValue() != null){
            Region seccion = (Region) cmbSeccion.getValue();
            ObservableList ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cmbCircuito.setItems(ol);
        } else{
            cmbCircuito.getItems().clear();
        }

    }
}
