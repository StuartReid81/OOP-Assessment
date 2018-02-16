/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Stuart Reid
 * class created on the 30/10/2017
 * This Class describes the User superclass
 */
public class User {
    
    //attributes
    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
    
    //getters
    public int getUserID(){return userID;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    
    
    //setters
    public void setUserID(int userID){this.userID = userID;}
    public void setUserName(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    
    
    /**
     *  Default Constructor creating an instance of our user class without defining values for the attributes.
     */
    public User(){}
    
    
    /**
     * Overloaded Constructor
     * @param userID this sets the userID for a new instance of our user class.
     * @param username this sets the username for a new instance of our user class.
     * @param password this sets the password in a new instance of our user class.
     * @param firstName this sets the first name of a new instance of our user class.
     * @param lastName this sets the last name for a new instance of our user class.
     * 
     */ 
    public User(int userID, String username, String password, String firstName, String lastName)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
