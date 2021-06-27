

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joann
 */
public class ValidationTest {
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of checkLength method, of class Validation.
     */
    @Test
    public void testCheckLength() {
        System.out.println("checkLength");
        String website="https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
        String website2="https://www.sportingnews.com/us/athletics/news/tour-de-france-2021-fan-sign-crash-allez-opi-omi/r230uew3fe7n15v3eyfbd353o";
        String website3=" ";
        String website4=null;
        String website5="https://";
        
        Validation instance = new Validation();
        String expResult = "";
        String result = instance.checkLength(website);
        assertEquals(expResult, result);   
        result = instance.checkLength(website2);
        assertEquals(expResult, result);  
        result = instance.checkLength(website3.trim());
        assertFalse(result.length()==0);      
        result = instance.checkLength(website4);
        assertFalse(result.length()==0); 
        result = instance.checkLength(website5);
        assertTrue(result.length()==0);  
    }

    /**
     * Test of checkValid method, of class Validation.
     */
    @Test
    public void testCheckValid() {
        String website="https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";        
        String website2="https://www.sportingnews.com/us/athletics/news/tour-de-france-2021-fan-sign-crash-allez-opi-omi/r230uew3fe7n15v3eyfbd353o";
        String website3=" ";
        String website4=null;
        String website5="https://";
        System.out.println("checkValid");
        Validation instance = new Validation();
        String expResult = "";
        String result = instance.checkValid(website);
        assertEquals(expResult, result);
        result = instance.checkValid(website2);
        assertEquals(expResult, result);
        result = instance.checkValid(website3.trim());
        assertFalse(result.length()==0);
        result = instance.checkValid(website4);
        assertFalse(result.length()==0);
        result = instance.checkValid(website5);
        assertFalse(result.length()==0);
    }

    
}
