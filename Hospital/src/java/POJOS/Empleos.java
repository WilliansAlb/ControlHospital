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
public class Empleos {
    private Double sueldo;
    private String nombre_empleo;
    private boolean descuentos;
    private int total;
    private int id_empleos;
    
    public Empleos(){
    
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombre_empleo() {
        return nombre_empleo;
    }

    public void setNombre_empleo(String nombre_empleo) {
        this.nombre_empleo = nombre_empleo;
    }

    public boolean isDescuentos() {
        return descuentos;
    }

    public void setDescuentos(boolean descuentos) {
        this.descuentos = descuentos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_empleos() {
        return id_empleos;
    }

    public void setId_empleos(int id_empleos) {
        this.id_empleos = id_empleos;
    }
    
}
