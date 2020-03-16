package com.sw.model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HikingCarrot7
 */
public class OS
{

    private final MemoryHandler memoryHandler;
    private final ProcessHandler processHandler;

    public OS(RAM ram, ArrayList<Proceso> colaProcesos)
    {
        memoryHandler = new MemoryHandler(ram);
        processHandler = new ProcessHandler(ram, colaProcesos);
    }

    public void siguienteMomento()
    {
        if (processHandler.hayProcesosPorDespachar())
        {
            int celdaATerminar = processHandler.terminaraUnProceso();

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
        {
            JOptionPane.showMessageDialog(null, "El programa terminó");
            System.out.println("El programa terminó");
        }

        memoryHandler.compactarMemoria();
    }

}
