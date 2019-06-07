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
public class Selling_Bill {

    int bill_num;
    int account_number;
    double total_price;
    double discount;
    Date sell_date;
    String note;
    ArrayList<Details_of_selling_bill> list_of_types;
    public static String Symbol = "A";

    public Selling_Bill(int bill_num, int account_number, double total_price, double discount,
            Date sell_date, String note, ArrayList<Details_of_selling_bill> list_of_types) {
        this.bill_num = bill_num;
        this.account_number = account_number;
        this.total_price = total_price;
        this.discount = discount;
        this.sell_date = sell_date;
        this.note = note;
        this.list_of_types = list_of_types;
    }

    public void setBill_num(int bill_num) {
        this.bill_num = bill_num;
    }

    public void setList_of_types(ArrayList<Details_of_selling_bill> list_of_types) {
        this.list_of_types = list_of_types;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setSell_date(Date sell_date) {
        this.sell_date = sell_date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getBill_num() {
        return bill_num;
    }

    public String getBill_symbol() {
        return this.Symbol;
    }

    public ArrayList<Details_of_selling_bill> getList_of_types() {
        return list_of_types;
    }

    public int getAccount_number() {
        return account_number;
    }

    public double getTotal_price() {
        return total_price;
    }

    public double getDiscount() {
        return discount;
    }

    public Date getSell_date() {
        return sell_date;
    }

    public String getNote() {
        return note;
    }

    public String getFullBillNumber() {
        return Symbol + "_" + this.getBill_num();
    }
}
