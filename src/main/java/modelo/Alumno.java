/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    private boolean graduado;
    private List<Cursada> cursadas;
    private Set<Cursada> cursadasAprobadas;
    private Set<Cursada> materiasAprobadas;
    private List<Inscripcion> inscripciones;

    // Constructor
    public Alumno(String nombre, String apellido) {
        this.legajo = contadorLegajo;
        contadorLegajo++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.inscripciones = new ArrayList<>();
        this.cursadas = new ArrayList<>();
        this.cursadasAprobadas = new HashSet<>();;
        this.materiasAprobadas = new HashSet<>();;
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

    public List<Inscripcion> getInscripciones() {
        return this.inscripciones;
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

    public int getMateriasObligatoriasAprobadas() {
        var cantidad = 0;

        for (Cursada cursada : getCursadasAprobadas()) {
            if (cursada.getMateria().getObligatoria().equals("SI")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public void setCursadas(Cursada cursada) {
        this.cursadas.add(cursada);
    }

    public void addCursadaAprobada(Cursada cursada) {
        this.cursadasAprobadas.add(cursada);
    }

    public void addMateriaAprobada(Cursada cursada) {
        this.materiasAprobadas.add(cursada);
    }

    public List<Carrera> getCarrerasRealizadas() {
        List<Carrera> carreras = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            carreras.add(inscripcion.getCarrera());
        }
        return carreras;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void inscribirseEnCarrera(Carrera carrera) {
        Inscripcion inscripcion = new Inscripcion(carrera, this);
        inscripciones.add(inscripcion);
    }

    public boolean recibido(Carrera carrera) {
        return cursadasAprobadas.containsAll(carrera.getMateriasObligatorias())
                && getMateriasObligatoriasAprobadas() >= carrera.getNumeroMateriasOpcionales();
    }

}
