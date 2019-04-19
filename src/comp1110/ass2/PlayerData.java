package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import comp1110.ass2.gui.SpecialTiles;

public class PlayerData
{
    int player;
    public Board boardData;
    public Dices diceData;
    public SpecialTiles specialData;

    public PlayerData(int player, Board boardData, Dices diceData, SpecialTiles specialData)
    {
        this.player = player;
        this.boardData = boardData;
        this.diceData = diceData;
        this.specialData = specialData;
    }
}
