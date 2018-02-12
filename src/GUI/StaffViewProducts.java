/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Clothing;
import Classes.DBManager;
import Classes.Footwear;
import Classes.Staff;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author angel
 */
public class StaffViewProducts extends javax.swing.JFrame {

    Staff staff;
    
    /**
     * Creates new form StaffViewProducts
     */
    public StaffViewProducts() {
        initComponents();
        staff = new Staff();
        fillCategoryList();
    }
    
    
    public StaffViewProducts(Staff staff)
    {
        initComponents();
        this.staff = staff;
        fillCategoryList();
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
        titleLbl = new javax.swing.JLabel();
        catLbl = new javax.swing.JLabel();
        prdLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        catList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        proList = new javax.swing.JList<>();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));

        backBtn.setText("RETURN TO STAFF HOME");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        titleLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLbl.setText("EDIT PRODUCTS");

        catLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        catLbl.setText("CATEGORY");

        prdLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prdLbl.setText("PRODUCT");

        catList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                catListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(catList);

        jScrollPane2.setViewportView(proList);

        editBtn.setText("EDIT PRODUCT");

        deleteBtn.setText("DELETE PRODUCT");

        addBtn.setText("ADD A NEW PRODUCT");
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
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(titleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(catLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(prdLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn)
                    .addComponent(titleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prdLbl)
                    .addComponent(catLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(deleteBtn))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void catListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_catListValueChanged
        
        //if list box selection equals clothing
        if(catList.getSelectedValue().equals("Clothing"))
        {
            //set up db manager, clothing HashMap and list model
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Clothing> clothing;    
            
            //maps clothing to the HashMap returned from the DB
            clothing = db.loadAllClothing();         
            
            //checking if hashmap isn't empty
            if(!clothing.isEmpty())
            {
                //loops the HashMap adding each product to our list model
                clothing.values().forEach((c) -> {
                    dlm.addElement(c);
                });    
            }
                
                
            //passed the list model to the list for display
            proList.setModel(dlm);
            
        }
        
        //if list box selection equals footwear
        if(catList.getSelectedValue().equals("Footwear"))
        {
            //set up db manager, footwear HashMap and list model
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Footwear> footwear;
            
            //maps footwear to the HashMap returned from the DB
            footwear = db.loadAllFootwear();
            
            //checking if hashmap isn't empty
            if(!footwear.isEmpty())
            {
                //loops the HashMap adding each product to our list model
                footwear.values().forEach((c) -> {
                    dlm.addElement(c);
                });
            }
            
            //passed the list model to the list for display
            proList.setModel(dlm);  
            
        }
      
    }//GEN-LAST:event_catListValueChanged

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        AddProduct ap = new AddProduct(staff);
        this.dispose();
        ap.setVisible(true);
    }//GEN-LAST:event_addBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        StaffHome sh = new StaffHome(staff);
        this.dispose();
        sh.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    

    
    
    
    /**
     * method filling our category list box
     */
    private void fillCategoryList()
    {
        //sets rows to 2
        catList.setVisibleRowCount(2);
        
        //ensures one selection at a time
        catList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //creates new default list model
        DefaultListModel dlm = new DefaultListModel();
        
        //adds current categories to list model
        dlm.addElement("Clothing");
        dlm.addElement("Footwear");
        
        //displays list model via list box
        catList.setModel(dlm);
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
            java.util.logging.Logger.getLogger(StaffViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffViewProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel catLbl;
    private javax.swing.JList<String> catList;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel prdLbl;
    private javax.swing.JList<String> proList;
    private javax.swing.JLabel titleLbl;
    // End of variables declaration//GEN-END:variables
}
