/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author cativ
 */
public class Inscripcion {

    private Alumno alumno;
    private Carrera carrera;
    private Date fecha;

    public Inscripcion(Carrera carrera, Alumno alumno) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.fecha = new Date(); // Fecha de inscripción
    }
    
     public Carrera getCarrera() {
        return carrera;
    }

}
