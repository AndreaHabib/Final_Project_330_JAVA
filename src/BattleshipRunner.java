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



public class BattleshipRunner extends Application {

    GamePieces Hpea = new Pea();
    GamePieces Hcarrot = new Carrot();
    GamePieces Hpotato = new Potato();
    GamePieces Htomato = new Tomato();

    GamePieces Cpea = new Pea();
    GamePieces Ccarrot = new Carrot();
    GamePieces Cpotato = new Potato();
    GamePieces Ctomato = new Tomato();

    int piece = 0;

    int TCX, TCY;

    HumanBoard humanBoard = new HumanBoard();
    ComputerBoard computerBoard = new ComputerBoard();

    GamePieces[] arr = {Hcarrot, Hpotato, Htomato, Hpea};
    GamePieces[] arr1 = {Ccarrot, Cpotato, Ctomato, Cpea};
    String[] direc = {"HORIZONTAL", "VERTICAL", "HORIZONTAL", "VERTICAL"};

    Button nextScene = new Button("Start");
    Button startGame = new Button("Start!");
    Button exit = new Button("Exit");

    ToggleButton cheatON = new ToggleButton("On");
    ToggleButton cheatOFF = new ToggleButton("Off");
    ToggleGroup groupCheat = new ToggleGroup();


    private Parent createGame() {

        nextScene.setDisable(true);
        int loop = 0;
        while(loop != 4) {
            try {
                int CX = ((int) (Math.random() * (10)) - 0);
                int CY = ((int) (Math.random() * (10)) - 0);
                int rand = ((int) (Math.random() * (5)) - 0);
                String CDIR = direc[rand];
                if (CDIR == "VERTICAL") {
                    arr1[loop].setDirection("VERTICAL");

                } else if (CDIR == "HORIZONTAL") {
                    arr1[loop].setDirection("HORIZONTAL");
                }
                if (computerBoard.setPiece(CX, CY, arr1[loop].getDirection(), arr1[loop])) {
                    loop++;
                }
            }
            catch(IndexOutOfBoundsException err1) {
                continue;
            }
        }

        BorderPane game = new BorderPane();
        game.setPrefSize(1200, 900);

        Label user = new Label("User");
        Label computer = new Label("Computer");

        VBox vert1 = vertNum();
        VBox vert2 = vertNum();

        HBox hor1 = horNum();
        HBox hor2 = horNum();

        Label row = new Label("Row");
        Label column = new Label("Column");
        Button submit = new Button("Submit");
        Text Miss = new Text();
        Text isWin = new Text();

        TextField rows = new TextField();
        TextField columns = new TextField();

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
        HBox input = new HBox(50, input1, input2, submit);

        submit.setOnAction(e -> {
            int turn = 1;
            if(humanBoard.getAddPiece() != 0 && turn == 1) {
                try {
                    try {
                        int y, x, attack;
                        y = Integer.parseInt(rows.getText());
                        x = Integer.parseInt(columns.getText());
                        attack = humanBoard.attack(x, y, computerBoard);
                        if (attack == 3) {
                            Miss.setText("Miss!");
                            turn = 0;
                        } else if (attack == 1) {
                            Miss.setText("Hit!");
                            turn = 0;
                        } else if (attack == 2) {
                            Miss.setText(("Already Guess!"));
                            turn = 1;
                        } else if (attack == 0) {
                            System.exit(-1);
                        }
                    } catch (IndexOutOfBoundsException err) {
                        Miss.setText("Invalid Location");
                    }
                } catch (NumberFormatException e1) {
                    Miss.setText("Invalid Location");
                }
            }
            else if(turn != 0) {
                isWin.setText("Computer is the winner!");
                nextScene.setText("Play Again!");
                nextScene.setDisable(false);
                submit.setDisable(true);

            }
            if(computerBoard.getAddPiece() != 0 && turn == 0) {
                try {
                    turn = 1;
                    int CXG = 0, CYG = 0;
                    int attack = 0, choose = 0;
                    do {
                        if(TCX == 0) {
                            CXG = ((int) (Math.random() * (10)) - 0);
                            CYG = ((int) (Math.random() * (10)) - 0);
                            attack = computerBoard.attack(CXG, CYG, humanBoard);
                        }
                        else {
                            choose = ((int) (Math.random() * 5) + 1);
                            if(choose == 1) {
                                CXG = TCX;
                                CYG = TCY + 1;
                            }
                            else if(choose == 2) {
                                CXG = TCX + 1;
                                CYG = TCY;
                            }
                            else if(choose == 3) {
                                CXG = TCX;
                                CYG = TCY - 1;
                            }
                            else if(choose == 4) {
                                CXG = TCX - 1;
                                CYG = TCY;
                            }
                            attack = computerBoard.attack(CXG, CYG, humanBoard);

                            if(attack == 3) {
                                TCX = 0;
                                TCY = 0;
                            }
                        }
                    } while(attack == 2);

                    if(attack == 1) {
                        TCX = CXG;
                        TCY = CYG;
                    }
                    else {
                        TCX = 0;
                        TCY = 0;
                    }


                } catch (IndexOutOfBoundsException err1) {

                }
            }
            else if(turn != 1) {
                isWin.setText("Human is the winner!");
                nextScene.setText("Play Again!");
                nextScene.setDisable(false);
                submit.setDisable(true);
            }


        });

        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(40, nextScene, exit);

        buttons.setAlignment(Pos.CENTER);

        game.setStyle("-fx-background-color: #B5D3E7");

        VBox vbox  = new VBox(30,hbox1, sidebyside, input, isWin, buttons, Miss);
        vbox.setAlignment(Pos.CENTER);
        game.setCenter(vbox);
        return game;


    }

