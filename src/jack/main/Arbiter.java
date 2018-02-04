package jack.main;

import java.util.ArrayList;
import java.util.Collections;

import static jack.main.PowerUtil.colors;
import static jack.main.PowerUtil.letters;

public class Arbiter {
    private ArrayList<Card> suit;
    private Player player1;
    private Player player2;
    private ArrayList<Card> pull = new ArrayList<>();

    private boolean randomGame = true;

    private String[] setP1;
    private String[] setP2;

    private long time;

    private Card actualCardP1;
    private Card actualCardP2;



    public Arbiter(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        generateSuit();
    }

    public void setOwnGame(String[] setP1, String[] setP2) {
        randomGame = false;
        this.setP1 = setP1;
        this.setP2 = setP2;
    }

    private void takeCardsFromPlayers() {
        player1.outCards();
        player2.outCards();
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
        for (String s : setP1) player1.addCard(new Card(s));
        for (String s : setP2) player2.addCard(new Card(s));

        player1.reverseCards();
        player2.reverseCards();
    }

    private void haveAllTheCards() {
        while (!suit.isEmpty()) {
            player1.addCard(suit.remove(0));
            player2.addCard(suit.remove(0));
        }
    }

    public void giveCardsBetween() {
        if (randomGame) {
            haveAllTheCards();
        } else {
            setOwnCards();
        }
        time = System.nanoTime();
    }

    private void operationsIfItTakesTooLong() {
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

    private void makeWarIfNooneLoose() {
        if(!player1.losed() && !player2.losed()) {
            Card warCardP1 = player1.throwCardFromTop();
            Card warCardP2 = player2.throwCardFromTop();

            Lo.g("Wojna! Pod spodem: (" + warCardP1.toString() + " i " + warCardP2.toString() + ")");
            pull.add(warCardP1);
            pull.add(warCardP2);

            move();
        }
    }

    private void throwCardsToPull() {
        actualCardP1 = player1.throwCardFromTop();
        actualCardP2 = player2.throwCardFromTop();
        pull.add(actualCardP1);
        pull.add(actualCardP2);
        Lo.g(actualCardP1.toString() + " vs " +actualCardP2.toString());
    }

    private void giveCardsTo(Player p) {
        p.addCards(pull);
        pull = new ArrayList<>();
        Lo.g(p.getName() + " zgarnia!");
    }

    public void move() {
        if ((System.nanoTime() - time) > 5000000000L) operationsIfItTakesTooLong();

        if(!player1.losed() && !player2.losed()) {
            throwCardsToPull();
            if (actualCardP1.equals(actualCardP2)) makeWarIfNooneLoose();
            else if (actualCardP1.isBetterThan(actualCardP2)) giveCardsTo(player1);
            else giveCardsTo(player2);
        }
    }
}
