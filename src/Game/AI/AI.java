package Game.AI;

import Game.Board;
import Game.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AI {

    private static AI INSTANCE;
    private static int TYPE_EAT = 2;
    private static int TYPE_MOVE = 1;
    
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
                        if(b[i][x] != null &&  ((Piece) b[i][x]).isPiece()) {
                            count++;

                            //If the Piece is of not our color, it is an obstacle
                            if(b[i][x].getColor() != color && obstacle == null){
                                obstacle = new Point(x, i);
                            }
                        }
                    }
                    Point obstacleY = null;
                    int countY = 0;
                    //Vertical check on all spaces
                    for(int y = 0; y < b[i].length; y++){

                        //If spot inside the i ROW contains a Piece
                        if(b[y][j] != null && ((Piece)b[y][j]).isPiece() ) {
                        	countY++;

                            //If the Piece is of not our color, it is an obstacle
                            if(b[y][j].getColor() != color && obstacleY == null){
                                obstacleY = new Point(j, y);
                            }
                        }
                    }
                    
                   
                  //Diagonal 1 check on all spaces
                    Point obstacleD1 = null;
                    int countD1 = 0;
                    int yD = j;
                    for(int x = i; x >= 0 && yD >= 0; x--){
                    		if(b[x][yD] != null && ((Piece)b[x][yD]).isPiece() ) {
                            	countD1++;

                                //If the Piece is of not our color, it is an obstacle
                                if(b[x][yD].getColor() != color && obstacleD1 == null){
                                    obstacleD1 = new Point(yD, x);
                                }
                            }
                    	yD--;
                    }
                    
                  //Diagonal 3 check on all spaces
                    Point obstacleD3 = null;
                    int countD3 = 0;
                    yD = j;
                    for(int x = i; x < b[i].length && yD >= 0; x++){
                    		if(b[x][yD] != null && ((Piece)b[x][yD]).isPiece() ) {
                            	countD3++;

                                //If the Piece is of not our color, it is an obstacle
                                if(b[x][yD].getColor() != color && obstacleD3 == null){
                                    obstacleD3 = new Point(yD, x);
                                }
                            }
                    	yD--;
                    }
                  //Diagonal 7 check on all spaces
                    Point obstacleD7 = null;
                    int countD7 = 0;
                    yD = j;
                    for(int x = i; x >= 0 && yD < b[i].length; x--){
                    		if(b[x][yD] != null && ((Piece)b[x][yD]).isPiece() ) {
                            	countD7++;

                                //If the Piece is of not our color, it is an obstacle
                                if(b[x][yD].getColor() != color && obstacleD7 == null){
                                    obstacleD7 = new Point(yD, x);
                                }
                            }
                    	yD++;
                    }
                  //Diagonal 9 check on all spaces
                    Point obstacleD9 = null;
                    int countD9 = 0;
                    yD = j;
                    for(int x = i; x < b[i].length && yD < b[i].length; x++){
                    		if(b[x][yD] != null && ((Piece)b[x][yD]).isPiece() ) {
                            	countD9++;

                                //If the Piece is of not our color, it is an obstacle
                                if(b[x][yD].getColor() != color && obstacleD9 == null){
                                    obstacleD9 = new Point(yD, x);
                                }
                            }
                    	yD++;
                    }
                    
                    
                    //Movement validation
                  
                    //FORWARD MOVE (RIGHT)
                    if((j+count) < b[i].length){
                        //Check for obstacles
                        if(obstacle != null && (j+count) < obstacle.getX()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j+count, i), TYPE_MOVE));
                        }
                        else if(obstacle != null && (j+count) == obstacle.getX()){
                        	moves.add(new Move(b[i][j], new Point(j, i), new Point(j+count, i), TYPE_EAT));
                        }
                        else if(obstacle == null){
                        	moves.add(new Move(b[i][j], new Point(j, i), new Point(j+count, i), TYPE_MOVE));
                        }
                        
                    }
                    
                  //BACK MOVE (LEFT)
                    if((j-count) >= 0){
                        //Check for obstacles
                        if(obstacle != null && (j-count) > obstacle.getX()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j-count, i), TYPE_MOVE));
                        }
                        else if(obstacle != null && (j-count) == obstacle.getX()){
                        	moves.add(new Move(b[i][j], new Point(j, i), new Point(j-count, i), TYPE_EAT));
                        }
                        else if(obstacle == null){
                        	moves.add(new Move(b[i][j], new Point(j, i), new Point(j-count, i), TYPE_MOVE));
                        }
                    }
                    
                    //DOWN MOVE
                    if((i+countY) < b[i].length){
                        //Check for obstacles
                    	if(obstacleY != null && (i+countY) < obstacleY.getY()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i+count), TYPE_MOVE));
                        }
                    	else if(obstacleY != null && (i+countY) == obstacleY.getY()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i+count), TYPE_EAT));
                        }
                    	else if(obstacleY == null){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i+count), TYPE_MOVE));
                        }
                    }
                    
                    //UP MOVE
                    if((i-countY) >= 0){
                        //Check for obstacles
                    	if(obstacleY != null && (i-countY) > obstacleY.getY()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i-count), TYPE_MOVE));
                        }
                    	else if(obstacleY != null && (i+countY) == obstacleY.getY()){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i-count), TYPE_EAT));
                        }
                    	else if(obstacleY == null){
                            moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i-count), TYPE_MOVE));
                        }
                    }
                    
                    //D1 MOVE
                    if((i-countD1) >= 0 && (j-countD1) >= 0){
                    	//Check for obstacles
                    	if(obstacleD1 != null && ((i-countD1)> obstacleD1.getY() && (j-countD1) > obstacleD1.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD1, i-countD1), TYPE_MOVE));
                    	}
                    	else if(obstacleD1 != null && ((i-countD1) == obstacleD1.getY() && (j-countD1) == obstacleD1.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD1, i-countD1), TYPE_EAT));
                    	}
                    	else if(obstacleD1 == null){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD1, i-countD1), TYPE_MOVE));	
                    	}
                    }
                  //D3 MOVE
                    if((i+countD3) < b[i].length && (j-countD3) >= 0){
                    	//Check for obstacles
                    	if(obstacleD3 != null && ((i+countD3)< obstacleD3.getY() && (j-countD3) > obstacleD3.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD3, i+countD3), TYPE_MOVE));
                    	}
                    	else if(obstacleD3 != null && ((i+countD3) == obstacleD3.getY() && (j-countD3) == obstacleD3.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD3, i+countD3), TYPE_EAT));
                    	}
                    	else if(obstacleD3 == null){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j-countD3, i+countD3), TYPE_MOVE));
                    	}
                    }
                  //D7 MOVE
                    if((i-countD7) >= 0 && (j+countD7) < b[i].length){
                    	//Check for obstacles
                    	if(obstacleD7 != null && ((i-countD7) > obstacleD7.getY() && (j+countD7) < obstacleD7.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD7, i-countD7), TYPE_MOVE));
                    	}
                    	else if(obstacleD7 != null && ((i-countD7) == obstacleD7.getY() && (j+countD7) == obstacleD7.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD7, i-countD7), TYPE_EAT));
                    	}
                    	else if(obstacleD7 == null){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD7, i-countD7), TYPE_MOVE));
                    	}
                    }
                    
                  //D9 MOVE
                    if((i+countD9) < b[i].length && (j+countD9) < b[i].length){
                    	//Check for obstacles
                    	if(obstacleD9 != null && ((i+countD9) < obstacleD9.getY() && (j+countD9) < obstacleD9.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD9, i+countD9), TYPE_MOVE));
                    	}
                    	else if(obstacleD9 != null && ((i+countD9) == obstacleD9.getY() && (j+countD9) == obstacleD9.getX())){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD9, i+countD9), TYPE_EAT));
                    	}
                    	else if(obstacleD9 == null){
                    		moves.add(new Move(b[i][j], new Point(j, i), new Point(j+countD9, i+countD9), TYPE_MOVE));
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
        attributeCosts(moves);
        return "";
    }
    public static void attributeCosts(ArrayList<Move> array){
    	Random r = new Random();
    	for(Move move:array){
    		move.setCost((r.nextInt(100+100)-100) * move.getType());
    	}
    }
}