    private Parent createSetGame(){

        BorderPane setGame = new BorderPane();
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
        Text isOccupied = new Text();
        Text pieceName = new Text();

        TextField rows = new TextField();
        TextField columns = new TextField();
        ToggleButton vertical = new ToggleButton("Vertical");
        ToggleButton horizontal = new ToggleButton("Horizontal");
        nextScene.setDisable(true);

        ToggleGroup group = new ToggleGroup();
        vertical.setToggleGroup(group);
        horizontal.setToggleGroup(group);

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

        pieceName.setText(arr[piece].getName());

        submit.setOnAction(e -> {

            try {
                int y = Integer.parseInt(rows.getText());
                int x = Integer.parseInt(columns.getText());

                if (vertical.isSelected()) {
                    arr[piece].setDirection("VERTICAL");

                } else if (horizontal.isSelected()) {
                    arr[piece].setDirection("HORIZONTAL");
                }
                try {
                    if (!humanBoard.setPiece(x, y, arr[piece].getDirection(), arr[piece])) {
                        isOccupied.setText("Invalid Location");
                    } else {
                        isOccupied.setText("");
                        piece++;
                        if (piece < 4) {
                            pieceName.setText(arr[piece].getName());
                        }
                    }
                } catch (IndexOutOfBoundsException err) {
                    isOccupied.setText("Invalid Location");
                }
                if (piece == 4) {
                    submit.setDisable(true);
                    piece = 0;
                    nextScene.setDisable(false);

                }
            }
            catch (NumberFormatException e1) {
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
        return setGame;
    }

    public VBox vertNum(){
        VBox verticalNum = new VBox(26);
        for(int i  = 0; i < 10; i++){
            Label num = new Label(Integer.toString(i));
            verticalNum.getChildren().add(num);
        }
    return verticalNum;
    }

    public HBox horNum(){
        HBox horiNum = new HBox(32);
        for(int i  = 0; i < 10; i++){
            Label num = new Label(Integer.toString(i));
            horiNum.getChildren().add(num);
        }
        return horiNum;
    }

    private Parent mainMenu() {
        BorderPane menu = new BorderPane();
        menu.setPrefSize(1200, 900);

        Image image = new Image("/menu.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(650);
        img.setFitWidth(650);



        cheatON.setToggleGroup(groupCheat);
        cheatOFF.setToggleGroup(groupCheat);
        HBox cheat = new HBox(cheatON, cheatOFF);


        Label welcome = new Label("Welcome!");
        Label Cheat = new Label("Cheats");

        Cheat.setAlignment(Pos.CENTER);
        cheat.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(40, startGame, exit);

        buttons.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(40, welcome, buttons, Cheat, cheat);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-image: url('/menu.png');" +
                "-fx-background-size: 650 500;" +
                "-fx-background-position: center center;");
        menu.setCenter(vbox);


        return menu;
    }


    public void start(Stage primaryStage) {

        Scene scene = new Scene(mainMenu());

        startGame.setOnAction( e -> {
            if (cheatON.isSelected()) {
                computerBoard.setCheatOnOff(true);

            } else if (cheatOFF.isSelected()) {
                computerBoard.setCheatOnOff(false);
            }
            primaryStage.getScene().setRoot(createSetGame());
        });
            nextScene.setOnAction(e -> {
                String set = ((Button)e.getSource()).getText();
                if(set == "Start") {
                    primaryStage.getScene().setRoot(createGame());
                }
                else if(set == "Play Again!") {
                    humanBoard = new HumanBoard();
                    computerBoard = new ComputerBoard();
                    nextScene.setText("Start");
                    primaryStage.getScene().setRoot(mainMenu());
                }

            });

        exit.setOnAction(e -> System.exit(0));

        primaryStage.setTitle("Food Battle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}

