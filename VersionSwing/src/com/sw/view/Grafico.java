package com.sw.view;

import com.sw.model.AreaLibre;
import com.sw.model.CeldaMemoria;
import com.sw.model.Fragmento;
import com.sw.model.Particion;
import com.sw.model.RAM;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nicolás
 */
public class Grafico extends JPanel
{

    private final Color BACKGROUND_COLOR = new Color(252, 252, 252);
    private final Color COLOR_AREA_LIBRE = new Color(131, 157, 165);
    private final Color COLOR_PARTICION = new Color(232, 232, 232);
    private final Color COLOR_FRAGMENTO = new Color(221, 79, 67);

    private final Font DEFAULT_FONT = new Font("Tahoma", Font.BOLD, 11);
    private final Font BLOCK_OS_FONT = new Font("Tahoma", Font.BOLD, 24);
    private final Font CELDA_MEMORIA_FONT = new Font("Tahoma", Font.PLAIN, 14);

    private final int OFFSET_X = 40;

    private ArrayList<AreaLibre> areasLibres;
    private ArrayList<Particion> particiones;
    private ArrayList<Fragmento> fragmentos;

    private final int MAX_MEMORIA_RAM;
    private final int TAMANIO_MEMORIA_OS;

    public Grafico(int MAX_MEMORIA_RAM, int TAMANIO_MEMORIA_OS)
    {
        this.MAX_MEMORIA_RAM = MAX_MEMORIA_RAM;
        this.TAMANIO_MEMORIA_OS = TAMANIO_MEMORIA_OS;

        areasLibres = new ArrayList<>();
        particiones = new ArrayList<>();
        fragmentos = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(BACKGROUND_COLOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        createOSBlock(g2d);
        dibujarAreasLibres(g2d);
        dibujarParticiones(g2d);
        dibujarFragmentacion(g2d);

        g.dispose();
        g2d.dispose();
    }

    public void actualizarGrafico(ArrayList<AreaLibre> areasLibres, ArrayList<Particion> particiones, ArrayList<Fragmento> fragmentos)
    {
        this.areasLibres = areasLibres;
        this.particiones = particiones;
        this.fragmentos = fragmentos;
        repaint();
    }

    private void createOSBlock(Graphics2D g)
    {
        dibujarCeldaMemoria(g, BLOCK_OS_FONT, "SO", new Color(0, 112, 192), Color.WHITE, TAMANIO_MEMORIA_OS, 0);
    }

    private void dibujarAreasLibres(Graphics2D g)
    {
        areasLibres.forEach(areaLibre ->
        {
            dibujarCeldaMemoria(g, CELDA_MEMORIA_FONT, "Área libre", COLOR_AREA_LIBRE, Color.BLACK, areaLibre.getSize(), areaLibre.getInicio());
        });
    }

    private void dibujarParticiones(Graphics2D g)
    {
        particiones.forEach(particion ->
        {
            dibujarCeldaMemoria(g, CELDA_MEMORIA_FONT, particion.getProceso().getNombre(), COLOR_PARTICION, Color.BLACK, particion.getSize(), particion.getInicio());
        });
    }

    private void dibujarFragmentacion(Graphics2D g)
    {
        fragmentos.forEach(fragmento ->
        {
            dibujarCeldaMemoria(g, CELDA_MEMORIA_FONT, "Área libre", COLOR_FRAGMENTO, Color.BLACK, fragmento.getSize(), fragmento.getInicio());
        });
    }

    private void dibujarCeldaMemoria(Graphics2D g, Font font, String label, Color colorCeldaMemoria, Color colorTexto, int tamanioMemoria, int posicionMemoria)
    {
        Stroke strokeActual = g.getStroke();
        g.setColor(colorCeldaMemoria);
        Rectangle rect = new Rectangle(OFFSET_X, obtenerPosicionEnGrafica(posicionMemoria), getWidth() - OFFSET_X * 2, obtenerTamanioEnGrafica(tamanioMemoria));
        g.fill(rect);
        g.setStroke(new BasicStroke(2));
        g.setColor(BACKGROUND_COLOR);
        g.draw(rect);
        centrarTextoEnRect(g, font, colorTexto, label, rect);
        g.setStroke(strokeActual);
    }

    private void centrarTextoEnRect(Graphics2D g, Font font, Color colorTexto, String text, Rectangle rect)
    {
        g.setFont(font);
        g.setColor(colorTexto);
        Rectangle bounds = g.getFontMetrics().getStringBounds(text, g).getBounds();
        g.drawString(text, (int) (OFFSET_X + (rect.getWidth() - bounds.getWidth()) / 2), (int) (rect.y + (rect.getHeight() + bounds.getHeight() - 10) / 2));
        g.setFont(DEFAULT_FONT);
    }

    /**
     * Regresa la altura (en píxeles) de una {@link CeldaMemoria} de acuerda a su tamaño en memoria.
     *
     * @param tamanioMemoria El tamaño en memoria para una {@link CeldaMemoria}.
     *
     * @return Regresa el tamaño (en píxeles) de la {@link CeldaMemoria} para ser graficada.
     */
    private int obtenerTamanioEnGrafica(int tamanioMemoria)
    {
        return tamanioMemoria * getHeight() / MAX_MEMORIA_RAM;
    }

    /**
     * Regresa la posición (en píxeles) de una {@link CeldaMemoria} de acuerda a su posición inicial en la {@link RAM}.
     *
     * @param inicioMemoria El inicio en memoria para una {@link CeldaMemoria}.
     *
     * @return Regresa la posición (en píxeles) de la {@link CeldaMemoria} para ser graficada.
     */
    private int obtenerPosicionEnGrafica(int inicioMemoria)
    {
        return inicioMemoria * getHeight() / MAX_MEMORIA_RAM;
    }

    private void dibujarLateralIzq(Graphics2D g)
    {
        g.setColor(BACKGROUND_COLOR);
        g.fill(new Rectangle(0, 0, OFFSET_X, getHeight()));
    }

    private void dibujarLateralDer(Graphics2D g)
    {
        g.setColor(BACKGROUND_COLOR);
        g.fill(new Rectangle(getWidth() - OFFSET_X, 0, OFFSET_X, getHeight()));
    }

}
