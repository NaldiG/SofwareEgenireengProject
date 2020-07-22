/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ASUS
 */
public class ResultScene {
    Scene scene;
    GridPane root;

    public ResultScene(QuestionModel model) {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        Label questionNumber = new Label(model.getScore());
        root.add(questionNumber,0,0);
    }
    
    public Scene getScene(){
        return scene;
    }
}
