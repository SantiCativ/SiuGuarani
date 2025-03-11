package modelo;

import modelo.Alumno;
import modelo.Materia;
import modelo.PlanEstudio;

public class PlanD implements PlanEstudio {

    private static final String condiciones = "Aprobó las cursadas de las correlativas y los finales de todas las materias de 3 "
            + "cuatrimestres previos al que se quiere anotar";
    private static final String nombre = "Plan D";
    private int cuatrimestrePrev = 3;

    @Override
    public boolean puedeCursar(Alumno alumno, Materia materia) {

        return (alumno.convertirCursadasAMaterias(alumno.getCursadasAprobadas()).containsAll(materia.getCorrelativasRegularesPrev(cuatrimestrePrev)))
                && (alumno.convertirCursadasAMaterias(alumno.getMateriasAprobadas()).containsAll(materia.getCorrelativasAprobadasPrev(cuatrimestrePrev)));
    }

    @Override
    public String toString() {
        return nombre;
    }
}
