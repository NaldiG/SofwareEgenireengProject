/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author ASUS
 */
public class PastScoresScene {
    Scene scene;
    GridPane root;
    private TableView<Quiz> table = new TableView<Quiz>();
    private ObservableList<Quiz> scores;
    

    public PastScoresScene(int userId) {
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        DBConn conn = new DBConn();
        scores = conn.getPastScores(userId);
        
        Label label = new Label("The list of completed quizes");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn catCol = new TableColumn("Category");
        catCol.setMinWidth(100);
        catCol.setCellValueFactory(
                new PropertyValueFactory<Quiz, String>("category"));
 
        TableColumn diffCol = new TableColumn("Difficulty");
        diffCol.setMinWidth(100);
        diffCol.setCellValueFactory(
                new PropertyValueFactory<Quiz, String>("difficulty"));
 
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setMinWidth(200);
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<Quiz, Integer>("score"));
        
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(200);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Quiz, String>("date"));
 
        table.setItems(scores);
        table.getColumns().addAll(catCol, diffCol, scoreCol, dateCol);
        
        root.add(label, 0, 0);
        root.add(table, 0, 1);
        
        double avg = 0;
        for(int i = 0; i < scores.size(); i++){
            avg += scores.get(i).getScore();
        }
        avg = avg/scores.size();
        
        Label labe2 = new Label("Games played today: " + Integer.toString(conn.gamesPlayedToday(userId)));
        Label labe3 = new Label("Prefered category: " + conn.prefferedCategory(userId));
        Label labe4 = new Label("Average score" + Double.toString(avg));
        root.add(labe2, 0, 2);
        root.add(labe3, 0, 3);
        root.add(labe4, 0, 4);
        
        
        
    }
    
     public Scene getScene(){
        return scene;
    }
    
}
