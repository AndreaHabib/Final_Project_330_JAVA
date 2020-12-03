//all imports used
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//Runner
public class BattleshipRunner extends Application {

    //instantiate all human pieces
    GamePieces Hpea = new Pea();
    GamePieces Hcarrot = new Carrot();
    GamePieces Hpotato = new Potato();
    GamePieces Htomato = new Tomato();

    //instantiate all computer pieces
    GamePieces Cpea = new Pea();
    GamePieces Ccarrot = new Carrot();
    GamePieces Cpotato = new Potato();
    GamePieces Ctomato = new Tomato();

    //index variable to access specific game piece from arr/arr1
    int piece = 0;

    //temporary X and Y coordinates. Used by computer to remember last position if hit
    int TCX = 0, TCY = 0, T1 = 0, T2 = 0;

    //instantiate human and computer boards
    HumanBoard humanBoard = new HumanBoard();
    ComputerBoard computerBoard = new ComputerBoard();

    //game pieces in arr to access specific piece arr for human and arr1 for computer
    GamePieces[] arr = {Hcarrot, Hpotato, Htomato, Hpea}; //human
    GamePieces[] arr1 = {Ccarrot, Cpotato, Ctomato, Cpea}; //computer
    String[] direc = {"HORIZONTAL", "VERTICAL", "HORIZONTAL", "VERTICAL"}; //direction determined by the computer

    Button nextScene = new Button("Start"); //Button to switch scenes. Switches from setGame to game and from game to main menu
    Button startGame = new Button("Start!"); //Button to start game. in Main Menu to setGame
    Button exit = new Button("Exit"); //exits with code 0

    //enable or disable cheats. ability to see computer board
    ToggleButton cheatON = new ToggleButton("On");
    ToggleButton cheatOFF = new ToggleButton("Off");
    ToggleGroup groupCheat = new ToggleGroup();

