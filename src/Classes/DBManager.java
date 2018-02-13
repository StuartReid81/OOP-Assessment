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
    

    //This method takes in a Product as a parameter and stores it to the relevant tables
    public void saveProduct(Product prod) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO PRODUCTSTABLE (PRODUCTID, PRODUCTNAME, PRICE, STOCKLEVEL)" + " VALUES (" + prod.getProductID() + ", '" + prod.getProductName() + "', " + prod.getPrice() + ", " + prod.getStockLevel() + ")");
                if(prod instanceof Clothing)
                {
                    stmt.executeUpdate("INSERT INTO CLOTHINGTABLE (PRODUCTID, MEASUREMENT) VALUES (" + prod.getProductID() + ", '" + ((Clothing) prod).getMeasurement() + "')");
                }
                else
                {
                    stmt.executeUpdate("INSERT INTO FOOTWEARTABLE (PRODUCTID, SIZE) VALUES (" + prod.getProductID() + ", " + ((Footwear) prod).getSize() + ")");
                }
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
                    String sql = "DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    String sql2 =  "DELETE FROM CLOTHINGTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    stmt.executeUpdate(sql2);
                    stmt.executeUpdate(sql);
                    
                }
                else
                {
                    String sql = "DELETE FROM PRODUCTSTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    String sql2 = "DELETE FROM FOOTWEARTABLE WHERE PRODUCTID = " + prod.getProductID() + "";
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sql2);
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
    public void updateClothingItem (Clothing newCloth)
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
    public void updateFootwearItem (Footwear newFoot)
    {
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE PRODUCTSTABLE SET PROCUCTNAME = '" + newFoot.getProductName() + "', PRICE = '" + newFoot.getPrice() + "', STOCKLEVEL = '" + newFoot.getStockLevel() + "' WHERE PRODUCTID = '" + newFoot.getProductID() + "'");
                    stmt.executeUpdate("UPDATE FOOTWEARTABLE SET SIZE = '" + newFoot.getSize() + "' WHERE PRODUCTID = '" + newFoot.getProductID() + "'");
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
            String sql = "Select * From CLOTHINGTABLE INNER JOIN PRODUCTSTABLE ON CLOTHINGTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTSTABLE.PRODUCTID = " + id + "";
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From FOOTWEARTABLE INNER JOIN PRODUCTSTABLE ON FOOTWEARTABLE.PRODUCTID = PRODUCTSTABLE.PRODUCTID WHERE PRODUCTSTABLE.PRODUCTID = " + id + "";
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
    
    
    public int getNextOrderID()
    {
        int next = 1;
        
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(ORDERID) From ORDERSTABLE";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
            
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
            }
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(ORDERLINEID) From ORDERLINESTABLE";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(USERID) From (SELECT USERID FROM CUSTOMERSTABLE UNION SELECT USERID FROM STAFFTABLE)COMBINED";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) 
            {
                Statement stmt = conn.createStatement();
                int id = ol.getProduct().getProductID();
                String query = "INSERT INTO ORDERLINESTABLE (ORDERLINEID, PRODUCTID, ORDERID, QUANTITY, LINETOTAL)" + " " + "VALUES (" + ol.getProductLineID() + ", " + id + ", " + ol.getOrderID() + ", " + ol.getQuantity() + ", " + ol.getLineTotal() + ")";
                stmt.executeUpdate(query);
            }

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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERLINESTABLE Where PRODUCTLINEID = '" + input.trim() + "'";
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
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return ol;
    }
    
    
    
    //Methods for DB management of our Customer class
    
    
    
    public void saveCustomer(Customer cust) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO CUSTOMERSTABLE (USERID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESSLINE1, ADDRESSLINE2, TOWN, POSTCODE, ISREGISTERED) VALUES (" + cust.getUserID() + " , '" + cust.getUsername() + "' , '" + cust.getPassword() + "', '" + cust.getFirstName() + "', '" + cust.getLastName() + "', '" + cust.getAddressLine1() + "', '" + cust.getAddressLine2() + "', '" + cust.getTown() + "', '" + cust.getPostcode() + "', '" + cust.getIsRegistered() + "')" );
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
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        return cust;
    }
    
    
    public void saveOrder(Order order)
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                Statement stmt = conn.createStatement();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                stmt.executeUpdate("INSERT INTO ORDERSTABLE (ORDERID, CUSTID, ORDERDATE, ORDERTOTAL, STATUS)" + " VALUES (" + order.getOrderID() + ", " + order.getCustID() + ", '" + date + "', " + order.getOrderTotal() + ", '" + order.getStatus() + "')");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
    }
    
    
    public void addOrderID(int olID, int orderID)
    {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234")) {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("UPDATE ORDERLINESTABLE SET ORDERID = " + orderID + " WHERE ORDERLINEID = " + olID + "");
                }

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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERSTABLE Where CUSTID = " + id + "";
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From ORDERLINESTABLE Where ORDERID = " + id + "";
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select * From STAFFTABLE Where USERNAME = '" + inputUsername.trim() + "'";
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
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CITYSHOPPINGDB","Stuart", "1234");
            Statement stmt = conn.createStatement();
            String sql = "Select MAX(PRODUCTID) From PRODUCTSTABLE";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
                
            if(rst.next())
            {
                next = rst.getInt(1);              
                next++;
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
            System.out.println(message);
        }
        
        return next;        
    }

}