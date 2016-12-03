package Game;

import java.awt.*;

/**
 * Created by Arnaud on 10/26/2016.
 */

public class Piece{
	
	/** The Constant EMPTY. */
	// 4 = white, 2 = black
	public static final int EMPTY = 0;
	
	/** The Constant BLACK. */
	public static final int BLACK = 2;
	
	/** The Constant WHITE. */
	public static final int WHITE = 4;
	
	/** The x. */
	public int x;
	
	/** The y. */
	public int y;
	
	/** The color. */
	private int color;
	
	/**
	 * Instantiates a new piece.
	 *
	 * @param color the color
	 */
	public Piece(int color) {
		super();
		this.color = color;
	}

	/**
	 * Instantiates a new piece.
	 *
	 * @param p the p
	 */
	public Piece(Piece p){
        this.color = p.getColor();
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }
    
    /**
     * Checks if is piece.
     *
     * @return true, if is piece
     */
    public boolean isPiece(){
    	return (color == WHITE || color == BLACK);
    }
    
    /**
     * Sets the X and Y.
     *
     * @param x the x
     * @param y the y
     */
    public void setXY(int x, int y){
    	this.x = x;
    	this.y = y;
    }
}
