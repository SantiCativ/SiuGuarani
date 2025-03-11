/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.CarreraController;
import controlador.MateriaController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import modelo.Alumno;
import modelo.Carrera;
import modelo.Cursada;
import modelo.Materia;

/**
 *
 * @author cativ
 */
public class DetailAlumno {

    private CarreraController carreraController;
    private MateriaController materiaController;
    private Alumno alumno;
    private JTable tb;

    public DetailAlumno(MateriaController materia, CarreraController carrera, Alumno estudiante) {
        carreraController = carrera;
        materiaController = materia;
        alumno = estudiante;
        initComponents();
    }

    private void initComponents() {

        // Obtener las carreras del alumno
        List<Carrera> carrerasRealizadas = alumno.getCarreras();

        // Crear el modal de información
        JDialog dialog = new JDialog((Frame) null, "Detalles del Alumno", true);
        dialog.setSize(400, 350);
        dialog.setLayout(new BorderLayout());

        // Panel superior con información del alumno
        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.add(new JLabel("Alumno: " + alumno.getNombre() + " " + alumno.getApellido(), SwingConstants.CENTER));

        // ComboBox para seleccionar carrera
        JComboBox<String> comboCarreras = new JComboBox<>();
        for (Carrera carrera : carrerasRealizadas) {
            comboCarreras.addItem(carrera.getNombre());
        }

        // Si no está inscripto en ninguna carrera, agregar mensaje
        if (carrerasRealizadas.isEmpty()) {
            comboCarreras.addItem("No Inscripto");
            comboCarreras.setEnabled(false);
        }

        panelInfo.add(comboCarreras);

        // Panel con botones para las diferentes acciones
        JPanel panelOpciones = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnInscribirCarrera = new JButton("🏫 Inscribir Carrera");
        JButton btnInscribirMaterias = new JButton("📌 Inscribir a Materias");
        JButton btnVerCursadas = new JButton("📖 Ver Cursadas");
        JButton btnVerGraduacion = new JButton("🎓 Verificar Graduación");

        panelOpciones.add(btnInscribirCarrera);
        panelOpciones.add(btnInscribirMaterias);
        panelOpciones.add(btnVerCursadas);
        panelOpciones.add(btnVerGraduacion);

        // Acción para inscribir carrera
        btnInscribirCarrera.addActionListener(e -> inscribirAlumnoEnCarrera(alumno, comboCarreras));

        // Acción para inscribir a materias
        btnInscribirMaterias.addActionListener(e -> {
            String carreraSeleccionada = (String) comboCarreras.getSelectedItem();
            Carrera carrera = carreraController.obtenerCarreraPorNombre(carreraSeleccionada);
            mostrarMateriasDisponibles(alumno, carrera);
        });

        // Acción para ver cursadas
        btnVerCursadas.addActionListener(e -> {
            String carreraSeleccionada = (String) comboCarreras.getSelectedItem();
            Carrera carrera = carreraController.obtenerCarreraPorNombre(carreraSeleccionada);
            TablaCursadas tablaCursada = new TablaCursadas(carrera, alumno);
        });

        btnVerGraduacion.addActionListener(e -> {
            String carreraSeleccionada = (String) comboCarreras.getSelectedItem();
            Carrera carrera = carreraController.obtenerCarreraPorNombre(carreraSeleccionada);
            if (alumno.recibido(carrera)) {
                JOptionPane.showMessageDialog(null, "¡ALUMNO RECIBIDO!");
            } else {
                JOptionPane.showMessageDialog(null, "¡ALUMNO NO RECIBIDO!");
            }

        });

        // Agregar los paneles al diálogo
        dialog.add(panelInfo, BorderLayout.NORTH);
        dialog.add(panelOpciones, BorderLayout.CENTER);

        // Mostrar el diálogo
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    private void inscribirAlumnoEnCarrera(Alumno alumno, JComboBox<String> comboCarreras) {
        List<Carrera> carrerasDisponibles = carreraController.obtenerCarreras();
        List<Carrera> carrerasAlumno = alumno.getCarreras();

        if (carrerasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay carreras disponibles para inscripción.");
            return;
        }

        String[] opciones = new String[carrerasDisponibles.size()];
        for (int i = 0; i < carrerasDisponibles.size(); i++) {
            opciones[i] = carrerasDisponibles.get(i).getNombre();
        }

        // Mostrar un cuadro de selección
        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione una carrera para inscribirse:",
                "Inscripción", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        // Si el usuario selecciona una opción
        if (seleccion != null) {
            for (Carrera carrera : carrerasDisponibles) {
                if (carrera.getNombre().equals(seleccion)) {

                    // 🔹 **Verificar si ya está inscrito**
                    if (carrerasAlumno.contains(carrera)) {
                        JOptionPane.showMessageDialog(null, "Ya está inscrito en esta carrera.");
                        return;
                    }

                    // 🔹 **Inscribir y actualizar lista**
                    alumno.setCarrera(carrera);
                    JOptionPane.showMessageDialog(null, "Inscripción exitosa en " + carrera.getNombre());

                    // 🔄 **Actualizar el comboCarreras**
                    comboCarreras.addItem(carrera.getNombre());
                    comboCarreras.setEnabled(true);

                    break;
                }
            }
        }
    }

    private void mostrarMateriasDisponibles(Alumno alumno, Carrera carrera) {

        if (carrera == null || "No Inscripto".equals(carrera.getNombre())) {
            JOptionPane.showMessageDialog(null, "Debe inscribirse en una carrera primero.");
            return;
        }

        List<Materia> materiasDisponibles = carrera.obtenerMateriasDisponibles(alumno);
        if (materiasDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay materias disponibles.");
            return;
        }

        String[] columnas = {"Código", "Nombre", "Cuatrimestre", "Acción"};
        Object[][] datos = new Object[materiasDisponibles.size()][4];

        for (int i = 0; i < materiasDisponibles.size(); i++) {
            Materia materia = materiasDisponibles.get(i);
            datos[i][0] = materia.getCodigo();
            datos[i][1] = materia.getNombre();
            datos[i][2] = materia.getNroCuatrimestre();
            datos[i][3] = "Inscribir";
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        JTable tablaMaterias = new JTable(modelo);
        tablaMaterias.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaMaterias.getColumn("Acción").setCellEditor(new ButtonEditor());

        // Crear el JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaMaterias);

        // Crear un nuevo JDialog para mostrar las materias
        JDialog dialogMaterias = new JDialog((Frame) null, "Materias Disponibles", true);
        dialogMaterias.setSize(500, 400);
        dialogMaterias.setLayout(new BorderLayout());

        // Agregar el JScrollPane al diálogo
        dialogMaterias.add(scrollPane, BorderLayout.CENTER);

        // Mostrar el diálogo
        dialogMaterias.setLocationRelativeTo(null);
        dialogMaterias.setVisible(true);
    }

    // ====================== RENDERIZADOR DEL BOTÓN ======================
    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Inscribir");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // ====================== EDITOR DEL BOTÓN ======================
    private class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private Materia currentMateria;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton("Inscribir");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inscribirMateria(currentMateria);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            String codigo = (String) table.getValueAt(row, 0); // Obtener el cdoigo de la materia
            currentMateria = materiaController.obtenerMateriaCodigo(codigo); // Buscar la materia en el controlador
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Inscribir";
        }
    }

    private void inscribirMateria(Materia materia) {

        if (materia != null) {
            for (Cursada cursada : alumno.getCursadas()) {
                if (cursada.getMateria().getCodigo().equals(materia.getCodigo())) {
                    JOptionPane.showMessageDialog(null, "Ya estás inscrito.");
                    return;
                }
            }

            Cursada nuevaCursada = new Cursada(materia, alumno);
            alumno.setCursadas(nuevaCursada);
            JOptionPane.showMessageDialog(null, "Inscripción exitosa en " + materia.getNombre());
        }
    }
}
