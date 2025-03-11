/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.CarreraController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public class TablaCursadas {

    private JTable tb;
    private JButton btnCrear;
    private JButton btnInscribir;
    private Alumno alumno;
    private Carrera carrera;

    public TablaCursadas(Carrera carre, Alumno estudiante) {
        carrera = carre;
        alumno = estudiante;
        initComponents();
    }

    private void initComponents() {

        if (carrera == null || "No Inscripto".equals(carrera.getNombre())) {
            JOptionPane.showMessageDialog(null, "Debe inscribirse en una carrera primero.");
            return;
        }
        List<Cursada> cursadas = alumno.getCursadas();

        if (cursadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay cursadas, anotese a una materia.");
            return;
        }

        String[] columnas = {"Materia", "promocionable", "notaAprobarPromocion", "notaAprobarCursada", "notaAprobarFinal", "notaParcial", "notaFinal", "Acción"};
        Object[][] datos = new Object[cursadas.size()][8];

        for (int i = 0; i < cursadas.size(); i++) {
            Cursada cursada = cursadas.get(i);
            datos[i][0] = cursada.getMateria().getNombre();
            datos[i][1] = cursada.getMateria().getPromocionable();
            datos[i][2] = cursada.getMateria().getNotaPromocion();
            datos[i][3] = cursada.getMateria().notaAprobarCursada;
            datos[i][4] = cursada.getMateria().notaAprobarFinal;
            datos[i][5] = cursada.getParcial();
            datos[i][6] = cursada.getNotaFinal();
            datos[i][7] = "Guardar";
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6 || column == 7;
            }
        };

        JTable tablaCursadas = new JTable(modelo);
        tablaCursadas.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaCursadas.getColumn("Acción").setCellEditor(new ButtonEditor());

        // Crear el JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaCursadas);

        // Crear un nuevo JDialog para mostrar las materias
        JDialog dialogCursadas = new JDialog((Frame) null, "Cursadas Actuales", true);
        dialogCursadas.setSize(500, 400);
        dialogCursadas.setLayout(new BorderLayout());

        // Agregar el JScrollPane al diálogo
        dialogCursadas.add(scrollPane, BorderLayout.CENTER);

        // Mostrar el diálogo
        dialogCursadas.setLocationRelativeTo(null);
        dialogCursadas.setVisible(true);

        //JOptionPane.showMessageDialog(null, new JScrollPane(tablaCursadas), "Cursadas Actuales", JOptionPane.INFORMATION_MESSAGE);
    }

    // ====================== RENDERIZADOR DEL BOTÓN ======================
    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Guardar");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // ====================== EDITOR DEL BOTÓN ======================
    private class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private Cursada currentCursada;
        private int notaCursada;
        private int notaFinal;

        public ButtonEditor() {
            super(new JTextField());
            button = new JButton("Guardar");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guardarCursada(currentCursada, notaCursada, notaFinal);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            String nombre = (String) table.getValueAt(row, 0); // Obtener el nombre de la materia
            notaCursada = Integer.parseInt(table.getValueAt(row, 5).toString());
            notaFinal = Integer.parseInt(table.getValueAt(row, 6).toString());
            for (Cursada cursada : alumno.getCursadas()) {// Buscar la cursada
                if (cursada.getMateria().getNombre().equals(nombre)) {
                    currentCursada = cursada;
                }
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Inscribir";
        }
    }

    private void guardarCursada(Cursada cursada, int notaCursada, int notaFinal) {
        cursada.setCursada(notaCursada, notaFinal);
        JOptionPane.showMessageDialog(null, "Notas actualizadas para " + cursada.getMateria().getNombre());
    }

}
