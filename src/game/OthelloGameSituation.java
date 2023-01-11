// An OthelloGameState represents the state of an Othello game at a given
// time.

package game;
import java.util.ArrayList;
public class OthelloGameSituation {
    /**
     * This function checks if the board is empty at a given position
     *
     * @param x the x coordinate of the board
     * @param y The y coordinate of the board
     * @return A boolean value.
     */


    /**
     * It counts the number of black and white pieces on the board and returns an array containing the number of black
     * pieces and the number of white pieces
     *
     * @param board The current state of the board.
     * @return The game score at the moment is being returned.
     */
    public static int[] gameScore(char board[][]){
        int blackScore = 0;
        int whiteScore = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == 'w')
                    whiteScore++;
                else if(board[i][j] == 'b')
                    blackScore++;
            }
        }
        return new int[] { blackScore, whiteScore};
    }


    /**
     * This function takes in a board and determines the winner of the game
     *
     * @param board the current state of the board
     */
    public static String winner(char board[][]) {
        int[] scores = OthelloGameSituation.gameScore(board);
        // Black wins
        if(scores[0] > scores[1]){
            System.out.println("Game over. Black wins!");
            return ("Game over. You win!");
        }

        // Tie game
        else if (scores[0] == scores[1]){
            System.out.println("Game Over. Draw!");
            return ("Game Over. Draw!");
        }

        //White wins
        else{
            System.out.println("Game over. White wins!");
            return ("Game over. Computer White wins!");
        }
    }

    /**
     * It checks if the player can make a move in a certain position, and if so, it adds the move to the list of valid
     * moves
     *
     * @param x the x coordinate of the move
     * @param y the y coordinate of the move
     * @param player the player who is making the move
     * @return An ArrayList of Move objects.
     */
    public ArrayList<Move> validMoves(int x, int y, char player, char[][] board) {

        int counter;
        ArrayList<Move> moves = new ArrayList<>();
        char oppositePlayer;

        if (player == 'w') {
            oppositePlayer = 'b';
        } else {
            player = 'b';
            oppositePlayer = 'w';
        }
        //going down
        for (counter = 1; x+counter < board.length ; counter++) {
            if ((board[x + counter][y]) == oppositePlayer) {
                for (int temp = counter + 1; x + temp < board.length ; temp++) {
                    if ((board[x + temp][y]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }
        //going up
        for (counter = 1; x-counter >= 0 ; counter++){
            if ((board[x - counter][y]) == oppositePlayer) {
                for (int temp = counter + 1; x - temp >= 0 ; temp++) {
                    if ((board[x - temp][y]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        //going left
        for (counter = 1; y-counter >= 0 ; counter++){
            if ((board[x][y - counter]) == oppositePlayer) {
                for (int temp = counter + 1; y - temp >= 0 ; temp++) {
                    if ((board[x][y - temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        //going right
        for (counter = 1; y + counter < board.length ; counter++){
            if ((board[x][y + counter]) == oppositePlayer) {
                for (int temp = counter + 1; y + temp < board.length ; temp++) {
                    if ((board[x][y + temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        //going right under diagonal
        for (counter = 1; (x + counter < board.length) && (y + counter < board.length) ; counter++){
            if ((board[x + counter][y + counter]) == oppositePlayer) {
                for (int temp = counter + 1; x + temp < board.length && y + temp < board.length; temp++) {
                    if ((board[x + temp][y + temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        //going right upper diagonal
        for (counter = 1; (x - counter >= 0)&&(y + counter < board.length) ; counter++){
            if ((board[x - counter][y + counter]) == oppositePlayer) {
                for (int temp = counter + 1; x - temp >= 0 && y + temp < board.length; temp++) {
                    if ((board[x - temp][y + temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        //going left upper diagonal
        for (counter = 1; (x - counter >= 0) && (y - counter >= 0) ; counter++){
            if ((board[x - counter][y - counter]) == oppositePlayer) {
                for (int temp = counter + 1; x - temp >= 0 && y - temp >= 0; temp++) {
                    if ((board[x - temp][y - temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        // going left under diagonal
        for (counter = 1; (x + counter < board.length)&&(y - counter >=0) ; counter++) {
            if ((board[x + counter][y - counter]) == oppositePlayer) {
                for (int temp = counter + 1; x + temp < board.length && y - temp >= 0; temp++) {
                    if ((board[x + temp][y - temp]) == player) {
                        if (board[x][y] == '_') {
                            Move possibleMove = new Move(x, y);
                            moves.add(possibleMove);
                        }
                    }
                }
                continue;
            }
            break;
        }

        return moves;
    }

    /**
     * It returns all the valid moves for the current player.
     *
     * @param player The player whose turn it is.
     * @return the valid moves of the current player.
     */
    public ArrayList<Move> validMovesCurrentPlayer(char player, char[][] board){
        ArrayList<Move> moves = new ArrayList<>();
        for(int x=0;x<board.length;x++){
            for(int y=0;y<board.length;y++){
                // maybe the type dont match when we ınject arraylıst to type move
                moves.addAll(validMoves(x, y, player, board));
            }
        }
        return moves;
    }

    /**
     * This function checks to see if the game is over by checking to see if there are any empty spaces on the board
     *
     * @return A boolean value.
     */
    public static boolean gameIsOver(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int k = 0; k < board.length; k++){
                if(board[i][k] == '_'){
                    return false;
                }
            }
        }
        return true;
    }
}


