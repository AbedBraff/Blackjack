package game;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//  card2.setIcon(new javax.swing.ImageIcon(getClass().getResource(c2.getImage())));
/**
 *
 * @author Zach
 */
public class gamePage extends javax.swing.JFrame {
    private Game g;

    /**
     * Creates new form gamePage
     */
    public gamePage() {
        initComponents();
    }
    
    public gamePage(Game gPassed) {
        initComponents();
        g = gPassed;
        userNameLabel.setText("User Name: " + g.getPlayer(0).getName());
        deckLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/b2fv.png")));
        
        String difficulty = "Difficulty: ";
        if(g.getDifficulty() == 0) {
            difficulty += "Easy";
        }
        else{
            difficulty += "Hard";
        }
        difficultyLabel.setText(difficulty);
        
        if(g.getNumPlayers() == 2) {            //  Hide images depending on # players selected
            hidePlayer2();
            hidePlayer3();
        }
        else if(g.getNumPlayers() == 3) {
            hidePlayer3();
        }
        
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
    }
    
    private void startOfHandUpdate() {
        computer1HandValueLabel.setText("Hand Value: 0");    //  Reset computer hand value labels
        computer2HandValueLabel.setText("Hand Value: 0");
        computer3HandValueLabel.setText("Hand Value: 0");
        
        computer1HandStatusLabel.setText("Hand Status: In");
        computer2HandStatusLabel.setText("Hand Status: In");
        computer3HandStatusLabel.setText("Hand Status: In");
        
        outputPane.setText(outputPane.getText() + "Next Hand:\n");
        
        userCard3.setIcon(null);            //  Reset all icons in userCard labels to null
        userCard4.setIcon(null);
        userCard5.setIcon(null);
        userCard6.setIcon(null);
        userCard7.setIcon(null);
        userCard8.setIcon(null);
        userCard9.setIcon(null);
        userCard10.setIcon(null);
        userCard11.setIcon(null);
        
        
        for(int i = 1; i < g.getNumPlayers(); i++) {                                //  Hide eliminated players
            if(g.getPlayer(i).getOutOfChips()) {
                if(i == 1) {
                    hidePlayer1();
                }
                if(i == 2) {
                    hidePlayer2();
                }
                if(i == 3) {
                    hidePlayer3();
                }
            }
        }
    }
    
    private void hidePlayer1() {                                                    // set all player1 elements in GUI to invisible
        computer1BetLabel.setVisible(false);
        computer1ChipsLabel.setVisible(false);
        computer1NameLabel.setVisible(false);
        computer1NumCardsLabel.setVisible(false);
        computer1HandValueLabel.setVisible(false);
        computer1HandStatusLabel.setVisible(false);
        computer1FaceUpCardLabel.setVisible(false);
        computer1FaceDownCardLabel.setVisible(false);
    }
    
    private void hidePlayer2() {                                                // set all player2 elements in GUI to invisible
        computer2BetLabel.setVisible(false);
        computer2ChipsLabel.setVisible(false);
        computer2NameLabel.setVisible(false);
        computer2NumCardsLabel.setVisible(false);
        computer2HandValueLabel.setVisible(false);
        computer2HandStatusLabel.setVisible(false);
        computer2FaceUpCardLabel.setVisible(false);
        computer2FaceDownCardLabel.setVisible(false);
    }
    
    private void hidePlayer3() {                                                //  set all player3 elements in GUI to invisible
        computer3BetLabel.setVisible(false);
        computer3ChipsLabel.setVisible(false);
        computer3NameLabel.setVisible(false);
        computer3NumCardsLabel.setVisible(false);
        computer3HandValueLabel.setVisible(false);
        computer3HandStatusLabel.setVisible(false);
        computer3FaceUpCardLabel.setVisible(false);
        computer3FaceDownCardLabel.setVisible(false);
    }
    
