/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.CarreraController;
import controlador.MateriaController;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import modelo.PlanEstudio;

/**
 *
 * @author cativ
 */
public class TablaCarrera extends JPanel {

    private JTable tb;
    private CarreraController carreraController;
    private MateriaController materiaController;
    private JButton btnCrear;
    private PlanEstudio[] opcionesPlanes;

    public TablaCarrera(PlanEstudio[] planes, MateriaController materiaControllerr, CarreraController carreraControllerr) {
        materiaController = materiaControllerr;
        carreraController = carreraControllerr;
        opcionesPlanes = planes;
        initComponents();
        mostrarCarreras();
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
        btnCrear.addActionListener(e -> abrirFormularioCarrera());
    }

    private void mostrarCarreras() {
        // Columnas y datos para Carreras
        String[] columnas = {"Nombre", "Materias Opcionales", "Plan"};

        ArrayList<Carrera> listaCarreras = this.carreraController.obtenerCarreras();
        Object[][] datos = new Object[listaCarreras.size()][3];

        for (int i = 0; i < listaCarreras.size(); i++) {
            Carrera carrera = listaCarreras.get(i);
            datos[i][0] = carrera.getNombre();
            datos[i][1] = carrera.getNumeroMateriasOpcionales();
            datos[i][2] = carrera.getPlanDeEstudio();
        }
        tb.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void abrirFormularioCarrera() {
        // Crear el JDialog
        JDialog modalDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nuevo Carrera", true);
        modalDialog.setLayout(new BorderLayout());

        // Panel para contener el formulario
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Campos del formulario
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(10);

        JLabel lblMateriasOpcionales = new JLabel("Materias opcionales:");
        JTextField txtMateriasOpcionales = new JTextField(10);

        JLabel lblPlanes = new JLabel("Plan de estudio:");
        JComboBox<PlanEstudio> comboBoxPlanes = new JComboBox<>();

        // Llenar el JComboBox con los planes de estudio disponibles
        for (PlanEstudio plan : opcionesPlanes) {
            comboBoxPlanes.addItem(plan);
        }

        // Lista para seleccionar materias obligatorias
        JLabel lblMateriasObligatorias = new JLabel("Materias Obligatorias:");
        JList<Materia> listMateriasObligatorias = new JList<>();
        listMateriasObligatorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollObligatorias = new JScrollPane(listMateriasObligatorias);

        // Lista para seleccionar materias opcionales
        JLabel lblMateriasOpcionalesLista = new JLabel("Materias Opcionales:");
        JList<Materia> listMateriasOpcionales = new JList<>();
        listMateriasOpcionales.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollOpcionales = new JScrollPane(listMateriasOpcionales);

        // Llenar las listas con las materias disponibles
        DefaultListModel<Materia> modeloObligatorias = new DefaultListModel<>();
        DefaultListModel<Materia> modeloOpcionales = new DefaultListModel<>();

        for (Materia materia : materiaController.obtenerMaterias()) {
            modeloObligatorias.addElement(materia);
            modeloOpcionales.addElement(materia);
        }

        listMateriasObligatorias.setModel(modeloObligatorias);
        listMateriasOpcionales.setModel(modeloOpcionales);

        // Botón para enviar el formulario
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String nombre = txtNombre.getText();
                    int numMateriasOpcionales = Integer.parseInt(txtMateriasOpcionales.getText());
                    PlanEstudio planSeleccionado = (PlanEstudio) comboBoxPlanes.getSelectedItem();

                    // Obtener materias seleccionadas
                    List<Materia> materiasObligatorias = listMateriasObligatorias.getSelectedValuesList();
                    List<Materia> materiasOpcionales = listMateriasOpcionales.getSelectedValuesList();

                    // Crear la carrera y guardarla
                    Carrera nuevaCarrera = new Carrera(nombre, numMateriasOpcionales, planSeleccionado);
                    nuevaCarrera.setMateriasObligatorias(materiasObligatorias);
                    nuevaCarrera.setMateriasOpcionales(materiasOpcionales);

                    carreraController.agregarCarrera(nuevaCarrera);
                    mostrarCarreras();

                    // Cerrar el modal
                    modalDialog.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(modalDialog, "Error: El número de materias opcionales debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir los componentes al panel
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblMateriasOpcionales);
        panel.add(txtMateriasOpcionales);
        panel.add(lblPlanes);
        panel.add(comboBoxPlanes);

        // Panel para las listas de materias
        JPanel panelListas = new JPanel(new GridLayout(2, 2, 10, 10));
        panelListas.add(lblMateriasObligatorias);
        panelListas.add(scrollObligatorias);
        panelListas.add(lblMateriasOpcionalesLista);
        panelListas.add(scrollOpcionales);

        // Botón en un panel separado
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);

        // Agregar paneles al modal
        modalDialog.add(panel, BorderLayout.NORTH);
        modalDialog.add(panelListas, BorderLayout.CENTER);
        modalDialog.add(panelBoton, BorderLayout.SOUTH);

        // Configurar y mostrar el JDialog
        modalDialog.pack();
        modalDialog.setLocationRelativeTo(this);
        modalDialog.setVisible(true);
    }

}
