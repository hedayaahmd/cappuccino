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
public class Balance_Account {
    private String account_number ;
    private double credit_amount ; //دائن
    private double debit_amount ;//مدين
    private String bill_number ;

    public Balance_Account(String account_number, double credit_amount, double debit_amount, String bill_number) {
        this.account_number = account_number;
        this.credit_amount = credit_amount;
        this.debit_amount = debit_amount;
        this.bill_number = bill_number;
    }
    

    /**
     * @return the account_number
     */
    public String getAccount_number() {
        return account_number;
    }

    /**
     * @param account_number the account_number to set
     */
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    /**
     * @return the credit_amount
     */
    public double getCredit_amount() {
        return credit_amount;
    }

    /**
     * @param credit_amount the credit_amount to set
     */
    public void setCredit_amount(double credit_amount) {
        this.credit_amount = credit_amount;
    }

    /**
     * @return the debit_amount
     */
    public double getDebit_amount() {
        return debit_amount;
    }

    /**
     * @param debit_amount the debit_amount to set
     */
    public void setDebit_amount(double debit_amount) {
        this.debit_amount = debit_amount;
    }

    /**
     * @return the bill_number
     */
    public String getBill_number() {
        return bill_number;
    }

    /**
     * @param bill_number the bill_number to set
     */
    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }
    
    
}

