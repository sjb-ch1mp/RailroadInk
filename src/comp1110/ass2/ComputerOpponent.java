package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import comp1110.ass2.gui.SpecialTiles;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The ComputerOpponent class will hold all player data for the computer opponent
 * and handle all the behaviour of the computer opponent.
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */
public class ComputerOpponent
{
    private Iterator iterator;
    public PlayerData playerData;

    public ComputerOpponent(PlayerData playerData)
    {
        iterator = new Iterator();
        this.playerData = playerData;
    }

    /**
     * The haveTurn() method will allow the computer opponent to have a turn.
     */
    public String haveTurn(Button btnContinue)
    {
        StringBuilder moveReport = new StringBuilder();

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
                playerData.boardData.addTile(bestPlacement.toString(), true);
            }
            else
            { //move is a dice
                playerData.diceData.useDice(id);
                playerData.boardData.addTile(bestPlacement.toString(), true);
            }

            moveReport.append(bestPlacement.toString());

        }

        //at the end...
        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();

        btnContinue.setVisible(true);

        return moveReport.toString();
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
                    tile.fixOrientation();
                    Board testBoard = buildTestBoard(playerData.boardData);
                    Placement placement = new Placement(tile);
                    placement.updateCoordinates("" + row + column);
                    if(testBoard.addTile(placement.toString(), true))
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

    private Board buildTestBoard(Board board)
    {
        Board testBoard = new Board();
        Board actualBoard = board;

        while(testBoard.toString().length() != actualBoard.toString().length())
        {
            for(Map.Entry<String, Tile> mapEntry : actualBoard.getPlacements().entrySet())
            {
                testBoard.addTile(mapEntry.getValue().getPlacementString(), true);
            }
        }

        return testBoard;
    }

    public String haveAdvancedTurn(Button btnContinue)
    {
        StringBuilder moveReport = new StringBuilder();
        HashMap<String, String> freeExits;
        HashMap<String, String> freeEdges;

        while(playerData.boardData.legalMovesRemaining(playerData.diceData) && !playerData.diceData.allDicesUsed())
        {
            String placement;
            String tileId;

            //Separate into exits and routes
            if(playerData.boardData.getPlacements().isEmpty())
            { //this is the first turn - place one dice
                //get a first move

                String firstMove = getRandomExitConnection(playerData.boardData.getExitCoords());
                placement = firstMove.substring(0, 5);
                tileId = firstMove.substring(5);

            }
            else
            { //tiles are on the board

                //get all free exits
                freeExits = new HashMap<>(0);
                getFreeExits(freeExits);

                //get all free edges
                freeEdges = new HashMap<>(0);
                getFreeEdges(freeEdges);

                //attempt to make each placement on a free edge
                String randomMove;
                if(freeEdges.size() > 0)
                {
                    randomMove = getRandomEdgeConnection(freeEdges);
                    if(randomMove.equals(""))
                    {//if no placements can be made to a route - attempt to connect to an exit
                        randomMove = getRandomExitConnection(freeExits);
                    }
                }
                else
                {
                    randomMove = getRandomExitConnection(freeExits);
                }

                placement = randomMove.substring(0, 5);
                tileId = randomMove.substring(5);
            }

            //add random placement to board, use dice, add to moveReport
            playerData.boardData.addTile(placement, true);
            if(tileId.charAt(0) == 'D')
            {
                playerData.diceData.useDice(tileId);
            }
            else
            {
                playerData.specialData.useSpecialTile(tileId);
            }
            moveReport.append(placement);

        }

        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();
        btnContinue.setVisible(true);
        return moveReport.toString();
    }

    private String getRandomExitConnection(HashMap<String, String> freeExits)
    {
        //get all possible placements
        ArrayList<PossiblePlacement> possiblePlacements = getPossiblePlacements(freeExits);

        //return a randomly selected legal placement
        Random rand = new Random();
        PossiblePlacement randomPlacement = possiblePlacements.get(rand.nextInt(possiblePlacements.size()));
        return randomPlacement.toString() + randomPlacement.id;
    }

    private String getRandomEdgeConnection(HashMap<String, String> freeEdges)
    {
        //get all the possible placements
        ArrayList<PossiblePlacement> possiblePlacements = getPossiblePlacements(freeEdges);

        if(possiblePlacements.size() > 1)
        {
            //filter placements by score
            possiblePlacements = filterByScore(possiblePlacements);
            if(possiblePlacements.size() > 1)
            {
                //return a random placement
                Random rand = new Random();
                PossiblePlacement randomPlacement = possiblePlacements.get(rand.nextInt(possiblePlacements.size()));
                return randomPlacement.toString() + randomPlacement.id;
            }
            else
            {
                return possiblePlacements.get(0).toString() + possiblePlacements.get(0).id;
            }
        }
        else if(possiblePlacements.size() == 1)
        {
            return possiblePlacements.get(0).toString() + possiblePlacements.get(0).id;
        }
        return "";
    }

    private ArrayList<PossiblePlacement> getPossiblePlacements(HashMap<String, String> connections)
    {
        ArrayList<PossiblePlacement> possiblePlacements = new ArrayList<>(0);

        for(int i=1; i<=4; i++)
        {
            String id = "D" + i;
            if(!playerData.diceData.getDice(id).isUsed())
            { //if the dice has not been used
                Tile dice = playerData.diceData.getDice(id);
                for(Map.Entry<String, String> mapEntry : connections.entrySet())
                { //for each free exit/edge
                    for(int orientation = 0; orientation < 8 ; orientation++)
                    { //for each orientation

                        dice.updateOrientation(orientation);
                        dice.fixOrientation();
                        PossiblePlacement possiblePlacement = new PossiblePlacement(dice, id);
                        possiblePlacement.updateCoordinates(mapEntry.getKey());

                        if(dice.getEdge(mapEntry.getValue().charAt(0)) == mapEntry.getValue().charAt(1))
                        { //if there is a valid connection to the free exit/edge
                            if(playerData.boardData.addTile(possiblePlacement.toString(), false))
                            { //if this is a legal placement
                                possiblePlacements.add(possiblePlacement);
                            }
                        }
                    }
                }
            }
        }

        return possiblePlacements;
    }

    private ArrayList<PossiblePlacement> filterByScore(ArrayList<PossiblePlacement> possiblePlacements)
    {
        //calculate the score for each possible placement
        for(PossiblePlacement possiblePlacement : possiblePlacements)
        {
            Board testBoard = buildTestBoard(playerData.boardData);
            testBoard.addTile(possiblePlacement.toString(), true);
            ScoreCalculator sc = new ScoreCalculator(testBoard);
            possiblePlacement.score = sc.getAdvancedScore() + sc.getErrors();
        }

        //get the maximum score
        int maxScore = Integer.MIN_VALUE;
        for(PossiblePlacement possiblePlacement : possiblePlacements)
        {
            if(maxScore < possiblePlacement.score)
            {
                maxScore = possiblePlacement.score;
            }
        }

        //get all possible placements with a score that equals the maximum
        ArrayList<PossiblePlacement> filtered = new ArrayList<>(0);
        for(PossiblePlacement possiblePlacement : possiblePlacements)
        {
            if(possiblePlacement.score == maxScore)
            {
                filtered.add(possiblePlacement);
            }
        }

        return filtered;
    }

    private void getFreeExits(HashMap<String, String> freeExits)
    {
        for(Map.Entry<String, String> mapEntry : playerData.boardData.getExitCoords().entrySet())
        { //for each exit coordinate
            if(!playerData.boardData.getPlacements().containsKey(mapEntry.getKey()))
            { //if there is not a tile there, it is a free exit
                freeExits.put(mapEntry.getKey(), mapEntry.getValue());
            }
        }
    }

    private void getFreeEdges(HashMap<String, String> freeEdges)
    {
        for(Map.Entry<String, Tile> mapEntry : playerData.boardData.getPlacements().entrySet())
        { //for each tile currently on the board
            Tile tile = mapEntry.getValue();
            for(int i=0; i<4; i++)
            {
                if(iterator.edgeIsNotOnRim(i, tile.getTestValue(i)))
                {
                    char edge = iterator.getDirection(i);
                    String adjCoords = playerData.boardData.getAdjCoords(edge, tile);
                    if(tile.getEdge(edge) != '0' && !playerData.boardData.getPlacements().containsKey(adjCoords))
                    { //if there is a valid exit at this edge, and there is no placement - this is a free edge

                        //connectionInfo records which edge must be which route type for a tile to be placed at these coordinates
                        String connectionInfo = "" + playerData.boardData.getOppositeEdge(edge) + tile.getEdge(edge);
                        freeEdges.put(adjCoords, connectionInfo);
                    }
                }
            }
        }
    }

    class PossiblePlacement extends Placement
    {
        Tile tile;
        String id;
        int score;

        PossiblePlacement(Tile tile, String id)
        {
            super(tile);
            this.tile = tile;
            this.id = id;
            score = 0;
        }
    }
}
