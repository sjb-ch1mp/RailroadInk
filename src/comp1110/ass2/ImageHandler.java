package comp1110.ass2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import java.io.File;

/**
 * The ImageHandler class contains static methods that make it easier to
 * retrieve images from the assets folder for use as tiles in the UI.
 * Images are resized, rotated and returned as an ImageView object.
 */

public class ImageHandler
{
    private static final double IMAGE_DIMENSIONS = 60;
    private static final String URI_BASE = "src\\comp1110\\ass2\\gui\\assets\\";
    private static final String ext = ".png";
    private static int orientation;

    /**
     * The getTileImage() method returns an ImageView object for a given tile
     * after resizing it and rotating it appropriately.
     *
     * @param tile (Tile) A tile of type A, B or S.
     * @return (ImageView) The image of the given tile in the appropriate orientation.
     */
    public static ImageView getTileImage(Tile tile)
    {
        ImageView img;
        orientation = tile.getOrientation();
        if(orientation >= 4)
        { //If orientation is one of the mirrored orientations, start from the mirrored image

            //This is a temporary solution until I can get rid of the bugs when mirroring programmatically!!!!
            img = new ImageView(new Image(new File(URI_BASE + tile.getId() + "_mirrored" + ext).toURI().toString()));
            orientation -= 4;
        }
        else
        { //Start from the image for orientation zero
            img = new ImageView(new Image(new File(URI_BASE + tile.getId() + ext).toURI().toString()));
        }

        //set the dimensions of the ImageView before rotating, otherwise funny things happen
        img = setDimensions(img);

        //if the orientation is not zero, rotate the ImageView
        if(orientation != 0)
        {
            img = rotateImage(img);
        }

        //NOTE FOR LATER: can set up actionHandlers in here too

        return img;
    }

    /**
     * The getExitImage() method returns an ImageView object of the given exit
     * tile after resizing it and rotating it.
     *
     * @param side (char) The side of the board on which the exit tile is.
     * @param type (char) The type of the exit (either R or H).
     * @return (ImageView) An image of the exit in the appropriate orientation.
     */
    public static ImageView getExitImage(char side, char type)
    {
        ImageView img = new ImageView(new Image(new File(URI_BASE + "X" + type + ext).toURI().toString()));
        img = setDimensions(img);

        switch(side)
        {
            case 'N': orientation = 0; break;
            case 'E': orientation = 1; break;
            case 'S': orientation = 2; break;
            case 'W': orientation = 3; break;
        }

        if(orientation != 0)
        {
            img = rotateImage(img);
        }

        return img;
    }

    /**
     * The getMiscTile() method returns an ImageView object of the requested tile type.
     *
     * @param tile (String) The requested tile type (CENTER_TILE, EDGE_TILE or BLANK_TILE).
     * @return (ImageView) An image of the requested tile type.
     */
    public static ImageView getMiscTile(String tile)
    {
        ImageView img = new ImageView(new Image(new File(URI_BASE + tile + ext).toURI().toString()));
        img = setDimensions(img);
        return img;
    }

    /**
     * The rotateImage() method rotates the given ImageView object based on
     * its orientation.
     *
     * @param img (ImageView) The image that needs to be rotated.
     * @return (ImageView) The same image after being rotated.
     */
    private static ImageView rotateImage(ImageView img)
    {
        /* THIS MIRRORING CODE IS CAUSING STRANGE BUGS, I'LL COME BACK TO IT
        if(orientation >= 4)
        {
            Rotate rotate = new Rotate();
            rotate.setPivotX(IMAGE_DIMENSIONS/2);
            rotate.setPivotY(IMAGE_DIMENSIONS/2);
            rotate.setAxis(Rotate.Y_AXIS);
            rotate.setAngle(180);
            img.getTransforms().add(rotate);
            orientation -= 4;
        }
        */
        for(int i=0; i<orientation; i++)
        {
            Rotate rotate = new Rotate();
            rotate.setPivotX(IMAGE_DIMENSIONS/2);
            rotate.setPivotY(IMAGE_DIMENSIONS/2);
            rotate.setAngle(90);
            img.getTransforms().add(rotate);
        }

        return img;
    }

    /**
     * The setDimensions() method takes an ImageView object
     * and sets its FitWidth and FitHeight.
     *
     * @param img (ImageView) The image that needs to be resized.
     * @return (ImageView) The same image after being resized.
     */
    private static ImageView setDimensions(ImageView img)
    {
        img.setFitWidth(IMAGE_DIMENSIONS);
        img.setFitHeight(IMAGE_DIMENSIONS);
        return img;
    }

}
