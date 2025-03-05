/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
/**
 *
 * @author cativ
 */
public class PlanC implements PlanEstudio {

    private static final String condiciones = "Aprobó las cursadas de las correlativas y los finales de todas las materias de los últimos 5 cuatrimestres";
    private static final String nombre = "Plan C";
    private static final int cuatrimestrePrev = 5;

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {

        return (materia.convertirCursadasAMaterias(alumno.getCursadasAprobadas()).containsAll(materia.getCorrelativasRegularesPrev(cuatrimestrePrev)))
                && (materia.convertirCursadasAMaterias(alumno.getMateriasAprobadas()).containsAll(materia.getCorrelativasAprobadasPrev(cuatrimestrePrev)));
    }

    @Override
    public String toString() {
        return nombre;
    }
}
