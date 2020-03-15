package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Equipo 1
 */
public class ModeloMemoria
{

    // Une las celdas de memoria del array
    public void compactarMemoria(ArrayList<CeldaMemoria> memoria)
    {
        ordenarMemoria(memoria);
        for (int i = 0; i < memoria.size() - 1; i++)
        {
            CeldaMemoria celdaActual = memoria.get(i);
            CeldaMemoria celdaSiguiente = memoria.get(i + 1);
            if (celdaActual.isDisponible())
                if (celdaSiguiente.isDisponible())
                    if (comprobarContinuidad(celdaActual, celdaSiguiente))
                    {
                        celdaActual = unirCeldas(celdaActual, celdaSiguiente);
                        memoria.remove(i + 1);
                        i--;
                    }
        }
    }

    private boolean comprobarContinuidad(CeldaMemoria celda1, CeldaMemoria celda2)
    {
        boolean contiguoDerecha = celda1.getInicio() + celda1.getSize() == celda2.getInicio();
        boolean contiguoIzquierda = celda2.getInicio() + celda2.getSize() == celda1.getInicio();

        return contiguoDerecha || contiguoIzquierda;
    }

    private CeldaMemoria unirCeldas(CeldaMemoria celda1, CeldaMemoria celda2)
    {
        if (celda2.getInicio() < celda1.getInicio())
            celda1.setInicio(celda2.getInicio());
        celda1.setSize(celda1.getSize() + celda2.getSize());
        return celda1;
    }

    // Bubble sort
    private void ordenarMemoria(ArrayList<CeldaMemoria> memoria)
    {
        int n = memoria.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (memoria.get(j).getInicio() > memoria.get(j + 1).getInicio())
                {
                    // swap arr[j+1] and arr[i]
                    CeldaMemoria temp = memoria.get(j);
                    memoria.set(j, memoria.get(j + 1));
                    memoria.set(j + 1, temp);
                }
    }

}
