package com.sw.model;

/**
 *
 * @author Equipo 1
 */
public class Proceso
{

    private String nombre;
    private int size;
    private int llegada;
    private int duracion;
    private int nProceso;

    public Proceso(String nombre, int size, int llegada, int duracion)
    {
        this.nombre = nombre;
        this.size = size;
        this.llegada = llegada;
        this.duracion = duracion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getLlegada()
    {
        return llegada;
    }

    public void setLlegada(int llegada)
    {
        this.llegada = llegada;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public void setDuracion(int duracion)
    {
        this.duracion = duracion;
    }

    public int getNProceso()
    {
        return nProceso;
    }

    public void setNProceso(int nProceso)
    {
        this.nProceso = nProceso;
    }

    @Override
    public String toString()
    {
        return "Proceso{" + "nombre=" + nombre + ", size=" + size + ", llegada=" + llegada + ", duracion=" + duracion + ", nProceso=" + nProceso + '}';
    }

}
