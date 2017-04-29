/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextPane;
/**
 *
 * @author Zach
 */
public class Game {
    private int numPlayers, difficulty, playersRemaining, pot;
    Deck d;
    Discard discard;
    private ArrayList<Player> players;
    
    public Game() {
        
    }
    
    public Game(String gPlayers, String gDifficulty) {
        d = new Deck();                                 //  Create & shuffle starting deck
        d.shuffle();
        
        discard = new Discard();                        //  Create discard pile
        
        if(gPlayers.compareTo("2") == 0) {              //  Parse num players combo box info & convert to int value
            numPlayers = 2;
        }
        else if(gPlayers.compareTo("3") == 0) {
            numPlayers = 3;
        }
        else {
            numPlayers = 4;
        }
        players = new ArrayList<>(numPlayers);
        playersRemaining = numPlayers;
        

        for(int i = 0; i < numPlayers; i++){             //  Add players to ArrayList players
            Player p = new Player();
            players.add(p);
        }
        
        if(gDifficulty.compareTo("Easy") == 0) {        // Parse difficulty combo box info & convert to int value
            difficulty = 0;
        }
        else {
            difficulty = 1;
        }
    }
    
    
    //      Accessors
    protected int getNumPlayers() {
        return numPlayers;
    }
    
    protected int getDifficulty() {
        return difficulty;
    }
    
    protected Player getPlayer(int i) {
        return players.get(i);
    }
    
    protected int getPlayersSize() {
        return players.size();
    }
    
    protected int getPlayersRemaining() {
        return playersRemaining;
    }
    
    protected int getPot() {
        return pot;
    }
    
    
    //      Mutators
    protected void setNumPlayers(int i) {
        numPlayers = i;
    }
    
    protected void setDifficulty(String s) {
        if(s.compareTo("Easy") == 0) {
            difficulty = 0;
        }
        else {
            difficulty = 1;
        }
    }
    
    protected void updatePlayersRemaining() {
        playersRemaining--;
    }
    
    protected void setPot() {
        pot = determinePot();
    }
    
    protected void setAllChips() {
        for(int i = 0; i < getNumPlayers(); i++) {
            if(!getPlayer(i).getOutOfChips()) {
                getPlayer(i).setChips(getPlayer(i).getChips() - getPlayer(i).getBet());
            }
        }
    }
    
    protected void setAllBets(int i) {
        for(int j = 0; j < getNumPlayers(); j++) {
            if(!getPlayer(j).getOutOfChips()) {
                getPlayer(j).setBet(i);
            }
        }
    }
    
    
    //      Game Logic & Dealing Cards
    protected boolean enoughChipsForBet(int i) {            //  Determine if everyone still in the game has enough chips for the required bet
        boolean enoughChips = true;
        
        for(int j = 0; j < getNumPlayers(); j++) {
            if(!getPlayer(j).getOutOfChips()) {
                if(getPlayer(j).getChips() < i) {
                    enoughChips = false;
                    break;
                }
            }
        }
        
        return enoughChips;
    }
    
    protected int determineMinChips() {                     //  Determines min value of chips held among the players still in the game
        int minChips = getPlayer(0).getChips();
        for(int i = 1; i < getNumPlayers(); i++) {
            if(!getPlayer(i).getOutOfChips()) {
                if(getPlayer(i).getChips() < minChips) {
                    minChips = getPlayer(i).getChips();
                }
            }
        }
        
        return minChips;
    }
    
    protected int determinePot() {                          //      Add up bets to set pot for hand
        int pot = 0;
        for(int i = 0; i < getNumPlayers(); i++) {
            pot += getPlayer(i).getBet();
        }
        return pot;
    }
    
    protected void dealStartingCards() {                    //      Deal 2 cards to each player at start of hand & check if deck is empty, refilling if true
        for(int i = 0; i < getNumPlayers(); i++) {
            for(int j = 0; j < 2; j++) {
                if(!getPlayer(i).getOutOfChips()) {
                    getPlayer(i).getHand().addCard(d.getCard(0));
                    d.removeCard();
                    if(d.isEmpty()) {
                        refillDeck();
                    }
                }
            }
        }
    }
    
