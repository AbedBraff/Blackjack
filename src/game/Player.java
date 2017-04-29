/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
/**
 *
 * @author Zach
 */
public class Player {
    
    private Hand h;
    private int chips, bet;
    private boolean turnOver, over21, outOfChips;
    private String name;
    
    public Player() {
        h = new Hand();
        chips = 100;
        turnOver = false;
        over21 = false;
        name = null;
        outOfChips = false;
    }
    
    
    //  Accessors
    protected String getName() {
        return name;
    }
    
    protected int getChips() {
        return chips;
    }
    
    protected int getBet() {
        return bet;
    }
    
    protected Hand getHand() {
        return h;
    }
    
    protected boolean getTurnOver() {
        return turnOver;
    }
    
    protected boolean getOver21() {
        return over21;
    }
    
    protected boolean getOutOfChips() {
        return outOfChips;
    }
    
    
    //  Mutators
    protected void setName(String s) {
        name = s;
    }
    
    protected void setChips(int i) {
        chips = i;
    }
    
    protected void setBet(int i) {
        bet = i;
    }
    
    protected void setTurnOver(boolean b) {
        turnOver = b;
    }
    
    protected void setOver21(boolean b) {
        over21 = b;
    }
    
    protected void setOutOfChips(boolean b) {
        outOfChips = b;
    }
}
