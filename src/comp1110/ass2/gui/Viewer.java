package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

/**
 * A very simple viewer for tile placements in the Railroad Ink game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various tile placements.
 */
public class Viewer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    /* board layout */
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private static final String URI_BASE = "assets/";

    private Board boardData;
    private final VBox board = new VBox();
    private GridPane boardProper;
    private final VBox root = new VBox();
    private final Group controls = new Group();
    private Placement prevPlacement = null;
    private TextField textField;
    private Text textWarning;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer

        /*
         * The makePlacement() method takes valid placement strings
         * and puts them onto boardProper after clearing
         * any previous placement (if it exists).
         *
         * NOTE: The makePlacement() method DOES NOT apply the placement
         * rules of the game as this was not a requirement of Task 4.
         * It is simply to test whether the placement strings can be translated
         * into an actual placement on the game board.
         * */

        if(placement.length() == 5 && RailroadInk.isTilePlacementWellFormed(placement))
        { //if placementString is valid
            Placement p = new Placement(placement);
            ImageView img;
            if(prevPlacement != null)
            { //if this is not the first placement, replace the previous placement with an appropriate tile
                if(boardData.isCenterCoord(prevPlacement.getCoords()))
                { //replace with center tile
                    img = ImageHandler.getMiscTile("CENTER_TILE");
                }
                else
                { //replace with blank tile
                    img = ImageHandler.getMiscTile("BLANK_TILE");
                }

                //replace previous placement with blank/center tile
                boardProper.add(img, prevPlacement.getColumn(), prevPlacement.getRowAsInt());
            }

            //create tile for this placement
            Tile tile = new Tile(p.getFullId());
            tile.updateOrientation(p.getOrientation());
            tile.addCoordinates(p.getCoords());

            //get the appropriate image
            img = ImageHandler.getTileImage(tile);

            //add the tile to the board
            boardProper.add(img, p.getColumn(), p.getRowAsInt());
            textWarning.setText("");

            //store p as prevPlacement
            prevPlacement = p;
        }
        else
        { //otherwise warn user that placement string is wrong
            textWarning.setText("Bad placement string! Try again.");
        }

    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {

        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        textField.setOnKeyPressed(ae ->
        { //added key press event to textfield because having to press the button is annoying
            KeyCode key = ae.getCode();
            if(key == KeyCode.ENTER)
            {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            makePlacement(textField.getText());
            textField.clear();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    /**
     * The makeBoard() method sets up the edges of the board, as well as the
     * board proper, as GridPanes.
     * The eastEdge, westEdge and boardProper are housed within a HBox (middleContainer),
     * which is in turn sandwiched between the northEdge and southEdge and housed in the
     * board VBox.
     * Green squares are used for the edges, blue for the board and red for the center tiles.
     */
    private void makeBoard()
    {
        GridPane northEdge = new GridPane();
        GridPane eastEdge = new GridPane();
        GridPane southEdge = new GridPane();
        GridPane westEdge = new GridPane();
        HBox middleContainer = new HBox();
        boardProper = new GridPane();
        ImageView tile;

        //make northEdge
        northEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            {
                tile = ImageHandler.getExitImage('N', 'H');
            }
            else if(i==4)
            {
                tile = ImageHandler.getExitImage('N', 'R');
            }
            else
            {
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            northEdge.add(tile, i, 0);
        }

        //make eastEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            {
                tile = ImageHandler.getExitImage('E', 'R');
            }
            else if(i==3)
            {
                tile = ImageHandler.getExitImage('E', 'H');
            }
            else
            {
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            eastEdge.add(tile, 0, i);
        }

        //make southEdge
        southEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            {
                tile = ImageHandler.getExitImage('S', 'H');
            }
            else if(i==4)
            {
                tile = ImageHandler.getExitImage('S', 'R');
            }
            else
            {
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            southEdge.add(tile, i, 0);
        }

        //make westEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            {
                tile = ImageHandler.getExitImage('W', 'R');
            }
            else if(i==3)
            {
                tile = ImageHandler.getExitImage('W', 'H');
            }
            else
            {
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            westEdge.add(tile, 0, i);
        }

        //make boardProper
        makeBoardProper();

        //add to middle container
        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
        middleContainer.setAlignment(Pos.CENTER);
        board.getChildren().addAll(northEdge, middleContainer, southEdge);
        board.setAlignment(Pos.CENTER);
    }

    /**
     * The makeBoardProper() method makes the boardProper GridPane.
     */
    private void makeBoardProper()
    {
        ImageView tile;
        for(int y=0; y<7; y++)
        {
            for(int x=0; x<7; x++)
            {
                if(y > 1 && y < 5 && x > 1 && x < 5)
                { //create center tile
                    tile = ImageHandler.getMiscTile("CENTER_TILE");
                }
                else
                { //create blank tile
                    tile = ImageHandler.getMiscTile("BLANK_TILE");
                }
                StringBuilder id = new StringBuilder();
                id.append((char)(y + 65));
                id.append(x);
                tile.setId(id.toString());
                boardProper.add(tile, y, x);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        boardData = new Board();
        makeBoard();
        makeControls();
        textWarning = new Text();
        textWarning.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        textWarning.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().add(board);
        root.getChildren().add(textWarning);
        root.getChildren().add(controls);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
