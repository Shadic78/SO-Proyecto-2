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

    private final Label etiqueta;
    private final Label tamanioTexto;
    private final int inicioMemoria;
    private final int tamanioMemoria;

    public RectCeldaMemoria(String etiqueta, int inicioMemoria, int tamanioMemoria, double x, double y, double width, double height, Paint fill)
    {
        super(x, y, width, height);
        this.etiqueta = new Label(etiqueta);
        this.tamanioTexto = new Label((tamanioMemoria + inicioMemoria) + "K");
        this.inicioMemoria = inicioMemoria;
        this.tamanioMemoria = tamanioMemoria;
        setFill(fill);
    }

    public Label getEtiqueta()
    {
        return etiqueta;
    }

    public Label getTamanioTexto()
    {
        return tamanioTexto;
    }

    public int getInicioMemoria()
    {
        return inicioMemoria;
    }

    public int getTamanioMemoria()
    {
        return tamanioMemoria;
    }

}