    private void updateBetAndChipLabels () {        //  Post computer bet, chip labels to GUI
        userBetLabel.setText("Player Bet: " + g.getPlayer(0).getBet());
        userChipsLabel.setText("Player Chips: " + (g.getPlayer(0).getChips()));
        for(int i = 1; i < g.getNumPlayers(); i++) {
            if(i == 1) {
                computer1BetLabel.setText("Bet: " + g.getPlayer(i).getBet());
                computer1ChipsLabel.setText("Chips: " + (g.getPlayer(i).getChips()));
            }
            if(i == 2) {
                computer2BetLabel.setText("Bet: " + g.getPlayer(i).getBet());
                computer2ChipsLabel.setText("Chips: " + (g.getPlayer(i).getChips()));
            }
            if(i == 3) {
                computer3BetLabel.setText("Bet: " + g.getPlayer(i).getBet());
                computer3ChipsLabel.setText("Chips: " + (g.getPlayer(i).getChips()));
            }
        }
    }
    
    protected void updateNumCardsLabels() {     //  Post computer # cards to GUI
        for(int i = 1; i < g.getNumPlayers(); i++) {
            if(i == 1) {
                computer1NumCardsLabel.setText("# Cards: " + g.getPlayer(1).getHand().getSize());
            }
            if(i == 2) {
                computer2NumCardsLabel.setText("# Cards: " + g.getPlayer(2).getHand().getSize());
            }
            if(i == 3){
                computer3NumCardsLabel.setText("# Cards: " + g.getPlayer(3).getHand().getSize());
            }
        }
    }
    
    private void updateHandValueLabels() {      //  Post computer hand values to GUI
        for(int i = 1; i < g.getNumPlayers(); i++) {
            if(i == 1) {
                computer1HandValueLabel.setText("Hand Value: " + g.getPlayer(1).getHand().getHandValue());
            }
            if(i == 2) {
                computer2HandValueLabel.setText("Hand Value: " + g.getPlayer(2).getHand().getHandValue());
            }
            if(i == 3) {
                computer3HandValueLabel.setText("Hand Value: " + g.getPlayer(3).getHand().getHandValue());
            }
        }
    }
    
    private void endOfDealUpdate() {        //  Post card images, value of user hand, computer # cards in GUI after deal at start of each hand
        for(int i = 0; i < g.getNumPlayers(); i++) {
            if(!g.getPlayer(i).getOutOfChips()) {
                if(i == 0) {
                    updateImage(userCard1, g.getPlayer(0).getHand().getCard(0));
                    updateImage(userCard2, g.getPlayer(0).getHand().getCard(1));
                    userHandValueLabel.setText("Player Hand Value: " + g.getPlayer(0).getHand().getHandValue());
                }
                if(i == 1) {
                    updateImage(computer1FaceUpCardLabel, g.getPlayer(i).getHand().getCard(0));
                    computer1FaceDownCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/b2fv.png")));
                    computer1NumCardsLabel.setText("# Cards: " + g.getPlayer(i).getHand().getSize());
                }
                if(i == 2) {
                    updateImage(computer2FaceUpCardLabel, g.getPlayer(i).getHand().getCard(0));
                    computer2FaceDownCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/b2fv.png")));
                    computer2NumCardsLabel.setText("# Cards: " + g.getPlayer(i).getHand().getSize());
                }
                if(i == 3) {
                    updateImage(computer3FaceUpCardLabel, g.getPlayer(i).getHand().getCard(0));
                    computer3FaceDownCardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/b2fv.png")));
                    computer3NumCardsLabel.setText("# Cards: " + g.getPlayer(i).getHand().getSize()); 
                }
            }
        }
        
        dealButton.setEnabled(false);                                                                   //  Disable bet combobox & deal button
        betComboBox.setEnabled(false);
        stayButton.setEnabled(true);                                                                    //  Enable stay & hit buttons for upcoming hand
        hitButton.setEnabled(true);
    }
    
