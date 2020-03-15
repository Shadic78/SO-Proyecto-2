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
        else {
            if(hayProcesosEnMemoria()) {
                retirarSiguienteProceso();
            }
            else {
                System.out.println("El programa termino");
            }
        }
        System.out.println("\n");        
        System.out.println("Libres: " + Main.areasLibres);
        System.out.println("Particiones: " + Main.particiones);
        System.out.println("Procesos: " + Main.colaProcesos);
        System.out.println("Aux: " + Main.colaAuxiliarProcesos);
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
                System.out.println("Se inserto: " + proceso.getNombre());                
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
    
    private boolean hayProcesosEnMemoria() {
        boolean existeProceso = false;
        if(Main.particiones.size() > 0) {
            existeProceso = true;
        }
        return existeProceso;
    }
    
    private void retirarSiguienteProceso() {
        System.out.println("Retirar proceso siguiente");
        // Encontrar el menor
        Proceso proceso = Main.particiones.get(0).getProceso();
        int celdaARetirar = -1;
        int finMenor = proceso.getLlegada() + proceso.getDuracion();
        int fin2 = 0;
        for(int i = 0; i < Main.particiones.size(); i++) {
            proceso = Main.particiones.get(i).getProceso();
            fin2 = proceso.getLlegada() + proceso.getDuracion();
            if(fin2 <= finMenor) {
                finMenor = fin2;
                celdaARetirar = i;
            }
        }
        
        if(celdaARetirar >= 0) {
            System.out.println("Se retiro: " + Main.particiones.get(celdaARetirar).getProceso().getNombre());
            CeldaMemoria celdaDesocupada = Main.particiones.get(celdaARetirar);
            celdaDesocupada.setProceso(null);
            celdaDesocupada.setDisponible(true);
            Main.areasLibres.add(celdaDesocupada);
            Main.particiones.remove(celdaARetirar);
        }
    }

    public FrmMemoria getForm() {
        return form;
    }

    public void setForm(FrmMemoria form) {
        this.form = form;
    }
    
}
