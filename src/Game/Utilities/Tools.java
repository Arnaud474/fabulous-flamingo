package Game.Utilities;

import Game.Board;

public class Tools {
	/**
	 * 
	 * @param currentColor
	 * @return int - The other player's color
	 */
	public static int getOtherPlayer(int currentColor){
		if(currentColor==2)
			return 4;
		else if(currentColor == 4)
			return 2;
		else
			return 0;
	}
	
	/**
	 * 
	 * @param b - The board object
	 * 
	 * Sets the list of each color's pieces inside the board
	 */
	public static void setListOfPieces(Board b){
		for (int i = 0; i < Board.BOARD_SIZE; i++) {
			for (int j = 0; j < Board.BOARD_SIZE; i++) {
				if(b.getBoard()[i][j].getColor() == Board.COLOR_BLACK){
					b.blackPieces.add(b.getBoard()[i][j]);
				}
				else if(b.getBoard()[i][j].getColor() == Board.COLOR_WHITE){
					b.whitePieces.add(b.getBoard()[i][j]);
				}
			}
		}
	}
}
