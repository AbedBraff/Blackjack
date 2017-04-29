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
public class Card {
    
    private int suit, value;
    private String[] cardValue = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private String[] cardSuit = {"Spades", "Diamonds", "Hearts", "Clubs"};
    
    public Card() {
        
    }
    
    public Card(int cSuit, int cValue) {
        suit = cSuit;
        value = cValue;
    }
    
    
    //  Accessors
    protected int getValue() {
        return this.value;
    }
    
    protected int getSuit() {
        return this.suit;
    }
    
    protected String getImage() {
        String num = cardValue[value];
        String cSuit = cardSuit[suit];
        
        return "/game/images/" + num + cSuit + ".png";
        
    }
    
    public String toString() {
        String cardInfo = cardValue[value] + " of " + cardSuit[suit];
        
        return cardInfo;
    }
    
    
    //  Mutators
    protected void set(int cValue, int cSuit) {
        this.value = cValue;
        this.suit = cSuit;
    }
}
