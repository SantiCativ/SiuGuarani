/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Alumno;

/**
 *
 * @author cativ
 */
public class AlumnoController {
    private ArrayList<modelo.Alumno> listaAlumnos = new ArrayList<>();
    
    public AlumnoController() {
        this.listaAlumnos = new ArrayList<>();
    }

    public void agregarAlumno(String nombre, String apellido) {
        modelo.Alumno nuevoAlumno = new modelo.Alumno(nombre, apellido);
        listaAlumnos.add(nuevoAlumno);
    }

    public ArrayList<Alumno> obtenerAlumnos() {
        return listaAlumnos;
    }

    // Método que agrega la solución al error
    public Alumno obtenerAlumnoPorLegajo(int legajo) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getLegajo() == legajo) {
                return alumno;
            }
        }
        return null; // Si no se encuentra el alumno, retorna null
    }
}
