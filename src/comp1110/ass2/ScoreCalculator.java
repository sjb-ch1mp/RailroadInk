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
 *
 *  @author Samuel J. Brookes (unless indicated otherwise)
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
                    if(routeNode.entryType ==
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
                    route = getRoute(exit, false);

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

        /*
         * Search for 'hanging' routes
         * These are routes that are not connected to any exit,
         * but exist due to a connection with an overpass.
         * While they do not contribute to the network score, they
         * need to be evaluated for the longest highway/railroad
         * score.
         * */
        for(Map.Entry<String, Tile> placement : board.getPlacements().entrySet())
        {//For each placement on the board
            boolean placementExists = false;
            for (ArrayList<RouteNode> route : routes)
            {//For each route
                for (RouteNode routeNode : route)
                {//For each routeNode
                    if (routeNode.data.getCoords().equals(placement.getKey()))
                    {
                        placementExists = true;
                        break;
                    }

                }
            }
            if (!placementExists)
            {//This placement is not included in any route - therefore it is part of a 'hanging' route
                ArrayList<RouteNode> hangingRoute = getRoute(placement.getKey(), true);
                if(hangingRoute != null)
                {
                    routes.add(hangingRoute);
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
    private ArrayList<RouteNode> getRoute(String startCoord, boolean freeHanging)
    {
        ArrayList<RouteNode> route = new ArrayList<>(0);
        ArrayList<RouteNode> evaluated = new ArrayList<>(0);
        ArrayList<RouteNode> unevaluated = new ArrayList<>(0);
        HashMap<String, RouteNode> overpasses = new HashMap<>(0);

        RouteNode startNode;
        if(!freeHanging)
        { //if this is not a free hanging tile
            //Store the data for the starting tile as a RouteNode
            startNode = new RouteNode(board.getExit(startCoord).charAt(0), board.getTile(startCoord));
        }
        else
        {
            char startEdge = getStartEdgeForHanging(startCoord);
            if(startEdge != 'x')
            {
                startNode = new RouteNode(startEdge, board.getTile(startCoord));
            }
            else
            {
                return null;
            }
        }

        //Add it to the unevaluated ArrayList
        unevaluated.add(startNode);

        //This loop will iterate until all legal connections from the start coordinate have been evaluated
        while(unevaluated.size() > 0)
        {
            //get the first unevaluated routeNode
            RouteNode routeNode = unevaluated.get(0);

            //Variables for adjacent nodes
            String adjCoords;
            RouteNode adjRouteNode;

            //This loop uses the directions HashMap, the tests HashMap and the
            //getTestValue() method in conjunction to achieve an iterable edge check

            if(routeNode.data.isOverPass())
            { //If the routeNode is an overpass, it needs only to check the opposite edge to the entry
                char oppositeEdge = board.getOppositeEdge(routeNode.entryEdge);
                adjCoords = board.getAdjCoords(oppositeEdge, routeNode.data);
                if(board.getPlacements().containsKey(adjCoords))
                { //If there is a placement on the opposite edge of the overpass
                    adjRouteNode = new RouteNode(routeNode.entryEdge, board.getTile(adjCoords));
                    if(!alreadyInArrayList(adjRouteNode, evaluated) && !alreadyInArrayList(adjRouteNode, unevaluated))
                    { //If the adjRouteNode is not already in evaluated or unevaluated
                        if(routeNode.entryType == adjRouteNode.entryType)
                        { //If this is a valid connection (do not need to check for '0' because tile is an overpass)
                            //(Also, can compare entry to entry because the exit is the same as the entry for overpasses)
                            if(adjRouteNode.data.isOverPass())
                            { //If the adjRouteNode is an overpass, add it to the appropriate list
                                addOverpass(oppositeEdge, adjRouteNode, overpasses, unevaluated);
                            }
                            else
                            { //adjRouteNode is not an overpass, add it to unevaluated
                                unevaluated.add(adjRouteNode);
                            }
                        }
                    }
                }
            }
            else
            { //The routeNode is not an overpass, check all non-blank edges that are not the entry or at the edge of the board
                for(int i=0; i<4; i++)
                {
                    if(routeNode.data.getEdge(directions.get(i)) != '0' &&      //If this edge is not blank
                            directions.get(i) != routeNode.entryEdge &&         //...and this is not the entry to the node
                            tests.get(i).test(routeNode.data.getTestValue(i)))  //...and the tile is not at the edge of the board
                    {

                        char exitEdge = directions.get(i); //the exit from the tile
                        char exitType = routeNode.data.getEdge(exitEdge); //the route type of the exit
                        char oppositeEdge = board.getOppositeEdge(exitEdge); //this is the edge opposite the exit edge (i.e. the entry to the adj tile)
                        adjCoords = board.getAdjCoords(exitEdge, routeNode.data); //the coordinates of the adjacent tile at this edge

                        if(board.getPlacements().containsKey(adjCoords))
                        { //If a tile exists at this edge...

                            //Store it as a Route Node
                            adjRouteNode = new RouteNode(oppositeEdge, board.getTile(adjCoords));

                            if(!alreadyInArrayList(adjRouteNode, evaluated) && !alreadyInArrayList(adjRouteNode, unevaluated))
                            {//If the adjacent route node is not already in the evaluated or unevaluated lists
                                if(exitType == adjRouteNode.entryType)
                                { //If the exit from the current node matches the entry to the adjacent node
                                    if(adjRouteNode.data.isOverPass())
                                    { //If the adjRouteNode is an overpass, add it to the appropriate list
                                        addOverpass(oppositeEdge, adjRouteNode, overpasses, unevaluated);
                                    }
                                    else
                                    { //adjRouteNode is not an overpass, add it to unevaluated
                                        unevaluated.add(adjRouteNode);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /*
            * Overpasses must be processed differently to other nodes
            * as they can be traversed twice. Only place them into evaluated
            * once they have been checked twice, otherwise put them into
            * overpasses.
            * */

            if(routeNode.data.isOverPass())
            { //if the routeNode is an overpass

                if(!routeNode.checked)
                {
                    routeNode.checked = true;
                    overpasses.put(routeNode.data.getCoords(), routeNode);
                    route.add(routeNode);
                }
                else
                {
                    evaluated.add(routeNode);
                }
            }
            else
            { //routeNode is not an overpass
                evaluated.add(routeNode);
                route.add(routeNode);
            }

            //Remove node from unevaluated
            unevaluated.remove(0);

        }

        return route;
    }

    /**
     * This method searches for a blank edge that can start as an
     * entry to a free hanging route node, in the same way that an entry
     * onto the board acts for a non-hanging node.
     *
     * @param coords
     * @return
     */
    private char getStartEdgeForHanging(String coords)
    {
        char startEdge = 'x';
        Tile hangingTile = board.getTile(coords);

        for(int i=0; i<4; i++)
        {
            if(tests.get(i).test(hangingTile.getTestValue(i)))
            {
                String adjCoords = board.getAdjCoords(directions.get(i), hangingTile);
                if(errorAtEdge(hangingTile, directions.get(i)))
                {
                    return directions.get(i);
                }
            }
        }

        return startEdge;
    }

    /**
     * This method checks whether an overpass route node has already been checked once (i.e. is
     * is the overpasses HashMap). If it has, it revives it, changes its fields and puts it back in
     * the unevaluated ArrayList. Otherwise, it simply adds it to the unevaluated ArrayList.
     *
     * @param newEntryEdge
     * @param adjRouteNode
     * @param overpasses
     * @param unevaluated
     */
    private void addOverpass(char newEntryEdge, RouteNode adjRouteNode, HashMap<String, RouteNode> overpasses, ArrayList<RouteNode> unevaluated)
    {
        if(overpasses.containsKey(adjRouteNode.data.getCoords()))
        { //If this overpass has already been checked once
            RouteNode revivedOverpass = overpasses.remove(adjRouteNode.data.getCoords()); //revive it by removing it from overpasses
            revivedOverpass.entryType = revivedOverpass.data.getEdge(newEntryEdge); //change the entry type to the new entry type
            revivedOverpass.entryEdge = newEntryEdge; //change the entry edge to the new entry edge
            unevaluated.add(revivedOverpass); //add it to unevaluated again
        }
        else
        { //overpass has not yet been checked
            unevaluated.add(adjRouteNode);
        }
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
        for(RouteNode listNode : arrayList)
        {
            if(listNode.data.getCoords().equals(routeNode.data.getCoords()))
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
        boolean checked;
        char entryEdge;
        char entryType;
        Tile data;

        RouteNode(char entryEdge, Tile data)
        {
            this.data = data;
            this.entryEdge = entryEdge;
            this.entryType = data.getEdge(entryEdge);
            checked = false;
        }
    }

    /**
     * This method returns the number of routes on the board
     * @return (int) number of routes on the board
     */
    public int getNumberOfRoutes()
    {
        /*
        * Need to include free hanging routes somehow
        * */
        return routes.size();
    }
}