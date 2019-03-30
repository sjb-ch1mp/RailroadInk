package comp1110.ass2;

import javafx.scene.image.ImageView;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Dices class keeps track of the tile information in the Dices sub-UI
 * It stores Tile information for each of the Dices in the game and handles the rotation
 * of the ImageView objects on the board.
 */

public class Dices
{
    private HashMap<String, Tile> dices;
    private int diceCounter; //keeps track of how many dice tiles have been used this turn. Refreshes on rollDice()

    public Dices()
    {
        /*
        * The constructor for the Dices object. Set up dices array, then get image URIs from
        * these elements for the ImageView references. The indices of dices[] and imageRefs[]
        * will always align to the same dices, i.e. the data for dice one is at dices[0] and
        * the ImageView reference for dice one is at imageRefs[0].
        * */
        dices = new HashMap<>(0);
        dices.put("D1", Tile.valueOf("A0"));
        dices.put("D2", Tile.valueOf("A1"));
        dices.put("D3", Tile.valueOf("A2"));
        dices.put("D4", Tile.valueOf("B0"));

        diceCounter = 0;
    }

    public void rollDice()
    {
        /*
        * Assigns an appropriate random tile to dices, refreshes diceCounter
        * and refreshes all images on the dices
        * */
        Random rand = new Random();
        diceCounter = 0;
        dices = new HashMap<>(0);
        dices.put("D1", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D2", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D3", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D4", Tile.valueOf("B" + rand.nextInt(3)));
    }

    public void rotateTiles()
    {
        /*
        * This method rotates all tiles in the dices[] array by looping through
        * the array and calling rotateTile() to update the position of the edges.
        * */
        for(Map.Entry<String, Tile> dice : dices.entrySet())
        {
            dice.getValue().rotateTile();
        }
    }

    public ImageView getImage(String dice)
    {
        //returns the reference to an ImageView object for the dice at the given index
        return dices.get(dice).getImage();
    }

    public Tile useDice(String dice)
    {
        //return the Tile enum for a dice at the given index
        diceCounter++;
        dices.get(dice).useTile();
        return dices.get(dice);
    }

    public boolean isUsed(String dice)
    {
        return dices.get(dice).isUsed();
    }

    public int getDiceCounter()
    {
        return diceCounter;
    }
}
