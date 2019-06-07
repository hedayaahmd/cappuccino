/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;
import DBParameter.DBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Account_Controller {
    boolean done =false ;
    DBConnection link ;

    public Account_Controller() {
        link=new DBConnection();
    }
    
    
    public boolean updateAccount(int account_number,double credit_amount/*مدين*/,double debit_amount,String bill_number){
        done=false ;
        try {
            link.myPreparedStat=link.myCon.prepareStatement("insert into balance_account (account_number, credit_amount, "
                    + "debit_amount, bill_number) values (?,?,?,?) ;");
            link.myPreparedStat.setInt(1, account_number);
            link.myPreparedStat.setDouble(2, credit_amount);
            link.myPreparedStat.setDouble(3, debit_amount);
            link.myPreparedStat.setString(4, bill_number);
            if(link.myPreparedStat.executeUpdate()!=0){
                done=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }
    
    public double getBalance(String customer_number){
        double balance =0 ;
        try {
            link.myPreparedStat=link.myCon.prepareStatement("select sum(credit_amount)-sum(debit_amount) as balance from balance_account where account_number=? ");
            link.myPreparedStat.setString(1, customer_number);
            link.groupInfo=link.myPreparedStat.executeQuery();
            while(link.groupInfo.next()){
                balance=link.groupInfo.getDouble("balance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balance ;
    }
      public double getDebit(String customer_number){
        double balance =0 ;
        try {
            link.myPreparedStat=link.myCon.prepareStatement("select sum(debit_amount) as balance from balance_account where account_number=? ");
            link.myPreparedStat.setString(1, customer_number);
            link.groupInfo=link.myPreparedStat.executeQuery();
            while(link.groupInfo.next()){
                balance=link.groupInfo.getDouble("balance");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balance ;
    }
    
}
