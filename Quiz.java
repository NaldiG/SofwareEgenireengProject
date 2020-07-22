/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Quiz {
    String category, difficulty;
    ArrayList<Question> questions;
    int score = 0;

    public Quiz(String category, String difficulty, ArrayList<Question> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String getQuestion(int inedx){
        return questions.get(inedx).getQuestion();
    }
    
    public boolean isMultiple(int index){
        if(questions.get(index).getWrongAnswer().size() > 1){
            return true;
        }else{
            return false;
        }
    }
    
    public String getRightAnswer(int index){
        return questions.get(index).getRightAnswer();
    }
    
    public ArrayList<String> getWrongAsnwers(int index){
        return questions.get(index).getWrongAnswer();
    }
}
