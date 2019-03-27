package comp1110.ass2;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * The Dices class keeps track of the tile information in the Dices sub-UI
 * It stores Tile information for each of the Dices in the game and handles the rotation
 * of the ImageView objects on the board.
 */

public class Dices
{
    private Tile[] dices; //tracks the tile data for the A tiles and the B tile
    private ImageView[] imageRefs; //holds references to the ImageViews of each dice to make updating easier
    private int diceCounter; //keeps track of how many dice tiles have been used this turn. Refreshes on rollDice()
    private Rotate rotate; //a rotate object to change the orientation of the ImageViews

    public Dices()
    {
        /*
        * The constructor for the Dices object. Set up dices array, then get image URIs from
        * these elements for the ImageView references. The indices of dices[] and imageRefs[]
        * will always align to the same dices, i.e. the data for dice one is at dices[0] and
        * the ImageView reference for dice one is at imageRefs[0].
        * */
    }

    public void rollDice()
    {
        /*
        * Assigns an appropriate random tile to dices, refreshes diceCounter
        * and refreshes all images on the dices
        * */
    }

    public void rotateTiles()
    {
        /*
        * This method rotates all tiles in the dices[] array by looping through
        * the array and calling rotateTile() to update the position of the edges.
        * It will then update the images on the board using the Rotate object on elements
        * in the imageRefs() array.
        * */
    }

    public ImageView getImageRef(int idx)
    {
        //returns the reference to an ImageView object for the dice at the given index
    }

    public Tile getDice(int idx)
    {
        //return the Tile enum for a dice at the given index
    }
}
