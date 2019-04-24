package comp1110.ass2;

import java.util.HashMap;

/**
 * The Tile class makes it easier to handle and access Tile data and behaviour.
 * A couple important aspects of Tile is that it will keep track of which edges
 * have which connections when changing the orientation, and will make it easier
 * to get the correct image of a Tile from assets.
 */

public class Tile
{
    private HashMap<String, Character[]> constructors;
    private final int TOTAL_ORIENTATIONS = 8;
    private char[] edges; //edges stores the connectors on each edge of the tile (north, east, south, west)
    private String id;
    private int orientation;
    private char intersectionType;
    private char row; //the row and column of a given placement can be stored when the tile is placed on the board
    private int column; //this is for calculating score
    private boolean used; //this stores whether the tile has been used (dices and special tiles)
    private boolean selected; //this tracks whether a tile is selected

    public Tile(String id)
    {
        buildConstructors();

        this.id = id;
        this.orientation = 0;
        intersectionType = constructors.get(id)[0];
        used = false;
        selected = false;

        edges = new char[4];
        edges[0] = constructors.get(id)[1];
        edges[1] = constructors.get(id)[2];
        edges[2] = constructors.get(id)[3];
        edges[3] = constructors.get(id)[4];
    }

    /**
     * The updateOrientation() method changes the orientation of the Tile.
     * To do so, it shifts the values in the edges array depending upon the difference
     * between the current orientation and the desired orientation.
     *
     * @param newOrientation (int) The desired orientation for the Tile
     */
    public void updateOrientation(int newOrientation)
    {
        // Updates the orientation and the position of the values in the edges array
        //Note: To move from orientation 3 to 4, take the array for orientation 0 and switch the east and west values
        //      then continue to 5, 6 and 7 from there. E.g. if the edges[] array for orientation 0 = [H, R, 0, 0], the
        //      edge[] array for orientation 4 = [H, 0, 0, R].
        if(orientation == newOrientation) return;

        int nrShifts = (orientation < newOrientation) ?
                newOrientation - orientation :
                TOTAL_ORIENTATIONS - (orientation - newOrientation);

        for(int i=0; i<nrShifts; i++)
        {
            if(orientation == 7)
            {
                orientation = 0;
            }
            else
            {
                orientation++;
            }

            char hold;
            if(orientation == 4)
            { //moving to mirror orientations
                resetEdges();

                //switch east and west edge
                hold = edges[1];
                edges[1] = edges[3];
                edges[3] = hold;
            }
            else if(orientation == 0)
            { //returning to normal orientations
                //reset edges array to orientation zero
                resetEdges();
            }
            else
            { //otherwise right shift edges by one
                hold = edges[3];
                edges[3] = edges[2];
                edges[2] = edges[1];
                edges[1] = edges[0];
                edges[0] = hold;
            }
        }
    }

    /**
     * The rotateTile() method updates the orientation of the tile by 1.
     */
    public void rotateTile()
    {
        //updates the orientation and the position of the values in the edges array by one
        switch(orientation)
        {
            case 0: updateOrientation(1); break;
            case 1: updateOrientation(2); break;
            case 2: updateOrientation(3); break;
            case 3: updateOrientation(4); break;
            case 4: updateOrientation(5); break;
            case 5: updateOrientation(6); break;
            case 6: updateOrientation(7); break;
            case 7: updateOrientation(0);
        }
    }

    /**
     * @return (String) The identifier for the Tile object
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return (char) The row at which the tile has been placed on the board.
     */
    public char getRow()
    {
        return row;
    }

    /**
     * @return (int) The column at which the tile has been placed on the board.
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * This method ensures that when the tests and directions HashMaps are being
     * used in a loop within the ScoreCalculator class, that the correct value is
     * being passed to the IntPredicate to test whether a given edge needs to be
     * evaluated.
     * @param i = the current iteration of the loop
     * @return (int) Either the row or the column
     */
    public int getTestValue(int i)
    {
        switch(i)
        {
            case 0: return (int) row;
            case 1: return column;
            case 2: return (int) row;
            default: return column;
        }
    }

    /**
     * @return (String) The coordinates at which the tile has been placed
     */
    public String getCoords()
    {
        return "" + row + column;
    }

