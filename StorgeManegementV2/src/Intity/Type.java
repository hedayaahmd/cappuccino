/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

/**
 *
 * @author USER
 */
public class Type {
    private int number ;
    private String name ;
    private double price;
    private double length ; 
    private double width ;
    private double thick ;
    public static String SYMBOL="T";

    public Type(int number, String name, double price, double length, double width, double thick) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.length = length;
        this.width = width;
        this.thick = thick;
    }
    

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
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
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the thick
     */
    public double getThick() {
        return thick;
    }

    /**
     * @param thick the thick to set
     */
    public void setThick(double thick) {
        this.thick = thick;
    }

    /**
     * @return the SYMBOL
     */
    public static String getSYMBOL() {
        return SYMBOL;
    }

    /**
     * @param aSYMBOL the SYMBOL to set
     */
    public static void setSYMBOL(String aSYMBOL) {
        SYMBOL = aSYMBOL;
    }
    
    
    
}
