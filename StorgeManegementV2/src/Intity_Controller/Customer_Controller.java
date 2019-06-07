/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import DBParameter.DBConnection;
import Intity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Customer_Controller {

    DBConnection link;

    /**
     * constructer which create connection with the DB
     */
    public Customer_Controller() {
        link = new DBConnection();
    }

    /**
     *
     * @param Customer_name
     * @return Customer_id
     * @throws SQLException
     */
    public String get_Customer_id(String Customer_name) throws SQLException {
        String sql = "select id from account where name=?";
        link.myPreparedStat = link.myCon.prepareStatement(sql);
        link.myPreparedStat.setString(1, Customer_name);
        link.groupInfo = link.myPreparedStat.executeQuery();
        return link.groupInfo.getString(1);
    }

    /**
     *
     * @param customer_id
     * @return customer_name
     * @throws SQLException
     */
    public String getCustomerName(String customer_id) throws SQLException {
        String sql = "select name from account where id=?";
        link.myPreparedStat = link.myCon.prepareStatement(sql);
        link.myPreparedStat.setString(1, customer_id);
        link.groupInfo = link.myPreparedStat.executeQuery();
        return link.groupInfo.getString(1);
    }

    /**
     *
     * @param customer query to get account of specific customer "report"
     * @throws SQLException
     */
    public void get_AccountofCustomer(Customer customer) throws SQLException {
        String sql = "select sell_date as 'تاريخ البيع',balance_account.account_number as 'رقم الحساب', note as 'البيان',credit_amount as 'دائن',debit_amount as 'مدين' from cappuccino_database.balance_account join selling_bill_data on cappuccino_database.balance_account.bill_number=selling_bill_data.bill_num "
                + "where account_number=?;";
    }//لم يجهزززززز

    /**
     *
     * @param customer
     * @return true if add customer
     * @throws SQLException
     */
    public boolean add_Customer_data(Customer customer) {
        boolean add = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("INSERT into account(account_number,account_symbol,name,phone_number,address,id,registry_Date)values (?,?,?,?,?,?,?)");
            link.myPreparedStat.setInt(1, customer.getAccount_num());
            link.myPreparedStat.setString(2, customer.getSymbol());
            link.myPreparedStat.setString(3, customer.getName());
            link.myPreparedStat.setString(4, customer.getPhone());
            link.myPreparedStat.setString(5, customer.getAddress());
            link.myPreparedStat.setString(6, customer.getId());
            link.myPreparedStat.setDate(7, customer.getRegistry_Date());
            if (link.myPreparedStat.executeUpdate()!=0) {
                add = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return add;

    }

    /**
     *
     * @param customer
     * @return true if delete customer
     * @throws SQLException
     */
    public boolean delete_Customer_data(int customer_number) {
        boolean delete = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("delete from account where account_number=?");
            link.myPreparedStat.setInt(1, customer_number);
            if (link.myPreparedStat.executeUpdate()!=0) {
                delete = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return delete;
    }

    /**
     *
     * @param customer
     * @return true if update customer
     * @throws SQLException
     */
    public boolean update_Customer_data(Customer customer) {
        boolean update = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement( "update account set id=?,name=?,phone_number=?,address=? where account_number=?");
            link.myPreparedStat.setString(1, customer.getId());
            link.myPreparedStat.setString(2, customer.getName());
            link.myPreparedStat.setString(3, customer.getPhone());
            link.myPreparedStat.setString(4, customer.getAddress());
            link.myPreparedStat.setInt(5, customer.getAccount_num());
            if (link.myPreparedStat.executeUpdate() != 0) {
                update = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    /**
     * this function used when full the table of account in payment to get the
     * list of account
     *
     * @return as a ResultSet
     */
    public ResultSet getAccountsList() {
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select account_symbol+\"_\"+account_number,name from account ;");
            link.groupInfo = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return link.groupInfo;
    }

    public int getNewCustomer_Number() {
        int auto_number = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT max(account_number) FROM account");
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                auto_number = link.groupInfo.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_number + 1;
    }

    public ResultSet selectCustomers() {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select account_number+\"_\"+account_symbol,name,phone_number,address,id,registry_Date from account;");
            set = link.groupInfo = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
   

}
