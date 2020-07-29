/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import javafx.scene.image.Image;

/**
 *
 * @author ASUS
 */
public class User {
    private int id, subscription, role;
    private String username, password, quote;
    private Image avatar;

    public Image getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public int getSubscription() {
        return subscription;
    }

    public int getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQuote() {
        if(quote != null){
            return quote;
        }else{
            return "";
        }
        
    }

    public User(int id, int subscription, int role, String username, String password, String quote, Image avatar) {
        this.id = id;
        this.subscription = subscription;
        this.role = role;
        this.username = username;
        this.password = password;
        this.quote = quote;
        this.avatar = avatar;
    }
    
    public String getSubscriptionName(){
        if (subscription == 2){
            return "Premium Subscription";
        }else{
            return "Free Subscription";
        }
    }
    
}
