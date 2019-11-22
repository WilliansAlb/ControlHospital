/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author yelbetto
 */
public class Guardar {
    private ArrayList<String> lineas;
    private String[] lineando;
    private int lineasNum = 0;
    
    public Guardar(){
    
    }

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    public int getLineasNum() {
        return lineasNum;
    }

    public void setLineasNum(int lineasNum) {
        this.lineasNum = lineasNum;
    }

    public String[] getLineando() {
        return lineando;
    }

    public void setLineando(String[] lineando) {
        this.lineando = lineando;
    }
    
}
