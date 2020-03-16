package com.sw.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author HikingCarrot7
 */
public class ProcessHandler extends Observable implements Notificador
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
            procesoAInsertar = procesosEnEspera.get(0); // Obtenemos al primer proceso que esté esperando.
            insertado = insertarProceso(procesoAInsertar); // Tratamos de añadirlo a la RAM.

            if (insertado) // Si pudimos añadirlo.
            {
                notificar("Se insertó: " + procesoAInsertar.getNombre());
                procesosEnEspera.remove(0); // Lo eliminamos de la cola de espera.
                return insertado;

            } else if (hayProcesosPorDespachar()) // Si se llegamos hasta aquí es porque se intentó insertar un proceso de la cola de espera pero no se logró, por lo que se intentará insertar un proceso de la cola principal.
            {
                procesoAInsertar = colaProcesos.get(0);
                insertado = insertarProceso(procesoAInsertar);

                if (insertado)
                {
                    notificar("Se insertó: " + procesoAInsertar.getNombre());
                    colaProcesos.remove(0);
                    return insertado;

                } else
                {
                    notificar("No se pudo insertar: " + procesoAInsertar.getNombre());
                    procesosEnEspera.add(procesoAInsertar);
                    colaProcesos.remove(0);
                    return insertado;
                }
            }

        } else // Si no hay nada en la cola de espera.
        {
            procesoAInsertar = colaProcesos.get(0); // Tomamos al primer proceso de la cola de procesos.
            insertado = insertarProceso(procesoAInsertar); // Tratamos de insertarlo en la RAM.

            if (insertado) // Si se pudo insertar.
            {
                notificar("Se insertó: " + procesoAInsertar.getNombre());
                colaProcesos.remove(0); // Lo eliminamos de la cola de procesos.
                return insertado;

            } else
            {
                notificar("No se pudo insertar: " + procesoAInsertar.getNombre());
                procesosEnEspera.add(procesoAInsertar); // Añadimos el proceso a la cola de espera, no se pudo añadir a la RAM.
                colaProcesos.remove(0); // Lo sacamos de la cola de procesos.
                return insertado;
            }
        }

        notificar("No se pudo insertar: " + procesoAInsertar.getNombre());
        return insertado;
    }

    /**
     * Tratará de insertar un {@link Proceso} en la {@link RAM}, en caso de ser posible procederá con la operación. Cancelará la operación en caso contrario.
     *
     * @param procesoAInsertar El {@link Proceso} que se tratará de insertar.
     *
     * @return <code>true</code> si el {@link Proceso} se pudo insertar, <code>false</code> en caso contrario.
     */
    private boolean insertarProceso(Proceso procesoAInsertar)
    {
        for (int i = 0; i < ram.getAreasLibres().size(); i++)
        {
            CeldaMemoria celda = ram.getAreaLibre(i);
            int tamCelda = celda.getSize();

            if (tamCelda >= procesoAInsertar.getSize())
            {
                int tamRestante = celda.getSize() - procesoAInsertar.getSize();
                celda.ocupar(procesoAInsertar, procesoAInsertar.getSize());
                ram.eliminarAreaLibre(i);
                ram.anadirParticion(celda);

                // Crear una nueva celda si sobra espacio.
                if (tamRestante > 0)
                    ram.anadirAreaLibre(new CeldaMemoria(celda.getInicio() + celda.getSize(), tamRestante));

                return true;
            }
        }

        return false;
    }

    /**
     * Elimina la {@link CeldaMemoria} especificada de la memoria {@link RAM}.
     *
     * @param celdaMemoria La {@link CeldaMemoria} ha ser removida.
     */
    public void retirarProcesoEnMemoria(int indiceCeldaMemoria)
    {
        retirarProcesoEnMemoria(ram.getParticion(indiceCeldaMemoria));
    }

    /**
     * Elimina la {@link CeldaMemoria} especificada de la memoria {@link RAM}.
     *
     * @param celdaMemoria La {@link CeldaMemoria} ha ser removida.
     */
    public void retirarProcesoEnMemoria(CeldaMemoria celdaMemoria)
    {
        celdaMemoria.liberar();
        ram.anadirAreaLibre(celdaMemoria);
        ram.eliminarParticion(celdaMemoria);
    }

    /**
     * Retorna el índice del {@link Proceso} que terminará a continuación.
     *
     * @return El índice del {@link Proceso} que terminará a continuación o -1 si no hay.
     */
    public int siguienteProcesoATerminar()
    {
        ArrayList<CeldaMemoria> particiones = ram.getParticiones();

        for (int i = 0; i < particiones.size(); i++)
        {
            Proceso procesoEnMemoria = particiones.get(i).getProceso();
            Proceso procesoEnCola = colaProcesos.get(0);

            int finalizacion = procesoEnMemoria.getLlegada() + procesoEnMemoria.getDuracion();

            if (finalizacion <= procesoEnCola.getLlegada())
                return i;
        }

        return -1;
    }

    /**
     * Retira al siguiente {@link Proceso} de la {@link RAM}. El proceso a retirar es determinado por la llegada + duración más corta.
     */
    public void retirarSiguienteProceso()
    {
        ArrayList<CeldaMemoria> particiones = ram.getParticiones();

        // Encontrar el proceso con la llegada + duración más corta.
        CeldaMemoria celdaARetirar = particiones.get(0);
        Proceso proceso = particiones.get(0).getProceso();
        int menorTiempoFinalizacion = proceso.getLlegada() + proceso.getDuracion();

        for (int i = 1; i < particiones.size(); i++)
        {
            proceso = particiones.get(i).getProceso();
            int tiempoFinalizacion = proceso.getLlegada() + proceso.getDuracion();

            if (tiempoFinalizacion < menorTiempoFinalizacion)
            {
                menorTiempoFinalizacion = tiempoFinalizacion;
                celdaARetirar = particiones.get(i);
            }
        }

        retirarProcesoEnMemoria(celdaARetirar);
        notificar("Se retiró: " + celdaARetirar.getProceso().getNombre());
    }

    @Override
    public void notificar(String mensaje)
    {
        setChanged();
        notifyObservers(mensaje);
        clearChanged();
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

    public ArrayList<Proceso> getColaProcesos()
    {
        return colaProcesos;
    }

    public ArrayList<Proceso> getProcesosEspera()
    {
        return procesosEnEspera;
    }

}
