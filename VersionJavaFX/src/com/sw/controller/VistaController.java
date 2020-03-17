package com.sw.controller;

import com.sw.model.AreaLibre;
import com.sw.model.OS;
import com.sw.model.Particion;
import com.sw.model.Proceso;
import com.sw.model.RAM;
import com.sw.view.Grafico;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author SonBear
 */
public class VistaController implements Initializable, Observer, Controller<ObservableList<Proceso>>
{

    @FXML
    private TableView<Proceso> tablaProcesos;
    @FXML
    private TableView<AreaLibre> tablaAreasLibres;
    @FXML
    private TableView<Particion> tablaParticiones;
    @FXML
    private Button btnSigPaso;
    @FXML
    private Pane panel;
    @FXML
    private Label estado;

    private OS os;
    private RAM ram;
    private TableManager tableManager;
    private Grafico grafico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ram = new RAM(64);
        tableManager = new TableManager();
        btnSigPaso.setCursor(Cursor.HAND);
        initTablas();
    }

    @Override
    public void setDefaultData(ObservableList<Proceso> colaProcesos)
    {
        os = new OS(10, ram, colaProcesos);
        os.addObserver(this);
        tablaProcesos.setItems(colaProcesos);
        tablaAreasLibres.setItems(ram.getAreasLibres());
        tablaParticiones.setItems(ram.getParticiones());
        grafico = new Grafico(panel, ram.MAX_TAM_MEMORIA(), os.MEMORIA_OS());
        actualizarGrafico();
    }

    private void initTablas()
    {
        tableManager.inicializarTablaProcesos(tablaProcesos);
        tableManager.inicializarTablaAreasLibres(tablaAreasLibres);
        tableManager.inicializarTablaParticiones(tablaParticiones);
    }

    private void actualizarGrafico()
    {
        Platform.runLater(() ->
        {
            grafico.refrescarGrafico();
            grafico.dibujarRepresentacionGrafica(ram.getAreasLibres(), ram.getParticiones(), ram.getFragmentos());
        });
    }

    @Override
    public void update(Observable o, Object arg)
    {
        actualizarEstado(arg.toString());
    }

    private void actualizarEstado(String mensaje)
    {
        Platform.runLater(() ->
        {
            estado.setText(mensaje);
        });
    }

    @FXML
    private void sigPaso(ActionEvent e)
    {
        os.siguienteMomento();
        actualizarGrafico();
        actualizarTablas();
    }

    private void actualizarTablas()
    {
        tablaAreasLibres.refresh();
        tablaParticiones.refresh();
    }

}
