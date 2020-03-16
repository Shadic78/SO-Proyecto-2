package com.sw.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author HikingCarrot7
 */
public class StageFactory
{

    public static String RUTA_ESTILOS = "/com/sw/styles/Stylesheet.css";

    /**
     * Crea un {@link Stage}.
     *
     * @param ruta La ruta del FXML del nuevo {@link Stage}
     * @param title El título para este {@link Stage}.
     * @param modality El {@link Modality} para este {@link Stage}.
     *
     * @return Una referencia al controlador de este nuevo {@link Stage}.
     */
    public static Controller createStage(String ruta, String title, Modality modality, Object defaultData)
    {
        return createStage(ruta, title, RUTA_ESTILOS, modality, defaultData);
    }

    /**
     * Crea un {@link Stage}.
     *
     * @param ruta La ruta del FXML del nuevo {@link Stage}
     * @param title El título para este {@link Stage}.
     * @param modality El {@link Modality} para este {@link Stage}.
     * @param stylesheet La ruta del {@link StyleSheet} para esta {@link Stage}.
     *
     * @return Una referencia al controlador de este nuevo {@link Stage}.
     */
    public static Controller createStage(String ruta, String title, String stylesheet, Modality modality, Object defaultData)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StageFactory.class.getResource(ruta));
            Pane pane = (Pane) loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            ((Controller) loader.getController()).setDefaultData(defaultData);
            scene.getStylesheets().add(stylesheet);
            stage.setTitle(title);
            stage.initModality(modality);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            return loader.getController();

        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        return null;
    }

}
