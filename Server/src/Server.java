/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

    /** 
* Creates a JavaFX form
* that displays the date/time the service was started
* Expects an input from the client for the
* website to pull the words from
* <p>
* Once the input is received from the client
* <ul>
* <li> the program will read the file
* <li> strip out html and special characters,
* <li> collect all of the words in the file and 
* <li> save them each to a mySQL database
* <li> Count the number of words in the database
* <li> The database will sort the words by the highest count
* <li> and send the results back to the client
* </ul>
* @author: Joanna Smith
* @version    4.0
* @see             Class DA
* @see             Class WordCount
* @see             Class MySQLAccess
*/
public class Server extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Text area for displaying contents
    TextArea ta = new TextArea();

    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
    primaryStage.setTitle("Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread( () -> {
      try {
        // Create a server socket
        ServerSocket serverSocket = new ServerSocket(8000);
        Platform.runLater(() ->
          ta.appendText("Server started at " + new Date() + '\n'));
  
        // Listen for a connection request
        Socket socket = serverSocket.accept();
  
        // Create data input and output streams
        DataInputStream inputFromClient = new DataInputStream(
          socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(
          socket.getOutputStream());
  
        //while (true) {
          // Receive website address from the client
          String strWebsite = inputFromClient.readUTF();
  
          // Count the words
          WordCount myCount = new WordCount(strWebsite);
          
          //grab the top 20 words 
          myCount.CountWords();
          String strResults = myCount.strResults;
  
          // Send area back to the client
          outputToClient.writeUTF(strResults);
  
          Platform.runLater(() -> {
            ta.appendText("Website provided by the client : " 
              + strWebsite + '\n'); 
          });
        //}
      }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }).start();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
* @param   args arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  }


