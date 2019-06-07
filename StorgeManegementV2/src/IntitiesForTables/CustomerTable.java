/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntitiesForTables;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Rana Obaid
 */
public class CustomerTable  extends RecursiveTreeObject<CustomerTable> {
     public StringProperty account_num ;
     public StringProperty name ; 
     public StringProperty phone ;
     public StringProperty address ;
    public StringProperty id ; 
     public StringProperty registry_Date ;

    public CustomerTable(String account_num, String name, String phone, String address, String id, Date registry_Date) {
        this.account_num = new SimpleStringProperty(account_num);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.id = new SimpleStringProperty(id);
        this.registry_Date = new SimpleStringProperty(registry_Date+"");
    }
     
     
    
    
}
