package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.TurnContext;

import java.util.List;
import java.util.Random;

// The CPU Player will randomly select options anytime an option is available
// The CPU is a compulsive gambler, maybe one day they'll get over it

public class CpuPlayer extends PlayerBase{

    private final Random rand = new Random();

    public CpuPlayer(){

        super("(Hopefully) Friendly CPU");
    }

    @Override
    public Card playCard(TurnContext turnContext){

        List<Card> playableCards = filterPlayableCards(turnContext);

        if (playableCards.isEmpty()){
            System.out.println("The CPU no playable cards. You will have to draw a card");

            return null;
        }

        showPlayableCards(turnContext, playableCards);

        int randInt = rand.nextInt(playableCards.size() + 1) - 1;
        if (randInt == -1){
            System.out.println(turnContext.getCurrentPlayer().getName() + " has chosen to draw a card instead of playing one.");
            return null;
        }
        return playableCards.get(randInt);
    }

    // CPU always plays drawn card if it is possible to play it.
    @Override
    public Card playDrawnCard(Card drawnCard, TurnContext turnContext){

        if (drawnCard.matches(turnContext)){
            return drawnCard;
        }
        else{
            return null;
        }
    }

    // CPU player chooses a random suit
    @Override
    public Suit chooseSuit(){

        Suit[] suits = Suit.values();

        int randInt = rand.nextInt(suits.length);
        return suits[randInt];

    }
}
