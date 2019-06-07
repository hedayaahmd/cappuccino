/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntitiesForTables;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class TypeTable extends RecursiveTreeObject<TypeTable> {

    public StringProperty number;
    public StringProperty name;
    public StringProperty price;
    public StringProperty length;
    public StringProperty width;
    public StringProperty thick;

    public TypeTable(String number, String name, double price, double length, double width, double thick) {
        this.number = new SimpleStringProperty(number + "");
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price + "");
        this.length = new SimpleStringProperty(length + "");
        this.width = new SimpleStringProperty(width + "");
        this.thick = new SimpleStringProperty(thick + "");
    }

}
