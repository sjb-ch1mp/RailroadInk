package comp1110.ass2;

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

    /**
     * The rollDice() method updates the Tiles in the dices HashMap with 3
     * randomly selected Tiles from the A type and one randomly selected
     * Tile from the B type. It also refreshes the diceCounter field.
     */
    public void rollDice()
    {
        Random rand = new Random();
        diceCounter = 0;
        dices = new HashMap<>(0);
        dices.put("D1", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D2", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D3", Tile.valueOf("A" + rand.nextInt(6)));
        dices.put("D4", Tile.valueOf("B" + rand.nextInt(3)));
    }

    /**
     * The rotateTiles() method rotates all Tiles in the dices HashMap
     * by one, clock-wise. It does so by calling rotateTile() on each of
     * the tiles in the HashMap.
     */
    public void rotateTiles()
    {
        for(Map.Entry<String, Tile> dice : dices.entrySet())
        {
            dice.getValue().rotateTile();
        }
    }

    /**
     * The useDice() method iterates the diceCounter field, calls the useTile() method
     * on the given dice and then returns the Tile enum for the dice being used.
     *
     * @param dice (String) The identifier for the dice being used (D1, D2, D3 or D4).
     * @return (Tile) The tile representing the dice being used.
     */
    public Tile useDice(String dice)
    {
        //return the Tile enum for a dice at the given index
        diceCounter++;
        dices.get(dice).useTile();
        return dices.get(dice);
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

    public int getDiceCounter()
    {
        return diceCounter;
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
}
