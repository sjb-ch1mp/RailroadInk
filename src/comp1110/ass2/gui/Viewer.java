package comp1110.ass2.gui;


import comp1110.ass2.Board;
import comp1110.ass2.Placement;
import javafx.geometry.Insets;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import static javafx.scene.layout.BorderStroke.MEDIUM;

/* IMPORT STATEMENTS FOR TASK 4
import comp1110.ass2.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
*/

/**
 * A very simple viewer for tile placements in the Railroad Ink game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various tile placements.
 */
public class Viewer extends Application {

    /* GAME ASSETS*/
    private Stage gameStage;
    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 768;
    private Text txtPlayer;
    private Text txtRound;
    private Board playerOneBoardData;
    private int player;
    private char gameMode;
    private Board opponentBoardData;
    //private Agent computerOpponent;
    private Dices diceData;
    private SpecialTiles sTilesData;
    private HBox gameInfo; //player and round
    private VBox boardContainer; //holds full board
    private GridPane boardProper; //actual board
    private VBox gameContainer; //holds gameInfo, boardContainer and notificationText
    private VBox menuUI; //main menu and game summary
    private VBox dicesUI; //dices and roll button
    private VBox specialUI; //special tiles
    private VBox controlsContainer; //menuUI, dicesUI and specialUI
    private String selected; //tracks which tile is selected
    private Placement placement;
    private Text txtNotification;

