/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.text.DecimalFormat;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author ASUS
 */
public class RankingScene {
    Scene scene;
    GridPane root;
    private TableView<User> table = new TableView<User>();
    private ObservableList<User> users;
    

    public RankingScene(String username) {
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        DBConn conn = new DBConn();
        users = conn.getRankings();
        
        Label label = new Label("The rankings of current users");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn rankCol = new TableColumn("Rank");
        rankCol.setMinWidth(100);
        rankCol.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("rank"));
 
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("username"));
 
        TableColumn scoreCol = new TableColumn("Total Score");
        scoreCol.setMinWidth(200);
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("score"));
        
        table.setItems(users);
        table.getColumns().addAll(rankCol, nameCol, scoreCol);
        
        int userRank = 0;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                userRank = users.get(i).getRank();
            }
        }
        Label label2 = new Label();
        if(userRank == 0){
            label2.setText("Your rank is: unranked");
        }else{
            label2.setText("Your rank is: " + userRank);
        }
        
        
        root.add(label, 0, 0);
        root.add(table, 0, 1);
        root.add(label2, 0, 3);
        
    }
    
     public Scene getScene(){
        return scene;
    }
    
}
