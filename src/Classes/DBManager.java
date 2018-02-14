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


    //Methods for DB management of Products and subclasses
    

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
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                cloth.setProductID(rst.getInt("PRODUCTID"));
                cloth.setProductName(rst.getString("PRODUCTNAME"));
                cloth.setPrice(rst.getDouble("PRICE"));
                cloth.setStockLevel(rst.getInt("STOCKLEVEL"));
                cloth.setMeasurement(rst.getString("MEASUREMENT"));
            }
            else
            {
                cloth = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cloth;
    }
        

    public Product findProduct(int id)
    {
        if(findClothing(id)!=null)
        {
            return findClothing(id);
        }
        else
        {
            return findFootwear(id);
        }
    }
    
    
    
    public Footwear findFootwear(int id)
    {
    Footwear foot = new Footwear();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTSTABLE.PRODUCTID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                foot.setProductID(rst.getInt("PRODUCTID"));
                foot.setProductName(rst.getString("PRODUCTNAME"));
                foot.setPrice(rst.getDouble("PRICE"));
                foot.setStockLevel(rst.getInt("STOCKLEVEL"));
                foot.setSize(rst.getInt("SIZE"));
            }
            else
            {
                foot = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return foot;
    }
    
    
    public int getNextOrderID()
    {
        int next = 1;
        
        try
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(ORDERID) From ORDERSTABLE";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
            
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }

        return next;
    }
    
    
    public int getNextOrderLineID()
    {
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(ORDERLINEID) From ORDERLINESTABLE";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        return next;
    }
    
    
    public int getNextUserID()
    {
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(USERID) From (SELECT USERID FROM CUSTOMERSTABLE UNION SELECT USERID FROM STAFFTABLE)COMBINED";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        return next;
    }
    
    
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
                String query = "INSERT INTO ORDERLINESTABLE (ORDERLINEID, PRODUCTID, ORDERID, QUANTITY, LINETOTAL)" + " " + "VALUES (" + ol.getProductLineID() + ", " + id + ", " + ol.getOrderID() + ", " + ol.getQuantity() + ", " + ol.getLineTotal() + ")";
                stmt.executeUpdate(query);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }

    }
    
    
    public OrderLine loadOrderLine(String input)
    {
        OrderLine ol = new OrderLine();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERLINESTABLE Where PRODUCTLINEID = '" + input.trim() + "'";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                ol.setProductLineID(rst.getInt("PRDUCTLINEID"));
                ol.setProduct(findProduct(rst.getInt("PRODUCTID")));
                ol.setLineTotal(rst.getDouble("LINETOTAL"));
                ol.setQuantity(rst.getInt("QUANTITY"));
            }
            else
            {
                ol = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return ol;
    }
    
    
    
    //Methods for DB management of our Customer class
    
    
    
    public void saveCustomer(Customer cust) {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO CUSTOMERSTABLE (USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESSLINE1, ADDRESSLINE2, TOWN, POSTCODE, ISREGISTERED) VALUES (" + cust.getUserID() + " , '" + cust.getUsername() + "' , '" + cust.getPassword() + "', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + cust.getAddressLine1() + "', '" + cust.getAddressLine2() + "', '" + cust.getTown() + "', '" + cust.getPostcode() + "', '" + cust.getIsRegistered() + "')" );
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    
    
    public void deleteAllCustomers() {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE * CUSTOMERSTABLE");
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
       
       
       
       
    public void deleteCustomer (Customer originalCust)
    {

        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE FROM CUSTOMERSTABLE WHERE USERNAME = '" + originalCust.getUsername() + "'");
                HashMap<Integer, Order> orders = loadCustomersOrders(originalCust.getUserID());
                stmt.executeUpdate("DELETE FROM ORDERSTABLE WHERE CUSTID = " + originalCust.getUserID() + "");
                for(int key : orders.keySet())
                {
                    stmt.executeUpdate("DELETE FROM ORDERLINESTABLE WHERE ORDERID = " + orders.get(key).getOrderID() + "");
                }
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
        
    
    public void updateCustomer (Customer originalCust, Customer newCust)
    {
        try {
            //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE CUSTOMERSTABLE SET PASSWORD = '" + newCust.getPassword() + "', FIRSTNAME = '" + newCust.getFirstName() + "', LASTNAME = '" + newCust.getLastName() + "', ADDRESSLINE1 = '" + newCust.getAddressLine1() + "', ADDRESSLINE2 = '" + newCust.getAddressLine2() + "', TOWN = '" + newCust.getTown() + "', POSTCODE = '" + newCust.getPostcode() + "' WHERE USERNAME = '" + originalCust.getUsername() + "'");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    
    


    public Customer findCustomer(String input)
    {
    Customer cust = new Customer();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From CUSTOMERSTABLE Where USERNAME = '" + input.trim() + "'";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
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
                cust.setOrders(loadCustomersOrders(cust.getUserID()));
            }
            else
            {
                cust = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cust;
    }
    
    
    public Customer findCustomer(int input)
    {
    Customer cust = new Customer();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From CUSTOMERSTABLE Where USERID = " + input + "";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
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
                cust.setOrders(loadCustomersOrders(cust.getUserID()));
            }
            else
            {
                cust = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cust;
    }
    
    
    
    public void saveOrder(Order order)
    {
        try {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                //creates a statment using our connection
                Statement stmt = conn.createStatement();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                stmt.executeUpdate("INSERT INTO ORDERSTABLE (ORDERID, CUSTID, ORDERDATE, ORDERTOTAL, STATUS)" + " VALUES (" + order.getOrderID() + ", " + order.getCustID() + ", '" + date + "', " + order.getOrderTotal() + ", '" + order.getStatus() + "')");
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    public void addOrderID(int olID, int orderID)
    {
            try {
                //points our derby database driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                //tries to create a connection to the database
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    //creates a statment using our connection
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE ORDERLINESTABLE SET ORDERID = " + orderID + " WHERE ORDERLINEID = " + olID + "");
                }
            //catch passing out any returned error messages to the console
            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    
    public HashMap<Integer, Order> loadCustomersOrders(int id)
    {
    
        HashMap<Integer, Order> orders = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERSTABLE Where CUSTID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            
            
            while(rst.next())
            {
                Order order = new Order();
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                order.setOrderLines(loadOrderLines(order.getOrderID()));
                orders.put(order.getOrderID(), order);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return orders;
    }
    
    
    public HashMap<Integer, Order> loadAllOrders()
    {
    
        HashMap<Integer, Order> orders = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //tries to create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERSTABLE";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            
            
            while(rst.next())
            {
                Order order = new Order();
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                order.setOrderLines(loadOrderLines(order.getOrderID()));
                orders.put(order.getOrderID(), order);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return orders;
    }
    
    
    
    public HashMap<Integer, OrderLine> loadOrderLines(int id)
    {
    
        HashMap<Integer, OrderLine> orderLines = new HashMap<>();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERLINESTABLE Where ORDERID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            
            
            while(rst.next())
            {
                OrderLine ol = new OrderLine();
                ol.setOrderID(rst.getInt("ORDERID"));
                ol.setProductLineID(rst.getInt("ORDERLINEID"));
                Product p = findProduct(rst.getInt("PRODUCTID"));
                ol.setProduct(p);
                ol.setQuantity(rst.getInt("QUANTITY"));
                ol.setLineTotal(rst.getDouble("LINETOTAL"));
                
                orderLines.put(ol.getProductLineID(), ol);
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return orderLines;
    }

    public Staff findStaff(String inputUsername) {
        Staff staff = new Staff();
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From STAFFTABLE Where USERNAME = '" + inputUsername.trim() + "'";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                staff.setUserID(rst.getInt("USERID"));
                staff.setUserName(rst.getString("USERNAME"));
                staff.setPassword(rst.getString("PASSWORD"));
                staff.setFirstName(rst.getString("FIRSTNAME"));
                staff.setLastName(rst.getString("LASTNAME"));
                staff.setPosition(rst.getString("POSITION"));
                staff.setSalary(rst.getDouble("SALARY"));
            }
            else
            {
                staff = null;
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return staff;
    }

    public int getNextProductID() {
        int next = 1;
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(PRODUCTID) From PRODUCTSTABLE";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        return next;        
    }

    public Order loadAnOrder(int id) 
    {
            
        Order order = new Order();
        
        try 
        {
            //points our derby database driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //creates a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            //creates a statment using our connection
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERSTABLE Where ORDERID = " + id + "";
            //instantiating our result set
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            
            
            while(rst.next())
            {
                order = new Order();
                order.setOrderID(rst.getInt("ORDERID"));
                order.setCustID(rst.getInt("CUSTID"));
                order.setOrderDate(rst.getDate("ORDERDATE"));
                order.setStatus(rst.getString("STATUS"));
                order.setOrderTotal(rst.getDouble("ORDERTOTAL"));
                order.setOrderLines(loadOrderLines(order.getOrderID()));
            }
        //catch passing out any returned error messages to the console
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return order;
    }

}