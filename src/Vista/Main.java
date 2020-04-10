package Vista;

import Controlador.CtrlFrmMemoria;
import Modelo.CeldaMemoria;
import Modelo.Proceso;
import java.util.ArrayList;

/**
 *
 * @author Equipo 1
 */
public class Main
{

    public static ArrayList<CeldaMemoria> areasLibres = new ArrayList<>();
    public static ArrayList<CeldaMemoria> particiones = new ArrayList<>();
    public static ArrayList<Proceso> procesos = new ArrayList<>();
    public static ArrayList<Proceso> colaAuxiliarProcesos = new ArrayList<>();

    public static void main(String[] args)
    {

        /*Proceso p1 = new Proceso("A", 8, 1, 7);
        Proceso p2 = new Proceso("B", 14, 2, 7);
        Proceso p3 = new Proceso("C", 18, 3, 4);
        Proceso p4 = new Proceso("D", 6, 4, 6);
        Proceso p5 = new Proceso("E", 14, 5, 5);*/
        Proceso p1 = new Proceso("A", 8, 1, 7);
        Proceso p2 = new Proceso("B", 14, 2, 7);
        Proceso p3 = new Proceso("C", 18, 3, 4);
        Proceso p4 = new Proceso("D", 6, 4, 6);
        Proceso p5 = new Proceso("E", 14, 5, 5);

        procesos.add(p1);
        procesos.add(p2);
        procesos.add(p3);
        procesos.add(p4);
        procesos.add(p5);

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
        System.out.println(Main.procesos);
        System.out.println(Main.colaAuxiliarProcesos);
    }

}
