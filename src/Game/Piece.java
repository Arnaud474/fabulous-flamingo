package Game;

import java.awt.*;

/**
 * Created by Arnaud on 10/26/2016.
 */

// LEGACY
public class Piece{
	// 4 = white, 2 = black
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
}
