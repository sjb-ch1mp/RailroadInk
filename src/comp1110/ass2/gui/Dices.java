package comp1110.ass2.gui;

import comp1110.ass2.Tile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Dices class keeps track of the tile information in the Dices sub-UI
 * It stores Tile information for each of the Dices in the game and handles the rotation
 * of the ImageView objects on the board.
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */

public class Dices
{
    private HashMap<String, Tile> dices;
    private HashMap<String, ImageView> images;

    public Dices()
    {
        /*
        * The constructor for the Dices object. Set up dices array, then get image URIs from
        * these elements for the ImageView references. The indices of dices[] and imageRefs[]
        * will always align to the same dices, i.e. the data for dice one is at dices[0] and
        * the ImageView reference for dice one is at imageRefs[0].
        * */
        dices = new HashMap<>(0);
        dices.put("D1", new Tile("A0"));
        dices.put("D2", new Tile("A1"));
        dices.put("D3", new Tile("A2"));
        dices.put("D4", new Tile("B0"));
    }

    /**
     * This is an overloaded constructor that can build a Dices object from a diceRoll string
     * @param diceRoll
     */
    public Dices(String diceRoll)
    {
        dices = new HashMap<>(0);
        dices.put("D1", new Tile(diceRoll.substring(0, 2)));
        dices.put("D2", new Tile(diceRoll.substring(2, 4)));
        dices.put("D3", new Tile(diceRoll.substring(4, 6)));
        dices.put("D4", new Tile(diceRoll.substring(6)));
    }

    /**
     * The rollDice() method updates the Tiles in the dices HashMap with 3
     * randomly selected Tiles from the A type and one randomly selected
     * Tile from the B type. It also refreshes the diceCounter field.
     */
    public void rollDice()
    {
        Random rand = new Random();
        dices = new HashMap<>(0);
        dices.put("D1", new Tile("A" + rand.nextInt(6)));
        dices.put("D2", new Tile("A" + rand.nextInt(6)));
        dices.put("D3", new Tile("A" + rand.nextInt(6)));
        dices.put("D4", new Tile("B" + rand.nextInt(3)));
        images = new HashMap<>(0);
    }

    /**
     * The rotateDice() method rotates the given dice in the dices HashMap
     * by one, clock-wise. It does so by calling rotateTile() on that dice.
     */
    public void rotateDice(String dice)
    {
        dices.get(dice).rotateTile();
    }

    /**
     * The useDice() method iterates the diceCounter field, calls the useTile() method
     * on the given dice and then returns the Tile enum for the dice being used.
     *
     * @param dice (String) The identifier for the dice being used (D1, D2, D3 or D4).
     * @return (Tile) The tile representing the dice being used.
     */
    public void useDice(String dice)
    {
        //return the Tile enum for a dice at the given index
        dices.get(dice).useTile();
    }

    /**
     * The isUsed() method checks whether a dice has already been used.
     *
     * @param dice (String) The identifier for the dice (D1, D2, D3 or D4).
     * @return boolean
     */
    public boolean isUsed(String dice)
    {
        return dices.get(dice).isUsed();
    }

    public Tile getDice(String dice)
    {
        return dices.get(dice);
    }

    /**
     * The toString() method returns a String representation of the dices,
     * e.g. ("A0A1A2B3")
     *
     * @return (String) A String representation of the dices
     */
    @Override
    public String toString()
    {
        StringBuilder diceString = new StringBuilder();
        for(Map.Entry<String, Tile> dice : dices.entrySet())
        {
            diceString.append(dice.getValue().getId());
        }
        return diceString.toString();
    }

    /**
     * This method is used in multiplayer and computer opponent mode to
     * ensure that both players are using the same dices every round. It takes the
     * Dices object for player one and copies it's data to the Dices object
     * of the other player, or the computer opponent.
     * @param playerOneDiceData
     */
    public void copyPlayerOneData(Dices playerOneDiceData)
    {
        String diceId;
        for(int i=1; i<=4; i++)
        {
            diceId = "D" + i;
            Tile dice = new Tile(playerOneDiceData.getDice(diceId).getId());
            dice.updateOrientation(playerOneDiceData.getDice(diceId).getOrientation());
            dices.put(diceId, dice);
        }
    }

    /**
     * This method deselects all dices so that when the DicesUI is reloaded
     * there are no dice tiles selected.
     */
    public void deselectAll()
    {
        dices.get("D1").deselectTile();
        dices.get("D2").deselectTile();
        dices.get("D3").deselectTile();
        dices.get("D4").deselectTile();
    }

    /**
     * This method is a debugging method used for the BoardTest class. It forces
     * the dices to be all of a given route type.
     *
     * THIS METHOD IS NOT TO BE USED AT ANY TIME IN THE GAME!!
     * THIS METHOD IS NOT TO BE USED AT ANY TIME IN THE GAME!!
     * THIS METHOD IS NOT TO BE USED AT ANY TIME IN THE GAME!!
     * THIS METHOD IS NOT TO BE USED AT ANY TIME IN THE GAME!!
     *
     * @param type (char) the route type (R or H)
     */
    public void forceType(char type)
    {
        switch(type)
        {
            case 'R':
            {
                dices = new HashMap<>(0);
                dices.put("D1", new Tile("A1"));
                dices.put("D2", new Tile("A1"));
                dices.put("D3", new Tile("A1"));
                dices.put("D4", new Tile("A1"));
                break;
            }
            default:
            {
                dices = new HashMap<>(0);
                dices.put("D1", new Tile("A4"));
                dices.put("D2", new Tile("A4"));
                dices.put("D3", new Tile("A4"));
                dices.put("D4", new Tile("A4"));
            }
        }
    }
}
