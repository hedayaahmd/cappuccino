/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import DBParameter.DBConnection;
import Intity.Buying_Bill;
import Intity.Description_Of_Buying_Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Buying_Bill_Controller {

    DBConnection link;

    public Buying_Bill_Controller() {
        link = new DBConnection();
    }

    public int get_bill_number() {
        int n = 0;
        String q = "select max(bill_number) from  cappuccino_database.buying_bill_data";
        try {
            link.myPreparedStat = link.myCon.prepareStatement(q);
            link.groupInfo = link.myPreparedStat.executeQuery();

            while (link.groupInfo.next()) {
                n = link.groupInfo.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n + 1;
    }

    public boolean Add_buying_Bill(Buying_Bill bill) {
        boolean added = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into buying_bill_data(bill_number,bill_symbol,total_price,date,note,enter_date) values(?,?,?,?,?,?)");
            link.myPreparedStat.setInt(1, bill.getBill_number());
            link.myPreparedStat.setString(2, bill.getBill_Symbol());
            link.myPreparedStat.setDouble(3, bill.getTotal_price());
            link.myPreparedStat.setDate(4, bill.getDate());
            link.myPreparedStat.setString(5, bill.getNote());
            link.myPreparedStat.setDate(6, bill.getEnter_date());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }

    public void addTypes(ArrayList<Description_Of_Buying_Bill> types_list) {
        for (int i = 0; i < types_list.size();) {
            if (addTypesList(types_list.get(i))) {
                i++;
            }
        }

    }

    public boolean addTypesList(Description_Of_Buying_Bill type) {
        boolean added = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into details_of_buying_bill values(?,?,?)");
            link.myPreparedStat.setInt(1, type.get_bill_number());
            link.myPreparedStat.setInt(2, type.getType_number());
            link.myPreparedStat.setInt(3, type.get_quantity());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }

    public ResultSet Read_Buying_Bill(Buying_Bill bill) throws SQLException {
        String sql = "select * from buying_bill_data natural join details_of_buying_bill";
        link.groupInfo = link.myPreparedStat.executeQuery();
        return link.groupInfo;
    }

    public boolean update_buying_bill(Buying_Bill bill) {
        boolean edited = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("UPDATE buying_bill_data set total_price=? ,note=? where bill_number=? ;");
            link.myPreparedStat.setDouble(1, bill.getTotal_price());
            link.myPreparedStat.setString(2, bill.getNote());
            link.myPreparedStat.setInt(3, bill.getBill_number());
            if (link.myPreparedStat.executeUpdate() != 0) {
                edited = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return edited;
    }

    public boolean deleteTypeList(int bill_number) {
        boolean deleted = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("DELETE from details_of_buying_bill where bill_number=? ;");
            link.myPreparedStat.setInt(1, bill_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }

    public boolean delete_buying_bill(int bill_number) {
        boolean deleted = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("delete from buying_bill_data where bill_number=?");
            link.myPreparedStat.setInt(1, bill_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                deleted = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return deleted;
    }
    public ResultSet searchByNumber(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select buying_bill_data.bill_number,date,note ,buying_bill_data.total_price from buying_bill_data where bill_number=?;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    public ResultSet searchDetailsByNumber(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select details_of_buying_bill.type_number,type_data.name,type_data.price,details_of_buying_bill.quantity\n"
                    + "from buying_bill_data ,details_of_buying_bill ,type_data\n"
                    + "where buying_bill_data.bill_number=details_of_buying_bill.bill_number  and details_of_buying_bill.type_number=type_data.type_number and buying_bill_data.bill_number=?;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }


}
