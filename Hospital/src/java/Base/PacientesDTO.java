/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yelbetto
 */
public class PacientesDTO {
    
    Connection db = null;
    PreparedStatement ps = null;
    
    public PacientesDTO(){
        Conexion nueva = new Conexion();
        db = nueva.getDb();
    }
    
    public String[] nombres(){
        String sql = "SELECT nombre_paciente FROM Pacientes";
        ArrayList<String> nombres = new ArrayList<>();
        
        try{
            ps = db.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nombres.add(rs.getString("nombre_paciente"));
            }
        }catch(SQLException esa){
        
        }
        int cuantos = nombres.size();
        String[] nom = new String[cuantos];
        if (cuantos>0){
            for (int u = 0; u < cuantos; u++){
                nom[u] = nombres.get(u);
            }
        }
        return nom;
    }
    public int unicidad(String nombre){
        String sql = "SELECT COUNT(*) AS total FROM Pacientes WHERE nombre_paciente = ?";
        int numero = 0;
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                numero = rs.getInt("total");
            }
        }catch(SQLException ea){
        
        }
        return numero;
    }
    public int codigoPaciente(String nombre){
        String sql = "SELECT id_paciente FROM Pacientes WHERE nombre_paciente = ?";
        int numero = 0;
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                numero = rs.getInt("id_paciente");
            }
        }catch(SQLException ea){
        
        }
        return numero;
    }
}
