/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class ProfileScene {
    Scene scene;
    GridPane root;
    ImageView imageView;
    Button buttonQuoteConfirm = new Button("Confirm change");
    Button buttonAvatarConfirm = new Button("Confirm change");
    Label label = new Label("New quote: ");
    TextField quote = new TextField();

    public ProfileScene(User user) {
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        scene = new Scene(root, 600, 400);
        
        imageView = new ImageView();
        imageView.setImage(user.getAvatar());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50 * (user.getAvatar().getHeight() / user.getAvatar().getWidth()));
        
        HBox hbImage = new HBox(10);
        hbImage.setAlignment(Pos.CENTER);
        hbImage.getChildren().add(imageView);
        root.add(hbImage,0,0,2,1);
        
        Label label1 = new Label("Name: ");
        root.add(label1,0,1);
        Label labelName = new Label(user.getUsername());
        root.add(labelName,1,1);
        Label label2 = new Label("Subscription: ");
        root.add(label2,0,2);
        Label labelSubscription = new Label(user.getSubscriptionName());
        root.add(labelSubscription,1,2);
        Label label3 = new Label("Quote: ");
        root.add(label3,0,3);
        Label labelQuote = new Label(user.getQuote());
        root.add(labelQuote,1,3);
        
        Button buttonQuote = new Button("Change quote");
        root.add(buttonQuote,0,4);
        Button buttonAvatar = new Button("Change avatar");
        root.add(buttonAvatar,1,4);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(buttonQuoteConfirm);
        
        final Text actiontarget = new Text();
        root.add(actiontarget,0,7);
        
        buttonQuote.setOnAction(event ->
        {
            root.add(label,0,5);
            root.add(quote,1,5);
            root.add(hbBtn,0,6,2,1);
        });
        
        buttonQuoteConfirm.setOnAction(event ->
        {
            root.getChildren().remove(label);
            root.getChildren().remove(quote);
            root.getChildren().remove(hbBtn);
            DBConn conn = new DBConn();
            conn.changeQuote(quote.getText(), user.getId());
            labelQuote.setText(quote.getText());
        });
        
        buttonAvatar.setOnAction(event ->
        {
            FileInputStream input = null;
                try {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                    fileChooser.getExtensionFilters().add(extFilter);
                    Stage secondaryStage = new Stage();
                    File file = fileChooser.showOpenDialog(secondaryStage);
                    //System.out.println("File size: " + file.length());
                    input = new FileInputStream(file);
                    //System.out.println(file);
                    DBConn conn = new DBConn();
                    conn.changeAvatar(input, user.getId());
                    imageView.setImage(conn.getUser(user.getUsername(), user.getPassword()).getAvatar());
                     
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProfileScene.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("No file was selected");
                } finally {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ProfileScene.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NullPointerException ex) {
                        
                    }
                }
        });
        
    }
    
    public Scene getScene(){
        return scene;
    }
    
    
}
