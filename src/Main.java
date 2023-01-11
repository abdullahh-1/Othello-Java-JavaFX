import game.*;

import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class Main extends Application {
	Button button;
    Button button2;
    Button[][] buttons = new Button[8][8];
	public static final int grid_cell=3;
	public static final int cell_size=0;
	Othello game;
	Board board;
	StackPane layouts;
	Stage stage;
	PlayerImpl player;
	static int count = 0;

	 public EventHandler<Event> createButtonHandler() {
		 EventHandler btnHandler = new EventHandler<Event>() {

			 @Override
			 public void handle(Event event) {
				 count ++;
				 Button btn = (Button) event.getTarget();
				 int id = Integer.parseInt(btn.getId());
				 int col = id / 8;
				 int row = id % 8;
				 System.out.println(row + " " + col);
				 int valid = game.play(row, col, 'b');
				 if (valid == 0) {
					 // prints computers move
					 System.out.println("Computer's Turn! (White)");
					 Move othelloMove = PlayerImpl.BestMove(game);
					 game.play(othelloMove.getX(), othelloMove.getY(), 'w');
					 board.copyBoard(game.printBoard());
					 layouts.getChildren().removeAll();
					 int []Scores = game.Scores();
					 initBoard(Scores[1], Scores[0]);

					 // Endgame
					 if (OthelloGameSituation.gameIsOver(game.gameBoard.board)) {
						 OthelloGameSituation.winner(game.gameBoard.board);
						 Alert alert = new Alert(Alert.AlertType.INFORMATION);
						 alert.setTitle("Game Over");
						 alert.setHeaderText(null);
						 alert.setContentText(OthelloGameSituation.winner(game.gameBoard.board));
						 alert.showAndWait();
						 stage.close();
					 }
				 }
				 else {
					 Alert alert = new Alert(Alert.AlertType.WARNING);
					 alert.setTitle("Incorrect Move!");
					 alert.setHeaderText(null);
					 alert.setContentText("Try Again.");
					 alert.showAndWait();
				 }
			 }
		 };
		 return btnHandler;
	 }
	
    public void start(Stage pStage) throws Exception {
		 // Start Game Dialog
		stage = pStage;
		player = new PlayerImpl();
		player.init(0, 0, new Random(0));
		board = new Board();
		game = new Othello();
		layouts = new StackPane();
		pStage.setTitle("Othello");
		int wScore=2,bScore=2;
		initBoard(wScore, bScore);
        Scene scenes =new Scene(layouts, 300,300);
        pStage.setScene(scenes);
        pStage.setResizable(false);
        pStage.show();
    }

	public static void main(String[] args)  {
		launch(args);
    }

	public void initBoard(int wScore, int bScore) {
		Button whiteScore = new Button("White Score = " + wScore);
		Button blackScore = new Button("Black Score = " + bScore);
		layouts.getChildren().add(whiteScore);
		layouts.getChildren().add(blackScore);
		whiteScore.setTranslateX(-70);
		whiteScore.setTranslateY(-130);
		blackScore.setTranslateX(80);
		blackScore.setTranslateY(-130);
		whiteScore.setStyle("-fx-background-color: #ffffff");
		blackScore.setStyle("-fx-background-color: #ffffff");
		for (int i = 0; i < board.board.length; i++) {
			for (int k = 0; k < board.board.length; k++) {
				buttons[i][k] = new Button();
				if (board.board[i][k] == 'w') {
					buttons[i][k].setStyle("-fx-background-color: #ffffff");
				} else if (board.board[i][k] == 'b') {
					buttons[i][k].setStyle("-fx-background-color: #000000");
				} else {
					buttons[i][k].setStyle("-fx-background-color: #00ff00");
				}
				buttons[i][k].setTranslateX(i * 30 - 100);
				buttons[i][k].setTranslateY(k * 30 - 100);
				buttons[i][k].setMinSize(28, 28);
				buttons[i][k].setId(String.valueOf((i + 8 * k)));
				buttons[i][k].addEventHandler(MouseEvent.MOUSE_CLICKED, createButtonHandler());
				layouts.getChildren().add(buttons[i][k]);
			}
		}
	}
}