    /* LAUNCHER ASSETS */
    private Stage launchStage;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        launchStage = buildLaunchStage(primaryStage);
        launchStage.show();
    }

    private Stage buildLaunchStage(Stage launchStage)
    {
        ImageView logo = ImageHandler.getMiscTile("Logo");
        logo.setFitWidth(500);
        logo.setFitHeight(240);

        RadioButton btnSinglePlayer = new RadioButton("Single Player");
        btnSinglePlayer.setMaxSize(170, 10);
        btnSinglePlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        RadioButton btnMultiPlayer = new RadioButton("Multi-player");
        btnMultiPlayer.setMaxSize(170, 10);
        btnMultiPlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        RadioButton btnComputer = new RadioButton("Computer Opponent");
        btnComputer.setMaxSize(170, 10);
        btnComputer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        ToggleGroup tgPlayMode = new ToggleGroup();
        tgPlayMode.getToggles().addAll(btnSinglePlayer, btnMultiPlayer, btnComputer);

        btnSinglePlayer.setSelected(true);
        Button btnPlay = new Button("Play");
        formatButton(btnPlay);
        Text txtNotification = new Text();

        btnPlay.setOnAction(ae ->
        {
            if(btnSinglePlayer.isSelected())
            {
                launchGameStageOnePlayer();
            }
            else if(btnMultiPlayer.isSelected())
            {
                //launchGameStageTwoPlayer();
                txtNotification.setText("Multi-player is still under development!");
            }
            else
            { //btnComputer is selected
                //launchGameStageCompOpponent();
                txtNotification.setText("Computer opponent is still under development!");
            }
        });

        VBox rootLaunch = new VBox();
        rootLaunch.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        rootLaunch.setSpacing(25);
        rootLaunch.setAlignment(Pos.CENTER);
        rootLaunch.getChildren().addAll(logo, btnSinglePlayer, btnMultiPlayer, btnComputer, btnPlay, txtNotification);

        launchStage.setScene(new Scene(rootLaunch, 550, 550));
        launchStage.setResizable(false);
        launchStage.setTitle("Railroad Ink");

        return launchStage;
    }

    private void quitQuery()
    {
        Stage quitStage = new Stage();
        quitStage.setTitle("Return to menu");
        Text txtQuit = new Text("Are you sure you want to quit?");
        formatText(txtQuit, 20, true, false);
        Button btnYes = new Button("Yes");
        formatButton(btnYes);
        Button btnNo = new Button("No");
        formatButton(btnNo);
        btnYes.setOnAction(ae ->
        {
            quitStage.close();
            gameStage.close();
            start(new Stage());
        });
        btnNo.setOnAction(ae -> quitStage.close());
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnYes, btnNo);
        formatBox(buttons, Color.LAVENDER, 50, false);
        VBox root = new VBox();
        formatBox(root, Color.LAVENDER, 10, false);
        root.getChildren().addAll(txtQuit, buttons);

        quitStage.setResizable(false);
        quitStage.setScene(new Scene(root, 300, 100));
        quitStage.show();
    }

    private void launchGameStageOnePlayer()
    {
        gameMode = 's';
        player = 1;

        //set up gameContainer
        playerOneBoardData = new Board();
        boardProper = new GridPane();
        setUpGameInfo();
        setUpBoard(playerOneBoardData);
        txtNotification = new Text();
        formatText(txtNotification, 30, true, false);
        gameContainer = new VBox();
        formatBox(gameContainer, Color.LIGHTBLUE, 10, false);
        gameContainer.getChildren().addAll(gameInfo, boardContainer, txtNotification);

        //set up controlsContainer
        diceData = new Dices();
        dicesUI = new VBox();
        setUpDiceUI();
        specialUI = new VBox();
        sTilesData = new SpecialTiles();
        setUpSpecialUI();
        controlsContainer = new VBox();
        formatBox(controlsContainer, Color.LIGHTBLUE, 10, false);
        Button btnMenu = new Button("Main Menu");
        formatButton(btnMenu);
        btnMenu.setOnAction(ae -> quitQuery());
        ImageView logo = ImageHandler.getMiscTile("Logo");
        logo.setFitWidth(180);
        logo.setFitHeight(90);
        controlsContainer.getChildren().addAll(logo, btnMenu, dicesUI, specialUI);

        //set up root
        HBox rootGame = new HBox();
        formatBox(rootGame, Color.LIGHTBLUE, 10, false);
        rootGame.getChildren().addAll(gameContainer, controlsContainer);

        //close launch page
        launchStage.close();

        //launch game
        gameStage = new Stage();
        gameStage.setTitle("Railroad Ink");
        gameStage.setScene(new Scene(rootGame, GAME_WIDTH, GAME_HEIGHT));
        gameStage.setResizable(false);
        gameStage.show();
    }

    private void launchGameStageTwoPlayer()
    {
        gameMode = 'm';
    }

    private void launchGameStageCompOpponent()
    {
        gameMode = 'c';
    }

    private void setUpGameInfo()
    {
        txtPlayer = new Text("~ Player 1 ~");
        formatText(txtPlayer, 30, true, false);
        txtRound = new Text("~ Round 1 ~");
        formatText(txtRound, 30, true, false);
        gameInfo = new HBox();
        formatBox(gameInfo, Color.LIGHTBLUE, 150, false);
        gameInfo.getChildren().addAll(txtPlayer, txtRound);
    }

    private void setUpDiceUI()
    {
        Text txtDices = new Text("Dices");
        formatText(txtDices, 20, true, true);

        ImageView D1 = (diceData.isUsed("D1"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceData.getDice("D1"));
        D1.setId("D1");
        setUpSelectAndRotate(D1);

        ImageView D2 = (diceData.isUsed("D2"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceData.getDice("D2"));
        D2.setId("D2");
        setUpSelectAndRotate(D2);

        HBox diceRowOne = setUpRow(D1, D2);

        ImageView D3 = (diceData.isUsed("D3"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceData.getDice("D3"));
        D3.setId("D3");
        setUpSelectAndRotate(D3);

        ImageView D4 = (diceData.isUsed("D4"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceData.getDice("D4"));
        D4.setId("D4");
        setUpSelectAndRotate(D4);

        HBox diceRowTwo = setUpRow(D3, D4);

        Button btnRoll = new Button("Roll");
        formatButton(btnRoll);
        btnRoll.setOnAction(ae ->
        {
            txtNotification.setText("");
            sTilesData.resetCounterRound();

            if(playerOneBoardData.iterateRoundCounter())
            {
                //check that round conditions have been met: are there any legal moves available?
                if(selected != null && selected.charAt(0) == 'S')
                {
                    sTilesData.deselectAll();
                    setUpSpecialUI();
                }
                else
                {
                    diceData.deselectAll();
                }
                selected = null;

                diceData.rollDice();
                txtRound.setText("~ Round " + playerOneBoardData.getRound() + " ~");
                setUpDiceUI();
            }
            else
            {
                //calculate scores and finish game
            }
        });
        formatBox(dicesUI, Color.LAVENDER, 10, true);
        dicesUI.getChildren().clear();
        dicesUI.getChildren().addAll(txtDices, diceRowOne, diceRowTwo, btnRoll);
    }

    private void setUpSpecialUI()
    {
        Text txtSpecials = new Text("Special Tiles");
        formatText(txtSpecials, 20, true, true);
        ImageView S1 = (sTilesData.isUsed("S1"))?
            ImageHandler.getMiscTile("invalid"):
            ImageHandler.getTileImage(sTilesData.getSpecialTile("S1"));
        S1.setId("S1");
        setUpSelectAndRotate(S1);

        ImageView S2 = (sTilesData.isUsed("S2"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(sTilesData.getSpecialTile("S2"));
        S2.setId("S2");
        setUpSelectAndRotate(S2);

        HBox specialRowOne = setUpRow(S1, S2);

        ImageView S3 = (sTilesData.isUsed("S3"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(sTilesData.getSpecialTile("S3"));
        S3.setId("S3");
        setUpSelectAndRotate(S3);

        ImageView S4 = (sTilesData.isUsed("S4"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(sTilesData.getSpecialTile("S4"));
        S4.setId("S4");
        setUpSelectAndRotate(S4);

        HBox specialRowTwo = setUpRow(S3, S4);

        ImageView S5 = (sTilesData.isUsed("S5"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(sTilesData.getSpecialTile("S5"));
        S5.setId("S5");
        setUpSelectAndRotate(S5);

        ImageView S6 = (sTilesData.isUsed("S6"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(sTilesData.getSpecialTile("S6"));
        S6.setId("S6");
        setUpSelectAndRotate(S6);

        HBox specialRowThree = setUpRow(S5, S6);

        formatBox(specialUI, Color.LAVENDER, 10, true);
        specialUI.getChildren().clear();
        specialUI.getChildren().addAll(txtSpecials, specialRowOne, specialRowTwo, specialRowThree);
    }

    private void setUpSelectAndRotate(ImageView tile)
    {
        tile.setOnMouseClicked(ae ->
        {
            if(tile.getImage().getUrl().contains("invalid.png")) return;

            txtNotification.setText("");

            //deselect previous tile
            if(selected != null)
            {
                if(selected.charAt(0) == 'D')
                {
                    diceData.getDice(selected).deselectTile();
                    setUpDiceUI();
                }
                else
                {
                    sTilesData.getSpecialTile(selected).deselectTile();
                    setUpSpecialUI();
                }
            }

            if(ae.getButton().equals(MouseButton.PRIMARY))
            {
                selected = tile.getId();

                //highlight selected tile
                if(tile.getId().charAt(0) == 'D')
                {
                    diceData.getDice(selected).selectTile();
                    placement = new Placement(diceData.getDice(tile.getId()));
                    setUpDiceUI();
                }
                else
                {
                    sTilesData.getSpecialTile(tile.getId()).selectTile();
                    placement = new Placement(sTilesData.getSpecialTile(tile.getId()));
                    setUpSpecialUI();
                }
            }
            else if(ae.getButton().equals(MouseButton.SECONDARY))
            {
                if(tile.getId().charAt(0) == 'D')
                { //tile is a dice
                    diceData.rotateDice(tile.getId());
                    setUpDiceUI();
                }
                else
                { //tile is special
                    sTilesData.rotateTile(tile.getId());
                    setUpSpecialUI();
                }
            }
        });
    }

    private void setUpDropTarget(ImageView tile)
    {
        tile.setOnMouseClicked(ae ->
        {
            if(placement == null) return;

            if(ae.getButton().equals(MouseButton.PRIMARY))
            {
                placement.updateCoordinates(tile.getId());

                Board board;
                if(gameMode == 'c')
                {
                    board = playerOneBoardData;
                }
                else
                {
                    board = (player == 1)?playerOneBoardData:opponentBoardData;
                }

                if(selected.charAt(0) == 'D')
                { //dice is selected
                    if(board.addTile(placement.toString()))
                    {
                        makeBoardProper(board);
                        diceData.useDice(selected);
                        setUpDiceUI();
                        selected = null;
                        placement = null;
                    }
                    else
                    {
                        txtNotification.setText("Illegal placement!");
                    }
                }
                else
                {
                    if(sTilesData.getCounterGame() < 3 && sTilesData.getCounterRound() == 0)
                    {
                        if(board.addTile(placement.toString()))
                        {
                            makeBoardProper(board);
                            sTilesData.useSpecialTile(selected);
                            setUpSpecialUI();
                            selected = null;
                            placement = null;
                        }
                        else
                        {
                            txtNotification.setText("Illegal placement!");
                        }
                    }
                    else
                    {
                        txtNotification.setText((sTilesData.getCounterRound() == 1)?
                                "You can only use 1 special tile per round!":
                                "You can only use 3 special tiles per game!");
                    }
                }
            }
        });
    }

    private HBox setUpRow(ImageView imgOne, ImageView imgTwo)
    {
        HBox row = new HBox();
        row.setSpacing(10);
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(imgOne, imgTwo);
        return row;
    }

    private void formatBox(Pane controlBox, Color color, double spacing, boolean border)
    {
        controlBox.setBackground(new Background(new BackgroundFill(color, null, null)));
        controlBox.setPadding(new Insets(10));

        VBox vbox;
        HBox hbox;
        if(controlBox.getClass().getSimpleName().equals("VBox"))
        {
            vbox = (VBox) controlBox;
            vbox.setSpacing(spacing);
            vbox.setAlignment(Pos.CENTER);
        }
        else
        {
            hbox = (HBox) controlBox;
            hbox.setSpacing(spacing);
            hbox.setAlignment(Pos.CENTER);
        }

        if(border)
        {
            controlBox.setBorder(new Border(new BorderStroke(Color.MEDIUMPURPLE, BorderStrokeStyle.SOLID, null, MEDIUM)));
        }
    }

    private void formatText(Text text, double size, boolean bold, boolean underline)
    {
        if(bold)
        {
            text.setFont(Font.font("Garamond", FontWeight.BOLD, size));
        }
        else
        {
            text.setFont(Font.font("Garamond", size));
        }

        text.setUnderline(underline);

    }

    private void formatButton(Button button)
    {
        button.setFont(Font.font("Garamond", FontWeight.BOLD, 14));
    }

    private void setUpBoard(Board boardData)
    {
        GridPane northEdge = new GridPane();
        GridPane eastEdge = new GridPane();
        GridPane southEdge = new GridPane();
        GridPane westEdge = new GridPane();
        HBox middleContainer = new HBox(); //this container holds the center of the board configuration (east, boardProper and west)
        ImageView tile;

        //make northEdge
        northEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            { //if this tile is highway exit
                tile = ImageHandler.getExitImage('N', 'H');
            }
            else if(i==4)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('N', 'R');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            northEdge.add(tile, i, 0);
        }

        //make eastEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('E', 'R');
            }
            else if(i==3)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('E', 'H');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            eastEdge.add(tile, 0, i);
        }

        //make southEdge
        southEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('S', 'H');
            }
            else if(i==4)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('S', 'R');
            }
            else
            { //otherwise get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            southEdge.add(tile, i, 0);
        }

        //make westEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('W', 'R');
            }
            else if(i==3)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('W', 'H');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            westEdge.add(tile, 0, i);
        }

        //make boardProper
        makeBoardProper(boardData);

        //add to middle container
        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
        middleContainer.setAlignment(Pos.CENTER);

        //add everything to the board
        boardContainer = new VBox();
        boardContainer.getChildren().addAll(northEdge, middleContainer, southEdge);
        boardContainer.setAlignment(Pos.CENTER);
    }

     private void makeBoardProper(Board boardData)
     {
         ImageView tile;
         for(int y=0; y<7; y++)
         {
             for(int x=0; x<7; x++)
             {
                 //build the id from the x and y values
                 StringBuilder id = new StringBuilder();
                 id.append((char)(y + 65));
                 id.append(x);

                 if(boardData.getPlacements().containsKey(id.toString()))
                 { //there is a placement here
                    tile = ImageHandler.getTileImage(boardData.getTile(id.toString()));
                 }
                 else
                 {
                     if(y > 1 && y < 5 && x > 1 && x < 5)
                     { //create center tile
                         tile = ImageHandler.getMiscTile("CENTER_TILE");
                     }
                     else
                     { //create blank tile
                         tile = ImageHandler.getMiscTile("BLANK_TILE");
                     }
                 }

                 //add to the board
                 tile.setId(id.toString());
                 setUpDropTarget(tile);
                 boardProper.add(tile, x, y);
             }
         }
     }



    /* ===========================================
       ALL METHODS BELOW THIS POINT ARE FOR TASK 4
       ===========================================

    /* board layout *
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private CheckBox withRules; //This checkbox is selected to apply the placement rules of the game
    private Board boardData = null; //This Board object stores tiles when the rules are applied
    private final HBox boardAndTiles = new HBox(); //Contains the board and the tileContainer
    private final VBox tileContainer = new VBox(); //Contains the columns of tiles
    private final VBox board = new VBox(); //Contains the board and textWarning
    private GridPane boardProper; //The GridPane for the actual board
    private final VBox root = new VBox();
    private final Group controls = new Group();
    private ArrayList<Placement> prevPlacements = null; //Holds information about previous placements so that they can be removed
    private TextField textField;
    private Text textWarning;
    private Button btnReset; //Clears the board

    /**
     * This is the primary method that controls the flow of the placement string,
     * depending upon whether a board string or single placement string has been entered
     * and also whether or not the rules are being applied.
     *
     * @param placement A valid placement string
     *
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer

        if(placement.length() > 5 && RailroadInk.isBoardStringWellFormed(placement))
        { //placement is a valid board string
            if(prevPlacements != null)
            { //if this is not the first placement
                resetBoard();
                prevPlacements.clear();
            }
            for(int i=0; i<placement.length(); i+= 5)
            {//place each placement in the board string onto the board
                makeSinglePlacement(placement.substring(i, i+5));
            }
        }
        else if(placement.length() == 5 && RailroadInk.isTilePlacementWellFormed(placement))
        { //placement is a valid single placement string
            if(prevPlacements != null)
            {//if this is not the first placement, clear the previous placement
                resetBoard();
                prevPlacements.clear();
            }//place the placement on the board
            makeSinglePlacement(placement);
        }
        else
        { //otherwise, placement is illegal
            textWarning.setText("Bad placement string! Try again.");
        }
    }

    /**
     * The makePlacementWithRules() method is called when the withRules checkbox
     * is selected. This applies the placement rules of the game to each placement
     * by attempting to add the tile to the Board object. If it successfully adds the
     * Tile, then the placement is legal and the image is shown on the board.
     *
     * @param placement A valid placement String
     *
    void makePlacementWithRules(String placement)
    {
        if(placement.length() > 5)
        { //According to the rules, you can only place one tiles at a time
            textWarning.setText("You may only place one tile at a time.");
        }
        else if(placement.length() == 5 && RailroadInk.isTilePlacementWellFormed(placement))
        { //A valid placement string
            if(boardData.addTile(placement))
            { //if the tile is successfully added to the board
                makeSinglePlacement(placement); //paint it on the UI
            }
            else
            { //the placement violates the rules of the game
                textWarning.setText("Illegal placement! Try again.");
            }
        }
        else
        { //otherwise, the placement string is invalid
            textWarning.setText("Bad placement string! Try again.");
        }
    }

    /**
     * This method paints a single tile placement on the board UI.
     *
     * @param placement A valid placement string
     *
    void makeSinglePlacement(String placement)
    {
        //create a Placement object to access components of placement
        Placement p = new Placement(placement);
        ImageView img;

        //create tile for this placement
        Tile tile = new Tile(p.getFullId());
        tile.updateOrientation(p.getOrientation());
        tile.addCoordinates(p.getCoords());

        //get the appropriate image
        img = ImageHandler.getTileImage(tile);

        //add the tile to the board
        boardProper.add(img, p.getColumn(), p.getRowAsInt());
        textWarning.setText("");

        if(prevPlacements == null)
        { //if this is the first placement, create the prevPlacements ArrayList
            prevPlacements = new ArrayList<>();
        }
        prevPlacements.add(new Placement(placement)); //add the placement to prevPlacements

    }

    /**
     * This method resets the board by painting blank tiles over all previous placements.
     *
    private void resetBoard()
    {
        textWarning.setText("");
        if(prevPlacements != null)
        {
            for(Placement p : prevPlacements)
            { //refresh each tile that has a previous placement
                refreshTile(p);
            }
        }
        if(boardData != null)
        { //clear data from the Board object
            boardData = new Board();
        }
    }

    /**
     * This method paints a blank tile over a previous placement on the board.
     *
     * @param prevPlacement A previous placement on the board
     *
    private void refreshTile(Placement prevPlacement)
    {
        Text id = new Text(prevPlacement.getCoords()); //get the coordinates of the previous placement
        StackPane layers = new StackPane();
        ImageView img;

        if(boardData.isCenterCoord(prevPlacement.getCoords()))
        { //if the coordinates are in the centre of the board, get the image for a center tile
            img = ImageHandler.getMiscTile("CENTER_TILE");
        }
        else
        { //otherwise, get the image for a blank tile
            img = ImageHandler.getMiscTile("BLANK_TILE");
        }


        layers.getChildren().addAll(img, id); //layer the text on top of the tile image
        boardProper.add(layers, prevPlacement.getColumn(), prevPlacement.getRowAsInt()); //replace previous placement with blank/center tile
    }

    /**
     * Create a basic text field for input and a refresh button.
     *
    private void makeControls() {
        Label label1 = new Label("Placement:");
        Button button = new Button("Refresh");
        withRules = new CheckBox("Rules");
        textField = new TextField();
        textField.setPrefWidth(300);

        withRules.setOnAction(ae ->
        { //if with rules is selected
            textWarning.setText(""); //refresh the warning text
            resetBoard(); //reset the board
            if(!btnReset.isVisible())
            { //if the reset button is not visible, make it visible
                btnReset.setVisible(true);
                button.setVisible(false);
            }
            else
            { //otherwise it is not needed - make it invisible
                btnReset.setVisible(false);
                button.setVisible(true);
            }
        });

        button.setOnAction(e -> {
            textWarning.setText(""); //refresh the warning text
            if(textField.getText().equals(""))
            {
                resetBoard();
            }
            else if(withRules.isSelected())
            { //if the rules are being applied
                makePlacementWithRules(textField.getText());
                textField.clear();
            }
            else
            { //otherwise, do an unchecked placement
                makePlacement(textField.getText());
                textField.clear();
            }
        });

        textField.setOnKeyPressed(ae ->
        { //added key press event to textfield because having to press the button is annoying
            KeyCode key = ae.getCode();
            if(key == KeyCode.ENTER)
            { //if enter is pressed within the text field, the user is finished typing
                button.fire();
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button, withRules);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    /**
     * This method makes the sub-UI that contains all the tiles for the user to reference.
     *
    private void makeTiles()
    {

        ImageView img;
        Text title = new Text("Tiles"); //the title for the sub-UI
        title.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        Text tileId;
        HBox tileColumnContainer = new HBox(); //the container that holds the columns of tiles

        for(int i=0; i<3; i++)
        { //cycle through three times as there are three types of tiles

            VBox tileColumn = new VBox(); //the column for this tile type
            int limit; //the number of tiles of this type
            char type; //the type of these tile
            switch(i)
            { //select the appropriate type and its number
                case 0: type = 'A'; limit = 6; break;
                case 1: type = 'B'; limit = 3; break;
                default: type = 'S'; limit = 6;
            }
            for(int j=0; j<limit; j++)
            { //get the name and image of each file of this type and add it to the column
                tileId = new Text(type + "" + j);
                img = ImageHandler.getTileImage(new Tile(type + "" + j));
                tileColumn.getChildren().addAll(tileId, img);
            }

            //add the column to the column container
            tileColumn.setSpacing(5);
            tileColumnContainer.getChildren().add(tileColumn);
        }

        //clean up the presentation
        tileColumnContainer.setSpacing(10);
        tileContainer.getChildren().addAll(title, tileColumnContainer);
        tileContainer.setAlignment(Pos.CENTER);
        tileContainer.setSpacing(10);
        tileContainer.setPadding(new Insets(5));
        tileContainer.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        tileContainer.setBorder(new Border(new BorderStroke(Color.MEDIUMPURPLE, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));
    }

    /**
     * The makeBoard() method sets up the edges of the board, as well as the
     * board proper, as GridPanes.
     * The eastEdge, westEdge and boardProper are housed within a HBox (middleContainer),
     * which is in turn sandwiched between the northEdge and southEdge and housed in the
     * board VBox.
     * Green squares are used for the edges, blue for the board and red for the center tiles.
     *
    private void makeBoard()
    {
        GridPane northEdge = new GridPane();
        GridPane eastEdge = new GridPane();
        GridPane southEdge = new GridPane();
        GridPane westEdge = new GridPane();
        HBox middleContainer = new HBox(); //this container holds the center of the board configuration (east, boardProper and west)
        boardProper = new GridPane();
        ImageView tile;

        //make northEdge
        northEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            { //if this tile is highway exit
                tile = ImageHandler.getExitImage('N', 'H');
            }
            else if(i==4)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('N', 'R');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            northEdge.add(tile, i, 0);
        }

        //make eastEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('E', 'R');
            }
            else if(i==3)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('E', 'H');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            eastEdge.add(tile, 0, i);
        }

        //make southEdge
        southEdge.setAlignment(Pos.CENTER);
        for(int i=0; i<9; i++)
        {
            if(i==2 || i==6)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('S', 'H');
            }
            else if(i==4)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('S', 'R');
            }
            else
            { //otherwise get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            southEdge.add(tile, i, 0);
        }

        //make westEdge
        for(int i=0; i<7; i++)
        {
            if(i==1 || i==5)
            { //if this tile is a railroad exit
                tile = ImageHandler.getExitImage('W', 'R');
            }
            else if(i==3)
            { //if this tile is a highway exit
                tile = ImageHandler.getExitImage('W', 'H');
            }
            else
            { //otherwise, get a blank edge tile
                tile = ImageHandler.getMiscTile("EDGE_TILE");
            }
            westEdge.add(tile, 0, i);
        }

        //make boardProper
        makeBoardProper();

        //add to middle container
        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
        middleContainer.setAlignment(Pos.CENTER);

        //add everything to the board
        board.getChildren().addAll(northEdge, middleContainer, southEdge);
        board.setAlignment(Pos.CENTER);
    }

    /**
     * The makeBoardProper() method makes the boardProper GridPane.
     *
    private void makeBoardProper()
    {
        StackPane layers;
        ImageView tile;
        Text coordinates;
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

                //build the id from the x and y values
                StringBuilder id = new StringBuilder();
                id.append((char)(x + 65));
                id.append(y);
                tile.setId(id.toString());

                //layer the text over the image of the tile
                coordinates = new Text(id.toString());
                layers = new StackPane();
                layers.getChildren().addAll(tile, coordinates);

                //add to the board
                boardProper.add(layers, y, x);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        boardData = new Board();
        makeBoard();
        makeTiles();
        boardAndTiles.getChildren().addAll(board, tileContainer);
        boardAndTiles.setAlignment(Pos.CENTER);
        boardAndTiles.setSpacing(10);

        makeControls();
        textWarning = new Text();
        textWarning.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        textWarning.setTextAlignment(TextAlignment.CENTER);
        btnReset = new Button("Reset board");
        btnReset.setVisible(false);
        btnReset.setOnAction(ae -> resetBoard());

        root.getChildren().add(btnReset);
        root.getChildren().add(boardAndTiles);
        root.getChildren().add(textWarning);
        root.getChildren().add(controls);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    */
}
