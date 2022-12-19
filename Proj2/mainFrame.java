/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.project2;
import numeric.Factorial;
import numeric.GCD;
import numeric.NegativeNumberException;

/**
 *
 * @author clafa
 */
public class mainFrame extends javax.swing.JFrame {

    int gcdNumOne, gcdNumTwo, facNum = 0;
    
    public mainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gcdDialog = new javax.swing.JDialog();
        gcdLabel = new javax.swing.JLabel();
        gcdNumOneField = new javax.swing.JTextField();
        gcdNumTwoField = new javax.swing.JTextField();
        gcdRunButton = new javax.swing.JButton();
        gcdOutputLabel = new javax.swing.JLabel();
        gcdExitButton = new javax.swing.JButton();
        facDialog = new javax.swing.JDialog();
        facLabel = new javax.swing.JLabel();
        facNumField = new javax.swing.JTextField();
        facRunButton = new javax.swing.JButton();
        facOutputLabel = new javax.swing.JLabel();
        facExitButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitItem = new javax.swing.JMenuItem();
        computeMenu = new javax.swing.JMenu();
        gcdItem = new javax.swing.JMenuItem();
        factorialItem = new javax.swing.JMenuItem();

        gcdDialog.setBounds(new java.awt.Rectangle(0, 0, 330, 390));
        gcdDialog.setMaximumSize(new java.awt.Dimension(390, 330));
        gcdDialog.setMinimumSize(new java.awt.Dimension(390, 330));
        gcdDialog.setPreferredSize(new java.awt.Dimension(390, 330));
        gcdDialog.setResizable(false);

        gcdLabel.setText("Compute GCD!");

        gcdNumOneField.setText("First Number:");

        gcdNumTwoField.setText("Second Number:");

