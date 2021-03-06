package game;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zach
 */
public class startPage extends javax.swing.JFrame {

    
    private Game g;
    /**
     * Creates new form startPage
     */
    public startPage() {
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

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        difficultyLabel = new javax.swing.JLabel();
        numPlayersLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        difficultyBox = new javax.swing.JComboBox<>();
        numPlayersBox = new javax.swing.JComboBox<>();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        backgroundLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        optMenu = new javax.swing.JMenu();
        rulesMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blacjkjack v1.0 Start");
        setResizable(false);

        jLayeredPane2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jLayeredPane2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 0, 145, 279));

        difficultyLabel.setText("Select Difficulty:");
        jLayeredPane2.add(difficultyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 95, -1, 23));

        numPlayersLabel.setText("Select # of Players:");
        jLayeredPane2.add(numPlayersLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 146, 134, 23));

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jLayeredPane2.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 205, -1, -1));

        difficultyBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Easy", "Hard" }));
        jLayeredPane2.add(difficultyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 96, -1, -1));

        numPlayersBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4" }));
        jLayeredPane2.add(numPlayersBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 147, -1, -1));

        nameLabel.setText("Enter your name:");
        jLayeredPane2.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 52, 111, -1));
        jLayeredPane2.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 49, 59, -1));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/blackjack.png"))); // NOI18N
        jLayeredPane2.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 280));

        optMenu.setText("Options");

        rulesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        rulesMenuItem.setText("Rules");
        rulesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesMenuItemActionPerformed(evt);
            }
        });
        optMenu.add(rulesMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        optMenu.add(exitMenuItem);

        jMenuBar1.add(optMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        g = new Game(numPlayersBox.getSelectedItem().toString(), difficultyBox.getSelectedItem().toString());       //  Get difficulty & number of players and pass to game constructor
        if(nameField.getText().compareTo("") == 0) {                                                                //  Check to see if namefield is empty & insert goof if so
            g.getPlayer(0).setName("IMMA SPOOKY GHOST");
        }
        else {
            g.getPlayer(0).setName(nameField.getText());
        }
        String name = "Computer Player ";
        for(int i = 1; i < g.getNumPlayers(); i++) {
            g.getPlayer(i).setName(name + i);
        }
        gamePage gp = new gamePage(g);
        gp.setVisible(true);
        dispose();
    }//GEN-LAST:event_startButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        dispose();
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void rulesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesMenuItemActionPerformed
        // TODO add your handling code here:
        rulesPage rp = new rulesPage();
        rp.setVisible(true);
    }//GEN-LAST:event_rulesMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(startPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(startPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(startPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new startPage().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JComboBox<String> difficultyBox;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> numPlayersBox;
    private javax.swing.JLabel numPlayersLabel;
    private javax.swing.JMenu optMenu;
    private javax.swing.JMenuItem rulesMenuItem;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