    //Scene where the user and computer play against each other
    private Parent createGame() {

        nextScene.setDisable(true); //disable the button. prevent user from switching scenes

        //set the computer pieces using random coordinates generator
        int loop = 0; //index. Increment only if setting the piece was successful
        while(loop != 4) {
            try { //check if it goes out of bounds
                int CX = ((int) (Math.random() * (10)) - 0); //random from 0 to 9
                int CY = ((int) (Math.random() * (10)) - 0); //random from 0 to 9
                int rand = ((int) (Math.random() * (5)) - 0); //random from 0 to 5
                String CDIR = direc[rand]; //add random direction
                if (CDIR == "VERTICAL") { //if vertical
                    arr1[loop].setDirection("VERTICAL"); //sets direction vertical

                } else if (CDIR == "HORIZONTAL") { //if horizontal
                    arr1[loop].setDirection("HORIZONTAL"); //sets direction to horizontal
                }
                if (computerBoard.setPiece(CX, CY, arr1[loop].getDirection(), arr1[loop])) { //sets the piece on board
                    loop++; //if successful increment
                }
            }
            catch(IndexOutOfBoundsException err1) { //catch if out of index
                continue; //continue --> try different coordinates until setting piece is successful
            }
        }

        BorderPane game = new BorderPane(); //create new BorderPane root
        game.setPrefSize(1200, 900); //set resolution

        Label user = new Label("User"); //user label
        Label computer = new Label("Computer"); //computer label

        VBox vert1 = vertNum(); //get vertical grid label
        VBox vert2 = vertNum();

        HBox hor1 = horNum(); //get horizontal grid label
        HBox hor2 = horNum();

        Label row = new Label("Row"); //Row
        Label column = new Label("Column"); //Column
        Button submit = new Button("Submit"); //Submit coordinates
        Text Miss = new Text(); //text used to print if user missed
        Text isWin = new Text(); //text used to print if user/computer won

        TextField rows = new TextField(); //rows input
        TextField columns = new TextField(); //columns input

        HBox hbox1 = new HBox(500, user, computer); //put user and computer label in HBox

        //add number labels with the board grid
        HBox hbox3 = new HBox(40, vert1, humanBoard);
        HBox hbox4 = new HBox(40, vert2, computerBoard);

        //put them in VBox
        VBox human = new VBox(40, hor1, hbox3);
        VBox comp = new VBox(40, hor2, hbox4);

        //then put then in HBox side by side
        HBox sidebyside = new HBox(100, human, comp);

        //center align all grids present
        hbox1.setAlignment(Pos.CENTER);
        hor1.setAlignment(Pos.CENTER);
        hor2.setAlignment(Pos.CENTER);
        human.setAlignment(Pos.CENTER);
        comp.setAlignment(Pos.CENTER);
        sidebyside.setAlignment(Pos.CENTER);

        //put all input together
        HBox input1 = new HBox(10, row, rows);
        HBox input2 = new HBox(10, column, columns);
        HBox input = new HBox(50, input1, input2, submit);

        //event handle for submit button
        submit.setOnAction(e -> {
            int turn = 1; //turns between computer and player. 1 = player 0 = computer.
            if(humanBoard.getAddPiece() != 0 && turn == 1) { //if user didn't lose all his pieces, game keeps going.
                try { //catch non-numeric input
                    try { //catch out of bounds input
                        int y, x, attack; //x > rows, y > cols, attack > return value. 1 > hit, 2 > already guess, 3 > missed, 0 > program failure
                        y = Integer.parseInt(rows.getText()); //convert text into int
                        x = Integer.parseInt(columns.getText());
                        attack = humanBoard.attack(x, y, computerBoard); //perform the attack
                        if (attack == 3) { //missed
                            Miss.setText("Miss!");
                            turn = 0;
                        } else if (attack == 1) { //hit
                            Miss.setText("Hit!");
                            turn = 0;
                        } else if (attack == 2) {
                            Miss.setText(("Already Guess!")); //already guessed
                            turn = 1; //user still plays if already guessed
                        } else if (attack == 0) {
                            System.exit(-1); //exit with status -1
                        }
                    } catch (IndexOutOfBoundsException err) { //catch and print message
                        Miss.setText("Invalid Location");
                    }
                } catch (NumberFormatException e1) { //catch and print message
                    Miss.setText("Invalid Location");
                }
            }
            else if(turn != 0) { //if pieces on board = 0, computer wins
                isWin.setText("Computer is the winner!"); //print winner message
                nextScene.setText("Play Again!"); //change text to Play Again. Refer to line 471
                nextScene.setDisable(false); //enable button
                submit.setDisable(true); //disable button

            }
            if(computerBoard.getAddPiece() != 0 && turn == 0) { //same process for computer as human, except input is random generated
                try {
                    turn = 1; //Computer only attacks when successful, therefore turn = 1.
                    int CXG = 0, CYG = 0; //computer x and y
                    int attack = 0, choose = 0;
                    do {
                        if(TCX == 0) { //if temporary is empty, normal attack
                            CXG = ((int) (Math.random() * (10)) - 0); //random number between 0 and 9
                            CYG = ((int) (Math.random() * (10)) - 0);
                            attack = computerBoard.attack(CXG, CYG, humanBoard); //computer attack
                        }
                        else { //else, add 1 to temporary to up, down, right or left. Randomly selected
                            choose = ((int) (Math.random() * 5) + 1); //random number between 1 and 4
                            if(choose == 1) { //attacks right
                                CXG = TCX;
                                CYG = TCY + 1;
                            }
                            else if(choose == 2) { //attacks down
                                CXG = TCX + 1;
                                CYG = TCY;
                            }
                            else if(choose == 3) { //attacks left
                                CXG = TCX;
                                CYG = TCY - 1;
                            }
                            else if(choose == 4) { //attacks up
                                CXG = TCX - 1;
                                CYG = TCY;
                            }
                            attack = computerBoard.attack(CXG, CYG, humanBoard); //attack

                            if(attack == 3) { //if miss, set temp to 0
                                TCX = 0;
                                TCY = 0;
                            }
                        }
                    } while(attack == 2); //since computer can't guess already guessed grid, computer keeps choosing until either hitting or missing

                    if(attack == 1) { //if hit, memorize x and y
                        TCX = CXG;
                        TCY = CYG;
                        T1 = CXG;
                        T2 = CYG;
                    }
                    else {
                        TCX = 0;
                        TCY = 0;
                    }


                } catch (IndexOutOfBoundsException err1) { //catch index out of bounds, do nothing

                }
            }
            else if(turn != 1) { //if user choose all the correct, human wins
                isWin.setText("Human is the winner!");
                nextScene.setText("Play Again!");
                nextScene.setDisable(false);
                submit.setDisable(true);
            }


        });

        //center align input
        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        //add buttons in HBox
        HBox buttons = new HBox(40, nextScene, exit);

        //center align buttons
        buttons.setAlignment(Pos.CENTER);

        //set background color to light blue
        game.setStyle("-fx-background-color: #B5D3E7");

        //add everything in VBox and center align them
        VBox vbox  = new VBox(30,hbox1, sidebyside, input, isWin, buttons, Miss);
        vbox.setAlignment(Pos.CENTER);
        game.setCenter(vbox); //center align the VBox in root
        return game; //return root


    }

