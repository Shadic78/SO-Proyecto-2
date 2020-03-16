package com.sw.model;

/**
 *
 * @author Equipo 1
 */
public class CeldaMemoria
{

    private boolean fragmentado;
    private int inicio;
    private int size;

    public CeldaMemoria()
    {
        this(false, 0, 0);
    }

    public CeldaMemoria(int inicio, int size)
    {
        this(false, inicio, size);
    }

    public CeldaMemoria(boolean fragmentado, int inicio, int size)
    {
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

    public boolean isFragmentado()
    {
        return fragmentado;
    }

    public void setFragmentado(boolean fragmentado)
    {
        this.fragmentado = fragmentado;
    }

    public void ocupar(int size)
    {
        setSize(size);
    }

    public void ocupar(int inicio, int size)
    {
        setInicio(inicio);
        setSize(size);
    }

    @Override
    public String toString()
    {
        return "CeldaMemoria{" + "fragmentado=" + fragmentado + ", inicio=" + inicio + ", size=" + size + '}';
    }

}
