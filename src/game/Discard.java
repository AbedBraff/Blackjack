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
public class Discard {
    
    private ArrayList<Card> discard;
    private final int numCards = 52;
    
    public Discard() {
        discard = new ArrayList<>(numCards);
    }
    
    
    //  Accessors
    protected Card getCard(int i) {
        return discard.get(i);
    }
    
    protected int getSize() {
        return discard.size();
    }
    
    protected boolean isEmpty() {
        return discard.isEmpty();
    }
    
    
    //  Mutators
    protected void addCard(Card c) {
        discard.add(c);
    }
    
    protected void removeCard() {
        discard.remove(0);
    }
}
