package Game.Utilities;

import java.util.ArrayList;

import Game.Board;
import Game.Group;
import Game.Piece;

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
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				if(b.getBoard()[i][j].getColor() == Board.COLOR_BLACK){
					b.getBoard()[i][j].setXY(i, j);
					b.blackPieces.add(b.getBoard()[i][j]);
				}
				else if(b.getBoard()[i][j].getColor() == Board.COLOR_WHITE){
					b.getBoard()[i][j].setXY(i, j);
					b.whitePieces.add(b.getBoard()[i][j]);
				}
			}
		}
	}
	
	
	public static void setGroupsOfPieces(Board b){
		  ArrayList<Piece> plist = new ArrayList<Piece>();
	      int count = 0;
	      for (int i = 0; i<b.blackPieces.size(); i++) {
	    	  Piece p = b.blackPieces.get(i);
	    	  plist.add(p);
	    	  count++;
	      }/**
	      Piece Connectlist = plist;
	      plist = plist.next;
	      Connectlist.next = null;
	      boolean isConnected = true;
	      int teller = 1;
	      while (count > 1 && plist != null && isConnected) {
	          isConnected = false;
	          Piece p = plist;
	          for (Piece plist2 = Connectlist; plist2 != null && !isConnected;
	               plist2 = plist2.next) {
	              if ((p.x == plist2.x + 1 || p.x == plist2.x - 1
	                   || p.x == plist2.x)
	                  && (p.y == plist2.y || p.y == plist2.y + 1
	                      || p.y == plist2.y - 1)) {
	                  plist = plist.next;
	                  p.next = Connectlist;
	                  Connectlist = p;
	                  teller++;
	                  isConnected = true;
	              }
	          }
	          if (!isConnected) {
	              Piece tracker = plist;
	              for (p = plist.next; p != null && !isConnected; p = p.next) {
	                  for (Piece plist2 = Connectlist; plist2 != null && !isConnected;
	                       plist2 = plist2.next) {
	                      if ((p.x == plist2.x + 1 || p.x == plist2.x - 1
	                           || p.x == plist2.x)
	                          && (p.y == plist2.y || p.y == plist2.y + 1
	                              || p.y == plist2.y - 1)) {
	                          tracker.next = p.next;
	                          p.next = Connectlist;
	                          Connectlist = p;
	                          teller++;
	                          isConnected = true;
	                      }
	                  }
	                  if (!isConnected)
	                      tracker = p;
	              }
	          }
	      }
		*/
	}
}
