package Game;

import Game.AI.Heuristic.Heuristic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * Created by Arnaud on 10/26/2016.
 */
public class Board {

	/** The Constant BOARD_SIZE. */
	public static final int BOARD_SIZE = 8;
	
	/** The Constant COLOR_WHITE. */
	public static final int COLOR_WHITE = 4;
	
	/** The Constant COLOR_BLACK. */
	public static final int COLOR_BLACK = 2;
	
	/** The conversion. */
	// private int[][] board;
	private HashMap<Character, Integer> conversion;
	
	/** The board. */
	private Piece[][] board;

	/** The quad. */
	private int quad[][][] = new int[5][BOARD_SIZE + 1][BOARD_SIZE + 1];
	
	/** The quadcount. */
	private int quadcount[][] = new int[5][6];

	/** The black pieces. */
	public ArrayList<Piece> blackPieces = new ArrayList<Piece>();
	
	/** The white pieces. */
	public ArrayList<Piece> whitePieces = new ArrayList<Piece>();

	/**
	 * Instantiates a new board with a piece array (used in minimax).
	 *
	 * @param p the p
	 */
	public Board(Piece[][] p) {
		super();
		this.board = deepCopy(p);
		// Initializing the conversion hashmap for x coordinates
		conversion = new HashMap<Character, Integer>();

		// Horizontal (X)
		conversion.put('A', 0);
		conversion.put('B', 1);
		conversion.put('C', 2);
		conversion.put('D', 3);
		conversion.put('E', 4);
		conversion.put('F', 5);
		conversion.put('G', 6);
		conversion.put('H', 7);

		// Vertical (Y)
		conversion.put('1', 7);
		conversion.put('2', 6);
		conversion.put('3', 5);
		conversion.put('4', 4);
		conversion.put('5', 3);
		conversion.put('6', 2);
		conversion.put('7', 1);
		conversion.put('8', 0);
	}

	/**
	 * Instantiates a new board.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Board(int x, int y) {
		super();
		this.board = new Piece[x][y];

		// Initializing the conversion hashmap for x coordinates
		conversion = new HashMap<Character, Integer>();

		// Horizontal (X)
		conversion.put('A', 0);
		conversion.put('B', 1);
		conversion.put('C', 2);
		conversion.put('D', 3);
		conversion.put('E', 4);
		conversion.put('F', 5);
		conversion.put('G', 6);
		conversion.put('H', 7);

		// Vertical (Y)
		conversion.put('1', 7);
		conversion.put('2', 6);
		conversion.put('3', 5);
		conversion.put('4', 4);
		conversion.put('5', 3);
		conversion.put('6', 2);
		conversion.put('7', 1);
		conversion.put('8', 0);

	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Piece[][] getBoard() {
		return board;
	}

	/**
	 * Gets the quad.
	 *
	 * @return the quad
	 */
	public int[][][] getQuad() {
		return quad;
	}

	/**
	 * Gets the quadcount.
	 *
	 * @return the quadcount
	 */
	public int[][] getQuadcount() {
		return quadcount;
	}

	/**
	 * Sets the board.
	 *
	 * @param state the new board
	 */
	public void setBoard(String[] state) {

		for (int i = 0; i < state.length; i++) {
			// Super efficient one-liner of destiny
			board[i / board.length][i % board[0].length] = new Piece(Integer.parseInt(state[i]));
		}
	}

	/**
	 * Update the board when a movement is done.
	 *
	 * @param move the move
	 */
	public void updateBoard(String move) {

		// Make sure it works for a move without -
		String[] moves = move.split(" - ");

		// Removing white spaces
		for (int i = 0; i < moves.length; i++)
			moves[i] = moves[i].trim();
		// Removing the piece from first position
		Piece empty = new Piece(0);

		Piece piece = new Piece(board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))]);
		board[conversion.get(moves[0].charAt(1))][conversion.get(moves[0].charAt(0))] = empty;

		// Putting the piece in the new position
		board[conversion.get(moves[1].charAt(1))][conversion.get(moves[1].charAt(0))] = piece;
	}

	/**
	 * Deep copy a board into a new board.
	 *
	 * @param input the input
	 * @return the piece[][]
	 */
	public static Piece[][] deepCopy(Piece[][] input) {
		if (input == null)
			return null;
		Piece[][] result = new Piece[input.length][input[0].length];
		for (int r = 0; r < input.length; r++) {
			result[r] = input[r].clone();
		}
		return result;
	}
	
	
	/**
	 * Evaluate board according game heuristics.
	 *
	 * @param h the h
	 * @param currentColor the current color
	 * @return the int
	 */
	public int evaluateBoard(Heuristic h, int currentColor) {
		return h.calculate(this, currentColor);
	}

	/**
	 * Game over.
	 *
	 * @param player
	 *            the player
	 * @return true, if successful
	 */
	public boolean gameOver(int player) {
		setQuads();
		int e;

		e = (quadcount[player][1] - quadcount[player][3] - 2 * quadcount[player][5]) / 4;

		if (e > 1)
			return false;

		return true;
	}

	/**
	 * Prints the board.
	 */
	public void printBoard() {
		// Check every position on the board
		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				// Check if spot contains a Piece Object
				if (board[i][j] != null)
					System.out.print(board[i][j].getColor());
				else
					System.out.print(0);
			}

			System.out.print('\n');
		}
	}

	/**
	 * Returns the count of pieces on the board.
	 *
	 * @return An array of two integers [0] = count of white pieces, [1] = count
	 *         of black pieces
	 */
	public int[] countPieces() {

		int[] count = new int[2];

		Arrays.fill(count, 0);

		int color = 0;

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {

				color = board[i][j].getColor();

				// If 4 increment pos 0 in array else increment pos 1
				if (color == 4)
					count[0]++;
				else if (color == 2)
					count[1]++;
			}

		}

		return count;
	}

	/**
	 * Sets the quads.
	 */
	private void setQuads() {

		for (int i = 0; i < 6; i++)
			quadcount[2][i] = quadcount[4][i] = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				quad[2][i][j] = countQuads(i, j, 2);

				quad[4][i][j] = countQuads(i, j, 4);
				quadcount[2][quad[2][i][j]]++;
				quadcount[4][quad[4][i][j]]++;
			}
		}
	}

	/**
	 * Count the different quads for piece at [x,y].
	 *
	 * @param x            the x
	 * @param y            the y
	 * @param color            the color
	 * @return the int
	 */
	private int countQuads(int x, int y, int color) {
		int counter = 0;
		if (isInQuad(color, x - 1, y - 1))
			counter++;
		if (isInQuad(color, x, y - 1))
			counter++;
		if (isInQuad(color, x - 1, y))
			counter++;
		if (isInQuad(color, x, y))
			counter++;
		if (counter == 2 && ((isInQuad(color, x, y) && isInQuad(color, x - 1, y - 1))
				|| (isInQuad(color, x - 1, y) && isInQuad(color, x, y - 1))))
			return 5;
		return counter;
	}

	/**
	 * Checks if is in quad.
	 *
	 * @param player
	 *            the player
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return true, if is in quad
	 */
	public final boolean isInQuad(int player, int x, int y) {
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
			return false;
		if (board[x][y].getColor() != player)
			return false;
		return true;
	}

	/**
	 * Gets the pieces list.
	 *
	 * @param color the color
	 * @return the pieces list
	 */
	public ArrayList<Piece> getPiecesList(int color) {
		if (color == COLOR_BLACK) {
			return blackPieces;
		} else {
			return whitePieces;
		}
	}

}
