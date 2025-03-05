// En el archivo principal donde tienes los botones
package vista;

import controlador.CarreraController;
import controlador.InscripcionController;

import controlador.MateriaController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.Carrera;
import modelo.Materia;
import modelo.PlanA;
import modelo.PlanB;
import modelo.PlanC;
import modelo.PlanD;
import modelo.PlanE;
import modelo.PlanEstudio;
import vista.TablaAlumnos;
import vista.TablaCarrera;
import vista.TablaMateria;
import vista.TablaPlanEstudio;

public class Main extends javax.swing.JFrame {

    private JPanel panelContenedor;  // Panel que contendrá los diferentes paneles de tablas
    private JButton btnAlumnos, btnCarreras, btnMaterias, btnPlanes;
    private PlanEstudio[] opcionesPlanes;
    private MateriaController materiaController;
    private CarreraController carreraController;
    private InscripcionController inscripcionController;

    public Main() {
        initComponents();
    }

    private void inicializarData() {
        PlanEstudio pde = new PlanB();
        Carrera analistaSistemas = new Carrera("Analista en sistemas", 2, pde);
        Materia expresionDeAlgoritmos = new Materia("Expresión de Problemas y Algoritmos", "43243df", 1, true, analistaSistemas);
        Materia programacion1 = new Materia("Programacion 1", "sdd23", 2, true, analistaSistemas);
        programacion1.agregarCorrelativa(expresionDeAlgoritmos);
        List<Materia> materiasObligatorias = new ArrayList();
        materiasObligatorias.add(expresionDeAlgoritmos);
        materiasObligatorias.add(programacion1);
        analistaSistemas.setMateriasObligatorias(materiasObligatorias);

        carreraController.agregarCarrera(analistaSistemas);
        materiaController.agregarMateria(programacion1);
        materiaController.agregarMateria(expresionDeAlgoritmos);
    }

    private void initPlanes() {
        PlanEstudio pdeA = new PlanA();
        PlanEstudio pdeB = new PlanB();
        PlanEstudio pdeC = new PlanC();
        PlanEstudio pdeD = new PlanD();
        PlanEstudio pdeE = new PlanE();
        this.opcionesPlanes = new PlanEstudio[]{pdeA, pdeB, pdeC, pdeD, pdeE};
    }

    private void initComponents() {
        // Inicializamos el panel de contenido
        panelContenedor = new JPanel(new CardLayout());

        // inicio los controladores
        materiaController = new MateriaController();
        carreraController = new CarreraController();

        // Creamos los botones
        btnAlumnos = new JButton("Alumnos");
        btnCarreras = new JButton("Carreras");
        btnMaterias = new JButton("Materias");
        btnPlanes = new JButton("Planes");

        //data estatica de prueba
        inicializarData();

        // Crear las instancias de cada uno de los paneles de tablas
        TablaAlumnos tablaAlumnos = new TablaAlumnos(materiaController, carreraController);
        initPlanes();
        TablaCarrera tablaCarreras = new TablaCarrera(this.opcionesPlanes, this.materiaController, this.carreraController);

        TablaMateria tablaMaterias = new TablaMateria(materiaController, carreraController);

        TablaPlanEstudio tablaPlanes = new TablaPlanEstudio(this.opcionesPlanes);

        // Añadir los paneles al contenedor con CardLayout
        panelContenedor.add(tablaAlumnos, "Alumnos");

        panelContenedor.add(tablaCarreras, "Carrera");

        panelContenedor.add(tablaMaterias, "Materias");

        panelContenedor.add(tablaPlanes, "Planes");

        // Configuración del layout y botones
        btnAlumnos.addActionListener(e -> mostrarPanel("Alumnos"));

        btnCarreras.addActionListener(e -> mostrarPanel("Carrera"));

        btnMaterias.addActionListener(e -> mostrarPanel("Materias"));

        btnPlanes.addActionListener(e -> mostrarPanel("Planes"));

        // Añadir los botones y panelContenedor al layout principal
        this.setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAlumnos);
        panelBotones.add(btnCarreras);
        panelBotones.add(btnMaterias);
        panelBotones.add(btnPlanes);
        this.add(panelBotones, BorderLayout.NORTH);
        this.add(panelContenedor, BorderLayout.CENTER);

        // Configuración del JFrame
        this.setTitle("SIU GUARANI");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void mostrarPanel(String nombrePanel) {
        CardLayout cardLayout = (CardLayout) panelContenedor.getLayout();
        cardLayout.show(panelContenedor, nombrePanel);  // Cambia entre los paneles
    }

  /*ublic static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
*/
}