        gcdRunButton.setText("Run!");
        gcdRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcdRunButtonActionPerformed(evt);
            }
        });

        gcdOutputLabel.setText("Please enter numbers");

        gcdExitButton.setText("Exit");
        gcdExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcdExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gcdDialogLayout = new javax.swing.GroupLayout(gcdDialog.getContentPane());
        gcdDialog.getContentPane().setLayout(gcdDialogLayout);
        gcdDialogLayout.setHorizontalGroup(
            gcdDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gcdDialogLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(gcdNumOneField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(gcdNumTwoField)
                .addGap(59, 59, 59))
            .addGroup(gcdDialogLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(gcdLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gcdDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gcdDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gcdDialogLayout.createSequentialGroup()
                        .addComponent(gcdRunButton)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gcdDialogLayout.createSequentialGroup()
                        .addComponent(gcdOutputLabel)
                        .addGap(134, 134, 134))
                    .addComponent(gcdExitButton, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        gcdDialogLayout.setVerticalGroup(
            gcdDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gcdDialogLayout.createSequentialGroup()
                .addComponent(gcdExitButton)
                .addGap(14, 14, 14)
                .addComponent(gcdLabel)
                .addGap(56, 56, 56)
                .addGroup(gcdDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gcdNumOneField)
                    .addComponent(gcdNumTwoField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(gcdRunButton)
                .addGap(35, 35, 35)
                .addComponent(gcdOutputLabel)
                .addGap(29, 29, 29))
        );

        facDialog.setBounds(new java.awt.Rectangle(0, 0, 330, 390));
        facDialog.setMaximumSize(new java.awt.Dimension(390, 330));
        facDialog.setMinimumSize(new java.awt.Dimension(390, 330));
        facDialog.setPreferredSize(new java.awt.Dimension(390, 330));
        facDialog.setResizable(false);

        facLabel.setText("Compute Factorial!");

        facNumField.setText("Number:");

        facRunButton.setText("Run!");
        facRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facRunButtonActionPerformed(evt);
            }
        });

        facOutputLabel.setText("Please enter numbers");

        facExitButton.setText("Exit");
        facExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout facDialogLayout = new javax.swing.GroupLayout(facDialog.getContentPane());
        facDialog.getContentPane().setLayout(facDialogLayout);
        facDialogLayout.setHorizontalGroup(
            facDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, facDialogLayout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(facDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, facDialogLayout.createSequentialGroup()
                        .addComponent(facRunButton)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, facDialogLayout.createSequentialGroup()
                        .addComponent(facLabel)
                        .addGap(71, 71, 71)
                        .addComponent(facExitButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, facDialogLayout.createSequentialGroup()
                        .addGroup(facDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(facNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(facOutputLabel))
                        .addGap(134, 134, 134))))
        );
        facDialogLayout.setVerticalGroup(
            facDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facDialogLayout.createSequentialGroup()
                .addGroup(facDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(facDialogLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(facLabel))
                    .addComponent(facExitButton))
                .addGap(71, 71, 71)
                .addComponent(facNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(facRunButton)
                .addGap(35, 35, 35)
                .addComponent(facOutputLabel)
                .addGap(29, 29, 29))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");

        exitItem.setText("Exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        computeMenu.setText("Compute");

        gcdItem.setText("GCD");
        gcdItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcdItemActionPerformed(evt);
            }
        });
        computeMenu.add(gcdItem);

        factorialItem.setText("Factorial");
        factorialItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factorialItemActionPerformed(evt);
            }
        });
        computeMenu.add(factorialItem);

        menuBar.add(computeMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitItemActionPerformed

    private void gcdRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcdRunButtonActionPerformed
        //checks for ints, then computes and prints to screen
        try{
            gcdNumOne = Integer.parseInt(gcdNumOneField.getText());
            gcdNumTwo = Integer.parseInt(gcdNumTwoField.getText());
            gcdOutputLabel.setText("GCD = " + GCD.compute(gcdNumOne, gcdNumTwo));
        }
        catch(NumberFormatException ex){
            gcdOutputLabel.setText("Please enter numbers");
        }
    }//GEN-LAST:event_gcdRunButtonActionPerformed

    private void facRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facRunButtonActionPerformed
        //checks for ints > 0, then computes and prints to screen
        try{
            facNum = Integer.parseInt(facNumField.getText());
            facOutputLabel.setText("Factorial = " + Factorial.compute(facNum));
        }
        
        catch (NegativeNumberException ex){
            facOutputLabel.setText("Please enter a positive number");
        }
        catch (NumberFormatException ex){
            facOutputLabel.setText("Please enter numbers");
        }
        
        
    }//GEN-LAST:event_facRunButtonActionPerformed

    private void gcdItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcdItemActionPerformed
        gcdDialog.setVisible(true);
    }//GEN-LAST:event_gcdItemActionPerformed

    private void factorialItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factorialItemActionPerformed
        facDialog.setVisible(true);
    }//GEN-LAST:event_factorialItemActionPerformed

    private void gcdExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcdExitButtonActionPerformed
        gcdDialog.setVisible(false);
    }//GEN-LAST:event_gcdExitButtonActionPerformed

    private void facExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facExitButtonActionPerformed
        facDialog.setVisible(false);
    }//GEN-LAST:event_facExitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu computeMenu;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JDialog facDialog;
    private javax.swing.JButton facExitButton;
    private javax.swing.JLabel facLabel;
    private javax.swing.JTextField facNumField;
    private javax.swing.JLabel facOutputLabel;
    private javax.swing.JButton facRunButton;
    private javax.swing.JMenuItem factorialItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JDialog gcdDialog;
    private javax.swing.JButton gcdExitButton;
    private javax.swing.JMenuItem gcdItem;
    private javax.swing.JLabel gcdLabel;
    private javax.swing.JTextField gcdNumOneField;
    private javax.swing.JTextField gcdNumTwoField;
    private javax.swing.JLabel gcdOutputLabel;
    private javax.swing.JButton gcdRunButton;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}