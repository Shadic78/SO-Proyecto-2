package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolás
 */
public class Vista extends JFrame
{

    /**
     * Creates new form NewJFrame
     */
    public Vista()
    {
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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
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

        jPanel1 = new JPanel();
        filler3 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        btnSigMomento = new JButton();
        estado = new JLabel();
        filler5 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel2 = new JPanel();
        filler4 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jLabel4 = new JLabel();
        filler7 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(0, 10));
        jPanel4 = new JPanel();
        jScrollPane3 = new JScrollPane();
        tablaProcesos = new JTable();
        btnAdmProcesos = new JButton();
        jPanel12 = new JPanel();
        filler8 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        filler1 = new Box.Filler(new Dimension(15, 0), new Dimension(15, 0), new Dimension(15, 32767));
        filler9 = new Box.Filler(new Dimension(15, 0), new Dimension(15, 0), new Dimension(15, 32767));
        jPanel8 = new JPanel();
        jPanel3 = new JPanel();
        jPanel6 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tablaAreasLibres = new JTable();
        filler6 = new Box.Filler(new Dimension(0, 10), new Dimension(0, 10), new Dimension(32767, 10));
        jPanel7 = new JPanel();
        jScrollPane2 = new JScrollPane();
        tablaParticiones = new JTable();
        panelGrafico = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(252, 252, 252));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 700));

        jPanel1.setBackground(new Color(252, 252, 252));
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.add(filler3);

        btnSigMomento.setText("Siguiente paso");
        btnSigMomento.setToolTipText("");
        btnSigMomento.setAlignmentX(0.5F);
        btnSigMomento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPanel1.add(btnSigMomento);

        estado.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        estado.setText(" ");
        estado.setAlignmentX(0.5F);
        jPanel1.add(estado);
        jPanel1.add(filler5);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        jPanel2.setBackground(new Color(252, 252, 252));
        jPanel2.setMaximumSize(new Dimension(450, 150));
        jPanel2.setMinimumSize(new Dimension(450, 150));
        jPanel2.setPreferredSize(new Dimension(450, 180));
        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel2.add(filler4);

        jLabel4.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("SIMULACIÓN DE ASIGNACIÓN DE MEMORIA CON MVT");
        jLabel4.setAlignmentX(0.5F);
        jPanel2.add(jLabel4);
        jPanel2.add(filler7);

        jPanel4.setBackground(new Color(252, 252, 252));
        jPanel4.setLayout(new GridBagLayout());

        tablaProcesos.setBackground(new Color(252, 252, 252));
        tablaProcesos.setModel(new DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Proceso", "Tamaño", "Tiempo de llegada", "Duración"
            }
        ));
        tablaProcesos.setGridColor(new Color(237, 237, 237));
        jScrollPane3.setViewportView(tablaProcesos);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new Insets(0, 40, 0, 0);
        jPanel4.add(jScrollPane3, gridBagConstraints);

        btnAdmProcesos.setText("<html><font size=-1><center>Administrar<p>procesos</center></html>");
        btnAdmProcesos.setAlignmentX(0.5F);
        btnAdmProcesos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdmProcesos.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 45);
        jPanel4.add(btnAdmProcesos, gridBagConstraints);

        jPanel12.setBackground(new Color(252, 252, 252));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 10, 0, 45);
        jPanel4.add(jPanel12, gridBagConstraints);

        jPanel2.add(jPanel4);
        jPanel2.add(filler8);

        getContentPane().add(jPanel2, BorderLayout.NORTH);

        filler1.setBackground(new Color(252, 252, 252));
        filler1.setOpaque(true);
        getContentPane().add(filler1, BorderLayout.LINE_END);

        filler9.setBackground(new Color(252, 252, 252));
        filler9.setOpaque(true);
        getContentPane().add(filler9, BorderLayout.LINE_START);

        jPanel8.setBackground(new Color(252, 252, 252));
        GridBagLayout jPanel8Layout = new GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {400};
        jPanel8Layout.columnWeights = new double[] {0.8, 0.2};
        jPanel8.setLayout(jPanel8Layout);

        jPanel3.setBackground(new Color(252, 252, 252));
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));

        jPanel6.setBackground(new Color(252, 252, 252));
        jPanel6.setBorder(BorderFactory.createTitledBorder("Tabla de areás libres"));
        jPanel6.setLayout(new BorderLayout());

        tablaAreasLibres.setBackground(new Color(252, 252, 252));
        tablaAreasLibres.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "No", "Localidad", "Tamaño", "Estado"
            }
        ));
        tablaAreasLibres.setGridColor(new Color(237, 237, 237));
        jScrollPane1.setViewportView(tablaAreasLibres);

        jPanel6.add(jScrollPane1, BorderLayout.CENTER);

        jPanel3.add(jPanel6);
        jPanel3.add(filler6);

        jPanel7.setBackground(new Color(252, 252, 252));
        jPanel7.setBorder(BorderFactory.createTitledBorder("Tabla de particiones"));
        jPanel7.setLayout(new BorderLayout());

        jScrollPane2.setBackground(new Color(252, 252, 252));

        tablaParticiones.setBackground(new Color(252, 252, 252));
        tablaParticiones.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "No", "Localidad", "Tamaño", "Estado", "Proceso"
            }
        ));
        tablaParticiones.setGridColor(new Color(237, 237, 237));
        jScrollPane2.setViewportView(tablaParticiones);

        jPanel7.add(jScrollPane2, BorderLayout.CENTER);

        jPanel3.add(jPanel7);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel8.add(jPanel3, gridBagConstraints);

        panelGrafico.setBackground(new Color(252, 252, 252));
        panelGrafico.setBorder(BorderFactory.createTitledBorder("Representación gráfica"));
        panelGrafico.setMaximumSize(new Dimension(400, 114));
        panelGrafico.setMinimumSize(new Dimension(400, 114));
        panelGrafico.setPreferredSize(new Dimension(400, 114));
        panelGrafico.setLayout(new BoxLayout(panelGrafico, BoxLayout.Y_AXIS));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel8.add(panelGrafico, gridBagConstraints);

        getContentPane().add(jPanel8, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public JPanel getPanelGrafico()
    {
        return panelGrafico;
    }

    public JButton getBtnSigMomento()
    {
        return btnSigMomento;
    }

    public JButton getBtnAdmProcesos()
    {
        return btnAdmProcesos;
    }

    public JTable getTablaAreasLibres()
    {
        return tablaAreasLibres;
    }

    public JTable getTablaParticiones()
    {
        return tablaParticiones;
    }

    public JTable getTablaProcesos()
    {
        return tablaProcesos;
    }

    public JLabel getEstado()
    {
        return estado;
    }

    public JFrame getFrame()
    {
        return this;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAdmProcesos;
    private JButton btnSigMomento;
    private JLabel estado;
    private Box.Filler filler1;
    private Box.Filler filler3;
    private Box.Filler filler4;
    private Box.Filler filler5;
    private Box.Filler filler6;
    private Box.Filler filler7;
    private Box.Filler filler8;
    private Box.Filler filler9;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel12;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JPanel panelGrafico;
    private JTable tablaAreasLibres;
    private JTable tablaParticiones;
    private JTable tablaProcesos;
    // End of variables declaration//GEN-END:variables
}
