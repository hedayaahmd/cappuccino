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
public class AccountSearchTable extends RecursiveTreeObject<AccountSearchTable>{
    public StringProperty account_num ;
    public StringProperty account_name ; 

    public AccountSearchTable(String account_num ,String account_name ) {
        this.account_num=new SimpleStringProperty(account_num);
        this.account_name=new SimpleStringProperty(account_name);
    }
    

    
}
