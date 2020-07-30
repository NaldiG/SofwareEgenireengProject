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
    static Stage secondaryStage = new Stage();
    DBConn connection = new DBConn();

    public MainScene(String username, String password) {
        
        connection = new DBConn();
        currentUser = connection.getUser(username, password);
        
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
            if(currentUser.getSubscription() == 2 || connection.gamesPlayedToday(currentUser.getId()) <= 5){
                
                System.out.println(connection.gamesPlayedToday(currentUser.getId()));
                QuizSelectionScene qs = new QuizSelectionScene(currentUser.getId());
                secondaryStage.setScene(qs.getScene());
                secondaryStage.setTitle("Quiz Selection");
                secondaryStage.show();
                
            }else{
                
                System.out.print("limit reached");
                
            }
        });
        
        Button btn2 = new Button("Check subscription");
        
        root.add(btn2,0,1);
          
        btn2.setOnAction(event ->
        {
            
            currentUser = connection.getUser(username, password);
            SubscriptionScene subscriptionScene = new SubscriptionScene(currentUser.getSubscription(), currentUser.getId());
            secondaryStage.setScene(subscriptionScene.getScene());
            secondaryStage.setTitle("Subscription");
            secondaryStage.show();
            
        });
        
        Button btn3 = new Button("Check profile");
        
        root.add(btn3,0,2);
          
        btn3.setOnAction(event ->
        {
            
            currentUser = connection.getUser(username, password);
            ProfileScene profileScene = new ProfileScene(currentUser);
            secondaryStage.setScene(profileScene.getScene());
            secondaryStage.setTitle("Profile");
            secondaryStage.show();
            
        });
        
        Button btn4 = new Button("View quiz history");
        
        root.add(btn4,0,3);
          
        btn4.setOnAction(event ->
        {
            
            PastScoresScene pastScoresScene = new PastScoresScene(currentUser.getId());
            secondaryStage.setScene(pastScoresScene.getScene());
            secondaryStage.setTitle("Past activity");
            secondaryStage.show();
            
        });
         
    }
    
    public Scene getScene(){
        return scene;
    }
    
    public static void setSecondaryScene(Scene scene, String title){
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(title);
    }
    
}
