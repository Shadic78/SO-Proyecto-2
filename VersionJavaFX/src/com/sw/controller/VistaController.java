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
import static javafx.application.Platform.runLater;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.Cursor.HAND;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Button btnAdmProcesos;
    @FXML
    private Pane panel;
    @FXML
    private Label estado;

    private OS os;
    private RAM ram;
    private TableManager tableManager;
    private Grafico grafico;
    private Stage myStage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ram = new RAM(64);
        tableManager = TableManager.getInstance();
        btnSigPaso.setWrapText(true);
        btnSigPaso.setCursor(HAND);
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

    @Override
    public void initStage(Stage s)
    {
        myStage = s;
    }

    private void initTablas()
    {
        tableManager.inicializarTablaProcesos(tablaProcesos);
        tableManager.inicializarTablaAreasLibres(tablaAreasLibres);
        tableManager.inicializarTablaParticiones(tablaParticiones);
    }

    @FXML
    private void sigPaso(ActionEvent e)
    {
        os.siguienteMomento();
        actualizarGrafico();
        actualizarTablas();
    }

    @FXML
    private void admProcesos(ActionEvent e)
    {
        StageFactory.createStage("/com/sw/view/AdmProcesos.fxml",
                "Administrador de procesos",
                StageFactory.RUTA_ESTILOS,
                Modality.APPLICATION_MODAL,
                myStage,
                tablaProcesos.getItems());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        actualizarEstado(arg.toString());
    }

    private void actualizarGrafico()
    {
        runLater(() ->
        {
            grafico.refrescarGrafico();
            grafico.dibujarRepresentacionGrafica(ram.getAreasLibres(), ram.getParticiones(), ram.getFragmentos());
        });
    }

    private void actualizarEstado(String mensaje)
    {
        runLater(() ->
        {
            estado.setText(mensaje);
        });
    }

    private void actualizarTablas()
    {
        tablaAreasLibres.refresh();
        tablaParticiones.refresh();
    }

}
