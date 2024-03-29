package comp1110.ass2;

import comp1110.ass2.gui.Dices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Board class keeps of all placements on the board in a HashMap for easy access (by coordinate).
 * It also stores the coordinates for the centre tiles and the exit tiles (along with their exit type)
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */

public class Board
{
    private //This hashmap stores the coordinates of the tile, and the tile enum. This will make it easier to calculate the score
    HashMap<String, Tile> placements;
    //This ArrayList holds the centre coordinates. This will make it easier to calculate the score
    private ArrayList<String> centerCoords;
    //This hashmap stores the coordinates of the exit tiles as well as which side needs to be which route,
    //e.g. "<A2, NR>" means at coordinate A2, the North side of the tile needs to be a Road.
    private HashMap<String, String> exitCoords;
    private int roundCounter;

    //The fields are public so that they can be freely accessed by the ScoreCalculator

    /**
     * This is the basic constructor that builds an empty board
     */
    public Board()
    {
        initialise();
    }

    /**
     * This is an overloaded constructor that builds a board
     * from a boardString.
     * @param boardString
     */
    public Board(String boardString)
    {
        initialise();
        while(this.toString().length() != boardString.length())
        {
            for(int i=0; i<boardString.length(); i+=5)
            {
                this.addTile(boardString.substring(i, i+5), true);
            }
        }
    }

    /**
     * This method initialises all fields in a Board object. This
     * was created to clean up the overloaded constructors.
     */
    private void initialise()
    {
        roundCounter = 1;
        placements = new HashMap<>(0);

        //center coordinates
        centerCoords = new ArrayList<>(0);
        centerCoords.add("C2");
        centerCoords.add("C3");
        centerCoords.add("C4");
        centerCoords.add("D2");
        centerCoords.add("D3");
        centerCoords.add("D4");
        centerCoords.add("E2");
        centerCoords.add("E3");
        centerCoords.add("E4");

        //Build exitCoords HashMap
        exitCoords = new HashMap<>(0);
        exitCoords.put("A1", "NH");
        exitCoords.put("A3", "NR");
        exitCoords.put("A5", "NH");
        exitCoords.put("B6", "ER");
        exitCoords.put("D6", "EH");
        exitCoords.put("F6", "ER");
        exitCoords.put("G5", "SH");
        exitCoords.put("G3", "SR");
        exitCoords.put("G1", "SH");
        exitCoords.put("F0", "WR");
        exitCoords.put("D0", "WH");
        exitCoords.put("B0", "WR");
    }

    /**
     * The addTile() method takes a placement string and applies the placement
     * rules of the Railroad Ink game. It first checks whether there are any
     * illegal connections, then it checks whether there is a legal exit connection,
     * and finally it checks for at least one valid connection to an adjacent tile.
     * If there is an illegal connection OR there is no legal connections to an
     * exit or adjacent tile, the tile is not placed and the method returns false.
     * Otherwise, the tile is placed and the method returns true.
     *
     * @param placementString
     * @return boolean
     */
    public boolean addTile(String placementString, boolean placeOnBoard)
    {
        //Adds a tile to the board if the placement is legal. Returns false if the placement is invalid
        Tile newTile = new Tile(placementString.substring(0, 2));
        newTile.updateOrientation(Integer.parseInt(placementString.substring(4)));
        newTile.addCoordinates(placementString.substring(2, 4));

        //check a tile isn't already on that space
        if(placements.containsKey(newTile.getCoords()))
        {
            return false;
        }

        if(noIllegalConnections(newTile))
        { //if there are no ILLEGAL connections

            //check for a legal edge connection
            int legalConnection = 0;
            if(exitCoords.containsKey(newTile.getCoords()))
            { //tile has been placed on an exit tile
                if(newTile.getEdge(exitCoords.get(newTile.getCoords()).charAt(0)) == exitCoords.get(newTile.getCoords()).charAt(1))
                {
                    legalConnection++;
                }
            }

            String adjCoords;
            if(newTile.getRow() > 'A')
            { //check north
                adjCoords = getAdjCoords('N', newTile);
                if(placements.containsKey(adjCoords))
                {
                    if(newTile.getEdge('N') != '0' &&
                            placements.get(adjCoords).getEdge(getOppositeEdge('N')) != '0' &&
                            newTile.getEdge('N') == placements.get(adjCoords).getEdge(getOppositeEdge('N')))
                    {
                        legalConnection++;
                    }
                }
            }
            if(newTile.getColumn() < 6)
            { //check east
                adjCoords = getAdjCoords('E', newTile);
                if(placements.containsKey(adjCoords))
                {
                    if(newTile.getEdge('E') != '0' &&
                            placements.get(adjCoords).getEdge(getOppositeEdge('E')) != '0' &&
                            newTile.getEdge('E') == placements.get(adjCoords).getEdge(getOppositeEdge('E')))
                    {
                        legalConnection++;
                    }
                }
            }
            if(newTile.getRow() < 'G')
            { //check south
                adjCoords = getAdjCoords('S', newTile);
                if(placements.containsKey(adjCoords))
                {
                    if(newTile.getEdge('S') != '0' &&
                            placements.get(adjCoords).getEdge(getOppositeEdge('S')) != '0' &&
                            newTile.getEdge('S') == placements.get(adjCoords).getEdge(getOppositeEdge('S')))
                    {
                        legalConnection++;
                    }
                }
            }
            if(newTile.getColumn() > 0)
            { //check west
                adjCoords = getAdjCoords('W', newTile);
                if(placements.containsKey(adjCoords))
                {
                    if(newTile.getEdge('W') != '0' &&
                            placements.get(adjCoords).getEdge(getOppositeEdge('W')) != '0' &&
                            newTile.getEdge('W') == placements.get(adjCoords).getEdge(getOppositeEdge('W')))
                    {
                        legalConnection++;
                    }
                }
            }
            if(legalConnection > 0)
            { //there is at least one legal connection to an adjacent tile
                if(placeOnBoard)
                {
                    placements.put(newTile.getCoords(), newTile);
                }
                return true;
            }
        }

        //if there is an illegal connection OR there are no legal connections
        //to an exit or an adjacent tile, return false

        return false;
    }

