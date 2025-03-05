/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.List;
import java.util.ArrayList;
import modelo.Carrera;
import modelo.Alumno;
/**
 *
 * @author cativ
 */
public class InscripcionController {
    public void inscribirAlumnoEnCarrera(Alumno alumno, Carrera carrera) {
        alumno.inscribirseEnCarrera(carrera);
    }

    public List<Carrera> obtenerCarrerasDeAlumno(Alumno alumno) {
        return alumno.getCarrerasRealizadas();
    }
}
