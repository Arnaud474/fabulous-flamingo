package Game.AI;

import Game.Piece;

import java.awt.*;
import java.util.HashMap;

import sun.net.www.content.audio.x_aiff;

/**
 * Created by Arnaud on 11/7/2016.
 */

public class Move {
    
    /** The piece. */
    private Piece piece;
    
    /** The from coordinate. */
    private Point from;
    
    /** The to coordinate. */
    private Point to;
    
    
    /** The cost. */
    private int cost;

    /**
     * Instantiates a new move.
     *
     * @param p the p
     * @param f the f
     * @param t the t
     */
    public Move(Piece p, Point f, Point t){
        this.piece = p;
        this.from = f;
        this.to = t;
    }
    
    /**
     * Sets the cost.
     *
     * @param cost the new cost
     */
    public void setCost(int cost){
    	this.cost = cost;
    }
 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Used to create a new command to send to server
	 */
	@Override
	public String toString() {
		HashMap<Integer, Character> hmapVert = new HashMap<Integer, Character>();
		HashMap<Integer, Character> hmapHor = new HashMap<Integer, Character>();
		hmapHor.put(0, 'A');
		hmapHor.put(1, 'B');
		hmapHor.put(2, 'C');
		hmapHor.put(3, 'D');
		hmapHor.put(4, 'E');
		hmapHor.put(5, 'F');
		hmapHor.put(6, 'G');
		hmapHor.put(7, 'H');
		
		hmapVert.put(7, '1');
		hmapVert.put(6, '2');
		hmapVert.put(5, '3');
		hmapVert.put(4, '4');
		hmapVert.put(3, '5');
		hmapVert.put(2, '6');
		hmapVert.put(1, '7');
		hmapVert.put(0, '8');
		
		return "" + (hmapHor.get(from.x)) + (hmapVert.get(from.y)) + " - " + hmapHor.get(to.x) + hmapVert.get(to.y);
	}
    
    
}
