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
public class PaymentTable {

    public String bond_number;
    public Date date;
    public int customer_number;
    public String customer_name;
    public double total_amount;
    public String note;

    public PaymentTable(String bond_number, Date date, int customer_number, String customer_name, double total_amount, String note) {
        this.bond_number = bond_number;
        this.date = date;
        this.customer_number = customer_number;
        this.customer_name = customer_name;
        this.total_amount = total_amount;
        this.note = note;
    }

}
