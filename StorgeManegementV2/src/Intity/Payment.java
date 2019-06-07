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
public class Payment {
    private int bond_number ;
    private Date date ;
    private int account_number ;
    private double total_amount ;
    private Date entry_date ;
    private String note ;
    private static String Symbol="C";

    public Payment(int bond_number, Date date, int account_number, double total_amount, Date entry_date, String note) {
        this.bond_number = bond_number;
        this.date = date;
        this.account_number = account_number;
        this.total_amount = total_amount;
        this.entry_date = entry_date;
        this.note = note;
    }
    public String getFullBondNumber(){
        return Symbol+"_"+this.getBond_number();
    }
    
    

    /**
     * @return the bond_number
     */
    public int getBond_number() {
        return bond_number;
    }

    /**
     * @param bond_number the bond_number to set
     */
    public void setBond_number(int bond_number) {
        this.bond_number = bond_number;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the account_number
     */
    public int getAccount_number() {
        return account_number;
    }

    /**
     * @param account_number the account_number to set
     */
    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    /**
     * @return the total_amount
     */
    public double getTotal_amount() {
        return total_amount;
    }

    /**
     * @param total_amount the total_amount to set
     */
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    /**
     * @return the entry_date
     */
    public Date getEntry_date() {
        return entry_date;
    }

    /**
     * @param entry_date the entry_date to set
     */
    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the Symbol
     */
    public static String getSymbol() {
        return Symbol;
    }

    /**
     * @param aSymbol the Symbol to set
     */
    public static void setSymbol(String aSymbol) {
        Symbol = aSymbol;
    }
     
    
}
