package com.sw.model;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class ProcessHandler
{

    private final RAM ram;
    private final ArrayList<Proceso> colaProcesos;
    private final ArrayList<Proceso> procesosEnEspera;

    public ProcessHandler(RAM ram, ArrayList<Proceso> colaProcesos)
    {
        this.ram = ram;
        this.colaProcesos = colaProcesos;
        procesosEnEspera = new ArrayList<>();
    }

    public boolean insertarProcesoEnMemoria()
    {
        Proceso procesoAInsertar;
        boolean insertado;

        // Primero checamos la cola de espera.
        if (hayProcesosEnEspera())
        {
            procesoAInsertar = procesosEnEspera.get(0);
            insertado = insertarProceso(procesoAInsertar);

            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                procesosEnEspera.remove(0);
                return insertado;

            } else
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());

        } // Si no hay nada en la cola de espera
        else
        {
            procesoAInsertar = colaProcesos.get(0);
            insertado = insertarProceso(procesoAInsertar);

            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                colaProcesos.remove(0);
                return insertado;

            } else
            {
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());
                procesosEnEspera.add(procesoAInsertar);
                colaProcesos.remove(0);
                return insertado;
            }
        }

        /**
         * *********
         * Si se llega aqui es por que se intento insertar un proceso de la cola de espera pero no se logro, por lo que se intentara insertar un proceso de la cola principal ************
         */
        if (!insertado && colaProcesos.size() > 0)
        {
            procesoAInsertar = colaProcesos.get(0);
            insertado = insertarProceso(procesoAInsertar);

            if (insertado)
            {
                System.out.println("Se inserto: " + procesoAInsertar.getNombre());
                colaProcesos.remove(0);
                return insertado;

            } else
            {
                System.out.println("No se pudo insertar: " + procesoAInsertar.getNombre());
                procesosEnEspera.add(procesoAInsertar);
                colaProcesos.remove(0);
                return insertado;
            }
        }

        return insertado;
    }

    private boolean insertarProceso(Proceso procesoAInsertar)
    {
        ArrayList<CeldaMemoria> areasLibres = ram.getAreasLibres();
        ArrayList<CeldaMemoria> particiones = ram.getParticiones();

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

    public int terminaraUnProceso()
    {
        ArrayList<CeldaMemoria> particiones = ram.getParticiones();

        for (int i = 0; i < particiones.size(); i++)
        {
            CeldaMemoria celdaMemoria = particiones.get(i);

            if (!celdaMemoria.isDisponible())
            {
                Proceso procesoEnMemoria = celdaMemoria.getProceso();
                Proceso procesoEnCola = colaProcesos.get(0);

                int finalizacion = procesoEnMemoria.getLlegada() + procesoEnMemoria.getDuracion();

                if (finalizacion <= procesoEnCola.getLlegada())
                    return i;
            }
        }

        return -1;
    }

    public void retirarSiguienteProceso()
    {
        ArrayList<CeldaMemoria> particiones = ram.getParticiones();

        System.out.println("Retirar proceso siguiente");

        // Encontrar el proceso con la llegada + duracion mas corta
        Proceso proceso = particiones.get(0).getProceso();
        int celdaARetirar = 0;
        int finMenor = proceso.getLlegada() + proceso.getDuracion();

        for (int i = 1; i < particiones.size(); i++)
        {
            proceso = particiones.get(i).getProceso();
            int tiempoFin = proceso.getLlegada() + proceso.getDuracion();

            if (tiempoFin < finMenor)
            {
                finMenor = tiempoFin;
                celdaARetirar = i;
            }
        }

        System.out.println("Se retiro: " + particiones.get(celdaARetirar).getProceso().getNombre());

        CeldaMemoria celdaDesocupada = particiones.get(celdaARetirar);
        celdaDesocupada.liberar();
        ram.anadirAreaLibre(celdaDesocupada);
        particiones.remove(celdaARetirar);
    }

    public void retirarProcesoEnMemoria(int celda)
    {
        CeldaMemoria celdaDesocupada = ram.getParticiones().get(celda);
        celdaDesocupada.liberar();
        ram.anadirAreaLibre(celdaDesocupada);
        ram.eliminarParticion(celdaDesocupada);
    }

    public ArrayList<Proceso> getColaProcesos()
    {
        return colaProcesos;
    }

    public ArrayList<Proceso> getProcesosEspera()
    {
        return procesosEnEspera;
    }

    public boolean hayProcesosEnEspera()
    {
        return !getProcesosEspera().isEmpty();
    }

    public boolean hayProcesosPorDespachar()
    {
        return !getColaProcesos().isEmpty();
    }

    public boolean hayProcesosEnMemoria()
    {
        return !ram.getParticiones().isEmpty();
    }

}
