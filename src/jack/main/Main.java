package jack.main;

public class Main {

    /// Serca = Hearts (H)
    /// Dzwonki = Tiles (T)
    /// Żołędzie = Clovers (C)
    /// Wino = Pikes (P)
    /// 0 to 10

    public static void main(String[] args) {
        Player p1 = new Player("Mati");
        Player p2 = new Player("Jacek");
        Arbiter arbiter = new Arbiter(p1, p2);

        String[] setMati = {"0H", "5C", "3C", "2T", "3P", "7C", "7T", "7P", "8C", "8P",
                "8T", "3T", "4P", "2P", "KC", "AC", "J", "4T", "5P", "3H",
                "5T", "J", "5H", "DT", "DH", "DP", "DC" };

        String[] setJacek = {"0T", "7H", "8H", "AH", "AT", "AP", "KH", "KP", "4C", "KT",
                "6C", "0P", "0C", "6H", "6P", "4H", "6T", "J", "9H", "9T",
                "9C", "2C", "2H", "WP", "WH", "WT", "WC" };

        arbiter.setOwnGame(setMati, setJacek);

        arbiter.giveCardsBetween();

        while (!p1.losed() && !p2.losed()) arbiter.move();

        if (p1.losed()) Lo.g("Wygrał " + p2.getName());
        if (p2.losed()) Lo.g("Wygrał " + p1.getName());

    }
}
