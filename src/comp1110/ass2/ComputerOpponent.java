package comp1110.ass2;

public class ComputerOpponent
{

    public PlayerData playerData;

    public ComputerOpponent(PlayerData playerData)
    {
        this.playerData = playerData;
    }

    public void haveTurn()
    {
        System.out.println("Computer Opponent having turn"); //=====debug

        //at the end...
        playerData.boardData.iterateRoundCounter();
        playerData.specialData.resetCounterRound();
    }
}
