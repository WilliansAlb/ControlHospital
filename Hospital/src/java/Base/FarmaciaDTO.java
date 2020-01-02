/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import POJOS.Medicamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author yelbetto
 */
public class FarmaciaDTO {
    Connection db = null;
    PreparedStatement ps = null;
    
    public FarmaciaDTO(){
        Conexion nueva = new Conexion();
        db = nueva.getDb();
    }
    
    public void agregarMedicamento(String nombre_medicamento, String pres_medicamento, double precio, int existencias, int minimo, String fecha_ingreso){
        String sql = "INSERT INTO Medicamentos(nombre_medicamento,pres_medicamento,precio,existencias,minimo,fecha_ingreso) VALUES(?,?,?,?,?,?)";
        
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_medicamento);
            ps.setString(2, pres_medicamento);
            ps.setDouble(3, precio);
            ps.setInt(4, existencias);
            ps.setInt(5, minimo);
            ps.setString(6, fecha_ingreso);
            ps.executeUpdate();
        
        }catch(SQLException s){
        
        }
    }
    public void suplirMedicamento(int id_medicamento, String pres_medicamento, double precio, int existencias, int minimo, String fecha_ingreso){
        String sql = "UPDATE Medicamentos SET precio = ?, existencias = existencias + ?, minimo = ?, fecha_ingreso = ? WHERE id_medicamento = ?";
         
        try{
            ps = db.prepareStatement(sql);
            ps.setDouble(1, precio);
            ps.setInt(2, existencias);
            ps.setInt(3, minimo);
            ps.setString(4, fecha_ingreso);
            ps.setInt(5, id_medicamento);
            ps.executeUpdate();
        
        }catch(SQLException s){
        
        }
    }
    public int id_medicamento(String nombre_medicamento){
        String sql = "SELECT id_medicamento FROM Medicamentos WHERE nombre_medicamento = ?";
        int id_medicamento = 0;
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_medicamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id_medicamento = rs.getInt("id_medicamento");
            }
        }catch(SQLException esa){
        }
        return id_medicamento;
    }
    
    public void agregarCompra(int cantidad, int id_medicamento, String fecha_compra,double total,String id_empleado){
        String sql = "INSERT INTO Compras(cantidad,fecha_compra,id_medicamento,total,id_empleado) VALUES(?,?,?,?,?)";
        
        try{
            ps = db.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2, fecha_compra);
            ps.setInt(3,id_medicamento);
            ps.setDouble(4, total);
            ps.setString(5,id_empleado);
            ps.executeUpdate();
        
        }catch(SQLException s){
        
        }
    }
    
    public ArrayList<Medicamento> nombresMedicamentos(){
        String sql = "SELECT * FROM Medicamentos";
        ArrayList<Medicamento> nuevo = new ArrayList<>();
        
        int count = 0;
        try{
            ps = db.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medicamento temp = new Medicamento();
                temp.setId_medicamento(rs.getInt("id_medicamento"));
                temp.setExistencias(rs.getInt("existencias"));
                temp.setMinimo(rs.getInt("minimo"));
                temp.setNombre_medicamento(rs.getString("nombre_medicamento"));
                temp.setPrecio(rs.getDouble("precio"));
                temp.setFecha(rs.getString("fecha_ingreso"));
                nuevo.add(count, temp);
                count++;
            }
        }catch(SQLException esa){
        }
        return nuevo;
    }
    public String[] precio(int id_medicamento){
        String sql = "SELECT precio,minimo FROM Medicamentos WHERE id_medicamento = ?";
        String[] precio = new String[2];
        try{
            ps = db.prepareStatement(sql);
            ps.setInt(1, id_medicamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                precio[0] = rs.getDouble("precio")+"";
                precio[1] = rs.getInt("minimo")+"";
            }
        }catch(SQLException esa){
        
        }
        return precio;
    }
    public boolean existencia(String nombre_medicamento){
        String sql = "SELECT COUNT(*) AS total FROM Medicamentos WHERE nombre_medicamento = ?";
        int existe = 0;
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_medicamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                existe = rs.getInt("total");
            }
        }catch(SQLException esa){
        
        }
        return existe != 0;
    }
    public String costou(int id_medicamento){
        String sql = "SELECT precio FROM Medicamentos WHERE id_medicamento = ?";
        String costou = "";
        try{
            ps = db.prepareStatement(sql);
            ps.setInt(1, id_medicamento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                costou = rs.getDouble("precio")+"";
            }
        }catch(SQLException esa){
        
        }
        return costou;
    }
    public void prueba(String cosas){
        String sql = "INSERT INTO Pruebas(cosa) VALUES(?)";
        
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, cosas);
            ps.executeUpdate();
        
        }catch(SQLException s){
        
        }
    }
    
    public void venderMedicamento(int id_medicamento, String id_empleado, double total, int cantidad, String nit, String fecha_venta){
        String sql = "INSERT INTO Ventas(id_medicamento,cantidad,nit,fecha_venta,total,id_empleado) VALUES(?,?,?,?,?,?)";
        
        try{
            ps = db.prepareStatement(sql);
            ps.setInt(1, id_medicamento);
            ps.setInt(2, cantidad);
            ps.setString(3, nit);
            ps.setString(4, fecha_venta);
            ps.setDouble(5, total);
            ps.setString(6, id_empleado);
            ps.executeUpdate();
        
        }catch(SQLException s){
        
        }
    }
    public String[] datosComprador(String nit){
        String sql = "SELECT nombre_cliente,ciudad FROM Clientes WHERE nit = ?";
        String[] precio = new String[2];
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                precio[0] = rs.getString("nombre_cliente");
                precio[1] = rs.getString("ciudad");
            }
        }catch(SQLException esa){
        
        }
        return precio;
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
