/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.DBManager;
import Classes.Order;
import Classes.Staff;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @date 21/02/2018 - commented - sr
 * @author Stuart Reid
 */
public class StaffViewOrders extends javax.swing.JFrame {

    //global variable holding our logged in staff member
    Staff staff;
    
    
    /**
     * Creates new form StaffViewOrders
     * initialises components and calls our fill in table method
     */
    public StaffViewOrders() {
        initComponents();
        fillTable();
    }

    /**
     * Creates new form StaffViewOrders
     * initialises components and calls our fill in table method
     * maps parameter to our global variable
     * @param staff - parameter holding our logged in staff member
     */    
    public StaffViewOrders(Staff staff)
    {
        initComponents();
        this.staff = staff;
        fillTable();
    }
    
    
    /**
     * method that fills out our table with the desired data
     */
    private void fillTable()
    {
        //dreates new instance of our database manager
        DBManager db = new DBManager();
        // creates a new default table model pulling the model from our order table element
        DefaultTableModel dtm = (DefaultTableModel)orderTbl.getModel();
        //creates a hashmap and calls our databse to return all the current orders
        HashMap<Integer, Order> orders = db.loadAllOrders();
        
        //for each order in our hashmap
        for(Integer key : orders.keySet())
        {
            //creates an array of strings
            String[] data = new String[4];
            //assings the otder total to a double variable
            double price = orders.get(key).getOrderTotal();
            //filling each element of the array with our order data
            data[0] = "" + db.findCustomer(orders.get(key).getCustID()).getUsername();
            data[1] = "" + orders.get(key).getOrderID();
            data[2] = "" + orders.get(key).getOrderDate();
            data[3] = "£" + String.format("%.2f", price);

            //adding the array to our table model
            dtm.addRow(data);
        }
        
        //sets the model to our table
        orderTbl.setModel(dtm);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("RETURN TO STAFF HOME");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        viewBtn.setText("VIEW SELECTED ORDER");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        orderTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USERMANE", "ORDER ID", "DATE", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(orderTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(viewBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewBtn)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * on click event for our back button
     * @param evt 
     */
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        //creates a new instance of our staff home page passing in our logged in staff member
        StaffHome sh = new StaffHome(staff);
        //closes the current form
        this.dispose();
        //sets new form to visible
        sh.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    
    //on click for our view order button
    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        
        //if there is a row selected
        if(orderTbl.getSelectedRow() != -1)
        {
            //creates a default table model and sets it to the one on our table
            DefaultTableModel dtm = (DefaultTableModel)orderTbl.getModel();

            //creates an int variable and sets it to the currently selected row
            int row = orderTbl.getSelectedRow();

            //string holding our order id value for conversion to integer
            String idValue = dtm.getValueAt(row, 1).toString();

            //in holding our converted id
            int id = Integer.parseInt(idValue);

            //new instance of our db manager class
            DBManager db = new DBManager();

            //creates a new order and calls our database method to find via id
            Order order = db.loadAnOrder(id);
            //creates a new instance of our view an order form passing in our logged in staff member and our order
            StaffViewAnOrder svao = new StaffViewAnOrder(staff, order);
            //closes existing form
            this.dispose();
            //sets new form to visible
            svao.setVisible(true);
        }
        //if no row selected
        else
        {
            //error message displayed
            infoBox("Please select the order you would like to view!","ORDERS");
        }
          
    }//GEN-LAST:event_viewBtnActionPerformed

    
    /**
     * Method defining our pop up box
     * @param infoMessage - holds the body of our message
     * @param titleBar  - holds the title of the info box
     */ 
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffViewOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffViewOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffViewOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffViewOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffViewOrders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable orderTbl;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
