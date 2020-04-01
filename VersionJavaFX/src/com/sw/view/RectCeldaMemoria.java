package com.sw.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author SonBear
 */
public class RectCeldaMemoria extends Rectangle
{

    private final Label nombreLabel;
    private final Label posicionTerminaLabel;
    private final Label tamanioLabel;
    private final Line tamanioLinea;
    private final int inicioMemoria;
    private final int tamanioMemoria;

    public RectCeldaMemoria(String etiqueta, int inicioMemoria, int tamanioMemoria, double x, double y, double width, double height, Paint fill)
    {
        super(x, y, width, height);
        this.nombreLabel = new Label(etiqueta);
        this.nombreLabel.setMouseTransparent(true);
        this.posicionTerminaLabel = new Label((tamanioMemoria + inicioMemoria) + "K");
        this.tamanioLabel = new Label(tamanioMemoria + "K");
        this.tamanioLabel.setMouseTransparent(true);
        this.tamanioLinea = new Line(x + width - 5, y + 5, x + width - 5, y + height - 5);
        this.inicioMemoria = inicioMemoria;
        this.tamanioMemoria = tamanioMemoria;
        setFill(fill);
    }

    public Label getNombreLabel()
    {
        return nombreLabel;
    }

    public Label getPosicionTerminaLabel()
    {
        return posicionTerminaLabel;
    }

    public Label getTamanioLabel()
    {
        return tamanioLabel;
    }

    public Line getTamanioLinea()
    {
        return tamanioLinea;
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
