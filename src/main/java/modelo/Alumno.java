/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import modelo.Cursada;

/**
 *
 * @author cativ
 */
public class Alumno {

    private static int contadorLegajo = 1;
    private final int legajo;
    private String nombre;
    private String apellido;
    private List<Cursada> cursadas;
    private Set<Cursada> cursadasAprobadas;
    private Set<Cursada> materiasAprobadas;
    private List<Carrera> carreras;

    // Constructor
    public Alumno(String nombre, String apellido) {
        this.legajo = contadorLegajo;
        contadorLegajo++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carreras = new ArrayList<>();
        this.cursadas = new ArrayList<>();
        this.cursadasAprobadas = new HashSet<>();
        this.materiasAprobadas = new HashSet<>();
    }

    // Getters y Setters
    public int getLegajo() {
        return this.legajo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public List<Carrera> getCarreras() {
        return this.carreras;
    }

    public List<Cursada> getCursadas() {
        return this.cursadas;
    }

    public Set<Cursada> getCursadasAprobadas() {
        return this.cursadasAprobadas;
    }

    public Set<Cursada> getMateriasAprobadas() {
        return this.materiasAprobadas;
    }

    public int getMateriasOpcionalesAprobadas(Carrera carrera) {
        if (carrera == null) {
            return 0; // Si la carrera es nula, no hay opcionales aprobadas
        }

        Set<Materia> materiasAprobadas = convertirCursadasAMaterias(getMateriasAprobadas());
        if (materiasAprobadas == null || materiasAprobadas.isEmpty()) {
            return 0; // Si el alumno no tiene materias aprobadas, retorna 0
        }

        List<Materia> materiasOpcionales = carrera.getMateriasOpcionales();
        if (materiasOpcionales == null || materiasOpcionales.isEmpty()) {
            return 0; // Si la carrera no tiene materias opcionales, retorna 0
        }

        /* no funciono asi:
            // Crea una copia para no modificar el original y retiene solo las opcionales aprobadas
            Set<Materia> opcionalesAprobadas = new HashSet<>(materiasAprobadas);
            opcionalesAprobadas.retainAll(materiasOpcionales);
         */
        Set<Materia> opcionalesAprobadas = new HashSet<>();
        for (Materia materia : materiasAprobadas) {
            if (materiasOpcionales.contains(materia)) {
                opcionalesAprobadas.add(materia);
            }
        }
        return opcionalesAprobadas.size();
    }

    public void setCursadas(Cursada cursada) {
        this.cursadas.add(cursada);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCarrera(Carrera carrera) {
        this.carreras.add(carrera);
    }

    public void addCursadaAprobada(Cursada cursada) {
        this.cursadasAprobadas.add(cursada);
    }

    public void addMateriaAprobada(Cursada cursada) {
        this.materiasAprobadas.add(cursada);
    }

    public Set<Materia> convertirCursadasAMaterias(Set<Cursada> cursadas) {
        return cursadas.stream()
                .map(Cursada::getMateria) // Extrae la materia de cada cursada
                .collect(Collectors.toSet()); // Devuelve un conjunto de materias
    }

    public boolean recibido(Carrera carrera) {
        return convertirCursadasAMaterias(materiasAprobadas).containsAll(carrera.getMateriasObligatorias())
                && getMateriasOpcionalesAprobadas(carrera) >= carrera.getNumeroMateriasOpcionales();
    }

}