    private void endofHandUpdate() {
        dealButton.setEnabled(true);        //  Reset buttons & combo box for next hand
        stayButton.setEnabled(false);
        betComboBox.setEnabled(true);
        betComboBox.setSelectedIndex(0);
        
        for(int i = 1; i < g.getNumPlayers(); i++) {                    //      Reset computer labels for new hand
            if(g.getPlayer(i).getOutOfChips()) {
                if(i == 1) {
                    computer1NameLabel.setText(g.getPlayer(i).getName() + " has lost.");
                    computer1HandValueLabel.setText("Hand Value: 0");
                    computer1NumCardsLabel.setText("# Cards: 0");
                }
                if(i == 2) {
                    computer2NameLabel.setText(g.getPlayer(i).getName() + " has lost.");
                    computer2HandValueLabel.setText("Hand Value: 0");
                    computer2NumCardsLabel.setText("# Cards: 0");
                }
                if(i == 3) {
                    computer3NameLabel.setText(g.getPlayer(i).getName() + " has lost.");
                    computer3HandValueLabel.setText("Hand Value: 0");
                    computer3NumCardsLabel.setText("# Cards: 0");
                }
            }
        }
        potLabel.setText("Current Pot: 0");
    }
    
    private void oneRoundUpdate() {     //  Post status of computer players to GUI
        outputPane.setText(outputPane.getText() + "Back to you " + g.getPlayer(0).getName() + "!\nEither hit again or stay with your current hand.\n\n");
        
        String inPlay = "This Hand: In";
        String lostHand = "This Hand: Lost";
        for(int i = 1; i < g.getNumPlayers(); i++) {
            if(i == 1) {
                if(g.getPlayer(i).getOver21()) {
                    computer1HandStatusLabel.setText(lostHand);
                }
                else {
                    computer1HandStatusLabel.setText(inPlay);
                }
            }
            if(i == 2) {
                if(g.getPlayer(i).getOver21()) {
                    computer2HandStatusLabel.setText(lostHand);
                }
                else {
                    computer2HandStatusLabel.setText(inPlay);
                }
            }
            if(i == 3) {
                if(g.getPlayer(i).getOver21()) {
                    computer3HandStatusLabel.setText(lostHand);
                }
                else {
                    computer3HandStatusLabel.setText(inPlay);
                }
            }
        }
    }
    
