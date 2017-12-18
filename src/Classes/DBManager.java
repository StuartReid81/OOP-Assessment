package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;



/**
 * @author Stuart Reid
 * Class defining an instance of the database manager
 */
public class DBManager {

    
    
    //Methods for DB management of Products and subclasses
    
    //method that takes in a Hashmap of products and iterates through it saving to relevant tables
    public void saveAllProducts(HashMap<Integer, Product> products){
        Product prod;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")){
                Statement stmt = conn.createStatement();
                
                for(int y = 0; y< products.size(); y++)
                {
                    prod = products.get(y);
                    stmt.executeUpdate("INSERT INTO PRODUCTSTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL)" + " VALUES ('" + prod.getProductID() + "', '" + prod.getProductName() + "', '" + prod.getPrice() + "', '" + prod.getStockLevel() + ",)");
                    if(prod instanceof Clothing)
                    {
                        stmt.executeUpdate("INSERT INTO CLOTHINGTABLE (PRODUCTID, MEASUREMENT) VALUES ('" + prod.getProductID() + "', '" + ((Clothing) prod).getMeasurement() + ",)");
                    }
                    else
                    {
                        stmt.executeUpdate("INSERT INTO FOOTWEARTABLE (PRODUCTID, SIZE) VALUES ('" + prod.getProductID() + "', '" + ((Footwear) prod).getSize() + ",)");
                    }
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    //This method takes in a Product as a parameter and stores it to the relevant tables
    public void saveProduct(Product prod) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO PRODUCTSTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL)" + " VALUES ('" + prod.getProductID() + "', '" + prod.getProductName() + "', '" + prod.getPrice() + "', '" + prod.getStockLevel() + ",)");
                if(prod instanceof Clothing)
                {
                    stmt.executeUpdate("INSERT INTO CLOTHINGTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL, MEASUREMENT) VALUES ('" + prod.getProductID() + "', '" + ((Clothing) prod).getMeasurement() + ",)");
                }
                else
                {
                    stmt.executeUpdate("INSERT INTO FOOTWEARTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL, SIZE) VALUES ('" + prod.getProductID() + "', '" + ((Footwear) prod).getSize() + ",)");
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    //this method deletes all entries from our trio of product tables
    public void deleteAllProducts() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE * PRODUCTSTABLE; DELETE * CLOTHINGTABLE; DELETE * FOOTWEARTABLE");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    //this method deletes a single product entry
    public void deleteProduct (Product prod)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
            if(prod instanceof Clothing)
                {
                    stmt.executeUpdate("DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = '" + prod.getProductID() + "'; DELETE FROM CLOTHINGTABLE WHERE PRODUCTID = " + prod.getProductID() + "'");
                    
                }
                else
                {
                    stmt.executeUpdate("DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = '" + prod.getProductID() + "'; DELETE FROM FOOTWEARTABLE WHERE PRODUCTID = " + prod.getProductID() + "'");
                }
            }
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
        HashMap<Integer, Clothing> clothes = new HashMap();
        
        
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                String sql = "Select * From CLOTHINGTABLE INNER JOIN PRODUCTSTABLE ON CLOTHINGTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID";
                ResultSet rst;
                rst = stmt.executeQuery(sql);
                
                while (rst.next())
                {
                    Clothing cloth = new Clothing();
                    cloth.setProductID(rst.getInt("PRODUCTID"));
                    cloth.setProductName(rst.getString("PRODUCTNAME"));
                    cloth.setPrice(rst.getDouble("PRICE"));
                    cloth.setStockLevel(rst.getInt("STOCKLEVEL"));
                    cloth.setMeasurement(rst.getString("MEASUREMENT"));
                    clothes.put(cloth.getProductID(), cloth);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return clothes;
    }
    
    
    /**
     *This method cycles through our database and stores all Footwear entries to our HashMap
     * @return Method returns our HashMap of Footwear items
     */
    public HashMap<Integer, Footwear> loadAllFootwear()
    {
        HashMap<Integer, Footwear> footwear = new HashMap();
        
        
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID";
                ResultSet rst;
                rst = stmt.executeQuery(sql);
                
                while (rst.next())
                {
                    Footwear foot = new Footwear();
                    foot.setProductID(rst.getInt("PRODUCTID"));
                    foot.setProductName(rst.getString("PRODUCTNAME"));
                    foot.setPrice(rst.getDouble("PRICE"));
                    foot.setStockLevel(rst.getInt("STOCKLEVEL"));
                    foot.setSize(rst.getInt("SIZE"));
                    footwear.put(foot.getProductID(), foot);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return footwear;
    }
    
    
    /**
     * This method takes in two parameters and uses them to update an entry in our database
     * @param originalCloth
     * @param newCloth 
     */
        public void updateClothingItem (Clothing originalCloth, Clothing newCloth)
    {
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET PROCUCTNAME = '" + newCloth.getProductName() + "', PRICE = '" + newCloth.getPrice() + "', STOCKLEVEL = '" + newCloth.getStockLevel() + "' WHERE PRODUCTID = '" + newCloth.getProductID() + "'");
                    stmt.executeUpdate("UPDATE CLOTHINGTABLE SET MEASUREMENT = '" + newCloth.getMeasurement() + "' WHERE PRODUCTID = '" + newCloth.getProductID() + "'");
                }

            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
    /**
     * This method takes in two parameters and uses them to update an entry in our database
     * @param originalFoot
     * @param newFoot
     */
        public void updateFootwearItem (Footwear originalFoot, Footwear newFoot)
    {
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET PROCUCTNAME = '" + newFoot.getProductName() + "', PRICE = '" + newFoot.getPrice() + "', STOCKLEVEL = '" + newFoot.getStockLevel() + "' WHERE PRODUCTID = '" + originalFoot.getProductID() + "'");
                    stmt.executeUpdate("UPDATE FOOTWEARTABLE SET SIZE = '" + newFoot.getSize() + "' WHERE PRODUCTID = '" + originalFoot.getProductID() + "'");
                }

            } catch (ClassNotFoundException | SQLException ex) {
                String message = ex.getMessage();
                System.out.println(message);
            }
    }
    
        
    public Clothing findClothing(int id)
    {
    Clothing cloth = new Clothing();
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From CLOTHINGTABLE INNER JOIN PRODUCTSTABLE ON CLOTHINGTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTID = '" + id + "'";
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
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cloth;
    }
        
    
    public Footwear findFootwear(int id)
    {
    Footwear foot = new Footwear();
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTID = '" + id + "'";
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
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return foot;
    }
    
    
    
    
    
    
    
    
    
    
    
    //Methods for DB management of our Customer class
    
    public void saveAllCustomers(HashMap<Integer, Customer> customers) {
        Customer cust;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
                for (int y = 0; y < customers.size(); y++) {
                    cust=(customers.get(y));
                    stmt.executeUpdate("INSERT INTO CUSTOMERSTABLE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESSLINE1, ADDRESSLINE2, TOWN, POSTCODE, ISREGISTERED)"
                            + " " + "VALUES ('" + cust.getUsername() + "' , '" + cust.getPassword() + "', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + cust.getAddressLine1()
                            + "', '" + cust.getAddressLine2() + "', '" + cust.getTown() + "', '" + cust.getPostcode() + "', '" + cust.getIsRegistered() + "')" );
                    //HashMap<Integer, Order> ord = cust.getOrders();
                    //for(int i = 0; i < ord.size(); i++)
                    //{
                    //    stmt.executeUpdate("INSERT INTO ORDERSTABLE (ORDERID, ORDERDATE, ORDERTOTAL, ORDERSTATUS, CUSTOMERID)" + " " + "VALUES ('" + ord.getOrderID() + "' , '" + 
                    //}
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    
    
    //still needs orders hashmap implementation
    public void saveCustomer(Customer cust) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO CUSTOMERSTABLE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESSLINE1, ADDRESSLINE2, TOWN, POSTCODE, ISREGISTERED) VALUES ('" + cust.getUsername() + "' , '" + cust.getPassword() + "', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + cust.getAddressLine1() + "', '" + cust.getAddressLine2() + "', '" + cust.getTown() + "', '" + cust.getPostcode() + "', '" + cust.getIsRegistered() + "')" );
            }

        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    
    
    public void deleteAllCustomers() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE * CUSTOMERSTABLE");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
       
       
       
       
    public void deleteCustomer (Customer originalCust)
    {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE FROM CUSTOMERSTABLE WHERE USERNAME = '" + originalCust.getUsername() + "'");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
        
        

    /**
     *This method cycles through our database and stores all entries to our HashMap
    * @return Method returns our HashMap variable
    */
    public HashMap<String, Customer> loadAllCustomers()
    {
        HashMap<String, Customer> customers = new HashMap();
        
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                String sql = "Select * From CUSTOMERSTABLE";
                ResultSet rst;
                rst = stmt.executeQuery(sql);
                
                while (rst.next())
                {
                    Customer cust = new Customer();
                    cust.setUserName(rst.getString("USERNAME"));
                    cust.setPassword(rst.getString("PASSWORD"));
                    cust.setFirstName(rst.getString("FIRSTNAME"));
                    cust.setLastName(rst.getString("LASTNAME"));
                    cust.setAddressLine1(rst.getString("ADDRESSLINE1"));
                    cust.setAddressLine2(rst.getString("ADDRESSLINE2"));
                    cust.setTown(rst.getString("TOWN"));
                    cust.setPostcode(rst.getString("POSTCODE"));
                    cust.setIsRegistered(rst.getBoolean("ISREGISTERED"));
                    customers.put(cust.getUsername(), cust);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return customers;
    }
    
    
    
    
    public void updateCustomer (Customer originalCust, Customer newCust)
    {
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE CUSTOMERSTABLE SET PASSWORD = '" + newCust.getPassword() + "', FIRSTNAME = '" + newCust.getFirstName() + "', LASTNAME = '" + newCust.getLastName() + "', ADDRESSLINE1 = '" + newCust.getAddressLine1() + "', ADDRESSLINE2 = '" + newCust.getAddressLine2() + "', TOWN = '" + newCust.getTown() + "', POSTCODE = '" + newCust.getPostcode() + "' WHERE USERNAME = '" + originalCust.getUsername() + "'");
                }

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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From CUSTOMERSTABLE Where USERNAME = '" + input.trim() + "'";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                cust.setUserName(rst.getString("USERNAME"));
                cust.setPassword(rst.getString("PASSWORD"));
                cust.setFirstName(rst.getString("FIRSTNAME"));
                cust.setLastName(rst.getString("LASTNAME"));
                cust.setAddressLine1(rst.getString("ADDRESSLINE1"));
                cust.setAddressLine2(rst.getString("ADDRESSLINE2"));
                cust.setTown(rst.getString("TOWN"));
                cust.setPostcode(rst.getString("POSTCODE"));
                cust.setIsRegistered(rst.getBoolean("ISREGISTERED"));
            }
            else
            {
                cust = null;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cust;
    }
}