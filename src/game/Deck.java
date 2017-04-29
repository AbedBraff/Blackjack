/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Zach
 */
public class Deck {
    
    private final int numCards = 52;
    private ArrayList<Card> deck;
    
    public Deck() {                                    
        deck = new ArrayList<>(numCards);                                      
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                Card card = new Card(i, j);
                deck.add(card);
            }
        }
    }
    
    
    //  Accessors
    protected Card getCard(int i) {
        return deck.get(i);
    }
    
    protected int getSize() {
        return deck.size();
    }
    
    protected boolean isEmpty() {
        return deck.isEmpty();
    }
    
    protected void printAllCards() {
        for(int i = 0; i < 52; i++) {
            int value = deck.get(i).getValue();
            int suit = deck.get(i).getSuit();
            System.out.println("Suit is " + suit + " and value is " + value);
        }
        System.out.println("Number of cards is " + deck.size());
    }
    
    
    //  Mutators
    protected void addCard(Card c) {
        deck.add(c);
    }
    
    protected void removeCard() {
        deck.remove(0);
    }
    
    protected void shuffle() {
        Collections.shuffle(deck);
    }
}
