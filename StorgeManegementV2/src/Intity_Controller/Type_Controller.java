/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import DBParameter.DBConnection;
import Intity.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Type_Controller {

    DBConnection link;

    public Type_Controller() {
        link = new DBConnection();
       
    }

    /**
     *
     * @param type
     * @return true if type is added
     * @throws SQLException
     */
    public boolean add_Type_data(Type type) {
        boolean add = false;
        System.out.println("98");
        try {
            link.myPreparedStat = link.myCon.prepareStatement("INSERT into type_data(type_number,type_symbol,name,price,length,width,thick) values(?,?,?,?,?,?,?)");
            link.myPreparedStat.setInt(1, type.getNumber());
            link.myPreparedStat.setString(2, Type.getSYMBOL());
            link.myPreparedStat.setString(3, type.getName());
            link.myPreparedStat.setDouble(4, type.getPrice());
            link.myPreparedStat.setDouble(5, type.getLength());
            link.myPreparedStat.setDouble(6, type.getWidth());
            link.myPreparedStat.setDouble(7, type.getThick());
            if (link.myPreparedStat.executeUpdate()!=0) {
                add = true;
                System.out.println("add");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return add;
    }
    
    public int getNewRecord(){
        System.out.println("start here ");
        int auto_number=0;
        try {
            System.out.println("at try ");
            link.myPreparedStat=link.myCon.prepareStatement("SELECT max(type_number) from cappuccino_database.type_data ;");
            link.groupInfo=link.myPreparedStat.executeQuery();
            System.out.println("after execyt q");
            while(link.groupInfo.next()){
                
                auto_number=link.groupInfo.getInt(1);
                System.out.println("auto number"+auto_number);
                
            }
        } catch (SQLException ex) {
            System.out.println("****");
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_number+1;
    }

    /**
     *
     * @param type
     * @return true if type is deleted
     * @throws SQLException
     */
    public boolean delete_Type_data(int type_number) {
        boolean delete = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("delete from type_data where type_number=?");
            link.myPreparedStat.setInt(1,type_number);
            if (link.myPreparedStat.executeUpdate()!=0) {
                delete = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return delete;
    }

    public boolean update_type(Type new_type) {
        boolean update = false;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("UPDATE type_data set price=?,`length`=?,width=?,thick=? where type_number=?;\n");
            link.myPreparedStat.setDouble(1, new_type.getPrice());
            link.myPreparedStat.setDouble(2, new_type.getLength());
            link.myPreparedStat.setDouble(3, new_type.getWidth());
            link.myPreparedStat.setDouble(4, new_type.getThick());
            link.myPreparedStat.setInt(5, new_type.getNumber());
            if (link.myPreparedStat.executeUpdate() != 0) {
                update = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    /**
     * to get type number when i find that there exist that type name to put it
     * info in the frame
     *
     * @param type_name
     * @return
     */
    public int getTypeNumber(String type_name) {
        int type_number = 0;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT type_number  from type_data where `name`=?;\n");
            link.myPreparedStat.setString(1, type_name);
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                type_number = link.groupInfo.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type_number;
    }

    public String getTypeName(int type_number) {
        String type_name = "";
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT `name`  from type_data where type_number =?;\n");
            link.myPreparedStat.setInt(1, type_number);
            link.groupInfo = link.myPreparedStat.executeQuery();
            while (link.groupInfo.next()) {
                type_name = link.groupInfo.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type_name;
    }

    public ResultSet getListOfTypes() {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT type_number+\"_\"+type_symbol,name,price  from type_data ");
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
    public ResultSet getListOfTypesWithAllProp() {
        ResultSet set = null;
        try {
            link.myPreparedStat = link.myCon.prepareStatement("SELECT type_number+\"_\"+type_symbol,name,price,length,width,thick from type_data ");
            set = link.myPreparedStat.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Type_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    public ArrayList<Type> search_Type(String type_number) throws SQLException {
        Type type;
        ArrayList<Type> types = new ArrayList<>();
        String sql = "select type_number+\"_\"+type_symbol,`name`,price,`length`,width,thick  from type_data where type_number=?";
        link.myPreparedStat = link.myCon.prepareStatement(sql);
        link.groupInfo = link.myPreparedStat.executeQuery();
        while (link.groupInfo.next()) {
            type = new Type(link.groupInfo.getInt(1), link.groupInfo.getString(2), link.groupInfo.getFloat(3), link.groupInfo.getFloat(4),
                    link.groupInfo.getFloat(5), link.groupInfo.getFloat(6));
            types.add(type);
        }
        return types;

    }

}
