package com.sw.model;

import java.util.ArrayList;
import java.util.RandomAccess;

/**
 *
 * @author SonBear
 */
public class RAM implements RandomAccess, Volatil
{

    private final int MAX_TAM_MEMORIA;

    private ArrayList<Fragmento> fragmentos;
    private final ArrayList<AreaLibre> areasLibres;
    private final ArrayList<Particion> particiones;

    public RAM(final int MAX_TAM_MEMORIA)
    {
        areasLibres = new ArrayList<>();
        particiones = new ArrayList<>();
        fragmentos = new ArrayList<>();

        this.MAX_TAM_MEMORIA = MAX_TAM_MEMORIA;
    }

    public void anadirAreaLibre(AreaLibre areaLibre)
    {
        areasLibres.add(areaLibre);
    }

    public void eliminarAreaLibre(AreaLibre areaLibre)
    {
        areasLibres.remove(areaLibre);
    }

    public void eliminarAreaLibre(int indiceAreaLibre)
    {
        areasLibres.remove(indiceAreaLibre);
    }

    public void anadirParticion(Particion particion)
    {
        particiones.add(particion);
    }

    public void eliminarParticion(Particion particion)
    {
        particiones.remove(particion);
    }

    public void eliminarParticion(int indiceParticion)
    {
        particiones.remove(indiceParticion);
    }

    public AreaLibre getAreaLibre(int indiceAreaLibre)
    {
        return areasLibres.get(indiceAreaLibre);
    }

    public ArrayList<AreaLibre> getAreasLibres()
    {
        return areasLibres;
    }

    public Particion getParticion(int indiceParticion)
    {
        return particiones.get(indiceParticion);
    }

    public ArrayList<Particion> getParticiones()
    {
        return particiones;
    }

    public ArrayList<Fragmento> getFragmentos()
    {
        return fragmentos;
    }

    public void setFragmentos(ArrayList<Fragmento> fragmentos)
    {
        this.fragmentos = fragmentos;
    }

    public int MAX_TAM_MEMORIA()
    {
        return MAX_TAM_MEMORIA;
    }

    @Override
    public void eliminarTodosDatos()
    {
        areasLibres.clear();
        particiones.clear();
        fragmentos.clear();
    }

}
