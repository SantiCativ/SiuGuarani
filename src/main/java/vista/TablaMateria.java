/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.CarreraController;
import controlador.MateriaController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import modelo.Carrera;
import modelo.Materia;

/**
 *
 * @author cativ
 */
public class TablaMateria extends JPanel {

    private JTable tb;
    private JButton btnCrear;
    private MateriaController materiaController;
    private CarreraController carreraController;

    public TablaMateria(MateriaController materiaControllerr, CarreraController carreraControllerr) {
        materiaController = materiaControllerr;
        carreraController=carreraControllerr;
        initComponents();
        mostrarMaterias();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        // Crear tabla y agregar al panel
        tb = new JTable();
        JScrollPane scrollPane = new JScrollPane(tb);
        this.add(scrollPane, BorderLayout.CENTER);

        // Botón "Crear"
        btnCrear = new JButton("Crear");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCrear);
        this.add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón "Crear"
        btnCrear.addActionListener(e -> abrirFormularioMateria());
    }

    private void mostrarMaterias() {

        String[] columnas = {"Nombre", "codigo", "nro cuatrimestre", "obligatoria", "promocionable", "carrera"};

        ArrayList<Materia> listaMaterias = materiaController.obtenerMaterias();
        Object[][] datos = new Object[listaMaterias.size()][6];

        for (int i = 0; i < listaMaterias.size(); i++) {
            Materia materia = listaMaterias.get(i);
            datos[i][0] = materia.getNombre();
            datos[i][1] = materia.getCodigo();
            datos[i][2] = materia.getNroCuatrimestre();
            datos[i][3] = materia.getObligatoria();
            datos[i][4] = materia.getPromocionable();
            datos[i][5] = materia.getCarrera();
        }
        
        tb.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void abrirFormularioMateria() {
        // Crear el JDialog
        JDialog modalDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nueva Materia", true);
        modalDialog.setLayout(new BorderLayout());

        // Panel con GridBagLayout para mejor distribución
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Campos del formulario
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        JTextField txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        JTextField txtCodigo = new JTextField(15);
        panel.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Nro cuatrimestre:"), gbc);
        gbc.gridx = 1;
        JTextField txtCuatrimestre = new JTextField(15);
        panel.add(txtCuatrimestre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Obligatoria:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> cbObligatoria = new JComboBox<>(new String[]{"Sí", "No"});
        panel.add(cbObligatoria, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JCheckBox chkPromocionable = new JCheckBox("¿Es promocionable?");
        chkPromocionable.setSelected(false);
        panel.add(chkPromocionable, gbc);

        gbc.gridx = 1;
        JTextField txtNotaPromocion = new JTextField(15);
        txtNotaPromocion.setEnabled(false); // Se inicia deshabilitado
        panel.add(txtNotaPromocion, gbc);

        // Acción cuando cambia el estado del checkbox
        chkPromocionable.addActionListener(e -> txtNotaPromocion.setEnabled(chkPromocionable.isSelected()));

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Carrera:"), gbc);
        gbc.gridx = 1;
        JComboBox<Carrera> cbCarrera = new JComboBox<>();
        for (Carrera carrera : carreraController.obtenerCarreras()) {
            cbCarrera.addItem(carrera);
        }
        panel.add(cbCarrera, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Correlativas:"), gbc);
        gbc.gridx = 1;
        JList<Materia> listCorrelativas = new JList<>();
        listCorrelativas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollCorrelativas = new JScrollPane(listCorrelativas);
        scrollCorrelativas.setPreferredSize(new Dimension(200, 150));

        DefaultListModel<Materia> modeloLista = new DefaultListModel<>();
        for (Materia materia : materiaController.obtenerMaterias()) {
            modeloLista.addElement(materia);
        }
        listCorrelativas.setModel(modeloLista);
        panel.add(scrollCorrelativas, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnGuardar = new JButton("Guardar");
        panel.add(btnGuardar, gbc);

        btnGuardar.addActionListener(evt -> {
            try {
                String nombre = txtNombre.getText();
                String codigo = txtCodigo.getText();
                int nroCuatrimestre = Integer.parseInt(txtCuatrimestre.getText());
                boolean obligatoria = cbObligatoria.getSelectedItem().equals("Sí");
                boolean esPromocionable = chkPromocionable.isSelected();
                int notaPromocion = esPromocionable ? Integer.parseInt(txtNotaPromocion.getText()) : -1;
                Carrera carreraSeleccionada = (Carrera) cbCarrera.getSelectedItem();

                List<Materia> materiasCorrelativas = listCorrelativas.getSelectedValuesList();

                if (esPromocionable) {
                    materiaController.agregarMateria(nombre, codigo, nroCuatrimestre, obligatoria, carreraSeleccionada, materiasCorrelativas, notaPromocion);
                } else {
                    materiaController.agregarMateria(nombre, codigo, nroCuatrimestre, obligatoria, carreraSeleccionada, materiasCorrelativas);
                }

                mostrarMaterias();
                modalDialog.dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(modalDialog, "Error: El número de cuatrimestre debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        modalDialog.add(panel, BorderLayout.CENTER);
        modalDialog.pack();
        modalDialog.setLocationRelativeTo(this);
        modalDialog.setVisible(true);
    }

}
