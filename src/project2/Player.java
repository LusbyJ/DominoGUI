package project2;
/**
 * Player class to handle checking player moves and making player moves
 *
 * @author Justin Lusby
 * @version 1.0
 * @date 2/09/19
 */
public class Player {
    /**
     * Checks to see if a move is possible and where the move is made
     * @param index of tile to be played
     * @param tray current players tray
     * @param board current board to be played on
     * @return outcome
     */
    public int checkMove(int index, Domino tray, Domino board){
        if (index < tray.getSize()) {
            if (board.getSize() == 0) {
                return 0;
            }
            if (board.checkTileRight(board.getSize() - 1) == tray.checkTileLeft(index)) {
                return 1;
            }
            if (board.checkTileLeft(0) == tray.checkTileRight(index)) {
                return 2;
            }
            //Check tiles on the left side of the board
            if (board.checkTileLeft(0) == tray.checkTileLeft(index)) {
                return 3;
            }
            //Check tiles on the right side of the board
            if (board.checkTileRight(board.getSize() - 1) == tray.checkTileRight(index)) {
                return 4;
            }
        }
        return 5;
    }

    /**
     * Check all moves to determine if any are possible
     * Loops through current players tray to determine if any possible moves.
     * For each iteration of the loop, if the tile cannot be played, the
     * value miss is increased.  Once completed if miss is equal to the size
     * of the tray, no moves can be made.
     *
     * @param tray current players tray
     * @param board current board
     * @return 1 if no plays found else return 0
     */
    public static int checkAllMoves(Domino tray, Domino board){
        int miss = 0;
        for(int x = 0; x < tray.getSize(); x++){
            if(new Player().checkMove(x, tray, board) == 5){
                miss++;
            }
        }
        if(miss < tray.getSize()){
            return 0;
        }
        else return 1;
    }

    /**
     * Sets the index of the tile to be played by the computer.
     * Loops through the current computer tray tiles to look for
     * a valid move.
     * @param tray current computer tray
     * @param board current board to play on
     * @return index value of tile to be played
     */
    public static int setComputerMove(Domino tray, Domino board){
        for (int x = 0; x < tray.getSize(); x++) {
            if (new Player().checkMove(x, tray, board) < 5) {
                return x;
            }
        }
        return 5;
    }

    /**
     * Picks up a tile from the specified index of the bone yard
     * @param index of tile to pick up
     * @param tray current tray to add the tile
     * @param boneYard current bone yard to pick up from
     */
    public static void pickUp(int index, Domino tray, Domino boneYard){
        if(index< boneYard.getSize()) {
            tray.add(0, boneYard.removeTile(index));
        }
    }

    /**
     * After checking the outcome of the current tile move, the move
     * is made accordingly
     * @param x index of tile in current players tray
     * @param tray current players tray
     * @param board board to be played on
     * @return
     */
    public static int makeMove(int x, Domino tray, Domino board){
        //check the players move and act accordingly
        int outcome = new Player().checkMove(x, tray, board);
        if (outcome == 0) {
            board.add(0, tray.removeTile(x));
            return 0;
        }
        if (outcome == 1) {
            board.add(board.getSize(), tray.removeTile(x));
            return 0;
        }
        if (outcome == 2) {
            board.add(0, tray.removeTile(x));
            return 0;
        }
        if (outcome == 3) {
            tray.flipTile(x);
            board.add(0, tray.removeTile(x));
            return 0;
        }
        if (outcome == 4) {
            tray.flipTile(x);
            board.add(board.getSize(), tray.removeTile(x));
            return 0;
        }
        return 1;
    }
}