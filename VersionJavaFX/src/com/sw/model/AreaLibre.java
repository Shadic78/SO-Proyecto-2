package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class AreaLibre extends CeldaMemoria
{

    public AreaLibre(Particion particion)
    {
        this(particion.getInicio(), particion.getSize());
    }

    public AreaLibre(int inicio, int size)
    {
        this(false, inicio, size);
    }

    public AreaLibre(boolean fragmentado, int inicio, int size)
    {
        super(fragmentado, inicio, size);
    }

}
