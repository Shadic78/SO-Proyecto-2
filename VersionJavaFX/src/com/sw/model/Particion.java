package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Particion extends CeldaMemoria
{

    private Proceso proceso;

    public Particion(Proceso proceso)
    {
        this(proceso, false, 0, 0);
    }

    public Particion(Proceso proceso, int size)
    {
        this(proceso, false, 0, size);
    }

    public Particion(Proceso proceso, int inicio, int size)
    {
        this(proceso, false, inicio, size);
    }

    public Particion(Proceso proceso, boolean fragmentado, int inicio, int size)
    {
        super(fragmentado, inicio, size);
        this.proceso = proceso;
    }

    public void setProceso(Proceso proceso)
    {
        this.proceso = proceso;
    }

    public Proceso getProceso()
    {
        return proceso;
    }

}
