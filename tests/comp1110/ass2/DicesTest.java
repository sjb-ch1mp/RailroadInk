package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DicesTest
{
    @Test
    public void dicesUsedCorrectly()
    {
        /*
        * This tests whether the useDice() and isUsed() methods are functioning as expected.
        * */

        Dices dices = new Dices();

        for(int i=1; i<=4; i++)
        {
            dices.useDice("D" + i);
        }

        for(int i=1; i<4; i++)
        {
            assertTrue("Dice D" + i + " has not been used!", dices.isUsed("D" + i));
        }

        dices.rollDice();
        for(int i=1; i<=4; i++)
        {
            assertFalse("Dice D" + i + " is still used!", dices.isUsed("D" + i));
        }
    }

    @Test
    public void dicesCopyPlayerData()
    {
        /*
         * This tests whether the copyPlayerDices() method is functioning as expected.
         * */
        Dices dicesPlayerOne = new Dices();
        Dices dicesPlayerTwo = new Dices();

        for(int i=0; i<10; i++)
        {
            dicesPlayerOne.rollDice();
            dicesPlayerTwo.copyPlayerDices(dicesPlayerOne);
            assertTrue("Dices of player one (" + dicesPlayerOne.toString() + ") are not the same as the " +
                    "dices of player two (" + dicesPlayerTwo.toString() + ")", dicesPlayerOne.toString().equals(dicesPlayerTwo.toString()));
        }
    }

    @Test
    public void dicesSelectAndDeselect()
    {
        /*
        * This tests whether the deselectAll() method is working as expected.
        * */
        Dices dices = new Dices();

        for(int i=1; i<=4; i++)
        {
            dices.getDice("D" + i).selectTile();
            assertTrue("Dice D" + i + " is not selected!", dices.getDice("D" + i).isSelected());
        }

        dices.deselectAll();

        for(int i=1; i<=4; i++)
        {
            assertFalse("Dice D" + i + " is still selected!", dices.getDice("D" + i).isSelected());
        }

    }
}
