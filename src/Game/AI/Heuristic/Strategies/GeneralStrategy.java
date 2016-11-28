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

                //If piece is the good color
                if(arr[i][j].getColor() == color) {

                    //1 TOP LEFT

                    // Horzontal Case
                    if(i == 1 && j > 0 && arr[0].length > j){

                        //Check if not empty and not the same color
                        if(arr[i-1][j-1].getColor() != 0 && arr[i-1][j-1].getColor() != color){
                            value+=1;
                        }
                    }
                    //Vertical Case
                    else if(j == 1 && i > 0 && arr.length > i){
                        //Check if not empty and not the same color
                        if(arr[i-1][j-1].getColor() != 0 && arr[i-1][j-1].getColor() != color){
                            value+=1;
                        }
                    }

                    //2 TOP

                    if(i == 1){
                        //Check if not empty and not the same color
                        if(arr[i-1][j].getColor() != 0 && arr[i-1][j].getColor() != color){
                            value+=1;
                        }
                    }


                    //3 TOP RIGHT

                    // Horzontal Case
                    if(i == 1 && j >= 0 && arr[0].length - 1 > j){

                        //Check if not empty and not the same color
                        if(arr[i-1][j+1].getColor() != 0 && arr[i-1][j+1].getColor() != color){
                            value+=1;
                        }
                    }
                    //Vertical Case
                    else if(j == arr[0].length - 2 && i > 0 && arr.length > i){
                        //Check if not empty and not the same color
                        if(arr[i-1][j+1].getColor() != 0 && arr[i-1][j+1].getColor() != color){
                            value+=1;
                        }
                    }

                    //4 LEFT

                    if(j == 1){
                        //Check if not empty and not the same color
                        if(arr[i][j-1].getColor() != 0 && arr[i][j-1].getColor() != color){
                            value+=1;
                        }
                    }

                    //6 RIGHT

                    if(j == arr[0].length - 2){
                        //Check if not empty and not the same color
                        if(arr[i][arr[0].length-1].getColor() != 0 && arr[i][arr[0].length-1].getColor() != color){
                            value+=1;
                        }
                    }

                    //7 BOTTOM LEFT

                    // Horzontal Case
                    if(i == arr.length - 2 && j > 0 && arr[0].length > j){

                        //Check if not empty and not the same color
                        if(arr[arr.length - 1][j-1].getColor() != 0 && arr[arr.length - 1][j-1].getColor() != color){
                            value+=1;
                        }
                    }
                    //Vertical Case
                    else if(j == arr[0].length - 2 && i > 0 && arr.length > i){
                        //Check if not empty and not the same color
                        if(arr[i-1][arr[0].length-1].getColor() != 0 && arr[i-1][arr[0].length-1].getColor() != color){
                            value+=1;
                        }
                    }

                    //8 BOTTOM

                    if(i == (arr.length - 2)){
                        //Check if not empty and not the same color
                        if(arr[arr.length - 1][j].getColor() != 0 && arr[arr.length - 1][j].getColor() != color){
                            value+=1;
                        }
                    }

                    //9 BOTTOM RIGHT

                    // Horzontal Case
                    if(i == arr.length-2 && j >= 0 && arr[0].length - 1 > j){

                        //Check if not empty and not the same color
                        if(arr[arr.length-1][j+1].getColor() != 0 && arr[arr.length-1][j+1].getColor() != color){
                            value+=1;
                        }
                    }
                    //Vertical Case
                    else if(j == arr[0].length - 2 && i > 0 && arr.length-1 > i){
                        //Check if not empty and not the same color
                        if(arr[i+1][j+1].getColor() != 0 && arr[i-1][j+1].getColor() != color){
                            value+=1;
                        }
                    }


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
