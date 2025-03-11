/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author cativ
 */
public class PlanE implements PlanEstudio {

    private static final String condiciones = "Aprobó los finales de las correlativas y los finales de todas las materias de los últimos 3 cuatrimestres";
    private static final String nombre = "Plan E";
    private int cuatrimestrePrev = 3;

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {
        return (alumno.convertirCursadasAMaterias(alumno.getMateriasAprobadas()).containsAll(materia.getCorrelativas())
                && (alumno.convertirCursadasAMaterias(alumno.getMateriasAprobadas()).containsAll(materia.getCorrelativasAprobadasPrev(cuatrimestrePrev))));
    }

    @Override
    public String toString() {
        return nombre;
    }
}
