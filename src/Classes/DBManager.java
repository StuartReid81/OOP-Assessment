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
                        stmt.executeUpdate("INSERT INTO CLOTHINGTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL, MEASUREMENT) VALUES ('" + prod.getProductID() + "', '" + ((Clothing) prod).getMeasurement() + ",)");
                    }
                    else
                    {
                        stmt.executeUpdate("INSERT INTO FOOTWEARTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL, SIZE) VALUES ('" + prod.getProductID() + "', '" + ((Footwear) prod).getSize() + ",)");
                    }
                }
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
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
        }
    }
       
       
       
       
        public boolean deleteCustomer (Customer originalCust)
    {
        boolean deleted = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                
                stmt.executeUpdate("DELETE FROM CUSTOMERSTABLE WHERE USERNAME = '" + originalCust.getUsername() + "'");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
        }
        return deleted;
    }
    
        
        

    /**
     *This method cycles through our database and stores all entries to our NootBook
    * @return Method returns an instance of the NoteBook class
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
        }
        return cust;
    }
}