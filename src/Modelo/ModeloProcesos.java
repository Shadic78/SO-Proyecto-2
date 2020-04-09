package Modelo;

import Vista.Main;
import java.util.ArrayList;

/**
 * *********************************************************
 *
 * PARA QUE TODOS ESTOS METODOS FUNCIONEN SE DA POR HECHO QUE LA COLA DE PROCESOS Y LA COLA AUXILIAR ESTAN ORDENADAS DE MANERA ASCENDENTE SEGUN LA LLEGADA
 *
 ***********************************************************
 */
public class ModeloProcesos
{

    public boolean insertarProcesoEnMemoria(ArrayList<CeldaMemoria> areasLibres, ArrayList<CeldaMemoria> particiones, ArrayList<Proceso> colaProcesos, ArrayList<Proceso> colaEspera)
    {
        Proceso procesoAInsertar = null;
        boolean insertado = false;

        // Primero checar la cola prioritaria
        if (colaEspera.size() > 0)
        {
            procesoAInsertar = colaEspera.get(0);
            insertado = insertarProceso(areasLibres, particiones, procesoAInsertar);

            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                colaEspera.remove(0);
                return insertado;

            } else
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());

        } // Si no hay nada en la cola prioritaria
        else
        {
            procesoAInsertar = colaProcesos.get(0);
            insertado = insertarProceso(areasLibres, particiones, procesoAInsertar);
            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                colaProcesos.remove(0);
                return insertado;
            } else
            {
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());
                colaEspera.add(procesoAInsertar);
                colaProcesos.remove(0);
                return insertado;
            }
        }

        /**
         * *********
         * Si se llega aqui es por que se intento insertar un proceso de la cola auxiliar pero no se logro, por lo que se intentara insertar un proceso de la cola principal ************
         */
        if (!insertado && colaProcesos.size() > 0)
        {
            procesoAInsertar = colaProcesos.get(0);
            insertado = insertarProceso(areasLibres, particiones, procesoAInsertar);
            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                colaProcesos.remove(0);
                return insertado;
            } else
            {
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());
                colaEspera.add(procesoAInsertar);
                colaProcesos.remove(0);
                return insertado;
            }
        }

        return insertado;
    }

    private boolean insertarProceso(ArrayList<CeldaMemoria> areasLibres, ArrayList<CeldaMemoria> particiones, Proceso procesoAInsertar)
    {
        for (int i = 0; i < areasLibres.size(); i++)
        {
            CeldaMemoria celda = areasLibres.get(i);
            int tamCelda = celda.getSize();

            if (tamCelda >= procesoAInsertar.getSize())
            {
                int tamRestante = celda.getSize() - procesoAInsertar.getSize();
                celda.setProceso(procesoAInsertar);
                celda.setSize(procesoAInsertar.getSize());
                celda.setDisponible(false);
                areasLibres.remove(i);
                particiones.add(celda);

                // Crear una nueva celda si sobra espacio
                if (tamRestante > 0)
                {
                    CeldaMemoria celdaNueva = new CeldaMemoria();
                    celdaNueva.setInicio(celda.getInicio() + celda.getSize());
                    celdaNueva.setSize(tamRestante);
                    celdaNueva.setDisponible(true);
                    areasLibres.add(celdaNueva);
                }

                return true;
            }
        }

        return false;
    }

    // Retira de la memoria el siguiente proceso a terminar
    public void retirarSiguienteProceso(ArrayList<CeldaMemoria> particiones, ArrayList<CeldaMemoria> areasLibres)
    {
        System.out.println("Retirar proceso siguiente");

        // Encontrar el proceso con la llegada + duracion mas corta
        Proceso proceso = particiones.get(0).getProceso();
        int celdaARetirar = 0;
        int finMenor = proceso.getLlegada() + proceso.getDuracion();
        int fin2 = 0;

        for (int i = 1; i < particiones.size(); i++)
        {
            proceso = particiones.get(i).getProceso();
            fin2 = proceso.getLlegada() + proceso.getDuracion();

            if (fin2 < finMenor)
            {
                finMenor = fin2;
                celdaARetirar = i;
            }
        }

        System.out.println("Se retiro: " + particiones.get(celdaARetirar).getProceso().getNombre());

        CeldaMemoria celdaDesocupada = particiones.get(celdaARetirar);
        celdaDesocupada.setProceso(null);
        celdaDesocupada.setDisponible(true);
        areasLibres.add(celdaDesocupada);
        particiones.remove(celdaARetirar);
    }

    // Devuelve el indice del proceso que terminara a continuacion
    public int terminaraUnProceso(ArrayList<CeldaMemoria> memoriaEnUso, ArrayList<Proceso> colaProcesos)
    {
        Proceso procesoEnMemoria;
        Proceso procesoEnCola;

        for (int i = 0; i < memoriaEnUso.size(); i++)
        {
            procesoEnMemoria = memoriaEnUso.get(i).getProceso();
            procesoEnCola = colaProcesos.get(0);

            if (procesoEnMemoria != null)
            {
                int finalizacion = procesoEnMemoria.getLlegada() + procesoEnMemoria.getDuracion();

                if (finalizacion <= procesoEnCola.getLlegada())
                    return i;
            }
        }

        return -1;
    }

    // Retira la celda de la particion y la agrega a las areas libres
    public void retirarProcesoEnMemoria(int celda)
    {
        CeldaMemoria celdaDesocupada = Main.particiones.get(celda);
        celdaDesocupada.setProceso(null);
        celdaDesocupada.setDisponible(true);

        Main.areasLibres.add(celdaDesocupada);
        Main.particiones.remove(celda);
    }

    public boolean hayProcesosEnMemoria(ArrayList<CeldaMemoria> memoria)
    {
        boolean existeProceso = false;
        if (memoria.size() > 0)
            existeProceso = true;
        return existeProceso;
    }

    public boolean hayProcesosEnColaAuxiliar(ArrayList<Proceso> cola)
    {
        boolean existeProceso = false;
        if (cola.size() > 0)
            existeProceso = true;
        return existeProceso;
    }

}
