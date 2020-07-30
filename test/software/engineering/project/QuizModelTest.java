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
public class QuizModelTest {
    
    public QuizModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCategories() {
        QuizModel instance = new QuizModel();
        int expResult = 24;
        ArrayList result = instance.getCategories();
        assertEquals(expResult, result.size());
    }

    @Test
    public void testGetQuiz() {
        QuizModel instance = new QuizModel();
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(5);
        expResult.add(10);
        ArrayList<String> categories = instance.getCategories();
        for(int i = 0; i < categories.size(); i++){
            for(int j = 0; j < 3; j++){
                String category = categories.get(i);
                String difficulty = "easy";
                if(j == 1){
                    difficulty = "medium";
                }else if (j == 2){
                    difficulty = "hard";
                }
                Quiz result = instance.getQuiz(category, difficulty);
                System.out.println(result.getQuestions().size() + " " + category + " " + difficulty);
                if(!category.equals("Science: Gadgets") || !difficulty.equals("hard")){
                    assertEquals(true, expResult.contains(result.getQuestions().size()));
                }
                
            }
        }
        
    }
    
}
