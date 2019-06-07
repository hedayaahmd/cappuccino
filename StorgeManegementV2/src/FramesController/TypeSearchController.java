/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FramesController;

import IntitiesForTables.TypeTable;
import Intity.Type;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import Intity_Controller.Type_Controller;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TypeSearchController implements Initializable {

    ArrayList<Type> list = new ArrayList<Type>();
    Type_Controller controller ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller=new Type_Controller();
        addToTable(controller.getListOfTypesWithAllProp());
        types_table.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        types_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<TypeTable>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<TypeTable>> observable, TreeItem<TypeTable> oldValue, TreeItem<TypeTable> newValue) {
                if(!types_table.getSelectionModel().isEmpty()){
                list.add(new Type(Integer.parseInt(newValue.getValue().number.get()), newValue.getValue().name.get(), Double.parseDouble(newValue.getValue().price.get()),
                        Double.parseDouble(newValue.getValue().length.get()), Double.parseDouble(newValue.getValue().width.get()), Double.parseDouble(newValue.getValue().thick.get())));
            }
            }
        });
    }

    @FXML
    private JFXButton confirm_btt;

    @FXML
    private TextField search_txt;

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

    public ArrayList<Type> getList() {
        return list;
    }

    @FXML
    void confirm_btt_clicked(ActionEvent event) {
        confirm_btt.getScene().getWindow().hide();

    }

    public int getTheIntgerNumber(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, "_");
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }

    public void addToTable(ResultSet set) {
        System.out.println("add to the table");
        typeNumber_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TypeTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TypeTable, String> param) {
                return param.getValue().getValue().number;
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
//        ObservableList selectedItems = types_table.getSelectionModel().getSelectedItems();
//        ArrayList<String> selectedIDs = new ArrayList<String>();
//        for (int i = 0; i < selectedItems.size(); i++) {
//            ObservableList<String> innerSelectedItems = FXCollections.observableArrayList(selectedItems.get(i).toString());
//
//            for (String ID : innerSelectedItems) {
//                selectedIDs.add(ID);
//            }
//
//        }
//        ListIterator<String> iterator = selectedIDs.listIterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//
//        }

}
