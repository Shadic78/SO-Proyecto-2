package com.sw.controller;

import com.sw.model.Proceso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Nicolás
 */
public class AdmProcesosController implements Initializable, Controller<ObservableList<Proceso>>
{

    @FXML
    private TableView<Proceso> tablaProcesos;
    @FXML
    private TableColumn<Proceso, Button> colEliminar;
    @FXML
    private TextField inputNombreProceso;
    @FXML
    private TextField inputTamProceso;
    @FXML
    private TextField inputLlegadaProceso;
    @FXML
    private TextField inputFinProceso;
    @FXML
    private Button btnModificarProceso;

    private TableManager tableManager;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tableManager = TableManager.getInstance();
        tableManager.inicializarTablaProcesos(tablaProcesos);
        colEliminar.setCellFactory(ActionButtonTableCell.<Proceso>forTableColumn("Eliminar", this::eliminarProceso));

        NumberStringConverter stringConverter = new NumberStringConverter();

        inputTamProceso.setTextFormatter(new TextFormatter<>(stringConverter));
        inputLlegadaProceso.setTextFormatter(new TextFormatter<>(stringConverter));
        inputFinProceso.setTextFormatter(new TextFormatter<>(stringConverter));

        tablaProcesos.setOnMouseClicked(e ->
        {
            Proceso proceso = tablaProcesos.getSelectionModel().getSelectedItem();

            if (proceso != null)
                rellenarCampos(proceso);
        });
    }

    private void eliminarProceso(Proceso p)
    {
        tablaProcesos.getItems().remove(p);
    }

    private void rellenarCampos(Proceso proceso)
    {
        inputNombreProceso.setText(proceso.getNombre());
        inputTamProceso.setText(String.valueOf(proceso.getSize()));
        inputLlegadaProceso.setText(String.valueOf(proceso.getLlegada()));
        inputFinProceso.setText(String.valueOf(proceso.getDuracion()));
    }

    @Override
    public void setDefaultData(ObservableList<Proceso> data)
    {
        tablaProcesos.setItems(data);
    }

    @Override
    public void initStage(Stage s)
    {

    }

}
