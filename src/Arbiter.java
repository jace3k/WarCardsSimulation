import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Arbiter {
    private HashMap<String, Integer> powers;
    private ArrayList<Card> suit;
    private Player player1;
    private Player player2;
    private ArrayList<Card> pull = new ArrayList<>();

    private boolean randomGame = true;

    /// Serca = Hearts (H)
    /// Dzwonki = Tiles (T)
    /// Żołędzie = Clovers (C)
    /// Wino = Pikes (P)
    /// 0 to 10

    private String[] setMati = {"0H", "5C", "3C", "2T", "3P", "7C", "7T", "7P", "8C", "8P",
            "8T", "3T", "4P", "2P", "KC", "AC", "J", "4T", "5P", "3H",
            "5T", "J", "5H", "DT", "DH", "DP", "DC" };

    private String[] setJacek = {"0T", "7H", "8H", "AH", "AT", "AP", "KH", "KP", "4C", "KT",
            "6C", "0P", "0C", "6H", "6P", "4H", "6T", "J", "9H", "9T",
            "9C", "2C", "2H", "WP", "WH", "WT", "WC" };

    private long time;

    private char[] letters = {'2', '3', '4', '5', '6', '7', '8', '9', '0', 'W', 'D', 'K', 'A'};
    private char[] colors = {'H', 'T', 'C', 'P'};


    private ArrayList<Card> ownCardForMati = new ArrayList<>();
    private ArrayList<Card> ownCardForJacek = new ArrayList<>();

    public Arbiter(Player p1, Player p2) {

        player1 = p1;
        player2 = p2;
        generateSuit();
        generatePowers();

    }

    private void takeCardsFromPlayers() {
        player1.outCards();
        player2.outCards();
    }

    private void generatePowers() {
        powers = new HashMap<>();
        for (char color : colors) {
            int power = 2;
            for ( char c : letters) {
                powers.put(c + "" + color, power);
                power++;
            }
        }
        powers.put("J", 15);
    }

    private void generateSuit() {
        suit = new ArrayList<>();
        for (char color : colors) {
            for ( char c : letters) {
                suit.add(new Card(c + "" + color));
            }
        }
        suit.add(new Card("J"));
        suit.add(new Card("J"));
        Collections.shuffle(suit);
    }

    private void setOwnCards() {


        for (String s : setMati) {
            ownCardForMati.add(new Card(s));
        }

        for (String s : setJacek) {
            ownCardForJacek.add(new Card(s));
        }
        Collections.reverse(ownCardForJacek);
        Collections.reverse(ownCardForMati);
    }

    public void giveCardsBetween() {
        if (randomGame) {
            while (!suit.isEmpty()) {
                player1.addCard(suit.remove(0));
                player2.addCard(suit.remove(0));
            }
        } else {
            setOwnCards();
            player1.addCards(ownCardForMati);
            player2.addCards(ownCardForJacek);
        }
        time = System.nanoTime();
    }

    public void move() {
        if ((System.nanoTime() - time) > 5000000000L) {
            if (randomGame) {
                Lo.g("Gra w nieskonczoność. Rozdaję ponownie...");
                takeCardsFromPlayers();
                generateSuit();
                giveCardsBetween();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Lo.g("Gra w nieskonczoność.");
            }
        }

        if(!player1.losed() && !player2.losed()) {

            Card cp1 = player1.throwCardFromTop();
            Card cp2 = player2.throwCardFromTop();
            pull.add(cp1);
            pull.add(cp2);

            Lo.g(cp1.toString() + " vs " +cp2.toString());

            if (powers.get(cp1.toString()).equals(powers.get(cp2.toString()))) {
                if(!player1.losed() && !player2.losed()) {
                    Card cpm1 = player1.throwCardFromTop();
                    Card cpm2 = player2.throwCardFromTop();


                    Lo.g("Wojna! Pod spodem: (" + cpm1.toString() + " i " + cpm2.toString() + ")");
                    pull.add(cpm1);
                    pull.add(cpm2);

                    move();
                }

            } else if (powers.get(cp1.toString()) > powers.get(cp2.toString())) {
                player1.addCards(pull);
                pull = new ArrayList<>();
                Lo.g("Mati zgarnia!");
            } else {
                player2.addCards(pull);
                pull = new ArrayList<>();
                Lo.g("Jacek zgarnia!");
            }
        }
    }
}
