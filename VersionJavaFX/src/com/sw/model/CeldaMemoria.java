package com.sw.model;

/**
 *
 * @author Equipo 1
 */
public class CeldaMemoria
{

    private Proceso proceso;
    private boolean disponible;
    private boolean fragmentado;
    private int inicio;
    private int size;

    public CeldaMemoria()
    {
        this(null, true, false, 0, 0);
    }

    public CeldaMemoria(Proceso proceso, int inicio, int size)
    {
        this(proceso, true, false, inicio, size);
    }

    public CeldaMemoria(Proceso proceso, boolean disponible, int inicio, int size)
    {
        this(proceso, disponible, false, inicio, size);
    }

    public CeldaMemoria(Proceso proceso, boolean disponible, boolean fragmentado, int inicio, int size)
    {
        this.proceso = proceso;
        this.disponible = disponible;
        this.fragmentado = fragmentado;
        this.inicio = inicio;
        this.size = size;
    }

    public int getInicio()
    {
        return inicio;
    }

    public void setInicio(int inicio)
    {
        this.inicio = inicio;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public boolean isDisponible()
    {
        return disponible;
    }

    public void setDisponible(boolean disponible)
    {
        this.disponible = disponible;
    }

    public boolean isFragmentado()
    {
        return fragmentado;
    }

    public void setFragmentado(boolean fragmentado)
    {
        this.fragmentado = fragmentado;
    }

    public Proceso getProceso()
    {
        return proceso;
    }

    public void setProceso(Proceso proceso)
    {
        this.proceso = proceso;
    }

    @Override
    public String toString()
    {
        return "CeldaMemoria{" + "inicio=" + inicio + ", size=" + size + ", disponible=" + disponible + ", fragmentado=" + fragmentado + ", proceso=" + proceso + '}';
    }

}
