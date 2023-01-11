package game;

import javafx.scene.control.Alert;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Random;

public class PlayerImpl implements Player {

    public static int EvaluationFunction(char[][] board) {
        int heuristic = 0;
        int[] score = OthelloGameSituation.gameScore(board);
        heuristic = 10 * (score[1] - score[0]);      // computer 's score - user' s score

        if (board[0][0] == 'w')     //corner bonus
            heuristic += 10;
        if (board[7][0] == 'w')
            heuristic += 10;
        if (board[0][7] == 'w')
            heuristic += 10;
        if (board[7][7] == 'w')
            heuristic += 10;

        for (int i = 0; i < 8; i++) {
            if (board[i][0] == 'w')  // edges bonus
                heuristic += 5;
            if (board[0][i] == 'w')
                heuristic += 5;
            if (board[i][7] == 'w')
                heuristic += 5;
            if (board[7][i] == 'w')
                heuristic += 5;
        }
        return heuristic;
    }

    public static int MinMax(Othello game, int depth, boolean maximize, int alpha, int beta) {
        if (depth == 0)
            return PlayerImpl.EvaluationFunction(game.gameBoard.board);

        int best_value;
        if (maximize) {
            best_value = -9999;
            ArrayList<Move> moves = game.gameSituation.validMovesCurrentPlayer('w', game.gameBoard.board);
            for (Move move: moves) {
                Othello temp = new Othello(game);    // creating new temp board to apply temp moves
                temp.play(move.getX(), move.getY(), 'w');
                int value = MinMax(temp, depth - 1, false, alpha, beta);
                best_value = max(best_value, value);
                alpha = max(alpha, best_value);
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            best_value = 9999;
            ArrayList<Move> moves = game.gameSituation.validMovesCurrentPlayer('b', game.gameBoard.board);
            for (Move move: moves) {
                Othello temp = new Othello(game);    // creating new temp board to apply temp moves
                temp.play(move.getX(), move.getY(), 'b');
                int value = MinMax(temp, depth - 1, true, alpha, beta);
                best_value = min(best_value, value);
                beta = min(beta, best_value);
                if (beta <= alpha)
                    break;
            }
        }
        return best_value;
    }

    public static Move BestMove(Othello othello) {
        ArrayList<Move> moves = othello.gameSituation.validMovesCurrentPlayer('w', othello.gameBoard.board);
        int row = -1, column = -1;
        int heuristic = -9999;
        for (Move move: moves) {
            Othello temp = new Othello(othello);
            temp.play(move.getX(), move.getY(), 'w');
            int h = MinMax(temp, 1, false, -9999, 9999);
            if (h > heuristic) {
                heuristic = h;
                row = move.getX();
                column = move.getY();
            }
        }
        return new Move(row, column);
    }

    @Override
    public void init(int order, long t, Random rnd) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome to Othello");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to Othello! You play as Black and you should capture more places to win the game. Good Luck!");
        alert.showAndWait();
    }

    @Override
    public Move nextMove(Move prevMove, long tOpponent, long t) {
        return null;
    }
}
