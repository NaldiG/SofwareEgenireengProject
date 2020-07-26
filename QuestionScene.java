/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author ASUS
 */
public class QuestionScene {

    Scene scene;
    GridPane root;
    RadioButton radio1, radio2, radio3, radio4;
    ToggleGroup group;
    
    public QuestionScene(QuestionModel model) {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        Label questionNumber = new Label(model.getQuestionNumber());
        root.add(questionNumber,0,0);
        Label question = new Label(model.getQuestion());
        root.add(question,0,1);
        
        group = new ToggleGroup();
        radio1 = new RadioButton("radio 1");
        radio2 = new RadioButton("radio 2");
        radio3 = new RadioButton("radio 3");
        radio4 = new RadioButton("radio 4");
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio4.setToggleGroup(group);
        
        Button button = new Button("Next");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BASELINE_CENTER);
        hbBtn.getChildren().add(button);
        root.add(button,0,6);
        
        final Text actiontarget = new Text();
        root.add(actiontarget,0,7);
        
        ArrayList<String> options = model.getAnswers();
        if(options.size() > 2){
            radio1.setText(options.get(0));
            radio2.setText(options.get(1));
            radio3.setText(options.get(2));
            radio4.setText(options.get(3));
            root.add(radio1,0,2);
            root.add(radio2,0,3);
            root.add(radio3,0,4);
            root.add(radio4,0,5);
        }else{
            radio1.setText(options.get(0));
            radio2.setText(options.get(1));
            root.add(radio1,0,2);
            root.add(radio2,0,3);
        }
        
        button.setOnAction(event ->
        {
            boolean selected = false;
            if (radio1.isSelected()){
                model.checkAnswer(radio1.getText());
                selected = true;
            }else if(radio2.isSelected()){
                model.checkAnswer(radio2.getText());
                selected = true;
            }else if(radio3.isSelected()){
                model.checkAnswer(radio3.getText());
                selected = true;
            }else if(radio4.isSelected()){
                model.checkAnswer(radio4.getText());
                selected = true;
            }else{
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Please select an option!");
            }
            
            if(selected){
                if(model.hasNext()){
                    QuestionScene qscene = new QuestionScene(model);
                    MainScene.setSecondaryScene(qscene.getScene(), "Question");
                }else{
                    model.saveScore();
                    ResultScene resultScene = new ResultScene(model);
                    MainScene.setSecondaryScene(resultScene.getScene(), "Result");
                }
            }

        });
        
    }
    
    public Scene getScene(){
        return scene;
    }
    
}
