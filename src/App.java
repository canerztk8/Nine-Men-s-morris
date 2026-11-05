public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();

        game.placeStone(0); // W
        game.placeStone(3); // S
        game.placeStone(1); // W
        game.placeStone(4); // S
        game.placeStone(2); // W: değirmen oluştu

        System.out.println(game.mustRemoveStone()); // true
        System.out.println(game.getCurrentPlayer()); // W

        System.out.println(game.placeStone(6)); // false
    }
}
