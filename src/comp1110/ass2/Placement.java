package comp1110.ass2;

/**
 * The Placement class makes it easier to handle placement data.
 */
public class Placement
{
    private char type; //The type of the tile (A, B or S)
    private char id; //The ID of the tile (A: 0-5, B: 0-3, S: 0-5)
    private char row; //The row of the tile placement (A-G)
    private int column; //The column of the tile placement (0-6)
    private int orientation; //The orientation of the tile (0-7)

    public Placement(String placementString)
    {
        //constructs a placement from a placementString
    }

    public Placement(Tile tile)
    {
        //constructs a placement from a Tile (no row and column data)
    }

    public void updateCoordinates(String coords)
    {
        //updates the row and column fields of the placement (passed from the name of the ImageView in the board)
    }

    public char getType()
    {
        return type;
    }

    public char getId()
    {
        return id;
    }

    public char getRow()
    {
        return row;
    }

    public int getRowAsInt()
    {
        return (int) row - 65;
    }

    public int getColumn()
    {
        return column;
    }

    public int getOrientation()
    {
        return orientation;
    }

    @Override
    public String toString()
    {
        //returns the fields as a valid placement string
    }
}
