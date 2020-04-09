package com.sw.model;

/**
 *
 * @author SonBear
 */
public class AreaLibre extends CeldaMemoria
{

    public AreaLibre(Particion particion)
    {
        this(particion.getInicio(), particion.getSize());
    }

    public AreaLibre(int inicio, int size)
    {
        super(inicio, size);
    }

}
