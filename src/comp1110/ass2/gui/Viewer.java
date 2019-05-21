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
 * The Viewer class has been modified to act as the main class for the game interface.
 *
 * @author Samuel J. Brookes (u5380100) - unless indicated otherwise
 */
public class Viewer extends Application {

    /* GAME ASSETS*/
    private boolean gameFinished = false;
    private Stage gameStage;
    private Stage scoreStage;
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

    /**
     * The start method of the Viewer launches the game menu (launchStage)
     * @param primaryStage
     */
    public void start(Stage primaryStage)
    {
        launchStage = buildLaunchStage(primaryStage);
        launchStage.show();
    }

    /**
     * This method creates the launchStage which acts as the game menu.
     * @param launchStage
     * @return (Stage) launchStage
     */
    private Stage buildLaunchStage(Stage launchStage)
    {
        ImageView logo = ImageHandler.getMiscTile("Logo");
        logo.setFitWidth(500);
        logo.setFitHeight(240);

        RadioButton btnSinglePlayer = new RadioButton("Single Player");
        btnSinglePlayer.setMaxSize(200, 10);
        btnSinglePlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        RadioButton btnMultiPlayer = new RadioButton("Multi-player");
        btnMultiPlayer.setMaxSize(200, 10);
        btnMultiPlayer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        RadioButton btnComputer = new RadioButton("Computer Opponent");
        btnComputer.setMaxSize(200, 10);
        btnComputer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        RadioButton btnViewer = new RadioButton("Use Viewer");
        btnViewer.setMaxSize(200, 10);
        btnViewer.setFont(Font.font("Garamond", FontWeight.BOLD, 15));

        ToggleGroup tgPlayMode = new ToggleGroup();
        tgPlayMode.getToggles().addAll(btnSinglePlayer, btnMultiPlayer, btnComputer, btnViewer);

        btnSinglePlayer.setSelected(true);
        Button btnPlay = new Button("Play");
        formatButton(btnPlay);
        Text txtNotification = new Text();

        /* Choosing different RadioButtons changes the gameMode field.
         * The gameMode field is accessed through the launchGameStage()
         * method to determine which game is loaded, and also throughout
         * the game by event handlers to ensure that the correct
         * actions are carried out depending upon the game mode.
         */
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
                //txtNotification.setText("Two player under development");
                gameMode = 'm';
                player = 1;
                launchGameStage();
            }
            else if(btnComputer.isSelected())
            {
                //txtNotification.setText("Computer opponent under development");
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

    /**
     * This method builds the actual game stage and launches it.
     */
    private void launchGameStage()
    {
        /*
        * If this is the first time the game stage has been loaded this game,
        * create the playerData HashMap which holds the data for the Board, Dices and
        * Special Tiles
        * */
        if(playerData == null)
        {
            playerData = new HashMap<>(0);

            if(gameMode == 's')
            { //If single player, create only one PlayerData object and put it in the playerData HashMap
                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.get(1).diceData.rollDice();
            }
            else
            { //If two player (incl. computer opponent), create two PlayerData objects
                playerData.put(1, new PlayerData(1, new Board(), new Dices(), new SpecialTiles()));
                playerData.put(2, new PlayerData(2, new Board(), new Dices(), new SpecialTiles()));

                //Roll the dice of player one
                playerData.get(1).diceData.rollDice();

                //Copy the dice data into player two's PlayerData object
                playerData.get(2).diceData.copyPlayerDices(playerData.get(1).diceData);

                if(gameMode == 'c')
                { //add pointer to player data for computer opponent if gameMode is 'c'
                    computerOpponent = new ComputerOpponent(playerData.get(2));
                }
            }
        }

        /* Set up local reference variables for this launch.
        *  Each time the game board is reloaded, the correct player
        *  data is accessed from the playerData HashMap because the
        *  player field keeps track of which player's turn it is.
        * */
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

    /**
     * This method builds and launches the quitStage.
     * The quitStage exists just in case a player accidentally clicks
     * the Main Menu button. It asks the player if they are sure they would like
     * to quit the current game.
     */
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

        /*
        * If the player selects yes, the game data is deleted,
        * and whichever board is loaded is unloaded before the
        * main menu is reloaded.
        * */
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
                if(scoreStage != null)
                {
                    scoreStage.close();
                    scoreStage = null;
                }
                gameStage.close();
                gameStage = null;

                playerData = null;
                gameFinished = false;
            }
            start(new Stage());
        });

        /*
        * If the player selects no, the quitStage is unloaded and the
        * game continues.
        * */
        btnNo.setOnAction(ae -> quitStage.close());

        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnYes, btnNo);
        formatBox(buttons, Color.LAVENDER, 50, false);
        VBox root = new VBox();
        formatBox(root, Color.LAVENDER, 10, false);
        root.getChildren().addAll(txtQuit, buttons);

        quitStage.setResizable(false);
        quitStage.setScene(new Scene(root, 350, 150));
        quitStage.show();
    }

    /**
     * This method sets up the fields above the game board
     * that inform the player of whose turn it is and what round it
     * is.
     */
    private void setUpGameInfo()
    {
        Text txtPlayer = new Text((gameMode == 'c' && player == 2)?"~ Computer ~":"~ Player " + player + " ~");
        formatText(txtPlayer, 30, true, false);
        txtRound = new Text("~ Round " + boardRef.getRound() + " ~");
        formatText(txtRound, 30, true, false);
        gameInfo = new HBox();
        formatBox(gameInfo, Color.LIGHTBLUE, 150, false);
        gameInfo.getChildren().addAll(txtPlayer, txtRound);
    }

    /**
     * This method sets up the Dice UI, which holds the dice tile and
     * the roll button.
     */
    private void setUpDiceUI()
    {
        Text txtDices = new Text("Dices");
        formatText(txtDices, 20, true, true);

        /*
        * The dice tiles are loaded each time a tile is selected or
        * placed or roll button is clicked. The ImageView instantiation
        * statements therefore need to track if the dice has been used
        * or not so that it loads the correct image each time.
        * Each dice tile is made selectable and rotatable by the
        * setUpSelectAndRotate() method.
        * */
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

        /*
        * These conditional statements ensure that the correct
        * text is put on the roll button. This depends on
        * which round the game is up to, or whether it is multiplayer.
        * */
        Button btnRoll;
        if(gameMode == 's')
        { //If the game is single player or computer opponent

            //Set the roll button text to end game if it's round 7, otherwise set it to roll
            btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"Roll");
        }
        else if(gameMode == 'm')
        {
            if(player == 1)
            { //In two player mode, player one's roll button always reads 'End Turn'
                btnRoll = new Button("End Turn");
            }
            else
            { //The End Game text is only shown on player two's button if it is round 7
                btnRoll = new Button((boardRef.getRound() == 7)?"End Game":"End Turn");
            }
        }
        else
        {
            btnRoll = new Button("End Turn");
        }
        formatButton(btnRoll);

        /*
        * The roll button is perhaps the most important button on the game board because
        * it controls the flow of the game. As such, it has a complex event handler which
        * must track a number of different game elements.
        * */
        btnRoll.setOnAction(ae ->
        {
            txtNotification.setText("");

            if(gameMode == 'm')
            { //If the game is in two player mode
                if(!boardRef.legalMovesRemaining(diceRef))
                { //If there are no legal moves remaining on the board
                    if(player == 1)
                    { //If it is player one's turn

                        boardRef.iterateRoundCounter(); //iterate player one's round counter
                        specialRef.resetCounterRound(); //reset player one's specials-used-per-round counter
                        player = 2; //change the player field to 2 to ensure the correct data is loaded
                        gameStage.close(); //relaunch the game stage with player two's data
                        launchGameStage();
                        txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
                    }
                    else
                    { //Otherwise, if it is player two's turn
                        if(btnRoll.getText().equals("End Game"))
                        {//If the roll button reads 'End Game'
                            //Calculate the scores, and launch the endGameStage to declare the winner
                            gameFinished = true;
                            btnRoll.setVisible(false); //set the roll button to invisible so the player cannot press it
                            showScoreStage();
                        }
                        else
                        { //Otherwise, it is not yet the final round
                            boardRef.iterateRoundCounter(); //iterate player two's round counter
                            specialRef.resetCounterRound(); //reset player two's specials-used-per-round counter
                            player = 1; //change the player field to 1 to ensure the correct data is loaded
                            gameStage.close(); //relaunch the game stage with player one's data
                            launchGameStage();
                            diceRef.rollDice(); //roll player one's dice
                            playerData.get(2).diceData.copyPlayerDices(diceRef); //copy the dices into player two's data
                            setUpDiceUI(); //reload the dice UI
                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
                        }

                    }
                }
                else
                { //Otherwise there are still legal moves
                    txtNotification.setText("You must place all of the dice!");
                }
            }
            else
            { //If the game mode is single player or computer opponent
                if(!boardRef.legalMovesRemaining(diceRef))
                { //If there are no more legal moves this round

                    if(gameMode == 's')
                    {
                        if(btnRoll.getText().equals("End Game"))
                        { //this is the last round
                            gameFinished = true;
                            btnRoll.setVisible(false);
                            showScoreStage();
                        }
                        else
                        {
                            boardRef.iterateRoundCounter(); //iterate player one's round counter
                            specialRef.resetCounterRound(); //reset the specials-used-this-round counter
                            diceRef.rollDice(); //roll the dice
                            setUpDiceUI(); //reload the dice UI
                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
                        }
                    }
                    else
                    { //gameMode == 'c'
                        if(boardRef.getRound() == 7)
                        { //this is the last turn
                            gameFinished = true;
                            btnRoll.setVisible(false);
                            computerOpponentHaveTurn(true);
                        }
                        else
                        { //this is not the last turn
                            computerOpponentHaveTurn(false);
                            gameStage.hide();
                            boardRef.iterateRoundCounter(); //iterate player one's round counter
                            specialRef.resetCounterRound(); //reset the specials-used-this-round counter
                            diceRef.rollDice(); //roll the dice
                            computerOpponent.playerData.diceData.copyPlayerDices(diceRef);
                            setUpDiceUI(); //reload the dice UI
                            txtRound.setText("~ Round " + boardRef.getRound() + " ~"); //update the round notifier
                        }
                    }
                }
                else
                { //Otherwise there are still legal moves that can be made
                    txtNotification.setText("You must place all of the dice!");
                }
            }
        });

        if(scoreStage != null)
        {
            btnRoll.setVisible(false);
        }

        formatBox(dicesUI, Color.LAVENDER, 10, true);
        dicesUI.getChildren().clear();
        dicesUI.getChildren().addAll(txtDices, diceRowOne, diceRowTwo, btnRoll);
    }

    /**
     * This method builds the interface for the Special Tiles. This holds the
     * special tiles.
     */
    private void setUpSpecialUI()
    {
        Text txtSpecials = new Text("Special Tiles");
        formatText(txtSpecials, 20, true, true);

        /*
        * The special tiles are loaded each time a tile is selected or
        * placed or roll button is clicked. The ImageView instantiation
        * statements therefore need to track if the tile has been used
        * or not so that it loads the correct image each time.
        * Each special tile is made selectable and rotatable by the
        * setUpSelectAndRotate() method.
        * */
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

    /**
     * This method takes a tile and adds the event handler that allows
     * dices and special tiles to be rotated and selected by the user.
     * @param tile
     */
    private void setUpSelectAndRotate(ImageView tile)
    {
        tile.setOnMouseClicked(ae ->
        { //If this tile is the target of a mouse button click

            //If the tile image is 'invalid.png' - do nothing
            if(gameFinished || tile.getImage().getUrl().contains("invalid.png")) return;

            txtNotification.setText("");

            //Deselect the previously selected tile
            if(selected != null)
            {
                if(selected.charAt(0) == 'D')
                { //If the previously selected tile is a dice
                    diceRef.getDice(selected).deselectTile(); //deselect the selected tile
                    setUpDiceUI(); //and reload the dice UI
                }
                else
                { //otherwise the previously selectted tile is a special tile
                    specialRef.getSpecialTile(selected).deselectTile(); //deselect it
                    setUpSpecialUI(); //and reload the special tile UI
                }
            }

            if(ae.getButton().equals(MouseButton.PRIMARY))
            { //If the left mouse button was clicked - SELECT THE TILE

                selected = tile.getId(); //add the tile id to the selected field

                if(tile.getId().charAt(0) == 'D')
                { //If the selected tile is a dice
                    diceRef.getDice(selected).selectTile(); //select the tile
                    placement = new Placement(diceRef.getDice(tile.getId())); //initiate the placement field with it's information
                    setUpDiceUI(); //reload Dice UI (the selected dice will be highlighted by the ImageHandler class)
                }
                else
                { //otherwise the selected tile is a special tile
                    specialRef.getSpecialTile(tile.getId()).selectTile(); //select the tile
                    placement = new Placement(specialRef.getSpecialTile(tile.getId())); //initiate the placement field with it's information
                    setUpSpecialUI(); //reload the Special Tile UI (the selected tile will be highlighted by the ImageHandler class)
                }
            }
            else if(ae.getButton().equals(MouseButton.SECONDARY))
            { //If the right mouse button was click - ROTATE THE TILE
                selected = null;
                if(tile.getId().charAt(0) == 'D')
                { //If the target tile is a dice
                    diceRef.rotateDice(tile.getId()); //rotate the dice
                    setUpDiceUI(); //reload the dice UI
                }
                else
                { //Otherwise the target tile is a special tile
                    specialRef.rotateTile(tile.getId()); //rotate the special tile
                    setUpSpecialUI(); //reload the special tile UI
                }
            }
        });
    }

    /**
     * This method adds an event handler to the tiles in the boardProper
     * that allow them to be targets for dropping a selected tile.
     * @param tile
     */
    private void setUpDropTarget(ImageView tile)
    {
        tile.setOnMouseClicked(ae ->
        {
            //If there is no placement to be made, do nothing
            if(gameFinished || placement == null || selected == null) return;

            if(ae.getButton().equals(MouseButton.PRIMARY))
            { //If the board tile is clicked with the left mouse button

                placement.updateCoordinates(tile.getId()); //put the coordinates of the clicked tile into the placement string

                if(selected.charAt(0) == 'D')
                { //If the selected tile is a dice
                    if(boardRef.addTile(placement.toString(), true))
                    { //Add the placement to the player's board (if it is legal)
                        makeBoardProper(); //reload the board
                        diceRef.useDice(selected); //use the placed dice
                        setUpDiceUI(); //reload the dice UI
                        selected = null; //clear selected and placement fields
                        placement = null;
                    }
                    else
                    { //Otherwise, the placement is not legal
                        txtNotification.setText("Illegal placement!");
                    }
                }
                else
                { //Otherwise the seleted tile is a special tile
                    if(specialRef.getCounterGame() < 3 && specialRef.getCounterRound() == 0)
                    { //if less than 3 special tiles have been used this game and no special tiles have been used this round
                        if(boardRef.addTile(placement.toString(), true))
                        { //Add the placement to the player's board (if it is legal)
                            makeBoardProper(); //reload the board
                            specialRef.useSpecialTile(selected); //use the placed special tile
                            setUpSpecialUI(); //reload the special tile UI
                            selected = null; //clear selected and placement fields
                            placement = null;
                        }
                        else
                        { //Otherwise, the placement is not legal
                            txtNotification.setText("Illegal placement!");
                        }
                    }
                    else
                    { //Otherwise 3 special tile have been used this game or a special tile has been used this round.
                        txtNotification.setText((specialRef.getCounterRound() == 1)?
                                "You can only use 1 special tile per round!":
                                "You can only use 3 special tiles per game!");
                    }
                }
            }
        });
    }

    /**
     * This method reduces clutter in the setUpDiceUI() and setUpSpecialUI()
     * methods. It takes two ImageView objects and returns a HBox with them
     * in a row.
     * @param imgOne
     * @param imgTwo
     * @return (HBox) A row with two ImageView objects
     */
    private HBox setUpRow(ImageView imgOne, ImageView imgTwo)
    {
        HBox row = new HBox();
        row.setSpacing(10);
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(imgOne, imgTwo);
        return row;
    }

    /**
     * This method takes a Pane object (either a HBox or VBox) and formats it.
     * This method reduces clutter in other methods and ensures the format is uniform throughout the game.
     * @param controlBox
     * @param color
     * @param spacing
     * @param border
     */
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

    /**
     * This method takes a Text object and formats it. This method reduces clutter in
     * other methods and ensures the format is uniform throughout the game.
     * @param text
     * @param size
     * @param bold
     * @param underline
     */
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

    /**
     * This method takes a Button object and formats it. This method reduces clutter in
     * other methods and ensures the format is uniform throughout the game.
     * @param button
     */
    private void formatButton(Button button)
    {
        button.setFont(Font.font("Garamond", FontWeight.BOLD, 14));
    }

    /**
     * This method sets up the board component of the game stage.
     */
    private void setUpBoard()
    {
        GridPane northEdge = new GridPane();
        GridPane eastEdge = new GridPane();
        GridPane southEdge = new GridPane();
        GridPane westEdge = new GridPane();
        HBox middleContainer = new HBox(); //this container holds the center of the board configuration (east, boardProper and west)
        ImageView tile;

        /*
        * The 'Edge' GridPanes hold the exit tiles for the board
        * */

        //Make the North Edge
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

        //Make the East Edge
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

        //Make the South Edge
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

        //Make the West Edge
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

        //Make the boardProper (with selectable tiles)
        makeBoardProper();

        //add to middle container
        middleContainer.getChildren().addAll(westEdge, boardProper, eastEdge);
        middleContainer.setAlignment(Pos.CENTER);

        //add everything to the board
        boardContainer = new VBox();
        boardContainer.getChildren().addAll(northEdge, middleContainer, southEdge);
        boardContainer.setAlignment(Pos.CENTER);
    }

    /**
     * This method loads the board proper (which contains tile placements).
     */
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
                 { //If the game mode is not 'Viewer' and there is a placement at these coordinates
                    tile = ImageHandler.getTileImage(boardRef.getTile(id.toString())); //load the appropriate tile image
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
                 {//If the game mode is 'Viewer'

                     //Show the coordinates of the tile
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

    /**
     * This method builds and shows the score stage.
     * The score stage contains a break down of each players'
     * score, and allows the board to be viewed and the board
     * string to be exported for debugging.
     */
    private void showScoreStage()
     {
         scoreStage = new Stage();
         scoreStage.setTitle("Scores");
         scoreStage.setResizable(false);
         VBox root = new VBox();
         Button btnMenu = new Button("Main Menu");
         btnMenu.setOnAction(ae ->
         {
             scoreStage.close();
             scoreStage = null;

             gameStage.close();
             gameStage = null;
             playerData = null;
             gameFinished = false;

             start(new Stage());
         });
         ImageView winImage;

         //Set up appropriate scoreStage, depending upon gameMode
         if(gameMode == 's')
         {
             VBox playerOneStats = getPlayerStats(1);
             winImage = ImageHandler.getMiscTile("gameOver");
             winImage.setFitHeight(100);
             winImage.setFitWidth(550);

             root.getChildren().addAll(winImage, playerOneStats, btnMenu);
             root.setAlignment(Pos.CENTER);
             formatBox(root, Color.LAVENDER, 10, false);
         }
         else
         {
             HBox players = new HBox();
             VBox playerOneStats = getPlayerStats(1);
             VBox playerTwoStats = getPlayerStats(2);

             Button showBoardPlayerOne = new Button("Show Board");
             showBoardPlayerOne.setOnAction(ae ->
             {
                 player = 1;
                 gameStage.close();
                 launchGameStage();
                 scoreStage.toFront();
             });
             playerOneStats.getChildren().add(showBoardPlayerOne);

             Button showBoardPlayerTwo = new Button("Show Board");
             showBoardPlayerTwo.setOnAction(ae ->
             {
                 player = 2;
                 gameStage.close();
                 launchGameStage();
                 scoreStage.toFront();
             });
             playerTwoStats.getChildren().add(showBoardPlayerTwo);

             players.getChildren().addAll(playerOneStats, playerTwoStats);
             players.setAlignment(Pos.CENTER);
             players.setSpacing(10);

             if(playerData.get(1).scoreCalculator.getAdvancedScore() ==
                     playerData.get(2).scoreCalculator.getAdvancedScore())
             {
                 winImage = ImageHandler.getMiscTile("draw");
             }
             else if(playerData.get(1).scoreCalculator.getAdvancedScore() >
                     playerData.get(2).scoreCalculator.getAdvancedScore())
             {
                 winImage = (gameMode == 'c')?ImageHandler.getMiscTile("youWin"):ImageHandler.getMiscTile("player1Wins");
             }
             else
             {
                 winImage = (gameMode == 'c')?ImageHandler.getMiscTile("youLose"):ImageHandler.getMiscTile("player2Wins");
             }
             winImage.setFitHeight(100);
             winImage.setFitWidth(550);

             root.getChildren().addAll(winImage, players, btnMenu);
             root.setAlignment(Pos.CENTER);
             formatBox(root, Color.LAVENDER, 10, false);
         }

         scoreStage.setScene(new Scene(root, GAME_WIDTH/1.5, GAME_HEIGHT/1.5));
         scoreStage.show();
     }

    /**
     * This method compiles the player scores from the game
     * and formats them into a VBox.
     * @param playerNumber
     * @return
     */
     private VBox getPlayerStats(int playerNumber)
     {
         PlayerData player;
         Text txtHeading;
         if(gameMode == 'c')
         { //player is computer
             txtHeading = new Text(((playerNumber == 1)?"~ PLAYER 1 ~":"~ COMPUTER ~"));
         }
         else
         { //player is not computer
             txtHeading = new Text("~ PLAYER " + playerNumber + " ~");
         }

         player = playerData.get(playerNumber);
         player.calculateScore();
         formatText(txtHeading, 20, true, true);
         Text txtCenterScore = new Text("Center Score: " + player.scoreCalculator.getCenterScore());
         formatText(txtCenterScore, 18, false, false);
         Text txtNetworkScore = new Text("Network Score: " + player.scoreCalculator.getNetworkScore());
         formatText(txtNetworkScore, 18, false, false);
         Text txtErrors = new Text("Number of Errors: " + player.scoreCalculator.getErrors());
         formatText(txtErrors, 18, false, false);
         Text txtRailroad = new Text("Longest Railroad: " + player.scoreCalculator.getLongestRailroad());
         formatText(txtRailroad, 18, false, false);
         Text txtHighway = new Text("Longest Highway: " + player.scoreCalculator.getLongestHighway());
         formatText(txtHighway, 18, false, false);
         Text txtTotalScore = new Text("SCORE: " + player.scoreCalculator.getAdvancedScore());
         formatText(txtTotalScore, 18, true, false);

         TextField txtBoardString = new TextField();
         txtBoardString.setVisible(false);
         Button btnBoardString = new Button("Show Board String");
         btnBoardString.setOnAction(ae ->
         {
             if(btnBoardString.getText().equals("Show Board String"))
             {
                 txtBoardString.setVisible(true);
                 txtBoardString.setText(playerData.get(playerNumber).boardData.toString());
                 btnBoardString.setText("Hide Board String");
             }
             else
             {
                 txtBoardString.setVisible(false);
                 btnBoardString.setText("Show Board String");
             }
         });

         VBox playerStats = new VBox();
         playerStats.getChildren().addAll(txtHeading, txtCenterScore, txtNetworkScore, txtErrors, txtRailroad, txtHighway, txtTotalScore, btnBoardString, txtBoardString);
         playerStats.setAlignment(Pos.CENTER);

         formatBox(playerStats, Color.LIGHTBLUE, 10, true);

         return playerStats;
     }

    /**
     * This method shows the 'Computer is thinking' window
     * and hides the game board so that the player can not do anything
     * while the computer is having a turn.
     * The computer has it's turn and then the continue button
     * is made visible so that the player can continue.
     * @param lastRound
     */
    private void computerOpponentHaveTurn(boolean lastRound)
    {
        gameStage.hide();

        Stage computerStage = new Stage();
        computerStage.setTitle("Computer is thinking...");
        computerStage.setResizable(false);
        VBox root = new VBox();
        formatBox(root, Color.LAVENDER, 10, false);
        ImageView computer = ImageHandler.getMiscTile("thinking");
        computer.setFitHeight(GAME_HEIGHT - 280);
        computer.setFitWidth(GAME_WIDTH - 200);
        Button btnContinue;
        if(lastRound)
        {
            btnContinue = new Button("End Game...");
            btnContinue.setOnAction(ae ->
            {
                computerStage.close();
                gameStage.show();
                showScoreStage();
            });
        }
        else
        {
            btnContinue = new Button("Press to Continue...");
            btnContinue.setOnAction(ae ->
            {
                computerStage.close();
                gameStage.show();
            });
        }
        btnContinue.setVisible(false);
        computerOpponent.haveTurn(btnContinue, false);

        root.getChildren().addAll(computer, btnContinue);
        root.setAlignment(Pos.CENTER);
        computerStage.setScene(new Scene(root, GAME_WIDTH - 200, GAME_HEIGHT - 220));
        computerStage.show();
    }

    /*
    ========================================================
   ALL METHODS BELOW THIS POINT ARE FOR THE VIEWER (TASK 4)
   ========================================================
   */

    /* board layout */
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;

    private String savedGame;
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
        // Author: Samuel J. Brookes (u5380100)

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
        Button btnRefresh = new Button("Refresh");
        Button btnScore = new Button("Calculate Score");
        formatButton(btnRefresh);
        formatButton(btnScore);
        textField = new TextField();
        textField.setPrefWidth(300);

        btnRefresh.setOnAction(e -> {
            textWarning.setText(""); //refresh the warning text
            if(textField.getText().equals(""))
            {
                resetBoard();
            }
            else
            { //otherwise, do an unchecked placement
                makePlacement(textField.getText());
                savedGame = textField.getText();
                System.out.println("Saved game = " + savedGame);
                textField.clear();
            }
        });

        btnScore.setOnAction(ae ->
        {
            if(savedGame != null)
            {
                Board board = new Board();
                for(int i=0; i<savedGame.length(); i+=5)
                {
                    board.addTile(savedGame.substring(i, i+5), true);
                }
                ScoreCalculator sc = new ScoreCalculator(board);
                textWarning.setText("Score: " + sc.getNetworkScore() + " (network) + " +
                        sc.getCenterScore() + " (center) + " + sc.getLongestHighway() + " (highway) + " + sc.getLongestRailroad() +
                        " (railroad) - " + sc.getErrors() + " (errors) = " + sc.getAdvancedScore());
            }
        });

        textField.setOnKeyPressed(ae ->
        { //added key press event to textfield because having to press the button is annoying
            KeyCode key = ae.getCode();
            if(key == KeyCode.ENTER)
            { //if enter is pressed within the text field, the user is finished typing
                btnRefresh.fire();
            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, btnRefresh, btnScore);
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

    /**
     * This is the main method for building and launching the viewer mode of the RailroadInk game.
     */
    private void launchViewer(){

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
