/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import Converter.Selling_Converter;
import IntitiesForTables.SellingBillListTable;
import IntitiesForTables.SellingBillTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Intity.Selling_Bill;
import Intity.Type;
import Intity.Customer;
import Intity.Selling_Bill;
import Intity.Details_of_selling_bill;
import Intity_Controller.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import Intity_Controller.Selling_bill_controller;
import Utilites.Notifications_store;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SellingBillFrameController implements Initializable {

    int autoNumber = 0;
    Selling_bill_controller sell_controller;
    Account_Controller account_Controller;
    ArrayList<Details_of_selling_bill> list_types = new ArrayList<>();
    Notifications_store notification;
    Selling_Converter convert ;
    int bill=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sell_controller = new Selling_bill_controller();
        account_Controller = new Account_Controller();
        main_scrollPane.setFitToHeight(true);
        main_scrollPane.setFitToWidth(true);
        notification = new Notifications_store();
        convert=new Selling_Converter();
        customerNumber_txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                customerNumber_txt.setDisable(false);
            }
        });
        sumation_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String discountStr = discount_txt.getText();
                double discount = 0;
                if (!discountStr.equals("")) {
                    discount = Double.parseDouble(discountStr);
                }
                totalSum_txt.setText(Double.parseDouble(newValue) - discount + "");

            }
        });
        discount_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String sumStr = sumation_txt.getText();
                double sumation = 0;
                if (!sumStr.equals("")) {
                    sumation = Double.parseDouble(sumStr);
                }
                totalSum_txt.setText(sumation - Double.parseDouble(newValue) + "");

            }
        });
        search(bill);
        // TODO
    }
    public void setBill(int bill_number ){
       bill= bill_number;
    }
    @FXML
    private ToolBar main_toolPar;

    @FXML
    private JFXButton new_btt;

    @FXML
    private JFXButton add_btt;

    @FXML
    private JFXButton edit_btt;

    @FXML
    private JFXButton delete_btt;

    @FXML
    private JFXButton print_btt;

    @FXML
    private TextField sellingBillNum_txt;

    @FXML
    private DatePicker sellingDate_pick;

    @FXML
    private ScrollPane main_scrollPane;

    @FXML
    private VBox VBoxOfHBox;

    @FXML
    private JFXButton addNewCustomer_btt;

    @FXML
    private JFXButton searchCustomer_btt;

    @FXML
    private TextField customerNumber_txt;

    @FXML
    private TextField customerName_txt;

    @FXML
    private TextField sumation_txt;

    @FXML
    private TextField discount_txt;

    @FXML
    private TextArea note_txt;

    @FXML
    private TextField totalSum_txt;

    @FXML
    private TextField search_txt;

    @FXML
    private JFXButton search_btt;

    @FXML
    void search_btt_selected(ActionEvent event) {

    }

    public void fillTheText(ResultSet set) throws SQLException {
        while (set.next()) {
            sellingBillNum_txt.setText(set.getString(1));
            sellingDate_pick.setValue(set.getDate(2).toLocalDate());
            customerNumber_txt.setText(set.getString(3));
            customerName_txt.setText(set.getString(3));
        }
    }

    @FXML
    void addNewCustomer_btt_selected(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/CustomerFrame.fxml"));
            Stage stage = new Stage();
            stage.initOwner(searchCustomer_btt.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));

            // showAndWait will block execution until the window closes...
            stage.showAndWait();

            CustomerFrameController controller = loader.getController();
            Customer customerCreated = controller.getCustomerCreated();
            customerNumber_txt.setText(Customer.getSymbol() + "_" + customerCreated.getAccount_num());
            customerName_txt.setText(customerCreated.getName());
        } catch (IOException ex) {
            Logger.getLogger(PaymentFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void add_btt_selected(ActionEvent event) {
        if (addSellingBill()) {
            notification.saved("فاتورة المبيعات");
        } else {
            notification.error("إضافة فاتورة المبيعات ");
        }
    }

    @FXML
    void delete_btt_selected(ActionEvent event) {
        if (deleteSellingBill()) {
            notification.deleted("فاتورة المبيعات");
            emptyText();
        } else {
            notification.error("حذف فاتورة المبيعات ");
        }
    }

    @FXML
    void edit_btt_selected(ActionEvent event) {
        if (updateSellingBill()) {
            notification.updated("فاتورة المبيعات");
        } else {
            notification.error("تعديل فاتورة المبيعات ");
        }
    }

    @FXML
    void new_btt_selected(ActionEvent event) {
        emptyText();
    }

    @FXML
    void print_btt_selected(ActionEvent event) {

    }

    @FXML
    void searchCustomer_btt_selected(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/AccountSearch.fxml"));
            Stage stage = new Stage();
            stage.initOwner(searchCustomer_btt.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));

            // showAndWait will block execution until the window closes...
            stage.showAndWait();

            AccountSearchController controller = loader.getController();
            customerNumber_txt.setText(Customer.getSymbol() + "_" + controller.getCustomerNumber());
            customerName_txt.setText(controller.getCustomerName());
        } catch (IOException ex) {
            Logger.getLogger(PaymentFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * this method create the HBox
     *
     * @return
     */
    public HBox create_HBox(ArrayList<String> listOfText) {
        Label autoNumbr_label = new Label(autoNumber + "");
        autoNumbr_label.setStyle("-fx-background-color: #f8bdbd");
        autoNumbr_label.setAlignment(Pos.CENTER);
        autoNumbr_label.setMinWidth(51);
        autoNumbr_label.setMinHeight(23);
        TextField typeNumber_txt = new TextField(listOfText.get(0));
        typeNumber_txt.setMinWidth(74);
        typeNumber_txt.setMinHeight(26);
        JFXButton searchType_btt = new JFXButton("+");
        searchType_btt.setMinWidth(45);
        searchType_btt.setMinHeight(26);
        TextField typeName_txt = new TextField(listOfText.get(1));
        typeName_txt.setMinWidth(226);
        typeName_txt.setMinHeight(26);
        TextField quantity_txt = new TextField();
        quantity_txt.setMinWidth(30);
        quantity_txt.setMinHeight(26);
        TextField price_txt = new TextField(listOfText.get(2));
        price_txt.setMinWidth(30);
        price_txt.setMinHeight(26);
        TextField sumationForType_txt = new TextField();
        sumationForType_txt.setMinWidth(50);
        sumationForType_txt.setMinHeight(26);
        JFXButton addNewRow_btt = new JFXButton();// it will be different by the icon
        searchType_btt.setOnAction(new EventHandler<ActionEvent>() {//ارجعي هون
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/TypeSearch.fxml"));
                    Stage stage = new Stage();
                    stage.initOwner(searchType_btt.getScene().getWindow());
                    stage.setScene(new Scene((Parent) loader.load()));

                    // showAndWait will block execution until the window closes...
                    stage.showAndWait();
                    TypeSearchController controller = loader.getController();
                    ArrayList<Type> list = controller.getList();
                    fillTheTextWithList(list);

                } catch (IOException ex) {
                    Logger.getLogger(PaymentFrameController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        });

        addNewRow_btt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addToVBox();
            }

        });

//        price_txt.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                int quantity = Integer.parseInt(quantity_txt.getText());
//                double price = Double.parseDouble(newValue);
//                sumationForType_txt.setText(quantity * price + "");
//            }
//        });
        quantity_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String priceStr = price_txt.getText();
                Double price;
                if (priceStr.equals("")) {
                    price = 0.0;
                } else {
                    price = Double.parseDouble(priceStr);
                }
                int quantity = Integer.parseInt(newValue);
                System.out.println("price =" + price);
                System.out.println("quantity =" + quantity);
                sumationForType_txt.setText(quantity * price + "");
            }
        });
        sumationForType_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                sumation_txt.setText(getTotalTypesSum() + "");
            }
        });

        HBox new_HBox = new HBox(12, autoNumbr_label, typeNumber_txt, searchType_btt, typeName_txt, price_txt, quantity_txt, sumationForType_txt);
        new_HBox.setPadding(new Insets(12, 12, 12, 12));
        return new_HBox;

    }

    public void fillTheTextWithList(ArrayList<Type> list) {
        if (VBoxOfHBox.getChildren().size() == 1) {
            VBoxOfHBox.getChildren().remove(0);
            autoNumber = 0;
        }
        for (int i = 0; i < list.size();) {
            autoNumber++;
            ArrayList<String> listOfText = new ArrayList<>();
            listOfText.add(Type.SYMBOL + "_" + list.get(i).getNumber() + "");
            listOfText.add(list.get(i).getName());
            listOfText.add(list.get(i).getPrice() + "");
            if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
                i++;
            }
        }
    }

    public void fillTheText(ObservableList list, Type type_selected) {

    }

    public double getTotalTypesSum() {
        double sumation = 0;
        ArrayList<HBox> listOfHBox = getListOfHBox();
        ObservableList list = null;
        for (int i = 0; i < listOfHBox.size(); i++) {
            list = listOfHBox.get(i).getChildren();
            TextField type_price_txt = (TextField) list.get(4);
            TextField quantity_txt = (TextField) list.get(5);
            String price = type_price_txt.getText();
            String quntity = quantity_txt.getText();
            if (!(price.equals("") || quntity.equals(""))) {
                sumation += Double.parseDouble(price) * Double.parseDouble(quntity);
            }
        }
        return sumation;
    }

    /**
     * this method add a new HBox to the VBox
     *
     * @return true or false if added or not
     */
    public boolean addToVBox() {
        boolean added = false;
        autoNumber++;
        ArrayList<String> listOfText = new ArrayList<>();
        listOfText.add("");
        listOfText.add("");
        listOfText.add("");
        if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
            added = true;

        }
        return added;
    }

    public String get_The_New_BillNumber() {
        return Selling_Bill.Symbol + "_" + sell_controller.get_bill_number();
    }

    /**
     * this method process each HBox content to add it to the array
     *
     * @param listOfBoxes array if HBoxes
     */
    public void getListOfNodesToProcess(ArrayList<HBox> listOfBoxes) {
        ObservableList list = null;
        for (int i = 0; i < listOfBoxes.size(); i++) {
            list = listOfBoxes.get(i).getChildren();//list of every each hbox element
            process_list(list);
        }
    }

    /**
     * method for get the value of a picker as an sql date
     *
     * @param picker
     * @return date in sql
     */
    public Date getPickerDate(DatePicker picker) {
        return java.sql.Date.valueOf(picker.getValue());
    }

    /**
     * this method process the context list by identify parameter add it text to
     * the details list
     *
     * @param list
     */
    public void process_list(ObservableList list) {
        TextField type_number_txt = (TextField) list.get(1);
        TextField quantity_txt = (TextField) list.get(5);
        list_types.add(new Details_of_selling_bill(getTheBillIntgerNumber(sellingBillNum_txt), getTheBillIntgerNumber(type_number_txt), Integer.parseInt(quantity_txt.getText())));

    }

    public int getTheBillIntgerNumber(TextField text) {
        StringTokenizer tokenizer = new StringTokenizer(text.getText(), "_");
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }

    /**
     * method for delete the context of a HBox in VBox
     *
     * @param HBOxList
     */
    public void delete(ArrayList<HBox> HBOxList) {
        for (int i = 0; i < HBOxList.size(); i++) {
            clearFromVBox(HBOxList.get(i));
        }
    }

    public void clearFromVBox(HBox deleteMe) {
        VBoxOfHBox.getChildren().remove(deleteMe);
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

    /**
     * in each VBox list of HBox this method
     *
     * @return list of HBox
     */
    public ArrayList<HBox> getListOfHBox() {
        ArrayList<HBox> listOfHBoxes = new ArrayList<>();
        for (int i = 0; i < VBoxOfHBox.getChildren().size(); i++) {
            listOfHBoxes.add((HBox) VBoxOfHBox.getChildren().get(i));//list of hbox
        }
        return listOfHBoxes;

    }

    public boolean addSellingBill() {
        boolean add = false;
        getListOfNodesToProcess(getListOfHBox());
        System.out.println("at add");
        for (int i = 0; i < list_types.size(); i++) {
            System.out.println(list_types.get(i));
        }
        Selling_Bill sell_obj = new Selling_Bill(getTheBillIntgerNumber(sellingBillNum_txt), getTheBillIntgerNumber(customerNumber_txt), Double.parseDouble(totalSum_txt.getText()),
                Double.parseDouble(discount_txt.getText()), getPickerDate(sellingDate_pick), note_txt.getText(), list_types);
        if (sell_controller.add_selling_bill(sell_obj)) {
            sell_controller.addTypes(sell_obj.getList_of_types());
            add = true;
            account_Controller.updateAccount(sell_obj.getAccount_number(), 0, sell_obj.getTotal_price(), sell_obj.getFullBillNumber());

        }

        return add;
    }

    public boolean updateSellingBill() {
        boolean edited = false;
        double lastTotal_price = sell_controller.getPrevouisPrice(getTheBillIntgerNumber(sellingBillNum_txt));
        getListOfNodesToProcess(getListOfHBox());
        Selling_Bill sell_obj = new Selling_Bill(getTheBillIntgerNumber(sellingBillNum_txt), getTheBillIntgerNumber(customerNumber_txt), Double.parseDouble(totalSum_txt.getText()),
                Double.parseDouble(discount_txt.getText()), getPickerDate(sellingDate_pick), note_txt.getText(), list_types);
        if (sell_controller.update_selling_bill(sell_obj)) {
            if (sell_controller.delete_types(sell_obj.getBill_num())) {
                sell_controller.addTypes(sell_obj.getList_of_types());
                edited = true;
            }

        }
        if (edited) {
            if (account_Controller.updateAccount(sell_obj.getAccount_number(), lastTotal_price, 0, sell_obj.getFullBillNumber())) {
                account_Controller.updateAccount(sell_obj.getAccount_number(), 0, sell_obj.getTotal_price(), sell_obj.getFullBillNumber());

            }
        }
        return edited;
    }

    public boolean deleteSellingBill() {
        boolean delete = false;
        int bill_number = getTheBillIntgerNumber(sellingBillNum_txt);
//        String account_number = customerNumber_txt.getText();
        double total_price = sell_controller.getPrevouisPrice(bill_number);
        if (sell_controller.delete_types(bill_number)) {
            if (sell_controller.delete_selling_bill(bill_number)) {
                account_Controller.updateAccount(getTheBillIntgerNumber(customerNumber_txt), total_price, 0, sellingBillNum_txt.getText());
                delete = true;

            }
        }
        return delete;
    }

    public void emptyText() {
        sellingBillNum_txt.setText(get_The_New_BillNumber());
        sellingDate_pick.setValue(null);
        customerNumber_txt.setText("");
        customerName_txt.setText("");
        delete(getListOfHBox());
        addToVBox();
        autoNumber = 0;
        note_txt.setText("");
        sumation_txt.setText("0.00");
        discount_txt.setText("0.00");
        totalSum_txt.setText("0.00");
    }

    public void search(int bill_number) {
        if(bill_number==0){
            System.out.println("nothing to be done");
        }else {
        SellingBillTable  bill_info = convert.getBillInfo(sell_controller.searchByNumber(bill_number));
        ArrayList<SellingBillListTable> listOfDetails = convert.getTheListOfBuyingBill(sell_controller.searchDetailsByNumber(bill_number));
        sellingBillNum_txt.setText(Selling_Bill.Symbol + "_" + bill_info.bill_number);
        sellingDate_pick.setValue(bill_info.sell_date.toLocalDate());
        note_txt.setText(bill_info.note);
        sumation_txt.setText(bill_info.total_price + "");
        customerNumber_txt.setText(Customer.getSymbol()+"_"+bill_info.customr_number);
        customerName_txt.setText(bill_info.customer_name);
        discount_txt.setText(bill_info.discount+"");
        totalSum_txt.setText(bill_info.total_price-bill_info.discount+"");
        fillTheTextWithListWithSearch(listOfDetails);
        }

    }

    public void fillTheTextWithListWithSearch(ArrayList<SellingBillListTable> list) {
        if (VBoxOfHBox.getChildren().size() == 1) {
            VBoxOfHBox.getChildren().remove(0);
            autoNumber = 0;
        }
        for (int i = 0; i < list.size();) {
            autoNumber++;
            ArrayList<String> listOfText = new ArrayList<>();
            listOfText.add(Type.SYMBOL + "_" + list.get(i).type_number + "");
            listOfText.add(list.get(i).type_name);
            listOfText.add(list.get(i).price + "");
            listOfText.add(list.get(i).quantity + "");
            if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
                i++;
            }
        }
    }

}
