public class Main {
    public static void main(String[] args) {
        Player p1 = new Player();
        Player p2 = new Player();
        Arbiter arbiter = new Arbiter(p1, p2);

        arbiter.giveCardsBetween();

        while (!p1.losed() && !p2.losed()) arbiter.move();

        if (p1.losed()) Lo.g("Wygrał Jacek");
        if (p2.losed()) Lo.g("Wygrał Mati");

    }
}
