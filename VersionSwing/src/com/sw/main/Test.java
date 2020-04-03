package com.sw.main;

import com.sw.controller.VistaController;
import com.sw.view.Vista;
import java.awt.EventQueue;

/**
 *
 * @author Nicolás
 */
public class Test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Create and display the form */
        EventQueue.invokeLater(() ->
        {
            Vista vista = new Vista();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            new VistaController(vista);
        });
    }

}
