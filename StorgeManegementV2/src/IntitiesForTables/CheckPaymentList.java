/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntitiesForTables;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class CheckPaymentList {

    public String check_number;
    public String owner_name;
    public String bank_account_number;
    public double value;
    public Date maturity_date;
    public String note;

    public CheckPaymentList(String check_number, String owner_name, String bank_account_number, double value, Date maturity_date, String note) {
        this.check_number = check_number;
        this.owner_name = owner_name;
        this.bank_account_number = bank_account_number;
        this.value = value;
        this.maturity_date = maturity_date;
        this.note = note;
    }

}
