/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import Converter.History_converter;
import Intity.History;
import Intity_Controller.Selling_bill_controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HistoryFrameController implements Initializable {

    Selling_bill_controller cont;
    ArrayList<History> history;
    History_converter convert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont = new Selling_bill_controller();
        convert = new History_converter();
        try {

            history = convert.translate(cont.getHistory());
            fillHypres(history);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fillHypres(ArrayList<History> history) {
        
        hyperText1.setText(history.get(0).description);
        //hyperText2.setText(history.get(1).description);
       // hyperText3.setText(history.get(2).description);
       // hyperText4.setText(history.get(3).description);
       // hyperText5.setText(history.get(4).description);
       // hyperText6.setText(history.get(5).description);
       // hyperText7.setText(history.get(6).description);
    }
    @FXML
    private AnchorPane historyAnchor;

    @FXML
    private Hyperlink hyperText1;

    @FXML
    private Hyperlink hyperText2;

    @FXML
    private Hyperlink hyperText3;

    @FXML
    private Hyperlink hyperText4;

    @FXML
    private Hyperlink hyperText5;

    @FXML
    private Hyperlink hyperText6;

    @FXML
    private Hyperlink hyperText7;

    @FXML
    void hyperText1_selected(ActionEvent event) {
        open(history.get(0).bill_number);

    }

    @FXML
    void hyperText2_selected(ActionEvent event) {
        open(history.get(1).bill_number);
    }

    @FXML
    void hyperText3_selected(ActionEvent event) {
        open(history.get(2).bill_number);
    }

    @FXML
    void hyperText4_selected(ActionEvent event) {
        open(history.get(3).bill_number);
    }

    @FXML
    void hyperText5_selected(ActionEvent event) {
        open(history.get(4).bill_number);
    }

    @FXML
    void hyperText6_selected(ActionEvent event) {
        open(history.get(5).bill_number);
    }

    @FXML
    void hyperText7_selected(ActionEvent event) {
        open(history.get(6).bill_number);
    }

    public void open(int bill_number) {
        Node node;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/Frames/SellingBillFrame.fxml"));

            node = (Node) FXMLLoader.load(getClass().getResource("/Frames/SellingBillFrame.fxml"));
            historyAnchor.getChildren().setAll(node);
            SellingBillFrameController controller = loader.<SellingBillFrameController>getController();
            controller.setBill(bill_number);
        } catch (IOException ex) {
            Logger.getLogger(HomePageFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
