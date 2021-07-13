
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.rowset.CachedRowSet;



/**
 * 
 *  File: WordCount.java
 *  Description: strips the html out of the website
 *               counts the ocurrances of each word
 *               lists the top 20 most words in the url
 *
 *   @author: Joanna Smith
 *   @version  3.0 
 */
public class WordCount {
 /** 
* Once the validation is done on the provided url, the program will read the file
* <li>strip out html and special characters,</li>
* <li>collect all of the words in the file in an array,</li>
* <li> Count the number of words in the array</li>
* <li> Sort the array by the highest count</li>
* <li> Display the top 20 words to output in order by top word count</li>
* <li> Write the top 20 words to a file.</li>
*
* @param website   the url used for reading the words in the file
* @param x         the x-coordinate of the northwest corner
*                  of the destination rectangle in pixels
* @param y         the y-coordinate of the northwest corner
*                  of the destination rectangle in pixels
* @param observer  the image observer to be notified as more
*                  of the image is converted.  May be 
*                  <code>null</code>
* @return          <code>true</code> if the image is completely 
*                  loaded and was painted successfully; 
*                  <code>false</code> otherwise.
* @see             Validation
* @see             WordCount
* @since           1.0
*/

    private static String strWebsite="";
    private int cnt = 0;
    public String strResults="";

    WordCount(String website) {
/** 
* Once the validation is done on the provided url, 
* the New() constructor requires a website parameter.
* The constructor sets the private variable to be used
* in the CountWords method
*
* @param website   the url used for reading the words in the file
* @see             CountWords
* @since           1.0
*/
        strWebsite = website;
    }

    /** 
* Once the validation is done on the provided url, the program will read the file
* <ul>
* <li> Uses a <code>BufferedReader</code> to read the file.
* <li> Uses <code>reader.readLine</code> to read each line of the file.
* <li> Uses <code>readLine.replaceAll</code> to strip out html and special characters,
* <li> Use <code>HashMap </code> to collect all of the words in the file to an array,
* <li> Count the number of words in the array
* <li> Use <code>Map </code> to Sort the array by the highest count
* <li> Display the top 20 words to output in order by top word count
* <li> Use <code>BufferedWriter</code> to write the top 20 words to a file.
* </ul>
*
* @return          <code>totalWordCount</code> integer the total number of
*                  words found in the file
* @since           1.0
*/
    public int CountWords(){

        int totalWordCount=0;
        int wordCount=0;
        try{
          //int wordCount = 0; //start with 0 count
        //location of the poem
        
        URL url = new URL(strWebsite);
        //set the file up so it can be read
        System.out.println("Open file and read the contents.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String readFile="";
        String readLine;
        int startFile=0;
        
            
        System.out.println("ommit special characters ");
        System.out.println("Find the beginning of the poem");
        System.out.println("find the end of the poem ");
        while((readLine=reader.readLine()) !=null){
        //only keep the regular characters - ommit special characters    

            readLine = readLine.replaceAll("\\<.*?\\>", "");
            readLine = readLine.replaceAll("&mdash;","");
            readLine = readLine.replaceAll("[^a-zA-Z0-9]"," ");
            
            //the start of the actual poem
            if(strWebsite.contains("gutenberg")){
            if(readLine.contains("Once upon a midnight drear"))
                startFile=1;
            //the end of the poem     
            if(readLine.contains("END OF THE PROJECT"))
                startFile=0;
            //if it is after the start and before the end
            //add the line to the file
            if(startFile !=0)
                    readFile+=readLine;
            }
            else{
                readFile+=readLine;
            }
        }
        
        System.out.println("close reader ");
        reader.close();
        
        System.out.println("separate the individual words");
        String[] words =  readFile.split(" ");
        String[] words2 =  readFile.split(" ");
        String thisWord="";
        DA da = new DA();
        //first delete the words that are in the table
        da.deleteWords();
        
        //loop through each word in the array
        System.out.println("loop through each word, save to db");
        for(int i=0; i<words.length; i++){
               thisWord = words[i];
               if(thisWord.length()>1) {
                   thisWord=thisWord.toLowerCase();
                   //save each instance of the word in the database
                   da.saveWordCount(thisWord,1);
               }  
        }
        
        //write to a file
         BufferedWriter out = new BufferedWriter(new FileWriter("Words.txt"));
         try{
          
        System.out.println("get the top 20 results from the db");
        CachedRowSet rowset =  da.getWords(true);
        System.out.println("Resultset length: " + rowset.size() );
        //loop through each row in the resultset

        while (rowset.next()) {
            String  rsWord= rowset.getString("word");
            int rsCount=rowset.getInt("sum_count");
            System.out.println(rsWord + ": " + rsCount );
            strResults +=rsWord + ": " + rsCount + System.lineSeparator();
        }  
        //grab the total word count from the database
        totalWordCount = da.getWordCount();
         }
         catch(Exception e){
             
          System.out.println("Error: " + e.getMessage());
         }
        //close the file
         out.close();
      }
      catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
      }
                
        System.out.println("Finished!");
        //System.exit(0);
    
   
        return totalWordCount;
}
}

