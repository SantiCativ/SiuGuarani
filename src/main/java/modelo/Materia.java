/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import modelo.Carrera;

/**
 *
 * @author cativ
 */
public class Materia {

    private String codigo;
    private String nombre;
    private int nroCuatrimestre;
    private boolean obligatoria;
    private Carrera carrera;
    private List<Materia> correlativas;
    public static int notaAprobarCursada = 6;
    public static int notaAprobarFinal = 4;

    public Materia(String nombre, String codigo, int nroCuatrimestre, boolean obligatoria, Carrera carrera) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nroCuatrimestre = nroCuatrimestre;
        this.obligatoria = obligatoria;
        this.carrera = carrera;
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

    public void agregarCorrelativa(Materia materia) {
        this.correlativas.add(materia);
    }

    public void agregarCorrelativa(List<Materia> correlativas) {
        for (Materia materia : correlativas) {
            this.correlativas.add(materia);
        }
    }

    public boolean esObligatoria() {
        return this.obligatoria;
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

    public String getObligatoria() {
        if (this.obligatoria) {
            return "SI";
        } else {
            return "NO";
        }
    }

    public String getCarrera() {
        return this.carrera.toString();
    }

    public String getPromocionable() {
        //por defecto no
        return "NO";
    }

    public int getNotaPromocion() {
        return 0;
    }

    public int getNotaAprobarCursada() {
        return notaAprobarCursada;
    }

    public int getNotaAprobarFinal() {
        return notaAprobarFinal;
    }

    public Set<Materia> convertirCursadasAMaterias(Set<Cursada> cursadas) {
        return cursadas.stream()
                .map(Cursada::getMateria) // Extrae la materia de cada cursada
                .collect(Collectors.toSet()); // Devuelve un conjunto de materias
    }

}
