package com.sw.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

/**
 *
 * @author HikingCarrot7
 */
public class MemoryHandler extends Observable implements Notificador
{

    private final RAM ram;

    public MemoryHandler(RAM ram)
    {
        this.ram = ram;
    }

    /**
     * Une todas las {@link CeldaMemoria} que estén contiguas en la {@link RAM}.
     */
    public void compactarMemoria()
    {
        ArrayList<CeldaMemoria> areasLibres = ram.getAreasLibres();

        ordenarMemoria(); // Se ordena las celdas de memoria de acuerdo a su posición el la RAM.

        for (int i = 0; i < areasLibres.size() - 1; i++)
        {
            CeldaMemoria celdaActual = areasLibres.get(i);
            CeldaMemoria celdaSiguiente = areasLibres.get(i + 1);

            //Si las dos celdas de memoria están disponibles, procede a unirlas.
            if (celdaActual.isDisponible() && celdaSiguiente.isDisponible())
            {
                unirCeldas(celdaActual, celdaSiguiente);
                areasLibres.remove(celdaSiguiente);
                i--;
            }
        }
    }

    /**
     * Une dos {@link CeldaMemoria} que estén contiguas en la {@link RAM}.
     */
    private CeldaMemoria unirCeldas(CeldaMemoria celda1, CeldaMemoria celda2)
    {
        if (celda2.getInicio() < celda1.getInicio())
            celda1.setInicio(celda2.getInicio());

        celda1.setSize(celda1.getSize() + celda2.getSize());

        return celda1;
    }

    /**
     * Ordena las {@link CeldaMemoria} de la {@link RAM} de acuerdo a su posición en la memoria.
     */
    private void ordenarMemoria()
    {
        ram.getAreasLibres().sort(Comparator.comparing(CeldaMemoria::getInicio));
    }

    @Override
    public void notificar(String mensaje)
    {
        setChanged();
        notifyObservers(mensaje);
        clearChanged();
    }

}
