/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CeldaMemoria;
import Modelo.ModeloMemoria;
import Modelo.ModeloProcesos;
import Modelo.ModeloTablas;
import Modelo.Proceso;
import Vista.FrmMemoria;
import Vista.Main;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo 1
 */
public class CtrlFrmMemoria {
    private FrmMemoria form;
    private ModeloTablas modeloTablas;
    private ModeloMemoria modeloMemoria;
    private ModeloProcesos modeloProcesos;

    public CtrlFrmMemoria() {
        this.modeloTablas = new ModeloTablas();
        this.modeloMemoria = new ModeloMemoria();
        this.modeloProcesos = new ModeloProcesos();
    }
    
    public void siguientePaso() {
        if(Main.colaProcesos.size() > 0) {
            if(modeloProcesos.terminaraUnProceso(Main.particiones, Main.colaProcesos) >= 0) {
                modeloProcesos.retirarProcesoEnMemoria(modeloProcesos.terminaraUnProceso(Main.particiones, Main.colaProcesos));   
            }
            else {
                modeloProcesos.insertarProcesoEnMemoria(Main.areasLibres, Main.particiones, Main.colaProcesos, Main.colaAuxiliarProcesos);
            }
        }
        else {
            if (modeloProcesos.hayProcesosEnMemoria(Main.particiones)) {
                if(modeloProcesos.hayProcesosEnColaAuxiliar(Main.colaAuxiliarProcesos)) {
                    if(!modeloProcesos.insertarProcesoEnMemoria(Main.areasLibres, Main.particiones, Main.colaProcesos, Main.colaAuxiliarProcesos)) {
                        modeloProcesos.retirarSiguienteProceso(Main.particiones, Main.areasLibres); 
                    }                    
                }
                else {
                    modeloProcesos.retirarSiguienteProceso(Main.particiones, Main.areasLibres);   
                }
            }
            else {
                if(modeloProcesos.hayProcesosEnColaAuxiliar(Main.colaAuxiliarProcesos)) {
                    modeloProcesos.insertarProcesoEnMemoria(Main.areasLibres, Main.particiones, Main.colaProcesos, Main.colaAuxiliarProcesos);    
                }
                else {
                    JOptionPane.showMessageDialog(null, "El programa termino");
                    System.out.println("El programa termino");                    
                }
            }
        }
        modeloMemoria.compactarMemoria(Main.areasLibres);
        //modeloTablas.actualizarTablaProcesos(form.getTableProcesos(), Main.colaProcesos);
        modeloTablas.actualizarTablaAreasLibres(form.getTableEspaciosLibres(), Main.areasLibres);
        modeloTablas.actualizarTablaParticiones(form.getTableParticiones(), Main.particiones);
        
        System.out.println("\n");
        /*System.out.println("\n");        
        System.out.println("Libres: " + Main.areasLibres);
        System.out.println("Particiones: " + Main.particiones);
        System.out.println("Procesos: " + Main.colaProcesos);
        System.out.println("Aux: " + Main.colaAuxiliarProcesos);
        System.out.println("\n");*/
    }

    public FrmMemoria getForm() {
        return form;
    }

    public void setForm(FrmMemoria form) {
        this.form = form;
    }
    
}
