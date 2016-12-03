package Game.AI;



import Game.AI.Heuristic.Heuristic;
import Game.Board;
import Game.Piece;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The Class AI.
 */
public class AI {

	

	//DIRECTIONS TOOLTIP
	// 1 2 3
	// 4 X 6
	// 7 8 9
	// X=piece
	
	/** The instance. */
	private static AI INSTANCE;
	
	/** The type move. */
	private static int TYPE_MOVE = 1;
	
	/** The player color. */
	private static int PLAYER_COLOR = 0;
	
	/** The opponent color. */
	private static int OPPONENT_COLOR = 0;
	
	/** The selected move. */
	private static Move selectedMove = null;
	
	/** The heuristic. */
	private static Heuristic heuristic = new Heuristic();
	
	/** The depth max. */
	private static int DEPTH_MAX = 4;



	/**
	 * Instantiates a new ai.
	 */
	private AI() {
	}

	/**
	 * Gets the single instance of AI.
	 *
	 * @return single instance of AI
	 */
	public static AI getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AI();
		}

		return INSTANCE;
	}

	/**
	 * Sets the player color.
	 *
	 * @param color the new player color
	 */
	public static void setPlayerColor(int color) {
		PLAYER_COLOR = color;
		if(color == 2){
			OPPONENT_COLOR = 4;
		}
		else{
			OPPONENT_COLOR = 2;
		}
	}

	/**
	 * Check obstacles.
	 *
	 * @param obstacles the obstacles
	 * @param i the x
	 * @param j the y
	 * @param direction the direction
	 * @return true, if successful
	 */
	public boolean checkObstacles(ArrayList<Point> obstacles, int i, int j, int direction) {
		boolean isObstacle = false;
		for (Point obstacle : obstacles) {


			if (!isObstacle) {
				switch (direction) {
				case 9:
					if ((i) > obstacle.getY() && (j) > obstacle.getX()) {
						isObstacle = true;
					}
					break;
				case 8:
					if ((i) > obstacle.getY()) {
						isObstacle = true;
					}

					break;
				case 7:
					if ((i) > obstacle.getY() && (j) < obstacle.getX()) {
						isObstacle = true;
					}
					break;
				case 6:
					if (j > obstacle.getX()) {
						isObstacle = true;
					}
					break;
				case 5:
					System.out.println("TRUMP FOR PRESIDENT");
					break;
				case 4:
					if (j < obstacle.getX()) {
						isObstacle = true;
					}
					break;
				case 3:

					if ((i) < obstacle.getY() && (j) > obstacle.getX()) {
						return  true;
					}
					break;
				case 2:
					if (i < obstacle.getY()) {
						isObstacle = true;
					}

					break;
				case 1:
					if ((i) < obstacle.getY() && (j) < obstacle.getX()) {
						isObstacle = true;
					}
					break;
				}
			}
		}
		return isObstacle;
	}

	/**
	 * Find all possible moves on this board.
	 *
	 * @param color the color of current player
	 * @param board the board
	 * @return the array list
	 */
	private ArrayList<Move> findAllPossibleMoves(int color, Board board) {

		ArrayList<Move> moves = new ArrayList<Move>();

		Piece[][] b = board.getBoard();

		for (int i = 0; i < b.length; i++) {

			for (int j = 0; j < board.getBoard()[0].length; j++) {

				// Check if spot contains Piece of the good color
				if (b[i][j] != null && b[i][j].getColor() == color) {
					// To contain Piece of another color in the same direction

					// Check number of pieces in the same ROW/COLUMN (Check if
					// blocked by another color)
					ArrayList<Point> obstacleX = new ArrayList<Point>();
					int countX = 0;

					// Horizontal check on all spaces
					for (int x = 0; x < b[i].length; x++) {

						// If spot inside the i ROW contains a Piece
						if (b[i][x] != null && ((Piece) b[i][x]).isPiece()) {
							countX++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[i][x].getColor() != color) {
								obstacleX.add(new Point(x, i));
							}
						}
					}

					ArrayList<Point> obstacleY = new ArrayList<Point>();
					int countY = 0;
					// Vertical check on all spaces
					for (int y = 0; y < b[i].length; y++) {

						// If spot inside the i ROW contains a Piece
						if (b[y][j] != null && ((Piece) b[y][j]).isPiece()) {
							countY++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[y][j].getColor() != color) {
								obstacleY.add(new Point(j, y));
							}
						}
					}

					// Diagonal 1 check on all spaces
					ArrayList<Point> obstacleD1 = new ArrayList();
					int countD1 = 0;
					int yD = j;
					for (int x = i; x >= 0 && yD >= 0; x--) {
						if (b[x][yD] != null && ((Piece) b[x][yD]).isPiece()) {
							countD1++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[x][yD].getColor() != color) {
								obstacleD1.add(new Point(yD, x));
							}
						}
						yD--;
					}

					// Diagonal 3 check on all spaces
					ArrayList obstacleD3 = new ArrayList();
					int countD3 = 0;
					yD = j;
					for (int x = i; x >= 0 && yD < b.length; x--) {
						if (b[x][yD] != null && ((Piece) b[x][yD]).isPiece()) {
							countD3++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[x][yD].getColor() != color) {
								obstacleD3.add(new Point(yD, x));
							}
						}
						yD++;
					}
					// Diagonal 7 check on all spaces
					ArrayList obstacleD7 = new ArrayList();
					int countD7 = 0;
					yD = j;
					for (int x = i; x < b.length && yD >= 0; x++) {
						if (b[x][yD] != null && ((Piece) b[x][yD]).isPiece()) {
							countD7++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[x][yD].getColor() != color) {
								obstacleD7.add(new Point(yD, x));
							}
						}
						yD--;
					}
					// Diagonal 9 check on all spaces
					ArrayList<Point> obstacleD9 = new ArrayList<Point>();
					int countD9 = 0;
					yD = j;
					for (int x = i; x < b[i].length && yD < b[i].length; x++) {
						if (b[x][yD] != null && ((Piece) b[x][yD]).isPiece()) {
							countD9++;

							// If the Piece is of not our color, it is an
							// obstacle
							if (b[x][yD].getColor() != color) {
								obstacleD9.add(new Point(yD, x));
							}
						}
						yD++;
					}

					// Movement validation

					// FORWARD MOVE (RIGHT)
					if ((j + countX) < b[i].length) {
						if (((Piece) b[i][j + countX]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleX, i, j + countX, 6)) {
								moves.add(new Move(b[i][j], new Point(j, i), new Point(j + countX, i)));
							}
						}

					}

					// BACK MOVE (LEFT)
					if ((j - countX) >= 0) {
						if (((Piece) b[i][j - countX]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleX, i, j - countX, 4)) {
								moves.add(new Move(b[i][j], new Point(j, i), new Point(j - countX, i)));
							}
						}
					}

					// DOWN MOVE
					if ((i + countY) < b[i].length) {
						if (((Piece) b[i + countY][j]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleY, i + countY, j, 8)) {
								moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i + countY)));
							}
						}
					}

					// UP MOVE
					if ((i - countY) >= 0) {
						if (((Piece) b[i - countY][j]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleY, i - countY, j, 2)) {
								moves.add(new Move(b[i][j], new Point(j, i), new Point(j, i - countY)));
							}
						}
					}

					// D1 MOVE
					if ((i - (countD1 + countD9 - 1)) >= 0 && (j - (countD1 + countD9 - 1)) >= 0) {
						if (((Piece) b[i - (countD1 + countD9 - 1)][j - (countD1 + countD9 - 1)]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleD1, i - (countD1 + countD9 - 1), j - (countD1 + countD9 - 1),
									1)) {
								moves.add(new Move(b[i][j], new Point(j, i),
										new Point(j - (countD1 + countD9 - 1), i - (countD1 + countD9 - 1))));
							}

						}
					}
					// D3 MOVE
					if ((i - (countD7 + countD3 - 1)) >= 0 && (j + (countD7 + countD3 - 1)) < b[i].length) {
						if (((Piece) b[i - (countD7 + countD3 - 1)][j + (countD7 + countD3 - 1)]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleD3, i - (countD7 + countD3 - 1), j + (countD7 + countD3 - 1),
									3)) {
								moves.add(new Move(b[i][j], new Point(j, i),
										new Point(j + (countD7 + countD3 - 1), i - (countD7 + countD3 - 1))));
							}
						}
					}

					// D7 MOVE
					if ((i + (countD7 + countD3 - 1)) < b[i].length && (j - (countD7 + countD3 - 1))  >= 0) {
						if (((Piece) b[i + (countD7 + countD3 - 1)][j - (countD7 + countD3 - 1)]).getColor() != color) {
							// Check for obstacles
							if (!checkObstacles(obstacleD7, i + (countD7 + countD3 - 1), j - (countD7 + countD3 - 1),
									7)) {
								moves.add(new Move(b[i][j], new Point(j, i),
										new Point(j - (countD7 + countD3 - 1), i + (countD7 + countD3 - 1))));
							}
						}
					}
					// D9 MOVE
					if ((i + (countD1 + countD9 - 1)) < b[i].length && (j + (countD1 + countD9 - 1)) < b[i].length) {
						if (((Piece) b[i + (countD1 + countD9 - 1)][j + (countD1 + countD9 - 1)]).getColor() != color) {
							// Check for obstacles

							if (!checkObstacles(obstacleD9, i + (countD1 + countD9 - 1), j + (countD1 + countD9 - 1),
									9)) {
								moves.add(new Move(b[i][j], new Point(j, i),
										new Point(j + (countD1 + countD9 - 1), i + (countD1 + countD9 - 1))));
							}
						}
					}
				}
			}

		}

		return moves;
	}

	/**
	 * Finds the best move for a certain color of piece and board layout.
	 *
	 * @param color            Color of the client
	 * @param board            The current board layout
	 * @return the string
	 */
	public static String findBestMove(int color, Board board) {
		
		System.out.println("------------------");
		System.out.println("Finding best move");
		System.out.println("------------------");
		int best = miniMax(DEPTH_MAX, color, Integer.MIN_VALUE, Integer.MAX_VALUE, board);

		board.printBoard();


		return selectedMove.toString();
	}
	
	
	/**
	 * Mini max.
	 * Uses Minimax alpha/beta algorithm to get the best score for current configuration
	 * Generates a new temporary board for each different move, removes the need to revert every move
	 *
	 * @param depth the depth
	 * @param color the color
	 * @param alpha the alpha
	 * @param beta the beta
	 * @param boardParam the board param
	 * @return the int
	 */
	public static int miniMax(int depth, int color, int alpha, int beta, Board boardParam) {
		
		// If terminal node or GG
		if(depth == 0 || boardParam.gameOver(color)){
			int value = boardParam.evaluateBoard(heuristic, color);
			return value; 
		}
		
		// Get list of moves
		ArrayList<Move> moves = INSTANCE.findAllPossibleMoves(color, boardParam);
		Iterator<Move> iterator = moves.iterator();
		
		//If maximizing
		if(color == PLAYER_COLOR){
			while(iterator.hasNext()){
				Move currentMove = iterator.next();
				Board boardTemp = new Board(boardParam.getBoard());
				boardTemp.updateBoard(currentMove.toString());
				//Recursion
				int score = miniMax(depth - 1, OPPONENT_COLOR, alpha, beta, boardTemp);
				
				//Elagage
				if(score > alpha){
					alpha = score;
					if(depth == DEPTH_MAX){
						selectedMove = currentMove;
					}
				}
				if(alpha >= beta){
					return alpha;
				}
			}
			return alpha;
		}
		//if minimizing
		else{
			while(iterator.hasNext()){
				Move currentMove = iterator.next();
				Board boardTemp = new Board(boardParam.getBoard());
				boardTemp.updateBoard(currentMove.toString());
				//Recursion
				int score = miniMax(depth - 1, PLAYER_COLOR, alpha, beta, boardTemp);
				
				//Elagage
				if(score < beta){
					beta = score;
					if(depth == DEPTH_MAX){
						selectedMove = currentMove;
					}
				}
				if(alpha >= beta){
					return beta;
				}
			}
			return beta;
		}
	}
}
