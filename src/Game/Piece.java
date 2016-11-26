package Game;

import java.awt.*;

/**
 * Created by Arnaud on 10/26/2016.
 */

// LEGACY
public class Piece{
	// 4 = white, 2 = black
	public static final int EMPTY = 0;
	public static final int BLACK = 2;
	public static final int WHITE = 4;

	private int color;
	
	public Piece(int color) {
		super();
		this.color = color;
	}

	public Piece(Piece p){
        this.color = p.getColor();
    }

    public int getColor() {
        return color;
    }
    public boolean isPiece(){
    	return (color == WHITE || color == BLACK);
    }
}
