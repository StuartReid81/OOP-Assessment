package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;



/**
 * @author Stuart Reid
 * Class defining an instance of the database manager
 */
public class DBManager {

    
    /**
     * This method takes in a Product as a parameter and stores it to the relevant tables
     * @param prod our product we pass in that is to be saved to our products table
     **/
    public void saveProduct(Product prod) {
        
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                //runs update to our database inserting values from our passed in product to our products table 
                stmt.executeUpdate("INSERT INTO PRODUCTSTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL)" + " VALUES (" + prod.getProductID() + ", '" + prod.getProductName() + "', " + prod.getPrice() + ", " + prod.getStockLevel() + ")");
                //if the product is an instance of the clothing class
                if(prod instanceof Clothing)
                {
                    //adds the products id and measurement values to our clothing table
                    stmt.executeUpdate("INSERT INTO CLOTHINGTABLE (PRODUCTID, MEASUREMENT) VALUES (" + prod.getProductID() + ", '" + ((Clothing) prod).getMeasurement() + "')");
                }
                //if the product is an instance of the footwear class
                else
                {
                    //adds the products id and size values to our footwear table
                    stmt.executeUpdate("INSERT INTO FOOTWEARTABLE (PRODUCTID, SIZE) VALUES (" + prod.getProductID() + ", " + ((Footwear) prod).getSize() + ")");
                }
            }
        //catch passing out error message to console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    

