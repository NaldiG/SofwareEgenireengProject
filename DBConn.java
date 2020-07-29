/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

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
            Blob blob = rs.getBlob("avatar");
            InputStream in = blob.getBinaryStream();
            BufferedImage image = ImageIO.read(in);
            Image avatar = SwingFXUtils.toFXImage(image, null);
            return new User(rs.getInt("id"), rs.getInt("subscription"),rs.getInt("role"), rs.getString("name"), rs.getString("password"), rs.getString("quote"), avatar);
            
        }
        catch(SQLException ex){
            System.err.println(ex);
            return null;
        }
        catch(IOException ex){
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
    
    public void upgrade(int userId){
        try{
            Statement stm = conn.createStatement();
            stm.execute("update users set subscription = 2 where id = " + userId);
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    public boolean register(String username, String password){
        try{
            Statement stm = conn.createStatement();
            stm.execute("insert into users (name, password, subscription, role) values ('" + username + "', '" + password + "', 1, 1)");
            return true;
        }
        catch(SQLException ex){
            System.err.println(ex);
            return false;
        }
    }
    
    public void changeQuote (String quote, int id){
        try{
            Statement stm = conn.createStatement();
            stm.execute("update users set quote = '" + quote + "' where id = " + id);
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    public void changeAvatar(FileInputStream avatar, int id){
        try{
            String sql = "update users set avatar = ? where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBinaryStream(1, avatar);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }catch(SQLException ex){
            System.err.println(ex);
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
