package Game.AI.Heuristic;

import Game.AI.Heuristic.Strategies.AggressiveStrategy;
import Game.AI.Heuristic.Strategies.PassiveStrategy;
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
    private PassiveStrategy passStrat; //Strategy to eat pieces and block moves from opponent

    private Strategy strat;

    public Heuristic(){

        aggroStrat = new AggressiveStrategy();
        passStrat = new PassiveStrategy();

        this.strat = passStrat;
    }

    /**
     * Execute the calculation of value for every moves
     *
     * @param board
     */
    public void calculate(Board board, ArrayList<Move> moves){

        //CONDITIONS TO DECIDE WHICH STRATEGY TO USE
        chooseStrategy(board, moves);

        strat.calculateValues(board, moves);
    }

    /**
     * Picks strategy according to board state
     *
     * @param board
     */
    public void chooseStrategy(Board board, ArrayList<Move> moves){

    }
}
