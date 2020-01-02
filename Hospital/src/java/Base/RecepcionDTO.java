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
public class RecepcionDTO {

    Connection db = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public RecepcionDTO() {
        Conexion nueva = new Conexion();
        db = nueva.getDb();
    }

    public void crearPaciente(String nombre, int edad, int peso, String cui, String sexo) {
        String sql = "INSERT INTO Pacientes(nombre_paciente,edad_paciente,cui,peso,sexo) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setInt(4, peso);
            ps.setString(3, cui);
            ps.setString(5, sexo);
            ps.executeUpdate();
        } catch (SQLException esa) {

        }
    }

    public void ingresarConsulta(int id_paciente, String id_empleado, int id_tarifa, double precio, String fecha_consulta, String nit) {
        String sql = "INSERT INTO Consultas(id_paciente,id_empleado,id_tarifa,precio,fecha_consulta,nit) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = db.prepareStatement(sql);
            ps.setInt(1, id_paciente);
            ps.setString(2, id_empleado);
            ps.setInt(3,id_tarifa);
            ps.setDouble(4, precio);
            ps.setString(5, fecha_consulta);
            ps.setString(6, nit);
            ps.executeUpdate();
        } catch (SQLException esa) {

        }
    }
    public boolean existePaciente(String nombre_paciente){
        String sql = "SELECT COUNT(*) AS total FROM Pacientes WHERE nombre_paciente = ?";
        int total = 0;
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, nombre_paciente);
            rs = ps.executeQuery();
            while(rs.next()){
                total = rs.getInt("total");
            }
        }catch(SQLException esa){
        
        }
        return total>0;
    }
    public ArrayList<String> listadoEspera(String id_empleado,String fecha_consulta){
        String sql = "SELECT id_paciente FROM Consultas WHERE id_empleado = ? AND atendido = ? AND fecha_consulta = ?";
        ArrayList<String> listadoTemp = new ArrayList<>();
        try{
            ps = db.prepareStatement(sql);
            ps.setString(1, id_empleado);
            ps.setBoolean(2, false);
            ps.setString(3, fecha_consulta);
            rs = ps.executeQuery();
            while(rs.next()){
                String sql2 = "SELECT nombre_paciente FROM Pacientes WHERE id_paciente = ?";
                PreparedStatement ps2 = db.prepareStatement(sql2);
                ps2.setInt(1, rs.getInt("id_paciente"));
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){
                    listadoTemp.add(rs2.getString("nombre_paciente"));
                }
            }
        } catch (SQLException esa){
        
        }
        return listadoTemp;
    }
}
