/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class QuestionModel {
    
    static Quiz quiz;
    static int index = 0;
    int userId;

    public QuestionModel(Quiz quiz, int userId) {
        this.quiz = quiz;
        this.userId = userId;
        index = 0;
    }
    
    public String getQuestionNumber(){
        int output = 1 + index;
        return "" + output + "/10";
    }
    
    public String getQuestion(){
        return quiz.getQuestion(index);
    }
    
    public ArrayList<String> getAnswers(){
        if(quiz.isMultiple(index)){
            Random random = new Random();
            int number = random.nextInt(4);
            if(number == 1){
                return arangeAnswersMultiple1();
            }else if(number == 2){
                return arangeAnswersMultiple2();
            }else if(number == 3){
                return arangeAnswersMultiple3();
            }else{
                return arangeAnswersMultiple4();
            }
        }else{
            ArrayList<String> output = new ArrayList<String>();
            output.add("True");
            output.add("False");
            return output;
        }
    }
    
    private ArrayList<String> arangeAnswersMultiple1(){
        ArrayList<String> output = new ArrayList<String>();
        output.add(quiz.getRightAnswer(index));
        ArrayList<String> wrongAnswers = quiz.getWrongAsnwers(index);
        output.add(wrongAnswers.get(0));
        output.add(wrongAnswers.get(1));
        output.add(wrongAnswers.get(2));
        return output;
    }
    private ArrayList<String> arangeAnswersMultiple2(){
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> wrongAnswers = quiz.getWrongAsnwers(index);
        output.add(wrongAnswers.get(0));
        output.add(quiz.getRightAnswer(index));
        output.add(wrongAnswers.get(1));
        output.add(wrongAnswers.get(2));
        return output;
    }
    private ArrayList<String> arangeAnswersMultiple3(){
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> wrongAnswers = quiz.getWrongAsnwers(index);
        output.add(wrongAnswers.get(0));
        output.add(wrongAnswers.get(1));
        output.add(quiz.getRightAnswer(index));
        output.add(wrongAnswers.get(2));
        return output;
    }
    private ArrayList<String> arangeAnswersMultiple4(){
        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> wrongAnswers = quiz.getWrongAsnwers(index);
        output.add(wrongAnswers.get(0));
        output.add(wrongAnswers.get(1));
        output.add(wrongAnswers.get(2));
        output.add(quiz.getRightAnswer(index));
        return output;
    }
    
    public boolean checkAnswer(String answer){
        if(answer.equals(quiz.getRightAnswer(index))){
            quiz.setScore(quiz.getScore() + 1);
            index ++;
            return true;
        }else{
            index ++;
            return false;
        }
    }
    
    public boolean hasNext(){
        if (index < quiz.getQuestions().size()){
            return true;
        }else{
            return false;
        }
    }
    
    public String getScore(){
        return "" + quiz.getScore() + "/10";
    }
    
    public void saveScore(){
        DBConn con = new DBConn();
        con.insertScore(quiz.getScore(), userId, quiz.getCategory(), quiz.getDifficulty());
    }
}
