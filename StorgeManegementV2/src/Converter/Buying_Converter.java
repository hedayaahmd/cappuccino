/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import IntitiesForTables.BuyingBillListTable;
import IntitiesForTables.BuyingBillTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Buying_Converter {

    public BuyingBillTable getBillInfo(ResultSet set) {
        BuyingBillTable bill = null;
        try {
            while (set.next()) {
                bill = new BuyingBillTable(set.getString(1), set.getDate(2), set.getString(3), set.getDouble(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bill;
    }

    public ArrayList<BuyingBillListTable> getTheListOfBuyingBill(ResultSet set) {
        ArrayList<BuyingBillListTable> list = new ArrayList<>();
        try {
            while (set.next()) {
                list.add(new BuyingBillListTable(set.getString(1), set.getString(2), set.getDouble(3), set.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

}
