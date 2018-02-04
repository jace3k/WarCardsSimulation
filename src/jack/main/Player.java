package jack.main;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private ArrayList<Card> cards = new ArrayList<>();
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card throwCardFromTop() {
        return cards.remove(0);
    }

    public boolean losed() {
        return cards.isEmpty();
    }

    public int cardCount() {
        return cards.size();
    }

    public void outCards() {
        this.cards = new ArrayList<>();
    }

    public void reverseCards() {
        Collections.reverse(this.cards);
    }
}
