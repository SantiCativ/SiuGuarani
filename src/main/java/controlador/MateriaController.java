/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author cativ
 */
import java.util.ArrayList;
import java.util.List;
import modelo.Carrera;
import modelo.Materia;
import modelo.MateriaPromocionable;

public class MateriaController {

    private ArrayList<Materia> listaMaterias = new ArrayList<>();

    public void agregarMateria(Materia materia) {
        listaMaterias.add(materia);
    }

    public void agregarMateria(String nombre, String codigo, int nroCuatrimestre, List<Materia> correlativas) {
        Materia nuevaMateria = new Materia(nombre, codigo, nroCuatrimestre);
        nuevaMateria.agregarCorrelativa(correlativas);
        listaMaterias.add(nuevaMateria);
    }

    public void agregarMateria(String nombre, String codigo, int nroCuatrimestre, List<Materia> correlativas, int notaPromocion) {
        Materia nuevaMateria = new MateriaPromocionable(nombre, codigo, nroCuatrimestre, notaPromocion);
        nuevaMateria.agregarCorrelativa(correlativas);
        listaMaterias.add(nuevaMateria);
    }

    public ArrayList<Materia> obtenerMaterias() {
        return listaMaterias;
    }

    public Materia obtenerMateriaCodigo(String codigo) {
        for (Materia materia : listaMaterias) {
            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }
        }
        return null;
    }
}
