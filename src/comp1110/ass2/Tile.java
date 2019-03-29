package comp1110.ass2;

import javafx.scene.image.Image;

/**
 * The Tile enum makes it easier to handle and access Tile data and behaviour.
 * A couple important aspects of Tile is that it will keep track of which edges
 * have which connections when changing the orientation, and will make it easier
 * to get the correct image of a Tile from assets.
 */

public enum Tile
{
    A0("A0", 'N', 'R', '0', '0', 'R'),
    A1("A1", 'N', 'R', '0', 'R', '0'),
    A2("A2", 'N', 'R', 'R', 'R', '0'),
    A3("A3", 'N', 'H', 'H', 'H', '0'),
    A4("A4", 'N', 'H', '0', 'H', '0'),
    A5("A5", 'N', 'H', '0', '0', 'H'),
    B0("B0", 'S', 'H', '0', 'R', '0'),
    B1("B1", 'S', 'H', 'R', '0', '0'),
    B2("B2", 'O', 'H', 'R', 'H', 'R'),
    S0("S0", 'S', 'H', 'H', 'R', 'H'),
    S1("S1", 'S', 'H', 'R', 'R', 'R'),
    S2("S2", 'N', 'H', 'H', 'H', 'H'),
    S3("S3", 'N', 'R', 'R', 'R', 'R'),
    S4("S4", 'S', 'H', 'R', 'R', 'H'),
    S5("S5", 'S', 'H', 'R', 'H', 'R');

    private final int TOTAL_ORIENTATIONS = 8;
    private char[] edges; //edges stores the connectors on each edge of the tile (north, east, south, west)
    private String id;
    private int orientation;
    private char routeType; //stores the route type, 'O' = overpass, 'S' = station, 'N' = neither
    private char row; //the row and column of a given placement can be stored when the tile is placed on the board
    private int column; //this is for calculating score

    Tile(String id, char routeType, char north, char east, char south, char west)
    {
        this.id = id;
        this.orientation = 0;
        this.routeType = routeType;

        edges = new char[4];
        edges[0] = north;
        edges[1] = east;
        edges[2] = south;
        edges[3] = west;
    }

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
            char hold;
            if(orientation == 3)
            { //moving to mirror orientations
                this.edges = Tile.valueOf(this.id).getEdges(); //reset the edges to orientation 0

                //switch east and west edge
                hold = edges[1];
                edges[1] = edges[3];
                edges[3] = hold;
            }
            else if(orientation == 7)
            { //returning to normal orientations
                this.edges = Tile.valueOf(this.id).getEdges(); //reset the edges to orientation 0
            }
            else
            { //otherwise right shift edges by one
                hold = edges[3];
                edges[3] = edges[2];
                edges[2] = edges[1];
                edges[1] = edges[0];
                edges[0] = hold;
            }

            if(orientation == 7)
            {
                orientation = 0;
            }
            else
            {
                orientation++;
            }
        }

        orientation = newOrientation;
    }

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

    public String getId()
    {
        return id;
    }

    public int getOrientation()
    {
        return orientation;
    }

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

    private char[] getEdges()
    {
        return edges;
    }

    public char getRouteType()
    {
        return routeType;
    }

    public Image getImage()
    {
        //returns a new Image using the imageUri field
        return new Image("assets/" + id + ".png"); //e.g. assets/A00.png
    }

    public void addCoordinates(String coords)
    {
        //stores the coordinates for this tile when it is placed in the board - will help when compiling routes
        //in the ScoreCalculator
        row = coords.charAt(0);
        column = coords.charAt(1);
    }

}
