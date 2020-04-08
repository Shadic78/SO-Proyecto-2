package com.sw.controller;

import com.sw.model.OS;
import com.sw.model.Proceso;
import com.sw.model.RAM;
import com.sw.view.AdmProcesos;
import com.sw.view.Grafico;
import com.sw.view.Vista;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Nicolás
 */
public class VistaController implements Observer, Controller<ArrayList<Proceso>>
{

    private final Vista vista;
    private final RAM ram;
    private final TableManager tableManager;
    private Grafico grafico;
    private OS os;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        ram = new RAM(64);
        tableManager = TableManager.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        tableManager.initTabla(vista.getTablaProcesos());
        tableManager.initTabla(vista.getTablaAreasLibres());
        tableManager.initTabla(vista.getTablaParticiones());

        tableManager.initTableSelectionBehavior(vista.getTablaProcesos());
        tableManager.initTableSelectionBehavior(vista.getTablaAreasLibres());
        tableManager.initTableSelectionBehavior(vista.getTablaParticiones());

        vista.getBtnSigMomento().addActionListener(this::accionBtnSigMomento);
        vista.getBtnAdmProcesos().addActionListener(this::accionBtnAdmProcesos);

        vista.getFrame().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                vista.getFrame().requestFocus();
            }
        });
    }

    @Override
    public void establecerDatosPorDefecto(ArrayList<Proceso> procesos)
    {
        os = new OS(10, ram, procesos);
        os.addObserver(this);

        grafico = new Grafico(ram.MAX_TAM_MEMORIA(), os.MEMORIA_OS());
        vista.getPanelGrafico().add(grafico);
        repintarGrafico();

        tableManager.actualizarTablaProcesos(vista.getTablaProcesos(), procesos);
        actualizarTablas();
    }

    private void accionBtnSigMomento(ActionEvent e)
    {
        os.siguienteMomento();
        repintarGrafico();
        actualizarTablas();
    }

    private void accionBtnAdmProcesos(ActionEvent e)
    {
        EventQueue.invokeLater(() ->
        {
            AdmProcesos vistaFrm = new AdmProcesos(vista);
            AdmProcesosController admProcesosController = new AdmProcesosController(vistaFrm, this);
            admProcesosController.establecerDatosPorDefecto(os.getProcessHandler().getProcesos());
            vistaFrm.setLocationRelativeTo(vista);
            vistaFrm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vistaFrm.setVisible(true);
        });
    }

    private void repintarGrafico()
    {
        grafico.actualizarGrafico(ram.getAreasLibres(), ram.getParticiones(), ram.getFragmentos());
    }

    private void actualizarTablas()
    {
        tableManager.actualizarTablaAreasLibres(vista.getTablaAreasLibres(), ram.getAreasLibres());
        tableManager.actualizarTablaParticiones(vista.getTablaParticiones(), ram.getParticiones());
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
