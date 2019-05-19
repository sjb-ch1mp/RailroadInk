package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import comp1110.ass2.gui.SpecialTiles;

/**
 * The PlayerData class keeps all data related to a given player in a single object.
 * It holds a Board object, a Dices object and a SpecialTiles object for a single player.
 * The access for the data objects is public as they will only ever be accessed by the
 * Viewer class.
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */
public class PlayerData
{

    int player;
    public Board boardData;
    public Dices diceData;
    public SpecialTiles specialData;
    public ScoreCalculator scoreCalculator;

    public PlayerData(int player, Board boardData, Dices diceData, SpecialTiles specialData)
    {
        this.player = player;
        this.boardData = boardData;
        this.diceData = diceData;
        this.specialData = specialData;
    }

    /**
     * This method calculates the final score for the board when the game is finished.
     */
    public void calculateScore()
    {
        scoreCalculator = new ScoreCalculator(boardData);
    }
}
