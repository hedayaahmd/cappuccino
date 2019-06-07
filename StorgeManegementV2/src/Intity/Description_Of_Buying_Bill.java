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
public class Description_Of_Buying_Bill {

    int bill_number;
    private int type_number;
    int quantity;

    public Description_Of_Buying_Bill(int bill_number, int type_number, int quantity) {
        this.bill_number = bill_number;
        this.type_number = type_number;
        this.quantity = quantity;
    }

    public int get_quantity() {
        return this.quantity;
    }

    public void set_quantity(int quantity) {
        this.quantity = quantity;
    }

    public int get_bill_number() {
        return this.bill_number;
    }

    public void set_bill_number(int bill_number) {
        this.bill_number = bill_number;
    }
    
    /**
     * @return the type_number
     */
    public int getType_number() {
        return type_number;
    }

    /**
     * @param type_number the type_number to set
     */
    public void setType_number(int type_number) {
        this.type_number = type_number;
    }

}
