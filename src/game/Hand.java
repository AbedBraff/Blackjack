/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
/**
 *
 * @author Zach
 */
public class Hand {
    private final int numCards = 11;
    private ArrayList<Card> hand;
    private int value;
    
    public Hand() {
        hand = new ArrayList<>(numCards);
        value = 0;
    }
    
    
    //  Accessors
    protected Card getCard(int i) {
        return hand.get(i);
    }
    
    protected int getHandValue() {
        int numAces = 0;
        int handValue = 0;
        
        for(int i = 0; i < getSize(); i++) {                                                                                //      Add up value of hand assuming aces are worth 11
            if(hand.get(i).getValue() >= 0 && hand.get(i).getValue() <= 8) {
                handValue += (hand.get(i).getValue() + 2);                                                                  //      for array value 0-8 (2-9 actual card value) use cardvalue + 2 due to cardValue[] in Card.java
            }
            else if(hand.get(i).getValue() == 9 || hand.get(i).getValue() == 10 || hand.get(i).getValue() == 11) {          //      for array value 9-11 (10, jack, queen, king) add 10 to handvalue
                handValue += 10;
            }
            else if(hand.get(i).getValue() == 12) {                                                                         // for array value 12 (aces) add 11 and increment numaces for possible use later
                handValue += 11;
                numAces++;
            }
        }
        
        while(numAces > 0 && handValue > 21) {                                                                              // Loop while player still has aces and a handvalue over 21
            handValue -= 10;                                                                                                //  Decrement numaces and subtract 10 (aces 11 originally down to 1 if handvalue > 21 so 11 - 1 = 10)
            numAces--;
            if(handValue <= 21) {                                                                                           //  Break if/when handValue goes under 22
                break;
            }
        }
        
        return handValue;
    }
    
    protected int getSize() {
        return hand.size();
    }
    
    
    //  Mutators
    protected void addCard(Card c) {
        hand.add(c);
    }
    
    protected void removeHand() {
        while(!hand.isEmpty()) {
            hand.remove(0);
        }
    } 
}
