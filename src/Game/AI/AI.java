package Game.AI;

import Game.Board;
import Game.Piece;

import java.util.ArrayList;

public class AI {

    private static AI INSTANCE;

    private AI(){
    }

    public static AI getInstance(){
        if(INSTANCE == null)
        {
            INSTANCE = new AI();
        }

        return INSTANCE;
    }

    /**
     *
     * Find all possible moves on this board
     *
     * @param board
     * @return
     */
    private static ArrayList<Move> findAllPossibleMoves(int color, Board board){

        System.out.println("All Moves");

        ArrayList<Move> moves = new ArrayList<Move>();

        Piece[][] b = board.getBoard();

        for(int i = 0; i < b.length; i++){

            for(int j = 0; j < board.getBoard()[0].length; j++){
                //Check if spot contains Piece of the good color
                if(b[i][j] != null && b[i][j].getColor() == color)
                {
                    //Check number of pieces in the same ROW/COLUMN (Check if blocked by another color)

                    //Check diagonals (Check if blocked by another color)

                    //Also verfiy destination does not contain a Piece of the same color

                }
            }


        }

        return moves;
    }

    /**
     *
     * Finds the best move for a certain color of piece and board layout
     *
     * @param color Color of the client
     * @param board The current board layout
     * @return
     */
    public static String findBestMove(int color, Board board){

        //Find all moves
        ArrayList<Move> moves = findAllPossibleMoves(color, board);

        return "";
    }
}
