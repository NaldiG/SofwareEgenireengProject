/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author ASUS
 */
public class MainScene {
    
    Scene scene;
    GridPane root;
    User currentUser;

    public MainScene(String username, String password) {
        try{
            Connection con = DBConn.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select * from users where name = '" + username + "' and password = '" + password + "'");
            rs.next();
            currentUser = new User(rs.getInt("id"), rs.getInt("subscription"),rs.getInt("role"), rs.getString("name"), rs.getString("password"), rs.getString("quote"));
            
        }
        catch(SQLException ex){
            System.err.println(ex);
        }
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 300, 275);
        
        Button btn = new Button("Take a quiz");
        
        root.add(btn,0,0);
          
        btn.setOnAction(event ->
        {
            
            Stage secondaryStage = new Stage();
            
            QuizScene qs = new QuizScene(currentUser.getId());
            
            secondaryStage.setScene(qs.getScene());
            secondaryStage.setTitle("Quiz");
            secondaryStage.show();
            
        });
         
    }
    
    public Scene getScene(){
        return scene;
    }
    
    
    
}
