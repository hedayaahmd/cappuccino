/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import IntitiesForTables.CheckPaymentList;
import IntitiesForTables.PaymentTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Payment_Converter {
    
    public PaymentTable getBondInfo(ResultSet set ){ 
        PaymentTable bond = null ; 
        try {
            while (set.next()){
                bond =new  PaymentTable(set.getString(1), set.getDate(2), set.getInt(3),set.getString(4), set.getDouble(5), set.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bond ;
    }
    
    public ArrayList<CheckPaymentList>getTheListOfCheck(ResultSet set ){ 
        ArrayList<CheckPaymentList> list =new ArrayList<>();
        try {
            while(set.next()){
                list.add(new CheckPaymentList(set.getString(1),set.getString(2), set.getString(3),set.getDouble(4), set.getDate(5),set.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
    
}
