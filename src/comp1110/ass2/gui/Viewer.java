package comp1110.ass2.gui;

/* IMPORT STATEMENTS FOR GAME */
import comp1110.ass2.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
import javafx.scene.image.ImageView;
import java.util.HashMap;
import static javafx.scene.layout.BorderStroke.MEDIUM;

/* IMPORT STATEMENTS FOR VIEWER */
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;

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
    private Text txtRound;
    private HashMap<Integer, PlayerData> playerData;
    private Board boardRef;
    private Dices diceRef;
    private SpecialTiles specialRef;
    private char gameMode;
    private int player;
    private ComputerOpponent computerOpponent;
    private HBox gameInfo; //player and round
    private VBox boardContainer; //holds full board
    private GridPane boardProper; //actual board
    private VBox dicesUI; //dices and roll button
    private VBox specialUI; //special tiles
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

        RadioButton btnViewer = new RadioButton("Use Viewer");
        btnViewer.setMaxSize(170, 10);
        btnViewer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        ToggleGroup tgPlayMode = new ToggleGroup();
        tgPlayMode.getToggles().addAll(btnSinglePlayer, btnMultiPlayer, btnComputer, btnViewer);

        btnSinglePlayer.setSelected(true);
        Button btnPlay = new Button("Play");
        formatButton(btnPlay);
        Text txtNotification = new Text();

        btnPlay.setOnAction(ae ->
        {
            if(btnSinglePlayer.isSelected())
            {
                gameMode = 's';
                player = 1;
                launchGameStage();
            }
            else if(btnMultiPlayer.isSelected())
            {
                gameMode = 'm';
                player = 1;
                launchGameStage();
            }
            else if(btnComputer.isSelected())
            {
                gameMode = 'c';
                player = 1;
                launchGameStage();
            }
            else
            { //btnViewer is selected
                gameMode = 'v';
                launchViewer();
            }
        });

        VBox rootLaunch = new VBox();
        rootLaunch.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        rootLaunch.setSpacing(25);
        rootLaunch.setAlignment(Pos.CENTER);
        rootLaunch.getChildren().addAll(logo, btnSinglePlayer, btnMultiPlayer, btnComputer, btnViewer, btnPlay, txtNotification);

        launchStage.setScene(new Scene(rootLaunch, 550, 550));
        launchStage.setResizable(false);
        launchStage.setTitle("Railroad Ink");

        return launchStage;
    }

    private void launchGameStage()
    {
        //if this is the first launch, initialise playerData
        if(playerData == null)
        {
            playerData = new HashMap<>(0);

            if(gameMode == 's')
            {
                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.get(1).diceData.rollDice();
            }
            else if(gameMode == 'm')
            {
                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.put(2, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.get(1).diceData.rollDice();
                playerData.get(2).diceData.copyPlayerOneData(playerData.get(1).diceData);
            }
            else
            {
                //gameMode = 'c'
                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.get(1).diceData.rollDice();
                computerOpponent = new ComputerOpponent(new PlayerData(2, new Board(), new Dices(), new SpecialTiles()));
                computerOpponent.playerData.diceData.copyPlayerOneData(playerData.get(1).diceData);
            }
        }

        //set up local reference variables for this launch
        boardRef = playerData.get(player).boardData;
        diceRef = playerData.get(player).diceData;
        specialRef = playerData.get(player).specialData;

        //set up gameContainer
        boardProper = new GridPane();
        setUpGameInfo();
        setUpBoard();
        txtNotification = new Text();
        formatText(txtNotification, 30, true, false);
        VBox gameContainer = new VBox();
        formatBox(gameContainer, Color.LIGHTBLUE, 10, false);
        gameContainer.getChildren().addAll(gameInfo, boardContainer, txtNotification);

        //set up controlsContainer
        dicesUI = new VBox();
        setUpDiceUI();
        specialUI = new VBox();
        setUpSpecialUI();
        VBox controlsContainer = new VBox();
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
            playerData = null;
            quitStage.close();
            if(gameMode == 'v')
            {
                viewerStage.close();
            }
            else
            {
                gameStage.close();
            }
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

    private void setUpGameInfo()
    {
        Text txtPlayer = new Text("~ Player " + player + " ~");
        formatText(txtPlayer, 30, true, false);
        txtRound = new Text("~ Round " + boardRef.getRound() + " ~");
        formatText(txtRound, 30, true, false);
        gameInfo = new HBox();
        formatBox(gameInfo, Color.LIGHTBLUE, 150, false);
        gameInfo.getChildren().addAll(txtPlayer, txtRound);
    }

    private void setUpDiceUI()
    {
        Text txtDices = new Text("Dices");
        formatText(txtDices, 20, true, true);

        ImageView D1 = (diceRef.isUsed("D1"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceRef.getDice("D1"));
        D1.setId("D1");
        setUpSelectAndRotate(D1);

        ImageView D2 = (diceRef.isUsed("D2"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceRef.getDice("D2"));
        D2.setId("D2");
        setUpSelectAndRotate(D2);

        HBox diceRowOne = setUpRow(D1, D2);

        ImageView D3 = (diceRef.isUsed("D3"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceRef.getDice("D3"));
        D3.setId("D3");
        setUpSelectAndRotate(D3);

        ImageView D4 = (diceRef.isUsed("D4"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(diceRef.getDice("D4"));
        D4.setId("D4");
        setUpSelectAndRotate(D4);

        HBox diceRowTwo = setUpRow(D3, D4);

        Button btnRoll;
        if(gameMode == 's' || gameMode == 'c')
        {
            btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"Roll");
        }
        else
        {
            if(player == 1)
            {
                btnRoll = new Button("End Turn");
            }
            else
            {
                btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"End Turn");
            }
        }

        formatButton(btnRoll);
        btnRoll.setOnAction(ae ->
        {
            txtNotification.setText("");

            if(gameMode == 'm')
            { //two player
                if(!boardRef.legalMovesRemaining(diceRef))
                {
                    if(player == 1)
                    {
                        boardRef.iterateRoundCounter();
                        specialRef.resetCounterRound();

                        player = 2;
                        gameStage.close();
                        launchGameStage();
                        txtRound.setText("~ Round " + boardRef.getRound() + " ~");

                        if(boardRef.getRound() == 7)
                        {
                            btnRoll.setText("End Game");
                        }
                    }
                    else
                    { //player = 2
                        if(btnRoll.getText().equals("End Game"))
                        {// this is round 7
                            //Calculate scores and end game
                        }
                        else
                        {
                            boardRef.iterateRoundCounter();
                            specialRef.resetCounterRound();

                            player = 1;
                            gameStage.close();
                            launchGameStage();
                            diceRef.rollDice();
                            playerData.get(2).diceData.copyPlayerOneData(diceRef);

                            setUpDiceUI();
                            txtRound.setText("~ Round " + boardRef.getRound() + " ~");
                        }

                    }
                }
                else
                {
                    txtNotification.setText("You must place all of the dice!");
                }
            }
            else
            { //gameMode == 'c' or 's'
                if(!boardRef.legalMovesRemaining(diceRef))
                { //if there are no more legal moves this round
                    if(btnRoll.getText().equals("End Game"))
                    { //this is round 7
                        //calculate score and end game
                        if(gameMode == 's')
                        {
                            txtNotification.setText("Well done! Your score is " + playerData.get(1).boardData.calculateScore());
                            btnRoll.setVisible(false);
                        }
                        else
                        { //gameMode = 'c'

                        }
                    }
                    else
                    {
                        if(gameMode == 'c')
                        {
                            computerOpponent.haveTurn();
                        }

                        boardRef.iterateRoundCounter();
                        specialRef.resetCounterRound();
                        diceRef.rollDice();

                        if(gameMode == 'c')
                        {
                            computerOpponent.playerData.diceData.copyPlayerOneData(diceRef);
                        }

                        setUpDiceUI();
                        txtRound.setText("~ Round " + boardRef.getRound() + " ~");

                        if(boardRef.getRound() == 7)
                        {
                            btnRoll.setText("End Game");
                        }
                    }
                }
                else
                {
                    txtNotification.setText("You must place all of the dice!");
                }
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
        ImageView S1 = (specialRef.isUsed("S1"))?
            ImageHandler.getMiscTile("invalid"):
            ImageHandler.getTileImage(specialRef.getSpecialTile("S1"));
        S1.setId("S1");
        setUpSelectAndRotate(S1);

        ImageView S2 = (specialRef.isUsed("S2"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(specialRef.getSpecialTile("S2"));
        S2.setId("S2");
        setUpSelectAndRotate(S2);

        HBox specialRowOne = setUpRow(S1, S2);

        ImageView S3 = (specialRef.isUsed("S3"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(specialRef.getSpecialTile("S3"));
        S3.setId("S3");
        setUpSelectAndRotate(S3);

        ImageView S4 = (specialRef.isUsed("S4"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(specialRef.getSpecialTile("S4"));
        S4.setId("S4");
        setUpSelectAndRotate(S4);

        HBox specialRowTwo = setUpRow(S3, S4);

        ImageView S5 = (specialRef.isUsed("S5"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(specialRef.getSpecialTile("S5"));
        S5.setId("S5");
        setUpSelectAndRotate(S5);

        ImageView S6 = (specialRef.isUsed("S6"))?
                ImageHandler.getMiscTile("invalid"):
                ImageHandler.getTileImage(specialRef.getSpecialTile("S6"));
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
                    diceRef.getDice(selected).deselectTile();
                    setUpDiceUI();
                }
                else
                {
                    specialRef.getSpecialTile(selected).deselectTile();
                    setUpSpecialUI();
                }
            }

            if(ae.getButton().equals(MouseButton.PRIMARY))
            {
                selected = tile.getId();

                //highlight selected tile
                if(tile.getId().charAt(0) == 'D')
                {
                    diceRef.getDice(selected).selectTile();
                    placement = new Placement(diceRef.getDice(tile.getId()));
                    setUpDiceUI();
                }
                else
                {
                    specialRef.getSpecialTile(tile.getId()).selectTile();
                    placement = new Placement(specialRef.getSpecialTile(tile.getId()));
                    setUpSpecialUI();
                }
            }
            else if(ae.getButton().equals(MouseButton.SECONDARY))
            {
                if(tile.getId().charAt(0) == 'D')
                { //tile is a dice
                    diceRef.rotateDice(tile.getId());
                    setUpDiceUI();
                }
                else
                { //tile is special
                    specialRef.rotateTile(tile.getId());
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

                if(selected.charAt(0) == 'D')
                { //dice is selected
                    if(boardRef.addTile(placement.toString()))
                    {
                        makeBoardProper();
                        diceRef.useDice(selected);
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
                    if(specialRef.getCounterGame() < 3 && specialRef.getCounterRound() == 0)
                    {
                        if(boardRef.addTile(placement.toString()))
                        {
                            makeBoardProper();
                            specialRef.useSpecialTile(selected);
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
                        txtNotification.setText((specialRef.getCounterRound() == 1)?
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

    private void setUpBoard()
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
        makeBoardProper();

        //add to middle container
        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
        middleContainer.setAlignment(Pos.CENTER);

        //add everything to the board
        boardContainer = new VBox();
        boardContainer.getChildren().addAll(northEdge, middleContainer, southEdge);
        boardContainer.setAlignment(Pos.CENTER);
    }

     private void makeBoardProper()
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

                 if(gameMode != 'v' && boardRef.getPlacements().containsKey(id.toString()))
                 { //there is a placement here
                    tile = ImageHandler.getTileImage(boardRef.getTile(id.toString()));
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

                 tile.setId(id.toString());

                 if(gameMode == 'v')
                 {
                     //layer the text over the image of the tile
                     Text coordinates = new Text(id.toString());
                     StackPane layers = new StackPane();
                     layers.getChildren().addAll(tile, coordinates);

                     //add to the board
                     boardProper.add(layers, x, y);
                 }
                 else
                 {
                     //add to the board
                     setUpDropTarget(tile);
                     boardProper.add(tile, x, y);
                 }
             }
         }
     }


    /*
    ========================================================
   ALL METHODS BELOW THIS POINT ARE FOR THE VIEWER (TASK 4)
   ========================================================
   */

    /* board layout */
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private Stage viewerStage;
    private Board boardData = null; //This Board object stores tiles when the rules are applied
    private VBox tileContainer; //Contains the columns of tiles
    private VBox root;
    private Group controls = new Group();
    private ArrayList<Placement> prevPlacements = null; //Holds information about previous placements so that they can be removed
    private TextField textField;
    private Text textWarning;

    /**
     * This is the primary method that controls the flow of the placement string,
     * depending upon whether a board string or single placement string has been entered
     * and also whether or not the rules are being applied.
     *
     * @param placement A valid placement string
     */
    private void makePlacement(String placement) {
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
     * This method paints a single tile placement on the board UI.
     *
     * @param placement A valid placement string
     */
    private void makeSinglePlacement(String placement)
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
     */
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
     */
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
     */
    private void makeControls() {
        Text label1 = new Text("Placement:");
        formatText(label1, 18, true, false);
        Button button = new Button("Refresh");
        formatButton(button);
        textField = new TextField();
        textField.setPrefWidth(300);

        button.setOnAction(e -> {
            textWarning.setText(""); //refresh the warning text
            if(textField.getText().equals(""))
            {
                resetBoard();
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
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    /**
     * This method makes the sub-UI that contains all the tiles for the user to reference.
     */
    private void makeTiles()
    {

        ImageView img;
        Text title = new Text("Tiles"); //the title for the sub-UI
        title.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
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

        //add the menu button
        Button btnMenu = new Button("Main menu");
        btnMenu.setOnAction(ae -> quitQuery());
        formatButton(btnMenu);

        //clean up the presentation
        tileColumnContainer.setSpacing(10);
        VBox innerTileContainer = new VBox();
        innerTileContainer.getChildren().addAll(title, tileColumnContainer);
        innerTileContainer.setAlignment(Pos.CENTER);
        innerTileContainer.setSpacing(10);
        innerTileContainer.setPadding(new Insets(5));
        innerTileContainer.setBackground(new Background(new BackgroundFill(Color.LAVENDER, null, null)));
        innerTileContainer.setBorder(new Border(new BorderStroke(Color.MEDIUMPURPLE, BorderStrokeStyle.SOLID, null, BorderStroke.THIN)));

        //add to tileContainer
        tileContainer = new VBox();
        tileContainer.setAlignment(Pos.CENTER);
        tileContainer.setSpacing(10);
        tileContainer.getChildren().addAll(btnMenu, innerTileContainer);
    }

    public void launchViewer(){

        boardData = new Board();
        boardProper = new GridPane();
        setUpBoard();
        makeTiles();
        HBox boardAndTiles = new HBox();
        boardAndTiles.getChildren().addAll(boardContainer, tileContainer);
        boardAndTiles.setAlignment(Pos.CENTER);
        boardAndTiles.setSpacing(10);

        makeControls();
        textWarning = new Text();
        textWarning.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        textWarning.setTextAlignment(TextAlignment.CENTER);

        root = new VBox();
        root.getChildren().add(boardAndTiles);
        root.getChildren().add(textWarning);
        root.getChildren().add(controls);
        formatBox(root, Color.LIGHTBLUE, 10, false);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        viewerStage = new Stage();
        viewerStage.setTitle("Railroad Ink Viewer");
        viewerStage.setScene(new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT));
        viewerStage.setResizable(false);

        launchStage.close();
        viewerStage.show();
    }
}
