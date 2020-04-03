package com.sw.controller;

import com.sw.view.AdmProcesos;

/**
 *
 * @author Nicolás
 */
public class AdmProcesosController
{

    private final AdmProcesos vistaFrm;
    private final TableManager tableManager;

    public AdmProcesosController(AdmProcesos vistaFrm)
    {
        this.vistaFrm = vistaFrm;
        tableManager = TableManager.getInstance();
        initComponents();
        vistaFrm.setVisible(true);
    }

    private void initComponents()
    {
        tableManager.initTabla(vistaFrm.getTablaProcesos());
    }

}
