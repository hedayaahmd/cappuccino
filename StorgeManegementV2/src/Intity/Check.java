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
public class Check {
    private String check_number ;
    private String owner_name; 
    private String bank_account_number;
    private double value ;
    private Date maturity_date ;
    private String note ;
    private Date enter_date ; 

    public Check(String check_number, String owner_name, String bank_account_number, double value, Date maturity_date, String note, Date enter_date) {
        this.check_number = check_number;
        this.owner_name = owner_name;
        this.bank_account_number = bank_account_number;
        this.value = value;
        this.maturity_date = maturity_date;
        this.note = note;
        this.enter_date = enter_date;
    }

   
    

    /**
     * @return the check_number
     */
    public String getCheck_number() {
        return check_number;
    }

    /**
     * @param check_number the check_number to set
     */
    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    /**
     * @return the owner_name
     */
    public String getOwner_name() {
        return owner_name;
    }

    /**
     * @param owner_name the owner_name to set
     */
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the maturity_date
     */
    public Date getMaturity_date() {
        return maturity_date;
    }

    /**
     * @param maturity_date the maturity_date to set
     */
    public void setMaturity_date(Date maturity_date) {
        this.maturity_date = maturity_date;
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
     * @return the bank_account_number
     */
    public String getBank_account_number() {
        return bank_account_number;
    }

    /**
     * @param bank_account_number the bank_account_number to set
     */
    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }
    
    
}
