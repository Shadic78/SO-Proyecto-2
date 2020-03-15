/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Main;
import java.util.ArrayList;

/***********************************************************
 * 
 *  PARA QUE TODOS ESTOS METODOS FUNCIONEN SE DA POR HECHO 
 *  QUE LA COLA DE PROCESOS Y LA COLA AUXILIAR ESTAN ORDENADAS
 *  DE MANERA ASCENDENTEMENTE SEGUN LA LLEGADA
 * 
 ************************************************************/
public class ModeloProcesos {

    public int terminaraUnProceso(ArrayList<CeldaMemoria> memoriaEnUso, ArrayList<Proceso> colaProcesos) {
        Proceso procesoEnMemoria;
        Proceso procesoEnCola;
        int celda = -1;
        for (int i = 0; i < memoriaEnUso.size(); i++) {
            procesoEnMemoria = memoriaEnUso.get(i).getProceso();
            procesoEnCola = colaProcesos.get(0);
            if (procesoEnMemoria != null) {
                int finalizacion = procesoEnMemoria.getLlegada() + procesoEnMemoria.getDuracion();
                if (finalizacion <= procesoEnCola.getLlegada()) {
                    celda = i;
                    break;
                }
            }
        }
        return celda;
    }

    public void retirarProcesoEnMemoria(int celda) {
        CeldaMemoria celdaDesocupada = Main.particiones.get(celda);
        celdaDesocupada.setProceso(null);
        celdaDesocupada.setDisponible(true);
        Main.areasLibres.add(celdaDesocupada);
        Main.particiones.remove(celda);
    }

    public void insertarProcesoEnMemoria(ArrayList<CeldaMemoria> areasLibres, ArrayList<CeldaMemoria> particiones,
            ArrayList<Proceso> colaProcesos, ArrayList<Proceso> colaAuxiliar) {
        Proceso proceso = colaProcesos.get(0);
        for (int i = 0; i < areasLibres.size(); i++) {
            CeldaMemoria celda = areasLibres.get(i);
            int tamCelda = celda.getSize();
            if (tamCelda >= proceso.getSize()) {
                int tamRestante = celda.getSize() - proceso.getSize();
                celda.setProceso(proceso);
                celda.setSize(proceso.getSize());
                celda.setDisponible(false);
                System.out.println("Se inserto: " + proceso.getNombre());
                areasLibres.remove(i);
                particiones.add(celda);
                colaProcesos.remove(0);
                
                // Crear una nueva celda si sobra espacio
                if (tamRestante > 0) {
                    CeldaMemoria celdaNueva = new CeldaMemoria();
                    celdaNueva.setInicio(celda.getInicio() + celda.getSize());
                    celdaNueva.setSize(tamRestante);
                    celdaNueva.setDisponible(true);
                    areasLibres.add(celdaNueva);
                }
                return;
            }
        }
        // No se pudo insertar el proceso
        System.out.println("No se pudo insertar " + proceso.getNombre());
        colaAuxiliar.add(proceso);
        colaProcesos.remove(0);
    }

    public boolean hayProcesosEnMemoria(ArrayList<CeldaMemoria> memoria) {
        boolean existeProceso = false;
        if (memoria.size() > 0) {
            existeProceso = true;
        }
        return existeProceso;
    }

    public void retirarSiguienteProceso(ArrayList<CeldaMemoria> particiones, ArrayList<CeldaMemoria> areasLibres) {
        System.out.println("Retirar proceso siguiente");
        // Encontrar el proceso con la llegada + duracion mas corta
        Proceso proceso = particiones.get(0).getProceso();
        int celdaARetirar = -1;
        int finMenor = proceso.getLlegada() + proceso.getDuracion();
        int fin2 = 0;
        for (int i = 0; i < particiones.size(); i++) {
            proceso = particiones.get(i).getProceso();
            fin2 = proceso.getLlegada() + proceso.getDuracion();
            if (fin2 <= finMenor) {
                finMenor = fin2;
                celdaARetirar = i;
            }
        }

        if (celdaARetirar >= 0) {
            System.out.println("Se retiro: " + particiones.get(celdaARetirar).getProceso().getNombre());
            CeldaMemoria celdaDesocupada = particiones.get(celdaARetirar);
            celdaDesocupada.setProceso(null);
            celdaDesocupada.setDisponible(true);
            areasLibres.add(celdaDesocupada);
            particiones.remove(celdaARetirar);
        }
    }
    
}
