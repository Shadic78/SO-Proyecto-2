package com.sw.model;

import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HikingCarrot7
 */
@OSVersion(version = "Windows 10")
public class OS extends Observable implements Observer, Notificador
{

    private final MemoryHandler memoryHandler;
    private final ProcessHandler processHandler;
    private final int MEMORIA_OS;

    public OS(final int MEMORIA_OS, RAM ram, ObservableList<Proceso> colaProcesos)
    {
        this.MEMORIA_OS = MEMORIA_OS;
        memoryHandler = new MemoryHandler(ram);
        processHandler = new ProcessHandler(ram, FXCollections.observableArrayList(colaProcesos));

        memoryHandler.addObserver(this);
        processHandler.addObserver(this);

        iniciarOS(ram);
    }

    private void iniciarOS(RAM ram)
    {
        AreaLibre areaLibre = new AreaLibre(MEMORIA_OS, ram.MAX_TAM_MEMORIA() - MEMORIA_OS);
        ram.anadirAreaLibre(areaLibre);
    }

    /**
     * Siguiente momento para este sistema operativo.
     */
    public void siguienteMomento()
    {
        if (processHandler.hayProcesosPorDespachar())
        {
            int celdaATerminar = processHandler.siguienteProcesoATerminar();

            if (celdaATerminar >= 0)
                processHandler.retirarProcesoEnMemoria(celdaATerminar);
            else
                processHandler.insertarProcesoEnMemoria();

        } else if (processHandler.hayProcesosEnMemoria())
            if (processHandler.hayProcesosEnEspera())
            {
                if (!processHandler.insertarProcesoEnMemoria())
                    processHandler.retirarSiguienteProceso();

            } else
                processHandler.retirarSiguienteProceso();

        else if (processHandler.hayProcesosEnEspera())
            processHandler.insertarProcesoEnMemoria();

        else
            notificar("El programa ha terminado");

        memoryHandler.compactarMemoria();
    }

    public int MEMORIA_OS()
    {
        return MEMORIA_OS;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        notificar(arg.toString());
    }

    @Override
    public void notificar(String mensaje)
    {
        setChanged();
        notifyObservers(mensaje);
        clearChanged();
    }

}
