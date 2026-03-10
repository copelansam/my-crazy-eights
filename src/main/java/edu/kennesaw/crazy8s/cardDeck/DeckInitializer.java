package edu.kennesaw.crazy8s.cardDeck;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.cards.StandardCard;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;

import java.util.*;

// Responsible for creating a deck and shuffling it
public class DeckInitializer {

    public static Deck createDeck(){

        return new Deck(createCards());
    }

    private static List<Card> createCards(){

        List<Card> cards = new ArrayList<>();

        for (Suit suit: Suit.values()){
            for (Rank rank: Rank.values()){
                cards.add(new StandardCard(rank,suit));
            }
        }

        return shuffle(cards);
    }

    private static List<Card> shuffle(List<Card> cards){

        Collections.shuffle(cards);
        return cards;
    }

}
