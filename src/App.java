public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();

    
    for (int i = 0; i < 18; i++) {
        System.out.println("Stone placed: " + game.placeStone(i));
    }

    System.out.println("Next player: " + game.getCurrentPlayer());

    
    System.out.println("move from 0 to 23: " + game.moveStone(0, 23));  // Invalid move, not a neighbor

    
    System.out.println("move from 16 to 19: " + game.moveStone(16, 19)); //valid move, neighbor

    System.out.println("position 16: " + game.getStoneAt(16));
    System.out.println("position 19: " + game.getStoneAt(19));
    System.out.println("Next player: " + game.getCurrentPlayer());
}
}
