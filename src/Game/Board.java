package Game;

import Game.AI.Heuristic.Heuristic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Arnaud on 10/26/2016.
 */
public class Board{
	
	static final int BOARD_SIZE = 8;
	//private int[][] board;
	private HashMap<Character, Integer> conversion;
    private Piece[][] board;
   
    private int quad[][][] = new int[5][BOARD_SIZE+1][BOARD_SIZE+1];
    private int quadcount[][] = new int[5][6];
    
    
    public Board(Piece[][] p){
    	super();
    	this.board = deepCopy(p);
    	//Initializing the conversion hashmap for x coordinates
    			conversion = new HashMap<Character, Integer>();

    	        //Horizontal (X)
    			conversion.put('A', 0);
    			conversion.put('B', 1);
    			conversion.put('C', 2);
    			conversion.put('D', 3);
    			conversion.put('E', 4);
    			conversion.put('F', 5);
    			conversion.put('G', 6);
    			conversion.put('H', 7);

    	        //Vertical (Y)
    			conversion.put('1', 7);
    			conversion.put('2', 6);
    			conversion.put('3', 5);
    			conversion.put('4', 4);
    			conversion.put('5', 3);
    			conversion.put('6', 2);
    			conversion.put('7', 1);
    			conversion.put('8', 0);
    }
	public Board(int x, int y) {
		super();
		this.board = new Piece[x][y];

		//Initializing the conversion hashmap for x coordinates
		conversion = new HashMap<Character, Integer>();

        //Horizontal (X)
		conversion.put('A', 0);
		conversion.put('B', 1);
		conversion.put('C', 2);
		conversion.put('D', 3);
		conversion.put('E', 4);
		conversion.put('F', 5);
		conversion.put('G', 6);
		conversion.put('H', 7);

        //Vertical (Y)
		conversion.put('1', 7);
		conversion.put('2', 6);
		conversion.put('3', 5);
		conversion.put('4', 4);
		conversion.put('5', 3);
		conversion.put('6', 2);
		conversion.put('7', 1);
		conversion.put('8', 0);

	}

	public Piece[][] getBoard() {
		return board;
	}

	public int[][][] getQuad() {
		return quad;
	}

	public int[][] getQuadcount() {
		return quadcount;
	}

	public void setBoard(String[] state) {

		//Turn the array of String into a bidimensional array of integers
		/*for(int i = 0; i <  state.length; i++){
			//Super efficient one-liner of destiny
			board[i/board.length][i % board[0].length] = Integer.parseInt(state[i]);
		}*/
		int stuff = 0;
        for(int i = 0; i <  state.length; i++){
            //Super efficient one-liner of destiny
            board[i/board.length][i % board[0].length] = new Piece(Integer.parseInt(state[i]));
        }
	}

	/**
	 * Update the board when a movement is done
	 *
	 * @param move
	 */
	public void updateBoard(String move){
		
		//Make sure it works for a move without -
		String[] moves = move.split(" - ");

		//Removing white spaces
		for (int i = 0; i < moves.length; i++)
			moves[i] = moves[i].trim();
		//Removing the piece from first position
		Piece empty = new Piece(0);
		
		Piece piece = new Piece(board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))]);
		board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))] = empty;

		//Putting the piece in the new position
		board[conversion.get(moves[1].charAt(1))][conversion.get(moves[1].charAt(0))] = piece;

        //Prints the move string
      //  System.out.println("Player " + (piece.getColor() == 4 ? "White" : "Black") + " moved from " + moves[0] + " to " + moves[1]);
      //  System.out.println("---------------------------------");

	  //	printBoard();
	}
	
	public static Piece[][] deepCopy(Piece[][] input) {
	    if (input == null)
	        return null;
	    Piece[][] result = new Piece[input.length][input[0].length];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}
	public int evaluateBoard(Heuristic h, int currentColor){
		return h.calculate(this, currentColor);
	}
	public boolean gameOver(int player){
		recountQuads();
		int e;
		
		e = (quadcount[player][1]-quadcount[player][3]-2*quadcount[player][5])/4;
		
		if(e>1)
			return false;
		
		
			return true;
	}
	
	/**
	 * 
	 */
	public void printBoard(){ 
        //Check every position on the board
        for(int i = 0; i < board.length; i++){

            for(int j = 0; j < board[0].length; j++){
                //Check if spot contains a Piece Object
                if(board[i][j] != null)
                    System.out.print(board[i][j].getColor());
                else
                    System.out.print(0);
            }

            System.out.print('\n');
        }
    }

	/**
	 *
	 * Returns the count of pieces on the board
	 *
	 * @return An array of two integers [0] = count of white pieces, [1] = count of black pieces
	 */
	public int[] countPieces(){

		int[] count = new int[2];

		Arrays.fill(count, 0);

		int color = 0;

		for(int i = 0; i < board.length; i++){

			for(int j= 0; j < board[0].length; j++){

				color = board[i][j].getColor();

				//If 4 increment pos 0 in array else increment pos 1
				if(color == 4)
					count[0]++;
				else if(color == 2)
					count[1]++;
			}

		}

		return count;
	}
	
	private void recountQuads() {

	      for (int i = 0; i < 6; i++ )
	        quadcount[2][i] = quadcount[4][i] = 0;
	      for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	          quad[2][i][j] = quadValue(i, j, 2); // # of pieces in quad, 5 for diagonals per side
	          quad[4][i][j] = quadValue(i, j, 4);
	          quadcount[2][quad[2][i][j]]++; // quad counts per side
	          quadcount[4][quad[4][i][j]]++;
	        }
	      }
	    }
	/**
     * Helper function for recountQuads. For this quad, counts the number of
     * pieces in the quad of color side. If the count = 2 and they are a
     * diagonal return 5 otherwise return the count.
     *
     * @param x integer value for quad x
     * @param y integer value for quad y
     * @param side integer of player to calculate (PLAYER_WHITE, PLAYER_BLACK)
     * @return integer count of pieces of color side in quad or 5 if diagonal
     */
    private int quadValue(int x, int y, int side) {
        int counter = 0;
        if ( checker_of(side,x-1,y-1) )
            counter++;
        if ( checker_of(side,x,y-1) )
            counter++;
        if ( checker_of(side,x-1,y) )
            counter++;
        if ( checker_of(side,x,y) )
            counter++;
        if (counter == 2 && ((checker_of(side,x,y) && checker_of(side,x-1,y-1))
                             || (checker_of(side,x-1,y) && checker_of(side,x,y-1))))
            return 5;
        return counter;
    }
    
    /**
     * Outputs whether the checker in location [x,y] is owned by the player.
     *
     * @param player int The player to test [PLAYER_WHITE,PLAYER_BLACK]
     * @param x int The column value to test.
     * @param y int The row value to test.
     * @return final boolean True if player present
     */
    public final boolean checker_of( int player, int x, int y ) {
        if ( x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE )
          return false;
        if ( board[x][y].getColor() != player )
          return false;
        return true;
    }
    
    
}
