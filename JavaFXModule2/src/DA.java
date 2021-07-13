
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

    /** 
* Data access layer
* uses MySQLAccess layer to connect to the database
* this layer is the bridge between the business layer
* and the sql layer
* <ul>
* <li> saveWordCount
* <li> getWordCount
* <li> deleteWords
* <li> getWords
* <li> getWordCount
* </ul>
* @author: Joanna Smith
* @version    4.0
* @see             Class MySQLAccess
*/
public class DA {
  /** 
* saveWordCount
* inserts one word found from the selected file
* into the database with a count of one
* 
* @param word the word from the file
* @param wordCount count=1
* 
* @author: Joanna Smith
* @version    4.0
*/
    public void saveWordCount(String word, int wordCount){
           try{
                MySQLAccess mySQL = new MySQLAccess();
                String query = "call insertWord  (?, ?)";
                List<String> params = new ArrayList<>();
                     params.add(String.valueOf(word));
                     params.add(String.valueOf(wordCount));
                mySQL.execWithParameters(query, params);
           }
           catch(Exception e)
           {
               System.out.println(e.getMessage());}
           
           }
     /** 
* getWordCount
* gets the number of times the word is found 
* in the database
* 
* @param word the word from the file
* 
* @author: Joanna Smith
* @version    4.0
*/
    public void getWordCount(String word){
           try{
                MySQLAccess mySQL = new MySQLAccess();
                String query = "Call getWord  (?)";
                List<String> params = new ArrayList<>();
                     params.add(String.valueOf(word));
                mySQL.execResultSet(query, params);
           }
           catch(Exception e)
           {
               System.out.println(e.getMessage());}
           
           }
    /** 
* deleteWords
* deletes all the words out of the table 
* in the database before inserting new words
* 
* 
* @author: Joanna Smith
* @version    4.0
*/
    public void deleteWords(){
        
           try{
                MySQLAccess mySQL = new MySQLAccess();
                String query = "call deleteWords";
                mySQL.execSP(query);
           }
           catch(Exception e){               
               System.out.println(e.getMessage());
           }
    }
    /** 
* getWords
* pulls each word in the database
* and its associated word count
* in descending order of word count
* may also show only the top 20 words
* 
* @param top20  a boolean parameter to indicate if the top 20 count is wanted
* @author: Joanna Smith
* @version    4.0
*/
    public CachedRowSet getWords(boolean top20)throws Exception{

        MySQLAccess mySQL = new MySQLAccess();
        String query = "call getWords (?)";
        List<String> params = new ArrayList<>();
             params.add("true");
        CachedRowSet resultSet =mySQL.execResultSet(query, params);

        return resultSet;
 

    } /** 
* getWordCount
* grabs the total number of words found in the file

* 
* @author: Joanna Smith
* @version    4.0
*/
    public int getWordCount()throws Exception{

        MySQLAccess mySQL = new MySQLAccess();
        String query = "call getWordCount";
        CachedRowSet resultSet =mySQL.execResults(query);
        int wordCount = resultSet.getInt("sum_count");
        return wordCount;
    }  
}