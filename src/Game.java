public class Game {

    // GAME BOARD
    private char[] board;

    // PLAYERS
    private char currentPlayer = 'W';
    private int whiteStonesToPlace = 9;
    private int blackStonesToPlace = 9;
    private boolean mustRemoveStone = false; // After the Mill, that shows if the player must remove a Stone.

    public Game() { // Game Board
        board = new char[24];
        for (int i = 0; i < board.length; i++) {
            board[i] = 'L';
        }
    }

    // All the Mills on the board.
    private final int Mills[][] = {
            { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 9, 10, 11 },
            { 12, 13, 14 }, { 15, 16, 17 }, { 18, 19, 20 }, { 21, 22, 23 },
            { 0, 9, 21 }, { 3, 10, 18 }, { 6, 11, 15 }, { 1, 4, 7 },
            { 16, 19, 22 }, { 8, 12, 17 }, { 5, 13, 20 }, { 2, 14, 23 }
    };

    private final int neighbors[][] = { //Define the neighbours of points.
        {1, 9}, {0, 2, 4}, {1, 14}, {4, 10}, {1, 3, 5, 7}, {4,13}, {7, 11}, {4, 6, 8}, {7, 12}, {0, 10, 21},  // as an Example, this ones from 0,1,2,3,4,5,6,7,8,9
        {3, 9, 11, 18}, {6, 10, 15}, {8, 13, 17}, {5, 12, 14, 20}, {2, 13, 23}, //from 10 to 14
        {11, 16}, {15, 17, 19}, {12, 16}, //15 to 17
        {10, 19}, {16, 18, 20, 22}, {13, 19}, // 18 to 20
        {9, 22}, {19, 21, 23}, {14, 22} //21 to 23
    };

    public boolean isNeighbor(int from, int to){
        for(int nachbarn : neighbors[from]){
            if(nachbarn == to){
                return true;
            }
        }
         return false;
    }

    public int countStonesOnBoard (char player){
        int count = 0;
        for (int i = 0; i < board.length; i++ ){
           if( board[i] == player);{
             count++;
             }
        }
        return count;
    }

    public char getStoneAt(int position) { // Reads the stone at the given position on the board.
        return board[position];

    }

    public char getCurrentPlayer() { // Reads the current player, whose turn it is.
        return currentPlayer;
    }

    private char getOpponentPlayer() {  // Gets info about the opponent.
        if (currentPlayer == 'W') {
            return 'S';
        } else {
            return 'W';
        }
    }

    public boolean mustRemoveStone() { // Checks if the current player must remove a stone.
        return mustRemoveStone;
    }

    public boolean placeStone(int position) { // Place the stone if the position is valid.

        if (position < 0 || position >= board.length) {
            return false;
        }

        if (mustRemoveStone) {
            return false;

        }

        if (board[position] != 'L') {
            return false;
        }

        // Checks if the player has stones left.
        if (currentPlayer == 'W' && whiteStonesToPlace == 0) {
            return false;
        }

        if (currentPlayer == 'S' && blackStonesToPlace == 0) {
            return false;
        }

        board[position] = currentPlayer;

        if (currentPlayer == 'W') {
            whiteStonesToPlace--;
        } else {
            blackStonesToPlace--;
        }

        // if the player in mills position he must remove a stone. else turn switches.
        if (isInMill(position, currentPlayer)) {
            mustRemoveStone = true;
        } else {
            switchPlayer();
        }

        return true;

    }

    public boolean removeStone(int position) { // Removes the stone if the position is valid.

        if (position < 0 || position >= board.length) {
            return false;
        }

        if (!mustRemoveStone) {
            return false;
        }

        if (board[position] != getOpponentPlayer()) {
            return false;
        }

        if (isInMill(position, getOpponentPlayer()) && hasStoneOutsideMill(getOpponentPlayer())) {
            return false;
        }

        board[position] = 'L';
        mustRemoveStone = false;
        switchPlayer();
        return true;

    }

    private void switchPlayer() { // Switches the current player.
        if (currentPlayer == 'W') {
            currentPlayer = 'S';
        } else {
            currentPlayer = 'W';
        }
    }

    // Checks if there is a mill formed by a player.
    public boolean isMill(int a, int b, int c, char player) {
        return board[a] == player && board[b] == player && board[c] == player;
    }

    public boolean isInMill(int position, char player) { // Checks if the stone at the given position is part of a mill.
        for (int[] mill : Mills) {
            if (mill[0] == position || mill[1] == position || mill[2] == position) {
                if (isMill(mill[0], mill[1], mill[2], player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasStoneOutsideMill(char player) { // check if the player has stones outside of the mill

        for (int i = 0; i < board.length; i++) {
            if (board[i] == player && !isInMill(i, player)) {
                return true;
            }
        }
        return false;
    }

}
