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

    //The directions hash map makes it possible to iterate through each edge of a tile being evaluated: N, E, S, W
    private HashMap<Integer, Character> directions;

    //The tests has map makes it possible to iterate through appropriate tests for whether an edge evaluation is necessary
    private HashMap<Integer, IntPredicate> tests;

    /**
     * Whenever a new ScoreCalculator object is created. The score
     * is automatically calculated.
     * @param board
     */
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

        //The parameter x can be either a row or column, depending upon which edge is being evaluated
        tests = new HashMap<>(0);
        tests.put(0, x -> x > 65); //if the row is greater than 'A', IntPredicate returns true
        tests.put(1, x -> x < 6); //if the column is less than 6, IntPredicate returns true
        tests.put(2, x -> x < 71); //if the row is less than 'G', IntPredicate returns true
        tests.put(3, x -> x > 0); //if the column is greater than 0, IntPredicate returns true

        this.board = board;
        routes = compileRoutes(); //compile all the routes on the board

        /*
        * Might need to also create a list of 'free hanging' tile (i.e. tiles that are part of a route that
        * does not connect to an exit and therefore are never evaluated as part of a route, see game for example:
        * A4G10B2F10A4E10A0F20A3D17A0E22A2E31B1E44S0D42A3D23A4D31A2F30B1F42A1G30A0C42A0C57B0C22A2F03A1E02S5D01A0B22B0A50A4D51A3D61B2B53A0B30B2A31A4E60A3A41A0B03)
        * */

        centerScore = countCenterSquares();
        errors = countErrors();
        networkScore = calculateNetworkScore();
        longestHighway = calculateLongestRoute('H');
        longestRailroad = calculateLongestRoute('R');

        score = (centerScore + networkScore + longestHighway + longestRailroad) - errors;
    }

    /**
     * This method counts how many tiles are on the center squares of the board.
     * @return (int) Number of squares on the center tiles.
     */
    private int countCenterSquares()
    {
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

    /**
     * This method iterates through all placements in the board and checks every edge
     * for an error.
     * @return (int) Number of errors on the board.
     */
    private int countErrors()
    {
        int errors = 0;
        for(Map.Entry<String, Tile> mapEntry : board.getPlacements().entrySet())
        {
            Tile tile = mapEntry.getValue();

            //This loop uses the directions HashMap, the tests HashMap and the
            //getTestValue() method in conjunction to achieve an iterable edge check
            for(int i=0; i<4; i++)
            {
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

    /**
     * This method evaluates whether there is an error at a given edge.
     * @param tile
     * @param edge
     * @return (boolean)
     */
    private boolean errorAtEdge(Tile tile, char edge)
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

    /**
     * This method counts the number of exits that each route connects to, in order
     * to calculate the network score.
     * @return (int) The network score.
     */
    private int calculateNetworkScore()
    {
        int networkScore = 0;

        for(ArrayList<RouteNode> route : routes)
        { //For each Route in the routes ArrayList

            int exit = 0;
            for(RouteNode routeNode : route)
            { //For each RouteNode in a give route
                if(board.isExitCoord(routeNode.data.getCoords()))
                { //If the routeNode is at an exit coordinate
                    if(routeNode.entry ==
                        board.getExitCoords().get(routeNode.data.getCoords()).charAt(1) &&
                            board.getExit(routeNode.data.getCoords()).charAt(1) ==
                                    routeNode.data.getEdge(board.getExit(routeNode.data.getCoords()).charAt(0)))
                    { //If there is a valid connection to the exit AND the exit type is the same as the entry into the routeNode
                        exit++;
                    }
                    else if(routeNode.data.isStation())
                    { //Otherwise, if the routeNode is a station
                        if(board.getExit(routeNode.data.getCoords()).charAt(1) ==
                                routeNode.data.getEdge(board.getExit(routeNode.data.getCoords()).charAt(0)))
                        { //If there is a valid connection to the exit at all
                            exit++;
                        }
                    }
                }
            }
            if(exit > 1)
            { //If the route connects at least 2 exits
                networkScore += networkValues.get(exit);
            }
        }

        return networkScore;
    }

    /**
     * This method iterates through exit coordinates to see if they are the
     * beginning of a route. If they are, it calls the getRoute method, which
     * gathers up the placements in that route.
     * @return (ArrayList) All routes on the board
     */
    private ArrayList<ArrayList<RouteNode>> compileRoutes()
    {
        ArrayList<ArrayList<RouteNode>> routes = new ArrayList<>(0);
        ArrayList<String> usedExits = new ArrayList<>(0);

        for(Map.Entry<String, String> mapEntry : board.getExitCoords().entrySet())
        { //For each exit coordinate in the board
            String exit = mapEntry.getKey();

            if(board.getPlacements().containsKey(exit) &&
                !usedExits.contains(exit))
            { //If there is a tile at this exit coordinate and it is part of a route that has not been evaluated

                ArrayList<RouteNode> route;

                if(board.getPlacements().get(exit).getEdge(mapEntry.getValue().charAt(0)) ==
                    mapEntry.getValue().charAt(1))
                { //If the start point of the route is a valid connection

                    //gather all the placements in this route
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

    /**
     * This method traces all tiles from a given start point (an exit coordinate) and
     * evaluates whether they belong in a route. Each node in the route is stored as a
     * RouteNode - which keeps track of the entry point to that node.
     * @param startCoord
     * @return (ArrayList) A route.
     */
    private ArrayList<RouteNode> getRoute(String startCoord)
    {
        ArrayList<RouteNode> route = new ArrayList<>(0);
        ArrayList<RouteNode> evaluated = new ArrayList<>(0);
        ArrayList<RouteNode> unevaluated = new ArrayList<>(0);

        //Store the data for the starting tile as a RouteNode
        RouteNode startNode = new RouteNode(board.getExit(startCoord).charAt(1), board.getTile(startCoord));

        //Add it to the unevaluated ArrayList
        unevaluated.add(startNode);

        //This loop will iterate until all legal connections from the start coordinate have been evaluated
        while(unevaluated.size() > 0)
        {
            //get the first unevaluated routeNode
            RouteNode routeNode = unevaluated.get(0);

            //This loop uses the directions HashMap, the tests HashMap and the
            //getTestValue() method in conjunction to achieve an iterable edge check
            for(int i=0; i<4; i++)
            {
                if(tests.get(i).test(routeNode.data.getTestValue(i)))
                {
                    char edge = directions.get(i); //the edge being evaluated
                    char exit = routeNode.data.getEdge(edge); //the route type that exits the tile at this edge
                    String adjCoords = board.getAdjCoords(edge, routeNode.data); //the coordinates of the adjacent tile at this edge
                    RouteNode adjRouteNode;
                    if(board.getPlacements().containsKey(adjCoords))
                    { //If a tile exists at this edge...

                        //Store it as a Route Node
                        adjRouteNode = new RouteNode(board.getTile(adjCoords).getEdge(board.getOppositeEdge(edge)), board.getTile(adjCoords));

                        if(!alreadyInArrayList(adjRouteNode, evaluated) && !alreadyInArrayList(adjRouteNode, unevaluated))
                        {//If the adjacent route node is not already in the evaluated or unevaluated lists
                            if(!routeNode.data.isOverPass())
                            { //if the adjacent route node is not an over pass
                                if(exit != '0' && exit == adjRouteNode.entry)
                                { //If the exit from the current route node is not blank and it is the same as the entry to the
                                    //adjacent route node

                                    //add it to the unevaluated list
                                    unevaluated.add(adjRouteNode);
                                }
                            }
                            else
                            { //The adjacent route node is an overpass
                                if(routeNode.entry == adjRouteNode.entry)
                                { //if the entry to the current route node is the same as the entry to the adjacent route node

                                    //add it to the unevaluated list
                                    unevaluated.add(adjRouteNode);
                                }
                            }
                        }
                        else
                        { //the adjacent route node is in either the evaluated or the unevaluated list
                            if(!routeNode.data.isOverPass() && adjRouteNode.data.isOverPass())
                            { //If the current route node is NOT an overpass and the adjacent route node IS an overpass

                                //Get the coordinates of the square on the other side of the adjacent overpass
                                adjCoords = board.getAdjCoords(edge, adjRouteNode.data);

                                if(board.getPlacements().containsKey(adjCoords))
                                { //If there is a placement on the other side of the overpass

                                    //store it as a new adjacent route node
                                    adjRouteNode = new RouteNode(board.getTile(adjCoords).getEdge(board.getOppositeEdge(edge)), board.getTile(adjCoords));

                                    if(!alreadyInArrayList(adjRouteNode, evaluated) && !alreadyInArrayList(adjRouteNode, unevaluated))
                                    {//If the tile on the other side of the overpass is not in the evaluated or unevaluated lists
                                        if(exit != '0' && exit == adjRouteNode.entry)
                                        { //if the exit from the current route node is not blank and it is the same as the entry
                                            //to the new adjacent route node

                                            //add it to the unevaluated list
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

    /**
     * This method checks whether a RouteNode is stored in either the evaluated
     * or unevaluated lists.
     * @param routeNode
     * @param arrayList
     * @return (boolean)
     */
    private boolean alreadyInArrayList(RouteNode routeNode, ArrayList<RouteNode> arrayList)
    {
        for(RouteNode evaluatedNode : arrayList)
        {
            if(evaluatedNode.data.getCoords().equals(routeNode.data.getCoords()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method calculates either the longest highway or longest railway by
     * iterating through the stored routes and getting the maximum value.
     * @param type
     * @return (int) Value of Longest Highway/Railway
     */
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

    /**
     * This method parses a given route and calculates the longest highway/railway
     * in that route.
     * @param route
     * @param type
     * @return
     */
    private int getLongestStretch(ArrayList<RouteNode> route, char type)
    {
        return 0;
    }

    /**
     * @return (int) The advanced score.
     */
    public int getAdvancedScore()
    {
        return score;
    }

    /**
     * @return (int) The basic score.
     */
    public int getBasicScore()
    {
        return (centerScore + networkScore) - errors;
    }

    /**
     * @return (int) The number of tiles in the center of the board.
     */
    public int getCenterScore()
    {
        return centerScore;
    }

    /**
     * @return (int) The network score.
     */
    public int getNetworkScore()
    {
        return networkScore;
    }

    /**
     * @return (int) The number of errors on the board
     */
    public int getErrors()
    {
        return errors;
    }

    /**
     * @return (int) The value of the longest highway on the board.
     */
    public int getLongestHighway()
    {
        return longestHighway;
    }

    /**
     * @return (int) The value of the longest railway on the board.
     */
    public int getLongestRailroad()
    {
        return longestRailroad;
    }

    /**
     * This class is a wrapper class used to evaluate nodes by the
     * getRoute() method. It stores an additional 'entry' value
     * which allows the method to keep track of which edge the route entered
     * the node from.
     */
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
}