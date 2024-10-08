/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import model.Address;
import model.Person;
import model.PersonDirectory;
import ui.PersonManager.ListPersonJPanel;
import ui.PersonManager.ManagePersonJPanel;


/**
 *
 * @author rahul
 */
public class MainJFrame extends javax.swing.JFrame {
    private PersonDirectory personDirectory;


    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        this.personDirectory = new PersonDirectory();
        this.setPreferredSize(new Dimension(1100, 900));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        generateDemoData();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        WorkAreaJPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        btnManagePerson = new javax.swing.JButton();
        btnListPerson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        WorkAreaJPanel.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(WorkAreaJPanel);

        btnManagePerson.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManagePerson.setText("Manage Person");
        btnManagePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagePersonActionPerformed(evt);
            }
        });

        btnListPerson.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListPerson.setText("List Persons");
        btnListPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListPersonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(btnManagePerson, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnListPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnManagePerson)
                    .addComponent(btnListPerson))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(controlPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManagePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagePersonActionPerformed
        // TODO add your handling code here:
        ManagePersonJPanel panel = new ManagePersonJPanel(WorkAreaJPanel, personDirectory);
        WorkAreaJPanel.add("ManagePersonJPanel", panel);
        CardLayout layout = (CardLayout) WorkAreaJPanel.getLayout();
        layout.next(WorkAreaJPanel);

    }//GEN-LAST:event_btnManagePersonActionPerformed

    private void btnListPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListPersonActionPerformed
        // TODO add your handling code here:
        ListPersonJPanel panel = new ListPersonJPanel(WorkAreaJPanel, personDirectory);
        WorkAreaJPanel.add("ListPersonJPanel", panel);
        CardLayout layout = (CardLayout) WorkAreaJPanel.getLayout();
        layout.next(WorkAreaJPanel);

    }//GEN-LAST:event_btnListPersonActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel WorkAreaJPanel;
    private javax.swing.JButton btnListPerson;
    private javax.swing.JButton btnManagePerson;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
 private void generateDemoData() {
        Person newPerson = personDirectory.addPerson();
        newPerson.setFirstName("Sairam");
        newPerson.setLastName("Mamidi");
        newPerson.setAge(25);
        newPerson.setSsn("111111111");
        Address home1 = new Address("3800 SW 34th ST", "Apt W220", "Gainesville", "FL", "32608", "2345678");
        Address work1 = new Address("3984 SW 43rd ST", "Texas Roadhouse", "Gainesville", "FL", "32608", "8765432");
        newPerson.setHomeAddress(home1);
        newPerson.setWorkAddress(work1);
        
        
        Person person1 = personDirectory.addPerson();
        person1.setFirstName("Akhil Varma");
        person1.setLastName("Chintalapati");
        person1.setSsn("9944176677");
        person1.setAge(23);
        Address homeAddress1 = new Address("3800 SW 34th ST", "Apt B10", "Gainesville", "FL", "32608", "2345678");
        Address workAddress1 = new Address("3984 SW 43rd ST", "Texas Roadhouse", "Gainesville", "FL", "32608", "8765432");
        person1.setHomeAddress(homeAddress1);
        person1.setWorkAddress(workAddress1);
        
        Person person2 = personDirectory.addPerson();
        person2.setFirstName("Yashwanth");
        person2.setLastName("Matta");
        person2.setSsn("9944176699");
        person2.setAge(24);
        Address homeAddress2 = new Address("3668 S Dallas ST", "Apt 4", "Aurora", "CO", "80014", "9876543");
        Address workAddress2 = new Address("9608 E Jefferson PL", "Apt 8", "Aurora", "CO", "80014", "8765432");
        person2.setHomeAddress(homeAddress2);
        person2.setWorkAddress(workAddress2);
        Person person3 = personDirectory.addPerson();
        person3.setFirstName("Venu");
        person3.setLastName("Sikhakolli");
        person3.setSsn("1102227766");
        person3.setAge(21);
        Address homeAddress3 = new Address("10 Paul Robeson Blvd", "Apt 7E", "New Brunswick", "NJ", "08901", "99887766"); 
        Address workAddress3 = new Address("10 Paul Robeson Blvd", "NJ APARTMENTS", "New Brunswick", "NJ", "08901", "77665544");
        person3.setHomeAddress(homeAddress3);
        person3.setWorkAddress(workAddress3);
        Person person4 = personDirectory.addPerson();
        person4.setFirstName("Pradhyumna ");
        person4.setLastName("Reddy");
        person4.setSsn("1818446227");
        person4.setAge(22);
        Address homeAddress4 = new Address("13260 34th ST N", "R-210", "Clearwater", "FL", "33762", "99876543");
        Address workAddress4 = new Address("13260 34th ST N", "Super 8 by wyndham,", "Clearwater", "FL", "33762", "98765432");
        person4.setHomeAddress(homeAddress4);
        person4.setWorkAddress(workAddress4);
 }
}
