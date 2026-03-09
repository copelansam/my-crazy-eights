package edu.kennesaw.crazy8s.domain;

public enum Suit {
    HEART("Hearts"),
    DIAMOND("Diamonds"),
    CLUB("Clubs"),
    SPADE("Spades");

    private final String displaySuit;

    private Suit(String displaySuit){

        this.displaySuit = displaySuit;
    }

    public String getDisplaySuit() {
        return this.displaySuit;
    }
}