    /**
     * this method deletes a single product entry
     * @param prod this is our product that is to be deleted from our tables
     **/ 
    public void deleteProduct (Product prod)
    {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
            
            //if product is an instance of our clothing class
            if(prod instanceof Clothing)
                {
                    //create our two sql strings to delete the item from our tables
                    String sql = "DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    String sql2 =  "DELETE FROM CLOTHINGTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    //run both sql statements
                    stmt.executeUpdate(sql2);
                    stmt.executeUpdate(sql);
                    
                }
                //if the product is an instance of the footwear class
                else
                {
                    //create our two sql strings to delete the item from our tables
                    String sql = "DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    String sql2 = "DELETE FROM FOOTWEARTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    //run both sql statements
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sql2);
                }
            }
        //catch passing out any error messages to console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    /**
     *This method cycles through our database and stores all Clothing entries to our HashMap
     * @return Method returns our HashMap of Clothing items
     */
    public HashMap<Integer, Clothing> loadAllClothing()
    {
        //instantiating clothes HashMap
        HashMap<Integer, Clothing> clothes = new HashMap();
        
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                //string containing sql statement to pull all results from our products and clothing table that are joined via the product ID
                String sql = "Select * From CLOTHINGTABLE INNER JOIN PRODUCTSTABLE ON CLOTHINGTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID";
                //instantiating our result set
                ResultSet rst;
                //running our query and storing to our result set 
                rst = stmt.executeQuery(sql);
                
                //while looping through all returned results
                while (rst.next())
                {
                    //instantiate cloth
                    Clothing cloth = new Clothing();
                    //storing returned values from current row of our result set to our clothing instance
                    cloth.setProductID(rst.getInt("PRODUCTID"));
                    cloth.setProductName(rst.getString("PRODUCTNAME"));
                    cloth.setPrice(rst.getDouble("PRICE"));
                    cloth.setStockLevel(rst.getInt("STOCKLEVEL"));
                    cloth.setMeasurement(rst.getString("MEASUREMENT"));
                    //adds cloth to our hashmap
                    clothes.put(cloth.getProductID(), cloth);
                }
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns the clothes hashmap from the method
        return clothes;
    }
    
    
    /**
     *This method cycles through our database and stores all Footwear entries to our HashMap
     * @return Method returns our HashMap of Footwear items
     */
    public HashMap<Integer, Footwear> loadAllFootwear()
    {
        //instantiating footwear HashMap
        HashMap<Integer, Footwear> footwear = new HashMap();
        
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                //string containing sql statement to pull all results from our products and footwear table that are joined via the product ID
                String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID";
                //instantiating our result set
                ResultSet rst;
                //running our query and storing to our result set
                rst = stmt.executeQuery(sql);
                
                //while looping through all returned results
                while (rst.next())
                {
                    //instantiate foot
                    Footwear foot = new Footwear();
                    //storing returned values from current row of our result set to our footwear instance
                    foot.setProductID(rst.getInt("PRODUCTID"));
                    foot.setProductName(rst.getString("PRODUCTNAME"));
                    foot.setPrice(rst.getDouble("PRICE"));
                    foot.setStockLevel(rst.getInt("STOCKLEVEL"));
                    foot.setSize(rst.getInt("SIZE"));
                    //adds foot to our hashmap
                    footwear.put(foot.getProductID(), foot);
                }
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns the footwear hashmap from the method
        return footwear;
    }
    
    
    /**
     * This method takes in an instance of clothing and updates the matching productid entry in our database tables
     * @param newCloth this is the clothing item that holds the data to be updated to our tables
     */
    public void updateClothingItem (Clothing newCloth)
    {
        try {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    //runs seperate sql statements to update revelent data to our product table and our clothing table
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET PRODUCTNAME = '" + newCloth.getProductName() + "', PRICE = " + newCloth.getPrice() + ", STOCKLEVEL = " + newCloth.getStockLevel() + " WHERE PRODUCTID = " + newCloth.getProductID() + "");
                    stmt.executeUpdate("UPDATE CLOTHINGTABLE SET MEASUREMENT = '" + newCloth.getMeasurement() + "' WHERE PRODUCTID = " + newCloth.getProductID() + "");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    
    /**
     * This method takes in two parameters and uses them to update an entry in our database
     * @param newFoot this is the footwear item that holds the data to be updated to our tables
     */
    public void updateFootwearItem (Footwear newFoot)
    {
        try {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    //runs seperate sql statements to update revelent data to our product table and our footwear table
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET PROCUCTNAME = '" + newFoot.getProductName() + "', PRICE = '" + newFoot.getPrice() + "', STOCKLEVEL = '" + newFoot.getStockLevel() + "' WHERE PRODUCTID = '" + newFoot.getProductID() + "'");
                    stmt.executeUpdate("UPDATE FOOTWEARTABLE SET SIZE = '" + newFoot.getSize() + "' WHERE PRODUCTID = '" + newFoot.getProductID() + "'");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    
    /**
     * This method searches our clothing table and our products table based on the productID column and returns the item
     * @param id - this is the id of the clothing item we are looking for
     * @return returns and instance of our clothing class
     */
    public Clothing findClothing(int id)
    {
        //instantiating variable 
        Clothing cloth = new Clothing();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing sql statement returning any entries that match the given id
            String sql = "Select * From CLOTHINGTABLE INNER JOIN PRODUCTSTABLE ON CLOTHINGTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTSTABLE.PRODUCTID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if a row is retuned
            if(rst.next())
            {
                //setting returned values to our cloth variable
                cloth.setProductID(rst.getInt("PRODUCTID"));
                cloth.setProductName(rst.getString("PRODUCTNAME"));
                cloth.setPrice(rst.getDouble("PRICE"));
                cloth.setStockLevel(rst.getInt("STOCKLEVEL"));
                cloth.setMeasurement(rst.getString("MEASUREMENT"));
            }
            else
            {
                //if nothing returned setting cloth to null
                cloth = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returning the cloth variable from the method
        return cloth;
    }
        

    /**
     * Method that differentiates the type of class and returns the instance matching the id parameter
     * @param id productID passed in to method
     * @return returns result of either findClothing method or findFootwear
     */
    public Product findProduct(int id)
    {
        //if the findClothing method doesn't return null
        if(findClothing(id)!=null)
        {
            //calls method passing in parameter
            return findClothing(id);
        }
        //if findClothing returns null
        else
        {
            //calls method passing in parameter
            return findFootwear(id);
        }
    }
    
    
    /**
     * method that joins products table and footwear table and searches for a matching productID 
     * @param id - this is the productID of the item we are searching for
     * @return the method returns an instance of the Footwear class
     */
    public Footwear findFootwear(int id)
    {
        //instantiating variable foot
        Footwear foot = new Footwear();
            try 
            {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                //string holding our sql statement - select all entried from the joined tables that match the id provided
                String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTSTABLE.PRODUCTID = " + id + "";
                //instantiating our result set
                ResultSet rst;
                //running our query and storing to our result set
                rst = stmt.executeQuery(sql);

                //if a row is retuned
                if(rst.next())
                {
                    // storing the returned rows values to our variable foot
                    foot.setProductID(rst.getInt("PRODUCTID"));
                    foot.setProductName(rst.getString("PRODUCTNAME"));
                    foot.setPrice(rst.getDouble("PRICE"));
                    foot.setStockLevel(rst.getInt("STOCKLEVEL"));
                    foot.setSize(rst.getInt("SIZE"));
                }
                else
                {
                    //if no results returned we set the variable foot to null
                    foot = null;
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
        //the variable foot is returned from the method
        return foot;
    }
    
    
    /**
     * A method that returns the maximum value for OrderID from our orders table
     * @return an int is returned holding the maximum ID value
     */
    public int getNextOrderID()
    {
        //instantiates our variable and sets its value to one
        int next = 1;
        
        try
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select MAX(ORDERID) From ORDERSTABLE";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
            
            //if a row is retuned
            if(rst.next())
            {
                //increments the returned value by 1 and sets it to our variable
                next = rst.getInt(1);              
                next++;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }

        //returns our int variable
        return next;
    }
    
    
    /**
     * A method that returns the maximum value for OrderlineID from our orderslines table
     * @return an int is returned holding the maximum ID value
     */
    public int getNextOrderLineID()
    {
        //instantiates our variable and sets its value to one
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select MAX(ORDERLINEID) From ORDERLINESTABLE";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if a row is retuned
            if(rst.next())
            {
                //increments the returned value by 1 and sets it to our variable
                next = rst.getInt(1);              
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns our int variable
        return next;
    }
    
    
    /**
     * A method that returns the maximum value for userID from our staff and customer tables
     * @return an int is returned holding the maximum ID value
     */
    public int getNextUserID()
    {
        //instantiates our variable and sets its value to one
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select MAX(USERID) From (SELECT USERID FROM CUSTOMERSTABLE UNION SELECT USERID FROM STAFFTABLE)COMBINED";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if a row is retuned
            if(rst.next())
            {
                //increments the returned value by 1 and sets it to our variable
                next = rst.getInt(1);              
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns our int variable
        return next;
    }
    
    
    /**
     * This method stores an orderline to our orderlines table
     * @param ol orderline passed to the method to be saved to our orderlines Table
     */
    public void saveOrderLine(OrderLine ol)
    {
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) 
            {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                int id = ol.getProduct().getProductID();
                //string containing our sql statement
                String query = "INSERT INTO ORDERLINESTABLE (ORDERLINEID, PRODUCTID, ORDERID, QUANTITY, LINETOTAL)" + " " + "VALUES (" + ol.getProductLineID() + ", " + id + ", " + ol.getOrderID() + ", " + ol.getQuantity() + ", " + ol.getLineTotal() + ")";
                //running sql statement
                stmt.executeUpdate(query);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }

    }
    
    
    /**
     * Method that will save an instance of the customer class to our customers table
     * @param cust - this is the instance of our customer class that we are saving to the database
     */
    public void saveCustomer(Customer cust) {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                //string containing our sql statement
                stmt.executeUpdate("INSERT INTO CUSTOMERSTABLE (USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESSLINE1, ADDRESSLINE2, TOWN, POSTCODE, ISREGISTERED) VALUES (" + cust.getUserID() + " , '" + cust.getUsername() + "' , '" + cust.getPassword() + "', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + cust.getAddressLine1() + "', '" + cust.getAddressLine2() + "', '" + cust.getTown() + "', '" + cust.getPostcode() + "', '" + cust.getIsRegistered() + "')" );
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
        
       
    /**
     * Method that deletes a customer from the database
     * @param originalCust - this is the instance of the customer class that will be deleted
     */
    public void deleteCustomer (Customer originalCust)
    {

        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();

                //storing the customers orders in a hashmap to allow deletion of all orderlines relating to customers orders
                HashMap<Integer, Order> orders = loadCustomersOrders(originalCust.getUserID());
                //running our two statement to delete customer from customer table and any orders under the order ID
                stmt.executeUpdate("DELETE FROM CUSTOMERSTABLE WHERE USERNAME = '" + originalCust.getUsername() + "'");                
                stmt.executeUpdate("DELETE FROM ORDERSTABLE WHERE CUSTID = " + originalCust.getUserID() + "");
                
                //looping through hashmap of orders
                for(int key : orders.keySet())
                {
                    //deleting all order lines relating to the customers orders
                    stmt.executeUpdate("DELETE FROM ORDERLINESTABLE WHERE ORDERID = " + orders.get(key).getOrderID() + "");
                }
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
        
    /**
     * Method to update an entry on our customer table
     * @param originalCust variable storing the original customer details
     * @param newCust  variable holding the new customer details
     */
    public void updateCustomer (Customer originalCust, Customer newCust)
    {
        try {
            //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    //running our sql statement
                    stmt.executeUpdate("UPDATE CUSTOMERSTABLE SET PASSWORD = '" + newCust.getPassword() + "', FIRSTNAME = '" + newCust.getFirstName() + "', LASTNAME = '" + newCust.getLastName() + "', ADDRESSLINE1 = '" + newCust.getAddressLine1() + "', ADDRESSLINE2 = '" + newCust.getAddressLine2() + "', TOWN = '" + newCust.getTown() + "', POSTCODE = '" + newCust.getPostcode() + "' WHERE USERNAME = '" + originalCust.getUsername() + "'");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
   
    /**
     * method returning an instance of customer based on the username
     * @param input - string containing our username
     * @return - the method returns an instance of the customer class
     */
    public Customer findCustomer(String input)
    {
        //creating our instance of our customer class
        Customer cust = new Customer();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select * From CUSTOMERSTABLE Where USERNAME = '" + input.trim() + "'";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if there is a result returned we are storing the values to our customer variable
            if(rst.next())
            {
                cust.setUserID(rst.getInt("USERID"));
                cust.setUserName(rst.getString("USERNAME"));
                cust.setPassword(rst.getString("PASSWORD"));
                cust.setFirstName(rst.getString("FIRSTNAME"));
                cust.setLastName(rst.getString("LASTNAME"));
                cust.setAddressLine1(rst.getString("ADDRESSLINE1"));
                cust.setAddressLine2(rst.getString("ADDRESSLINE2"));
                cust.setTown(rst.getString("TOWN"));
                cust.setPostcode(rst.getString("POSTCODE"));
                cust.setIsRegistered(rst.getBoolean("ISREGISTERED"));
                //loading the customer orders in a hashmap and setting them to the order attribute
                cust.setOrders(loadCustomersOrders(cust.getUserID()));
            }
            else
            {
                //if nothing it returned we are setting the customer variable to null
                cust = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returning our customer variable
        return cust;
    }
    
    /**
     * method returning an instance of the customer class based on the user id
     * @param input the user id we are looking for
     * @return - an instance of the customer class
     */
    public Customer findCustomer(int input)
    {
        //instance of the customer class
        Customer cust = new Customer();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string holding our sql statment
            String sql = "Select * From CUSTOMERSTABLE Where USERID = " + input + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if the query returns any results we are storing them to our customer variable
            if(rst.next())
            {
                cust.setUserID(rst.getInt("USERID"));
                cust.setUserName(rst.getString("USERNAME"));
                cust.setPassword(rst.getString("PASSWORD"));
                cust.setFirstName(rst.getString("FIRSTNAME"));
                cust.setLastName(rst.getString("LASTNAME"));
                cust.setAddressLine1(rst.getString("ADDRESSLINE1"));
                cust.setAddressLine2(rst.getString("ADDRESSLINE2"));
                cust.setTown(rst.getString("TOWN"));
                cust.setPostcode(rst.getString("POSTCODE"));
                cust.setIsRegistered(rst.getBoolean("ISREGISTERED"));
                //loading the customer orders in a hashmap and setting them to the order attribute
                cust.setOrders(loadCustomersOrders(cust.getUserID()));
            }
            else
            {
                //if nothing it returned we are setting the customer variable to null
                cust = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returning our customer variable
        return cust;
    }
    
    
    /**
     * method to save an instance of the order class
     * @param order - instance of the order class we are saving to our database
     */
    public void saveOrder(Order order)
    {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                // string saving our date in the correct format
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                //running our sql statement
                stmt.executeUpdate("INSERT INTO ORDERSTABLE (ORDERID, CUSTID, ORDERDATE, ORDERTOTAL, STATUS)" + " VALUES (" + order.getOrderID() + ", " + order.getCustID() + ", '" + date + "', " + order.getOrderTotal() + ", '" + order.getStatus() + "')");
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    /**
     * Method to add an orderID to out orderlines
     * @param olID variable holding our orderline ID
     * @param orderID variable holding our orderID
     */
    public void addOrderID(int olID, int orderID)
    {
            try {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    //running our sql statement
                    stmt.executeUpdate("UPDATE ORDERLINESTABLE SET ORDERID = " + orderID + " WHERE ORDERLINEID = " + olID + "");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    
    /**
     * a method that loads all customer orders that match the customer id passed in
     * @param id - an integer representing the desired customer ID
     * @return - a hashmap of orders is returned from the method
     */
    public HashMap<Integer, Order> loadCustomersOrders(int id)
    {
        //creates an instance of a hashmap to store our orders
        HashMap<Integer, Order> orders = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select * From ORDERSTABLE Where CUSTID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            
            //loop our result set
            while(rst.next())
            {
                //instance of the order class created
                Order order = new Order();
                //store the rows values to our order variable
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                order.setOrderLines(loadOrderLines(order.getOrderID()));
                //stores the order to our hashmap
                orders.put(order.getOrderID(), order);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns our hashmap from the method
        return orders;
    }
    
    
    /**
     * This method loads all orders from our database and stores them in a hashmap
     * @return - Hashmap is returned from method containing all orders found
     */
    public HashMap<Integer, Order> loadAllOrders()
    {
        //create our hashmap
        HashMap<Integer, Order> orders = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //String containing our sql query
            String sql = "Select * From ORDERSTABLE";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            
            //loops all rows returned
            while(rst.next())
            {
                //creates an order variable
                Order order = new Order();
                //stores row values to our order variable
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                order.setOrderLines(loadOrderLines(order.getOrderID()));
                //adds order variable to our hashmap
                orders.put(order.getOrderID(), order);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns the hashmap of orders
        return orders;
    }
    
    
    /**
     * This method checks our database for any orderlines that matche the ID passed in to it
     * @param id - the OrderID of the orderlines we are looking for
     * @return  - the method returns a hashmap of orederlines
     */
    public HashMap<Integer, OrderLine> loadOrderLines(int id)
    {
        // we create our hashmap
        HashMap<Integer, OrderLine> orderLines = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select * From ORDERLINESTABLE Where ORDERID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            
            //loops through returned rows
            while(rst.next())
            {
                //creates an orderline to store our result
                OrderLine ol = new OrderLine();
                //stores returned values to ol
                ol.setOrderID(rst.getInt("ORDERID"));
                ol.setProductLineID(rst.getInt("ORDERLINEID"));
                //takes the product ID and calls our find product method
                Product p = findProduct(rst.getInt("PRODUCTID"));
                ol.setProduct(p);
                ol.setQuantity(rst.getInt("QUANTITY"));
                ol.setLineTotal(rst.getDouble("LINETOTAL"));
                
                //adds order line to our hashmap
                orderLines.put(ol.getProductLineID(), ol);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns our hashmap of orderlines
        return orderLines;
    }

    
    /**
     * This method returns the staff member with a matching username
     * @param inputUsername - parameter passed in holding the desired username
     * @return - the method returns a staff member
     */
    public Staff findStaff(String inputUsername) {
        //instance of our staff class created
        Staff staff = new Staff();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statemnt
            String sql = "Select * From STAFFTABLE Where USERNAME = '" + inputUsername.trim() + "'";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if the query returns an entry
            if(rst.next())
            {
                //store returned values to our staff variable
                staff.setUserID(rst.getInt("USERID"));
                staff.setUserName(rst.getString("USERNAME"));
                staff.setPassword(rst.getString("PASSWORD"));
                staff.setFirstName(rst.getString("FIRSTNAME"));
                staff.setLastName(rst.getString("LASTNAME"));
                staff.setPosition(rst.getString("POSITION"));
                staff.setSalary(rst.getDouble("SALARY"));
            }
            //if there are no matching entries in the database
            else
            {
                //sets the staff variable to null
                staff = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //return variable staff
        return staff;
    }

    
    /**
     * method that checks database for max productID and increments it by one
     * @return - method returns an int representing the next ID to be used
     */
    public int getNextProductID() {
        //creatingour integer and setting it to one
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //rstring containing our sql statement
            String sql = "Select MAX(PRODUCTID) From PRODUCTSTABLE";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            //if query returns any results
            if(rst.next())
            {
                //sets variable to returned result
                next = rst.getInt(1);           
                //increments by one
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        //returns our int variable
        return next;        
    }

    
    /**
     * this method takes in an ID and searches the database for the corresponding order
     * @param id - OrderID of the order we are looking for
     * @return - returns an instance of our order class
     */
    public Order loadAnOrder(int id) 
    {
        //creates an instance of the order class
        Order order = new Order();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql query
            String sql = "Select * From ORDERSTABLE Where ORDERID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
                
            
            //if the query returns a row
            if(rst.next())
            {
                //sets our returned values to our order variable
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                //calls our load orderlines method to create our hashmap of orderlines
                order.setOrderLines(loadOrderLines(order.getOrderID()));
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        //returns our order
        return order;
    }

    
    /**
     * method checking if a product exists on an orderline
     * @return - true if it currently is on an order
     * @param id - int passed in holding value of item id we are looking for
     */
    public boolean isOnOrder(int id) 
    {
        //boolean to hold result of search
        boolean found = false;
        
        try{
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            //string containing our sql statement
            String sql = "Select * From ORDERLINESTABLE Where PRODUCTID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            //running our query and storing to our result set
            rst = stmt.executeQuery(sql);
            
            //if any results are found we set boolean to true
            if(rst.next())
            {
                found = true;
            }
                        
        }catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        //returning our result
        return found;
    }

    /**
     * method that adjusts stock levels when an item is purchased
     * @param productID
     * @param quantity 
     */
    public void adjustStockLevel(int productID, int quantity) {
            try {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    //running our sql statement
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET STOCKLEVEL = " + (findProduct(productID).getStockLevel() - quantity) + " WHERE PRODUCTID = " + productID + "");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
}