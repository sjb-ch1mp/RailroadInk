package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The Board class keeps of all placements on the board in a HashMap for easy access (by coordinate).
 * It also stores the coordinates for the centre tiles and the exit tiles (along with their exit type)
 */

public class Board
{
    //This hashmap stores the coordinates of the tile, and the tile enum. This will make it easier to calculate the score
    HashMap<String, Tile> placements;
    //This ArrayList holds the centre coordinates. This will make it easier to calculate the score
    ArrayList<String> centerCoords;
    //This hashmap stores the coordinates of the exit tiles as well as which side needs to be which route,
    //e.g. "<A2, NR>" means at coordinate A2, the North side of the tile needs to be a Road.
    HashMap<String, String> exitCoords;

    //The fields are public so that they can be freely accessed by the ScoreCalculator

    public Board()
    {
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

    public boolean addTile(String placementString)
    {
        //Adds a tile to the board if the placement is legal. Returns false if the placement is invalid
        Tile newTile = Tile.valueOf(placementString.substring(0, 2));
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
            if(exitCoords.containsKey(newTile.getCoords()))
            { //tile has been placed on an exit tile
                placements.put(newTile.getCoords(), newTile);
                return true;
            }
            else
            { //tile has been placed elsewhere - check for at least one legal connection with adjacent tiles
                int legalConnection = 0;
                if(newTile.getRow() > 'A')
                { //check north
                    if(placements.containsKey(getAdjCoords('N', newTile)))
                    {
                        legalConnection++;
                    }
                }
                if(newTile.getColumn() < 7)
                { //check east
                    if(placements.containsKey(getAdjCoords('E', newTile)))
                    {
                        legalConnection++;
                    }
                }
                if(newTile.getRow() < 'G')
                { //check south
                    if(placements.containsKey(getAdjCoords('S', newTile)))
                    {
                        legalConnection++;
                    }
                }
                if(newTile.getColumn() > 0)
                { //check west
                    if(placements.containsKey(getAdjCoords('W', newTile)))
                    {
                        legalConnection++;
                    }
                }
                if(legalConnection > 0)
                {
                    placements.put(newTile.getCoords(), newTile);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean noIllegalConnections(Tile newTile)
    {
        for(Map.Entry<String, String> exitTile : exitCoords.entrySet())
        {
            if(exitTile.getKey().equals(newTile.getCoords()))
            {
                if(newTile.getEdge(exitTile.getValue().charAt(0)) != exitTile.getValue().charAt(1))
                {
                    return false;
                }
            }
        }

        String chkCoords;
        if(newTile.getRow() > 'A')
        { //check north
            chkCoords = getAdjCoords('N', newTile);
            if(placements.containsKey(chkCoords))
            { //if the adjacent tiles south edge connector does not equal the new tiles north edge connector, it is illegal
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
            { //if the adjacent tiles west edge connector does not equal the new tiles east edge connector, it is illegal
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
            { //if the adjacent tiles north edge connector does not equal the new tiles south edge connector, it is illegal
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
            { //if the adjacent tiles east edge connector does not equal the new tiles west edge connector, it is illegal
                if(edgesClash(chkCoords, newTile, 'W'))
                {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean edgesClash(String chkCoords, Tile newTile, char thisEdge)
    {
        if(newTile.getEdge(thisEdge) != 0)
        { //this edge is not 0
            if(placements.get(chkCoords).getEdge(getOppositeEdge(thisEdge)) != 0)
            { //the other edge is not 0
                if(placements.get(chkCoords).getEdge(getOppositeEdge(thisEdge)) != newTile.getEdge(thisEdge))
                { //and this edge != other edge
                    return true;
                }
            }
        }
        return false;
    }

    private char getOppositeEdge(char edge)
    {
        switch(edge)
        {
            case 'N': return 'S';
            case 'E': return 'W';
            case 'S': return 'N';
            default: return 'E';
        }
    }

    private String getAdjCoords(char edge, Tile newTile)
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

    public void clearBoard()
    {
        //clears the board of all tiles when a new game begins
        placements = new HashMap<>(0);
    }

    public Tile getTile(String coords)
    {
        return placements.get(coords);
    }

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
}
