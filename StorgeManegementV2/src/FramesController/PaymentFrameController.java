/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import Converter.Payment_Converter;
import IntitiesForTables.CheckPaymentList;
import IntitiesForTables.PaymentTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Intity_Controller.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Intity.Check;
import Intity.Check_Payment;
import Intity.Cash_Payment;
import Intity.Customer;
import Intity.Payment;
import java.sql.Date;
import java.util.Calendar;
import java.util.StringTokenizer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import Intity_Controller.Payment_Controller;
import Intity_Controller.Account_Controller;
import Utilites.Notifications_store;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PaymentFrameController implements Initializable {

    ArrayList<Check> listOfChecks;
    Payment_Controller controller = new Payment_Controller();
    Account_Controller account_controller = new Account_Controller();
    double totalCheckSum =0 ;
   Notifications_store notification;
    Payment_Converter convert ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                notification=new Notifications_store();
                convert=new Payment_Converter();

        if (addToVBox()) {
            System.out.println("added successfully");//for testing
        } else {
            System.out.println("unsuccessfull to add ");//for testing
        }
        main_scrollPane.setFitToHeight(true);
        main_scrollPane.setFitToWidth(true);
        listOfChecks = new ArrayList<>();
        edit_btt.setDisable(false);
        accountNumber_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                debitAmount_label.setText(account_controller.getDebit(newValue) + "");
                balanceAmount_label.setText(account_controller.getBalance(newValue) + "");
            }
        });
        totalAmount_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if(!newValue.equals("")){
                  cashAmount_txt.setText(newValue);
                  checkAmount_txt.setText("0.00");
                  
              }
            }
        });
        cashAmount_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               String totalAmountStr=totalAmount_txt.getText();
               if(!totalAmountStr.equals("")){
                   double totalAmount=Double.parseDouble(totalAmountStr);
                   if(!newValue.equals("")){
                       double cashAmount=Double.parseDouble(newValue);
                   checkAmount_txt.setText(totalAmount-cashAmount+"");
                   }
               }
            }
        });
        checkAmount_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               if(!newValue.equals("")){
                   totalCheckSum=Double.parseDouble(newValue);
               }
            }
        });

    }

    /**
     * start of parameter
     */
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
    private ScrollPane main_scrollPane;

    @FXML
    private VBox VBoxOfHBox;

    @FXML
    private HBox HBoxInscroll1;

    @FXML
    private HBox bondHBox;

    @FXML
    private TextField bondNumber_txt;

    @FXML
    private DatePicker bondDate_pick;

    @FXML
    private HBox acccountHbox;

    @FXML
    private TextField accountNumber_txt;

    @FXML
    private JFXButton selectAccount_btt;

    @FXML
    private Label accountName_label;

    @FXML
    private Label debitAmount_label;

    @FXML
    private Label balanceAmount_label;

    @FXML
    private TextField totalAmount_txt;

    @FXML
    private TextField cashAmount_txt;

    @FXML
    private TextField checkAmount_txt;

    @FXML
    private ToolBar secondary_toolPar;

    @FXML
    private JFXButton addHBox_btt;

    @FXML
    private JFXButton deleteHBox_btt;

    @FXML
    private JFXButton repeatHBox_btt;

    @FXML
    private TextArea bondNote_txt;

    @FXML
    private TextField search_txt;

    @FXML
    private JFXButton search_btt;

    @FXML
    void search_btt_selected(ActionEvent event) {
        search(getTheBondIntgerNumber(search_txt));
    }

    /**
     * start of action events
     *
     */
    @FXML
    void addHBox_btt_selected(ActionEvent event) {
        System.out.println(totalCheckSum);
        System.out.println(getTotalTypesSum());
        if(totalCheckSum-getTotalTypesSum()>0){
        addToVBox();
        }
    }
    

    @FXML
    void add_btt_selected(ActionEvent event) {
        if (add_bond()) {
              notification.saved("سند القبض");
        } else {
            notification.error("إضافة سند القبض ");
        }
    }

    @FXML
    void deleteHBox_btt_selected(ActionEvent event) {
        delete(getListOfHBox());
    }

    @FXML
    void delete_btt_selected(ActionEvent event) {
        if (!emptyTextField(bondNumber_txt)) {
            if (!emptyTextField(accountNumber_txt)) {
                if (!emptyTextField(totalAmount_txt)) {
                    if (!emptyTextField(cashAmount_txt) || !emptyTextField(checkAmount_txt)) {
                        controller.change_availablity(getTheBondIntgerNumber(bondNumber_txt), "ملغى من الحذف");
                        account_controller.updateAccount(getTheBondIntgerNumber(accountNumber_txt), 0, Double.parseDouble(totalAmount_txt.getText()), bondNumber_txt.getText());
                        notification.deleted("سند القبض ");
                    }
                }
            }
        }

    }

    @FXML
    void edit_btt_selected(ActionEvent event) {
        notification.warning("لا يمكنك اجراء عملية التعديل");
    }

    @FXML
    void new_btt_selected(ActionEvent event) {
        bondNumber_txt.setText(get_The_New_BondNumber());
        bondDate_pick.setValue(LocalDate.now());
        accountNumber_txt.setText("");
        accountName_label.setText("");
        debitAmount_label.setText("");
        balanceAmount_label.setText("");
        totalAmount_txt.setText("0.00");
        cashAmount_txt.setText("0.00");
        checkAmount_txt.setText("0.00");

    }

    @FXML
    void print_btt_selected(ActionEvent event) {

    }

    @FXML
    void repeatHBox_btt_selected(ActionEvent event) {
        repeat(getListOfHBox());
    }

    @FXML
    void selectAccount_btt_selected(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/AccountSearch.fxml"));
            Stage stage = new Stage();
            stage.initOwner(selectAccount_btt.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));

            // showAndWait will block execution until the window closes...
            stage.showAndWait();

            AccountSearchController controller = loader.getController();
            accountNumber_txt.setText(Customer.getSymbol()+"_"+controller.getCustomerNumber());
            accountName_label.setText(controller.getCustomerName());
        } catch (IOException ex) {
            Logger.getLogger(PaymentFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean add_bond() {
        boolean add = false;
        getListOfNodesToProcess(getListOfHBox());

        Payment generalPayment = new Payment(getTheBondIntgerNumber(bondNumber_txt), getPickerDate(bondDate_pick),
                getTheBondIntgerNumber(accountNumber_txt), Double.parseDouble(totalAmount_txt.getText()), DateRightNow(), bondNote_txt.getText());

        Check_Payment check_payment = new Check_Payment(listOfChecks, getTheBondIntgerNumber(bondNumber_txt), generalPayment.getDate(),
                getTheBondIntgerNumber(accountNumber_txt), generalPayment.getTotal_amount(), DateRightNow(), generalPayment.getNote());

        Cash_Payment cash_payment = new Cash_Payment(Double.parseDouble(cashAmount_txt.getText()),getTheBondIntgerNumber(bondNumber_txt), generalPayment.getDate(),
                getTheBondIntgerNumber(accountNumber_txt), generalPayment.getTotal_amount(), DateRightNow(), generalPayment.getNote());
        if (controller.add_Payment(generalPayment)) {
            if (controller.addCheck(check_payment) && controller.addCash(cash_payment)) {
                add = true;
            }
            if (add) {
                account_controller.updateAccount(generalPayment.getAccount_number(), generalPayment.getTotal_amount(), 0, generalPayment.getFullBondNumber());
            }
        }
        return add;
    }

    public int getTheBondIntgerNumber(TextField text) {
        StringTokenizer tokenizer = new StringTokenizer(text.getText(), "_");
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }

    /**
     * method for repeat the context of a HBox in VBox
     *
     * @param HBOxList
     */
    public void repeat(ArrayList<HBox> HBOxList) {
        for (int i = 0; i < HBOxList.size(); i++) {
            if (testSelection(HBOxList.get(i).getChildren())) {
                HBox new_HBox = create_HBox(getListOfText(HBOxList.get(i).getChildren()));
                repeatAnHBoxContext(new_HBox);
            }
        }
    }

    public ArrayList<String> getListOfText(ObservableList list) {
        ArrayList<String> textList = new ArrayList<>();
        TextField checkNum_txt = (TextField) list.get(0);
        TextField checkOwnerName_txt = (TextField) list.get(1);
        TextField bankAccountNumber_txt = (TextField) list.get(2);
        DatePicker maturirtyDate_pick = (DatePicker) list.get(3);
        TextField checkValue_txt = (TextField) list.get(4);
        TextField note_txt = (TextField) list.get(5);
        CheckBox select_check = (CheckBox) list.get(6);
        textList.add(checkNum_txt.getText());
        textList.add(checkOwnerName_txt.getText());
        textList.add(bankAccountNumber_txt.getText());
        textList.add(maturirtyDate_pick.getValue().toString());
        textList.add(checkValue_txt.getText());
        textList.add(note_txt.getText());
        return textList;

    }

    /**
     * method for delete the context of a HBox in VBox
     *
     * @param HBOxList
     */
    public void delete(ArrayList<HBox> HBOxList) {
        for (int i = 0; i < HBOxList.size(); i++) {
            if (testSelection(HBOxList.get(i).getChildren())) {
                clearFromVBox(HBOxList.get(i));
            }
        }
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
        ArrayList<HBox> listOfBoxes = new ArrayList<>();
        for (int i = 0; i < VBoxOfHBox.getChildren().size(); i++) {
            listOfBoxes.add((HBox) VBoxOfHBox.getChildren().get(i));//list of hbox
        }
        return listOfBoxes;

    }

    /**
     * method to test if the checkBox is selected or not to make the operation
     * on it
     *
     * @param list the list is the content of HBox
     * @return true or false (selected or not)
     */
    public boolean testSelection(ObservableList list) {
        boolean checked = false;
        CheckBox select_check = (CheckBox) list.get(6);
        if (select_check.isSelected()) {
            checked = true;
        }
        return checked;
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

    public void clearFromVBox(HBox deleteMe) {
        VBoxOfHBox.getChildren().remove(deleteMe);
    }

    public void repeatAnHBoxContext(HBox repeatMe) {
        VBoxOfHBox.getChildren().add(repeatMe);
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
     * the check list
     *
     * @param list
     */
    public void process_list(ObservableList list) {
        TextField checkNum_txt = (TextField) list.get(0);
        TextField checkOwnerName_txt = (TextField) list.get(1);
        TextField bankAccountNumber_txt = (TextField) list.get(2);
        DatePicker maturirtyDate_pick = (DatePicker) list.get(3);
        TextField checkValue_txt = (TextField) list.get(4);
        TextField note_txt = (TextField) list.get(5);
        CheckBox select_check = (CheckBox) list.get(6);

        listOfChecks.add(new Check(checkNum_txt.getText(), checkOwnerName_txt.getText(), bankAccountNumber_txt.getText(),
                Double.parseDouble(checkValue_txt.getText()), getPickerDate(maturirtyDate_pick), note_txt.getText(), DateRightNow()));
    }

    /**
     * this method add a new HBox to the VBox
     *
     * @return true or false if added or not
     */
    public boolean addToVBox() {
        boolean added = false;
        ArrayList<String> textList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            textList.add("");
        }
        if (VBoxOfHBox.getChildren().add(create_HBox(textList))) {
            added = true;
        }
        return added;
    }

    /**
     * this method create the HBox
     *
     * @return
     */
    public HBox create_HBox(ArrayList<String> textList) {
        TextField checkNum_txt = new TextField(textList.get(0));
        TextField checkOwnerName_txt = new TextField(textList.get(1));
        TextField bankAccountNumber_txt = new TextField(textList.get(2));
        DatePicker maturirtyDate_pick;
        if(textList.get(3).equals("")){
             maturirtyDate_pick = new DatePicker();
        }else{
        maturirtyDate_pick = new DatePicker(LocalDate.parse(textList.get(3)));
        }
        TextField checkValue_txt = new TextField(textList.get(4));
        TextField note_txt = new TextField(textList.get(5));
        CheckBox select_check = new CheckBox();
        HBox new_HBox = new HBox(12, checkNum_txt, checkOwnerName_txt, bankAccountNumber_txt, maturirtyDate_pick, checkValue_txt, note_txt, select_check);
        new_HBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        new_HBox.setPadding(new Insets(15, 15, 15, 15));
        return new_HBox;
    }

    public boolean emptyTextField(TextField text) {
        boolean empty = false;
        String check_String = "";
        check_String = text.getText();
        if (check_String.equals("")) {
            empty = true;
        }
        return empty;
    }

    public String get_The_New_BondNumber() {
        return Payment.getSymbol() + "_" + controller.getBondNumber();
    }
     public double getTotalTypesSum() {
        double sumation = 0;
        ArrayList<HBox> listOfHBox = getListOfHBox();
        ObservableList list = null;
        for (int i = 0; i < listOfHBox.size(); i++) {
            list = listOfHBox.get(i).getChildren();
            TextField checkValue_txt = (TextField) list.get(4);
            String checkValue = checkValue_txt.getText();
            if (!(checkValue.equals("") )) {
                sumation += Double.parseDouble(checkValue) ;
            }
        }
        return sumation;
    }
     
     
     
     public void search(int bond_number ){
         PaymentTable bond =convert.getBondInfo(controller.searchBondInfo(bond_number));
         ArrayList<CheckPaymentList> list =convert.getTheListOfCheck(controller.searchBondCheckInfo(bond_number));
         fillTheTextWithListWithSearch(list);
         bondNumber_txt.setText(Payment.getSymbol()+"_"+bond.bond_number);
         bondDate_pick.setValue(bond.date.toLocalDate());
         accountNumber_txt.setText(Customer.getSymbol()+"_"+bond.customer_number);
         accountName_label.setText(bond.customer_name);
         totalAmount_txt.setText(bond.total_amount+"");
         cashAmount_txt.setText(controller.getTotalCash(bond_number)+"");
         checkAmount_txt.setText(controller.getTotalCeck(bond_number)+""); 
     }
     
     public void fillTheTextWithListWithSearch(ArrayList<CheckPaymentList> list) {
        if (VBoxOfHBox.getChildren().size() == 1) {
            VBoxOfHBox.getChildren().remove(0);
        }
        for (int i = 0; i < list.size();) {
            ArrayList<String> listOfText = new ArrayList<>();
            listOfText.add(list.get(i).check_number);
            listOfText.add(list.get(i).owner_name);
            listOfText.add(list.get(i).bank_account_number + "");
            listOfText.add(list.get(i).maturity_date+ "");
            listOfText.add(list.get(i).value+ "");
            listOfText.add(list.get(i).note+ "");
            if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
                i++;
            }
        }
    }

}
