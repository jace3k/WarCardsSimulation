package jack.main;

import java.util.HashMap;

public class PowerUtil {
    private static HashMap<String, Integer> powers = new HashMap<>();

    public static char[] letters = {'2', '3', '4', '5', '6', '7', '8', '9', '0', 'W', 'D', 'K', 'A'};
    public static char[] colors = {'H', 'T', 'C', 'P'};

    public PowerUtil() {
        generatePowers();
    }

    private void generatePowers() {
        for (char color : colors) {
            int power = 2;
            for ( char c : letters) {
                powers.put(c + "" + color, power);
                power++;
            }
        }
        powers.put("J", 15);
    }

    public boolean hasTheSamePower(Card card, Card second) {
        return powers.get(card.toString()).equals(powers.get(second.toString()));
    }

    public boolean firstBetter(Card card, Card second) {
        return powers.get(card.toString()) > powers.get(second.toString());
    }
}
