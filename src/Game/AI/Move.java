package Game.AI;

import Game.Piece;

import java.awt.*;

/**
 * Created by Arnaud on 11/7/2016.
 */

public class Move {
    private Piece piece;
    private Point from;
    private Point to;
    private int cost;

    public Move(Piece p, Point f, Point t){
        this.piece = p;
        this.from = f;
        this.to = t;
    }

    public void setCost(int cost){

    }
}
