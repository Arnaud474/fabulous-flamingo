package Game.AI.Heuristic;

import Game.AI.AI;
import Game.AI.Heuristic.Strategies.AggressiveStrategy;
import Game.AI.Heuristic.Strategies.BeginStrategy;
import Game.AI.Heuristic.Strategies.GeneralStrategy;
import Game.AI.Heuristic.Strategies.Strategy;
import Game.AI.Move;
import Game.Board;

import java.util.ArrayList;

/**
 * Created by Arnaud on 11/16/2016.
 */
public class Heuristic {

    //INSTANCE OF POSSIBLE STRATEGIES
    private AggressiveStrategy aggroStrat; //Strategy to link pieces togheter
    private GeneralStrategy generalStrat; //Strategy to eat pieces and block moves from opponent
    private BeginStrategy beginStrat;

    private Strategy strat;

    public Heuristic(){

        aggroStrat = new AggressiveStrategy();
        generalStrat = new GeneralStrategy();

        this.strat = generalStrat;
    }

    /**
     * Execute the calculation of value for every moves
     *
     * @param board
     */
    public int calculate(Board board, int currentColor){

        //CONDITIONS TO DECIDE WHICH STRATEGY TO USE
        chooseStrategy(board, currentColor);

        
        return strat.calculateValues(board, currentColor);

    }

    /**
     * Picks strategy according to board state
     *
     * @param board
     */
    public void chooseStrategy(Board board, int currentColor){

    }
}
