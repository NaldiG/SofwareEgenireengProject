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
public class Question {
    String question, rightAnswer;
    ArrayList<String> wrongAnswer;

    public Question(String question, String rightAnswer, ArrayList<String> wrongAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer = wrongAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public ArrayList<String> getWrongAnswer() {
        return wrongAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setWrongAnswer(ArrayList<String> wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
    
    
}
