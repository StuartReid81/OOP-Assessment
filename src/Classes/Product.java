/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 * @Date 1/2/2018
 * @author Stuart Reid
 * Super class defining the an instance of a product. 
 */
public class Product {
    
    //attributes
    private int productID;
    private String productName;
    private double price;
    private int stockLevel;
    
    
    //getters
    public int getProductID(){return productID;}
    public String getProductName(){return productName;}
    public double getPrice(){return price;}
    public int getStockLevel(){return stockLevel;}
    
    
    //setters
    public void setProductID(int productID){this.productID = productID;}
    public void setProductName(String productName){this.productName = productName;}
    public void setPrice(double price){this.price = price;}
    public void setStockLevel(int stockLevel){this.stockLevel = stockLevel;}
    
    
    /**
     * This is the default constructor of our product class. It defines an 
     * instance of a product with no values stored to its attributes.
     */
    public Product(){}
    
    
    /**
     * This is an overloaded constructor creating an instance of our product 
     * class. It takes in parameters and stores them to the instances attributes.
     * @param productID integer passed in to map to the productID attribute.
     * @param productName string passed in that we map to our productName attribute
     * @param price double passed in to map our price attribute.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock.
     */
    public Product(int productID, String productName, double price, int stockLevel)
    {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    
    
    /**
     * This is an overloaded constructor creating an instance of our product 
     * class. It takes in parameters and stores them to the instances attributes.
     * This version does not take in an ID number and will be used when creating a new
     * product without an ID.
     * @param productName string passed in that we map to our productName attribute
     * @param price double passed in to map our price attribute.
     * @param stockLevel integer passed in to map the number of units of each item that are in stock.
     */
    public Product(String productName, double price, int stockLevel)
    {
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }

}
