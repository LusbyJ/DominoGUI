/**
 * Class to create list of Dominoes to be used in a standard game of dominoes.
 * Contains all methods needed to manipulate it tiles during game play.
 *
 * @author Justin Lusby
 * @version 1.0
 * @date 1/26/19
 */
package project2;
import java.util.ArrayList;
import java.util.Collections;

public class Domino {
    private ArrayList<Tile> dominoes;
    /**
     * Constructor for a new list of dominoes
     */
    public Domino(){
        this.dominoes = new ArrayList<>();
    }

    /**
     * Gets the left value of this tile
     * @param index of tile
     * @return Left tile value
     */
    public int checkTileLeft(int index){
        return dominoes.get(index).getLeftValue();
    }

    /**
     * Flips the left and right values of this tile
     * @param index
     */
    public void flipTile(int index){

        dominoes.get(index).flipTile();
    }

    /**
     * Returns the tile located at this index of the this list
     * @param index
     * @return tile
     */
    public Tile getTile(int index){

        return dominoes.get(index);
    }

    /**
     * Gets the right value of this tile
     * @param index of tile
     * @return right tile value
     */
    public int checkTileRight(int index){
        return dominoes.get(index).getRightValue();
    }

    /**
     * adds tile to this list of dominoes
     * @param tile
     */
    public Tile add(int index, Tile tile){
        dominoes.add(index, tile);
        return tile;
    }

    /**
     * Method to shuffle the dominoes in this list
     */
    public void shuffle(){
        Collections.shuffle(dominoes);
    }

    /**
     * Removes the tile from this list of dominoes.
     * @return tile at index of this list
     */
    public Tile removeTile(int index){
        return dominoes.remove(index);
    }

    /**
     * checks to see the size of this list of dominoes
     * @return size of list
     */
    public int getSize(){
        return dominoes.size();
    }

    /**
     * Prints out this list of dominoes
     */
    public void printYard(int show){
        for(int x = 0; x < dominoes.size(); x++) {
            if (x % 14 == 0) {
                System.out.println();
            }
            if(show == 1) {
                System.out.print(" [" + dominoes.get(x).getLeftValue() +
                        "|" + dominoes.get(x).getRightValue() + "] ");
            }
            if(show == 0){
                if(x % 14 == 0){
                    System.out.println();
                }
                System.out.print(" [ | ] ");
            }
        }
    }
}