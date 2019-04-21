package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;

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
    private ArrayList<ArrayList<RouteNode>> routes;
    private int longestHighway;
    private int longestRailroad;
    private int centerScore;
    private int networkScore;
    private int errors;
    private HashMap<Integer, Character> directions;
    private HashMap<Integer, IntPredicate> tests;

    public ScoreCalculator(Board board)
    {
        networkValues = new HashMap<>(0);
        for(int i=2; i<12; i++)
        {
            networkValues.put(i, 4*(i-1));
        }
        networkValues.put(12, 45);

        directions = new HashMap<>(0);
        directions.put(0, 'N');
        directions.put(1, 'E');
        directions.put(2, 'S');
        directions.put(3, 'W');

        tests = new HashMap<>(0);
        tests.put(0, x -> x > 65);
        tests.put(1, x -> x < 6);
        tests.put(2, x -> x < 71);
        tests.put(3, x -> x > 0);

        this.board = board;
        routes = compileRoutes();

        centerScore = countCenterSquares();
        errors = countErrors();
        networkScore = calculateNetworkScore();
        longestHighway = calculateLongestRoute('H');
        longestRailroad = calculateLongestRoute('R');

        score = (centerScore + networkScore + longestHighway + longestRailroad) - errors;
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
            Tile tile = mapEntry.getValue();

            for(int i=0; i<4; i++)
            { //this loop tests all sides of the tile using lambda expressions
                if(tests.get(i).test(tile.getTestValue(i)))
                {
                    if(errorAtEdge(tile, directions.get(i)))
                    {
                        errors++;
                    }
                }
            }
        }

        return errors;
    }

    boolean errorAtEdge(Tile tile, char edge)
    {
        if(tile.getEdge(edge) != '0')
        {
            String adjCoords = board.getAdjCoords(edge, tile);
            if(!board.getPlacements().containsKey(adjCoords))
            {
                return true;
            }
            else if(board.getPlacements().get(adjCoords).getEdge(board.getOppositeEdge(edge)) != tile.getEdge(edge))
            {
                return true;
            }
        }
        return false;
    }

    private int calculateNetworkScore()
    {
        int networkScore = 0;

        for(ArrayList<RouteNode> route : routes)
        {
            int exit = 0;
            for(RouteNode routeNode : route)
            {
                if(board.isExitCoord(routeNode.data.getCoords()))
                {
                    if(routeNode.entry ==
                        board.getExitCoords().get(routeNode.data.getCoords()).charAt(1))
                    { //this is a valid connection to an exit
                        exit++;
                    }
                    else if(routeNode.data.isStation())
                    {
                        if(board.getExit(routeNode.data.getCoords()).charAt(1) ==
                                routeNode.data.getEdge(board.getExit(routeNode.data.getCoords()).charAt(0)))
                        { //this is a valid connect to an exit
                            exit++;
                        }
                    }
                }
            }
            if(exit > 1)
            {
                networkScore += networkValues.get(exit);
            }
        }

        return networkScore;
    }

    private ArrayList<ArrayList<RouteNode>> compileRoutes()
    {
        ArrayList<ArrayList<RouteNode>> routes = new ArrayList<>(0);
        ArrayList<String> usedExits = new ArrayList<>(0);

        for(Map.Entry<String, String> mapEntry : board.getExitCoords().entrySet())
        {
            String exit = mapEntry.getKey();

            if(board.getPlacements().containsKey(exit) &&
                !usedExits.contains(exit))
            { //exit tile is part of a route that has not been evaluated

                ArrayList<RouteNode> route;

                //check if start point is valid exit connection
                if(board.getPlacements().get(exit).getEdge(mapEntry.getValue().charAt(0)) ==
                    mapEntry.getValue().charAt(1))
                { //exit connector == edge of tile
                    route = getRoute(exit);

                    //add evaluated exits to usedExits
                    for(RouteNode routeNode : route)
                    {
                        if(board.isExitCoord(routeNode.data.getCoords()))
                        {
                            usedExits.add(routeNode.data.getCoords());
                        }
                    }

                    routes.add(route);
                }
            }

        }
        return routes;
    }

    private ArrayList<RouteNode> getRoute(String startCoord)
    {
        ArrayList<RouteNode> route = new ArrayList<>(0);
        ArrayList<RouteNode> evaluated = new ArrayList<>(0);
        ArrayList<RouteNode> unevaluated = new ArrayList<>(0);

        RouteNode startNode = new RouteNode(board.getExit(startCoord).charAt(1), board.getTile(startCoord));
        unevaluated.add(startNode);

        while(unevaluated.size() > 0)
        {
            RouteNode routeNode = unevaluated.get(0);

            for(int i=0; i<4; i++)
            {
                if(tests.get(i).test(routeNode.data.getTestValue(i)))
                {
                    char edge = directions.get(i);
                    char exit = routeNode.data.getEdge(edge);
                    String adjCoords = board.getAdjCoords(edge, routeNode.data);
                    RouteNode adjRouteNode;
                    if(board.getPlacements().containsKey(adjCoords))
                    { //tile exists at these coordinates
                        adjRouteNode = new RouteNode(board.getTile(adjCoords).getEdge(board.getOppositeEdge(edge)), board.getTile(adjCoords));
                        if(!alreadyEvaluated(adjRouteNode, evaluated) && !alreadyInUnevaluated(adjRouteNode, unevaluated))
                        {//routeNode has not yet been evaluated
                            if(!routeNode.data.isOverPass())
                            {
                                if(exit != '0' && exit == adjRouteNode.entry)
                                {
                                    unevaluated.add(adjRouteNode);
                                }
                            }
                            else
                            {
                                if(routeNode.entry == adjRouteNode.entry)
                                {
                                    unevaluated.add(adjRouteNode);
                                }
                            }
                        }
                        else
                        {
                            if(!routeNode.data.isOverPass() && adjRouteNode.data.isOverPass())
                            { //if the tile is an overpass, get the coordinates of the next tile on the other side of the overpass
                                adjCoords = board.getAdjCoords(edge, adjRouteNode.data);
                                if(board.getPlacements().containsKey(adjCoords))
                                { //if the tile on the other side of the overpass exists
                                    adjRouteNode = new RouteNode(board.getTile(adjCoords).getEdge(board.getOppositeEdge(edge)), board.getTile(adjCoords));
                                    if(!alreadyEvaluated(adjRouteNode, evaluated) && !alreadyInUnevaluated(adjRouteNode, unevaluated))
                                    {//the tile on the other side of the overpass has not been evaluated
                                        if(exit != '0' && exit == adjRouteNode.entry)
                                        {
                                            unevaluated.add(adjRouteNode);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //remove routeNode from unevaluated
            unevaluated.remove(0);

            //add routeNode to evaluated
            evaluated.add(routeNode);

            //add tile to route
            route.add(routeNode);
        }
        return route;
    }

    private boolean alreadyEvaluated(RouteNode adjRouteNode, ArrayList<RouteNode> evaluated)
    {
        for(RouteNode routeNode : evaluated)
        {
            if(routeNode.data.getCoords().equals(adjRouteNode.data.getCoords()))
            {
                return true;
            }
        }
        return false;
    }
    private boolean alreadyInUnevaluated(RouteNode adjRouteNode, ArrayList<RouteNode> unevaluated)
    {
        for(RouteNode routeNode : unevaluated)
        {
            if(routeNode.data.getCoords().equals(adjRouteNode.data.getCoords()))
            {
                return true;
            }
        }
        return false;
    }


    private int calculateLongestRoute(char type)
    {
        //put type (either 'R' or 'H') into the conditional statement that checks for a connection between tiles.
        //this will ensure that either the longest Railway is calculated, or the longest Highway
        int max = Integer.MIN_VALUE;

        for(ArrayList<RouteNode> route : routes)
        {
            int longestStretch = getLongestStretch(route, type);
            if(max < longestStretch)
            {
                max = longestStretch;
            }
        }

        return max;
    }

    private int getLongestStretch(ArrayList<RouteNode> route, char type)
    {
        return 0;
    }

    public int getAdvancedScore()
    {
        return score;
    }

    public int getBasicScore()
    {
        return (centerScore + networkScore) - errors;
    }

    public int getCenterScore()
    {
        return centerScore;
    }

    public int getNetworkScore()
    {
        return networkScore;
    }

    public int getErrors()
    {
        return errors;
    }

    public int getLongestHighway()
    {
        return longestHighway;
    }

    public int getLongestRailroad()
    {
        return longestRailroad;
    }

    private class RouteNode
    {
        char entry;
        Tile data;

        RouteNode(char entry, Tile data)
        {
            this.data = data;
            this.entry = entry;
        }
    }

    private class LengthNode extends RouteNode
    {
        private int cumulativeTotal;

        LengthNode(int cumulativeTotal, char type, Tile data)
        {
            super(type, data);

            if(cumulativeTotal == 0)
            {
                if(hasExitType(type))
                {
                    this.cumulativeTotal = 1;
                }
                else
                {
                    this.cumulativeTotal = 0;
                }
            }
        }

        boolean hasExitType(char type)
        {
            for(char edge : data.getEdges())
            {
                if(edge == type)
                {
                    return true;
                }
            }
            return false;
        }

        void updateTotal(int newTotal)
        {
            cumulativeTotal = newTotal;
        }
    }

}