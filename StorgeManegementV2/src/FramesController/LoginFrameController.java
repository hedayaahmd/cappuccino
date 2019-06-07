/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import Validate.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginFrameController implements Initializable {
    Validate.Login login_controller ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login_controller=new Login();
        invalidLabel.setVisible(false);
    }    
    
    
    @FXML
    private JFXTextField userName_txt;

    @FXML
    private JFXPasswordField password_txt;

    @FXML
    private JFXButton login_btt;
    
    @FXML
    private Label invalidLabel;

    @FXML
    void login_btt_selected(ActionEvent event) throws IOException {
        try {
            String name =login_controller.validateLogin(userName_txt.getText(), password_txt.getText());
                if(!name.equals("")){
                    
                Stage stage = showSecondWindowDialog(name);
                Node source = (Node) event.getSource();
                Stage stageMain = (Stage) source.getScene().getWindow();
                stageMain.close();
                }else {
                  invalidLabel.setVisible(true);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Stage showSecondWindowDialog(String name) throws IOException, SQLException {

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/Frames/HomePageFrame.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("الصفحةالرئيسية");
        stage.setScene(
                new Scene(
                        (Pane) loader.load()//, visualBounds.getWidth(), visualBounds.getHeight()
                )
        );
        HomePageFrameController controller
                = loader.<HomePageFrameController>getController();
        controller.send(name);
        stage.show();

        return stage;
    }
    
}
