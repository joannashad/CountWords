
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class WordCount {
    private static String strWebsite="";
    private int cnt = 0;
    public String strResults="";

    WordCount(String website) {
        strWebsite = website;
    }

    
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
        
        
        HashMap<String, Integer> listOfWords = new HashMap<String, Integer>();
        
        System.out.println("separate the individual words");
        String[] words =  readFile.split(" ");
        String[] words2 =  readFile.split(" ");
        String thisWord="";
        //loop through each word in the array
        
        System.out.println("loop through each word, create array");
        for(int i=0; i<words.length; i++){
               thisWord = words[i];
               if(thisWord.length()>1) {
                   thisWord=thisWord.toLowerCase();
                   //loop through the array again to count the words
                   
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
         
        totalWordCount = listOfWords.size();
        
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
             strResults+=entry.getKey() + ": " + entry.getValue() + System.lineSeparator();
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
        //System.exit(0);
    
   
        return totalWordCount;
}
}
