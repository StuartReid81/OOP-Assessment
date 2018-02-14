/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.HashMap;

/**
 * 
 * @author Stuart Reid
 * Sub Class inheriting from the User class defining an instance of a customer.
 */
public class Customer extends User {
    
    
    //attributes
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private HashMap<Integer, Order> orders; 
    private boolean isRegistered;
    
    
    //getters
    public String getAddressLine1(){return addressLine1;}
    public String getAddressLine2(){return addressLine2;}
    public String getTown(){return town;}
    public String getPostcode(){return postcode;}
    public HashMap<Integer, Order> getOrders(){return orders;}
    public boolean getIsRegistered(){return isRegistered;}
    
    
    //setters
    public void setAddressLine1(String addressLine1){this.addressLine1 = addressLine1;}
    public void setAddressLine2(String addressLine2){this.addressLine2 = addressLine2;}
    public void setTown(String town){this.town = town;}
    public void setPostcode(String postcode){this.postcode = postcode;}
    public void setOrders(HashMap<Integer, Order> orders){this.orders = orders;}
    public void setIsRegistered(boolean isRegistered){this.isRegistered = isRegistered;}
    
    
    /**
     * Default constructor for our Customer class creating an instance of a customer without declaring values for is attributes.
     */
    public Customer(){super(); orders = new HashMap<>();}

    
    /**
     * Overloaded constructor taking in parameters to define an instance of the customer class.
     * @param userID this sets the userID that we pass in to the constructor of our user class.
     * @param username this sets the username that we pass in to the constructor of our user class.
     * @param password this sets the password that we pass in to the constructor of our user class.
     * @param firstName this sets the first name that we pass in to the constructor of our user class.
     * @param lastName this sets the last name that we pass in to the constructor of our user class.
     * @param addressLine1 this sets the value for the first line of our customers address.
     * @param addressLine2 this sets the value for the second line of our customers address.
     * @param town this sets the value for our customers town.
     * @param postcode this value sets the customers postcode.
     * @param orders this is a HashMap of all the orders the customer has placed.
     * @param isRegistered this value is a boolean confirming true if the customer is registered.
     */
    public Customer(int userID, String username, String password, String firstName, String lastName, String addressLine1, String addressLine2, String town, String postcode, HashMap<Integer, Order> orders, boolean isRegistered)
    {
        super(userID, username, password, firstName, lastName);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.orders = orders;
        this.isRegistered = isRegistered;
        
        if(this.orders == null);
        {
            this.orders = new HashMap<>();
        }
    }
    
    
    //methods
    
    
    /**
     * Method used when customer visits their home page
     * @return greeting - this returns our welcome string
     */
    public String displayGreeting()
    {
        String greeting = "Welcome " + super.getFirstName() + "!";
        return greeting;
    }
    
    
    //allows us to add an order to our users HashMap of orders
    public void addOrder(Order o)
    {
        orders.put(o.getOrderID(), o);
    }
}
