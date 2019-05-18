package comp1110.ass2;

import org.junit.Test;
import org.junit.Assert;

/**
 * These tests check that the compileRoutes and getRoute methods are working as expected
 *
 * @author Samuel J. Brookes (unless indicated otherwise)
 */

public class RouteCompilerTest
{
    /*
    * Number of routes in each of the COMPLETED_GAMES:
    * COMPLETED_GAMES[0] = 1 Route
    * COMPLETED_GAMES[1] = 2 Routes
    * COMPLETED_GAMES[2] = 2 Routes
    * COMPLETED_GAMES[3] = 4 Routes
    * COMPLETED_GAMES[4] = 4 Routes
    * COMPLETED_GAMES[5] = 3 Routes (one free hanging)
    * */

    private static final int[] numberOfRoutes = {2, 2, 2, 4, 4, 3}; //counted by eye

    @Test
    public void correctNumberOfRoutes()
    {
        /*
        * This tests whether the getRoute() and compileRoutes() methods are working as expected.
        * The number of routes compiled should equal the number of routes counted by eye.
        * */

        for(int i=0; i<SampleGames.COMPLETED_GAMES.length; i++)
        {
            String game = SampleGames.COMPLETED_GAMES[i];

            Board board = new Board();
            for(int j=0; j<game.length(); j+=5)
            {
                board.addTile(game.substring(j, j+5), true);
            }

            ScoreCalculator sc = new ScoreCalculator(board);
            Assert.assertEquals("Sample Game " + (i + 1) + " has " + numberOfRoutes[i] + " routes. Returned: " + sc.getNumberOfRoutes() + "\nBoard string: " + game,
                    numberOfRoutes[i], sc.getNumberOfRoutes());
        }
    }
}