    /**
     * The noIllegalConnections() method takes a Tile from the addTile() method
     * and checks whether there is an illegal connection with an exit or an
     * adjacent tile. An connection is illegal if the both of the meeting edges
     * are not blank (i.e. Tile.getEdge() == '0') and they are not the same.
     *
     * @param newTile
     * @return boolean
     */
    private boolean noIllegalConnections(Tile newTile)
    {
        //check for illegal connections to an exit
        for(Map.Entry<String, String> exitTile : exitCoords.entrySet())
        {
            if(exitTile.getKey().equals(newTile.getCoords()))
            {
                if(newTile.getEdge(exitTile.getValue().charAt(0)) != '0' &&
                        newTile.getEdge(exitTile.getValue().charAt(0)) != exitTile.getValue().charAt(1))
                {
                    return false;
                }
            }
        }

        //check for illegal connections to an adjacent tile
        String chkCoords;
        if(newTile.getRow() > 'A')
        { //check north
            chkCoords = getAdjCoords('N', newTile);
            if(placements.containsKey(chkCoords))
            {
                if(edgesClash(chkCoords, newTile, 'N'))
                {
                    return false;
                }
            }
        }
        if(newTile.getColumn() < 7)
        { //check east
            chkCoords = getAdjCoords('E', newTile);
            if(placements.containsKey(chkCoords))
            {
                if(edgesClash(chkCoords, newTile, 'E'))
                {
                    return false;
                }
            }
        }
        if(newTile.getRow() < 'G')
        { //check south
            chkCoords = getAdjCoords('S', newTile);
            if(placements.containsKey(chkCoords))
            {
                if(edgesClash(chkCoords, newTile, 'S'))
                {
                    return false;
                }
            }
        }
        if(newTile.getColumn() > 0)
        { //check west
            chkCoords = getAdjCoords('W', newTile);
            if(placements.containsKey(chkCoords))
            {
                if(edgesClash(chkCoords, newTile, 'W'))
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * The edgesClash() method takes the coordinates of an adjacent tile and checks
     * whether there is a 'clash' between the two. There is a clash if both of the edges
     * that meet are not empty and they are not the same.
     *
     * @param chkCoords (String) Coordinate of adjacent tile.
     * @param newTile (Tile) The tile currently being placed.
     * @param thisEdge (char) The edge of the tile being checked.
     * @return boolean
     */
    private boolean edgesClash(String chkCoords, Tile newTile, char thisEdge)
    {
        if(newTile.getEdge(thisEdge) != '0')
        { //this edge is not 0
            if(placements.get(chkCoords).getEdge(getOppositeEdge(thisEdge)) != '0')
            { //the other edge is not 0
                if(placements.get(chkCoords).getEdge(getOppositeEdge(thisEdge)) != newTile.getEdge(thisEdge))
                { //and this edge != other edge
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The getOppositeEdge() method takes an edge from the Tile being placed
     * and returns the edge of an adjacent tile that needs to be checked for a
     * clash.
     *
     * @param edge (char) The edge of the Tile being placed.
     * @return The edge opposite to the edge parameter (char)
     */
    public char getOppositeEdge(char edge)
    {
        switch(edge)
        {
            case 'N': return 'S';
            case 'E': return 'W';
            case 'S': return 'N';
            default: return 'E';
        }
    }

    /**
     * The getAdjCoords() method returns the coordinates of an adjacent tile at
     * the given edge of the tile being placed.
     *
     * @param edge (char) The side of the tile for which the coordinates are required.
     * @param newTile (Tile) The tile being placed.
     * @return The coordinates of the adjacent tile at the given edge (String.
     */
    public String getAdjCoords(char edge, Tile newTile)
    {
        StringBuilder chkCoords = new StringBuilder();
        if(edge == 'N')
        {
            chkCoords.append((char)(newTile.getRow() - 1));
            chkCoords.append(newTile.getColumn());
        }
        else if(edge == 'E')
        {
            chkCoords.append(newTile.getRow());
            chkCoords.append(newTile.getColumn() + 1);
        }
        else if(edge == 'S')
        {
            chkCoords.append((char)(newTile.getRow() + 1));
            chkCoords.append(newTile.getColumn());
        }
        else if(edge == 'W')
        {
            chkCoords.append(newTile.getRow());
            chkCoords.append(newTile.getColumn() - 1);
        }

        return chkCoords.toString();
    }

    /**
     * The clearBoard() method clears all placements on the board
     * by re-initialising the placements HashMap.
     */
    public void clearBoard()
    {
        //clears the board of all tiles when a new game begins
        placements = new HashMap<>(0);
    }

    /**
     * The getTile() method returns a Tile at the given coordinates.
     *
     * @param coords (String) The coordinates of the required tile.
     * @return (Tile) The tile at the given coordinates.
     */
    public Tile getTile(String coords)
    {
        return placements.get(coords);
    }

    /**
     * The toString() method returns a String representation of all placements on the board.
     *
     * @return (String) Returns a string representation of all placements on the board.
     */
    @Override
    public String toString()
    {
        //Returns a String representation of all tile placements on the board
        if(placements.size() == 0)
        {
            return "";
        }

        StringBuilder boardString = new StringBuilder();
        for(Map.Entry<String, Tile> placement : placements.entrySet())
        {
            boardString.append(placement.getValue().getId());
            boardString.append(placement.getKey());
            boardString.append(placement.getValue().getOrientation());
        }
        return boardString.toString();
    }

    /**
     * The isCenterCoord() method takes coordinates and returns a boolean
     * based on whether or not they are in the 'center' region of the board.
     *
     * @param coord (String) The coordinates of a given tile
     * @return boolean
     */
    public boolean isCenterCoord(String coord)
    {
        for(String center : centerCoords)
        {
            if(center.equals(coord))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a boolean value depending upon whether
     * the coordinates are an exit or not.
     * @param coord
     * @return boolean
     */
    public boolean isExitCoord(String coord)
    {
        return exitCoords.containsKey(coord);
    }

    /**
     * This method returns the exit information about a given exit. Exit
     * information is represented by a digraph, e.g. 'NH' says that the
     * North edge of the tile placed here must be a Highway.
     * @param coord
     * @return (String) Exit information
     */
    public String getExit(String coord)
    {
        return exitCoords.get(coord);
    }

    /**
     * Returns the placements HashMap
     * @return (HashMap) placements
     */
    public HashMap<String, Tile> getPlacements()
    {
        return placements;
    }

    /**
     * This method iterates the round counter by one.
     */
    public void iterateRoundCounter()
    {
        if(roundCounter < 7)
        {
            roundCounter++;
        }
    }

    /**
     * @return (int) The round counter.
     */
    public int getRound()
    {
        return roundCounter;
    }

    /**
     * This method checks whether there are any legal moves remaining on the
     * board and returns a boolean.
     * @param diceData
     * @return (boolean)
     */
    public boolean legalMovesRemaining(Dices diceData)
    {
        for(int i=1; i<=4; i++)
        {
            String diceID = "D" + i;
            if(!diceData.getDice(diceID).isUsed())
            {
                Tile dice = new Tile(diceData.getDice(diceID).getId());
                //for all orientations - if testBoard.addTile() returns true, there is a legal placement remaining
                for(int j=0; j<8; j++)
                {
                    dice.updateOrientation(j);
                    for(char y='A'; y<='G'; y++)
                    {
                        for(int x=0; x<7; x++)
                        {
                            dice.addCoordinates(y + "" + x);
                            if(this.addTile(dice.getPlacementString(), false))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return (HashMap) The exit coordinates hash map.
     */
    public HashMap<String, String> getExitCoords()
    {
        return exitCoords;
    }
}
