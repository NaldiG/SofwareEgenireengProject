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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class SoftwareEngineeringProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        DBConn.getConnection();
        Scene scene;
        GridPane root;
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 300, 275);
        
        Text scenetitle = new Text("Please Login: ");
        scenetitle.setFont(Font.font("Tahoma",FontWeight.NORMAL, 20));
        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password: ");
        PasswordField pwBox = new PasswordField();
        
        root.add(scenetitle,0,0,2,1);
        root.add(userName,0,1);
        root.add(userTextField, 1,1);
        root.add(pw,0,2);
        root.add(pwBox,1,2);
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        root.add(hbBtn,1,4);
        
        final Text actiontarget = new Text();
        root.add(actiontarget,1,6);
        
        btn.setOnAction(event ->
        {
            
            String username = userTextField.getText();
            String password = pwBox.getText();
            
            MainScene ms = new MainScene();
            
            if(authenticateUser(username, password)){
                
                primaryStage.setTitle("Main Menu");
                primaryStage.setScene(ms.getScene());
                
            }else{
                
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in unsuccessful");
                
            }
            
            
            /*if(User.checkUser(username, password)) {
                System.out.println("Welcome");
                primaryStage.close();
                primaryStage.setScene(mscene.getScene());
                primaryStage.show();
            }
            else {
                System.out.println("Invalid user");
            }*/
            
        });
        
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean authenticateUser(String username, String password){
        
        try{
            Connection con = DBConn.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("Select * from users where name = '" + username + "' and password = '" + password + "'");
            if(rs.next()){
                System.out.println(rs.getString("name"));
                return true;
            }else{
                return false;
            }
        }
        catch(SQLException ex){
            return false;
        }
        
    }
    
}
