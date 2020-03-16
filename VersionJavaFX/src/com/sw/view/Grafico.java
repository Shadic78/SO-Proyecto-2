package com.sw.view;

import com.sw.model.AreaLibre;
import com.sw.model.Particion;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author HikingCarrot7
 */
public class Grafico
{

    private final Pane panel;

    public Grafico(Pane panel)
    {
        this.panel = panel;
    }

    public void dibujarRepresentacionGrafica(ObservableList<AreaLibre> areasLibres, ObservableList<Particion> particiones)
    {
        Rectangle r = new Rectangle(panel.getWidth(), panel.getHeight(), Color.rgb(131, 157, 165));
        r.widthProperty().bind(panel.widthProperty());
        r.heightProperty().bind(panel.heightProperty());

        panel.getChildren().add(r);
    }

}
