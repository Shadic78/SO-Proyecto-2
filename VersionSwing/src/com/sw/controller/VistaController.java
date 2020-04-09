/*
 * The MIT License
 *
 * Copyright 2020 Eusebio Ajax.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
 * @author Eusebio Ajax
 */
public class VistaController implements Observer, Controller<ArrayList<Proceso>>
{

    private final Vista vista;
    private final TableManager tableManager;
    private final RAM ram;
    private ArrayList<Proceso> procesos;
    private Grafico grafico;
    private OS os;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        this.ram = new RAM(64);
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
        this.procesos = procesos;
        reiniciarSimulacion(procesos);

        if (grafico == null)
        {
            grafico = new Grafico(ram.MAX_TAM_MEMORIA(), os.MEMORIA_OS());
            vista.getPanelGrafico().add(grafico);
        }
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
            admProcesosController.establecerDatosPorDefecto(procesos);
            vistaFrm.setLocationRelativeTo(vista);
            vistaFrm.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            vistaFrm.setVisible(true);
        });
    }

    private void reiniciarSimulacion(ArrayList<Proceso> procesos)
    {
        os = new OS(10, ram, procesos);
        os.addObserver(this);
        repintarGrafico();
        tableManager.actualizarTablaProcesos(vista.getTablaProcesos(), this.procesos);
        actualizarTablas();
        actualizarEstado(" ");
    }

    private void actualizarTablas()
    {
        tableManager.actualizarTablaAreasLibres(vista.getTablaAreasLibres(),
                os.getMemoryHandler().ordenarCeldasMemoriaPorInicio(ram.getAreasLibres()));

        tableManager.actualizarTablaParticiones(vista.getTablaParticiones(),
                os.getMemoryHandler().ordenarCeldasMemoriaPorInicio(ram.getParticiones()));
    }

    private void vaciarTablas()
    {
        tableManager.vaciarTabla(vista.getTablaProcesos());
        tableManager.vaciarTabla(vista.getTablaAreasLibres());
        tableManager.vaciarTabla(vista.getTablaAreasLibres());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        actualizarEstado(arg.toString());
    }

    private void repintarGrafico()
    {
        EventQueue.invokeLater(() ->
        {
            grafico.actualizarGrafico(ram.getAreasLibres(), ram.getParticiones(), ram.getFragmentos());
        });
    }

    private void actualizarEstado(String estado)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getEstado().setText(estado);
        });
    }

}
