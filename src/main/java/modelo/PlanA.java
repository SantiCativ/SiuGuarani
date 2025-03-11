/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author cativ
 */
public class PlanA implements PlanEstudio {

    private static final String condiciones = "Aprobó las cursadas de las correlativas";
    private static final String nombre = "Plan A";

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {

        return alumno.convertirCursadasAMaterias(alumno.getCursadasAprobadas()).containsAll(materia.getCorrelativas());
    }

    @Override
    public String toString() {
        return nombre;
    }
}
