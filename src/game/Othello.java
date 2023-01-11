package game;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The Othello class is the main class of the program. It contains the main method, which is the entry point of the
 * program. It also contains the board, player, currentPlayer, and oppositePlayer variables.
 */
public class Othello {

    public char player;
    public char oppositePlayer;
    OthelloGameSituation gameSituation;
    public Board gameBoard;

    public Othello() {
        gameSituation = new OthelloGameSituation();
        gameBoard = new Board();
        player = 'b';
        oppositePlayer = 'w';
    }
//
//    public Othello(GridPane pane) throws InterruptedException {
//        gameSituation = new OthelloGameSituation();
//        gameBoard = new Board(pane);
//        player = 'b';
//        oppositePlayer = 'w';
//    }

    public Othello(Othello othello) {
        player = othello.player;
        oppositePlayer = othello.oppositePlayer;
        gameSituation = new OthelloGameSituation();
        gameBoard = new Board(othello.gameBoard);
    }

    /**
     * This function prints current board
     * @return
     */
    public char[][] printBoard() {
        gameBoard.printBoard();
        return gameBoard.board;
    }

    public int[] Scores() {
        return gameSituation.gameScore(gameBoard.board);
    }

    /**
     * This function switches the player from black to white and vice versa
     */
    public void switchPlayer() {
        if (player == 'b') {
            player = 'w';
            oppositePlayer = 'b';
        } else {
            player = 'b';
            oppositePlayer = 'w';
        }
    }

    /**
     * The function takes in the coordinates of the move and the player who is making the move. It then checks if the
     * move is valid by comparing the coordinates of the move with the coordinates of the valid moves. If the move is
     * valid, the board is updated and the game score is calculated. If the move is invalid, the player is notified
     *
     * @param x The x coordinate of the move.
     * @param y The y coordinate of the move.
     * @param player The player who is playing the move.
     */
    public int play(int x, int y, char player) {
        ArrayList<Move> playableMoves = gameSituation.validMovesCurrentPlayer(player, gameBoard.board);

        // Checking if the move is valid.
        boolean valid = playableMoves.contains(new Move(x,y));
        if (valid) {
            gameBoard.board[x][y] = player;

            // flip the right tiles
            ArrayList<Move> flippingMoves = flippingPegs(x, y, player);
            for (Move flippingMove : flippingMoves) {
                int tempX = flippingMove.getX();
                int tempY = flippingMove.getY();

                if (gameBoard.board[tempX][tempY] == 'w') {
                    gameBoard.board[tempX][tempY] = 'b';
                    continue;
                }
                if (gameBoard.board[tempX][tempY] == 'b') {
                    gameBoard.board[tempX][tempY] = 'w';
                }
            }
            switchPlayer();
            return 0;
        }else {
            System.out.println("Invalid move");
            return -1;
        }
    }

    /**
     * It checks all the 8 directions from the given position and returns a list of all the positions that will be
     * flipped if a move is made at the given position
     *
     * @param x the x coordinate of the move
     * @param y the y coordinate of the move
     * @param player The player who is making the move.
     * @return The method is returning an ArrayList of Move objects.
     */
    public ArrayList<Move> flippingPegs(int x, int y, char player) {
        if (player == 'w') {
            player = 'w';
            oppositePlayer = 'b';
        } else if (player == 'b') {
            player = 'b';
            oppositePlayer = 'w';
        }

        ArrayList<Move> flippingMoves = new ArrayList<>();

        //going down
        for (int counter = 1; x + counter < gameBoard.board.length ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x + counter][y]) == oppositePlayer) {
                Move tempMove = new Move(x + counter, y);
                tempList.add(tempMove);
                for (int temp = counter + 1; x + temp < gameBoard.board.length ; temp++) {
                    if ((gameBoard.board[x + temp][y]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going up
        for (int counter = 1; x-counter >= 0 ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x - counter][y]) == oppositePlayer) {
                Move tempMove = new Move(x - counter, y);
                tempList.add(tempMove);
                for (int temp = counter + 1; x - temp >= 0 ; temp++) {
                    if ((gameBoard.board[x - temp][y]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going left
        for (int counter = 1; y-counter >= 0 ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x][y - counter]) == oppositePlayer) {
                Move tempMove = new Move(x, y - counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; y - temp >= 0 ; temp++) {
                    if ((gameBoard.board[x][y - temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going right
        for (int counter = 1; y + counter < gameBoard.board.length ; counter++){
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x][y + counter]) == oppositePlayer) {
                Move tempMove = new Move(x, y + counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; y + temp < gameBoard.board.length ; temp++) {
                    if ((gameBoard.board[x][y + temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going right under diagonal
        for (int counter = 1; (x + counter < gameBoard.board.length) && (y + counter < gameBoard.board.length) ; counter++){
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x + counter][y + counter]) == oppositePlayer) {
                Move tempMove = new Move(x + counter, y + counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; x + temp < gameBoard.board.length
                        && y + temp < gameBoard.board.length; temp++) {
                    if ((gameBoard.board[x + temp][y + temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going right upper diagonal
        for (int counter = 1; (x - counter >= 0)&&(y + counter < gameBoard.board.length) ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x - counter][y + counter]) == oppositePlayer) {
                Move tempMove = new Move(x - counter, y + counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; x - temp >= 0
                        && y + temp < gameBoard.board.length; temp++) {
                    if ((gameBoard.board[x - temp][y + temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        //going left upper diagonal
        for (int counter = 1; (x - counter >= 0) && (y - counter >= 0) ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x - counter][y - counter]) == oppositePlayer) {
                Move tempMove = new Move(x - counter, y - counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; x - temp >= 0
                        && y - temp >= 0; temp++) {
                    if ((gameBoard.board[x - temp][y - temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }

        // going left under diagonal
        for (int counter = 1; (x + counter < gameBoard.board.length)&&(y - counter >=0) ; counter++) {
            LinkedList<Move> tempList = new LinkedList<>();
            if ((gameBoard.board[x + counter][y - counter]) == oppositePlayer) {
                Move tempMove = new Move(x + counter, y - counter);
                tempList.add(tempMove);
                for (int temp = counter + 1; x + temp < gameBoard.board.length
                        && y - temp >= 0; temp++) {
                    if ((gameBoard.board[x + temp][y - temp]) == player) {
                        flippingMoves.addAll(tempList);
                        break;
                    }
                }
                continue;
            }
            break;
        }
        return flippingMoves;
    }
}

