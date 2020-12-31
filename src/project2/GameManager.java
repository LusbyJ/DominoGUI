package project2;
/**
 * Main game loop to control the game.
 * Entry point for the program
 *
 * @author Justin Lusby
 * @version 1.0
 * @date 1/26/19
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameManager extends Application {
    //Rectangle to be used as a blank filler
    private Rectangle space = new Rectangle(60, 20, Color.GREEN);
    private int isPlaying;
    private int i;

    public static Domino humanTray;    // humans tray of tiles
    public static Domino computerTray; // computers tray of tiles
    public static Domino board;        // collection of tiles played on the board
    public static Domino boneYard;     // collection of tiles to be picked up
    public static int player;          // keeps track of current player
    public static int state;           // keeps track of state of game

    /**
     * Sets up and initializes the game,
     * creates the Domino object for each list of dominoes
     * fills the bone yard and shuffles the tiles
     */
    public void setUp() {
        boneYard = new Domino();
        board = new Domino();
        humanTray = new Domino();
        computerTray = new Domino();
        //Fill the boneYard with tiles
        int index = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                if (y >= x) {
                    Tile tile = new Tile(x, y);
                    boneYard.add(index, tile);
                    index++;
                }
            }
        }
        //shuffle and set initial game loop variables
        boneYard.shuffle();
        state = 3;
        player = 0;
        isPlaying = 1;
        startGame();
    }

    /**
     * Removes all elements from the display
     */
    public void remove(){
        for(int x = 0; x < boneYard.getSize(); x++) {
            Display.boneYard.getChildren().remove(boneYard.getTile(x).getImage(0));
        }
        for(int x = 0; x < humanTray.getSize();x++) {
            Display.human.getChildren().remove(humanTray.getTile(x).getImage(0));
        }
        for(int x = 0; x < computerTray.getSize();x++) {
            Display.computer.getChildren().remove(computerTray.getTile(x).getImage(0));
        }
        for(int x = 0; x < board.getSize();x++) {
            if(x == 0) Display.board1.getChildren().remove(space);
            Display.board2.getChildren().remove(board.getTile(x).getImage(0));
            Display.board1.getChildren().remove(board.getTile(x).getImage(0));
        }
    }

    /**
     * Adds all elements into the display updating for any changes
     */
    public void update(){

        for(int x = 0; x < boneYard.getSize(); x++) {
            Display.boneYard.getChildren().add(boneYard.getTile(x).getImage(0));
        }
        for(int x = 0; x < humanTray.getSize();x++) {
            Display.human.getChildren().add(humanTray.getTile(x).getImage(1));
        }
        for(int x = 0; x < computerTray.getSize();x++) {
            Display.computer.getChildren().add(computerTray.getTile(x).getImage(0));
        }
        for(int x = 0; x < board.getSize();x++) {
            if(x == 0){
                Display.board1.getChildren().add(space);
            }
            if(x%2 == 0) {
                Display.board2.getChildren().add(board.getTile(x).getImage(1));
            }
            else{
                Display.board1.getChildren().add(board.getTile(x).getImage(1));
            }
        }
    }

    /**
     * Main game loop for the program.
     *  starts with player = 0 = human, and state = 3
     *  Waits for player to pick up 7 tiles, then state = 0, never returning to 3
     *  Starts with the human player: checks possible moves, if none possible
     *  state changes to 1.  If state = 1, waits for user to click bone yard tile,
     *  state returns to 0. If state = 0, wait for player move.  If a valid move
     *  is performed, player updates by 1, changing to computer player. Computer
     *  makes legal play, picking up a random bone yard tile if required and player
     *  increase by 1 returning back to the human player. Play continues until
     *  either the human, computer, or bone yard tray of tiles is empty.
     *  A corresponding message will display depending on outcome of the game.
     */
    public void startGame() {
        AnimationTimer a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(isPlaying == 1) {
                    //Respond to user input
                    if (player % 2 == 0 && state != 3) {
                        if (Player.checkAllMoves(humanTray, board) == 1) {
                            state = 1;
                        }
                    }
                    //Check computer tiles for move
                    if (player % 2 != 0) {
                        //if no possible moves, remove the first tile from the bone yard
                        if (Player.checkAllMoves(computerTray, board) == 1) {
                            computerTray.add(0, boneYard.removeTile(0));
                        } else {
                            //play first valid tile
                            i = Player.setComputerMove(computerTray, board);
                            if (Player.makeMove(i, computerTray, board) == 0) player += 1;
                        }
                    }

                    //if either players tray is empty, show message and end
                    if (computerTray.getSize() == 0 && state != 3) {
                        stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game");
                        alert.setContentText("Sorry the computer won");
                        alert.show();
                        isPlaying = 0;
                    }
                    if (humanTray.getSize() == 0 && state != 3) {
                        stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game");
                        alert.setContentText("Congratulations you won");
                        alert.show();
                        isPlaying = 0;
                    }
                    if (boneYard.getSize() == 0) {
                        stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game");
                        if(player%2 == 0) {
                            alert.setContentText("BoneYard is Empty, you lost");
                        }
                        else{
                            alert.setContentText("BoneYard is Empty, You win");
                        }
                        alert.show();
                        isPlaying = 0;
                    }
                }
                remove();
                update();
            }
        };
        a.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane boardPane = new BorderPane();
        boardPane.setTop(new Display().createComputerTray());
        boardPane.setCenter(new Display().createBoard());
        boardPane.setBottom(new Display().createHumanTray());
        boardPane.setRight(new Display().createBoneYard());

        stage.setTitle("Dominoes");
        Group root = new Group(boardPane);
        //show the stage with the board node
        Scene scene = new Scene(root, 1180, 550);
        scene.setFill(Color.GREEN);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(state == 3){
                    int x = (int) Math.round(((event.getX() / 60) % 60) - 18);
                    int y = (int) Math.round(((event.getY() / 20) % 20) - 6);
                    int pickUp = (2*y) + x;
                    humanTray.add(0, boneYard.removeTile(pickUp));
                    computerTray.add(0, boneYard.removeTile(0));
                    if(humanTray.getSize() >= 7){
                        state = 0;
                    }
                }
                if(state == 1) {
                    int x = (int) Math.round(((event.getX() / 60) % 60) - 18);
                    int y = (int) Math.round(((event.getY() / 20) % 20) - 6);
                    int pickUp = (2*y) + x;
                    Player.pickUp(pickUp, humanTray, boneYard);
                    state = 0;
                }
                else {
                    int x = (int) Math.round(((event.getX() / 60) % 60) - 2.5);
                    int y = (int)event.getY();
                    if(y < 530 && y > 510) {
                        if (Player.makeMove(x, humanTray, board) == 0) {
                            player += 1;
                        }
                    }
                }
            }
        });

        new GameManager().setUp();
    }
}
