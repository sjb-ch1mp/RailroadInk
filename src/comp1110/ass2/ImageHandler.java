package comp1110.ass2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.File;

public class ImageHandler
{
    private static final double IMAGE_DIMENSIONS = 60;
    private static final String URI_BASE = "src\\comp1110\\ass2\\gui\\assets\\";
    private static final String ext = ".png";
    private static int orientation;

    public static ImageView getTileImage(Tile tile)
    {
        ImageView img;
        orientation = tile.getOrientation();
        if(orientation >= 4)
        {
            //This is a temporary solution until I can figure out how to mirror programmatically!!!!
            img = new ImageView(new Image(new File(URI_BASE + tile.getId() + "_mirrored" + ext).toURI().toString()));
            orientation -= 4;
        }
        else
        {
            img = new ImageView(new Image(new File(URI_BASE + tile.getId() + ext).toURI().toString()));
        }
        img = setDimensions(img);

        if(orientation != 0)
        {
            img = rotateImage(img);
        }

        //can set up actionHandlers in here too

        return img;
    }

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

    public static ImageView getMiscTile(String tile)
    {
        ImageView img = new ImageView(new Image(new File(URI_BASE + tile + ext).toURI().toString()));
        img = setDimensions(img);
        return img;
    }

    private static ImageView rotateImage(ImageView img)
    {
        /*
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

    public static ImageView setDimensions(ImageView img)
    {
        img.setFitWidth(IMAGE_DIMENSIONS);
        img.setFitHeight(IMAGE_DIMENSIONS);
        return img;
    }

}
