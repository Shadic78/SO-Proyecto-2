package com.sw.controller;

import com.sw.model.CeldaMemoria;
import com.sw.model.Particion;
import com.sw.model.Proceso;
import com.sw.view.TableCellManager;
import com.sw.view.TableCellRenderer;
import com.sw.view.TableHeaderRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nicolás
 */
public class TableManager
{

    private static TableManager instance;

    private TableManager()
    {

    }

    public void initTabla(JTable table)
    {
        table.setDefaultRenderer(Object.class, new TableCellRenderer());
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new TableHeaderRenderer());
        jTableHeader.setReorderingAllowed(false);
        table.setTableHeader(jTableHeader);
        table.setDefaultEditor(Object.class, new TableCellManager());
        table.setGridColor(new Color(237, 237, 237));
        table.setRowSelectionAllowed(true);
        table.setRowHeight(20);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void initTableSelectionBehavior(JTable table)
    {
        initTableSelectionBehavior(table, new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                table.clearSelection();
            }
        });
    }

    public void initTableSelectionBehavior(JTable table, FocusListener focusListener)
    {
        table.addFocusListener(focusListener);

        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ESCAPE"), "Clear selection");
        table.getActionMap().put("Clear selection", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                table.getSelectionModel().clearSelection();
            }
        });
    }

    public void actualizarTablaProcesos(JTable table, ArrayList<? extends Proceso> procesos)
    {
        vaciarTabla(table);
        DefaultTableModel tableModel = getDefaultTableModel(table);

        procesos.forEach(proceso ->
        {
            tableModel.addRow(new Object[]
            {
                proceso.getNombre(),
                proceso.getSize() + "K",
                proceso.getLlegada(),
                proceso.getDuracion()
            });
        });
    }

    public void actualizarTablaAreasLibres(JTable table, ArrayList<? extends CeldaMemoria> areasLibres)
    {
        vaciarTabla(table);
        DefaultTableModel tableModel = getDefaultTableModel(table);

        areasLibres.forEach(areaLibre ->
        {
            tableModel.addRow(new Object[]
            {
                1,
                areaLibre.getInicio() + "K",
                areaLibre.getSize() + "K",
                "Disponible"
            });
        });
    }

    public void actualizarTablaParticiones(JTable table, ArrayList<Particion> particiones)
    {
        vaciarTabla(table);
        DefaultTableModel tableModel = getDefaultTableModel(table);

        particiones.forEach(particion ->
        {
            tableModel.addRow(new Object[]
            {
                1,
                particion.getInicio() + "K",
                particion.getSize() + "K",
                "Ocupado",
                particion.getProceso().getNombre()
            });
        });
    }

    public void vaciarTabla(JTable table)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);

        while (tableModel.getRowCount() > 0)
            tableModel.removeRow(0);
    }

    public void anadirFilaTabla(JTable table, Object[] fila)
    {
        DefaultTableModel tableModel = getDefaultTableModel(table);
        tableModel.addRow(fila);
    }

    public int[] obtenerFilasSeleccionadas(JTable table)
    {
        return table.getSelectedRows();
    }

    public DefaultTableModel getDefaultTableModel(JTable table)
    {
        return (DefaultTableModel) table.getModel();
    }

    public synchronized static TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }

}
