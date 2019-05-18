package comp1110.ass2;

import comp1110.ass2.gui.Dices;
import comp1110.ass2.gui.SpecialTiles;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private int[][] indexPermutations =
            {
                    {1, 2, 3, 4},
                    {1, 2, 4, 3},
                    {1, 3, 2, 4},
                    {1, 3, 4, 2},
                    {1, 4, 2, 3},
                    {1, 4, 3, 2},
                    {2, 1, 3, 4},
                    {2, 1, 4, 3},
                    {2, 3, 1, 4},
                    {2, 3, 4, 1},
                    {2, 4, 1, 3},
                    {2, 4, 3, 1},
                    {3, 1, 2, 4},
                    {3, 1, 4, 2},
                    {3, 2, 1, 4},
                    {3, 2, 4, 1},
                    {3, 4, 1, 2},
                    {3, 4, 2, 1},
                    {4, 1, 2, 3},
                    {4, 1, 3, 2},
                    {4, 2, 1, 3},
                    {4, 2, 3, 1},
                    {4, 3, 1, 2},
                    {4, 3, 2, 1}
            };

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

    private Dices buildTestDice()
    {
        Dices testDice = new Dices();
        testDice.copyPlayerDices(playerData.diceData);
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

    public String haveAdvancedTurn()
    {
        ArrayList<PossiblePlacement[]> possibleSequences = new ArrayList<>(0);

        for(int[] permutation : indexPermutations)
        {
            possibleSequences.add(getBestSequence(permutation));
        }

        //get the one sequence from this subset that has the highest score? - might be too greedy
        PossiblePlacement[] maxSequence = new PossiblePlacement[4];
        int maxScore = Integer.MIN_VALUE;
        for(PossiblePlacement[] possibleSequence : possibleSequences)
        {
            int score = getSequenceScore(possibleSequence);
            if(maxScore < score)
            {
                maxSequence = possibleSequence;
                maxScore = score;
            }
        }

        if(playerData.specialData.getCounterGame() < 3 && playerData.specialData.getCounterRound() == 0)
        {
            //once you have the highest scoring placement sequence - check if
            //adding a special tile at some point in the sequence will improve the score and
            //then add that placement (or don't)
        }

        StringBuilder moveReport = new StringBuilder();
        for(PossiblePlacement placement : maxSequence)
        {
            playerData.boardData.addTile(placement.toString(), true);
            if(placement.id.charAt(0) == 'S')
            { //placement is a SpecialTile
                playerData.specialData.useSpecialTile(placement.id);
            }
            else
            { //placement is a Dice
                playerData.diceData.useDice(placement.id);
            }
            moveReport.append(placement.toString());
        }

        return moveReport.toString();
    }

    private PossiblePlacement[] getBestSequence(int[] indices)
    {
        PossiblePlacement[] bestSequence = new PossiblePlacement[4];
        Board testBoard = buildTestBoard(playerData.boardData);

        for(int i=0; i<indices.length; i++)
        {
            PossiblePlacement possiblePlacement = new PossiblePlacement(playerData.diceData.getDice("D" + indices[i]), "D" + indices[i]);
            possiblePlacement.evaluatePlacement(testBoard);
            testBoard.addTile(possiblePlacement.toString(), true);
            bestSequence[i] = possiblePlacement;
        }

        return bestSequence;
    }

    private int getSequenceScore(PossiblePlacement[] placementSequence)
    {
        Board testBoard = buildTestBoard(playerData.boardData);
        for(PossiblePlacement placement : placementSequence)
        {
            testBoard.addTile(placement.toString(), true);
        }

        ScoreCalculator sc = new ScoreCalculator(testBoard);
        return sc.getAdvancedScore();
    }

    class PossiblePlacement extends Placement
    {
        /*
         *  ==== PLACEMENT CATEGORIES ====
         *  Ordered from best case to worst case
         *
         *  badPlacement
         *  1 = not a bad placement
         *  2 = leads route off board
         *  3 = blocks an exit
         *
         *  goodPlacement
         *  1 = connects route to an exit
         *  2 = connects 2 routes together
         *  3 = adds to an existing route and maintains route type
         *  4 = adds to an existing route and breaks route type
         *  5 = connects to an exit (i.e. starts new route)
         *  6 = placement is legal
         */
        int badPlacement;
        int goodPlacement;
        String id;

        PossiblePlacement(Tile tile, String id)
        {
            super(tile);
            badPlacement = goodPlacement = 0;
            this.id = id;
        }

        void evaluatePlacement(Board board)
        {
            /*
             * Find the best possible placement given the state of the
             * board and the specific tile.
             */
            ArrayList<PossiblePlacement> possiblePlacements = new ArrayList<>(0);

            for (char row = 'A'; row <= 'G'; row++)
            {
                for (int column = 0; column < 7; column++)
                {
                    for (int orientation = 0; orientation < 7; orientation++)
                    {
                        Tile tile = new Tile(this.getFullId());
                        tile.updateOrientation(orientation);
                        PossiblePlacement possiblePlacement = new PossiblePlacement(tile, id);
                        possiblePlacement.updateCoordinates("" + row + column);
                        if (board.addTile(possiblePlacement.toString(), false))
                        {
                            possiblePlacement.evaluateBad(board, tile);
                            possiblePlacement.evaluateGood(board, tile);
                            possiblePlacements.add(possiblePlacement);
                        }
                    }
                }
            }

            //split into badPlacement categories 1, 2 and 3
            //check through 1 first - if this is not empty - set the placement to the lowest goodPlacement value
            //if 1 is empty - set the placement to the lowest goodPlacement from 2
            //if 2 is empty - set the placement to the lowest goodPlacement from 3
            ArrayList<PossiblePlacement> badPlacements1 = new ArrayList<>(0);
            ArrayList<PossiblePlacement> badPlacements2 = new ArrayList<>(0);
            ArrayList<PossiblePlacement> badPlacements3 = new ArrayList<>(0);
            for (PossiblePlacement possiblePlacement : possiblePlacements)
            {
                switch (possiblePlacement.badPlacement)
                {
                    case 1:
                    {
                        badPlacements1.add(possiblePlacement);
                        break;
                    }
                    case 2:
                    {
                        badPlacements2.add(possiblePlacement);
                        break;
                    }
                    case 3:
                        badPlacements3.add(possiblePlacement);
                }
            }

            if (!badPlacements1.isEmpty())
            {
                possiblePlacements = badPlacements1;
            } else if (!badPlacements2.isEmpty())
            {
                possiblePlacements = badPlacements2;
            } else
            { //all placements block an exit
                possiblePlacements = badPlacements3;
            }

            int minGoodPlacement = Integer.MAX_VALUE;
            PossiblePlacement minPlacement = null;
            for (PossiblePlacement possiblePlacement : possiblePlacements)
            {
                if (minGoodPlacement > possiblePlacement.goodPlacement)
                {
                    minGoodPlacement = possiblePlacement.goodPlacement;
                    minPlacement = possiblePlacement;
                }
            }

            this.updateCoordinates(minPlacement.getCoords());
            this.updateOrientation(minPlacement.getOrientation());
        }

        void evaluateBad(Board board, Tile tile)
        {

            if (blocksExit(board, tile))
            {
                this.badPlacement = 3;
            }
            else if (leadsRouteOffBoard(board, tile))
            {
                this.badPlacement = 2;
            }
            this.badPlacement = 1;
        }

        void evaluateGood(Board board, Tile tile)
        {
            if (connectsRouteToExit(board, tile))
            {
                this.goodPlacement = 1;
            }
            else if (connectsTwoRoutes(board, tile))
            {
                this.goodPlacement = 2;
            }
            else if (maintainsRouteType(board, tile))
            {
                this.goodPlacement = 3;
            }
            else if (breaksRouteType(board, tile))
            {
                this.goodPlacement = 4;
            }
            else if (connectsToExit(board, tile))
            {
                this.goodPlacement = 5;
            }
            else
            { //it is a legal placement
                this.goodPlacement = 6;
            }
        }

        boolean blocksExit(Board board, Tile tile)
        {
            if(board.getExitCoords().containsKey(this.getCoords()))
            {//this placement is on an exit coordinate
                char exitEdge = board.getExitCoords().get(this.getCoords()).charAt(0);
                char exitType = board.getExitCoords().get(this.getCoords()).charAt(1);
                if(tile.getEdge(exitEdge) != exitType)
                { //if there is not a valid connection to the exit, the placement blocks the exit
                    return true;
                }
            }
            return false;
        }

        boolean leadsRouteOffBoard(Board board, Tile tile)
        {
            for(int i=0; i<4; i++)
            {
                if(!iterator.edgeIsNotOnRim(i, tile.getTestValue(i)))
                { //this edge is on the rim of the board
                    if(!board.getExitCoords().containsKey(this.getCoords()))
                    { //if this is at an exit coordinate, then it must be a legal connection (see blocksExit())
                        if(tile.getEdge(iterator.getDirection(i)) != '0')
                        { //if the edge of the tile is not blank, then it leads the route off the board
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        boolean connectsRouteToExit(Board board, Tile tile)
        {
            if(board.getExitCoords().containsKey(this.getCoords()))
            {//if placement is on an exit coordinate
                char exitEdge = board.getExitCoords().get(this.getCoords()).charAt(0);
                char exitType = board.getExitCoords().get(this.getCoords()).charAt(1);
                if(tile.getEdge(exitEdge) == exitType)
                { //there is a valid connection to the exit

                    for(int i=0; i<4; i++)
                    {
                        if(iterator.getDirection(i) != exitEdge)
                        { //if this is an edge other than the edge connected to the exit
                            if(tile.getEdge(iterator.getDirection(i)) != '0')
                            {//if the edge is not blank
                                String adjCoords = board.getAdjCoords(iterator.getDirection(i), tile);
                                if(board.getPlacements().containsKey(adjCoords))
                                { //if there is a placement at the adjacent coordinates
                                    Tile adjTile = board.getTile(adjCoords);
                                    if(tile.getEdge(iterator.getDirection(i)) == adjTile.getEdge(board.getOppositeEdge(iterator.getDirection(i))))
                                    { //if there is a valid connection to the adjacent tile

                                        //there is a valid connection to an exit AND a route
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        boolean connectsTwoRoutes(Board board, Tile tile)
        {
            int connectionCount = 0;

            for(int i=0; i<4; i++)
            {
                if(iterator.edgeIsNotOnRim(i, tile.getTestValue(i)))
                {//if this edge is not on the rim of the board
                    String adjCoords = board.getAdjCoords(iterator.getDirection(i), tile);
                    if(board.getPlacements().containsKey(adjCoords))
                    { //if there is a placement at these coordinates
                        Tile adjTile = board.getTile(adjCoords);
                        if(tile.getEdge(iterator.getDirection(i)) == adjTile.getEdge(board.getOppositeEdge(iterator.getDirection(i))))
                        { //if there is a valid connection to the adjacent tile
                            connectionCount++;
                        }
                    }
                }
            }

            return connectionCount > 1;
        }

        boolean maintainsRouteType(Board board, Tile tile)
        {
            for(int i=0; i<4; i++)
            {
                if(iterator.edgeIsNotOnRim(i, tile.getTestValue(i)))
                {//if this edge is not on the rim of the board
                    String adjCoords = board.getAdjCoords(iterator.getDirection(i), tile);
                    if(board.getPlacements().containsKey(adjCoords))
                    { //if there is a placement at these coordinates
                        Tile adjTile = board.getTile(adjCoords);
                        if(tile.getEdge(iterator.getDirection(i)) == adjTile.getEdge(board.getOppositeEdge(iterator.getDirection(i))))
                        { //if there is a valid connection to the adjacent tile

                            //return true if this route type continues out another edge
                            return countEdgeType(tile, tile.getEdge(iterator.getDirection(i))) > 1;
                        }
                    }
                }
            }
            return false;
        }

        boolean breaksRouteType(Board board, Tile tile)
        {
            for(int i=0; i<4; i++)
            {
                if(iterator.edgeIsNotOnRim(i, tile.getTestValue(i)))
                {//if this edge is not on the rim of the board
                    String adjCoords = board.getAdjCoords(iterator.getDirection(i), tile);
                    if(board.getPlacements().containsKey(adjCoords))
                    { //if there is a placement at these coordinates
                        Tile adjTile = board.getTile(adjCoords);
                        if(tile.getEdge(iterator.getDirection(i)) == adjTile.getEdge(board.getOppositeEdge(iterator.getDirection(i))))
                        { //if there is a valid connection to the adjacent tile

                            //return true if this is the only edge with that route
                            return countEdgeType(tile, tile.getEdge(iterator.getDirection(i))) == 1;
                        }
                    }
                }
            }
            return false;
        }

        boolean connectsToExit(Board board, Tile tile)
        {
            if(board.getExitCoords().containsKey(this.getCoords()))
            {//this placement is on an exit coordinate
                char exitEdge = board.getExitCoords().get(this.getCoords()).charAt(0);
                char exitType = board.getExitCoords().get(this.getCoords()).charAt(1);
                if(tile.getEdge(exitEdge) == exitType)
                { //if there is a valid connection to the exit
                    return true;
                }
            }
            return false;
        }

        int countEdgeType(Tile tile, char edgeType)
        {
            char[] edges = tile.getEdges();
            int edgeTypeCount = 0;
            for(int j=0; j<4; j++)
            {
                if(edges[j] == edgeType)
                {
                    edgeTypeCount++;
                }
            }
            return edgeTypeCount;
        }
    }
}
