package com.sw.main;

import com.sw.controller.VistaController;
import com.sw.model.Proceso;
import com.sw.view.Vista;
import java.awt.EventQueue;
import java.util.ArrayList;

/**
 *
 * @author Eusebio Ajax
 */
public class Test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        final ArrayList<Proceso> colaProcesos = new ArrayList<>();

        Proceso p1 = new Proceso("P1", 3, 2, 9);
        Proceso p2 = new Proceso("P2", 18, 2, 12);
        Proceso p3 = new Proceso("P3", 20, 4, 15);
        Proceso p4 = new Proceso("P4", 18, 5, 8);
        Proceso p5 = new Proceso("P5", 15, 7, 12);
        Proceso p6 = new Proceso("P6", 13, 8, 3);
        Proceso p7 = new Proceso("P7", 15, 9, 8);
        Proceso p8 = new Proceso("P8", 19, 9, 9);
        Proceso p9 = new Proceso("P9", 10, 12, 4);

        colaProcesos.add(p1);
        colaProcesos.add(p2);
        colaProcesos.add(p3);
        colaProcesos.add(p4);
        colaProcesos.add(p5);
        colaProcesos.add(p6);
        colaProcesos.add(p7);
        colaProcesos.add(p8);
        colaProcesos.add(p9);

        EventQueue.invokeLater(() ->
        {
            Vista vista = new Vista();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            VistaController vistaController = new VistaController(vista);
            vistaController.establecerDatosPorDefecto(colaProcesos);
        });
    }

}
