/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Carrera;
import modelo.Materia;
import modelo.PlanEstudio;

/**
 *
 * @author cativ
 */
public class CarreraController {

    private ArrayList<Carrera> listaCarreras = new ArrayList<>();
    private ArrayList<Materia> listaMaterias = new ArrayList<>();

    public void agregarCarrera(String nombre, int materiasOpcionales, PlanEstudio plan) {
        Carrera nuevaCarrera = new Carrera(nombre, materiasOpcionales, plan);
        listaCarreras.add(nuevaCarrera);
    }

    public void agregarCarrera(String nombre, int materiasOpcionales, PlanEstudio plan, List<Materia> obligatorias, List<Materia> opcionales) {
        Carrera nuevaCarrera = new Carrera(nombre, materiasOpcionales, plan, obligatorias, opcionales);
        listaCarreras.add(nuevaCarrera);
    }

    public void agregarCarrera(Carrera carrera) {
        listaCarreras.add(carrera);
    }

    public ArrayList<Carrera> obtenerCarreras() {
        return listaCarreras;
    }

    public Carrera obtenerCarreraPorNombre(String carreraSelected) {

        for (Carrera carrera : listaCarreras) {
            if (carrera.getNombre().equalsIgnoreCase(carreraSelected)) {
                return carrera;
            }
        }

        return null;

    }

}
