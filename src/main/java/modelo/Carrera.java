/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;
import modelo.PlanEstudio;

/**
 *
 * @author cativ
 */
public class Carrera {

    private String nombre;
    private int cantMateriasOpcionales;
    private PlanEstudio pde;
    private List<Materia> materiasObligatorias;
    private List<Materia> materiasOpcionales;

    // Constructor
    public Carrera(String nombre, int materiasOpcionales, PlanEstudio plan) {
        this.nombre = nombre;
        this.cantMateriasOpcionales = materiasOpcionales;
        this.pde = plan;
        this.materiasObligatorias = new ArrayList<>();
        this.materiasOpcionales = new ArrayList<>();
    }

    public Carrera(String nombre, int materiasOpcionales, PlanEstudio plan, List<Materia> obligatorias, List<Materia> opcionales) {
        this.nombre = nombre;
        this.cantMateriasOpcionales = materiasOpcionales;
        this.pde = plan;
        this.setMateriasObligatorias(obligatorias);
        this.setMateriasOpcionales(opcionales);
    }

    // Getters y Setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroMateriasOpcionales() {
        return this.cantMateriasOpcionales;
    }

    public List<Materia> getMateriasObligatorias() {
        return this.materiasObligatorias;
    }

    public List<Materia> getMateriasOpcionales() {
        return this.materiasOpcionales;
    }

    public String getPlanDeEstudio() {
        return this.pde.toString();
    }

    public void setPlanDeEstudio(PlanEstudio plan) {
        this.pde = plan;
    }

    public boolean puedeCursar(Alumno alumno, Materia materia) {
        return this.pde.puedeCursar(alumno, materia);
    }

    public List<Materia> obtenerMateriasDisponibles(Alumno alumno) {
        var materiasDisponibles = new ArrayList<Materia>();

        for (Materia materia : materiasObligatorias) {
            if (this.puedeCursar(alumno, materia)) {
                materiasDisponibles.add(materia);
            }
        }

        for (Materia materia : materiasOpcionales) {
            if (this.puedeCursar(alumno, materia)) {
                materiasDisponibles.add(materia);
            }
        }
        return materiasDisponibles;
    }

    public void setMateriasObligatorias(List<Materia> materiasObligatorias) {
        this.materiasObligatorias = materiasObligatorias;
    }

    public void setMateriasOpcionales(List<Materia> materiasOpcionales) {
        this.materiasOpcionales = materiasOpcionales;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    public Materia getMateriaPorCodigo(String codigoMateria) {
        for (Materia materia : materiasObligatorias) {
            if (materia.getCodigo().equals(codigoMateria)) {
                return materia;
            }
        }

        for (Materia materia : materiasOpcionales) {
            if (materia.getCodigo().equals(codigoMateria)) {
                return materia;
            }
        }

        return null;
    }

}
