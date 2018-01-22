/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.*;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;



/**
 *
 * @author angel
 */
public class ViewProducts extends javax.swing.JFrame {
    
    Customer cust;
    HashMap<Integer, OrderLine> basket;
    
    
    

    /**
     * Creates new form ViewProducts
     */
    public ViewProducts() {
        initComponents();
        
        basket = new HashMap<>();
        
        categoryList.setVisibleRowCount(2);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement("Clothing");
        dlm.addElement("Footwear");
        categoryList.setModel(dlm);
        
        if (cust == null)
        {
            returnBtn.setText("Return to Main Menu");
        }        
    }   
    
    public ViewProducts(Customer cust, HashMap<Integer, OrderLine> basket)
    {
        initComponents();
        
        this.basket = basket;
        this.cust = cust;
        
        categoryList.setVisibleRowCount(2);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement("Clothing");
        dlm.addElement("Footwear");
        categoryList.setModel(dlm);
        
        if (cust == null)
        {
            returnBtn.setText("Return to Main Menu");
        }        
    }
    
    
    
    
        public ViewProducts(Customer cust)
    {
        initComponents();
        
        this.cust = cust;
        
        categoryList.setVisibleRowCount(2);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement("Clothing");
        dlm.addElement("Footwear");
        categoryList.setModel(dlm);
        
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

    private void categoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoryListValueChanged
        if(categoryList.getSelectedValue().equals("Clothing"))
        {
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Clothing> clothing;
            clothing = db.loadAllClothing();
            clothing.values().forEach((c) -> {
                dlm.addElement(c);
            });
            productList.setModel(dlm);
        }
        if(categoryList.getSelectedValue().equals("Footwear"))
        {
            DBManager db = new DBManager();
            DefaultListModel dlm = new DefaultListModel();
            HashMap<Integer, Footwear> footwear;
            footwear = db.loadAllFootwear();
            footwear.values().forEach((c) -> {
                dlm.addElement(c);
            });
            productList.setModel(dlm);
        }
    }//GEN-LAST:event_categoryListValueChanged

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

            DefaultListModel dlm;
            int index = productList.getSelectedIndex();
            dlm = (DefaultListModel)productList.getModel();
            Product p = (Product)dlm.getElementAt(index);
            int quantity = quantityDD.getSelectedIndex()+1;
            
            DBManager db = new DBManager();
            
            OrderLine ol = new OrderLine(db.getNextOrderLineID(), p, quantity ,(p.getPrice()*quantity));
                    
            db.saveOrderLine(ol);
            
            int id = ol.getProductLineID();
            
            basket.put(id, ol);
            
            infoBox("Items have been added to your basket!","BASKET");
            
    
    }//GEN-LAST:event_addBtnActionPerformed

    private void basketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basketBtnActionPerformed
        ViewBasket vb = new ViewBasket(cust, basket);
        this.dispose();
        vb.setVisible(true);
    }//GEN-LAST:event_basketBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        if(cust == null)
        {
            Menu menu = new Menu(); 
            this.dispose();
            menu.setVisible(true);
        }
        else
        {
            CustomerHome custhome = new CustomerHome(cust);
            this.dispose();
            custhome.setVisible(true);
        }
    }//GEN-LAST:event_returnBtnActionPerformed

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
