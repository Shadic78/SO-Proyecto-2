package com.sw.controller;

import com.sw.model.OS;
import com.sw.model.Proceso;
import com.sw.model.RAM;
import com.sw.view.AdmProcesos;
import com.sw.view.Grafico;
import com.sw.view.Vista;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Nicolás
 */
public class VistaController implements Observer
{

    private final Vista vista;
    private final Grafico grafico;
    private final OS os;
    private final RAM ram;
    private final TableManager tableManager;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        ram = new RAM(64);
        os = new OS(10, ram, procesos());
        os.addObserver(this);
        grafico = new Grafico(ram.MAX_TAM_MEMORIA(), os.MEMORIA_OS());
        tableManager = TableManager.getInstance();
        vista.getPanelGrafico().add(grafico);
        initComponents();
    }

    private void initComponents()
    {
        tableManager.initTabla(vista.getTablaProcesos());
        tableManager.initTabla(vista.getTablaAreasLibres());
        tableManager.initTabla(vista.getTablaParticiones());

        tableManager.initSelectionBehaviour(vista.getTablaProcesos());
        tableManager.initSelectionBehaviour(vista.getTablaAreasLibres());
        tableManager.initSelectionBehaviour(vista.getTablaParticiones());

        vista.getBtnSigMomento().addActionListener(e ->
        {
            os.siguienteMomento();
            repintarGrafico();
        });

        vista.getBtnAdmProcesos().addActionListener(e ->
        {
            EventQueue.invokeLater(() ->
            {
                AdmProcesos vistaFrm = new AdmProcesos(vista);
                vistaFrm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                vistaFrm.setLocationRelativeTo(vista);
                new AdmProcesosController(vistaFrm);
            });
        });

        vista.getFrame().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                vista.getFrame().requestFocus();
            }
        });

        repintarGrafico();
    }

    private void repintarGrafico()
    {
        grafico.actualizarGrafico(ram.getAreasLibres(), ram.getParticiones(), ram.getFragmentos());
    }

    private ArrayList<Proceso> procesos()
    {
        ArrayList<Proceso> colaProcesos = new ArrayList<>();

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

        return colaProcesos;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        actualizarEstado(arg.toString());
    }

    private void actualizarEstado(String estado)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getEstado().setText(estado);
        });
    }

}
