package jack.test;

import jack.main.Arbiter;
import jack.main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArbiterTest {

    Player p1;
    Player p2;
    Arbiter arbiter;

    @BeforeEach
    public void setUp() {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        arbiter = new Arbiter(p1, p2);
    }

    @Test
    void shouldHave27Elements() {
        arbiter.giveCardsBetween();

        assertTrue(p1.cardCount() == 27);
        assertTrue(p2.cardCount() == 27);
    }

    @Test
    void shouldPlayer1take() {
        String[] setP1 = {"3H"};
        String[] setP2 = {"2H"};
        arbiter.setOwnGame(setP1, setP2);
        arbiter.giveCardsBetween();
        arbiter.move();

        assertTrue(p2.losed());
        assertTrue(p1.cardCount() == 2);

    }

}