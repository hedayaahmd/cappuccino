/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Cash_Payment extends Payment{
    private double amount ; 

    public Cash_Payment(double amount, int bond_number, Date date, int account_number, double total_amount, Date entry_date, String note) {
        super(bond_number, date, account_number, total_amount, entry_date, note);
        this.amount = amount;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
   
    
    
}