    //The scene where the user chooses and sets the game pieces on the board
    private Parent createSetGame(){

        BorderPane setGame = new BorderPane(); //create BorderPane for the root
        setGame.setPrefSize(1200, 900);

        Label user = new Label("User");
        Label computer = new Label("Computer");

        VBox vert1 = vertNum();
        VBox vert2 = vertNum();

        HBox hor1 = horNum();
        HBox hor2 = horNum();

        Label row = new Label("Row");
        Label column = new Label("Column");
        Label direction = new Label("Direction");
        Label table = new Label("Prepare Your Table!");
        Button submit = new Button("Submit");
        Text isOccupied = new Text(); //prints message if position is already occupied
        Text pieceName = new Text(); //prints out the piece name for the user to enter

        TextField rows = new TextField();
        TextField columns = new TextField();
        ToggleButton vertical = new ToggleButton("Vertical");
        ToggleButton horizontal = new ToggleButton("Horizontal");
        nextScene.setDisable(true);

        //let the user choose the direction of the game pieces to be places on the board
        ToggleGroup group = new ToggleGroup();
        vertical.setToggleGroup(group);
        horizontal.setToggleGroup(group);
        vertical.setSelected(true);

        HBox hbox1 = new HBox(500, user, computer);

        HBox hbox3 = new HBox(40, vert1, humanBoard);
        HBox hbox4 = new HBox(40, vert2, computerBoard);

        VBox human = new VBox(40, hor1, hbox3);
        VBox comp = new VBox(40, hor2, hbox4);

        HBox sidebyside = new HBox(100, human, comp);

        hbox1.setAlignment(Pos.CENTER);
        hor1.setAlignment(Pos.CENTER);
        hor2.setAlignment(Pos.CENTER);
        human.setAlignment(Pos.CENTER);
        comp.setAlignment(Pos.CENTER);
        sidebyside.setAlignment(Pos.CENTER);



        HBox input1 = new HBox(10, row, rows);
        HBox input2 = new HBox(10, column, columns);
        HBox input3 = new HBox(10, direction, vertical, horizontal);
        HBox input = new HBox(50, input1, input2, input3, submit);

        //print the name of the first piece: Carrot
        pieceName.setText(arr[piece].getName());

        submit.setOnAction(e -> {

            try { //catch if input is not a number

                int y = Integer.parseInt(rows.getText());
                int x = Integer.parseInt(columns.getText());

                if (vertical.isSelected()) { //if toggle is vertical
                    arr[piece].setDirection("VERTICAL");

                } else if (horizontal.isSelected()) { //if toggle is horizontal
                    arr[piece].setDirection("HORIZONTAL");
                }


                try { //catch if input is out of range

                    if (!humanBoard.setPiece(x, y, arr[piece].getDirection(), arr[piece])) { //if attack was not successful, print message
                        isOccupied.setText("Invalid Location");
                    } else {
                        isOccupied.setText(""); //else print nothing
                        piece++; //increment index
                        if (piece < 4) {
                            pieceName.setText(arr[piece].getName()); //print piece name
                        }
                    }
                } catch (IndexOutOfBoundsException err) { //print message if out of range
                    isOccupied.setText("Invalid Location");
                }
                if (piece == 4) { //if final piece, disable submit and enable nextScene for the user to start the game
                    submit.setDisable(true);
                    piece = 0;
                    nextScene.setDisable(false);

                }
            }
            catch (NumberFormatException e1) { //print message if input is not a number
                isOccupied.setText("Invalid Location");
            }
        });


        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(40, nextScene, exit);

        buttons.setAlignment(Pos.CENTER);

        setGame.setStyle("-fx-background-color: #B5D3E7");

        VBox vbox  = new VBox(30, table, hbox1, sidebyside, pieceName, input, buttons, isOccupied);
        vbox.setAlignment(Pos.CENTER);
        setGame.setCenter(vbox);
        return setGame; //return root
    }

