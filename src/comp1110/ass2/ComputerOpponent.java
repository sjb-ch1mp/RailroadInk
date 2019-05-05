package comp1110.ass2;

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
    public void haveTurn()
    {
        System.out.println("Computer Opponent having turn"); //=====debug

        //at the end...
        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();
    }
}
