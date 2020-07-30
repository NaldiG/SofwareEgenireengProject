/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author ASUS
 */
public class SubscriptionScene {
    
    Scene scene;
    GridPane root;

    public SubscriptionScene(int subscription, int userId) {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        Label label = new Label();
        root.add(label,0,1);
        
        Button btn = new Button("Upgrade");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BASELINE_CENTER);
        hbBtn.getChildren().add(btn);
        
        
        btn.setOnAction(event ->
        {
            
            DBConn connection = new DBConn();
            connection.upgrade(userId);
            label.setText("Now you have premium subscription which allows you to play unlimited games per day");
            root.getChildren().remove(hbBtn);
        });
        
        if(subscription == 1){
            label.setText("You currenly have a free subscription which limits you to 5 games per day.\n"
                    + "To upgrade to a premium subscription, which allows you to play an unlimited number of games per day, please press upgrade button.");
            root.add(hbBtn,0,2);
        }else{
            label.setText("You have premium subscription which allows you to play an unlimited number of games per day.");
        }
        
    }
    
    public Scene getScene(){
        return scene;
    }
    
}
