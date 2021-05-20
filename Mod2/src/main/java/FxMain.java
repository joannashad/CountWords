/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author joann
 */
public class FxMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Count Words");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                CountWords();
                
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Count Words");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static void CountWords(){
        try{
            int wordCount = 0; //start with 0 count
        //location of the poem
        File file = new File("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
        //set the file up so it can be read
        FileInputStream fileIS = new FileInputStream(file);
        byte[] myArray = new byte[(int)file.length()];
        //read the file
        fileIS.read(myArray);
        //put the file into a string
        String newStr = new String(myArray);
        //only keep the regular characters - ommit special characters
        newStr=newStr.replaceAll("[^a-xA-Z0-9]"," ");
        //split the string into words by spaces
        String[] words =  newStr.split(" ");
        String[] words2 = newStr.split(" ");
        for(int i=0; i<words.length; i++){
                String thisWord = words[i];
                for (String words21 : words2) {
                    if (thisWord == words21) {
                        wordCount++;
                    }
                }
                System.out.println(thisWord + " " + wordCount);
                wordCount=0;

            }
        }
        catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
        
