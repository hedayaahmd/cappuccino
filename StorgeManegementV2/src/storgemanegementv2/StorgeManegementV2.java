/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storgemanegementv2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class StorgeManegementV2 extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Frames/LoginFrame.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/Frames/IconsStyling.css");
//        scene.getStylesheets().add(getClass().getResource("IconsStyling.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
