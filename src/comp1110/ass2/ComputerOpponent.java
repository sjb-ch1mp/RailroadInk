package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import comp1110.ass2.gui.SpecialTiles;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.Map;

/**
 * The ComputerOpponent class will hold all player data for the computer opponent
 * and handle all the behaviour of the computer opponent.
 *
 * @author Samuel J. Brookes (unless indicated otherwise)
 */
public class ComputerOpponent
{

    public PlayerData playerData;

    public ComputerOpponent(PlayerData playerData)
    {
        this.playerData = playerData;
    }

    /**
     * The haveTurn() method will allow the computer opponent to have a turn.
     */
    public void haveTurn(Button btnContinue)
    {
        while(playerData.boardData.legalMovesRemaining(playerData.diceData))
        {
            HashMap<String, Integer> possibleMoves = new HashMap<>(0);

            //get the best possible dice move
            getDiceMoves(possibleMoves);

            //get the best possible special move
            if(playerData.specialData.getCounterRound() == 0 && playerData.specialData.getCounterGame() < 3)
            {
                getSpecialTilesMoves(possibleMoves);
            }

            //get the best move
            String bestMove = getBestMove(possibleMoves);

            //make the move
            Placement bestPlacement = new Placement(bestMove.substring(0, 5));
            String id = bestMove.substring(5);

            if(id.charAt(0) == 'S')
            { //move is a special tile
                playerData.specialData.useSpecialTile(id);
                playerData.boardData.addTile(bestPlacement.toString());
            }
            else
            { //move is a dice
                playerData.diceData.useDice(id);
                playerData.boardData.addTile(bestPlacement.toString());
            }

        }

        //at the end...
        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();

        btnContinue.setVisible(true);
    }

    private void getDiceMoves(HashMap<String, Integer> possibleMoves)
    {
        for(int i=1; i<=4; i++)
        {
            String id = "D" + i;
            if(!playerData.diceData.isUsed(id))
            {
                Tile dice = playerData.diceData.getDice(id);
                getLegalPlacements(possibleMoves, dice, id);
            }
        }
    }

    private void getSpecialTilesMoves(HashMap<String, Integer> possibleMoves)
    {
        for(int i=1; i<=6; i++)
        {
            String id = "S" + i;
            if(!playerData.specialData.isUsed(id))
            {
                Tile specialTile = playerData.specialData.getSpecialTile(id);
                getLegalPlacements(possibleMoves, specialTile, id);
            }
        }
    }

    private void getLegalPlacements(HashMap<String, Integer> possibleMoves, Tile tile, String id)
    {
        for(char row = 'A'; row <= 'G'; row++)
        {
            for(int column = 0; column < 7; column++)
            {

                for(int orientation = 0; orientation < 8; orientation++)
                {
                    tile.updateOrientation(orientation);
                    Board testBoard = buildTestBoard();
                    Placement placement = new Placement(tile);
                    placement.updateCoordinates("" + row + column);
                    if(testBoard.addTile(placement.toString()))
                    {
                        ScoreCalculator sc = new ScoreCalculator(testBoard);
                        possibleMoves.put(placement.toString() + id, sc.getAdvancedScore());
                    }
                }
            }
        }
    }

    private String getBestMove(HashMap<String, Integer> possibleMoves)
    {
        int max = Integer.MIN_VALUE;
        String bestMove = "";
        for(Map.Entry<String, Integer> mapEntry : possibleMoves.entrySet())
        {
            if(max < mapEntry.getValue())
            {
                max = mapEntry.getValue();
                bestMove = mapEntry.getKey();
            }
        }
        return bestMove;
    }

    private Board buildTestBoard()
    {
        Board testBoard = new Board();
        if(playerData.boardData.getPlacements().size() > 0)
        {
            for(Map.Entry<String, Tile> mapEntry : playerData.boardData.getPlacements().entrySet())
            {
                testBoard.getPlacements().put(mapEntry.getKey(), mapEntry.getValue());
            }
        }
        return testBoard;
    }

    private Dices buildTestDice()
    {
        Dices testDice = new Dices();
        testDice.copyPlayerOneData(playerData.diceData);
        return testDice;
    }

    private SpecialTiles buildTestSpecials()
    {
        SpecialTiles testSpecials = new SpecialTiles();
        for(int i=1; i<=4; i++)
        {
            String id = "S" + i;
            if(playerData.specialData.getSpecialTile(id).isUsed())
            {
                testSpecials.useSpecialTile(id);
            }
        }
        return testSpecials;
    }
}
