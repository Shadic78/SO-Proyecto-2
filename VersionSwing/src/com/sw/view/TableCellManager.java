/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.view;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Nicolás
 */
public class TableCellManager implements TableCellEditor
{

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        return null;
    }

    @Override
    public Object getCellEditorValue()
    {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject e)
    {
        return false;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent)
    {
        return true;
    }

    @Override
    public boolean stopCellEditing()
    {
        return false;
    }

    @Override
    public void cancelCellEditing()
    {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l)
    {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l)
    {

    }

}