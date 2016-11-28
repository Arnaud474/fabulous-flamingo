package Game.AI.Heuristic.Strategies;

import Game.Board;
import Game.Piece;

import java.util.ArrayList;
/**
 * Created by willkoua on 2016-11-21.
 */
public class BeginStrategy extends Strategy {

    @Override
    public int calculateValues(Board board, int currentColor) {

        return beginStrategy(board, currentColor);
    }

    private int beginStrategy(Board board, int type){
        int white = Piece.WHITE;
        int black = Piece.BLACK;
        int empty = Piece.EMPTY;
        int currentValue = 0;
        Piece[][] tab = board.getBoard();

        //noir
        if(type == black){

            if(tab[2][0].getColor() == white && tab[1][1].getColor() == empty)
                currentValue += 3;

            if(tab[3][0].getColor() == white && tab[2][1].getColor()  == empty)
                currentValue += 3;

            if(tab[4][0].getColor() == white && tab[6][1].getColor() == empty)
                currentValue += 3;

            if(tab[5][0].getColor() == white && tab[5][1].getColor() == empty)
                currentValue += 3;

            if(tab[2][7].getColor() == white && tab[1][6].getColor()  == empty)
                currentValue += 3;

            if(tab[3][7].getColor() == white && tab[3][6].getColor() == empty)
                currentValue += 3;

            if(tab[5][7].getColor() == white && tab[6][6].getColor() == empty)
                currentValue += 3;

            if(tab[4][7].getColor() == white && tab[5][6].getColor() == empty)
                currentValue += 3;
        }

        //blancs
        if(type == white){
            if(tab[0][2].getColor() == black && tab[1][1].getColor() == empty)
                currentValue += 3;

            if(tab[0][3].getColor() == black && tab[1][2].getColor() == empty)
                currentValue += 3;

            if(tab[7][3].getColor() == black && tab[6][2].getColor() == empty)
                currentValue += 3;

            if(tab[7][2].getColor() == black && tab[6][1].getColor() == empty)
                currentValue += 3;

            if(tab[0][5].getColor() == black && tab[1][6].getColor() == empty)
                currentValue += 3;

            if(tab[0][5].getColor() == black && tab[1][5].getColor() == empty)
                currentValue += 3;

            if(tab[7][4].getColor() == black && tab[6][5].getColor() == empty)
                currentValue += 3;

            if(tab[7][3].getColor() == black && tab[6][6].getColor() == empty)
                currentValue += 3;
        }
        
        return currentValue;
    }
}
