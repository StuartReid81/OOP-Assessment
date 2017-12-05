/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Stuart Reid
 * Class defining order lines for our order class
 *
 */
public class OrderLine {
    
    //attributes
    private int productLineID;
    private Product product;
    private int quantity;
    private double lineTotal;
    
    
    //getters
    public int getProductLineID(){return productLineID;}
    public Product getProduct(){return product;}
    public int getQuantity(){return quantity;}
    public double getLineTotal(){return lineTotal;}
    
    
    //setters
    public void setProductLineID(int productLineID){this.productLineID = productLineID;}
    public void setProduct(Product product){this.product = product;}
    public void setQuantity(int quantity){this.quantity = quantity;}
    public void setLineTotal(double lineTotal){this.lineTotal = lineTotal;}
    
    
    /**
     * 
     * This is the default constructor giving us an undefined instance of the OrderLine class
     * 
     */
    public OrderLine(){}
    
    /**
     * 
     * This is the overloaded constructor for our OrderLine class, it takes in parameters and sets them as the
     * instances attributes.
     * @param productLineID this takes in an int value that we store to our productLineID attribute
     * @param product this value is an instance of the Product class that we store to our product attribute.
     * @param quantity this is an int value that we store to our quantity attribute.
     * @param lineTotal this is a double that we store to our lineTotal attribute.
     */
    public OrderLine(int productLineID, Product product, int quantity, double lineTotal)
    {
        this.productLineID = productLineID;
        this.product = product;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
    }
}   


