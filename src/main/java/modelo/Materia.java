/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.Carrera;

/**
 *
 * @author cativ
 */
public class Materia {

    private String codigo;
    private String nombre;
    private int nroCuatrimestre;
    private Set<Carrera> carreras;
    private List<Materia> correlativas;
    public static int notaAprobarCursada = 6;
    public static int notaAprobarFinal = 4;

    public Materia(String nombre, String codigo, int nroCuatrimestre) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nroCuatrimestre = nroCuatrimestre;
        this.carreras = new HashSet<Carrera>();
        this.correlativas = new ArrayList<Materia>();
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    // Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Carrera> getCarreras() {
        return this.carreras;
    }

    public void setCarreras(Carrera carrera) {
        this.carreras.add(carrera);
    }

    public void agregarCorrelativa(Materia materia) {
        this.correlativas.add(materia);
    }

    public void agregarCorrelativa(List<Materia> correlativas) {
        for (Materia materia : correlativas) {
            this.correlativas.add(materia);
        }
    }

    public List<Materia> getCorrelativas() {
        return this.correlativas;
    }

    public Set<Materia> getCorrelativasRegularesPrev(int cuatrimestrePrev) {
        Set<Materia> materias = new HashSet<>();
        for (Materia materia : correlativas) {
            if (materia.getNroCuatrimestre() < cuatrimestrePrev) {
                materias.add(materia);
            }
        }
        return materias;
    }

    public Set<Materia> getCorrelativasAprobadasPrev(int cuatrimestrePrev) {
        Set<Materia> materias = new HashSet<>();
        int cuatrimestreLimite = Math.max(1, getNroCuatrimestre() - cuatrimestrePrev);

        for (Materia materia : correlativas) {
            if ((materia.getNroCuatrimestre() >= cuatrimestreLimite) && (materia.getNroCuatrimestre() < getNroCuatrimestre())) {
                materias.add(materia);
            }
        }
        return materias;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public int getNroCuatrimestre() {
        return this.nroCuatrimestre;
    }

    public String getPromocionable() {
        //por defecto no
        return "NO";
    }

    public int getNotaPromocion() {
        return 0;
    }

}
