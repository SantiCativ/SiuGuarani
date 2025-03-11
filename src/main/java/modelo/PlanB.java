/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cativ
 */
public class PlanB implements PlanEstudio {

    private static final String condiciones = "Aprobó los finales de las correlativas";
    private static final String nombre = "Plan B";

    @Override

    public boolean puedeCursar(Alumno alumno, Materia materia) {

        return alumno.convertirCursadasAMaterias(alumno.getMateriasAprobadas()).containsAll(materia.getCorrelativas());
    }

    @Override
    public String toString() {
        return nombre;
    }
}
