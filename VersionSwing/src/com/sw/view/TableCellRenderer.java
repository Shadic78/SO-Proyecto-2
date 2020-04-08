package com.sw.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nicolás
 */
public class TableCellRenderer extends DefaultTableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
        label.setOpaque(true);

        if (!isSelected)
        {
            label.setBackground(row % 2 == 0 ? new Color(203, 245, 225) : Color.WHITE);
            label.setForeground(Color.BLACK);

        } else
        {
            label.setBackground(new Color(0, 228, 168).darker());
            label.setForeground(Color.WHITE);
        }

        return label;
    }

}
