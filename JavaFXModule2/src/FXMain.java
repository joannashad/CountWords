/*
*  Opens the poem, The Raven
*  https://www.gutenberg.org/files/1065/1065-h/1065-h.htm
*  Reads each line, removes html tags and special characters
*  Splits the text into individual words
*  Counts how many times the word appears in the poem
*  Sorts the words in descending order of word count
*  Writes the top 20 resulsts to words.txt file
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author joanna
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        CountWords();

    }

    public static void main(String[] args) {
        launch(args);
    }
  
    public static void CountWords(){
        try{
            int wordCount = 0; //start with 0 count
        //location of the poem
        URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
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
        
        System.out.println("close reader ");
        reader.close();
        
        
        HashMap<String, Integer> listOfWords = new HashMap<String, Integer>();
        
        System.out.println("separate the individual words");
        String[] words =  readFile.split(" ");
        String[] words2 =  readFile.split(" ");
        String thisWord="";
        //loop through each word in the array
        
        System.out.println("loop through each word, create array");
        for(int i=0; i<words.length; i++){
               thisWord = words[i];
               if(thisWord.length()>0) {
                   //lop through the array again to count the words
                   
                 for(int a=0; a<words2.length; a++){
                    if(words2[a].length()>0){
                    String thatWord = words2[a].toString();
                    //count the number of words
                    if (thisWord.equals(thatWord)) {
                        wordCount++;
                    }
                    }
                }
                 //put the word and count in a HashMap
                 if(!listOfWords.containsKey(thisWord)){
                   listOfWords.put((String) thisWord, wordCount);  
                 }
                 
                wordCount=0;
               }
        }
         
        
        System.out.println("sort");
        
        //use a LinkedHashMap to sort the words in descending order by count
       try {
        Map<String, Integer> sortedMap = listOfWords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        
        sortedMap.putAll(listOfWords);
        //write to a file
         BufferedWriter out = new BufferedWriter(new FileWriter("Words.txt"));
        
        int i=0;
        //loop through each entry
         for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
             if(i<20){
             out.write(entry.getKey() + " : " + entry.getValue() + System.lineSeparator());
             System.out.println(entry.getKey() + ": " + entry.getValue());
             i++;
             }
         }
        //close the file
         out.close();
         System.out.println("File created successfully");
      }
      catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
      }
        
            
        }
        catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
        
        System.out.println("Finished!");
        System.exit(0);
    }
}
      
