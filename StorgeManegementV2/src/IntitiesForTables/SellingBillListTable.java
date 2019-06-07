/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntitiesForTables;

/**
 *
 * @author USER
 */
public class SellingBillListTable {
     public String type_number ;
    public String type_name ;
    public double price ;
    public int quantity ; 

    public SellingBillListTable(String type_number, String type_name, double price, int quantity) {
        this.type_number = type_number;
        this.type_name = type_name;
        this.price = price;
        this.quantity = quantity;
    }
    
}
