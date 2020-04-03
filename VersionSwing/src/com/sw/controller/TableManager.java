package com.sw.controller;

import com.sw.view.TableCellManager;
import com.sw.view.TableCellRenderer;
import com.sw.view.TableHeaderRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
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
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void initSelectionBehaviour(JTable table)
    {
        table.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent e)
            {
                table.clearSelection();
            }
        });

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

    public synchronized static TableManager getInstance()
    {
        if (instance == null)
            instance = new TableManager();

        return instance;
    }

}
