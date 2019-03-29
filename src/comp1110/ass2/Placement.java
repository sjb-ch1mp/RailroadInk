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
        type = placementString.charAt(0);
        id = placementString.charAt(1);
        row = placementString.charAt(2);
        column = Integer.parseInt(placementString.substring(3, 4));
        orientation = Integer.parseInt(placementString.substring(4));
    }

    public Placement(Tile tile)
    {
        //constructs a placement from a Tile (no row and column data)
        type = tile.getId().charAt(0);
        id = tile.getId().charAt(1);
        orientation = tile.getOrientation();
    }

    public void updateCoordinates(String coords)
    {
        //updates the row and column fields of the placement (passed from the name of the ImageView in the board)
        row = coords.charAt(0);
        column = Integer.parseInt(coords.substring(1));
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
        return "" + type + id + row + column + orientation;
    }
}
