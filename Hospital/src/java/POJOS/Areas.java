/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJOS;

/**
 *
 * @author yelbetto
 */
public class Areas {

    private String nombre_area;
    private boolean contratando;
    private int total;
    private int id_area;
    private int candidatos;

    public Areas() {

    }

    public String getNombre_area() {
        return nombre_area;
    }

    public void setNombre_area(String nombre_area) {
        this.nombre_area = nombre_area;
    }

    public boolean isContratando() {
        return contratando;
    }

    public void setContratando(boolean contratando) {
        this.contratando = contratando;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public int getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(int candidatos) {
        this.candidatos = candidatos;
    }

}
