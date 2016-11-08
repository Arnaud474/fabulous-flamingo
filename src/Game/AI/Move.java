package Game.AI;

import Game.Piece;

import java.awt.*;
import java.util.HashMap;

import sun.net.www.content.audio.x_aiff;

/**
 * Created by Arnaud on 11/7/2016.
 */

public class Move {
    private Piece piece;
    private Point from;
    private Point to;
    private int type;
    private int cost;

    public Move(Piece p, Point f, Point t, int type){
        this.piece = p;
        this.from = f;
        this.to = t;
        this.type = type;
    }
    
    public void setCost(int cost){
    	this.cost = cost;
    }
    
    public int getType(){
    	return this.type;
    }

	@Override
	public String toString() {
		HashMap<Integer, Integer> hmapVert = new HashMap<Integer, Integer>();
		HashMap<Integer, String> hmapHor = new HashMap<Integer, String>();
		hmapHor.put(0, "A");
		hmapHor.put(1, "B");
		hmapHor.put(2, "C");
		hmapHor.put(3, "D");
		hmapHor.put(4, "E");
		hmapHor.put(5, "F");
		hmapHor.put(6, "G");
		hmapHor.put(7, "H");
		
		hmapVert.put(7, 1);
		hmapVert.put(6, 2);
		hmapVert.put(5, 3);
		hmapVert.put(4, 4);
		hmapVert.put(3, 5);
		hmapVert.put(2, 6);
		hmapVert.put(1, 7);
		hmapVert.put(0, 8);

		return hmapHor.get(from.x) + hmapVert.get(from.y) + " - " + hmapHor.get(to.x) + hmapVert.get(to.y);
	}
    
    
}
