/*
 *  File: FXMain.java
 *  Description: 
*  Opens the poem, The Raven
*  https://www.gutenberg.org/files/1065/1065-h/1065-h.htm
*  Reads each line, removes html tags and special characters
*  Splits the text into individual words
*  Counts how many times the word appears in the poem
*  Sorts the words in descending order of word count
*  Writes the top 20 resulsts to words.txt file
 *  Author: Joanna Smith
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

    /** 
* Creates a JavaFX form
* with a <code>textfield</code> to enter a website url
* and a button to perform calculations
* and perform validation on the <code>textfield</code> 
* to check that the url is in the correct format and is not null or empty string
* <p>
* If a value in the <code>textbox</code> is empty or null
* alert will appear indicating a value is needed.
* If a value in the <code>textbox</code> is not in the corr3ect format
* alert will appear indicating the value is not a valid url.
* <p>
* If the value entered is valid, the client will send the website
* address to the server for processing.
* <p> The server will send back a list of the top 20 words
* sorted in descending order by word count
* <p> The client will display the top 20 words to output 
* and write the top 20 words to a file.
* @author: Joanna Smith
* @version    3.0
* @see             Class Validation
*/

public class FXMain extends Application {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    private static String strWebsite="";
    private int cnt = 0;
    private static String strResults="";
    
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
                Label lblResults=new Label();
                strWebsite = txt.getText();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Improper input");
                Validation valid = new Validation();
                String strError1 = valid.checkLength(strWebsite);
                String strError2 = valid.checkValid(strWebsite);

                if(strWebsite !=null)
                    strWebsite=strWebsite.trim();
                //check there is something in the text box
                if(strError1.length()>0){
                    alert.setContentText(strError1) ;
                    alert.showAndWait();}
                //check that the website is valid
                else if(strError2.length()>0){
                    alert.setContentText(strError2);
                    alert.showAndWait();}
                else{
                    WordCount myCount = new WordCount(strWebsite);
                    
                    /*alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Please wait.");
                    alert.setContentText("This may take a minute or two.");
                    alert.show();*/
                    //lblResults.setText("Please wait. \n This may take a minute or two.");
                     
                    cnt = myCount.CountWords();
                    Label lbl2 = new Label("Top 20 words");
                    lbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    grid.add(lbl2,1,3);
                    lblResults=new Label(myCount.strResults);
                    //lblResults=new Label(strResults);
                    lblResults.setWrapText(true);
                    lblResults.setPrefWidth(400);
                    grid.add(lblResults, 1, 4);
                    alert.setContentText("Finished!");
                    alert.setContentText("There are " + cnt + " words in this passage.");
                    alert.setTitle("Count");
                    alert.setHeaderText("Word Count");
                    alert.showAndWait();

            }
            }
        });
        
        grid.add(btn1, 1, 2);
        
        Scene scene = new Scene(grid, 500, 600);
        
        primaryStage.setTitle("Word Count application.");
        primaryStage.setScene(scene);
        primaryStage.show();
    


       

    }

    public static void main(String[] args) {
        launch(args);
    }
  

}      
