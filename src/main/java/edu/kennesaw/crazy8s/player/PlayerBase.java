package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerBase implements Player {

    private String name;
    private ArrayList<Card> hand;

    PlayerBase(String name){

        this.name = name;
        this.hand = new ArrayList<Card>();

    }

    @Override
    public abstract Card playCard(Card topDiscard, Suit currentSuit);

    @Override
    public void drawCard(Card card){

        hand.add(card);

    }

    @Override
    public List<Card> getPlayerHand(){

        return this.hand;
    }

    @Override
    public abstract Suit chooseSuit();

    public String getName(){
        return this.name;
    }

}
