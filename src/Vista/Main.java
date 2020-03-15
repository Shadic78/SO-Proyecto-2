/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CtrlFrmMemoria;
import Modelo.CeldaMemoria;
import Modelo.Proceso;
import java.util.ArrayList;

/**
 *
 * @author Equipo 1
 */
public class Main {

    public static ArrayList<CeldaMemoria> areasLibres = new ArrayList<CeldaMemoria>();
    public static ArrayList<CeldaMemoria> particiones = new ArrayList<CeldaMemoria>();    
    public static ArrayList<Proceso> colaProcesos = new ArrayList<Proceso>();
    public static ArrayList<Proceso> colaAuxiliarProcesos = new ArrayList<Proceso>();
    
    public static void main(String[] args) {
        
        Proceso p1 = new Proceso("A", 8, 1, 7);
        Proceso p2 = new Proceso("B", 14, 2, 7);
        Proceso p3 = new Proceso("C", 18, 3, 4);
        Proceso p4 = new Proceso("D", 6, 4, 6);
        Proceso p5 = new Proceso("E", 14, 5, 5);        
        
        colaProcesos.add(p1);
        colaProcesos.add(p2);
        colaProcesos.add(p3);
        colaProcesos.add(p4);
        colaProcesos.add(p5);

        CeldaMemoria so = new CeldaMemoria();
        so.setDisponible(false);
        so.setInicio(0);
        so.setSize(10);
        so.setProceso(null);
        
        CeldaMemoria inicial = new CeldaMemoria();
        inicial.setInicio(10);
        inicial.setSize(54);
        areasLibres.add(inicial);
        
        FrmMemoria ventana = new FrmMemoria();
        CtrlFrmMemoria control = new CtrlFrmMemoria();
        ventana.setControl(control);
        control.setForm(ventana);        
        ventana.setVisible(true);
        
        System.out.println(Main.areasLibres);
        System.out.println(Main.particiones);
        System.out.println(Main.colaProcesos);
        System.out.println(Main.colaAuxiliarProcesos);
    }
    
}
