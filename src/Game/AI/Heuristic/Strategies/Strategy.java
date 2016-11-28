package Game.AI.Heuristic.Strategies;

import Game.AI.Move;
import Game.Board;

import java.util.ArrayList;

/**
 * Created by Arnaud on 11/16/2016.
 */
public abstract class Strategy {

    protected int value;
    protected Board board;
    protected int color;

    public abstract int calculateValues(Board board,  int currentColor);
}
