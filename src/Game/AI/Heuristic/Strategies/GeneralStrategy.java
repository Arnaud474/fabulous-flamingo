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
    private int nbPiecePlayer;
    

    @Override
    public int calculateValues(Board board, int currentColor) {

        this.value = 0;
        this.board = board;
        this.color = currentColor;

        if(currentColor == 4)
        	this.nbPiecePlayer = board.countPieces()[0];
        
        else
        	this.nbPiecePlayer = board.countPieces()[1];

        //Check pieces relative to center of mass
        differenceWithCenterOfMass();

        //Check Score with quads
        checkNumberOfQuads();

        //If game over don't check anything else
        if(board.gameOver(currentColor)){
            value = Integer.MAX_VALUE;
            return value;
        }

        //System.out.println(value);

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

    public void checkNumberOfQuads(){

        //Getting the quads of the current color
        int[][] quads = board.getQuad()[color];

        //Check all quads
        for(int i = 0; i < quads.length;i++){

            for(int j = 0; j < quads[0].length; j++){

                //Check if quad has a diagonal
                if(quads[i][j] == 5)
                {
                    //If it contains diagonal
                    value+=10;

                    //If its linked to another quad

                    if(quads[i-1][j-1] == 5 && i > 0 && j > 0) //TOP LEFT
                        value+=10;
                    if(quads[i-1][j] == 5 && i > 0) //TOP
                        value+=5;
                    if(quads[i-1][j] == 5 && i < quads[0].length-1 && j < quads.length-1) //TOP RIGHT
                        value+=10;
                    if(quads[i+1][j-1] == 5 && j > 0 && i < quads.length) //BOTTOM LEFT
                        value+=10;
                    if(quads[i+1][j] == 5 && i < quads.length -1) //BOTTOM
                        value+=5;
                    if(quads[i+1][j+1] == 5 && j < quads[0].length && i < quads.length) //BOTTOM RIGHT
                        value+=10;
                    if(quads[i][j-1] == 5 && j > 0) //LEFT
                        value+=5;
                    if(quads[i][j+1] == 5 && j < quads[0].length -1) //RIGHT
                        value+=5;
                }

            }
        }

    }

    public void differenceWithCenterOfMass(){

        //Array of pieces
        Piece[][] arr = board.getBoard();

        //Get the center of mass of the current board
        int[] centerOfMass = getCenterOfMass(arr, color);

        //Check difference between pieces and center of  mass

        int numberOfPieces = 0;
        int diffX = 0;
        int diffY = 0;

        //Vertical
        for(int i = 0; i < arr.length; i++){

            //Horizontal
            for(int j = 0; j < arr[0].length; j++){

                //The pieces is the good color
                if(arr[i][j].getColor() == color){
                    numberOfPieces++;
                    diffX += Math.abs(j - centerOfMass[0]);
                    diffY += Math.abs(i - centerOfMass[1]);
                }
            }
        }

        //We calculate the average difference to the middle point
        diffX = diffX/numberOfPieces;
        diffY = diffY/numberOfPieces;

        //WE TAKE THE Least Common Multiplier OF 1 to 8, which is 840
        int lcm = 840;

        //So a board with pieces with a smaller difference from the center of mass will score better
        value+= lcm/(diffX+ 1);
        value+= lcm/(diffY + 1);

    }
    
    public int[] getCenterOfMass(Piece[][] board, int color){
    	int totalX = 0;
    	int totalY = 0;
    	for(int i = 0; i < board[0].length; i++ ){
    		for (int j = 0; j < board[0].length; j++) {
				if(board[i][j].isPiece() && board[i][j].getColor() == color){
					totalX += i;
					totalY += j;
				}
			}
    	}
    	
    	int[] center = new int[2];
    	center[0] = totalX/nbPiecePlayer;
    	center[1] = totalY/nbPiecePlayer;

        System.out.println(Arrays.toString(center));
    	return center;
    }


}
