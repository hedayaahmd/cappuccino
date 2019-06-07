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
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import IntitiesForTables.AccountSearchTable;
import Intity_Controller.Customer_Controller;
import Intity.Customer;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import javax.sql.rowset.Predicate;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AccountSearchController implements Initializable {

    @FXML
    private JFXTreeTableView<AccountSearchTable> account_table;

    @FXML
    private TreeTableColumn<AccountSearchTable, String> accountNumber_col;

    @FXML
    private TreeTableColumn<AccountSearchTable, String> accountName_col;

    @FXML
    private JFXButton confirm_btt;

    @FXML
    private TextField search_txt;
    Customer_Controller customer_controller;
    String customer_number;
    String customer_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<AccountSearchTable> list = null;
        try {
            customer_controller = new Customer_Controller();
            list = FXCollections.observableArrayList();
            ResultSet set = customer_controller.getAccountsList();
            while (set.next()) {
                list.add(new AccountSearchTable(set.getString(1), set.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addTotheTable(list);

        search_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                account_table.setPredicate(new java.util.function.Predicate<TreeItem<AccountSearchTable>>() {
                    @Override
                    public boolean test(TreeItem<AccountSearchTable> t) {
                       boolean flag = t.getValue().account_num.getValue().equals(newValue) || t.getValue().account_name.getValue().equals(newValue);
                        return flag;
                    }
                });
            }});
                account_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<AccountSearchTable>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<AccountSearchTable>> observable, TreeItem<AccountSearchTable> oldValue, TreeItem<AccountSearchTable> newValue) {

                        customer_number = newValue.getValue().account_num.get() + "";
                        customer_name = newValue.getValue().account_name.get() + "";

                    }
                });
    }
           
                

            @FXML
            void confirm_btt_clicked(ActionEvent event) {
                confirm_btt.getScene().getWindow().hide();
            }

            public String getCustomerNumber() {
                return customer_number;
            }

            public String getCustomerName() {
                return customer_name;
            }

            public void addTotheTable(ObservableList<AccountSearchTable> list) {
                accountNumber_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccountSearchTable, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccountSearchTable, String> param) {
                        return param.getValue().getValue().account_num;
                    }
                });
                accountName_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AccountSearchTable, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AccountSearchTable, String> param) {
                        return param.getValue().getValue().account_name;
                    }
                });

                final TreeItem<AccountSearchTable> root = new RecursiveTreeItem<AccountSearchTable>(list, RecursiveTreeObject::getChildren);
                account_table.setRoot(root);
                account_table.setShowRoot(false);

            }

        }
