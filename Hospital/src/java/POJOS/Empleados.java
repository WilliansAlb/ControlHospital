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
public class Empleados {
    private int id_empleado;
    private String nombre_empleado;
    private String cui;
    private boolean medico_especial;
    private int id_empleos;
    private boolean despedido;
    private boolean contratado;
    private String fecha_contrato;

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }
    
    public Empleados(){
    
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public boolean isMedico_especial() {
        return medico_especial;
    }

    public void setMedico_especial(boolean medico_especial) {
        this.medico_especial = medico_especial;
    }

    public int getId_empleos() {
        return id_empleos;
    }

    public void setId_empleos(int id_empleos) {
        this.id_empleos = id_empleos;
    }

    public boolean isDespedido() {
        return despedido;
    }

    public void setDespedido(boolean despedido) {
        this.despedido = despedido;
    }

    public boolean isContratado() {
        return contratado;
    }

    public void setContratado(boolean contratado) {
        this.contratado = contratado;
    }
    
}
