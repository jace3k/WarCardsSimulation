import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards = new ArrayList<>();


    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card throwCardFromTop() {
        return cards.remove(0);
    }

    public String showCards() {
        return cards.toString();
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
}
