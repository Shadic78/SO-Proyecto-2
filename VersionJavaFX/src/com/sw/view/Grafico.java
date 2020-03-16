package com.sw.view;

import com.sw.controller.Utilidades;
import com.sw.model.AreaLibre;
import com.sw.model.CeldaMemoria;
import com.sw.model.Particion;
import com.sw.model.RAM;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
        this.MAX_MEMORIA_RAM = MAX_TAMANIO_RAM;
        this.TAMANIO_MEMORIA_OS = TAMANIO_MEMORIA_OS;
    }

    /**
     * Dibuja el gráfico para el {@link ObservableList} de las áreas libres y particiones en la {@link RAM}.
     *
     * @param areasLibres El {@link ObservableList} que representa las áreas libres.
     * @param particiones El {@link ObservableList} que representa las particiones.
     */
    public void dibujarRepresentacionGrafica(ObservableList<AreaLibre> areasLibres, ObservableList<Particion> particiones)
    {
        createOSBlock();
        areasLibres.forEach(this::dibujarAreaLibre);
        particiones.forEach(this::dibujarParticion);

        for (int i = 0; i < panel.getChildren().size(); i++)
        {
            Node n = panel.getChildren().get(i);

            if (n instanceof Label)
                n.toFront();
        }

    }

    private void dibujarAreaLibre(AreaLibre areaLibre)
    {
        RectCeldaMemoria rectAreaLibre = createRectCeldaMemoria(areaLibre, Color.rgb(131, 157, 165));

        rectAreaLibre.heightProperty().bind(new Escalador(rectAreaLibre).getScaleHeightProperty());
        rectAreaLibre.yProperty().bind(new Reposicionador(rectAreaLibre).getPosicionHeightProperty());

        panel.getChildren().addAll(rectAreaLibre, rectAreaLibre.getEtiqueta());
    }

    private void dibujarParticion(Particion particion)
    {
        RectCeldaMemoria rectParticion = createRectCeldaMemoria(particion, Color.rgb(232, 232, 232));

        rectParticion.heightProperty().bind(new Escalador(rectParticion).getScaleHeightProperty());
        rectParticion.yProperty().bind(new Reposicionador(rectParticion).getPosicionHeightProperty());

        panel.getChildren().addAll(rectParticion, rectParticion.getEtiqueta());
    }

    private void createOSBlock()
    {
        RectCeldaMemoria os = createRectCeldaMemoria("OS", 0, TAMANIO_MEMORIA_OS, 0, 0, panel.getWidth(), obtenerTamanioEnGrafica(TAMANIO_MEMORIA_OS), Color.rgb(0, 112, 192));
        os.heightProperty().bind(new Escalador(os).getScaleHeightProperty());
        os.yProperty().bind(new Reposicionador(os).getPosicionHeightProperty());
        panel.getChildren().addAll(os, os.getEtiqueta());
    }

    /**
     * Crea un {@link Rectangle} que representa una {@link CeldaMemoria} en la {@link RAM}.
     *
     * @param label El texto representativo para esta {@link CeldaMemoria}.
     * @param inicio El la posición inicial de esta {@link CeldaMemoria} en la {@link RAM}.
     * @param tamanio El tamaño de esta {@link CeldaMemoria}.
     * @param x La posición en x para esta {@link CeldaMemoria}.
     * @param y La posición en y para esta {@link CeldaMemoria}.
     * @param width El ancho (en píxeles) para esta {@link CeldaMemoria}.
     * @param height El alto (en píxeles) para esta {@link CeldaMemoria}.
     * @param fill El {@link Paint} para rellenar esta {@link CeldaMemoria}.
     *
     * @return El {@link Rectangle} que representa esta {@link CeldaMemoria}.
     */
    private RectCeldaMemoria createRectCeldaMemoria(String label, int inicio, int tamanio, double x, double y, double width, double height, Paint fill)
    {
        RectCeldaMemoria rect = new RectCeldaMemoria(label, inicio, tamanio, x, y, width, height, fill);
        rect.widthProperty().bind(panel.widthProperty());
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(2);
        rect.toFront();
        return rect;
    }

    /**
     * Crea un {@link Rectangle} que representa una {@link CeldaMemoria} en la {@link RAM}.
     *
     * @param celdaMemoria La {@link CeldaMemoria} de la cual se quiere crear su representación.
     * @param fill El {@link Paint} para rellenar esta {@link CeldaMemoria}.
     * @return El {@link Rectangle} que representa esta {@link CeldaMemoria}.
     */
    private RectCeldaMemoria createRectCeldaMemoria(CeldaMemoria celdaMemoria, Paint fill)
    {
        return createRectCeldaMemoria(celdaMemoria instanceof Particion ? ((Particion) celdaMemoria).getProceso().getNombre() : "Área libre",
                celdaMemoria.getInicio(),
                celdaMemoria.getSize(), 0,
                obtenerPosicionEnGrafica(celdaMemoria.getInicio()),
                panel.getWidth(),
                obtenerTamanioEnGrafica(celdaMemoria.getSize()), fill);
    }

    /**
     * Regresa el tamaño (en píxeles) de una {@link CeldaMemoria} de acuerda a su tamaño en memoria.
     *
     * @param tamanioMemoria El tamaño en memoria para una {@link CeldaMemoria}.
     *
     * @return Regresa el tamaño (en píxeles) de la {@link CeldaMemoria} para ser graficada.
     */
    private double obtenerTamanioEnGrafica(int tamanioMemoria)
    {
        return tamanioMemoria * panel.getHeight() / MAX_MEMORIA_RAM;
    }

    /**
     * Regresa la posición (en píxeles) de una {@link CeldaMemoria} de acuerda a su posición inicial en la {@link RAM}.
     *
     * @param inicioMemoria El inicio en memoria para una {@link CeldaMemoria}.
     *
     * @return Regresa la posición (en píxeles) de la {@link CeldaMemoria} para ser graficada.
     */
    private double obtenerPosicionEnGrafica(int inicioMemoria)
    {
        return inicioMemoria * panel.getHeight() / MAX_MEMORIA_RAM;
    }

    /**
     * Vuelve a dibujar el gráfico.
     */
    public void refrescarGrafico()
    {
        panel.getChildren().clear();
    }

    /**
     * Cuando se cambia de tamaño al {@link Stage}, esta clase se encarga de actualizar el alto de los {@link Rectangle} que representan a las {@link CeldaMemoria} en el gráfico.
     *
     * Podemos decir que los objetos de esta clase están a la escucha de los cambios de tamaño del panel donde se dibuja el gráfico (dichos cambios se dan cuando se cambia el tamaño de la ventana).
     */
    private class Escalador
    {

        private DoubleProperty scaleHeightProperty;

        public Escalador(RectCeldaMemoria rectCeldaMemoriaAEscalar)
        {
            scaleHeightProperty = new SimpleDoubleProperty(obtenerTamanioEnGrafica(rectCeldaMemoriaAEscalar.getTamanioMemoria()));

            panel.heightProperty().addListener((observer, oldValue, newValue) ->
            {
                scaleHeightProperty.setValue(obtenerTamanioEnGrafica(rectCeldaMemoriaAEscalar.getTamanioMemoria()));
            });
        }

        /**
         * Retorna el {@link DoubleProperty} de este escalador.
         *
         * @return El {@link DoubleProperty} de este escalador.
         */
        public DoubleProperty getScaleHeightProperty()
        {
            return scaleHeightProperty;
        }

    }

    /**
     * "Arregla" la posición en la que los {@link Rectangle} que representan las {@link CeldaMemoria} deberían de dibujarse. También se encarga de reposicionar las {@link Label} de las {@link CeldaMemoria}.
     */
    private class Reposicionador implements ChangeListener<Number>
    {

        private final DoubleProperty posicionHeightProperty;
        private final RectCeldaMemoria rectCeldaMemoriaAReposicionar;

        public Reposicionador(RectCeldaMemoria rectCeldaMemoriaAReposicionar)
        {
            this.rectCeldaMemoriaAReposicionar = rectCeldaMemoriaAReposicionar;
            posicionHeightProperty = new SimpleDoubleProperty(obtenerPosicionEnGrafica(rectCeldaMemoriaAReposicionar.getInicioMemoria()));
            panel.heightProperty().addListener(this);
            reposicionarEtiqueta();
        }

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
        {
            reposicionarEtiqueta();
            posicionHeightProperty.setValue(obtenerPosicionEnGrafica(rectCeldaMemoriaAReposicionar.getInicioMemoria()));
        }

        private void reposicionarEtiqueta()
        {
            double posicionEnGrafica = obtenerPosicionEnGrafica(rectCeldaMemoriaAReposicionar.getInicioMemoria());
            double tamanioEnGrafica = obtenerTamanioEnGrafica(rectCeldaMemoriaAReposicionar.getTamanioMemoria());
            Label etiqueta = rectCeldaMemoriaAReposicionar.getEtiqueta();

            etiqueta.setTranslateY(posicionEnGrafica + tamanioEnGrafica / 2 - Utilidades.getFontHeight(etiqueta.getFont()) / 2);
            etiqueta.setTranslateX(panel.getWidth() / 2 - Utilidades.getFontWidth(etiqueta.getText(), etiqueta.getFont()) / 2);
        }

        public DoubleProperty getPosicionHeightProperty()
        {
            return posicionHeightProperty;
        }

    }

}
