package project2;
/**
 * Display class for the GUI. Creates and sets up the components of the GUI.
 *
 * @author Justin Lusby
 * @version 1.0
 * @date 2/08/19
 */


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Display {
    public static HBox computer =  new HBox();
    public static HBox human = new HBox();
    public static FlowPane boneYard = new FlowPane();
    public static FlowPane currentBoard = new FlowPane();
    public static HBox board1 = new HBox();
    public static HBox board2 = new HBox();

    /**
     * Creates the HBox to hold the computer's tiles
     * @return tray of tiles
     */
    public HBox createComputerTray(){
        computer.setPrefWidth(1050);
        computer.setPrefHeight(80);
        Label label = new Label("Computer:");
        label.setFont(Font.font ("Verdana", 22));
        computer.getChildren().add(label);
        return computer;
    }

    /**
     * Creates the HBox to hold the human's tiles
     * @retur human's tray of tiles
     */
    public HBox createHumanTray(){
        human.setPrefWidth(1050);
        human.setPrefHeight(80);
        Label label = new Label("Your Tray:");
        label.setFont(Font.font ("Verdana", 22));
        human.getChildren().add(label);
        return human;
    }

    /**
     * Creates the HBox to hold the top row of board tiles
     * @return board1
     */
    public HBox createBoardTray1(){
        board1.setPrefWidth(1050);
        board1.setPrefHeight(20);
        board1.setAlignment(Pos.CENTER);
        return board1;
    }

    /**
     * Creates the HBox to hold the bottom row of board tiles
     * @return board2
     */
    public HBox createBoardTray2(){
        board2.setPrefWidth(1050);
        board2.setPrefHeight(20);
        board2.setAlignment(Pos.CENTER);
        return board2;
    }

    /**
     * Creates the flow pane to hold both top and bottom board rows
     * of played tiles on the board
     * @return board
     */
    public FlowPane createBoard(){
        currentBoard.setPrefWidth(1050);
        currentBoard.setPrefHeight(100);
        currentBoard.setAlignment(Pos.CENTER);
        currentBoard.getChildren().addAll(createBoardTray1(), createBoardTray2());
        return currentBoard;
    }

    /**
     * Creates the FlowPane to hole the bone yard tiles
     * @return bone yard tiles
     */
    public FlowPane createBoneYard(){
        boneYard.setPrefWidth(150);
        boneYard.setPrefHeight(400);
        Label label = new Label("Bone Yard:");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font ("Verdana", 22));
        boneYard.getChildren().add(label);
        return boneYard;
    }
}