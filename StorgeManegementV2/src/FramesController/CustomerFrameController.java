
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
import java.sql.ResultSet;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import IntitiesForTables.CustomerTable;
import Intity.Customer;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import Intity_Controller.Customer_Controller;
import Utilites.Notifications_store;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CustomerFrameController implements Initializable {

    //ضايل بس الاشعارات هون:)))))
    Customer_Controller customer_controller;
        Notifications_store notification ;

    Customer customer_Created ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer_controller = new Customer_Controller();
        notification=new Notifications_store();
        add_to_table(customer_controller. selectCustomers());
        searchtxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                     add_to_table(customer_controller.selectCustomers());
                 }else {
                Cus_Table.setPredicate(new Predicate<TreeItem<CustomerTable>>() {
                    @Override
                    public boolean test(TreeItem<CustomerTable> t) {
                        boolean flag = t.getValue().account_num.getValue().equals(newValue) || t.getValue().name.getValue().equals(newValue)
                                || t.getValue().address.getValue().equals(newValue) || t.getValue().id.getValue().equals(newValue)
                                || t.getValue().phone.getValue().equals(newValue) || t.getValue().registry_Date.getValue().equals(newValue);
                        return flag;
                    }
                });
            }
            }
        });

        Cus_Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<CustomerTable>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<CustomerTable>> observable, TreeItem<CustomerTable> oldValue, TreeItem<CustomerTable> newValue) {
                 if(!Cus_Table.getSelectionModel().isEmpty()){
                     CustomerTable selected_customer =newValue.getValue();
                     fillTheText(selected_customer);
                 }
                
            }
        });
    }
    
    @FXML
    private TextField addresstxt;

    @FXML
    private ToolBar cus_main_toolPar;

    @FXML
    private JFXButton new_btt;

    @FXML
    private JFXButton cus_add_bt;

    @FXML
    private JFXButton cus_edit_bt;
    @FXML
    private JFXButton cus_delete_bt;

    @FXML
    private JFXButton print_btt;

    @FXML
    private TextField account_numtxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField phonetxt;

    @FXML
    private JFXTreeTableView<CustomerTable> Cus_Table;

    @FXML
    private TreeTableColumn<CustomerTable, String> account_num_col;

    @FXML
    private TreeTableColumn<CustomerTable, String> name_col;

    @FXML
    private TreeTableColumn<CustomerTable, String> phone_col;

    @FXML
    private TreeTableColumn<CustomerTable, String> address_col;

    @FXML
    private TreeTableColumn<CustomerTable, String> id_col;

    @FXML
    private TreeTableColumn<CustomerTable, String> registry_Date_col;

    @FXML
    private TextField searchtxt;

    @FXML
    void cus_add_bt_selected(ActionEvent event) {
        if (addCustomer()) {
            notification.saved("عميل جديد");
        } else {
             notification.error("إضافة عميل جديد");
        }
    }

    @FXML
    void cus_edit_bt_selected(ActionEvent event) {
        if (updateCustomer()) {
            notification.updated("بيانات عميل ");
        } else {
            notification.error("تعديل بيانات عميل"
                    + " ");
        }
    }

    @FXML
    void cus_delete_btt_selected(ActionEvent event) {
        if (deleteCustomer()) {
              notification.deleted("بيانات عميل");
        } else {
            notification.error("حذف بيانات عميل");;
        }
    }

    @FXML
    void new_btt_selected(ActionEvent event) {
        emptyTexts();
    }

    /**
     * the system right now date method
     *
     * @return sql right now date
     */
    public Date DateRightNow() {
        // create a java calendar instance
        Calendar calendar = Calendar.getInstance();

// get a java date (java.util.Date) from the Calendar instance.
// this java date will represent the current date, or "now".
        java.util.Date currentDate = calendar.getTime();

// now, create a java.sql.Date from the java.util.Date
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        return date;
    }

    public void add_to_table(ResultSet set) {
        account_num_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().account_num;

            }
        });
        name_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().name;

            }
        });
        phone_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().phone;

            }
        });
        address_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().address;

            }
        });
        id_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().id;

            }
        });
        registry_Date_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CustomerTable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CustomerTable, String> param) {
                return param.getValue().getValue().registry_Date;

            }
        });
        ObservableList<CustomerTable> list = FXCollections.observableArrayList();
        try {
            while (set.next()) {
                list.add(new CustomerTable(set.getString(1), set.getString(2), set.getString(3), set.getString(4),
                        set.getString(5), set.getDate(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
     final TreeItem<IntitiesForTables.CustomerTable> root = new RecursiveTreeItem<IntitiesForTables.CustomerTable>(list, RecursiveTreeObject::getChildren);

        Cus_Table.setRoot(root);
        Cus_Table.setShowRoot(false);

    }
    public Customer getCustomerCreated(){
        return customer_Created;
    }

    public int getTheAccountIntgerNumber() {
        StringTokenizer tokenizer = new StringTokenizer(account_numtxt.getText(), "_");
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }

    public boolean addCustomer() {
        boolean done = false;
        Customer customer = new Customer(getTheAccountIntgerNumber(), nametxt.getText(), phonetxt.getText(),
                addresstxt.getText(), idtxt.getText(), DateRightNow());
        if (customer_controller.add_Customer_data(customer)) {
            done = true;
            add_to_table(customer_controller.selectCustomers());
            customer_Created=customer;
        }
        return done;
    }

    public boolean deleteCustomer() {
        boolean done = false;
        if (customer_controller.delete_Customer_data(getTheAccountIntgerNumber())) {
            done = true;
            add_to_table(customer_controller.selectCustomers());
            emptyTexts();

        }
        return done;
    }

    public boolean updateCustomer() {
        boolean done = false;
        Customer customer = new Customer(getTheAccountIntgerNumber(), nametxt.getText(), phonetxt.getText(),
                addresstxt.getText(), idtxt.getText(), DateRightNow());
        if (customer_controller.update_Customer_data(customer)) {
            done = true;
            add_to_table(customer_controller.selectCustomers());
        }
        return done;
    }

    public void emptyTexts() {
        account_numtxt.setText(getNewAccountNumber());
        nametxt.setText("");
        phonetxt.setText("");
        addresstxt.setText("");
        idtxt.setText("");
    }

    public String getNewAccountNumber() {
        return Customer.getSymbol() + "_" + customer_controller.getNewCustomer_Number();
    }

    public void fillTheText(CustomerTable customer_selected) {
        account_numtxt.setText(Customer.getSymbol()+"_"+customer_selected.account_num.get());
        nametxt.setText(customer_selected.name.get());
        phonetxt.setText(customer_selected.phone.get());
        addresstxt.setText(customer_selected.address.get());
        idtxt.setText(customer_selected.id.get());
    }


}