    private void updateUserOnHit() {    //  Update userhandvalue & card images in GUI
        userHandValueLabel.setText("Player Hand Value: " + g.getPlayer(0).getHand().getHandValue());
        
        if(g.getPlayer(0).getHand().getSize() > 2) {
            updateImage(userCard3, g.getPlayer(0).getHand().getCard(2));
        }
        if(g.getPlayer(0).getHand().getSize() > 3) {
            updateImage(userCard4, g.getPlayer(0).getHand().getCard(3));
        }
        if(g.getPlayer(0).getHand().getSize() > 4) {
            updateImage(userCard5, g.getPlayer(0).getHand().getCard(4));
        }
        if(g.getPlayer(0).getHand().getSize() > 5) {
            updateImage(userCard6, g.getPlayer(0).getHand().getCard(5));
        }
        if(g.getPlayer(0).getHand().getSize() > 6) {
            updateImage(userCard7, g.getPlayer(0).getHand().getCard(6));
        }
        if(g.getPlayer(0).getHand().getSize() > 7) {
            updateImage(userCard8, g.getPlayer(0).getHand().getCard(7));
        }
        if(g.getPlayer(0).getHand().getSize() > 8) {
            updateImage(userCard9, g.getPlayer(0).getHand().getCard(8));
        }
        if(g.getPlayer(0).getHand().getSize() > 9) {
            updateImage(userCard10, g.getPlayer(0).getHand().getCard(9));
        }
        if(g.getPlayer(0).getHand().getSize() > 10) {
            updateImage(userCard11, g.getPlayer(0).getHand().getCard(10));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        userBetLabel = new javax.swing.JLabel();
        userChipsLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        hitButton = new javax.swing.JButton();
        deckLabel = new javax.swing.JLabel();
        userHandValueLabel = new javax.swing.JLabel();
        userCard11 = new javax.swing.JLabel();
        userCard10 = new javax.swing.JLabel();
        userCard9 = new javax.swing.JLabel();
        userCard8 = new javax.swing.JLabel();
        userCard7 = new javax.swing.JLabel();
        userCard6 = new javax.swing.JLabel();
        userCard5 = new javax.swing.JLabel();
        userCard4 = new javax.swing.JLabel();
        userCard3 = new javax.swing.JLabel();
        userCard2 = new javax.swing.JLabel();
        userCard1 = new javax.swing.JLabel();
        stayButton = new javax.swing.JButton();
        computer1FaceDownCardLabel = new javax.swing.JLabel();
        computer1FaceUpCardLabel = new javax.swing.JLabel();
        computer3FaceUpCardLabel = new javax.swing.JLabel();
        computer3FaceDownCardLabel = new javax.swing.JLabel();
        computer2FaceDownCardLabel = new javax.swing.JLabel();
        computer2FaceUpCardLabel = new javax.swing.JLabel();
        computer3NameLabel = new javax.swing.JLabel();
        computer1ChipsLabel = new javax.swing.JLabel();
        computer1HandValueLabel = new javax.swing.JLabel();
        computer1BetLabel = new javax.swing.JLabel();
        computer3NumCardsLabel = new javax.swing.JLabel();
        computer2NameLabel = new javax.swing.JLabel();
        computer1NameLabel = new javax.swing.JLabel();
        computer3HandValueLabel = new javax.swing.JLabel();
        computer2HandValueLabel = new javax.swing.JLabel();
        computer2ChipsLabel = new javax.swing.JLabel();
        computer3ChipsLabel = new javax.swing.JLabel();
        computer2BetLabel = new javax.swing.JLabel();
        computer3BetLabel = new javax.swing.JLabel();
        computer1NumCardsLabel = new javax.swing.JLabel();
        computer2NumCardsLabel = new javax.swing.JLabel();
        dealButton = new javax.swing.JButton();
        betComboBox = new javax.swing.JComboBox<>();
        betInputLabel = new javax.swing.JLabel();
        potLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextPane();
        difficultyLabel = new javax.swing.JLabel();
        computer1HandStatusLabel = new javax.swing.JLabel();
        computer2HandStatusLabel = new javax.swing.JLabel();
        computer3HandStatusLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        optMenu = new javax.swing.JMenu();
        restartMenuItem = new javax.swing.JMenuItem();
        rulesMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack v1.0 Game");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userBetLabel.setText("Player Bet: ");
        jLayeredPane1.add(userBetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 620, 217, 23));

        userChipsLabel.setText("Player Chips: 100");
        jLayeredPane1.add(userChipsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, 141, 23));

