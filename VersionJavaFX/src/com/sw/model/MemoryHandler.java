package com.sw.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author HikingCarrot7
 */
public class MemoryHandler
{

    private final RAM ram;

    public MemoryHandler(RAM ram)
    {
        this.ram = ram;
    }

    /**
     * Une dos {@link CeldaMemoria} que estén contiguas en la {@link RAM}.
     */
    public void compactarMemoria()
    {
        ArrayList<CeldaMemoria> areasLibres = ram.getAreasLibres();

        ordenarMemoria(areasLibres);

        for (int i = 0; i < areasLibres.size() - 1; i++)
        {
            CeldaMemoria celdaActual = areasLibres.get(i);
            CeldaMemoria celdaSiguiente = areasLibres.get(i + 1);

            if (celdaActual.isDisponible() && celdaSiguiente.isDisponible())
            {
                unirCeldas(celdaActual, celdaSiguiente);
                areasLibres.remove(celdaSiguiente);
                i--;
            }
        }
    }

    private CeldaMemoria unirCeldas(CeldaMemoria celda1, CeldaMemoria celda2)
    {
        if (celda2.getInicio() < celda1.getInicio())
            celda1.setInicio(celda2.getInicio());

        celda1.setSize(celda1.getSize() + celda2.getSize());

        return celda1;
    }

    private void ordenarMemoria(ArrayList<CeldaMemoria> aresLibres)
    {
        aresLibres.sort(Comparator.comparing(CeldaMemoria::getInicio));
    }

}
