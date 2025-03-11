/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author cativ
 */
public class Cursada {

    private Materia materia;
    private Alumno alumno;
    private int notaParcial = -1;
    private int notaFinal = -1;

    public Cursada(Materia materia, Alumno alumno) {
        this.materia = materia;
        this.alumno = alumno;
    }

    public int getParcial() {
        return this.notaParcial;
    }

    public void setNotaParcial(int nota) {
        notaParcial = nota;
    }

    public void setCursada(int notaParcial, int notaFinal) {
        setNotaParcial(notaParcial);
        setNotaFinal(notaFinal);

        if (materia.getPromocionable().equals("SI")) {
            if (notaParcial >= materia.getNotaPromocion()) {
                alumno.addMateriaAprobada(this);
            }
        }
        if (notaParcial >= materia.notaAprobarCursada) {
            alumno.addCursadaAprobada(this);
        }

        if (notaFinal >= materia.notaAprobarFinal) {
            alumno.addMateriaAprobada(this);
        }
    }

    public int getNotaFinal() {
        return this.notaFinal;
    }

    public void setNotaFinal(int nota) {
        notaFinal = nota;
    }

    public Materia getMateria() {
        return this.materia;
    }

}
