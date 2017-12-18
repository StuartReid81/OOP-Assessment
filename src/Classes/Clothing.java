/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Stuart Reid
 * class created on the 11/12/2017
 * This Class describes the Clothing subclass inheriting from Products
 */
public class Clothing extends Product{
    
    //attribute
    private String measurement;
    
    //getter
    public String getMeasurement(){return measurement;}
    
    //setter
    public void setMeasurement(String measurement){this.measurement = measurement;}
    
    
    //default constructor
    public Clothing(){super();}
    
    
    /**
     * Overloaded constructor passing in attributes for creating a defined instance of our Clothing class
     * @param productID integer passed in to map to the productID attribute via our super constructor.
     * @param productName string passed in that we map to our productName attribute via our super constructor.
     * @param price double passed in to map our price attribute via our super constructor.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock via our super constructor.
     * @param measurement String passed in to map to our measurement attribute.
     */
    public Clothing(int productID, String productName, double price, int stockLevel, String measurement)
    {
        super(productID, productName, price, stockLevel);
        this.measurement = measurement;
    }
    
    
    /**
     * Overloaded constructor passing in attributes for creating a defined instance of our Clothing class. This version does not take in a productID.
     * @param productName string passed in that we map to our productName attribute via our super constructor.
     * @param price double passed in to map our price attribute via our super constructor.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock via our super constructor.
     * @param measurement String passed in to map to our measurement attribute.
     */
    public Clothing(String productName, double price, int stockLevel, String measurement)
    {
        super(productName, price, stockLevel);
        this.measurement = measurement;
    }
    
    @Override
    public String productToString()
    {
        return ("" + super.getProductID() + " " + super.getProductName() + " " + super.getPrice() + " " + super.getStockLevel() + " " + measurement + "");
    }
}
