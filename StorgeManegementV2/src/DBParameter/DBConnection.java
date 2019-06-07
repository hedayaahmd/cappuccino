/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBParameter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DBConnection {
   

    public PreparedStatement myPreparedStat;
    public ResultSet groupInfo;
    public Connection myCon;
    String url = "jdbc:mysql://localhost:3306/cappuccino_database?zeroDateTimeBehavior=convertToNull";
    String sql_user = "root";
    String sql_password = "159753";

    public DBConnection() {
        if(connect()){
            System.out.println("connect");
        }else{
            System.out.println("not connect");
        }
    }

    

    public boolean connect() {
        boolean connect = false;
        try {
            myCon = DriverManager.getConnection(url, sql_user, sql_password);
            connect = true;
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }
    
   
    
    

}
