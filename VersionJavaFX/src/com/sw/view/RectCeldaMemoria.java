package com.sw.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author HikingCarrot7
 */
public class RectCeldaMemoria extends Rectangle
{

    private Label etiqueta;
    private int inicio;
    private int tamanio;

    public RectCeldaMemoria(String etiqueta, int inicio, int tamanio, double x, double y, double width, double height, Paint fill)
    {
        super(x, y, width, height);
        this.etiqueta = new Label(etiqueta);
        this.inicio = inicio;
        this.tamanio = tamanio;
        setFill(fill);
    }

    public Label getEtiqueta()
    {
        return etiqueta;
    }

    public void setEtiqueta(Label etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public int getInicioMemoria()
    {
        return inicio;
    }

    public void setInicio(int inicio)
    {
        this.inicio = inicio;
    }

    public int getTamanioMemoria()
    {
        return tamanio;
    }

    public void setTamanio(int tamanio)
    {
        this.tamanio = tamanio;
    }

}
