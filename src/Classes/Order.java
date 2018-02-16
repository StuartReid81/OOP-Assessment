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
    
}
