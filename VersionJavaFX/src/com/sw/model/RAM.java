package com.sw.model;

import java.util.RandomAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HikingCarrot7
 */
public class RAM implements RandomAccess, Volatil
{

    private final ObservableList<AreaLibre> areasLibres;
    private final ObservableList<Particion> particiones;

    public RAM()
    {
        areasLibres = FXCollections.observableArrayList();
        particiones = FXCollections.observableArrayList();
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

    public ObservableList<AreaLibre> getAreasLibres()
    {
        return areasLibres;
    }

    public Particion getParticion(int indiceParticion)
    {
        return particiones.get(indiceParticion);
    }

    public ObservableList<Particion> getParticiones()
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