    protected void dealCard(Player p) {                     //      Deal a card to one player & check if deck is empty, refilling if true
        p.getHand().addCard(d.getCard(0));
        d.removeCard();
        
        if(d.isEmpty()) {
            refillDeck();
        }
    }
    
    protected void refillDeck() {                           //      Refill deck from discard pile
        for(int i = 0; i < discard.getSize(); i++) {
            d.addCard(discard.getCard(0));
            discard.removeCard();
        }
    }
    
    protected ArrayList<Player> determineHandWinner(int state) {                           //  State is the situation in which this was called
        ArrayList<Player> winners = new ArrayList<>(getNumPlayers());
        while(!winners.isEmpty()) {                                                         //  Make sure winners is empty
            winners.remove(0);
        }

        boolean allPlayersOver21 = true;                                                //  Always check first in case all players over 21 (nobody should win)
        for(int i = 0; i < getNumPlayers(); i++) {
            if(getPlayer(i).getOver21() == false) {
                allPlayersOver21 = false;
                break;
            }
        }
        if(allPlayersOver21) {
            return winners;
        }
        
        boolean allComputerOver21 = false;
        if(state == 1) {                                                                //  Called w/ state 1 from stay button in gamePage
            allComputerOver21 = true;
            for(int i = 1; i < getNumPlayers(); i++) {                                                                                          //  Determine if all computer players are over 21
                if(getPlayer(i).getOver21() == false) {                                                                                         //  If so, the player wins because you can only choose to stay while <= 21
                    allComputerOver21 = false;
                }
            }
        }

        if(allComputerOver21) {
            winners.add(getPlayer(0));                                                                                                      //  Player wins
        }
        else {
            Player p = new Player();
            winners.add(p);                                                                                                                           //  Figure out which player(s) won including returning multiple players in event of tie
            for(int i = 0; i < getNumPlayers(); i++) {
                if(!getPlayer(i).getOver21()) {
                    if((winners.get(0).getHand().getHandValue() < getPlayer(i).getHand().getHandValue()) && !getPlayer(i).getOutOfChips()        //     Check if player(i) has a higher hand value under 22 and the player still has chips (has not lost) 
                            && getPlayer(i).getHand().getHandValue() < 22) {           
                            while(!winners.isEmpty()) {                                                                                                 //      If true empty the winner list (in case there was a tie previously and multiple spots were taken up)
                                winners.remove(0);
                            }
                        winners.add(0, getPlayer(i));
                    }
                    else if(winners.get(0).getHand().getHandValue() == getPlayer(i).getHand().getHandValue()) {                                         //      Add player to winner list if tied with current winner(s)
                        winners.add(getPlayer(i));
                    }
                }
            }
        }

        
        return winners;
    }
    
    protected void distributeChips(ArrayList<Player> winners, JTextPane pane) {
        if(winners.isEmpty()) {
            pane.setText(pane.getText() + "Everybody went over 21 so nobody wins!\n\n");        //  If winners is empty everybody went over 21 and nobody wins
        }
        
        for(int i = 0; i < winners.size(); i++) {
            for(int j = 0; j < getNumPlayers(); j++) {
                if(winners.get(i).getName().compareTo(getPlayer(j).getName()) == 0) {       //  Go through winner list and all players comparing names to determine who won and distributing chips evenly
                    int currentChips = winners.get(i).getChips();
                    winners.get(i).setChips(currentChips + (Math.round(getPot() / winners.size())));
                    pane.setText(pane.getText() + getPlayer(j).getName() + " has won " + Math.round(getPot() / winners.size()) + " chips for winning that hand!\n\n");
                }
            }
        }
    }
    
    protected void determineGameWinner(gamePage gp, JTextPane pane) {
        if(getPlayersRemaining() == 1) {
            for(int i = 0; i < getNumPlayers(); i++) {
                if(!getPlayer(i).getOutOfChips()) {                                                     //  Game is over when playersremaining = 1, so if player(i) isn't out of chips, they must have won
                    pane.setText(pane.getText() + getPlayer(i).getName() + " HAS WON THE GAME!\n\n");
                    resultPage rp = new resultPage(this, getPlayer(i));                                 //  Load resultpage
                    rp.setVisible(true);
                    gp.dispose();
                }
            }
        }
    }
    
