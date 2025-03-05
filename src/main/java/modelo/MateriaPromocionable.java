/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author cativ
 */
public class MateriaPromocionable extends Materia {

    private int notaPromocion;

    public MateriaPromocionable(String nombre, String codigo, int nroCuatrimestre, boolean obligatoria, Carrera carrera, int notaPromocion) {
        super(nombre, codigo, nroCuatrimestre, obligatoria, carrera);
        this.notaPromocion = notaPromocion;
    }

    @Override
    public String getPromocionable() {
        return "SI";
    }

    public int getNotaPromocion() {
        return this.notaPromocion;
    }

}
