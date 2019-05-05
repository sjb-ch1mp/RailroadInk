package comp1110.ass2.gui;

import comp1110.ass2.Tile;

import java.util.HashMap;
import java.util.Map;

/**
 * The SpecialTiles class keeps track of the tile information in the special tiles sub-UI.
 * It stores Tile information for each of the Special Tiles in a given game and handles the rotation
 * of the ImageView objects on the board.
 *
 * @author Samuel J. Brookes (unless indicated otherwise)
 */

public class SpecialTiles
{
    private HashMap<String, Tile> specialTiles; //tracks the tile data for the S tiles
    private int specialCounterGame; //keeps track of how many special tiles have been used this game
    private int specialCounterRound;

    public SpecialTiles()
    {
        /*
        * Set up specialTiles[] array, then get image URIs from these elements
        * for the ImageView references. The indices of specialTiles[] and
        * imageRefs[] will always align to the same special tiles, i.e. the data
        * for special one is at specialTiles[0] and the ImageView reference for
        * special one is at imageRefs[0].
        * */
        specialTiles = new HashMap<>(0);
        specialTiles.put("S1", new Tile("S0"));
        specialTiles.put("S2", new Tile("S1"));
        specialTiles.put("S3", new Tile("S2"));
        specialTiles.put("S4", new Tile("S3"));
        specialTiles.put("S5", new Tile("S4"));
        specialTiles.put("S6", new Tile("S5"));

        specialCounterGame = 0;
        specialCounterRound = 0;
    }

    /**
     * The rotateTiles() method rotates all Tiles in the specialTiles HashMap
     * by one, clock-wise. It does so by calling rotateTile() on each of
     * the tiles in the HashMap.
     */
    public void rotateTile(String tile)
    {
        specialTiles.get(tile).rotateTile();
    }

    /**
     * The useSpecialTile() method iterates the specialCounter field, calls the useTile() method
     * on the given special tile and then returns the Tile enum for the special tile being used.
     *
     * @param sTile (String) The identifier for the special tile being used (S1-S6).
     * @return (Tile) The tile representing the special tile being used.
     */
    public void useSpecialTile(String sTile)
    {
        specialCounterGame++;
        specialCounterRound++;
        specialTiles.get(sTile).useTile();
    }

    /**
     * Returns the counter that tracks the number of special tiles
     * used this round.
     * @return (int) specialCounterRound
     */
    public int getCounterRound()
    {
        return specialCounterRound;
    }

    /**
     * Returns the counter that tracks the number of special tiles
     * used this game.
     * @return (int) specialCounterGame
     */
    public int getCounterGame()
    {
        return specialCounterGame;
    }

    /**
     * This method resets the counter that tracks the number
     * of special tiles used this round.
     */
    public void resetCounterRound()
    {
        specialCounterRound = 0;
    }

    /**
     * The isUsed() method checks whether a special tile has already been used.
     *
     * @param sTile (String) The identifier for the special tile (S1-S6).
     * @return boolean
     */
    public boolean isUsed(String sTile)
    {
        return specialTiles.get(sTile).isUsed();
    }

    /**
     * This method returns the Tile object for the requested
     * special tile.
     * @param sTile (String) The identifier for the special tile (S1-S6).
     * @return Tile (Tile) The Tile object for the requested special tile
     */
    public Tile getSpecialTile(String sTile)
    {
        return specialTiles.get(sTile);
    }

    /**
     * This method deselects all special tile so that when the Special Tiles UI is
     * reloaded, there are no special tiles selected.
     */
    public void deselectAll()
    {
        specialTiles.get("S1").deselectTile();
        specialTiles.get("S2").deselectTile();
        specialTiles.get("S3").deselectTile();
        specialTiles.get("S4").deselectTile();
        specialTiles.get("S5").deselectTile();
        specialTiles.get("S6").deselectTile();
    }
}
