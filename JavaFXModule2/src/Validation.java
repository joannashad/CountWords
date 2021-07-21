
import java.net.URL;




/*
 * Validation for fields on the form
 */


/**
 *  File: Validation.java
 *  Description: checks the length of the value from the form
 *               verifies it is not null
 *               verifies it is a valid url
 *               returns error message to be passed back to the form
 * 
 * 
 *  Author: Joanna Smith
 */
public class Validation {
    public Validation(){
        
    }
    /** 
* Check if the website/url is null or blank
*
* @param            website     value provided from the <code>texbox</code> on the form  
*                   
* @return          blank if there is no error, error message if there is an error
* @see             checkValid
* @since           1.0
*/
    public String checkLength(String website){
        try{
        if(website !=null)
            website=website.trim();
        if(website.length() == 0)
            return "Please provide a website address." ;
        else
            return "";
        }
        catch(Exception e){
            return "Please provide a website address.";
        }
    }   
    /**
* Check if the website/url is a valid url
*
* @param            website   value provided from the <code>texbox</code> on the form 
*                  
* @return          blank if there is no error, error message if there is an error
* @see             checkLength
* @see             isValid
* @since           1.0
*/
    public String checkValid(String website){
        try{
        if(website !=null)
            website=website.trim();
        if(!isValid(website))
            return "Website entered is not valid." ;
        else
            return "";
        }
        catch(Exception e){
            return "Website entered is not valid." ;
            
        }
            
    }
    /**           
* Check if the website/url is a valid url
*
* @author: Joanna Smith
* @version  3.0
* @param            url   value provided from the <code>texbox</code> on the form 
*                  
* @return          returns true if the website is a valid url
*                  returns false if not valid
* @see             checkValid
* @see             checkLength
*/
         /* Returns true if url is valid */
    public boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
          
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    
    }
}
