package com.sw.model;

import java.util.ArrayList;
import java.util.RandomAccess;

/**
 *
 * @author HikingCarrot7
 */
public class RAM implements RandomAccess, Volatil
{

    private final ArrayList<CeldaMemoria> areasLibres;
    private final ArrayList<CeldaMemoria> particiones;

    public RAM()
    {
        areasLibres = new ArrayList<>();
        particiones = new ArrayList<>();
    }

    public void anadirAreaLibre(CeldaMemoria celdaMemoria)
    {
        areasLibres.add(celdaMemoria);
    }

    public void eliminarAreaLibre(CeldaMemoria celdaMemoria)
    {
        areasLibres.remove(celdaMemoria);
    }

    public void eliminarAreaLibre(int indiceCeldaMemoria)
    {
        areasLibres.remove(indiceCeldaMemoria);
    }

    public void anadirParticion(CeldaMemoria celdaMemoria)
    {
        particiones.add(celdaMemoria);
    }

    public void eliminarParticion(CeldaMemoria celdaMemoria)
    {
        particiones.remove(celdaMemoria);
    }

    public void eliminarParticion(int indiceCeldaMemoria)
    {
        particiones.remove(indiceCeldaMemoria);
    }

    public CeldaMemoria getAreaLibre(int indiceCeldaMemoria)
    {
        return areasLibres.get(indiceCeldaMemoria);
    }

    public ArrayList<CeldaMemoria> getAreasLibres()
    {
        return areasLibres;
    }

    public CeldaMemoria getParticion(int indiceCeldaMemoria)
    {
        return particiones.get(indiceCeldaMemoria);
    }

    public ArrayList<CeldaMemoria> getParticiones()
    {
        return particiones;
    }

    @Override
    public void eliminarTodosDatos()
    {
        areasLibres.clear();
        particiones.clear();
    }

}
