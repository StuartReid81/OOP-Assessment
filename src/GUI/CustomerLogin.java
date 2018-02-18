/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.*;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JOptionPane;


/**
 * @date 13/11/2017
 * @date 18/2/2018 - comments added sr
 * @author Stuart Reid
 * Class defining an instance of our login page.
 */
public class CustomerLogin extends javax.swing.JFrame {

    //attributes
    
    //gloabal variable to store our logged in customer
    Customer cust;
    //hashmap global variable holding customers basket
    HashMap<Integer, OrderLine> basket;
    //boolean tracking if page was opened via the ViewBasket page
    boolean fromBasket = false;
    
    
    /**
     * Default constructor that creates an empty instance of our CustomerLogin screen
     * initialises components
     * sets customer to null
     */
    public CustomerLogin() {
        initComponents();
        cust = null;
    }
    
    
    /**
     * Overloaded constructor creating an instance our our form
     * initialises components
     * sets from basket to true as it is only used via the View Basket screen
     * changes the text of our main menu button
     * @param basket - variable holding a hashmap of orderlines
     */
    public CustomerLogin(HashMap<Integer, OrderLine> basket)
    {
        initComponents();
        this.basket = basket;
        mainMenuBtn.setText("RETURN TO BASKET");
        fromBasket = true;  
    }

    /**
     * Overloaded constructor creating an instance our our form
     * initialises components
     * maps basket to passed in parameter
     * maps from basket to passed in parameter
     * changes the text of our main menu button
     * @param basket - variable holding a hashmap of orderlines
     * @param fromBasket - a boolean value tracking wither the page has been redirected via the view basket page
     */
    public CustomerLogin(boolean fromBasket, HashMap<Integer, OrderLine> basket)
    {
        initComponents();
        this.fromBasket = fromBasket;
        this.basket = basket;
        mainMenuBtn.setText("RETURN TO BASKET");
    }
    
    
    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLbl = new javax.swing.JLabel();
        userNameLbl = new javax.swing.JLabel();
        userNameTxtBx = new javax.swing.JTextField();
        passwordLbl = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();
        mainMenuBtn = new javax.swing.JButton();
        passwordTxtBx = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));

        titleLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLbl.setText("CUSTOMER LOGIN");

        userNameLbl.setText("USERNAME:");

        passwordLbl.setText("PASSWORD:");

        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        registerBtn.setText("REGISTER");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        mainMenuBtn.setText("RETURN TO MAIN MENU");
        mainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainMenuBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameLbl)
                            .addComponent(passwordLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameTxtBx)
                            .addComponent(passwordTxtBx))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(titleLbl)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLbl)
                    .addComponent(userNameTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl)
                    .addComponent(passwordTxtBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(loginBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(registerBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainMenuBtn)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * on click for log in button
     * @param evt 
     */
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        //creating an instance of our database manager
        DBManager db = new DBManager();
        
        //getting user input and storing it to a string
        String inputUserName = userNameTxtBx.getText().toLowerCase();
        
        //creating an instance ouf our customer class and setting it to the object returned via the dbmanager
        Customer myCust = db.findCustomer(inputUserName); 
        
        //if the value is null
        if (myCust == null)
        {
            //error message
            titleLbl.setText("Username or Password incorrect!");
        }
        else
        {
            //store user input in a character array
            char[] passwordInput = passwordTxtBx.getPassword();
            //splitting users password in to character array for comparison
            char[] validPassword = myCust.getPassword().toCharArray();
            //comparing arrays and returning true or fasle
            boolean pwCorrect = Arrays.equals(passwordInput, validPassword);
            
            //if the input password matches the one held in the database
            if (pwCorrect)
            {
                //if the page was not redirected from the viewbasket
                if(!fromBasket)
                {
                    //stores logged in customer to global variable
                    cust = myCust;
                    //creates new instance of customer home page passing in cust
                    CustomerHome home = new CustomerHome(cust);
                    //closes existing form
                    this.dispose();
                    //sets new form to visible
                    home.setVisible(true);
                }
                //if the page was redirected from the viewbasket
                else
                {
                    //maps customer global variable to our logged in user
                    cust = myCust;
                    //pop up confirmation displayed
                    infoBox("You are now logged in!","LOGGED IN");
                    //creates new instance of our view basket form passing in the logged in customer and the basket hashmap
                    ViewBasket vb = new ViewBasket(cust, basket);
                    //closes existing form
                    this.dispose();
                    //sets new form to visible
                    vb.setVisible(true);
                }
            }
            //if password doesnt match
            else
            {
                //error message displayed
                titleLbl.setText("Username or Password incorrect!");
            }
        }      
    }//GEN-LAST:event_loginBtnActionPerformed

    
    /**
     * on click for our main menu button
     * @param evt 
     */
    private void mainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuBtnActionPerformed
        // if customer is not null
        if (cust != null)
        {
            //creates a main menu passing in logged in customer
            Menu menu = new Menu(cust);
            //closes existing form
            this.dispose();
            //sets new form to visible
            menu.setVisible(true);
        }
        //if redirected via basket
        if(fromBasket)
        {
            //creates new instance of our view basket form passing in the logged in customer and the basket hashmap
            ViewBasket vb = new ViewBasket(cust, basket);
            //closes existing form
            this.dispose();
            //sets new form to visible
            vb.setVisible(true);
        }
        // if no logged in user and not redirected via the basket
        else
        {
            //creates instance of our main menu form
            Menu menu = new Menu();
            //closes existing form
            this.dispose();
            //sets new form to visible
            menu.setVisible(true);
        }
    }//GEN-LAST:event_mainMenuBtnActionPerformed

    
    /**
     * on click for our register button
     * @param evt 
     */
    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        
        //if not redirected via the basket
        if(!fromBasket)
        {
            //creates new register form
            Register reg = new Register();
            //closes existing form
            this.dispose();
            //sets new form to visible
            reg.setVisible(true);
        }
        //if via basket page
        else
        {
            //creates new register form passing in from basket boolean and the basket hashmap of orderlines
            Register reg = new Register(fromBasket, basket);
            //closes existing form
            this.dispose();
            //sets new form to visible
            reg.setVisible(true);
        }
    }//GEN-LAST:event_registerBtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CustomerLogin().setVisible(true);
        });
    }

    
    /**
     * Method defining our pop up box
     * @param infoMessage - holds the body of our message
     * @param titleBar  - holds the title of the info box
     */
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton mainMenuBtn;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxtBx;
    private javax.swing.JButton registerBtn;
    private javax.swing.JLabel titleLbl;
    private javax.swing.JLabel userNameLbl;
    private javax.swing.JTextField userNameTxtBx;
    // End of variables declaration//GEN-END:variables
}
