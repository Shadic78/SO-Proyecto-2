package com.sw.model;

/**
 *
 * @author SonBear
 */
public class Particion extends CeldaMemoria
{

    private Proceso proceso;

    public Particion(Proceso proceso, int inicio, int size)
    {
        super(inicio, size);
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