    /**
     * Method to store vertical numbers. Used to print numbers next to the grid
     *
     * @return VBox. store the vertical numbers in VBox
     */
    public VBox vertNum(){
        VBox verticalNum = new VBox(26);
        for(int i  = 0; i < 10; i++){
            Label num = new Label(Integer.toString(i));
            verticalNum.getChildren().add(num);
        }
    return verticalNum;
    }

    /**
     * Method to store horizontal numbers. Used to print numbers above to the grid
     *
     * @return HBox. store the horizontal numbers in HBox
     */
    public HBox horNum(){
        HBox horiNum = new HBox(32);
        for(int i  = 0; i < 10; i++){
            Label num = new Label(Integer.toString(i));
            horiNum.getChildren().add(num);
        }
        return horiNum;
    }

    //main menu scene. Contains a poster, start and exit buttons.
    private Parent mainMenu() {
        BorderPane menu = new BorderPane();
        menu.setPrefSize(1200, 900);

        Image image = new Image("/menu.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(400);
        img.setFitWidth(400);


        //if cheat is on, user can see pieces on computer board --> for testing purposes
        cheatON.setToggleGroup(groupCheat);
        cheatOFF.setToggleGroup(groupCheat);
        HBox cheat = new HBox(cheatON, cheatOFF);


        Label welcome = new Label("Welcome!");
        Label Cheat = new Label("Cheats");

        Cheat.setAlignment(Pos.CENTER);
        cheat.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(40, startGame, exit);

        buttons.setAlignment(Pos.CENTER);

        menu.setStyle("-fx-background-color: #B5D3E7");

        VBox vbox = new VBox(40, welcome, img, buttons, Cheat, cheat);
        vbox.setAlignment(Pos.CENTER);
        //use some CSS inline styling
        vbox.setStyle("-fx-background-image: url('/menu.png');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 400 300;" +
                "-fx-background-position: center center;"
        );
        menu.setCenter(vbox);


        return menu; //return root
    }

    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(mainMenu()); //start main menu scene

        //button inline CSS styling
        nextScene.setStyle(
                "-fx-background-color: black;"
                + "-fx-text-fill: white;"
                + "-fx-padding: 6px 20px;"
        );

        startGame.setStyle(
                "-fx-background-color: black;"
                        + "-fx-text-fill: white;"
                        + "-fx-padding: 6px 20px;"
        );

        exit.setStyle(
                "-fx-background-color: red;"
                        + "-fx-text-fill: white;"
                        + "-fx-padding: 6px 20px;"
        );

        //takes the user setGame scene, along with whether cheats are on or off
        startGame.setOnAction( e -> {
            if (cheatON.isSelected()) {
                computerBoard.setCheatOnOff(true);

            } else if (cheatOFF.isSelected()) {
                computerBoard.setCheatOnOff(false);
            }
            primaryStage.getScene().setRoot(createSetGame());
        });

        //behaviour changes based on text value of the button. If "Start", button goes to CreateGame, if "Play Again!", button goes to mainMenu
        nextScene.setOnAction(e -> {
            String set = ((Button)e.getSource()).getText();
            if(set == "Start") {
                primaryStage.getScene().setRoot(createGame());
            }
            else if(set == "Play Again!") { //reinstantiate human and computer board for new game
                humanBoard = new HumanBoard();
                computerBoard = new ComputerBoard();
                nextScene.setText("Start");
                primaryStage.getScene().setRoot(mainMenu());
            }
        });

        exit.setOnAction(e -> System.exit(0)); //exit program with code 0

        primaryStage.setTitle("Food Battle"); //title
        primaryStage.setScene(scene); //set the scene
        primaryStage.setResizable(false); //not resizable
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}

