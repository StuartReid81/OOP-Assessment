/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Stuart Reid
 * Class defining an instance of an Order.
 */
public class Order {
    
    
    //attributes
    private int orderID;
    private int custID;
    private Date orderDate;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;
    
    
    //getter
    public int getOrderID(){return orderID;}
    public int getCustID(){return custID;}
    public Date getOrderDate(){return orderDate;}
    public double getOrderTotal(){return orderTotal;}
    public String getStatus(){return status;}
    public HashMap getOrderLines(){return orderLines;}
    
    
    //setters
    public void setOrderID(int orderID){this.orderID = orderID;}
    public void setCustID(int custID){this.custID = custID;}
    public void setOrderDate(Date orderDate){this.orderDate = orderDate;}
    public void setOrderTotal(double orderTotal){this.orderTotal = orderTotal;}
    public void setStatus(String status){this.status = status;}
    public void setOrderLines(HashMap orderLines){this.orderLines = orderLines;}
    
    
    /**
     * This is the default constructor for our Order class.
     * It creates an undefined instance of the class.
     */
    public Order(){}
    
    /**
     * This is an overloaded constructor which takes in parameters and creates an
     * instance of the order class.
     * @param orderID attribute passed in is an int holding our ID.
     * @param custID attribute passed in to hold our foreign key custID.
     * @param orderDate Date passed in to store the date the order is placed.
     * @param orderTotal double passed in to store the total cost of our order.
     * @param status string passed to store the completion status of the order.
     * @param orderLines HashMap passed in to store any order lines in our order.
     */
    public Order(int orderID, int custID ,Date orderDate, double orderTotal, String status, HashMap orderLines)
    {
        this.orderID = orderID;
        this.custID = custID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.status = status;
        this.orderLines = orderLines;
    }
    
    
    //methods
    
    /**
     * This method takes in an OrderLine as a parameter and adds it to our orderLines
     * HashMap.
     * @param ol this is an instance of our OrderLine class which we are adding to our Order class.
     * @param isRegistered the value is a boolean whose value is true if our instance of the customer class is registered
     */
    public void addOrderLine(OrderLine ol, boolean isRegistered)
    {
        // if Customer is registered we use the getProductID method as the key 
        // when storing our OrderLine "ol" to our HashMap.
        if(isRegistered)
        {
        orderLines.put(ol.getProductLineID(), ol);
        }
    }
    
    /**
     * This method takes in an OrderLine as a parameter and checks orderLines to see if it is stored there
     * and if it is there we remove it from the HashMap.
     * @param ol this is an instance of our OrderLine class which we are removing from our Order class.
     * @param isRegistered the value is a boolean whose value is true if our instance of the customer class is registered
     */
    public void removeOrderLine(OrderLine ol, boolean isRegistered)
    {
        // if Customer is registered we check to see if ol is in our orderLines 
        // HashMap. If it is we remove it.
        if(isRegistered)
        {
            //loops our HashMap
            for(OrderLine x : orderLines.values())
            {
                //if our OrderLines match we remove it from the HashMap.
                if(x == ol)
                {
                    orderLines.remove(ol.getProductLineID());
                }
            }
        }
    }    
    /**
     * This method searches our orderLines HashMap and adds the number of items that match out productID parameter.
     * @param productID the integer passed in should be the value of the productID for the product we are looking for. 
     * @return totalProduct is the total number of items of the specified product in our order.
     */
    public int getQuantityOfProduct(int productID)
    {
        //local variables to track our running total and to assign our product in each cycle of our loop.
        int totalProduct = 0;
        Product p;
        //for each loop cycling all entries in our HashMap
        for(OrderLine x : orderLines.values())
        {
            //sets p to our current product and checks for a match.
            p = x.getProduct();
            if(p.getProductID() == productID)
            {
                //if matched we add to our running total.
                totalProduct = totalProduct + x.getQuantity();
            }
        }
        //returns our running total
        return totalProduct;
    }
    
    /**
     * A method that returns an integer to be used when creating our order lines.
     * @return we return the size of our orderLines HashMap incremented by 1.
     */
    public int generateUniqueOrderLineID()
    {
        DBManager db = new DBManager();
        return db.getNextOrderLineID();
    }
    
}
