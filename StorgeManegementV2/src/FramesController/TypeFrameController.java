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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import Intity.Type;
import Intity_Controller.Type_Controller;
import IntitiesForTables.TypeTable;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import Utilites.Notifications_store;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TypeFrameController implements Initializable {

    Type_Controller type_controller;
    Type type_created ;
    Notifications_store notification ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         type_controller = new Type_Controller();
        addToTheTable(type_controller.getListOfTypesWithAllProp());
        notification=new Notifications_store();
        search_txt.textProperty().addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 if(newValue.equals("")){
                     addToTheTable(type_controller.getListOfTypesWithAllProp());
                 }
                else{
               types_table.setPredicate(new Predicate<TreeItem<TypeTable>>() {
                   @Override
                   public boolean test(TreeItem<TypeTable> t) {
                       boolean flag =t.getValue().name.getValue().equals(newValue)|| t.getValue().number.getValue().equals(newValue);
                       return flag;
                   }
               });
             }
             }
         });
        types_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<TypeTable>>() {
             @Override
             public void changed(ObservableValue<? extends TreeItem<TypeTable>> observable, TreeItem<TypeTable> oldValue, TreeItem<TypeTable> newValue) {
                 if(!types_table.getSelectionModel().isEmpty()){
                       TypeTable type_selected=newValue.getValue();
                      fillTheText(type_selected);
                 }            
                 }                   
         }); 
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
    private TextField typeWidth_txt;

    @FXML
    private TextField typeLength_txt;

    @FXML
    private TextField typeThick_txt;

    @FXML
    private TextField typeNmae_txt;

    @FXML
    private TextField typeNumber_txt;

    @FXML
    private TextField typePrice_txt;
    @FXML
    private TextField  search_txt;

    @FXML
    private JFXTreeTableView<TypeTable> types_table;
    @FXML
    private TreeTableColumn<TypeTable, String> typeNumber_col;

    @FXML
    private TreeTableColumn<TypeTable, String> typeName_col;

    @FXML
    private TreeTableColumn<TypeTable, String> typePrice_col;

    @FXML
    private TreeTableColumn<TypeTable, String> typeLength_col;

    @FXML
    private TreeTableColumn<TypeTable, String> typeWidth_col;

    @FXML
    private TreeTableColumn<TypeTable, String> typeThick_col;

    @FXML
    void add_btt_selected(ActionEvent event) {
        if (addType()) {
         notification.saved("صنف جديد");
        } else {
          notification.error("إضافة صنف جديد");
        }

    }

    @FXML
    void delete_btt_selected(ActionEvent event) {
        if (deleteType()) {
           notification.deleted("صنف جديد");
        } else {
            notification.error("حذف بيانات صنف ");
        }
    }

    @FXML
    void edit_btt_selected(ActionEvent event) {
        if (updateType()) {
          notification.updated("بيانات الصنف ");
        } else {
          notification.error("تعديل بيانات الصنف ");
        }
    }

    @FXML
    void new_btt_selected(ActionEvent event) {
        System.out.println("new button selected");
        emptyText();
        System.out.println("after execute empty text ");
    }

    @FXML
    void print_btt_selected(ActionEvent event) {

    }

    public int getTheTypeIntgerNumber() {
        StringTokenizer tokenizer = new StringTokenizer(typeNumber_txt.getText(), "_");
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }

    public void addToTheTable(ResultSet set) {
        System.out.println("add to the table");
        typeNumber_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().number ;
            }
        });
        typeName_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().name;
            }
        });
        typeLength_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().length;
            }
        });
        typePrice_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().price;
            }
        });
        typeWidth_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().width;
            }
        });
        typeThick_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().thick;
            }
        });
        ObservableList<TypeTable> list = FXCollections.observableArrayList();
        try {
            while (set.next()) {
                list.add(new TypeTable(set.getString(1), set.getString(2), set.getDouble(3), set.getDouble(4), set.getDouble(5), set.getDouble(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<IntitiesForTables.TypeTable> root = new RecursiveTreeItem<IntitiesForTables.TypeTable>(list, RecursiveTreeObject::getChildren);

        types_table.setRoot(root);
        types_table.setShowRoot(false);
        System.out.println("done adding");

    }

    public void emptyText() {
        System.out.println("at empty before get the new type number");
        typeNmae_txt.setText("");
        String auto =getNewTypeNumber();
        System.out.println(auto+"*");
         System.out.println("at empty after get the new type number");
        typeNumber_txt.setText(auto);
        typePrice_txt.setText("0.00");
        typeLength_txt.setText("");
        typeWidth_txt.setText("");
        typeThick_txt.setText("");

    }

    public String getNewTypeNumber() {
        System.out.println("at new type number ");
        return Type.SYMBOL+ "_" + type_controller.getNewRecord();
    }

    public boolean addType() {
        boolean add = false;
        Type type_obj = new Type(getTheTypeIntgerNumber(), typeNmae_txt.getText(), Double.parseDouble(typePrice_txt.getText()),
                Double.parseDouble(typeLength_txt.getText()), Double.parseDouble(typeWidth_txt.getText()), Double.parseDouble(typeThick_txt.getText()));
        if (type_controller.add_Type_data(type_obj)) {
            System.out.println(add);
            add = true;
            System.out.println(add);
            addToTheTable(type_controller.getListOfTypesWithAllProp());
            type_created=type_obj;
        }
        System.out.println(add);
        return add;
    }

    public boolean deleteType() {
        System.out.println("at add");
        boolean delete = false;
        if (type_controller.delete_Type_data(getTheTypeIntgerNumber())) {
            delete = true;
            addToTheTable(type_controller.getListOfTypesWithAllProp());
            emptyText();

        }
        return delete;
    }

    public boolean updateType() {
        boolean update = false;
        Type type_obj = new Type(getTheTypeIntgerNumber(), typeNmae_txt.getText(), Double.parseDouble(typePrice_txt.getText()),
                Double.parseDouble(typeLength_txt.getText()), Double.parseDouble(typeWidth_txt.getText()), Double.parseDouble(typeThick_txt.getText()));
        if (type_controller.update_type(type_obj)) {
            update = true;
            addToTheTable(type_controller.getListOfTypesWithAllProp());
        }
        return update;
    }
    public Type  getTypeCreated(){
        return type_created;
    }
    
    public void fillTheText(TypeTable type ){
        typeNmae_txt.setText(type.name.get());
        String auto =getNewTypeNumber();
        typeNumber_txt.setText(Type.SYMBOL+"_"+type.number.get());
        typePrice_txt.setText(type.price.get());
        typeLength_txt.setText(type.length.get());
        typeWidth_txt.setText(type.width.get());
        typeThick_txt.setText(type.thick.get());
    }

}
