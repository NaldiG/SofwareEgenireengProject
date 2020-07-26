/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DBConn {
    
    private static Connection conn = null;

    public DBConn() {
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
    }
    
    public User getUser (String username, String password){
        try{

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from users where name = '" + username + "' and password = '" + password + "'");
            rs.next();
            return new User(rs.getInt("id"), rs.getInt("subscription"),rs.getInt("role"), rs.getString("name"), rs.getString("password"), rs.getString("quote"));
            
        }
        catch(SQLException ex){
            System.err.println(ex);
            return null;
        }
    }
    
    public boolean authenticateUser(String username, String password){
        
        try{
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from users where name = '" + username + "' and password = '" + password + "'");
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }
        catch(SQLException ex){
            return false;
        }
        
    }
    
    public Connection getConnection() {
        
        return conn;
    }
    
    public void insertScore(int score, int userId, String category, String difficulty){
        try{
            
            Statement stm = conn.createStatement();
            stm.execute("insert into scores (score, user_id, category, difficulty, date) values (" + score + ", " + userId + ", '" + category + "', '" + difficulty + "', now())");
            
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    public int gamesPlayedToday(int userId){
        try{
            int count = 0;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from scores where user_id = " + userId + " and date = date(now())");
            do{
                count++;
            }
            while(rs.next());
            return count;
        }
        catch(SQLException ex){
            System.err.println(ex);
            return 9;
        }
    }
    
    /*public ArrayList<String> getPastScores(int userId){
        ArrayList<String> output = new ArrayList<String>();
        try{
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select score, difficulty, category from scores where user_id = " + userId);
            
           
            
        }
        catch(SQLException ex){
            System.err.println(ex);
            
        }
    }*/
}
