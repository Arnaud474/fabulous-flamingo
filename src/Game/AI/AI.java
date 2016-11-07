package Game.AI;

import Game.Board;
import Game.Piece;

import java.awt.*;
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
    private ArrayList<Move> findAllPossibleMoves(int color, Board board){

        System.out.println("All Moves");

        ArrayList<Move> moves = new ArrayList<Move>();

        Piece[][] b = board.getBoard();

        for(int i = 0; i < b.length; i++){

            for(int j = 0; j < board.getBoard()[0].length; j++){

                //Check if spot contains Piece of the good color
                if(b[i][j] != null && b[i][j].getColor() == color)
                {
                    //To contain Piece of another color in the same direction
                    Point obstacle = null;
                    int count = 0;

                    //Check number of pieces in the same ROW/COLUMN (Check if blocked by another color)

                    //Horizontal check on all spaces
                    for(int x = 0; x < b[i].length; x++){

                        //If spot inside the i ROW contains a Piece
                        if(b[i][x] != null) {
                            count++;

                            //If the Piece is of not our color, it is an obstacle
                            if(b[i][x].getColor() != color && obstacle == null){
                                obstacle = new Point(x, i);
                            }
                        }
                    }

                    //Movement validation

                    //FORWARD MOVE (RIGHT)
                    if((j+count) < b[i].length){
                        //Check for obstacles
                        if(obstacle != null && (j+1) <= obstacle.getX()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j+count, i)));
                        }
                    }



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
        ArrayList<Move> moves = INSTANCE.findAllPossibleMoves(color, board);

        return "";
    }
}
