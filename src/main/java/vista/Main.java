// En el archivo principal donde tienes los botones
package vista;

import controlador.CarreraController;
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

    public Main() {
        initComponents();
    }

    private void inicializarData() {

        materiaController.agregarMateria("Álgebra", "MA045", 1, new ArrayList<Materia>(), 7);
        materiaController.agregarMateria("Expresión de Problemas y Algoritmos", "IF002", 1, new ArrayList<Materia>(), 7);
        materiaController.agregarMateria("Elementos de Informática", "IF001", 1, new ArrayList<Materia>(), 7);
        materiaController.agregarMateria("Acreditación de Idioma Inglés", "0412", 1, new ArrayList<Materia>());

        List<Materia> correlativasProg1 = new ArrayList();
        correlativasProg1.add(materiaController.obtenerMateriaCodigo("IF002"));
        materiaController.agregarMateria("Algorítmica y Programación I", "IF003", 2, correlativasProg1, 7);
        materiaController.agregarMateria("Elementos de Lógica y Matemática Discreta", "MA008", 2, new ArrayList<Materia>(), 7);
        materiaController.agregarMateria("Análisis Matemático", "MA046", 2, new ArrayList<Materia>(), 7);
        List<Materia> correlativasADC = new ArrayList();
        correlativasADC.add(materiaController.obtenerMateriaCodigo("IF001"));
        correlativasADC.add(materiaController.obtenerMateriaCodigo("0412"));
        materiaController.agregarMateria("Arquitectura de computadoras", "codigoadc", 3, correlativasADC);
        List<Materia> correlativasSYO = new ArrayList();
        correlativasSYO.add(materiaController.obtenerMateriaCodigo("0412"));
        materiaController.agregarMateria("Sistemas y Organizaciones", "IF004", 3, correlativasSYO, 7);
        List<Materia> correlativasprog2 = new ArrayList();
        correlativasprog2.add(materiaController.obtenerMateriaCodigo("IF003"));
        correlativasprog2.add(materiaController.obtenerMateriaCodigo("MA008"));
        correlativasprog2.add(materiaController.obtenerMateriaCodigo("0412"));
        materiaController.agregarMateria("Algorítmica y Programación II", "IF006", 3, correlativasprog2, 7);
        List<Materia> correlativasesta = new ArrayList();
        correlativasesta.add(materiaController.obtenerMateriaCodigo("MA045"));
        correlativasesta.add(materiaController.obtenerMateriaCodigo("MA046"));
        correlativasesta.add(materiaController.obtenerMateriaCodigo("0412"));
        materiaController.agregarMateria("Estadística", "MA006", 3, correlativasesta);
        List<Materia> correlativasBD1 = new ArrayList();
        correlativasBD1.add(materiaController.obtenerMateriaCodigo("IF006"));
        materiaController.agregarMateria("Bases de Datos I ", "IF007", 4, correlativasBD1, 8);
        List<Materia> correlativaspoo = new ArrayList();
        correlativaspoo.add(materiaController.obtenerMateriaCodigo("IF006"));
        materiaController.agregarMateria("Programación y Diseño Orientado a Objetos", "IF030", 4, correlativaspoo);
        List<Materia> correlativasingenieria1 = new ArrayList();
        correlativasingenieria1.add(materiaController.obtenerMateriaCodigo("IF003"));
        correlativasingenieria1.add(materiaController.obtenerMateriaCodigo("IF004"));
        materiaController.agregarMateria("Ingeniería de Software I", "IF031", 4, correlativasingenieria1, 8);
        List<Materia> correlativaslab = new ArrayList();
        correlativaslab.add(materiaController.obtenerMateriaCodigo("IF006"));
        materiaController.agregarMateria("Laboratorio de Programación y Lenguajes", "IF009", 5, correlativaslab);
        List<Materia> correlativasingenieria2 = new ArrayList();
        correlativasingenieria2.add(materiaController.obtenerMateriaCodigo("IF031"));
        correlativasingenieria2.add(materiaController.obtenerMateriaCodigo("MA006"));
        materiaController.agregarMateria("Ingeniería de Software II", "IF009", 5, correlativasingenieria2, 8);

        List<Materia> correlativasconcurrencia = new ArrayList();
        correlativasconcurrencia.add(materiaController.obtenerMateriaCodigo("IF031"));
        correlativasconcurrencia.add(materiaController.obtenerMateriaCodigo("MA006"));
        materiaController.agregarMateria("Introducción a la Concurrencia", "IF038", 5, correlativasconcurrencia, 7);

        List<Materia> correlativaslabsoftware = new ArrayList();
        correlativaslabsoftware.add(materiaController.obtenerMateriaCodigo("IF031"));
        correlativaslabsoftware.add(materiaController.obtenerMateriaCodigo("IF030"));
        correlativaslabsoftware.add(materiaController.obtenerMateriaCodigo("IF007"));
        materiaController.agregarMateria("Laboratorio de Software", "IF055", 6, correlativaslabsoftware);

        materiaController.agregarMateria("Curso(s) de Estrategias Comunicacionales que forma(n) parte del Seminario de Aspectos Legales y Profesionales I", "IF056", 6, new ArrayList<Materia>());

        List<Materia> correlativasistemasoperativos = new ArrayList();
        correlativasistemasoperativos.add(materiaController.obtenerMateriaCodigo("IF038"));
        materiaController.agregarMateria("Sistemas Operativos", "0411", 6, correlativasistemasoperativos);

        materiaController.agregarMateria("Robotica", "rbt", 6, new ArrayList<Materia>());
        materiaController.agregarMateria("Ciberseguridad", "crb", 6, new ArrayList<Materia>());
        materiaController.agregarMateria("Hacking Etico", "hcke", 6, new ArrayList<Materia>());

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
}
