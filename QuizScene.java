package software.engineering.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class QuizScene {
    
    Scene scene;
    GridPane root;
    JSONArray categories;

    public QuizScene() {
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        ComboBox categoryCombo = new ComboBox();
        ComboBox difficultyCombo = new ComboBox();
        Label categoryLbl = new Label("Category:");
        Label difficultyLbl = new Label("Difficulty:");
        final Text actiontarget = new Text();
        
        
        
        try {
            InputStream is = new URL("https://opentdb.com/api_category.php").openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            categories = json.getJSONArray("trivia_categories");
            for(int i = 0; i < categories.length(); i++) {
                
                JSONObject category = categories.getJSONObject(i);
                
                categoryCombo.getItems().add(category.get("name").toString());
            }
            
        }
        catch(IOException  ioe) {
            System.out.println("IO problem");
        }
        
        difficultyCombo.getItems().add("Easy");
        difficultyCombo.getItems().add("Medium");
        difficultyCombo.getItems().add("Hard");
        
        Button btn = new Button("Next");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BASELINE_CENTER);
        hbBtn.getChildren().add(btn);
        
        root.add(categoryLbl,0,0);
        root.add(categoryCombo,1,0);
        root.add(difficultyLbl,0,1);
        root.add(difficultyCombo,1,1);
        root.add(hbBtn,1,4);
        root.add(actiontarget,1,6);
        
        btn.setOnAction(event ->
        {
            String categoryId = "9";
            if(categoryCombo.getValue() != null && difficultyCombo.getValue() != null){
                for(int i = 0; i < categories.length(); i++) {
                
                    JSONObject category = categories.getJSONObject(i);
                    if(categoryCombo.getValue().toString().equals(category.get("name"))){
                        categoryId = category.get("id").toString();
                    }
                    
                }
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("https://opentdb.com/api.php?amount=5&category=" + categoryId + "&difficulty=" + difficultyCombo.getValue().toString() + "&type=multiple "
                        + "\nhttps://opentdb.com/api.php?amount=5&category=" + categoryId + "&difficulty=" + difficultyCombo.getValue().toString() + "&type=boolean");
            }else{
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Please select category and difficulty");
            }
            
            
            
        });
        
        
        
    }
    
    public Scene getScene(){
        return scene;
    }
    
}
