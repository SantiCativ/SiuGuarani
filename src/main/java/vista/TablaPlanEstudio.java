/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.PlanEstudio;

/**
 *
 * @author cativ
 */
public class TablaPlanEstudio extends JPanel {

    private JTable tb;
    private PlanEstudio[] planes;

    public TablaPlanEstudio(PlanEstudio[] opciones) {
        planes = opciones;
        initComponents();
        mostrarPlanes();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        // Crear tabla y agregar al panel
        tb = new JTable();
        JScrollPane scrollPane = new JScrollPane(tb);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void mostrarPlanes() {
        String[] columnas = {"Nombre"};
        List<Object[]> listaDatos = new ArrayList<>();

        for (PlanEstudio plan : this.planes) {
            listaDatos.add(new Object[]{plan.toString()});
        }
        // Convertir la lista a un array bidimensional
        Object[][] datos = listaDatos.toArray(new Object[0][]);
        tb.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }
}
