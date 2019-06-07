/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import DBParameter.DBConnection;
import Intity.Details_of_selling_bill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import Intity.Selling_Bill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Selling_bill_controller {

    boolean added = false;
    boolean edited = false;
    boolean deleted = false;
    DBConnection link;
    //ليش بتعملي كلشي من نوع سترنجج ؟؟؟؟ 

    public Selling_bill_controller() {
        link = new DBConnection();
    }

    //لازم يتم التعديل على حساب الشخص مبين بالكنترولر تبع الواجهة 
    public boolean add_selling_bill(Selling_Bill obj) {
        try {
            //  int bill_number = get_bill_number() + 1; *** بستدعيه لما اكبس على نيو بتن ومن هناك بوخد الرقم
            String query = " insert into cappuccino_database.selling_bill_data(bill_num , bill_symbol, account_number, total_price, discount, sell_date , note) values(?,?,?,?,?,?,?)";
            // Connection c = getconnection(); ***we have connection already with limk obj

            link.myPreparedStat = link.myCon.prepareStatement(query);
            System.out.println("******");

            int m = 0;
            //  stm.setString (0,String.valueOf(bill_number));
            link.myPreparedStat.setInt(1, obj.getBill_num());
            link.myPreparedStat.setString(2, obj.getBill_symbol());
            link.myPreparedStat.setInt(3, obj.getAccount_number());
            link.myPreparedStat.setDouble(4, obj.getTotal_price());
            link.myPreparedStat.setDouble(5, obj.getDiscount());
            link.myPreparedStat.setDate(6, obj.getSell_date());
            link.myPreparedStat.setString(7, obj.getNote());

            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }
    //*** هون الاشي اللي غيرته هو انه فصلت الميثودات عن بعض بضيف عاول جدول وبعدها عالتاني الخطا كان انه ترتيب الكولوم مش نفس الكويري 
    //وبرضو مش نفس نوع الداتا انت مدخلتيهم سترينج هون 

    public void addTypes(ArrayList<Details_of_selling_bill> details_list) {
        for (int i = 0; i < details_list.size();) {
            if (addDetails(details_list.get(i))) {
                i++;
            }
        }

    }

    public boolean addDetails(Details_of_selling_bill details) {
        added = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("insert into cappuccino_database.details_of_selling_bill (bill_number,type_number,quantity)values(?,?,?)");
            link.myPreparedStat.setInt(1, details.getBill_num());
            link.myPreparedStat.setInt(2, details.getType_number());
            link.myPreparedStat.setInt(3, details.getQuantity());
            if (link.myPreparedStat.executeUpdate() != 0) {
                added = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return added;
    }

    public int get_bill_number() {
        int n = 0;
        String q = "select max(bill_num) from  cappuccino_database.selling_bill_data";
        try {
            link.myPreparedStat = link.myCon.prepareStatement(q);
            link.groupInfo = link.myPreparedStat.executeQuery();

            while (link.groupInfo.next()) {
                n = link.groupInfo.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return n + 1;
    }
    //عملية التعديل هون كلها غلط 
    // الخطوات :
    //1.احذف كل التايبس الموجودة باسم هاي الفاتورة 
    // 2.اضيف كل اشي جديد 
    //3.تعديل الفاتورة نفسها
    //4.التعديل على حساب الشخص اضيف مدين 
    //5.ارجع دائن بنفس الوقت بنفس المبلغ الجديد 

    public boolean update_selling_bill(Selling_Bill new_obj) {
        try {
            edited = false;
            //child will be edited first
            link.myPreparedStat = link.myCon.prepareStatement(" update  cappuccino_database.selling_bill_data set total_price= ?, discount= ?, note = ? where bill_num = ? ");
            link.myPreparedStat.setDouble(1, new_obj.getTotal_price());
            link.myPreparedStat.setDouble(2, new_obj.getDiscount());
            link.myPreparedStat.setString(3, new_obj.getNote());
            link.myPreparedStat.setInt(4, new_obj.getBill_num());
            if (link.myPreparedStat.executeUpdate() != 0) {
                edited = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return edited;
    }

    public boolean delete_types(int bill_number) {
        try {
            deleted = false;
            String query = "DELETE from details_of_selling_bill where bill_num=?;";
            link.myPreparedStat = link.myCon.prepareStatement(query);
            link.myPreparedStat.setInt(1, bill_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                deleted = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }

    public boolean delete_selling_bill(int bill_number) {
        deleted = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("delete from selling_bill_data where bill_num=?;");
            link.myPreparedStat.setInt(1, bill_number);
            if (link.myPreparedStat.executeUpdate() != 0) {
                deleted = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;
    }

    public double getPrevouisPrice(int bill_num) {
        double price = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT total_price from selling_bill_data where bill_num=?;");
            link.myPreparedStat.setInt(1, bill_num);
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                price = link.groupInfo.getDouble(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public ResultSet searchSell(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select bill_symbol+\"_\"+bill_num,sell_date,account_symbol+\"_\"+selling_bill_data.account_number,account.name,note from selling_bill_data natural  join details_of_selling_bill natural join type_data \n"
                    + "join account where account.account_number=selling_bill_data.account_number and bill_num=? ;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }//not used

    public ResultSet searchSellList(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select type_symbol+\"_\"+type_number,types_data.name,price from selling_bill_data natural join details_of_selling_bill  natural join type_data where  bill_num=?;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }//not used

    public ResultSet searchSells() {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select bill_symbol+\"_\"+bill_num,sell_date,account_symbol+\"_\"+selling_bill_data.account_number,account.name,note from selling_bill_data natural  join details_of_selling_bill natural join type_data \n"
                    + "join account where account.account_number=selling_bill_data.account_number and bill_num;");
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }//not used

    /**
     * this used when search a bill
     *
     * @param bill_number
     * @return result set of bill info
     */
    public ResultSet searchByNumber(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT bill_num,sell_date,selling_bill_data.account_number,account.name,note,total_price,discount FROM cappuccino_database.selling_bill_data,account where selling_bill_data.account_number=account.account_number and bill_num=?;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    /**
     * used when search a bill
     *
     * @param bill_number
     * @return result set of details list of types
     */

    public ResultSet searchDetailsByNumber(int bill_number) {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("select details_of_selling_bill.type_number,type_data.name,type_data.price,details_of_selling_bill.quantity\n"
                    + "from selling_bill_data ,details_of_selling_bill ,type_data\n"
                    + "where selling_bill_data.bill_num=details_of_selling_bill.bill_number and details_of_selling_bill.type_number=type_data.type_number and selling_bill_data.bill_number=?;");
            link.myPreparedStat.setInt(1, bill_number);
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Buying_Bill_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
    
    public ResultSet getHistory(){
        ResultSet set =null ;
        try {
            link.myPreparedStat=link.myCon.prepareStatement("SELECT bill_num, account_number,name, total_price, discount, sell_date, note FROM cappuccino_database.selling_bill_data natural join account order by sell_date desc limit 7;");
            set=link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Selling_bill_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  set ;
    }

}
