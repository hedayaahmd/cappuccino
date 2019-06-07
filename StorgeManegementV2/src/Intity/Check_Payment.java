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
public class Check_Payment extends Payment{ 
   private ArrayList<Check> list_Of_Checks ;

    public Check_Payment(ArrayList<Check> list_Of_Checks, int bond_number, Date date, int account_number, 
            double total_amount, Date entry_date, String note) {
        super(bond_number, date, account_number, total_amount, entry_date, note);//for the supper 
        this.list_Of_Checks = list_Of_Checks;
    }

    


    /**
     * @return the list_Of_Checks
     */
    public ArrayList<Check> getList_Of_Checks() {
        return list_Of_Checks;
    }

    /**
     * @param list_Of_Checks the list_Of_Checks to set
     */
    public void setList_Of_Checks(ArrayList<Check> list_Of_Checks) {
        this.list_Of_Checks = list_Of_Checks;
    }

    
   
   
   
    
    
    
    
}