    protected void eliminatePlayers(JTextPane pane, gamePage gp) {
        for(int i = 0; i < getNumPlayers(); i++) {                                       // If computer is out
            if(getPlayer(i).getChips() == 0 && !getPlayer(i).getOutOfChips()) {
                getPlayer(i).setOutOfChips(true);
                updatePlayersRemaining();
                getPlayer(i).setBet(0);
                pane.setText(pane.getText() + getPlayer(i).getName() + " has been eliminated from the game!\n\n");
            }
        }
        
        if(getPlayer(0).getOutOfChips()) {                                              //      If user is out
            pane.setText(pane.getText() + "You lost!  Try again!\n\n");
            resultPage rp = new resultPage();
            rp.setVisible(true);
            gp.dispose();
        }
    }
    
    protected void removeHands() {          //  Remove the cards from every player's hand
        for(int i = 0; i < getNumPlayers(); i++) {
            for(int j = 0; j < getPlayer(i).getHand().getSize(); j++) {
                discard.addCard(getPlayer(i).getHand().getCard(j));
            }
            getPlayer(i).getHand().removeHand();
        }
    }
    
    protected boolean isHandOver() {        //  Determine if current hand has ended
        boolean handOver = true;
        
        for(int i = 0; i < getNumPlayers(); i++) {
            if(!getPlayer(i).getTurnOver() && !getPlayer(i).getOutOfChips()) {
                handOver = false;
            }
        }
        
        return handOver;
    }
    
    protected int determineAIChoice(int difficulty, Random r, Player p) {               //  Seed is the difficulty where 0 is easy and 1 is hard
        if(difficulty == 0) {
            return r.nextInt(1 - 0 + 1) + 0;                                            //  value of 0 or 1 where 0 is hit and 1 is stay
        }
        if(p.getHand().getHandValue() < 17 && difficulty == 1) {                                           // Med difficulty hits on 0-16 and stays on 17+
            return 0;
        }
        
        return 1;
    }
    
    protected void resetRemainingComputer() {       //  Reset value of players not yet eliminated for next hand
        for(int i = 0; i < getNumPlayers(); i++) {
            if(!getPlayer(i).getOutOfChips()) {
                getPlayer(i).setOver21(false);
                getPlayer(i).setTurnOver(false);
            }
        }
    }
    
    protected void computerLoop(int i, Random r, gamePage gp, JTextPane outputPane, JLabel computer1HandValueLabel, JLabel computer2HandValueLabel, JLabel computer3HandValueLabel) {
        if(getPlayer(i).getOver21() == false && getPlayer(i).getTurnOver() == false) {                              //  If player is still in game go through the AI choice loop & consequences
            int choice = determineAIChoice(getDifficulty(), r, getPlayer(i));
            if(choice == 0) {                                                                                       //  Computer player chooses to hit
                    dealCard(getPlayer(i));                                                                         //  Deal card to player i
                if(getPlayer(i).getHand().getHandValue() > 21) {                                                    //  Check if player hand value > 21 and set values accordingly
                    getPlayer(i).setOver21(true);
                    getPlayer(i).setTurnOver(true);
                    outputPane.setText(outputPane.getText() + getPlayer(i).getName() + " hit and went over 21!\n\n");
                        if(i == 1 && getPlayer(i).getTurnOver()) {
                            computer1HandValueLabel.setText("Hand Value: " + getPlayer(1).getHand().getHandValue());
                        }
                        if(i == 2 && getPlayer(i).getTurnOver()) {
                            computer2HandValueLabel.setText("Hand Value: " + getPlayer(2).getHand().getHandValue());
                        }
                        if(i == 3 && getPlayer(i).getTurnOver()) {
                            computer3HandValueLabel.setText("Hand Value: " + getPlayer(3).getHand().getHandValue());
                        }
                }
                else{                                                                                                               //  Player hit and is not over 21
                    outputPane.setText(outputPane.getText() + getPlayer(i).getName() + " hit and is still under 21.\n\n");
                }
                gp.updateNumCardsLabels();
            }
            else {                                                                                                                  // Computer player chooses to stay
                getPlayer(i).setTurnOver(true);
                outputPane.setText(outputPane.getText() + getPlayer(i).getName() + " decided to stay with their current hand.\n\n");
            }
        }
    }
}
