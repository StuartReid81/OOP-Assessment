/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Stuart Reid
 * class created on the 31/10/2017
 * This Class describes the Staff subclass which inherits from the User superclass
 */
public class Staff extends User{
    
    //attributes
    private String position;
    private double salary;
    
    
    //getters
    public String getPosition(){return position;}
    public double getSalary(){return salary;}
    
    
    //setters
    public void setPosition(String position){this.position = position;}
    public void setSalary(double salary){this.salary = salary;}
    
    
    /**
     * this is the default constructor for the Staff class. 
     * It creates an instance of a staff member without initialising its variables.
     * 
     */
    public Staff(){super();}
    
    
    /**
     * This is an overloaded constructor taking in parameters and creating an instance of our staff member with them.
     * @param userID this sets our userID that we pass in to the constructor for our user class.
     * @param username this sets the username that we pass in to the constructor of our user class.
     * @param password this sets the password that we pass in to the constructor of our user class.
     * @param firstName this sets the first name that we pass in to the constructor of our user class.
     * @param lastName this sets the last name that we pass in to the constructor of our user class.
     * @param position this is used to set our staff members position attribute.
     * @param salary this is used to set our staff members salary attribute.
     */
    public Staff(int userID, String username, String password, String firstName, String lastName, String position, double salary)
    {
        super(userID, username, password, firstName, lastName);
        this.position = position;
        this.salary = salary;
    }
    
    
    //methods
    public String displayGreeting()
    {
        String greeting = "";
        return greeting;
    }
   
}
