package ee.taltech.theme2.part9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cardgame {

    //todo 19.1 build a deck of cards
    // fix tests and add logic
    public static List<Card> buildDeck() {
        List<Card> cards = new ArrayList<>();
        return cards;
    }

    public static List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }

    public static void main(String[] args) {
        //todo 19.2 this is more advanced stuff
        // but try to simulate playing poker (buildDeck, shuffle and get 5 cards)
    }
}