    /**
     * @return (int) The orientation of the tile.
     */
    public int getOrientation()
    {
        return orientation;
    }

    /**
     * The getEdge() method returns the value of the desired edge.
     *
     * @param edge (char) The desired edge.
     * @return (char) The value of the desired edge.
     */
    public char getEdge(char edge)
    {
        //returns the current value of the edge - used to test compatibility
        //returns x on error
        switch(edge)
        {
            case 'N': return edges[0];
            case 'E': return edges[1];
            case 'S': return edges[2];
            case 'W': return edges[3];
        }
        return 'x';
    }

    /**
     * The resetEdges() method reverts all values in the
     * edges array to those of orientation zero.
     */
    private void resetEdges()
    {
        edges[0] = constructors.get(id)[1];
        edges[1] = constructors.get(id)[2];
        edges[2] = constructors.get(id)[3];
        edges[3] = constructors.get(id)[4];
    }

    /**
     * @return (char[]) The array of edges
     */
    public char[] getEdges()
    {
        return edges;
    }

    /**
     * @return (boolean) Whether or not the tile is an overpass
     */
    public boolean isOverPass()
    {
        return intersectionType == 'O';
    }

    /**
     * @return (boolean) Whether or not the tile is a station.
     */
    public boolean isStation()
    {
        return intersectionType == 'S';
    }

    /**
     * This method adds coordinates to a tile object when it is placed on a board
     * @param coords
     */
    public void addCoordinates(String coords)
    {
        row = coords.charAt(0);
        column = Integer.parseInt(coords.substring(1));
    }

    /**
     * Changes the used value to true
     */
    public void useTile()
    {
        used = true;
    }

    /**
     * @return (boolean) Whether or not a tile has been used
     */
    public boolean isUsed()
    {
        return used;
    }

    /**
     * Having Tile as an enum was causing issues because rotating any tile stored
     * in a Board object rotated ALL tiles of that type (because an enum is implicitly static).
     * The Tile enum is now implemented as a class, but retains a similar function as an enum
     * with the buildConstructors() method. This is called when a new tile is made and builds
     * the HashMap 'constructors' which stores the routeType and edge information for each Tile.
     * Once built, the correct information for this instance of the Tile class can be passed to
     * the object.
     *
     * The order in which information is stored in the Character[] array is:
     *  - routeType, north, east, south, west
     */
    private void buildConstructors()
    {
        constructors = new HashMap<>();
        constructors.put("A0", new Character[]{'N', 'R', '0', '0', 'R'});
        constructors.put("A1", new Character[]{'N', 'R', '0', 'R', '0'});
        constructors.put("A2", new Character[]{'N', 'R', 'R', 'R', '0'});
        constructors.put("A3", new Character[]{'N', 'H', 'H', 'H', '0'});
        constructors.put("A4", new Character[]{'N', 'H', '0', 'H', '0'});
        constructors.put("A5", new Character[]{'N', 'H', '0', '0', 'H'});
        constructors.put("B0", new Character[]{'S', 'H', '0', 'R', '0'});
        constructors.put("B1", new Character[]{'S', 'H', 'R', '0', '0'});
        constructors.put("B2", new Character[]{'O', 'H', 'R', 'H', 'R'});
        constructors.put("S0", new Character[]{'S', 'H', 'H', 'R', 'H'});
        constructors.put("S1", new Character[]{'S', 'H', 'R', 'R', 'R'});
        constructors.put("S2", new Character[]{'N', 'H', 'H', 'H', 'H'});
        constructors.put("S3", new Character[]{'N', 'R', 'R', 'R', 'R'});
        constructors.put("S4", new Character[]{'S', 'H', 'R', 'R', 'H'});
        constructors.put("S5", new Character[]{'S', 'H', 'R', 'H', 'R'});
    }

    /**
     * @return (String) The fields of the Tile object as a valid placement string
     */
    public String getPlacementString()
    {
        return id + row + column + orientation;
    }

    /**
     * Selects the tile (changes selected to true).
     */
    public void selectTile()
    {
        selected = true;
    }

    /**
     * Deselects the tile (changes selected to false).
     */
    public void deselectTile()
    {
        selected = false;
    }

    /**
     * @return (boolean) Whether or not the tile is selected
     */
    public boolean isSelected()
    {
        return selected;
    }
}
