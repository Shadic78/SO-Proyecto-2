package com.sw.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nicolás
 */
public class TableHeaderRenderer implements TableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 13));
        label.setSize(30, label.getWidth());
        label.setPreferredSize(new Dimension(6, label.getWidth()));

        label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
        label.setOpaque(true);
        label.setBackground(new Color(0, 228, 168));
        label.setForeground(Color.white);

        return label;
    }

}
