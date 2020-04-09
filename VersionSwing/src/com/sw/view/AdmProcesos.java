package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eusebio Ajax
 */
public class AdmProcesos extends JDialog
{

    public AdmProcesos(Window owner)
    {
        super(owner);
        initLookAndFeel();
        initComponents();
    }

    private void initLookAndFeel()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
        //</editor-fold>
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        GridBagConstraints gridBagConstraints;

        jPanel6 = new JPanel();
        filler8 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        jPanel5 = new JPanel();
        filler7 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        jPanel1 = new JPanel();
        filler4 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jLabel1 = new JLabel();
        filler1 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        nombreProceso = new JTextField();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        filler2 = new Box.Filler(new Dimension(15, 0), new Dimension(15, 0), new Dimension(15, 0));
        tamanioProceso = new JFormattedTextField();
        tiempoLlegada = new JFormattedTextField();
        duracion = new JFormattedTextField();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        filler9 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel3 = new JPanel();
        anadirProceso = new JButton();
        modificarProceso = new JButton();
        eliminarProceso = new JButton();
        jPanel4 = new JPanel();
        filler5 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jSeparator1 = new JSeparator();
        filler10 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel7 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tablaProcesos = new JTable();
        filler6 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500, 400));

        jPanel6.add(filler8);

        getContentPane().add(jPanel6, BorderLayout.EAST);

        jPanel5.add(filler7);

        getContentPane().add(jPanel5, BorderLayout.WEST);

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(filler4);

        jLabel1.setText("Administrador de procesos");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1);
        jPanel1.add(filler1);

        jPanel2.setLayout(new GridBagLayout());

        jLabel2.setText("Proceso:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 5, 2, 2);
        jPanel2.add(jLabel2, gridBagConstraints);

        nombreProceso.setToolTipText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(2, 12, 2, 2);
        jPanel2.add(nombreProceso, gridBagConstraints);

        jLabel3.setText("Tamaño:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 5, 2, 2);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Tiempo de llegada:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 5, 2, 2);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Duración:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 5, 2, 2);
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        jPanel2.add(filler2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(2, 12, 2, 2);
        jPanel2.add(tamanioProceso, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 12, 2, 2);
        jPanel2.add(tiempoLlegada, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 12, 2, 2);
        jPanel2.add(duracion, gridBagConstraints);

        jLabel6.setText("K");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("K");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setText("K");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jPanel1.add(jPanel2);
        jPanel1.add(filler9);

        anadirProceso.setText("Añadir proceso");
        jPanel3.add(anadirProceso);

        modificarProceso.setText("Modificar proceso");
        jPanel3.add(modificarProceso);

        eliminarProceso.setText("Eliminar proceso");
        jPanel3.add(eliminarProceso);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, BorderLayout.NORTH);

        jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS));
        jPanel4.add(filler5);
        jPanel4.add(jSeparator1);
        jPanel4.add(filler10);

        jPanel7.setBorder(BorderFactory.createTitledBorder("Procesos"));
        jPanel7.setLayout(new BorderLayout());

        tablaProcesos.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Proceso", "Tamaño", "Tiempo llegada", "Duración"
            }
        ));
        jScrollPane1.setViewportView(tablaProcesos);

        jPanel7.add(jScrollPane1, BorderLayout.CENTER);

        jPanel4.add(jPanel7);
        jPanel4.add(filler6);

        getContentPane().add(jPanel4, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getTablaProcesos()
    {
        return tablaProcesos;
    }

    public JTextField getNombreProceso()
    {
        return nombreProceso;
    }

    public JFormattedTextField getTamanioProceso()
    {
        return tamanioProceso;
    }

    public JFormattedTextField getDuracion()
    {
        return duracion;
    }

    public JFormattedTextField getTiempoLlegada()
    {
        return tiempoLlegada;
    }

    public JButton getAnadirProceso()
    {
        return anadirProceso;
    }

    public JButton getEliminarProceso()
    {
        return eliminarProceso;
    }

    public JButton getModificarProceso()
    {
        return modificarProceso;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton anadirProceso;
    private JFormattedTextField duracion;
    private JButton eliminarProceso;
    private Box.Filler filler1;
    private Box.Filler filler10;
    private Box.Filler filler2;
    private Box.Filler filler4;
    private Box.Filler filler5;
    private Box.Filler filler6;
    private Box.Filler filler7;
    private Box.Filler filler8;
    private Box.Filler filler9;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JButton modificarProceso;
    private JTextField nombreProceso;
    private JTable tablaProcesos;
    private JFormattedTextField tamanioProceso;
    private JFormattedTextField tiempoLlegada;
    // End of variables declaration//GEN-END:variables
}
