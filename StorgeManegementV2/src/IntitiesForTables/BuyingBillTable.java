/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntitiesForTables;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class BuyingBillTable {

    public String bill_number;
    public Date date;
    public String note;
    public double total_price;

    public BuyingBillTable(String bill_number, Date date, String note, double total_price) {
        this.bill_number = bill_number;
        this.date = date;
        this.note = note;
        this.total_price = total_price;
    }

    

}
