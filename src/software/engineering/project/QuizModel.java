/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

/**
 *
 * @author ASUS
 */
public class QuizModel {
    
    JSONArray categories;
    

    public QuizModel() {
        
        try {
            
            InputStream is = new URL("https://opentdb.com/api_category.php").openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            categories = json.getJSONArray("trivia_categories");
            
        }
        catch(IOException  ioe) {
            System.out.println("IO problem");
        }
        
    }
    
    public ArrayList getCategories(){
        ArrayList<String> categoriesList = new ArrayList<>();
        for(int i = 0; i < categories.length(); i++) {
                
                JSONObject category = categories.getJSONObject(i);
                
                categoriesList.add(category.get("name").toString());
            }
        return categoriesList;
    }
    
    public Quiz getQuiz(String category, String difficulty){
        String categoryId = "9";
        for(int i = 0; i < categories.length(); i++) {
                
            JSONObject categoryj = categories.getJSONObject(i);
            if(category.equals(categoryj.get("name"))){
                categoryId = categoryj.get("id").toString();
            }
                    
        }
        String multipleString = "https://opentdb.com/api.php?amount=5&category=" + categoryId + "&difficulty=" + difficulty + "&type=multiple ";
        String trueFalseString = "https://opentdb.com/api.php?amount=5&category=" + categoryId + "&difficulty=" + difficulty + "&type=boolean";
        
        ArrayList<Question> questions = new ArrayList<>();
        try {
            
            InputStream is = new URL(multipleString).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            JSONArray questionsj = json.getJSONArray("results");
            for(int i = 0; i < questionsj.length(); i++) {
                
                JSONObject questionj = questionsj.getJSONObject(i);
                JSONArray waj = questionj.getJSONArray("incorrect_answers");
                ArrayList<String> wa= new ArrayList<>();
                for(int j = 0; j < waj.length(); j++) {
                    wa.add(Jsoup.parse(waj.get(j).toString()).text());
                }
                Question question = new Question(Jsoup.parse(questionj.get("question").toString()).text(), Jsoup.parse(questionj.get("correct_answer").toString()).text(), wa);
                questions.add(question);
                
            }
            
        }
        catch(IOException  ioe) {
            System.out.println("IO problem");
        }
        
        try {
            
            InputStream is = new URL(trueFalseString).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            JSONArray questionsj = json.getJSONArray("results");
            for(int i = 0; i < questionsj.length(); i++) {
                
                JSONObject questionj = questionsj.getJSONObject(i);
                JSONArray waj = questionj.getJSONArray("incorrect_answers");
                ArrayList<String> wa= new ArrayList<>();
                for(int j = 0; j < waj.length(); j++) {
                    wa.add(waj.get(j).toString());
                }
                Question question = new Question(Jsoup.parse(questionj.get("question").toString()).text(), questionj.get("correct_answer").toString(), wa);
                questions.add(question);
                
            }
            
        }
        catch(IOException  ioe) {
            System.out.println("IO problem");
        }
        
        Quiz quiz = new Quiz(category, difficulty, questions);
        return quiz;
                
    }
    
}
