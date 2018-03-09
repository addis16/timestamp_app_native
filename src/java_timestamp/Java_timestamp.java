
package java_timestamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Java_timestamp extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        
        GridPane canvas = new GridPane();
       
        Scene scene = new Scene(canvas, 500, 500);
        
        TextField input = new TextField();
        Button submit = new Button("Submit");
        HBox div1 = new HBox(10, input, submit);
        canvas.prefWidthProperty().bind(primaryStage.widthProperty());
        
        canvas.add(input, 0, 0, 1, 1);
        canvas.add(submit, 1, 0, 1, 1);
        
        TreeItem<String> rootItem = new TreeItem<>("Root");
        rootItem.setExpanded(true);
        
        TreeView<String> fileTree = new TreeView<>(rootItem);
        
        canvas.add(fileTree, 0, 2, 1, 1);
        
        ArrayList<String> rows = Database.getAll();
        Iterator<String> itr = rows.iterator();
            while(itr.hasNext()) {
                TreeItem<String> newFile = new TreeItem<>(itr.next());
                rootItem.getChildren().add(newFile);
            }
         
        submit.setOnAction (event -> {

           String filename = input.getText();
           try {
               Database.addFile(filename);
               String last = Database.getLast();
               TreeItem lastNode = new TreeItem();
               lastNode.setValue(last);
               rootItem.getChildren().add(lastNode);
               
           } catch (SQLException ex){
               System.out.println(ex);
           } 
           
        });
        
        
        scene.getStylesheets().add(Java_timestamp.class.getResource("style.css").toExternalForm());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        launch(args);
        
        
    }
}
