package Modelo;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Equipo 1
 */
public class ModeloTablas
{

    public void actualizarTablaProcesos(JTable tabla, ArrayList<Proceso> procesos)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Proceso");
        model.addColumn("Tamaño");
        model.addColumn("Llegada");
        model.addColumn("Duracion");

        for (int i = 0; i < procesos.size(); i++)
        {
            Proceso proceso = procesos.get(i);

            model.addRow(new String[]
            {
                proceso.getNombre(), Integer.toString(proceso.getSize()),
                Integer.toString(proceso.getLlegada()), Integer.toString(proceso.getDuracion())
            });
        }

        tabla.setModel(model);
    }

    public void actualizarTablaAreasLibres(JTable tabla, ArrayList<CeldaMemoria> areasLibres)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Numero");
        model.addColumn("Localidad");
        model.addColumn("Tamaño");
        model.addColumn("Estado");

        for (int i = 0; i < areasLibres.size(); i++)
        {
            CeldaMemoria celda = areasLibres.get(i);
            String estado = "Disponible";

            if (!celda.isDisponible())
                estado = "Ocupado";

            model.addRow(new String[]
            {
                Integer.toString(i), Integer.toString(celda.getInicio()),
                Integer.toString(celda.getSize()), estado
            });
        }

        tabla.setModel(model);
    }

    public void actualizarTablaParticiones(JTable tabla, ArrayList<CeldaMemoria> particiones)
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Numero");
        model.addColumn("Localidad");
        model.addColumn("Tamaño");
        model.addColumn("Estado");
        model.addColumn("Proceso");

        for (int i = 0; i < particiones.size(); i++)
        {
            CeldaMemoria celda = particiones.get(i);
            String estado = "Disponible";

            if (!celda.isDisponible())
                estado = "Ocupado";

            model.addRow(new String[]
            {
                Integer.toString(i),
                Integer.toString(celda.getInicio()),
                Integer.toString(celda.getSize()),
                estado,
                celda.getProceso().getNombre()
            });

        }

        tabla.setModel(model);
    }

}
