package Game.AI.Heuristic.Strategies;

import Game.AI.Move;
import Game.Board;
import Game.Piece;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Arnaud on 11/16/2016.
 */
public class GeneralStrategy extends Strategy{

    private int value;
    private Board board;
    private int color;

    @Override
    public int calculateValues(Board board, int currentColor) {

        this.value = 0;
        this.board = board;
        this.color = currentColor;

        //Check if our player has more pieces than his opponent when doing this move
        //hasNumberAdvantage();
        isBlockingPieces();

        System.out.println(value);

        return value;
    }

    /**
     * Checks if client has superiority in numbers
     *
     * @return
     */
    public void hasNumberAdvantage(){

        int[] count = board.countPieces();

        int opp = 0;
        int self = 0;

        //If white
        if(color == 4)
        {
            self = count[0];
            opp = count[1];
        }
        else if(color == 2)
        {
            self = count[1];
            opp = count[0];
        }

        //Opponent is already low on pieces, we don't want to eat anything
        if(opp <= 4)
            return;

        //Move puts us
        if((self - opp) < 0)
            this.value+=1;
    }

    /**
     * Check if the client is blocking any Pieces of the opposing side
     */
    public void isBlockingPieces(){

        Piece[][] arr = board.getBoard();



        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){

                //Check all directions around the piece

                //System.out.println(color);

                //If piece is the good color
                if(arr[i][j].getColor() == color) {

                    //System.out.println("I : " + i  + " J : " + j);

                    //1
                    if(i == 1 && j > 0 && arr[0].length-1 > j){

                        //Check if not empty and not the same color
                        if(arr[i-1][j-1].getColor() != 0 && arr[i-1][j-1].getColor() != color){
                            System.out.println("TOP LEFT");
                            value+=1;
                        }
                    }


                    //2

                    //3

                    //4

                    //6

                    //7

                    //8

                    //9


                }


            }
        }
    }

    /**
     * Checks if the client has pieces being blocked
     *
     */
    public void hasBlockedPieces(){

    }

    /**
     * Check if the client has pieces that can get killed
     */
    public void hasPiecesNotInDanger(){

    }

    /**
     * Check the number of connections (Quad)
     */
    public void checkNumberOfConnections(){

    }

    /**
     * Check if the move breaks an ennemy connection (Quad)
     */

}
