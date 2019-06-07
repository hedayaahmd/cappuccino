/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import IntitiesForTables.SellingBillListTable;
import IntitiesForTables.SellingBillTable;
import Intity.Selling_Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Selling_Converter {
    public SellingBillTable getBillInfo(ResultSet set) {
        SellingBillTable bill = null;
        try {
            while (set.next()) {
                bill = new SellingBillTable(set.getString(1),set.getDate(2), set.getInt(3), set.getString(4),set.getString(5), set.getDouble(6), set.getDouble(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill;
    }

    public ArrayList<SellingBillListTable> getTheListOfBuyingBill(ResultSet set) {
        ArrayList<SellingBillListTable> list = new ArrayList<>();
        try {
            while (set.next()) {
                list.add(new SellingBillListTable(set.getString(1), set.getString(2), set.getDouble(3), set.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    
    
    
}
