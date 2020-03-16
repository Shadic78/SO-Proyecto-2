package com.sw.model;

import java.util.Comparator;
import java.util.Observable;
import javafx.collections.ObservableList;

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
     * Une todas las {@link AreaLibre} que estén contiguas en la {@link RAM}.
     */
    public void compactarMemoria()
    {
        ObservableList<AreaLibre> areasLibres = ram.getAreasLibres();

        ordenarMemoria(); // Se ordena las celdas de memoria de acuerdo a su posición el la RAM.

        for (int i = 0; i < areasLibres.size() - 1; i++)
        {
            AreaLibre areaLibreActual = areasLibres.get(i);
            AreaLibre areaLibreSig = areasLibres.get(i + 1);

            //Si las dos celdas de memoria están disponibles, procede a unirlas.
            if (sonContiguas(areaLibreActual, areaLibreSig))
            {
                unirCeldas(areaLibreActual, areaLibreSig);
                areasLibres.remove(areaLibreSig);
                i--;
            }
        }
    }

    /**
     * Nos dice si dos {@link AreaLibre} en la {@link RAM} son contiguas.
     *
     * @param areaLibre1 El primer {@link AreaLibre}
     * @param areaLibre2 El segundo {@link AreaLibre}
     *
     * @return <code>true</code> si las dos {@link AreaLibre} son contiguas, <code>false</code> en caso contrario.
     */
    private boolean sonContiguas(AreaLibre areaLibre1, AreaLibre areaLibre2)
    {
        boolean contiguoDerecha = areaLibre1.getInicio() + areaLibre1.getSize() == areaLibre2.getInicio();
        boolean contiguoIzquierda = areaLibre2.getInicio() + areaLibre2.getSize() == areaLibre1.getInicio();

        return contiguoDerecha || contiguoIzquierda;
    }

    /**
     * Une dos {@link AreaLibre} que estén contiguas en la {@link RAM}.
     */
    private AreaLibre unirCeldas(AreaLibre areaLibre1, AreaLibre areaLibre2)
    {
        if (areaLibre2.getInicio() < areaLibre1.getInicio())
            areaLibre1.setInicio(areaLibre2.getInicio());

        areaLibre1.setSize(areaLibre1.getSize() + areaLibre2.getSize());

        return areaLibre1;
    }

    /**
     * Ordena las {@link AreaLibre} de la {@link RAM} de acuerdo a su posición en la memoria.
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
