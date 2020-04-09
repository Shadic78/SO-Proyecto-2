package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Fragmento extends AreaLibre
{

    public Fragmento(AreaLibre areaLibre)
    {
        this(areaLibre.getInicio(), areaLibre.getSize());
    }

    public Fragmento(int inicio, int size)
    {
        super(inicio, size);
    }

}
