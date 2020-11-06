package Interfaz;

import Negocio.*;
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
    public Resultados resultados;

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
        ObservableList ol;

        Agrupaciones.leerAgrupaciones(lblUbicacion.getText());

        Regiones regiones = new Regiones(lblUbicacion.getText());
        ol = FXCollections.observableArrayList(regiones.getDistritos());
        cmbDistrito.setItems(ol);

        resultados = new Resultados(lblUbicacion.getText());
        ol = FXCollections.observableArrayList(resultados.getResultadosRegion("00"));
        lvwResultados.setItems(ol);
    }


    public void elegirDistrito(ActionEvent actionEvent) {
        Region distrito = (Region) cmbDistrito.getValue();
        ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cmbSeccion.setItems(ol);
        ol = FXCollections.observableArrayList(resultados.getResultadosRegion(distrito.getCodigo()));
        lvwResultados.setItems(ol);
    }

    public void elegirSeccion(ActionEvent actionEvent) {
        ObservableList ol;
        if (cmbSeccion.getValue() != null){
            Region seccion = (Region) cmbSeccion.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cmbCircuito.setItems(ol);
            ol = FXCollections.observableArrayList(resultados.getResultadosRegion(seccion.getCodigo()));
            lvwResultados.setItems(ol);
        } else{
            cmbCircuito.getItems().clear();
        }
    }

    public void elegirCircuito(ActionEvent actionEvent) {
        ObservableList ol;
        if (cmbCircuito.getValue() != null){
            Region circuito = (Region) cmbCircuito.getValue();
            ol = FXCollections.observableArrayList(resultados.getResultadosRegion(circuito.getCodigo()));
            lvwResultados.setItems(ol);
        } else{
            cmbCircuito.getItems().clear();
        }
    }
}
