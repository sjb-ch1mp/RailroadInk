package comp1110.ass2;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The ComputerOpponent class holds the player data for the computer opponent
 * and handles all of the behaviour of the computer opponent.
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */
public class ComputerOpponent
{
    public PlayerData playerData;

    public ComputerOpponent(PlayerData playerData)
    {
        this.playerData = playerData;
    }

    /**
     * The haveTurn() method allows the computer opponent to have a turn.
     * This is a very greedy method that gets the set of highest scoring
     * placements at each iteration and then chooses a random 'highest scoring'
     * placement and puts it on the board.
     * It continues to do this until there are no more legal moves available.
     *
     * This method has the ability to report the move that it has taken this turn
     * for debugging and for TASK 10 (RailroadInk.generateMove())
     */
    public String haveTurn(Button btnContinue, boolean simple)
    {
        StringBuilder moveReport = new StringBuilder(); //holds each placement in order

        while(playerData.boardData.legalMovesRemaining(playerData.diceData))
        { //while there are still legal placements available

            HashMap<String, Integer> possibleMoves = new HashMap<>(0);

            //get the best possible dice moves
            getDiceMoves(possibleMoves);

            //IF NOT CREATING A SIMPLE MOVE - get the best possible special moves if appropriate
            if(!simple && playerData.specialData.getCounterRound() == 0 && playerData.specialData.getCounterGame() < 3)
            {
                getSpecialTilesMoves(possibleMoves);
            }

            //get the maximum score from all possible placements
            int maxScore = getMaxScore(possibleMoves);

            //then compile a list of possible moves that have the maximum score
            ArrayList<String> maxScoreMoves = new ArrayList<>(0);
            for(Map.Entry<String, Integer> mapEntry : possibleMoves.entrySet())
            {
                if(mapEntry.getValue() == maxScore)
                {
                    maxScoreMoves.add(mapEntry.getKey());
                }
            }

            //get a random best scoring move from this set
            Random rand = new Random();
            String bestMove = maxScoreMoves.get(rand.nextInt(maxScoreMoves.size()));

            //make the move
            Placement bestPlacement = new Placement(bestMove.substring(0, 5));
            String id = bestMove.substring(5);
            if(id.charAt(0) == 'S')
            { //move is a special tile
                playerData.specialData.useSpecialTile(id);
                playerData.boardData.addTile(bestPlacement.toString(), true);
            }
            else
            { //move is a dice
                playerData.diceData.useDice(id);
                playerData.boardData.addTile(bestPlacement.toString(), true);
            }

            //update the placement string
            moveReport.append(bestPlacement.toString());
        }

        //iterate the round counter and reset the special tile round counter
        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();

        //make the continue button visible
        if(btnContinue != null)
        {
            btnContinue.setVisible(true);
        }

        //return the placement string
        return moveReport.toString();
    }

    /**
     * This method gets all possible dice moves.
     * @param  possibleMoves (HashMap of possible moves)
     */
    private void getDiceMoves(HashMap<String, Integer> possibleMoves)
    {
        for(int i=1; i<=4; i++)
        { //for each dice
            String id = "D" + i;
            if(!playerData.diceData.isUsed(id))
            { //if the dice has not been used, get all legal placements for this dice
                Tile dice = playerData.diceData.getDice(id);
                getLegalPlacements(possibleMoves, dice, id);
            }
        }
    }

    /**
     * This method gets all possible special tile moves.
     * @param possibleMoves (HashMap of possible moves)
     */
    private void getSpecialTilesMoves(HashMap<String, Integer> possibleMoves)
    {
        for(int i=1; i<=6; i++)
        { //for each special tile
            String id = "S" + i;
            if(!playerData.specialData.isUsed(id))
            { //if the special tile has not been used, get all legal placements for this special tile
                Tile specialTile = playerData.specialData.getSpecialTile(id);
                getLegalPlacements(possibleMoves, specialTile, id);
            }
        }
    }

    /**
     * This method gets all legal placements for a given tile.
     * @param possibleMoves (HashMap of possible moves)
     * @param tile (Tile) the specific dice/special tile
     * @param id (String) the id of the tile
     */
    private void getLegalPlacements(HashMap<String, Integer> possibleMoves, Tile tile, String id)
    {
        for(char row = 'A'; row <= 'G'; row++)
        { //for each row
            for(int column = 0; column < 7; column++)
            { //for each column
                for(int orientation = 0; orientation < 8; orientation++)
                { //for each orientation
                    tile.updateOrientation(orientation);
                    tile.fixOrientation();
                    Board testBoard = buildTestBoard(playerData.boardData);
                    Placement placement = new Placement(tile);
                    placement.updateCoordinates("" + row + column);
                    if(testBoard.addTile(placement.toString(), true))
                    { //if this placement is legal
                        ScoreCalculator sc = new ScoreCalculator(testBoard); //calculate the score
                        possibleMoves.put(placement.toString() + id, sc.getAdvancedScore()); //and store it in the HashMap
                    }
                }
            }
        }
    }

    /**
     * This method calculates the maximum score achievable by the list of possible moves
     * @param possibleMoves (HashMap) All possible moves
     * @return Maximum score (int)
     */
    private int getMaxScore(HashMap<String, Integer> possibleMoves)
    {
        int max = Integer.MIN_VALUE;
        for(Map.Entry<String, Integer> mapEntry : possibleMoves.entrySet())
        {
            if(max < mapEntry.getValue())
            {
                max = mapEntry.getValue();
            }
        }
        return max;
    }

    /**
     * This method builds a test board so that tiles can be placed into a
     * board without affecting the actual game board. To avoid having to
     * place the tiles in a specific order, a while loop is used.
     *
     * @param board (Board) The actual game board
     * @return (Board) A copy of the game board
     */
    private Board buildTestBoard(Board board)
    {
        Board testBoard = new Board();

        while(testBoard.toString().length() != board.toString().length())
        { //while the number of placements in the test board are not the same as that in the actual game board
            for(Map.Entry<String, Tile> mapEntry : board.getPlacements().entrySet())
            { //add a tile
                testBoard.addTile(mapEntry.getValue().getPlacementString(), true);
            }
        }

        return testBoard;
    }
}
