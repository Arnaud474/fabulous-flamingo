package Game;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Arnaud on 10/26/2016.
 */
public class Board {
	//private int[][] board;
	private HashMap<Character, Integer> conversion;
    private Piece[][] board;

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

	public void setBoard(String[] state) {

		//Turn the array of String into a bidimensional array of integers
		/*for(int i = 0; i <  state.length; i++){
			//Super efficient one-liner of destiny
			board[i/board.length][i % board[0].length] = Integer.parseInt(state[i]);
		}*/

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
		String[] moves = move.split(" - ");

		//Removing white spaces
		for (int i = 0; i < moves.length; i++)
			moves[i] = moves[i].trim();

		//Removing the piece from first position
		Piece piece = new Piece(board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))]);
		board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))] = null;

		//Putting the piece in the new position
		board[conversion.get(moves[1].charAt(1))][conversion.get(moves[1].charAt(0))] = piece;

        //Prints the move string
        System.out.println("Player " + (piece.getColor() == 4 ? "White" : "Black") + " moved from " + moves[0] + " to " + moves[1]);
        System.out.println("---------------------------------");

		printBoard();
	}

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
}
