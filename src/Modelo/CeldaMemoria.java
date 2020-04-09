package Modelo;

/**
 *
 * @author Equipo 1
 */
public class CeldaMemoria
{

    private int inicio;
    private int size;
    private boolean disponible;
    private boolean fragmentado;
    private Proceso proceso;

    public CeldaMemoria()
    {
        this.inicio = 0;
        this.size = 0;
        this.disponible = true;
        this.fragmentado = false;
        this.proceso = null;
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
