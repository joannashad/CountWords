
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * 
 *  File: WordCountTest.java
 *  Description: checks the WordCount method from WordCount.java
 *               to verify it counts the number of words in the url
 * 
 * 
 *   @author: Joanna Smith
 *   @version  3.0 
 */
public class WordCountTest {
    
    public WordCountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of CountWords method, of class WordCount.
     */
    @Test
    public void testCountWords() {
        String website = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
        String website2="https://en.wikipedia.org/wiki/Wyoming";
        String website3="https://en.wikipedia.org/wiki/Botanical_garden";
        System.out.println("CountWords");
        WordCount instance = new WordCount(website);
        int expResult = 441;
        int result = instance.CountWords();
        assertEquals(expResult, result);
        
        WordCount instance2 = new WordCount(website2);
        int expResult2 = 3686;
        int result2 = instance2.CountWords();
        assertEquals(expResult2, result2);
        
        WordCount instance3 = new WordCount(website3);
        int expResult3 = 2849;
        int result3 = instance3.CountWords();
        assertEquals(expResult3, result3);
        
    }
    
}
