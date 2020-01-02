/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import POJOS.Areas;
import POJOS.Empleos;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yelbetto
 */
public class AdministracionDTO {
    
    private boolean todoBien = true;
    Connection db = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public AdministracionDTO() {
        Conexion nueva = new Conexion();
        db = nueva.getDb();
    }
    
    public void agregarSolicitud(String cui, String nombre_empleado, int area, InputStream curriculum) {
        String sql = "INSERT INTO Empleados(id_empleado,nombre_empleado,area,curriculum) VALUES(?,?,?,?)";
        try {
            ps = db.prepareStatement(sql);
            ps.setString(1, cui);
            ps.setBlob(4, curriculum);
            ps.setInt(3, area);
            ps.setString(2, nombre_empleado);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            setTodoBien(false);
        }
    }
    
    public String[] nombresSolicitudes(int area) {
        String sql = "SELECT id_empleado, nombre_empleado FROM Empleados WHERE visto = ? AND area = ?";
        ArrayList<String> nombres = new ArrayList<>();
        
        try {
            ps = db.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, area);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("id_empleado"));
                nombres.add(rs.getString("nombre_empleado"));
            }
        } catch (SQLException esa) {
            
            setTodoBien(false);
        }
        int cuantos = nombres.size();
        String[] nom = new String[cuantos];
        if (cuantos > 0) {
            for (int u = 0; u < cuantos; u++) {
                nom[u] = nombres.get(u);
            }
        }
        return nom;
    }
    
    public void ingresarEmpleo(String nombre_empleo, Double salario, boolean descuentos) {
        String sql = "INSERT INTO Empleos(nombre_empleo,sueldo,descuentos) VALUES(?,?,?)";
        
        try {
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_empleo);
            ps.setDouble(2, salario);
            ps.setBoolean(3, descuentos);
            ps.executeUpdate();
        } catch (SQLException esa) {
            
            setTodoBien(false);
        }
    }
    
    public Empleos[] devolviendo() {
        ArrayList<Empleos> empleos1 = new ArrayList<>();
        String sql = "SELECT nombre_empleo, sueldo, descuentos, id_empleos FROM Empleos";
        try {
            ps = db.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleos tmp = new Empleos();
                tmp.setId_empleos(rs.getInt("id_empleos"));
                tmp.setNombre_empleo(rs.getString("nombre_empleo"));
                tmp.setSueldo(rs.getDouble("sueldo"));
                tmp.setDescuentos(rs.getBoolean("descuentos"));
                tmp.setTotal(empleados(rs.getInt("id_empleos")));
                empleos1.add(tmp);
            }
        } catch (SQLException sa) {
            setTodoBien(false);
        }
        Empleos[] nuevo = new Empleos[empleos1.size()];
        for (int i = 0; i < empleos1.size(); i++) {
            nuevo[i] = empleos1.get(i);
        }
        return nuevo;
    }
    
    public int empleados(int id_empleos) {
        String sql = "SELECT COUNT(*) AS total FROM Empleados WHERE id_empleos = ? AND contratado = ?";
        int empleados = 0;
        try {
            PreparedStatement ps2 = db.prepareStatement(sql);
            ps2.setInt(1, id_empleos);
            ps2.setBoolean(2, true);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                empleados = rs2.getInt("total");
            }
        } catch (SQLException sa) {
            setTodoBien(false);
        }
        return empleados;
    }
    
    public void ingresarArea(String nombre_area, boolean contratando) {
        String sql = "INSERT INTO Areas(nombre_area, contratando) VALUES(?,?)";
        
        try {
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_area);
            ps.setBoolean(2, contratando);
            ps.executeUpdate();
        } catch (SQLException esa) {
            setTodoBien(false);
        }
    }
    
    public Areas[] devolviendoAreas() {
        ArrayList<Areas> areas1 = new ArrayList<>();
        String sql = "SELECT nombre_area, contratando, id_areas FROM Areas";
        try {
            ps = db.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Areas tmp = new Areas();
                tmp.setId_area(rs.getInt("id_areas"));
                tmp.setNombre_area(rs.getString("nombre_area"));
                tmp.setContratando(rs.getBoolean("contratando"));
                tmp.setTotal(areas(rs.getInt("id_areas")));
                tmp.setCandidatos(areasCandidatos(rs.getInt("id_areas")));
                areas1.add(tmp);
            }
        } catch (SQLException sa) {
            setTodoBien(false);
        }
        Areas[] nuevo = new Areas[areas1.size()];
        for (int i = 0; i < areas1.size(); i++) {
            nuevo[i] = areas1.get(i);
        }
        return nuevo;
    }
    
    public int areas(int id_areas) {
        String sql = "SELECT COUNT(*) AS total FROM Empleados WHERE id_areas = ? AND contratado = ?";
        int empleados = 0;
        try {
            PreparedStatement ps2 = db.prepareStatement(sql);
            ps2.setInt(1, id_areas);
            ps2.setBoolean(2, true);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                empleados = rs2.getInt("total");
            }
        } catch (SQLException sa) {
            setTodoBien(false);
        }
        return empleados;
    }
    
    public int areasCandidatos(int id_areas) {
        String sql = "SELECT COUNT(*) AS total FROM Empleados WHERE id_areas = ? AND contratado = ? AND visto = ?";
        int candidatos = 0;
        try {
            PreparedStatement ps3 = db.prepareStatement(sql);
            ps3.setInt(1, id_areas);
            ps3.setBoolean(2, false);
            ps3.setBoolean(3, false);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                candidatos = rs3.getInt("total");
            }
        } catch (SQLException sa) {
            setTodoBien(false);
        }
        return candidatos;
    }
    
    public boolean isTodoBien() {
        return todoBien;
    }
    
    public void setTodoBien(boolean todoBien) {
        this.todoBien = todoBien;
    }
    
    public boolean existeEmpleos() {
        String sql = "SELECT COUNT(*) AS total FROM Empleos";
        int existe = 0;
        try {
            ps = db.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                existe = rs.getInt("total");
            }
        } catch (SQLException esa) {
            
        }
        return (existe > 0);
    }

    public boolean existeAreas() {
        String sql = "SELECT COUNT(*) AS total FROM Areas";
        int existe = 0;
        try {
            ps = db.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                existe = rs.getInt("total");
            }
        } catch (SQLException esa) {
            
        }
        return (existe > 0);
    }
}
