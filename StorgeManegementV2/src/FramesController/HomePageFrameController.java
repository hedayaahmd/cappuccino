/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomePageFrameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImages();
        Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/HistoryFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private AnchorPane main_anchorPane;
    @FXML
    private JFXButton user_btt;

    @FXML
    private Hyperlink userName_txt;

    @FXML
    private JFXButton logout_btt;

    @FXML
    private JFXButton sellFrame_btt;

    @FXML
    private JFXButton buyFrame_btt;

    @FXML
    private JFXButton customersFrame_btt;

    @FXML
    private JFXButton marbleTypes_btt;

    @FXML
    private JFXButton payment_btt;

    @FXML
    private JFXButton store_btt;

    @FXML
    private JFXButton report_btt;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;

    @FXML
    void buyFrame_btt_selected(MouseEvent event) {
        System.out.println("***");
        Node node;
        try {
            System.out.println("buy click");
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/BuyingBillFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void customersFrame_btt_selected(MouseEvent event) {
        Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/CustomerFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void logout_btt_selected(MouseEvent event) {
        System.out.println("out");
    }

    @FXML
    void marbleTypes_btt_selected(MouseEvent event) {
        Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/TypeFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void payment_btt_selected(MouseEvent event) {
        Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/PaymentFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void report_btt_selected(MouseEvent event) {

    }

    @FXML
    void sellFrame_btt_selected(MouseEvent event) {
        Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/SellingBillFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void store_btt_selected(MouseEvent event) {

    }

    @FXML
    void userName_txt_selected(MouseEvent event) {
                Node node;
        try {
            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/UserDataFrame.fxml"));
            main_anchorPane.getChildren().setAll(node);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void user_btt_selected(MouseEvent event) {

    }

    public void setBackgroudImage(JFXButton btt, String url) {
        Image backGround = new Image(url);
        BackgroundImage newBgr = new BackgroundImage(backGround, null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        btt.setBackground(new Background(newBgr));

    }

    public void setImages() {
        setBackgroudImage(user_btt, "/Icons/icons8-male-user-96.png");
        setBackgroudImage(logout_btt, "/Icons/icons8-shutdown-48.png");
        setBackgroudImage(sellFrame_btt, "/Icons/icons8-purchase-order-48.png");
        setBackgroudImage(buyFrame_btt, "/Icons/icons8-buying-48.png");
        setBackgroudImage(customersFrame_btt, "/Icons/icons8-queue-48 (1).png");
        setBackgroudImage(marbleTypes_btt, "/Icons/icons8-list-48.png");
        setBackgroudImage(store_btt, "/Icons/icons8-online-store-48.png");
        setBackgroudImage(report_btt, "/Icons/icons8-report-card-48 (1).png");
        setBackgroudImage(payment_btt, "/Icons/icons8-receipt-48.png");
    }

    public void send(String name) {
        userName_txt.setText(name);
    }
    
  

}
