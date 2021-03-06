package Game.AI.Heuristic;

import Game.AI.Heuristic.Strategies.GeneralStrategy;
import Game.AI.Heuristic.Strategies.Strategy;
import Game.Board;

/**
 * Created by Arnaud on 11/16/2016.
 */
public class Heuristic {

    //INSTANCE OF POSSIBLE STRATEGIES
    private GeneralStrategy generalStrat; //Strategy to eat pieces and block moves from opponent

    private Strategy strat;

    public Heuristic(){
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
