/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CeldaMemoria;
import Modelo.Proceso;
import Vista.FrmMemoria;
import Vista.Main;

/**
 *
 * @author Equipo 1
 */
public class CtrlFrmMemoria {
    private FrmMemoria form;
    
    public void siguientePaso() {
        if(Main.colaProcesos.size() > 0) {
            if(terminaraUnProceso() >= 0) {
                retirarProcesoEnMemoria(terminaraUnProceso());   
            }
            else {
                insertarProcesoEnMemoria();
            }
        }
        System.out.println("\n");        
        System.out.println(Main.areasLibres);
        System.out.println(Main.particiones);
        System.out.println(Main.colaProcesos);
        System.out.println(Main.colaAuxiliarProcesos);
        System.out.println("\n");
    }
    
    private int terminaraUnProceso() {
        Proceso procesoEnMemoria;        
        Proceso procesoEnCola;
        int celda = -1;
        for(int i = 0; i < Main.particiones.size(); i++) {
            procesoEnMemoria = Main.particiones.get(i).getProceso();
            procesoEnCola = Main.colaProcesos.get(0);
            if(procesoEnMemoria != null) {
                int finalizacion = procesoEnMemoria.getLlegada() + procesoEnMemoria.getDuracion();
                if(finalizacion <= procesoEnCola.getLlegada()) {
                    celda = i;
                    break;
                }                
            }
        }
        return celda;
    }
    
    private void retirarProcesoEnMemoria(int celda) {
        CeldaMemoria celdaDesocupada = Main.particiones.get(celda);
        celdaDesocupada.setProceso(null);
        celdaDesocupada.setDisponible(true);
        Main.areasLibres.add(celdaDesocupada);
        Main.particiones.remove(celda);
    }
    
    private void insertarProcesoEnMemoria() {
        Proceso proceso = Main.colaProcesos.get(0);        
        for(int i = 0; i < Main.areasLibres.size(); i++) {
            CeldaMemoria celda = Main.areasLibres.get(i);
            int tamCelda = celda.getSize();
            if(tamCelda >= proceso.getSize()) {
                int tamRestante = celda.getSize() - proceso.getSize();
                celda.setProceso(proceso);
                celda.setSize(proceso.getSize());
                celda.setDisponible(false);
                Main.areasLibres.remove(i);
                Main.particiones.add(celda);
                Main.colaProcesos.remove(0);
                
                if(tamRestante > 0) {
                    CeldaMemoria celdaNueva = new CeldaMemoria();
                    celdaNueva.setInicio(celda.getInicio() + celda.getSize());
                    celdaNueva.setSize(tamRestante);
                    celdaNueva.setDisponible(true);
                    Main.areasLibres.add(celdaNueva);
                }
                return;
            }
        }
        // No se pudo insertar el proceso
        System.out.println("No se pudo insertar " + proceso.getNombre());
        Main.colaAuxiliarProcesos.add(proceso);
        Main.colaProcesos.remove(0);
    }

    public FrmMemoria getForm() {
        return form;
    }

    public void setForm(FrmMemoria form) {
        this.form = form;
    }
    
}
