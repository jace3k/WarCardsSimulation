package jack.test;

import jack.main.Card;
import jack.main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Adam");
    }

    @Test
    void shouldNamed() {
        assertTrue(player.getName().equals("Adam"));
    }

    @Test
    void shouldAddCard() {
        player.addCard(new Card("4H"));

        assertTrue(player.cardCount() == 1);
    }

    @Test
    void shouldReverseCards() {
        player.addCard(new Card("3H"));
        player.addCard(new Card("5H"));
        player.reverseCards();

        assertTrue(player.throwCardFromTop().toString().equals("5H"));
    }

}