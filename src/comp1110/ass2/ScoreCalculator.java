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
    private int longestHighway;
    private int longestRailroad;
    private int centerScore;
    private int networkScore;
    private int errors;

    public ScoreCalculator(Board board)
    {
        networkValues = new HashMap<>(0);
        for(int i=2; i<12; i++)
        {
            networkValues.put(i, 4*(i-1));
        }
        networkValues.put(12, 45);

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
                    if(tile.getEdge(board.getExit(tile.getCoords()).charAt(0)) ==
                        board.getExitCoords().get(tile.getCoords()).charAt(1))
                    { //this is a valid connection to an exit
                        exit++;
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
        ArrayList<ArrayList<Tile>> routes = new ArrayList<>(0);
        ArrayList<String> usedExits = new ArrayList<>(0);

        for(Map.Entry<String, String> mapEntry : board.getExitCoords().entrySet())
        {
            String exit = mapEntry.getKey();

            if(board.getPlacements().containsKey(exit) &&
                !usedExits.contains(exit))
            { //exit tile is part of a route that has not been evaluated

                ArrayList<Tile> route;

                //check if start point is valid exit connection
                if(board.getPlacements().get(exit).getEdge(mapEntry.getValue().charAt(0)) ==
                    mapEntry.getValue().charAt(1))
                { //exit connector == edge of tile
                    route = getRoute(exit);

                    //add evaluated exits to usedExits
                    for(Tile t : route)
                    {
                        if(board.isExitCoord(t.getCoords()))
                        {
                            usedExits.add(t.getCoords());
                        }
                    }

                    routes.add(route);
                }
            }

        }

        return routes;
    }

    private ArrayList<Tile> getRoute(String startCoord)
    {
        ArrayList<Tile> route = new ArrayList<>(0);
        ArrayList<String> evaluated = new ArrayList<>(0);
        ArrayList<RouteNode> unevaluated = new ArrayList<>(0);

        unevaluated.add(new RouteNode(board.getExit(startCoord).charAt(0),
                board.getPlacements().get(startCoord)));

        while(unevaluated.size() > 0)
        {
            RouteNode routeNode = unevaluated.get(unevaluated.size() - 1);
            //add all legally connecting adjacent routeNodes to unevaluated
            if(routeNode.data.getRow() > 'A')
            { //check North
                if(routeNode.isValidRouteConnection('N', evaluated))
                {
                    unevaluated.add(new RouteNode(board.getOppositeEdge('N'),
                            board.getPlacements().get(board.getAdjCoords('N',
                                    board.getTile(routeNode.data.getCoords())))));
                }
            }
            if(routeNode.data.getRow() < 'G')
            { //check South
                if(routeNode.isValidRouteConnection('S', evaluated))
                {
                    unevaluated.add(new RouteNode(board.getOppositeEdge('S'),
                            board.getPlacements().get(board.getAdjCoords('S',
                                    board.getTile(routeNode.data.getCoords())))));
                }
            }
            if(routeNode.data.getColumn() > 0)
            { //check West
                if(routeNode.isValidRouteConnection('W', evaluated))
                {
                    unevaluated.add(new RouteNode(board.getOppositeEdge('W'),
                            board.getPlacements().get(board.getAdjCoords('W',
                                    board.getTile(routeNode.data.getCoords())))));
                }
            }
            if(routeNode.data.getColumn() < 6)
            { //check East
                if(routeNode.isValidRouteConnection('E', evaluated))
                {
                    unevaluated.add(new RouteNode(board.getOppositeEdge('E'),
                            board.getPlacements().get(board.getAdjCoords('E',
                                    board.getTile(routeNode.data.getCoords())))));
                }
            }

            //add current routeNode to evaluated
            evaluated.add(routeNode.data.getCoords());

            //remove current routeNode from unevaluated
            unevaluated.remove(routeNode);

            //add Tile from routeNode to route
            route.add(routeNode.data);
        }

        return route;
    }

    private int calculateLongestRoute(char type)
    {
        //put type (either 'R' or 'H') into the conditional statement that checks for a connection between tiles.
        //this will ensure that either the longest Railway is calculated, or the longest Highway
        int max = Integer.MIN_VALUE;

        for(ArrayList<Tile> route : routes)
        {
            int longestStretch = getLongestStretch(route, type);
            if(max < longestStretch)
            {
                max = longestStretch;
            }
        }

        return max;
    }

    private int getLongestStretch(ArrayList<Tile> route, char type)
    {
        ArrayList<Integer> stretches = new ArrayList<>(0);
        ArrayList<String> evaluated = new ArrayList<>(0);
        ArrayList<LengthNode> unevaluated = new ArrayList<>(0);

        //get starting coordinates (the first exit)
        String startCoords = "";
        for(Tile tile : route)
        {
            if(board.isExitCoord(tile.getCoords()))
            {
                startCoords = tile.getCoords();
                break;
            }
        }

        unevaluated.add(new LengthNode(0, type, board.getTile(startCoords)));

        while(unevaluated.size() > 0)
        {
            LengthNode lengthNode = unevaluated.get(unevaluated.size() - 1);

            if(lengthNode.data.getRow() > 'A')
            { //check North
                lengthNode.addIfValid('N', evaluated, unevaluated);
            }

            if(lengthNode.data.getRow() < 'G')
            { //check South
                lengthNode.addIfValid('S', evaluated, unevaluated);
            }

            if(lengthNode.data.getColumn() > 0)
            { //check West
                lengthNode.addIfValid('W', evaluated, unevaluated);
            }

            if(lengthNode.data.getColumn() < 6)
            { //check East
                lengthNode.addIfValid('E', evaluated, unevaluated);
            }

            //add current node to evaluated
            evaluated.add(lengthNode.data.getCoords());

            //remove current node from unevaluated
            unevaluated.remove(lengthNode);

            //add cumulative total to stretches
            stretches.add(lengthNode.cumulativeTotal);
        }

        //get the largest cumulative total after all nodes have been evaluated
        int max = Integer.MIN_VALUE;
        for(Integer stretch : stretches)
        {
            if(max < stretch)
            {
                max = stretch;
            }
        }

        return max;
    }

    public int getScore()
    {
        /* ============================= debug*/System.out.println("Network Score: " + networkScore);
        /* ============================= debug*/System.out.println("Center Score: " + centerScore);
        /* ============================= debug*/System.out.println("Errors: " + errors);
        /* ============================= debug*/System.out.println("Longest Highway: " + longestHighway);
        /* ============================= debug*/System.out.println("Longest Railroad: " + longestRailroad);

        return score;
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
            this.entry = data.getEdge(entry);
        }

        boolean isValidExit(char otherEdge)
        {
            if(otherEdge != '0')
            {
                if(data.getRouteType() == 'S')
                {
                    return true;
                }
                else
                {
                    if(entry == otherEdge)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean isValidRouteConnection(char edge, ArrayList<String> evaluated)
        {
            //get the coordinates that are adjacent to the given edge
            String adjCoords = board.getAdjCoords(edge, board.getPlacements().get(data.getCoords()));

            if(board.getPlacements().containsKey(adjCoords))
            { //if there is a tile placed at these coordinates
                if(!evaluated.contains(adjCoords))
                { //if the tile has not yet been evaluated
                    if(isValidExit(board.getTile(adjCoords).getEdge(board.getOppositeEdge(edge))))
                    { //if the connection is a valid exit. Stations can do both H and R.
                        // For Overpasses or Normal the exit must be the same as the entry
                        if(RailroadInk.areConnectedNeighbours(data.getPlacementString(),
                                board.getTile(adjCoords).getPlacementString()))
                        { //the RouteNodes are connected
                            return true;
                        }
                    }
                }
            }
            return false;
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

        void addIfValid(char edge, ArrayList<String> evaluated, ArrayList<LengthNode> unevaluated)
        {
            String adjCoords = board.getAdjCoords(edge, data);
            if(board.getPlacements().containsKey(adjCoords))
            { // board contains a tile at these coordinates
                if(!evaluated.contains(adjCoords))
                { //tile has not already been evaluated
                    if(RailroadInk.areConnectedNeighbours(board.getTile(adjCoords).getPlacementString(), data.getPlacementString()))
                    { //tiles are legally connected
                        if(alreadyInUnevaluated(unevaluated, adjCoords))
                        {
                            updateUnevaluatedNode(unevaluated, adjCoords);
                        }
                        else if(data.getEdge('E') == entry) //entry = type in super class
                        {
                            unevaluated.add(new LengthNode(cumulativeTotal + 1, entry, board.getTile(adjCoords)));
                        }
                        else
                        {
                            unevaluated.add(new LengthNode(0, entry, board.getTile(adjCoords)));
                        }
                    }
                }
            }
        }

        boolean alreadyInUnevaluated(ArrayList<LengthNode> unevaluated, String adjCoords)
        {
            for(LengthNode node : unevaluated)
            {
                if(node.data.getCoords().equals(adjCoords))
                {
                    return true;
                }
            }
            return false;
        }

        void updateUnevaluatedNode(ArrayList<LengthNode> unevaluated, String adjCoords)
        {
            for(LengthNode node : unevaluated)
            {
                if(node.data.getCoords().equals(adjCoords))
                {
                    node.updateTotal(cumulativeTotal + 1);
                    break;
                }
            }
        }
    }
}
