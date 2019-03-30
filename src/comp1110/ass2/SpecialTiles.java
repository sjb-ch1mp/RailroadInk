package comp1110.ass2;

import javafx.scene.image.ImageView;
import java.util.HashMap;
import java.util.Map;

/**
 * The SpecialTiles class keeps track of the tile information in the special tiles sub-UI.
 * It stores Tile information for each of the Special Tiles in a given game and handles the rotation
 * of the ImageView objects on the board.
 */

public class SpecialTiles
{
    private HashMap<String, Tile> specialTiles; //tracks the tile data for the S tiles
    private int specialCounter; //keeps track of how many special tiles have been used this game

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
        specialTiles.put("S1", Tile.valueOf("S0"));
        specialTiles.put("S2", Tile.valueOf("S1"));
        specialTiles.put("S3", Tile.valueOf("S2"));
        specialTiles.put("S4", Tile.valueOf("S3"));
        specialTiles.put("S5", Tile.valueOf("S4"));
        specialTiles.put("S6", Tile.valueOf("S5"));

        specialCounter = 0;
    }

    public void rotateTiles()
    {
        /*
         * Rotates all tiles in the specialTiles[] array by looping through the array
         * and calling rotateTile() to update the edge positions. It will then update
         * the ImageView on the board using the Rotate object on the elements in the imageRef[] array.
         */
        for(Map.Entry<String, Tile> sTile : specialTiles.entrySet())
        {
            sTile.getValue().rotateTile();
        }
    }

    public ImageView getImage(String sTile)
    {
        //returns a reference to the ImageView in the UI for the special tile at the given index
        return specialTiles.get(sTile).getImage();
    }

    public Tile useSpecialTile(String sTile)
    {
        //use a special tile
        specialCounter++;
        specialTiles.get(sTile).useTile();
        return specialTiles.get(sTile);
    }

    public boolean isUsed(String sTile)
    {
        return specialTiles.get(sTile).isUsed();
    }

    public int getSpecialCounter()
    {
        return specialCounter;
    }
}
