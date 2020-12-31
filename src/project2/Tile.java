/**
 * Class to create the domino tile object.
 *
 * @author Justin Lusby
 * @version 1.0
 * @date 1/26/19
 */
package project2;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane{
    private int leftValue;
    private int rightValue;
    private Text values;
    private StackPane tile;
    private Rectangle piece;

    /**
     * Constructor for the tile object
     * @param leftValue left value of the tile
     * @param rightValue right value of the tile
     */
    public Tile(int leftValue, int rightValue){
        this.tile = new StackPane();
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.values = new Text("   |   ");
        this.piece = new Rectangle(60, 20, Color.BEIGE);
        this.piece.setStroke(Color.BLACK);
        this.tile.getChildren().addAll(piece, values);
    }

    /**
     * Gets teh image of the tile
     * @return tile
     */
    public StackPane getImage(int show){
        if(show == 1){
            this.tile.getChildren().remove(values);
            values = new Text(leftValue + "  |  " + rightValue);
            this.tile.getChildren().add(values);
        }
        return tile;
    }

    /**
     * Swaps the values of this tile
     */
    public void flipTile(){
        int temp = leftValue;
        leftValue = rightValue;
        rightValue = temp;
        this.tile.getChildren().remove(values);
        values = new Text(leftValue + "  |  " + rightValue);
        this.tile.getChildren().add(values);
    }
    /**
     * Method to return the left value of tile
     * @return leftValue
     */
    public int getLeftValue(){
        return leftValue;
    }

    /**
     * Method to return the right value of the tile
     * @return rightValue
     */
    public int getRightValue(){
        return rightValue;
    }

}