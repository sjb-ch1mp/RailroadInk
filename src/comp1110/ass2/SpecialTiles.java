package comp1110.ass2;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * The SpecialTiles class keeps track of the tile information in the special tiles sub-UI.
 * It stores Tile information for each of the Special Tiles in a given game and handles the rotation
 * of the ImageView objects on the board.
 */

public class SpecialTiles
{
    private Tile[] specialTiles; //tracks the tile data for the S tiles
    private ImageView[] imageRefs; //holds references to the ImageViews of each special tile to make updating easier
    private int specialCounter; //keeps track of how many special tiles have been used this game
    private Rotate rotate; //A rotate object to change the orientation of the ImageViews

    public SpecialTiles()
    {
        /*
        * Set up specialTiles[] array, then get image URIs from these elements
        * for the ImageView references. The indices of specialTiles[] and
        * imageRefs[] will always align to the same special tiles, i.e. the data
        * for special one is at specialTiles[0] and the ImageView reference for
        * special one is at imageRefs[0].
        * */
    }

    public void refreshSpecials()
    {
        //returns all of the images to their original images. Called when new game starts.
    }

    public void rotateTiles()
    {
        /*
         * Rotates all tiles in the specialTiles[] array by looping through the array
         * and calling rotateTile() to update the edge positions. It will then update
         * the ImageView on the board using the Rotate object on the elements in the imageRef[] array.
         */
    }

    public ImageView getImageRef(int idx)
    {
        //returns a reference to the ImageView in the UI for the special tile at the given index
    }

    public Tile getSpecialTile(int idx)
    {
        //return a Tile enum for the special tile at the given index
    }
}
