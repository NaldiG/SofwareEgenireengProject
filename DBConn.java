/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DBConn {
    
    private static Connection conn = null;
    
    public static Connection getConnection() {
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/software_engineering_project?useUnicode=true&serverTimezone=UTC",
                        "root",
                        "");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
    
}
