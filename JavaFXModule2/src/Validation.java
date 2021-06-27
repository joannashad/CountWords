
import java.net.URL;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class Validation {
    public Validation(){
        
    }
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
