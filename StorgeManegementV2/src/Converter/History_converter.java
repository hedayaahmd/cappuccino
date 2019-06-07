/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Intity.History;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author USER
 */
public class History_converter {

    public ArrayList<History> translate(ResultSet set) throws SQLException {
        ArrayList<History> history = new ArrayList<>();
        while (set.next()) {
            history.add(new History(set.getInt(1), "فاتورة مبيعات للعميل  " + set.getInt(2) + "  ل " + set.getString(3) + " بتاريخ " + set.getDate(6)));
        }
        return history;
    }

}
