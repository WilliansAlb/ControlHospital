/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author yelbetto
 */
public class Empleados {
    Connection db = null;
    PreparedStatement ps = null;
    
    public Empleados(){
        Conexion con = new Conexion();
        db = con.getDb();
        ps = con.getPs();
    }
}
