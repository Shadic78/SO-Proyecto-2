package com.sw.model;

import java.util.ArrayList;
import java.util.RandomAccess;

/**
 *
 * @author HikingCarrot7
 */
public class RAM implements RandomAccess
{

    private final ArrayList<CeldaMemoria> areasLibres;
    private final ArrayList<CeldaMemoria> particiones;
    private final ArrayList<Proceso> procesosUsandome;

    public RAM()
    {
        areasLibres = new ArrayList<>();
        particiones = new ArrayList<>();
        procesosUsandome = new ArrayList<>();
    }

    public boolean puedoInsertarProceso(Proceso procesoAInsertar)
    {
        return areasLibres.stream().anyMatch(celda -> celda.getSize() >= procesoAInsertar.getSize());
    }

    public void anadirAreaLibre(CeldaMemoria celdaMemoria)
    {
        areasLibres.add(celdaMemoria);
    }

    public void eliminarAreaLibre(CeldaMemoria celdaMemoria)
    {
        areasLibres.remove(celdaMemoria);
    }

    public void anadirParticion(CeldaMemoria celdaMemoria)
    {
        particiones.add(celdaMemoria);
    }

    public void eliminarParticion(CeldaMemoria celdaMemoria)
    {
        particiones.remove(celdaMemoria);
    }

    public ArrayList<CeldaMemoria> getAreasLibres()
    {
        return areasLibres;
    }

    public ArrayList<CeldaMemoria> getParticiones()
    {
        return particiones;
    }

    public ArrayList<Proceso> getProcesosUsandome()
    {
        return procesosUsandome;
    }

}
