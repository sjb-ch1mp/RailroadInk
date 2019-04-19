package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Board board;
    private ArrayList<ArrayList<Tile>> routes;

    public ScoreCalculator(Board board)
    {
        this.board = board;
        routes = compileRoutes();
        score = calculateScore();
        //enter values for the network values
    }

    private int calculateScore()
    {
        int score = 0;
        score += countCenterSquares();
        score += calculateNetworkScore();
        score -= countErrors();

        int longestHighway = calculateLongestRoute('H');
        int longestRailroad = calculateLongestRoute('R');
        score += (longestHighway > longestRailroad)?longestHighway:longestRailroad;

        return score;
    }

    private int countCenterSquares()
    {
        //this method counts how many tiles are on the center squares using the
        //fields in the finalBoardState
        int centerSquares = 0;
        for(Map.Entry<String, Tile> mapEntry : board.getPlacements().entrySet())
        {
            if(board.isCenterCoord(mapEntry.getValue().getCoords()))
            {
                centerSquares++;
            }
        }
        return centerSquares;
    }

    private int countErrors()
    {
        //this method will run through the board list and deduct points from the score
        //for errors
        int errors = 0;
        for(Map.Entry<String, Tile> mapEntry : board.getPlacements().entrySet())
        {
            //check on the edge first
            if(mapEntry.getValue().getRow() == 'A')
            { //placement is on top edge
                if(mapEntry.getValue().getEdge('N') != '0' && !board.isExitCoord(mapEntry.getValue().getCoords()))
                {
                    errors++;
                }
            }
            else if(mapEntry.getValue().getRow() == 'G')
            { //placement is on bottom edge
                if(mapEntry.getValue().getEdge('S') != '0' && !board.isExitCoord(mapEntry.getValue().getCoords()))
                {
                    errors++;
                }
            }
            if(mapEntry.getValue().getColumn() == 0)
            { //placement is on west edge
                if(mapEntry.getValue().getEdge('W') != '0' && !board.isExitCoord(mapEntry.getValue().getCoords()))
                {
                    errors++;
                }
            }
            else if(mapEntry.getValue().getColumn() == 6)
            { //placement is on east edge
                if(mapEntry.getValue().getEdge('E') != '0' && !board.isExitCoord(mapEntry.getValue().getCoords()))
                {
                    errors++;
                }
            }

            //check all other placements
            String adjCoords = "";
            if(mapEntry.getValue().getRow() > 'A')
            { //check North edge
                if(mapEntry.getValue().getEdge('N') != '0')
                {
                    adjCoords = board.getAdjCoords('N', mapEntry.getValue());
                    if(!board.getPlacements().containsKey(adjCoords))
                    {
                        errors++;
                    }
                    else if(board.getPlacements().get(adjCoords).getEdge(board.getOppositeEdge('N')) != mapEntry.getValue().getEdge('N'))
                    {
                        errors++;
                    }
                }
            }
            if(mapEntry.getValue().getRow() < 'G')
            { //check South edge
                if(mapEntry.getValue().getEdge('S') != '0')
                {
                    adjCoords = board.getAdjCoords('S', mapEntry.getValue());
                    if(!board.getPlacements().containsKey(adjCoords))
                    {
                        errors++;
                    }
                    else if(board.getPlacements().get(adjCoords).getEdge(board.getOppositeEdge('S')) != mapEntry.getValue().getEdge('S'))
                    {
                        errors++;
                    }
                }
            }
            if(mapEntry.getValue().getColumn() > 0)
            { //check West edge
                if(mapEntry.getValue().getEdge('W') != '0')
                {
                    adjCoords = board.getAdjCoords('W', mapEntry.getValue());
                    if(!board.getPlacements().containsKey(adjCoords))
                    {
                        errors++;
                    }
                    else if(board.getPlacements().get(adjCoords).getEdge(board.getOppositeEdge('W')) != mapEntry.getValue().getEdge('W'))
                    {
                        errors++;
                    }
                }
            }
            if(mapEntry.getValue().getColumn() < 6)
            { //check East edge
                if(mapEntry.getValue().getEdge('E') != '0')
                {
                    adjCoords = board.getAdjCoords('E', mapEntry.getValue());
                    if(!board.getPlacements().containsKey(adjCoords))
                    {
                        errors++;
                    }
                    else if(board.getPlacements().get(adjCoords).getEdge(board.getOppositeEdge('E')) != mapEntry.getValue().getEdge('E'))
                    {
                        errors++;
                    }
                }
            }

        }

        return errors;
    }

    private int calculateNetworkScore()
    {
        int networkScore = 0;

        for(ArrayList<Tile> route : routes)
        {
            int exit = 0;
            for(Tile tile : route)
            {
                if(board.isExitCoord(tile.getCoords()))
                {
                    String exitType = board.getExitType(tile.getCoords());
                    if(tile.getEdge(exitType.charAt(0)) == exitType.charAt(1))
                    {
                        exit++;
                    }
                }
            }
            networkScore += networkValues.get(exit);
        }

        return networkScore;
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

    private int calculateLongestRoute(char type)
    {
        //put type (either 'R' or 'H') into the conditional statement that checks for a connection between tiles.
        //this will ensure that either the longest Railway is calculated, or the longest Highway
        return 1;
    }

    public int getScore()
    {
        return score;
    }
}
