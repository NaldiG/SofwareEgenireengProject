/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.engineering.project;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class QuestionModelTest {
    Quiz quiz;
    public QuestionModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<String> wa = new ArrayList<>();
        wa.add("wrong answer 1");
        wa.add("wrong answer 2");
        wa.add("wrong answer 3");
        Question question = new Question("Random question", "right answer", wa);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);
        quiz = new Quiz("Rondom category", "Random difficulty", questions);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetQuestionNumber() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        String expResult = "1/10";
        String result = instance.getQuestionNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetQuestion() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        String expResult = "Random question";
        String result = instance.getQuestion();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAnswers() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("wrong answer 2");
        expResult.add("wrong answer 1");
        expResult.add("wrong answer 3");
        expResult.add("right answer");
        ArrayList<String> result = instance.getAnswers();
        for(int i = 0; i < result.size(); i++){
            assertEquals(true, expResult.contains(result.get(i)));
        }
        
    }

    @Test
    public void testCheckAnswer() {
        String answer = "right answer";
        QuestionModel instance = new QuestionModel(quiz, 1);
        Boolean expResult = true;
        Boolean result = instance.checkAnswer(answer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckAnswer2() {
        String answer = "wrong answer";
        QuestionModel instance = new QuestionModel(quiz, 1);
        Boolean expResult = false;
        Boolean result = instance.checkAnswer(answer);
        assertEquals(expResult, result);
    }

    @Test
    public void testHasNext() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        boolean expResult = false;
        instance.checkAnswer("random answer");
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetScore() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        String expResult = "0/10";
        String result = instance.getScore();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetScore2() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        instance.checkAnswer("right answer");
        String expResult = "1/10";
        String result = instance.getScore();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetScore3() {
        QuestionModel instance = new QuestionModel(quiz, 1);
        instance.checkAnswer("wrong answer");
        String expResult = "0/10";
        String result = instance.getScore();
        assertEquals(expResult, result);
    }
    
}
