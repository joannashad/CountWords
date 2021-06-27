

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
        String website2="https://en.wikipedia.org/wiki/Wyoming";
        String website3=" ";
        String website4=null;
        String website5="https://";
        String website6="https://en.wikipedia.org/wiki/Botanical_garden";
        
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
        result = instance.checkLength(website6);
        assertEquals(expResult, result);    
    }

    /**
     * Test of checkValid method, of class Validation.
     */
    @Test
    public void testCheckValid() {
        String website="https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";                
        String website2="https://en.wikipedia.org/wiki/Wyoming";
        String website3=" ";
        String website4=null;
        String website5="https://";
        String website6="https://en.wikipedia.org/wiki/Botanical_garden";
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
        result = instance.checkLength(website6);
        assertEquals(expResult, result);
    }

    
}
