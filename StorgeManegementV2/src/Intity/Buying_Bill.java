/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Buying_Bill {

    private int bill_number;
    private ArrayList<Description_Of_Buying_Bill> list_of_types;
    private double total_price;
    private Date date;
    private String note;
    public static String bill_Symbol = "N";
    private Date enter_date;

    public Buying_Bill(int bill_number, ArrayList<Description_Of_Buying_Bill> list_of_types, double total_price, Date date, String note, Date enter_date) {
        this.bill_number = bill_number;
        this.list_of_types = list_of_types;
        this.total_price = total_price;
        this.date = date;
        this.note = note;
        this.enter_date = enter_date;
    }

    

    /**
     * @return the enter_date
     */
    public Date getEnter_date() {
        return enter_date;
    }

    /**
     * @param enter_date the enter_date to set
     */
    public void setEnter_date(Date enter_date) {
        this.enter_date = enter_date;
    }

    /**
     * @return the bill_number
     */
    public int getBill_number() {
        return bill_number;
    }

    /**
     * @param bill_number the bill_number to set
     */
    public void setBill_number(int bill_number) {
        this.bill_number = bill_number;
    }

    /**
     * @return the list_of_types
     */
    public ArrayList<Description_Of_Buying_Bill> getList_of_types() {
        return list_of_types;
    }

    /**
     * @param list_of_types the list_of_types to set
     */
    public void setList_of_types(ArrayList<Description_Of_Buying_Bill> list_of_types) {
        this.list_of_types = list_of_types;
    }

    /**
     * @return the total_price
     */
    public double getTotal_price() {
        return total_price;
    }

    /**
     * @param total_price the total_price to set
     */
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
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
     * @return the bill_Symbol
     */
    public static String getBill_Symbol() {
        return bill_Symbol;
    }

    /**
     * @param aBill_Symbol the bill_Symbol to set
     */
    public static void setBill_Symbol(String aBill_Symbol) {
        bill_Symbol = aBill_Symbol;
    }
    
}
