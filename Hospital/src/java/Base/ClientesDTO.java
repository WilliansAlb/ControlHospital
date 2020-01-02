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

/**
 *
 * @author yelbetto
 */
public class ClientesDTO {
    Connection db = null;
    PreparedStatement ps = null;
    
    public ClientesDTO(){
        Conexion nueva = new Conexion();
        db = nueva.getDb();
    }
    
     public boolean existeCliente(String nit){
        String sql = "SELECT COUNT(*) AS total FROM Clientes WHERE nit = ?";
        int a = 0; 
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                a = rs.getInt("total");
            }
        }catch(SQLException ea){
        
        }
        return a > 0;
    }
    public void crearCliente(String nit, String nombre_cliente, String ciudad){
        String sql = "INSERT INTO Clientes(nit,nombre_cliente,ciudad) VALUES(?,?,?)";
        
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nit);
            ps.setString(2, nombre_cliente);
            ps.setString(3, ciudad);
            ps.executeUpdate();
        }catch(SQLException sa){
        
        }
    }
}
