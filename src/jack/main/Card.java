package jack.main;

public class Card {
    private String color;
    private PowerUtil power;

    public Card(String color) {
        this.color = color;
        this.power = new PowerUtil();
    }

    @Override
    public String toString() {
        return color;
    }

    public boolean equals(Card c) {
        return power.hasTheSamePower(this, c);
    }

    public boolean isBetterThan(Card second) {
        return power.firstBetter(this, second);
    }


}
