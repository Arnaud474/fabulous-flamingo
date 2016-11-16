package Game.AI.Heuristic.Strategies;

import Game.AI.Move;
import Game.Board;

import java.util.ArrayList;

/**
 * Created by Arnaud on 11/16/2016.
 */
public class PassiveStrategy extends Strategy{

    @Override
    public void calculateValues(Board board, ArrayList<Move> moves) {

        int cost;

        //For each move in the move list
        for(Move move : moves){
            //Temp cost to increment before setting it inside the move
            cost = 0;

            //INCREMENT COST WITH FUNCTION1

            //INCREMENT COST WITH FUNCTION2

            //INCREMENT COST WITH FUNCTION...
        }
    }

    /**
     * Checks for From and To positions to increment score
     *
     * @param move
     * @return
     */
    public int calculateEdgeCornerCost(Move move){

        int cost = 0;



        return cost;
    }
}
