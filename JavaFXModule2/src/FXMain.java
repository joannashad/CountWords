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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author joanna
 */
public class FXMain extends Application {
    private static String strWebsite="";
    private int cnt = 0;
    
    @Override
    public void start(Stage primaryStage) {
                GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        String strWeb = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
    
        StackPane root = new StackPane();
        Label lbl = new Label("Provide a website to count the number of words.");
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        grid.add(lbl, 1, 0); 
        TextField txt = new TextField();
        txt.setText(strWeb);
        txt.setPrefWidth(400);        
        grid.add(txt, 1, 1,2,1);
        
        
        Button btn1 = new Button();
        btn1.setText("Count Words");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Improper input");
                //check there is something in the text bos
                if(txt.getText().length() == 0){
                    alert.setContentText("Please proviide a website address.") ;
                    alert.showAndWait();}
                //check that the website is valid
                else if(!isValid(txt.getText())){
                    alert.setContentText("Website entered is not valid.");
                    alert.showAndWait();}
                else{
                    strWebsite = txt.getText();
                    cnt = CountWords();
                
                    alert.setContentText("There are " + cnt + " words in this passage.");
                    alert.setTitle("Count");
                    alert.setHeaderText("Word Count");
                    alert.showAndWait();
            }}
        });
        
        grid.add(btn1, 1, 2);
        
        Scene scene = new Scene(grid, 500, 200);
        
        primaryStage.setTitle("Word Count application.");
        primaryStage.setScene(scene);
        primaryStage.show();
    


       

    }

    public static void main(String[] args) {
        launch(args);
    }
  
    public static int CountWords(){
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
     /* Returns true if url is valid */
    public static boolean isValid(String url)
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
