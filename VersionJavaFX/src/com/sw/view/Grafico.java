package com.sw.view;

import com.sw.model.AreaLibre;
import com.sw.model.Particion;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author HikingCarrot7
 */
public class Grafico
{

    private final int MAX_MEMORIA_RAM;
    private final int TAMANIO_MEMORIA_OS;

    private final Pane panel;

    public Grafico(Pane panel, final int MAX_TAMANIO_RAM, final int TAMANIO_MEMORIA_OS)
    {
        this.panel = panel;
        createBackground();
        this.MAX_MEMORIA_RAM = MAX_TAMANIO_RAM;
        this.TAMANIO_MEMORIA_OS = TAMANIO_MEMORIA_OS;
    }

    public void dibujarRepresentacionGrafica(ObservableList<AreaLibre> areasLibres, ObservableList<Particion> particiones)
    {
        createOSBlock();
        areasLibres.forEach(this::dibujarAreaLibre);
        particiones.forEach(this::dibujarParticion);
    }

    private void dibujarAreaLibre(AreaLibre areaLibre)
    {
        Rectangle rectAreaLibre = createRectangle(0, obtenerPosicionEnGrafica(areaLibre.getInicio()), panel.getWidth(), obtenerTamanioEnGrafica(areaLibre.getSize()), Color.rgb(131, 157, 165));
        rectAreaLibre.heightProperty().bind(new EscaladorAltura(areaLibre.getSize(), panel.heightProperty()).getScaleHeightProperty());
        panel.getChildren().add(rectAreaLibre);
    }

    private void dibujarParticion(Particion particion)
    {
        Rectangle rectParticion = createRectangle(0, obtenerPosicionEnGrafica(particion.getInicio()), panel.getWidth(), obtenerTamanioEnGrafica(particion.getSize()), Color.rgb(232, 232, 232));
        rectParticion.heightProperty().bind(new EscaladorAltura(particion.getSize(), panel.heightProperty()).getScaleHeightProperty());
        panel.getChildren().add(rectParticion);
    }

    private void createOSBlock()
    {
        Rectangle os = createRectangle(0, 0, panel.getWidth(), obtenerTamanioEnGrafica(TAMANIO_MEMORIA_OS), Color.rgb(0, 112, 192));
        os.heightProperty().bind(new EscaladorAltura(TAMANIO_MEMORIA_OS, panel.heightProperty()).getScaleHeightProperty());
        panel.getChildren().add(os);
    }

    private void createBackground()
    {
        Rectangle bg = createRectangle(0, 0, panel.getWidth(), panel.getHeight(), Color.rgb(244, 244, 244));
        bg.heightProperty().bind(panel.heightProperty());
        //bordearRect(bg);
        bg.toBack();
        panel.getChildren().add(bg);
    }

    private Rectangle createRectangle(double x, double y, double width, double height, Paint fill)
    {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(fill);
        rect.widthProperty().bind(panel.widthProperty());
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(2);
        //bordearRect(rect);
        rect.toFront();
        return rect;
    }

    private void bordearRect(Rectangle r)
    {
        r.setArcHeight(15);
        r.setArcWidth(15);
    }

    private double obtenerTamanioEnGrafica(int tamanioMemoria)
    {
        return tamanioMemoria * panel.getHeight() / MAX_MEMORIA_RAM;
    }

    private double obtenerPosicionEnGrafica(int inicioMemoria)
    {
        return inicioMemoria * panel.getHeight() / MAX_MEMORIA_RAM;
    }

    public void refrescarGrafico()
    {
        panel.getChildren().clear();
        createBackground();
    }

    private class EscaladorAltura
    {

        private DoubleProperty scaleHeightProperty;

        public EscaladorAltura(int tamanioMemoria, ReadOnlyDoubleProperty heightPanelProperty)
        {
            scaleHeightProperty = new SimpleDoubleProperty(panel.getHeight() / MAX_MEMORIA_RAM * tamanioMemoria);

            heightPanelProperty.addListener((observable, oldValue, newValue) ->
            {
                scaleHeightProperty.setValue(newValue.doubleValue() / MAX_MEMORIA_RAM * tamanioMemoria);
            });
        }

        public DoubleProperty getScaleHeightProperty()
        {
            return scaleHeightProperty;
        }

    }

}
