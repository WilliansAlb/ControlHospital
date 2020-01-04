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
public class Tarifas {
    private String nombre;
    private Double costo;
    private Double pago_especialista;
    private Double precio;
    private int id_tarifa;
    
    public Tarifas(){
    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPago_especialista() {
        return pago_especialista;
    }

    public void setPago_especialista(Double pago_especialista) {
        this.pago_especialista = pago_especialista;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId_tarifa() {
        return id_tarifa;
    }

    public void setId_tarifa(int id_tarifa) {
        this.id_tarifa = id_tarifa;
    }
    
}
