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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HikingCarrot7
 */
public class VistaController implements Initializable, Controller<ObservableList<Proceso>>, Observer
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
        ram = new RAM();
        tableManager = new TableManager();
        grafico = new Grafico(panel, 64, 10);
        initTablas();
    }

    @Override
    public void setDefaultData(ObservableList<Proceso> colaProcesos)
    {
        os = new OS(ram, colaProcesos);
        os.addObserver(this);
        tablaProcesos.setItems(colaProcesos);
        tablaAreasLibres.setItems(ram.getAreasLibres());
        tablaParticiones.setItems(ram.getParticiones());
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
            grafico.dibujarRepresentacionGrafica(ram.getAreasLibres(), ram.getParticiones());
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
    }

}
