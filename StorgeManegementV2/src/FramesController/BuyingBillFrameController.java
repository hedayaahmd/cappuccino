/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import Converter.Buying_Converter;
import IntitiesForTables.BuyingBillListTable;
import IntitiesForTables.BuyingBillTable;
import Intity.Buying_Bill;
import Intity.Description_Of_Buying_Bill;
import Intity.Type;
import Intity_Controller.Buying_Bill_Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Utilites.Notifications_store;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BuyingBillFrameController implements Initializable {
    
    int autoNumber = 0;
    Buying_Bill_Controller buy_controller;
    ArrayList<Description_Of_Buying_Bill> list_types = new ArrayList<>();
    Notifications_store notification;
    Converter.Buying_Converter convert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notification = new Notifications_store();
        buy_controller = new Buying_Bill_Controller();
        addToVBox();
        convert = new Buying_Converter();
//        setImages();

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
    private TextField buyingBillNum_txt;
    
    @FXML
    private DatePicker buyingDate_pick;
    
    @FXML
    private ScrollPane main_scrollPane;
    
    @FXML
    private VBox VBoxOfHBox;
    
    @FXML
    private TextField sumation_txt;
    
    @FXML
    private TextArea note_txt;
    @FXML
    private TextField search_txt;
    
    @FXML
    private JFXButton search_btt;
    
    @FXML
    void search_btt_selected(ActionEvent event) {
        search(getTheBillIntgerNumber(search_txt));
    }
    
    @FXML
    void add_btt_selected(ActionEvent event) {
        if (addBuyingBill()) {
            notification.saved("فاتورة المشتريات");
        } else {
            notification.error("إضافة فاتورة المشتريات ");
        }
    }
    
    @FXML
    void delete_btt_selected(ActionEvent event) {
        if (deleteBuyingBill()) {
            notification.deleted("فاتورة المشتريات");
        } else {
            notification.error("حذف فاتورة المشتريات ");
        }
    }
    
    @FXML
    void edit_btt_selected(ActionEvent event) {
        if (updateBuyingBill()) {
            notification.updated("فاتورة المشتريات");
        } else {
            notification.error("تعديل فاتورة المشتريات ");
        }
    }
    
    @FXML
    void new_btt_selected(ActionEvent event) {
        buyingBillNum_txt.setText(get_The_New_BillNumber());
        buyingDate_pick.setValue(null);
        delete(getListOfHBox());
        autoNumber = 0;
        addToVBox();
        sumation_txt.setText("0.00");
        note_txt.setText("");
    }
    
    @FXML
    void print_btt_selected(ActionEvent event) {
        
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
        searchType_btt.setMinWidth(90);
        searchType_btt.setMinHeight(30);
        TextField typeName_txt = new TextField(listOfText.get(1));
        typeName_txt.setMinWidth(200);
        typeName_txt.setMinHeight(26);
        JFXButton newType_btt = new JFXButton("new");
        searchType_btt.setMinWidth(33);
        searchType_btt.setMinHeight(26);
        TextField quantity_txt = new TextField(listOfText.get(3));
        quantity_txt.setMinWidth(55);
        quantity_txt.setMinHeight(26);
        TextField price_txt = new TextField(listOfText.get(2));
        price_txt.setMinWidth(77);
        price_txt.setMinHeight(26);
        TextField sumationForType_txt = new TextField();
        sumationForType_txt.setMinWidth(87);
        sumationForType_txt.setMinHeight(26);
        JFXButton addNewRow_btt = new JFXButton("+");// it will be different by the icon
        searchType_btt.setOnAction(new EventHandler<ActionEvent>() {
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
        newType_btt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/TypeFrame.fxml"));
                    Stage stage = new Stage();
                    stage.initOwner(newType_btt.getScene().getWindow());
                    stage.setScene(new Scene((Parent) loader.load()));

                    // showAndWait will block execution until the window closes...
                    stage.showAndWait();
                    
                    TypeFrameController controller = loader.getController();
                    Type creType = controller.getTypeCreated();
                    typeNumber_txt.setText(Type.getSYMBOL() + "_" + creType.getNumber());
                    typeName_txt.setText(creType.getName());
                    price_txt.setText(creType.getPrice() + "");
                    
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
        quantity_txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("oldValue="+oldValue+"\nnewValue"+newValue);
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
        
        HBox new_HBox = new HBox(25, autoNumbr_label, typeNumber_txt, searchType_btt, typeName_txt, newType_btt, price_txt, quantity_txt, sumationForType_txt, addNewRow_btt);
        new_HBox.setPadding(new Insets(7, 7, 7, 7));
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
            listOfText.add("");
            if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
                i++;
            }
        }
    }
    
    public double getTotalTypesSum() {
        double sumation = 0;
        ArrayList<HBox> listOfHBox = getListOfHBox();
        ObservableList list = null;
        for (int i = 0; i < listOfHBox.size(); i++) {
            list = listOfHBox.get(i).getChildren();
            TextField type_price_txt = (TextField) list.get(5);
            TextField quantity_txt = (TextField) list.get(6);
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
        listOfText.add("");
        if (VBoxOfHBox.getChildren().add(create_HBox(listOfText))) {
            added = true;
        }
        return added;
    }
    
    public String get_The_New_BillNumber() {
        return Buying_Bill.bill_Symbol + "_" + buy_controller.get_bill_number();
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
        TextField quantity_txt = (TextField) list.get(6);
        list_types.add(new Description_Of_Buying_Bill(getTheBillIntgerNumber(buyingBillNum_txt), getTheBillIntgerNumber(type_number_txt), Integer.parseInt(quantity_txt.getText())));
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
    
    public boolean addBuyingBill() {
        boolean done = false;
        getListOfNodesToProcess(getListOfHBox());
        Buying_Bill buy_bill = new Buying_Bill(getTheBillIntgerNumber(buyingBillNum_txt), list_types, Double.parseDouble(sumation_txt.getText()), getPickerDate(buyingDate_pick),
                note_txt.getText(), DateRightNow());
        if (buy_controller.Add_buying_Bill(buy_bill)) {
            done = true;
            buy_controller.addTypes(buy_bill.getList_of_types());
        }
        return done;
    }
    
    public boolean deleteBuyingBill() {
        boolean done = false;
        int bill_number = getTheBillIntgerNumber(buyingBillNum_txt);
        if (buy_controller.deleteTypeList(bill_number)) {
            if (buy_controller.delete_buying_bill(bill_number)) {
                done = true;
            }
        }
        return done;
    }
    
    public boolean updateBuyingBill() {
        boolean done = false;
        getListOfNodesToProcess(getListOfHBox());
        Buying_Bill buy_bill = new Buying_Bill(getTheBillIntgerNumber(buyingBillNum_txt), list_types, Double.parseDouble(sumation_txt.getText()), getPickerDate(buyingDate_pick),
                note_txt.getText(), DateRightNow());
        if (buy_controller.deleteTypeList(buy_bill.getBill_number())) {
            if (buy_controller.update_buying_bill(buy_bill)) {
                buy_controller.addTypes(buy_bill.getList_of_types());
                done = true;
            }
        }
        return done;
    }
    
    public void search(int bill_number) {
        BuyingBillTable bill_info = convert.getBillInfo(buy_controller.searchByNumber(bill_number));
        ArrayList<BuyingBillListTable> listOfDetails = convert.getTheListOfBuyingBill(buy_controller.searchDetailsByNumber(bill_number));
        buyingBillNum_txt.setText(Buying_Bill.bill_Symbol+"_"+bill_info.bill_number);
        buyingDate_pick.setValue(bill_info.date.toLocalDate());
        note_txt.setText(bill_info.note);
        sumation_txt.setText(bill_info.total_price + "");
        fillTheTextWithListWithSearch(listOfDetails);
        
    }

    public void fillTheTextWithListWithSearch(ArrayList<BuyingBillListTable> list) {
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

//     public void setBackgroudImage(JFXButton btt, String url) {
//        Image backGround = new Image(url);
//        BackgroundImage newBgr = new BackgroundImage(backGround, null, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
//        btt.setBackground(new Background(newBgr));
//
//    }
//
//    public void setImages() {
//        setBackgroudImage(new_btt, "/Icons/icons8-plus-math-48 (1).png");
////        setBackgroudImage(add_btt, "/Icons/icons8-add-file-48.png");
//        setBackgroudImage(edit_btt, "/Icons/icons8-edit-48 (1).png");
////        setBackgroudImage(delete_btt, "/Icons/icons8-trash-48.png");
////        setBackgroudImage(print_btt, "/Icons/icons8-print-48.png");
//        
//    }
}
