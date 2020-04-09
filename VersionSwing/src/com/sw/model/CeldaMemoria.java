package com.sw.model;

/**
 *
 * @author Equipo 1
 */
public abstract class CeldaMemoria
{

    private int inicio;
    private int size;

    public CeldaMemoria()
    {
        this(0, 0);
    }

    public CeldaMemoria(int inicio, int size)
    {
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
        return "CeldaMemoria{" + "inicio=" + inicio + ", size=" + size + '}';
    }

}
