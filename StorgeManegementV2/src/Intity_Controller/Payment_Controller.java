/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import Intity.Payment;
import Intity.Check_Payment;
import Intity.Cash_Payment;
import DBParameter.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Payment_Controller {

    boolean added = false;
    boolean edited = false;
    boolean deleted = false;
    DBConnection link;

    public Payment_Controller() {
        link = new DBConnection();
    }

    public boolean add_Payment(Payment pay) {
        added = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into payment_data(bond_number,"
                    + " bond_symbol, date, account_number, total_amount, entry_date, note)"
                    + " values(?, ?, ?, ?, ?, ?,?) ;");
            link.myPreparedStat.setInt(1, pay.getBond_number());
            link.myPreparedStat.setString(2, Payment.getSymbol());
            link.myPreparedStat.setDate(3, pay.getDate());
            link.myPreparedStat.setInt(4, pay.getAccount_number());
            link.myPreparedStat.setDouble(5, pay.getTotal_amount());
            link.myPreparedStat.setDate(6, pay.getEntry_date());
            link.myPreparedStat.setString(7, pay.getNote());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return added;
    }

    public void Confirm_Adding_Check(Intity.Check check) {
        // added=false ;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into check_data(check_number, owner_name, value,"
                    + " maturity_date, note, enter_date)values (?, ?, ?, ?, ?, ?) ;");
            link.myPreparedStat.setString(1, check.getCheck_number());
            link.myPreparedStat.setString(2, check.getOwner_name());
            link.myPreparedStat.setDouble(3, check.getValue());
            link.myPreparedStat.setDate(4, check.getMaturity_date());
            link.myPreparedStat.setString(5, check.getNote());
            link.myPreparedStat.setDate(6, check.getEnter_date());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        // return added ;
    }

    public boolean addCheck(Check_Payment checks) {
        added = false;
        for (int index = 0; index < checks.getList_Of_Checks().size(); index++) {
            Confirm_Adding_Check(checks.getList_Of_Checks().get(index));
            add_check_payment(checks.getList_Of_Checks().get(index).getCheck_number(), checks.getBond_number());
            added = true;//we must return here becouse we can't know if it's added or no 
        }
        return added;
    }

    public void add_check_payment(String check_number, int bond_number) {
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into check_payment(check_number, bond_number) values (?,?) ;");
            link.myPreparedStat.setString(1, check_number);
            link.myPreparedStat.setInt(2, bond_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                System.out.println("added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addCash(Cash_Payment cash) {
        added = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into cash_payment(bond_number, amount)values(?,?);");
            link.myPreparedStat.setInt(1, cash.getBond_number());
            link.myPreparedStat.setDouble(2, cash.getAmount());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return added;
    }

    public int getBondNumber() {
        int auto_number = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select max(bond_number)from payment_data;");
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                auto_number = link.groupInfo.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return auto_number + 1;
    }

    public boolean change_availablity(int bond_number, String new_note) {
        boolean updated = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("update payment_data set note=? where bond_number=? ;");
            link.myPreparedStat.setString(1, new_note);
            link.myPreparedStat.setInt(2, bond_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

    public ResultSet searchBondInfo(int bond_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT bond_number,date,account_number,name,total_amount,note\n"
                    + " FROM cappuccino_database.payment_data natural join account where bond_number=1 and payment_data.note != 'cancel';");
            link.myPreparedStat.setInt(1, bond_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    public ResultSet searchBondCheckInfo(int bond_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select check_number,owner_name,value,maturity_date,check_data.note from payment_data natural join check_payment natural join check_data where bond_number=? and payment_data.note != 'cancel' ;");
            link.myPreparedStat.setInt(1, bond_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    public double getTotalCash(int bond_number) {
        double cash = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement(" select sum(amount) from payment_data natural join cash_payment where bond_number=? and payment_data.note != 'cancel' ;");
            link.myPreparedStat.setInt(1, bond_number);
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                cash = link.groupInfo.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cash;
    }

    public double getTotalCeck(int bond_number) {
        double Ceck = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement(" select sum(check_data.value) from payment_data natural join check_payment natural join check_data where bond_number=? and payment_data.note != 'cancel';");
            link.myPreparedStat.setInt(1, bond_number);
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                Ceck = link.groupInfo.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ceck;
    }
}
