package GUI;

import Classes.*;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;


/**
 * @date 
 * @author Stuart Reid
 * class defining an instance of the View Products page
 */
public class ViewProducts extends javax.swing.JFrame {
    
    Customer cust;
    HashMap<Integer, OrderLine> basket;
   

    /**
     * Creates new form ViewProducts
     * sets up page and maps basket to a new Hashmap. We call the fillCategoryList method and checks for a logged in customer
     */
    public ViewProducts() 
    {
        initComponents();
        
        basket = new HashMap<>();
        
        fillCategoryList();
        
        if (cust == null)
        {
            returnBtn.setText("Return to Main Menu");
        }        
    }   
    

    /**
     * Creates new form ViewProducts
     * @param cust holds our logged in customer that is passed from previous form
     * @param basket holds our list of items we wish to purchase
     * sets up page and maps basket and cust to parameters passed in. We call the fillCategoryList method and checks for a logged in customer
     */
    public ViewProducts(Customer cust, HashMap<Integer, OrderLine> basket)
    {
        initComponents();
        
        this.basket = basket;
        this.cust = cust;
        
        fillCategoryList();
        
        if (cust == null)
        {
            returnBtn.setText("Return to Main Menu");
        }        
    }
    
    
    /**
     * Creates new form ViewProducts
     * @param cust holds our logged in customer that is passed from previous form
     * sets up page and maps cust to parameter passed in. We call the fillCategoryList method and checks for a logged in customer
     */
    public ViewProducts(Customer cust)
    {
        initComponents();
        
        this.cust = cust;
        basket = new HashMap<>();
        
        fillCategoryList();
        
        if (cust == null)
        {
            returnBtn.setText("Return to Main Menu");
        }        
    }
        
        
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        returnBtn = new javax.swing.JButton();
        titleLbl = new javax.swing.JLabel();
        basketBtn = new javax.swing.JButton();
        categoriesLbl = new javax.swing.JLabel();
        productLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        productList = new javax.swing.JList<>();
        quantityLbl = new javax.swing.JLabel();
        quantityDD = new javax.swing.JComboBox<>();
        addBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));

        returnBtn.setText("RETURN TO CUSTOMER HOME");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        titleLbl.setText("PRODUCTS");

        basketBtn.setText("VIEW BASKET");
        basketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basketBtnActionPerformed(evt);
            }
        });

        categoriesLbl.setText("CATEGORIES");

        productLbl.setText("PRODUCTS");

        categoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(categoryList);

        productList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                productListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(productList);

        quantityLbl.setText("QUANTITY:");

        quantityDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", " " }));

        addBtn.setText("ADD TO BASKET");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(returnBtn)
                        .addGap(18, 18, 18)
                        .addComponent(titleLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                        .addComponent(basketBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(categoriesLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(productLbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quantityLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quantityDD, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jScrollPane2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnBtn)
                    .addComponent(titleLbl)
                    .addComponent(basketBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriesLbl)
                    .addComponent(productLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLbl)
                    .addComponent(quantityDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addBtn)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * On click listener checking for changed selection in our categoryList
     * @param evt (un-used)
     */
    private void categoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoryListValueChanged

        //if list box selection equals clothing
        if(categoryList.getSelectedValue().equals("Clothing"))
        {
            //set up db manager, clothing HashMap and list model
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Clothing> clothing;    
            
            //maps clothing to the HashMap returned from the DB
            clothing = db.loadAllClothing();         
            
            //loops the HashMap adding each product to our list model
            clothing.values().forEach((c) -> {
                dlm.addElement(c);
            });    
            
            //passed the list model to the list for display
            productList.setModel(dlm);
        }
        
        //if list box selection equals footwear
        if(categoryList.getSelectedValue().equals("Footwear"))
        {
            //set up db manager, footwear HashMap and list model
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Footwear> footwear;
            
            //maps footwear to the HashMap returned from the DB
            footwear = db.loadAllFootwear();
            
            //loops the HashMap adding each product to our list model
            footwear.values().forEach((c) -> {
                dlm.addElement(c);
            });
            
            //passed the list model to the list for display
            productList.setModel(dlm);
        }
    }//GEN-LAST:event_categoryListValueChanged

    
    /**
     * on click listener for our add to basket button
     * @param evt (un-used)
     */
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        //if there is a category value selected
        if(categoryList.getSelectedValue() != null)
        {
            //if there is a product value selected
            if(productList.getSelectedValue() != null)
            {
                //creates list model
                DefaultListModel dlm;
                
                // gets selected items index number
                int index = productList.getSelectedIndex();
                
                //gets list model from list box
                dlm = (DefaultListModel)productList.getModel();
                
                //creates product p and sets it to the element at the given index
                Product p = (Product)dlm.getElementAt(index);
                
                //takes the value from our quantity drop down box
                int quantity = quantityDD.getSelectedIndex()+1;

                //creates a db manager
                DBManager db = new DBManager();

                //creates instance ol of our OrderLines class using the overloaded constructor
                //uses the DB method for finding the highest orderline ID  
                OrderLine ol = new OrderLine(db.getNextOrderLineID(), p, quantity ,(p.getPrice()*quantity));

                //saves the orderline to our DB
                db.saveOrderLine(ol);

                //stores the id to an int variable
                int id = ol.getProductLineID();

                //adds ol to our HashMap
                basket.put(id, ol);

                //displays confirmation window
                infoBox("Items have been added to your basket!","BASKET");
            }
            
            //if there is no product selected we display message in pop up window
            else
            {
                 infoBox("Please select an item!","BASKET");
            }
        }
        
        //if there is no category selected we display message in pop up window
        else
        {
            infoBox("Please select a category!","BASKET");
        }
    }//GEN-LAST:event_addBtnActionPerformed

    
    /**
     * on click for our view basket button
     * @param evt (un-used)
     */
    private void basketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basketBtnActionPerformed
        //if basket HashMap is not empty
        if(!basket.isEmpty())
        {
            //create a view basket form passing in our cust variable and our basket hashmap
            ViewBasket vb = new ViewBasket(cust, basket);

            //close current window
            this.dispose();
            
            //make new one visible
            vb.setVisible(true);
        }
        //if basket is empty
        else
        {       
            //display error message pop up
            infoBox("The basket is empty!","BASKET");
        }
    }//GEN-LAST:event_basketBtnActionPerformed

    
    /**
     * on click for our back button
     * @param evt (un-used)
     */
    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        //if no customer is logged in
        if(cust == null)
        {
            //create new menu form
            Menu menu = new Menu();
            
            //close current form
            this.dispose();
            
            //make new form visible
            menu.setVisible(true);
        }
        //if customer is logged in
        else
        {
            //create instance of our customer home form passing our cust variable to the new form
            CustomerHome custhome = new CustomerHome(cust);
            
            //close existing form
            this.dispose();
            
            //make new form visible
            custhome.setVisible(true);
        }
    }//GEN-LAST:event_returnBtnActionPerformed

    
    /**
     * on click listener for our product list box triggered when the selected element is changed
     * this method is used to ensure the quantity drop down is populated dynamically
     * @param evt (un-used)
     */
    private void productListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_productListValueChanged
        //creates default list model variable
        DefaultListModel dlm;
        
        //sotres selected index to an iint variable
        int index = productList.getSelectedIndex();
        
        //gets list model and stores to our variable
        dlm = (DefaultListModel)productList.getModel();
        
        //gets the product selected and stores it to p
        Product p = (Product)dlm.getElementAt(index);
        
        //checks is the product is in stock
        if(p.getStockLevel() != 0)
        {
            //enables our add button and drop down
            addBtn.setEnabled(true);
            quantityDD.setEnabled(true);
            
            //creates a string array the size of our stock level
            String[] quantity = new String[p.getStockLevel()];

            //loops from 1 to our stock level adding each number to our array
            for (int i = 1; i <= p.getStockLevel(); i++)
            {
                quantity[i-1] = "" + i;
            }

            //stores the array to a combobox model
            DefaultComboBoxModel dcbm = new DefaultComboBoxModel(quantity);
            
            ///sets the model to our combo box for display
            quantityDD.setModel(dcbm);
        }
        //if there is no stock
        else
        {
            //disables our add button and dropdown
            addBtn.setEnabled(false);
            quantityDD.setEnabled(false);
            
            //displays error message pop up for user
            infoBox("This item is out of stock!","BASKET");
        }
    }//GEN-LAST:event_productListValueChanged

    
    /**
     * method filling our category list box
     */
    private void fillCategoryList()
    {
        //sets rows to 2
        categoryList.setVisibleRowCount(2);
        
        //ensures one selection at a time
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //creates new default list model
        DefaultListModel dlm = new DefaultListModel();
        
        //adds current categories to list model
        dlm.addElement("Clothing");
        dlm.addElement("Footwear");
        
        //displays list model via list box
        categoryList.setModel(dlm);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProducts().setVisible(true);
            }
        });
    }
    
    
    /**
     * method defining an instance of our pop up window
     * @param infoMessage takes in the message we want displayed
     * @param titleBar takes in the title of the pop up window
     */
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton basketBtn;
    private javax.swing.JLabel categoriesLbl;
    private javax.swing.JList<String> categoryList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel productLbl;
    private javax.swing.JList<String> productList;
    private javax.swing.JComboBox<String> quantityDD;
    private javax.swing.JLabel quantityLbl;
    private javax.swing.JButton returnBtn;
    private javax.swing.JLabel titleLbl;
    // End of variables declaration//GEN-END:variables


}
