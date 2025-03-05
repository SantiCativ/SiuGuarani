/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.AlumnoController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controlador.AlumnoController;
import controlador.CarreraController;
import controlador.MateriaController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import modelo.Alumno;

/**
 *
 * @author cativ
 */
public class TablaAlumnos extends JPanel {

    private JTable tb;
    private AlumnoController alumnoController;
    private MateriaController materiaController;
    private CarreraController carreraController;
    private JButton btnCrear;
    private JButton btnInscribir;

    public TablaAlumnos(MateriaController materia, CarreraController carrera) {
        alumnoController = new AlumnoController();
        materiaController = materia;
        carreraController = carrera;

        initComponents();
        mostrarAlumnos();
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
        btnCrear.addActionListener(e -> abrirFormularioAlumno());
    }

    private void mostrarAlumnos() {
        // Columnas de la tabla
        String[] columnas = {"Legajo", "Apellido", "Nombre", "Acción"};

        // Obtener la lista de alumnos
        ArrayList<modelo.Alumno> listaAlumnos = alumnoController.obtenerAlumnos();
        Object[][] datos = new Object[listaAlumnos.size()][4];

        for (int i = 0; i < listaAlumnos.size(); i++) {
            modelo.Alumno alumno = listaAlumnos.get(i);
            datos[i][0] = alumno.getLegajo();
            datos[i][1] = alumno.getApellido();
            datos[i][2] = alumno.getNombre();
            datos[i][3] = "Ver";
        }
        // Crear el modelo de la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo la última columna (botón) es editable
            }
        };

        tb.setModel(modeloTabla);
        tb.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tb.getColumn("Acción").setCellEditor(new ButtonEditor());
    }

    private void abrirFormularioAlumno() {
        // Crear el JDialog
        JDialog modalDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nuevo Alumno", true);

        // Panel para contener el formulario
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Campos del formulario
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(10);

        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField(10);

        // Botón para enviar el formulario
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Aquí puedes capturar los datos del formulario
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                // Guardar datos usando el controlador
                alumnoController.agregarAlumno(nombre, apellido);
                mostrarAlumnos();
                // Cerrar el modal
                modalDialog.dispose();
            }

        });

        // Añadir los componentes al panel
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblApellido);
        panel.add(txtApellido);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnGuardar);

        // Configurar el JDialog
        modalDialog.add(panel);
        modalDialog.pack();
        modalDialog.setLocationRelativeTo(this); // Centrar el modal respecto a la ventana principal
        modalDialog.setVisible(true);
    }

    // ====================== RENDERIZADOR DEL BOTÓN ======================
    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Ver");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // ====================== EDITOR DEL BOTÓN ======================
    private class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private Alumno currentAlumno;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton("Ver");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentAlumno != null) {
                        new DetailAlumno(materiaController, carreraController, currentAlumno);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            int legajo = (int) table.getValueAt(row, 0); // Obtener el legajo del alumno
            currentAlumno = alumnoController.obtenerAlumnoPorLegajo(legajo); // Buscar el alumno en el controlador
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Ver";
        }
    }

}
