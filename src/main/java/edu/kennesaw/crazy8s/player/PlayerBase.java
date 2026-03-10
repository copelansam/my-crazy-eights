package edu.kennesaw.crazy8s.player;

import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.game.GameContext;
import edu.kennesaw.crazy8s.game.TurnAction;
import edu.kennesaw.crazy8s.game.TurnContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PlayerBase implements Player {

    private String name;
    protected final List<Card> hand = new ArrayList<>();

    PlayerBase(String name){

        this.name = name;
    }

    @Override
    public abstract Card playCard(TurnContext context);

    @Override
    public abstract Card playDrawnCard(Card drawnCard, TurnContext turnContext);

    @Override
    public void drawCard(Card card){
        hand.add(card);
    }

    @Override
    public void discard(Card card){
        hand.remove(card);
    }

    @Override
    public void takeTurn(GameContext gameContext, TurnContext turnContext){
        TurnAction.takeTurn(gameContext, turnContext);
    }

    @Override
    public abstract Suit chooseSuit();

    public String getName(){
        return this.name;
    }

    public void showAllCards(){
        System.out.println("Your current hand:");
        for (Card card: hand){
            System.out.println("- " + card.toString());
        }
    }

    public int getHandSize(){
        return hand.size();
    }

    public boolean emptyHand(){
        return hand.isEmpty();
    }

    public List<Card> filterPlayableCards(TurnContext turnContext){

        List<Card> playable = new ArrayList<Card>();

        // Filters out the unplayable cards based on the current card on top of the discard pile & current suit
        for (Card card : hand) {

            if (card.matches(turnContext)){

                playable.add(card);
            }

        }

        if (playable.isEmpty()){
            return Collections.emptyList();
        }

        return playable;
    }

    public void showPlayableCards(TurnContext turnContext, List<Card> playableCards){

        System.out.println("\nYour playable cards are: ");
        int counter = 1;
        for (Card card: playableCards){
            System.out.print(counter + ". " + card.toString());

            if (card.getRank() == Rank.EIGHT){
                System.out.print("(CRAZY EIGHT! You'll get to pick the suit!)");
            }
            else if (card.getRank() == turnContext.getTopDiscard().getRank()) {
                System.out.print("(Matching Rank)");
            }
            else {
                System.out.print("(Matching Suit)");
            }
            counter++;
            System.out.println();
        }

    }

}
