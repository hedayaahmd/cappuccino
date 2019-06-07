/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

/**
 *
 * @author USER
 */
public class Details_of_selling_bill {

    int bill_num;
    int type_number;
    int quantity;

    public Details_of_selling_bill(int bill_num, int type_number,int quantity) {
        this.bill_num = bill_num;
        this.type_number = type_number;
        this.quantity = quantity;
    }

    public int getType_number() {
        return type_number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setType_number(int type_number) {
        this.type_number = type_number;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBill_num() {
        return bill_num;
    }

    public void setBill_num(int bill_num) {
        this.bill_num = bill_num;
    }

}
