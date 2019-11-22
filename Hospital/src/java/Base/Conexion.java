/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author yelbetto
 */
public class Conexion {
  
    private final String user = "root";
    private final String password = "Cristeptesico_65";
    private final String urlConnection = "jdbc:mysql://localhost:3306/Hospital";
    Connection db = null;
    PreparedStatement ps = null;
    boolean paso = false;
    

    public boolean isPaso() {
        return paso;
    }

    public void setPaso(boolean paso) {
        this.paso = paso;
    }
    
  
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            db = DriverManager.getConnection(urlConnection, user, password);
            paso=true;
        } catch (SQLException ex) {
            paso=false;
        } catch (Exception e) {

        }
    }

    public Connection getDb() {
        return db;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }
}
