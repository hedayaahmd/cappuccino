/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Customer {
    private int account_num ;
    private String name ;
    private String phone ;
    private String address ;
    private String id ;
    private Date registry_Date ;
    private static String symbol="C";

    public Customer(int account_num, String name, String phone, String address, String id, Date registry_Date) {
        this.account_num = account_num;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.registry_Date = registry_Date;
    }
    
    

    /**
     * @return the account_num
     */
    public int getAccount_num() {
        return account_num;
    }

    /**
     * @param account_num the account_num to set
     */
    public void setAccount_num(int account_num) {
        this.account_num = account_num;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the registry_Date
     */
    public Date getRegistry_Date() {
        return registry_Date;
    }

    /**
     * @param registry_Date the registry_Date to set
     */
    public void setRegistry_Date(Date registry_Date) {
        this.registry_Date = registry_Date;
    }

    /**
     * @return the symbol
     */
    public static String getSymbol() {
        return symbol;
    }

    /**
     * @param aSymbol the symbol to set
     */
    public static void setSymbol(String aSymbol) {
        symbol = aSymbol;
    }
    
    
}
