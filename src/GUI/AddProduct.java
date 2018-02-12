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
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class AddProduct extends javax.swing.JFrame {

    Staff staff;
    /**
     * Creates new form AddProduct
     */
    public AddProduct() {
        initComponents();
        varLbl.setVisible(false);
        varTxtBx.setVisible(false);
        staff = new Staff();
    }
    
    
    public AddProduct(Staff staff)
    {
        initComponents();
        varLbl.setVisible(false);
        varTxtBx.setVisible(false);
        this.staff = staff;        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoryGroup = new javax.swing.ButtonGroup();
        titleLbl = new javax.swing.JLabel();
        typeLbl = new javax.swing.JLabel();
        clothRadBtn = new javax.swing.JRadioButton();
        footRadBtn = new javax.swing.JRadioButton();
        nameLbl = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        stockLbl = new javax.swing.JLabel();
        varLbl = new javax.swing.JLabel();
        priceTxtBx = new javax.swing.JTextField();
        nameTxtBx = new javax.swing.JTextField();
        varTxtBx = new javax.swing.JTextField();
        stockTxtBx = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLbl.setText("ADD NEW PRODUCT");

        typeLbl.setText("Type of Product:");

        categoryGroup.add(clothRadBtn);
        clothRadBtn.setText("Clothing");
        clothRadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clothRadBtnActionPerformed(evt);
            }
        });

        categoryGroup.add(footRadBtn);
        footRadBtn.setText("Footwear");
        footRadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                footRadBtnActionPerformed(evt);
            }
        });

        nameLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLbl.setText("NAME:");

        priceLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        priceLbl.setText("PRICE:");

        stockLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stockLbl.setText("STOCK LEVEL:");

        varLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        backBtn.setText("RETURN TO PRODUCT SELECT");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        submitBtn.setText("SUBMIT NEW PRODUCT");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stockLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(clothRadBtn))
                            .addComponent(varLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(footRadBtn)
                            .addComponent(priceTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(varTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clothRadBtn)
                    .addComponent(footRadBtn))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(submitBtn)
                .addGap(57, 57, 57)
                .addComponent(clearBtn)
                .addGap(71, 71, 71)
                .addComponent(backBtn)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clothRadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clothRadBtnActionPerformed
        footRadBtn.setSelected(false);
        varLbl.setText("MEASUREMENT:");
        varLbl.setVisible(true);
        varTxtBx.setVisible(true);
    }//GEN-LAST:event_clothRadBtnActionPerformed

    private void footRadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_footRadBtnActionPerformed
        clothRadBtn.setSelected(false);
        varLbl.setText("SIZE:");
        varLbl.setVisible(true);
        varTxtBx.setVisible(true);
    }//GEN-LAST:event_footRadBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        varLbl.setVisible(false);
        varTxtBx.setVisible(false);
        varTxtBx.setText("");
        nameTxtBx.setText("");
        priceTxtBx.setText("");
        stockTxtBx.setText("");
        categoryGroup.clearSelection();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        StaffViewProducts svp = new StaffViewProducts(staff);
        this.dispose();
        svp.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if(varTxtBx.getText().equals("") ||
        nameTxtBx.getText().equals("") ||
        priceTxtBx.getText().equals("") ||
        stockTxtBx.getText().equals(""))
        {
            infoBox("Please complete all fields before attempting to add an item!","ITEM ERROR");
        }
        else
        {
            DBManager db = new DBManager();
            try{
                Double price = Double.parseDouble(priceTxtBx.getText());
                try{
                    int stock = Integer.parseInt(stockTxtBx.getText());


                    if(clothRadBtn.isSelected())
                    {                    
                        Clothing cl = new Clothing(db.getNextProductID(), nameTxtBx.getText(), price, stock, varTxtBx.getText());
                        db.saveProduct(cl);
                        infoBox("Item has been added to our catalogue!","ITEM ADDED");
                    }
                    else
                    {
                        try{
                            int size = Integer.parseInt(varTxtBx.getText());
                            Footwear ft = new Footwear(db.getNextProductID(), nameTxtBx.getText(), price, stock, size);
                            db.saveProduct(ft);
                            infoBox("Item has been added to our catalogue!","ITEM ADDED");
                        } catch(Exception e){
                            infoBox("Please only enter a numeric value for the item size!","INPUT ERROR");
                        }
                        
                    }
                }catch (Exception e){
                infoBox("Please only enter a numeric value for a stock level!","INPUT ERROR");
                }
            }catch (Exception e) {
                infoBox("Please only enter a numeric value for the item price!","INPUT ERROR");
            }
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.ButtonGroup categoryGroup;
    private javax.swing.JButton clearBtn;
    private javax.swing.JRadioButton clothRadBtn;
    private javax.swing.JRadioButton footRadBtn;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField nameTxtBx;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JTextField priceTxtBx;
    private javax.swing.JLabel stockLbl;
    private javax.swing.JTextField stockTxtBx;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel titleLbl;
    private javax.swing.JLabel typeLbl;
    private javax.swing.JLabel varLbl;
    private javax.swing.JTextField varTxtBx;
    // End of variables declaration//GEN-END:variables
}
