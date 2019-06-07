/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

import DBParameter.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Login {
    DBParameter.DBConnection link ;

    public Login() {
        link=new DBConnection();
        System.out.println("in login");
    }
    
    
    

    public String validateLogin(String user_name ,String password) throws SQLException{
        System.out.println(user_name+" "+password);
        String name ="";
           link.myPreparedStat=link.myCon.prepareStatement("SELECT user_name,password from user_data where user_name=? and password=? ;");
           link.myPreparedStat.setString(1, user_name);
           link.myPreparedStat.setString(2, password);
           link.groupInfo=link.myPreparedStat.executeQuery();
               while(link.groupInfo.next()){
                   name=link.groupInfo.getString("user_name");
                   System.out.println(name+"*");
               }
        
        return name;
        
    }
    
    
}
