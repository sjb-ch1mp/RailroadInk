package comp1110.ass2;

import java.util.ArrayList;
import java.util.HashMap;

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
        //Build centerCoords array
        //Build exitCoords HashMap
    }

    public boolean addTile(String placementString)
    {
        //Adds a tile to the board if the placement is legal. Returns false if the placement is invalid
    }

    public void clearBoard()
    {
        //clears the board of all tiles when a new game begins
    }

    @Override
    public String toString()
    {
        //Returns a String representation of all tile placements on the board
    }
}
