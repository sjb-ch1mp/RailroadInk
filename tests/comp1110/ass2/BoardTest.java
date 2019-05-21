package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest
{
    @Test
    public void legalMovesRemain()
    {
        /*
        * This tests the legalMovesRemaining() method in the Board class.
        * */

        Board board = new Board();
        Dices dices = new Dices();

        /*
        * There is a brand new Board object and a brand new Dices object. There must be legal moves.
        * */
        assertTrue("Fresh board and dices, but board returns no legal moves!", board.legalMovesRemaining(dices));

        /*
         * All of the dices are used. There must be no legal moves remaining.
         * */
        for(int i=1; i<=4; i++)
        {
            dices.getDice("D" + i).useTile();
        }
        assertFalse("All dices are used, but board says that there are moves remaining!", board.legalMovesRemaining(dices));

        /*
         * All of the route connections are roads and all of the dice connections are highways.
         * There must be no legal moves remaining.
         * */
        dices = new Dices();
        dices.forceType('H');
        board = new Board();
        board.addTile("B0A10", true);
        board.addTile("B0A50", true);
        board.addTile("B0D61", true);
        board.addTile("B0G52", true);
        board.addTile("B0G12", true);
        board.addTile("B0D03", true);
        assertFalse("All routes are roads and all dice are highways. Should be no legal moves!", board.legalMovesRemaining(dices));

        /*
         * All of the route connections are highways and all of the dice connections are roads.
         * There must be no legal moves remaining.
         * */
        dices = new Dices();
        dices.forceType('R');
        board = new Board();
        board.addTile("B0A32", true);
        board.addTile("B0B63", true);
        board.addTile("B0F63", true);
        board.addTile("B0G30", true);
        board.addTile("B0F01", true);
        board.addTile("B0B01", true);
        assertFalse("All routes are highways and all dice are roads. Should be no legal moves!", board.legalMovesRemaining(dices));
    }

}
