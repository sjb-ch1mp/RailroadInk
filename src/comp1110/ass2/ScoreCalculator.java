package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The ScoreCalculator class puts all necessary methods to calculate the final score in a single object.
 * The stages to calculating the score (i.e. center squares, network values, error deduction and longest routes)
 * can be ordered roughly as follows:
 *  First, calculate how many tiles are on the center squares (so that we don't need to worry about it later)
 *  Second, traverse board list and deduct points for routes that don't successfully connect (so we don't worry about it later)
 *  Third, compile all the tiles into their separate routes, because the 'network value' is determined per route.
 *  Fourth, evaluate each route separately by counting the exits that it connects to and getting adding the network value to the score.
 *  Fifth, evaluate each route separately to find the longest highway.
 *  Sixth, evaluate each route separately to find the longest railway.
 */
public class ScoreCalculator
{
    //This hashmap holds the scores for each number of exits that are joined by a route as per the table 'Network Values'
    //e.g. <2, 4>, <3, 8>, etc
    private HashMap<Integer, Integer> networkValues;
    private int score;
    private Board finalBoardState;

    public ScoreCalculator(Board board)
    {
        finalBoardState = board;
        score = 0;
        //enter values for the network values
    }

    public void countCenterSquares()
    {
        //this method counts how many tiles are on the center squares using the
        //fields in the finalBoardState
    }

    public void deductErrors()
    {
        //this method will run through the board list and deduct points from the score
        //for errors
    }

    private ArrayList<ArrayList<Tile>> compileRoutes()
    {
        /*
        * This method may work in a similar fashion to the A* pathfinding algorithm in that it can have
        * an 'evaluated' list of Tile enums from the board and a 'not yet evaluated' list of Tile enums from the board.
        * By working through the board and evaluating each 'node', when a legal connection is found, the connecting node
        * is placed in 'not yet evaluated' so that the method knows to return to it later.
        *
        * The main loop can be something like 'for each exit coordinate - if it has a legal connection, follow the route'.
        *
        * In this way, hopefully all Tiles associated with a given route are stored in a single ArrayList<Tile>, and all
        * routes are stored in an overarching routes ArrayList
        * */
        return new ArrayList<>(0);
    }

    private void calculateLongestRoute(char type)
    {
        //put type (either 'R' or 'H') into the conditional statement that checks for a connection between tiles.
        //this will ensure that either the longest Railway is calculated, or the longest Highway
    }

    public int getScore()
    {
        return score;
    }
}
