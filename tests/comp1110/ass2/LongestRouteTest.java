package comp1110.ass2;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LongestRouteTest
{

    private static final int[] longestRailroads = {5, 8, 9, 10, 8, 9}; //counted by eye
    private static final int[] longestHighways = {12, 9, 12, 7, 11, 10}; //counted by eye

    @Test
    public void longestRoutesCorrect()
    {
        for(int i=0; i<SampleGames.COMPLETED_GAMES.length; i++)
        {
            String boardString = SampleGames.COMPLETED_GAMES[i];
            System.out.println("COMPLETED_GAMES[" + i + "] = " + boardString);
            Board board = new Board();
            for(int j=0; j<boardString.length(); j+=5)
            {
                if(!board.addTile(boardString.substring(j, j+5))) System.out.println("Didn't add: " + boardString.substring(i, i+5));
            }

            ScoreCalculator sc = new ScoreCalculator(board);

            int longestRailroad = sc.getLongestRailroad();
            int longestHighway = sc.getLongestHighway();

            System.out.println("LONGEST RAILROAD: Expected = " + longestRailroads[i] + ", Actual = " + longestRailroad);
            System.out.println("LONGEST HIGHWAY: Expected = " + longestHighways[i] + ", Actual = " + longestHighway);
            //assertTrue("LONGEST RAILROAD: Expected " + longestRailroads[i] + ", but got " + longestRailroad, longestRailroad == longestRailroads[i]);
            //assertTrue("LONGEST HIGHWAY: Expected " + longestHighways[i] + ", but got " + longestHighway, longestHighway == longestHighways[i]);

        }
    }

}

