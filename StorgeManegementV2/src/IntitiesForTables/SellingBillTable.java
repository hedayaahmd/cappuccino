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
public class SellingBillTable {
    public String bill_number ;
    public Date sell_date;
    public int customr_number ; 
    public String customer_name ; 
    public String note ;
    public double total_price ; 
    public double discount ;

    public SellingBillTable(String bill_number, Date sell_date, int customr_number, String customer_name, String note, double total_price, double discount) {
        this.bill_number = bill_number;
        this.sell_date = sell_date;
        this.customr_number = customr_number;
        this.customer_name = customer_name;
        this.note = note;
        this.total_price = total_price;
        this.discount = discount;
    }
    
    
}
