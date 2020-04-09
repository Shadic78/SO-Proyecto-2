package com.sw.controller;

import com.sw.model.AreaLibre;
import com.sw.model.Particion;
import com.sw.model.Proceso;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author SonBear
 */
public class TableManager
{

    private static TableManager instance;

    private TableManager()
    {

    }

    public void initDataTablaProcesos(TableView<Proceso> tablaProcesos)
    {
        for (int i = 0; i < tablaProcesos.getColumns().size(); i++)
        {
            TableColumn c = tablaProcesos.getColumns().get(i);

            switch (i)
            {
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    break;
                case 1:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((Proceso) (((TableColumn.CellDataFeatures) cellData).getValue())).getSize() + " K"));
                    break;
                case 2:
                    c.setCellValueFactory(new PropertyValueFactory<>("llegada"));
                    break;
                case 3:
                    c.setCellValueFactory(new PropertyValueFactory<>("duracion"));
                    break;
            }
        }
    }

    public void inicializarTablaAreasLibres(TableView<AreaLibre> tablaAreasLibres)
    {
        for (int i = 0; i < tablaAreasLibres.getColumns().size(); i++)
        {
            TableColumn c = tablaAreasLibres.getColumns().get(i);

            switch (i)
            {
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("posicion"));
                    break;
                case 1:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((AreaLibre) (((TableColumn.CellDataFeatures) cellData).getValue())).getInicio() + " K"));
                    break;
                case 2:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((AreaLibre) (((TableColumn.CellDataFeatures) cellData).getValue())).getSize() + " K"));
                    break;
                case 3:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty("Disponible"));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void inicializarTablaParticiones(TableView<Particion> tablaParticiones)
    {
        for (int i = 0; i < tablaParticiones.getColumns().size(); i++)
        {
            TableColumn c = tablaParticiones.getColumns().get(i);

            switch (i)
            {
                case 0:
                    c.setCellValueFactory(new PropertyValueFactory<>("posicion"));
                    break;
                case 1:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((Particion) (((TableColumn.CellDataFeatures) cellData).getValue())).getInicio() + " K"));
                    break;
                case 2:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((Particion) (((TableColumn.CellDataFeatures) cellData).getValue())).getSize() + " K"));
                    break;
                case 3:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty("Asignado"));
                    break;
                case 4:
                    c.setCellValueFactory(cellData -> new SimpleStringProperty(((Particion) (((TableColumn.CellDataFeatures) cellData).getValue())).getProceso().getNombre()));
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void seleccionarUltimaFila(TableView<?> tabla)
    {
        tabla.getSelectionModel().selectLast();
    }

    public void actualizarTabla(TableView<?> tabla)
    {
        Platform.runLater(tabla::refresh);
    }

    public synchronized static TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }

}
