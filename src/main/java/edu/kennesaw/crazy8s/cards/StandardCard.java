package edu.kennesaw.crazy8s.cards;

import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.TurnContext;

public class StandardCard implements Card{

    private final Rank rank;
    private final Suit suit;

    public StandardCard(Rank rank, Suit suit){

        this.rank = rank;
        this.suit = suit;

    }

    @Override
    public Rank getRank(){

        return this.rank;
    }

    @Override
    public Suit getSuit(){

        return this.suit;
    }

    @Override
    public boolean matches(TurnContext turnContext){
        // Checks to see if the card can be placed on top of the discard pile based on the following rules:
        // 1. The ranks match
        // 2. The suits match
        // 3. The rank of the card is 8 (crazy 8)
        // If any of these are true, then the card can be placed in the discard pile

        return (this.rank == turnContext.getTopDiscard().getRank() ||
                this.suit == turnContext.getCurrentSuit() ||
                this.rank == Rank.EIGHT);
    }

    @Override
    public String toString(){

        return this.getRank().getDisplayRank() + " of " + this.getSuit().getDisplaySuit();

    }

}
