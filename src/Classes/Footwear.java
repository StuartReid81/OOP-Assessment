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
 * This Class describes the Footwear subclass inheriting from Products
 */
public class Footwear extends Product{
    
    //attribute
    private int size;
    
    //getter
    public int getSize(){return size;}
    
    //setter
    public void setSize(int size){this.size = size;}
    
    
    //default constructor
    public Footwear(){super();}
    
    
    /**
     * Overloaded constructor passing in attributes for creating a defined instance of our Clothing class
     * @param productID integer passed in to map to the productID attribute via our super constructor.
     * @param productName string passed in that we map to our productName attribute via our super constructor.
     * @param price double passed in to map our price attribute via our super constructor.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock via our super constructor.
     * @param size integer passed in to map to our size attribute.
     */
    public Footwear(int productID, String productName, double price, int stockLevel, int size)
    {
        super(productID, productName, price, stockLevel);
        this.size = size;
    }
    
    
    /**
     * Overloaded constructor passing in attributes for creating a defined instance of our Clothing class. This version does not take in a productID.
     * @param productName string passed in that we map to our productName attribute via our super constructor.
     * @param price double passed in to map our price attribute via our super constructor.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock via our super constructor.
     * @param size integer passed in to map to our size attribute.
     */
    public Footwear(String productName, double price, int stockLevel, int size)
    {
        super(productName, price, stockLevel);
        this.size = size;
    }
    
        @Override
    public String productToString()
    {
        return ("Item No: " + super.getProductID() + ", Name: " + super.getProductName() + ", Price: £" + String.format("%.2f", super.getPrice()) + ", Stock Available: " + super.getStockLevel() + ", Size: " + size + "");
    }
}