        userNameLabel.setText("User Name:");
        jLayeredPane1.add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 530, 274, 23));

        hitButton.setText("Hit Me");
        hitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(hitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, -1, -1));

        deckLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/b2fv.png"))); // NOI18N
        jLayeredPane1.add(deckLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 100, 131));

        userHandValueLabel.setText("Player Hand Value:");
        jLayeredPane1.add(userHandValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, 240, 22));
        jLayeredPane1.add(userCard11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 100, 150));
        jLayeredPane1.add(userCard10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, 100, 150));
        jLayeredPane1.add(userCard9, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 100, 150));
        jLayeredPane1.add(userCard8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, 100, 150));
        jLayeredPane1.add(userCard7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 100, 150));
        jLayeredPane1.add(userCard6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 100, 150));
        jLayeredPane1.add(userCard5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, 100, 150));
        jLayeredPane1.add(userCard4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 100, 150));
        jLayeredPane1.add(userCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, 100, 150));
        jLayeredPane1.add(userCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 100, 150));
        jLayeredPane1.add(userCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 100, 150));

        stayButton.setText("Stay");
        stayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stayButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(stayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 550, -1, -1));
        jLayeredPane1.add(computer1FaceDownCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 100, 150));
        jLayeredPane1.add(computer1FaceUpCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 100, 150));
        jLayeredPane1.add(computer3FaceUpCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 100, 150));
        jLayeredPane1.add(computer3FaceDownCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 100, 150));
        jLayeredPane1.add(computer2FaceDownCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, -10, 100, 150));
        jLayeredPane1.add(computer2FaceUpCardLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, -10, 100, 150));

        computer3NameLabel.setText("Computer Player 3");
        jLayeredPane1.add(computer3NameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 250, 22));

        computer1ChipsLabel.setText("Chips: 100");
        jLayeredPane1.add(computer1ChipsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 107, 19));

        computer1HandValueLabel.setText("Hand Value: ");
        jLayeredPane1.add(computer1HandValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 131, 22));

        computer1BetLabel.setText("Bet: ");
        jLayeredPane1.add(computer1BetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 107, 22));

        computer3NumCardsLabel.setText("# Cards: ");
        jLayeredPane1.add(computer3NumCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 240, 107, 23));

        computer2NameLabel.setText("Computer Player 2");
        jLayeredPane1.add(computer2NameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, 200, -1));

        computer1NameLabel.setText("Computer Player 1");
        jLayeredPane1.add(computer1NameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        computer3HandValueLabel.setText("Hand Value: ");
        jLayeredPane1.add(computer3HandValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 160, 131, 22));

        computer2HandValueLabel.setText("Hand Value: ");
        jLayeredPane1.add(computer2HandValueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 131, 22));

        computer2ChipsLabel.setText("Chips: 100");
        jLayeredPane1.add(computer2ChipsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 107, 19));

        computer3ChipsLabel.setText("Chips: 100");
        jLayeredPane1.add(computer3ChipsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 107, 19));

        computer2BetLabel.setText("Bet: ");
        jLayeredPane1.add(computer2BetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 107, 22));

        computer3BetLabel.setText("Bet: ");
        jLayeredPane1.add(computer3BetLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 210, 107, 22));

        computer1NumCardsLabel.setText("# Cards: ");
        jLayeredPane1.add(computer1NumCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 119, 23));

        computer2NumCardsLabel.setText("# Cards: ");
        jLayeredPane1.add(computer2NumCardsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 107, 23));

        dealButton.setText("Deal");
        dealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dealButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(dealButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, -1, -1));

        betComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20", "50" }));
        jLayeredPane1.add(betComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        betInputLabel.setText("Place Your Bet:");
        jLayeredPane1.add(betInputLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, -1, -1));

        potLabel.setText("Current Pot: ");
        jLayeredPane1.add(potLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 130, 30));

        outputPane.setEditable(false);
        jScrollPane2.setViewportView(outputPane);

        jLayeredPane1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 400, 200));

        difficultyLabel.setText("Difficulty: ");
        jLayeredPane1.add(difficultyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, 190, 40));

        computer1HandStatusLabel.setText("Hand Status: ");
        jLayeredPane1.add(computer1HandStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 100, 30));

        computer2HandStatusLabel.setText("Hand Status: ");
        jLayeredPane1.add(computer2HandStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 160, 30));

        computer3HandStatusLabel.setText("Hand Status: ");
        jLayeredPane1.add(computer3HandStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 270, 120, 30));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/background.png"))); // NOI18N
        jLayeredPane1.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 730));

        optMenu.setText("Options");

        restartMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        restartMenuItem.setText("Restart");
        restartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartMenuItemActionPerformed(evt);
            }
        });
        optMenu.add(restartMenuItem);

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
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1390, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void restartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartMenuItemActionPerformed
        // TODO add your handling code here:
        dispose();
        startPage sp = new startPage();
        sp.setVisible(true);
        System.gc();
    }//GEN-LAST:event_restartMenuItemActionPerformed

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

    private void hitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitButtonActionPerformed
        // TODO add your handling code here:
        g.dealCard(g.getPlayer(0));

        updateUserOnHit();
        Random r;
        
        if(g.getPlayer(0).getHand().getHandValue() > 21) {                                                                              //  Check if player is over 21 after hit and update values & screen accordingly
            g.getPlayer(0).setOver21(true);
            g.getPlayer(0).setTurnOver(true);
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            outputPane.setText(outputPane.getText() + "You hit and went over 21.\n\n");
        }
        else {
            outputPane.setText(outputPane.getText() + "You hit successfully.  Your hand value is now " + g.getPlayer(0).getHand().getHandValue() + ".\n\n");
        }                                                                                                                                  //  Pause before either next hand or round
        
        if(g.getPlayer(0).getTurnOver()) {
            while(!g.isHandOver()) {                                                                                                                   //  Loop through computers until hand over if user hits to over 21
                for(int i = 1; i < g.getNumPlayers(); i++) {
                    r = new Random(System.currentTimeMillis());
                    g.computerLoop(i, r, this, outputPane, computer1HandValueLabel, computer2HandValueLabel, computer3HandValueLabel);
                }
            }
        }
        else if(!g.getPlayer(0).getTurnOver()) {                                                                                        //  Go through one loop of computers if user is still in
            for(int i = 1; i < g.getNumPlayers(); i++) {
                r = new Random(System.currentTimeMillis());
                g.computerLoop(i, r, this, outputPane, computer1HandValueLabel, computer2HandValueLabel, computer3HandValueLabel);
                if(g.isHandOver()) {
                    break;
                }
            }
            oneRoundUpdate();
        }
        
        if(g.isHandOver()) {                                                                                                                  //  Update GUI at end of round
            updateHandValueLabels();

            ArrayList<Player> winners = g.determineHandWinner(0);                                                                      //  Return winners as ArrayList of players
            g.distributeChips(winners, outputPane);                                                                                     //  Distribute chips
            updateBetAndChipLabels();

            g.removeHands();                                                                                                            //  Remove all hands to the discard pile

            g.eliminatePlayers(outputPane, this);
            g.determineGameWinner(this, outputPane);

            endofHandUpdate();                                                                                                         //  Update GUI at end of hand
            g.resetRemainingComputer();
        }
    }//GEN-LAST:event_hitButtonActionPerformed

    private void dealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dealButtonActionPerformed
        // TODO add your handling code here:
        startOfHandUpdate();
        
        int bet = Integer.parseInt(betComboBox.getSelectedItem().toString());                                                           //  Get bet from combobox
        if(g.enoughChipsForBet(bet)) {                                                                                                  //  If all remaining players have enough chips for bet
            g.setAllBets(bet);                                                                                                          //  then update all bets to given number 
        }
        else if(!g.enoughChipsForBet(bet)) {                                                                                            //  If all remaining players do not have enough chips for
            g.setAllBets(g.determineMinChips());                                                                                        //  then set all bets to smallest chip value of all remaining players
        }
        outputPane.setText(outputPane.getText() + "The bet has been set to " + g.getPlayer(0).getBet() + " chips.\n");
        
        g.setAllChips();                                                                                                                //  Update all chips based on bet
        outputPane.setText(outputPane.getText() + "You have " + g.getPlayer(0).getChips() + " chips remaining.\n");
        
        g.setPot();                                                                                                                     //  Set & display pot value based on bets
        potLabel.setText("Current Pot: " + g.getPot());
        outputPane.setText(outputPane.getText() + "The pot has been set to " + g.getPot() + " chips.\n\n");
        outputPane.setText(outputPane.getText() + "You may choose to either hit or stay.\n");
        
        updateBetAndChipLabels();                                                                                                       //  Update GUI info on bets & chips for all players
        
        g.dealStartingCards();                                                                                                          //  Deal two cards to each player to begin the hand
        
        endOfDealUpdate();                                                                                                              //  Update card images for all players & hand value for user
    }//GEN-LAST:event_dealButtonActionPerformed

    private void stayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stayButtonActionPerformed
        // TODO add your handling code here:
        hitButton.setEnabled(false);                                                                                                    //  Should not be able to hit again after staying so disable hit & set their turnover to true
        g.getPlayer(0).setTurnOver(true); 
        outputPane.setText(outputPane.getText() + "You chose to stay with your current hand.\n\n");
        
        game_Loop:
        while(!g.isHandOver()) {                                                                                                                   //  Loop while hand is not over
            Random r = new Random(System.currentTimeMillis());
            for(int i = 1; i < g.getNumPlayers(); i++) {
                g.computerLoop(i, r, this, outputPane, computer1HandValueLabel, computer2HandValueLabel, computer3HandValueLabel);
            }
        }
        
        updateHandValueLabels();
        
        ArrayList<Player> winners = g.determineHandWinner(1);                                                                          //  Return winners as ArrayList of players
        g.distributeChips(winners, outputPane);                                                                                         //  Distribute chips
        updateBetAndChipLabels();
        
        g.removeHands();                                                                                                                //  Remove all hands to the discard pile
        
        g.eliminatePlayers(outputPane, this);
        g.determineGameWinner(this, outputPane);
        
        endofHandUpdate();                                                                                                             //  Update GUI at end of hand
        g.resetRemainingComputer();
    }//GEN-LAST:event_stayButtonActionPerformed

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
            java.util.logging.Logger.getLogger(gamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gamePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gamePage().setVisible(true);
            }
        });
    }
    
    private void updateImage(JLabel label, Card c) {
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource(c.getImage())));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JComboBox<String> betComboBox;
    private javax.swing.JLabel betInputLabel;
    private javax.swing.JLabel computer1BetLabel;
    private javax.swing.JLabel computer1ChipsLabel;
    private javax.swing.JLabel computer1FaceDownCardLabel;
    private javax.swing.JLabel computer1FaceUpCardLabel;
    private javax.swing.JLabel computer1HandStatusLabel;
    private javax.swing.JLabel computer1HandValueLabel;
    private javax.swing.JLabel computer1NameLabel;
    private javax.swing.JLabel computer1NumCardsLabel;
    private javax.swing.JLabel computer2BetLabel;
    private javax.swing.JLabel computer2ChipsLabel;
    private javax.swing.JLabel computer2FaceDownCardLabel;
    private javax.swing.JLabel computer2FaceUpCardLabel;
    private javax.swing.JLabel computer2HandStatusLabel;
    private javax.swing.JLabel computer2HandValueLabel;
    private javax.swing.JLabel computer2NameLabel;
    private javax.swing.JLabel computer2NumCardsLabel;
    private javax.swing.JLabel computer3BetLabel;
    private javax.swing.JLabel computer3ChipsLabel;
    private javax.swing.JLabel computer3FaceDownCardLabel;
    private javax.swing.JLabel computer3FaceUpCardLabel;
    private javax.swing.JLabel computer3HandStatusLabel;
    private javax.swing.JLabel computer3HandValueLabel;
    private javax.swing.JLabel computer3NameLabel;
    private javax.swing.JLabel computer3NumCardsLabel;
    private javax.swing.JButton dealButton;
    private javax.swing.JLabel deckLabel;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton hitButton;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu optMenu;
    private javax.swing.JTextPane outputPane;
    private javax.swing.JLabel potLabel;
    private javax.swing.JMenuItem restartMenuItem;
    private javax.swing.JMenuItem rulesMenuItem;
    private javax.swing.JButton stayButton;
    private javax.swing.JLabel userBetLabel;
    private javax.swing.JLabel userCard1;
    private javax.swing.JLabel userCard10;
    private javax.swing.JLabel userCard11;
    private javax.swing.JLabel userCard2;
    private javax.swing.JLabel userCard3;
    private javax.swing.JLabel userCard4;
    private javax.swing.JLabel userCard5;
    private javax.swing.JLabel userCard6;
    private javax.swing.JLabel userCard7;
    private javax.swing.JLabel userCard8;
    private javax.swing.JLabel userCard9;
    private javax.swing.JLabel userChipsLabel;
    private javax.swing.JLabel userHandValueLabel;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
