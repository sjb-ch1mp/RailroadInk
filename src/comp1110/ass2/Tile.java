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
    A0(),
    A1(),
    A2(),
    A3(),
    A4(),
    A5(),
    B0(),
    B1(),
    B2(),
    S0(),
    S1(),
    S2(),
    S3(),
    S4(),
    S5();

    private char[] edges; //edges stores the connectors on each edge of the tile (north, east, south, west)
    private String id;
    private int orientation;
    private char routeType; //stores the route type (i.e. OVERPASS or STATION) - for calculating score
    private char row; //the row and column of a given placement can be stored when the tile is placed on the board
    private int column; //this is for calculating score

    Tile(String id, int orientation, char routeType, char north, char east, char south, char west)
    {
        this.id = id;
        this.orientation = orientation;
        this.routeType = routeType;

        edges = new char[4];
        edges[0] = north;
        edges[1] = east;
        edges[2] = south;
        edges[3] = west;

    }

    public void updateOrientation(int newOrientation)
    {
        //updates the orientation and the position of the values in the edges array

        //Note: To move from orientation 3 to 4, take the array for orientation 0 and switch the east and west values
        //      then continue to 5, 6 and 7 from there. E.g. if the edges[] array for orientation 0 = [H, R, 0, 0], the
        //      edge[] array for orientation 4 = [H, 0, 0, R].
    }

    public void rotateTile()
    {
        //updates the orientation and the position of the values in the edges array by one
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
    }

}
