package game;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board  {
	Stage pStage;
    public final int BOARD_SIZE = 8;
    public char [][] board;
    
    Button button;
    Button button2;
    Button[][] buttons = new Button[8][8];
//    public void start_this(Stage primaryStage) {
//    	primaryStage.setTitle("Othello");
//    	button = new Button();
//    	button.setText("hello ");
//    	StackPane layout = new StackPane();
//    	layout.getChildren().add(button);
//    	Scene scene =new Scene(layout, 300,250);
//    	primaryStage.setScene(scene);
//    	primaryStage.show();
//    }
    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int k = 0; k < BOARD_SIZE; k++) {
                this.board[i][k] = '_';
            }
        }
        this.board[3][3] = 'w';
        this.board[3][4] = 'b';
        this.board[4][3] = 'b';
        this.board[4][4] = 'w';
    }

    public Board(Board board) {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int k = 0; k < BOARD_SIZE; k++){
                this.board[i][k] = board.board[i][k];
            }
        }
    }

    /**
     * It prints the board
     */
    public void printBoard() {
        System.out.println("   0 1 2 3 4 5 6 7");
        for(int i = 0; i < this.board.length; i++){
            System.out.print(i + "  ");
            for(int k = 0; k < this.board.length; k++){
                System.out.print(this.board[i][k]+" ");
            }
            System.out.println();
        }
//        start_this(pStage);
    }
	public void start_this(Stage primaryStage) {
		pStage=primaryStage;
		pStage.setTitle("Othello");
		StackPane layouts = new StackPane();
        for(int i = 0; i < this.board.length; i++){
            for(int k = 0; k < this.board.length; k++){
            	buttons[i][k]=new Button("   ");
            	if(this.board[i][k]=='w') {
            		buttons[i][k].setStyle("-fx-background-color: #ffffff");
            	}
            	else if(this.board[i][k]=='b') {
            		buttons[i][k].setStyle("-fx-background-color: #000000");
            	}
            	else {
            		buttons[i][k].setStyle("-fx-background-color: #00ff00");
            	}
            	buttons[i][k].setTranslateX(i*30-100);
            	buttons[i][k].setTranslateY(k*30-100);
            	layouts.getChildren().add(buttons[i][k]);
            }
        }
        Scene scenes =new Scene(layouts, 300,300);
        pStage.setScene(scenes);
        pStage.setResizable(false);
        pStage.show();	
	}
	public void copyBoard(char[][] board) {
		for(int i = 0; i < this.BOARD_SIZE; i++){
            for(int k = 0; k < this.BOARD_SIZE; k++){
               this.board[i][k] = board[i][k];
            }
        }
	}
}












