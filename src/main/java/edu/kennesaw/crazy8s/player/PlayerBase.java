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

    // Performs player's turn by delegating to TurnAction
    // which calls concrete implementations for chooseSuit, playCard, playDrawnCard
    @Override
    public void takeTurn(GameContext gameContext, TurnContext turnContext){
        TurnAction.takeTurn(gameContext, turnContext);
    }

    @Override
    public abstract Suit chooseSuit();

    public String getName(){
        return this.name;
    }

    // Displays all of the cards in the player's hand
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

    // returns an unmodifiable list of all of the cards that are playable based on the current turn context
    public List<Card> filterPlayableCards(TurnContext turnContext){

        List<Card> playable = new ArrayList<Card>();

        for (Card card : hand) {

            if (card.matches(turnContext)){

                playable.add(card);
            }

        }

        if (playable.isEmpty()){
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(playable);
    }

    // Shows all of the cards that are playable based on the current turn context, and the reason why they are playable
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
