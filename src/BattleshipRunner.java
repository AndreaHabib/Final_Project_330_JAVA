import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    Player humanPlayer = new HumanPlayer(Hpea, Hcarrot, Hpotato, Htomato);
    Player computerPlayer = new ComputerPlayer(Cpea, Ccarrot, Cpotato, Ctomato);

    HumanBoard humanBoard = new HumanBoard();
    ComputerBoard computerBoard = new ComputerBoard();

    GamePieces[] arr = {Hcarrot, Hpotato, Htomato, Hpea};
    GamePieces[] arr1 = {Ccarrot, Cpotato, Ctomato, Cpea};
    String[] direc = {"HORIZONTAL", "VERTICAL", "HORIZONTAL", "VERTICAL"};

    Button nextScene = new Button("Start");
    Button startGame = new Button("Start!");

    private Parent createGame() {

        nextScene.setDisable(true);
        int loop = 0;
        while(loop != 4) {
            int CX = ((int) (Math.random() * (9)));
            int CY = ((int) (Math.random() * (9)));
            int rand = ((int) (Math.random() * (4)));
            String CDIR = direc[rand];
            if (CDIR == "VERTICAL") {
                arr1[loop].setDirection("VERTICAL");

            } else if (CDIR == "HORIZONTAL") {
                arr1[loop].setDirection("HORIZONTAL");
            }
            if(computerBoard.setPiece(CX, CY, arr[loop].getDirection(), arr[loop])) {
                loop++;
            }
        }

        BorderPane setGame = new BorderPane();
        setGame.setPrefSize(1200, 750);

        Label user = new Label("User");
        Label computer = new Label("Computer");

        VBox vert1 = vertNum();
        VBox vert2 = vertNum();

        HBox hor1 = horNum();
        HBox hor2 = horNum();

        Label row = new Label("Row");
        Label column = new Label("Column");
        Button submit = new Button("Submit");
        Text isOccupied = new Text();
        Text pieceName = new Text();

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

            int x = Integer.parseInt(rows.getText());
            int y = Integer.parseInt(columns.getText());

        });

        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        VBox vbox  = new VBox(40,hbox1, sidebyside, pieceName, input, nextScene, isOccupied);
        vbox.setAlignment(Pos.CENTER);
        setGame.setCenter(vbox);
        return setGame;


    }

    private Parent createSetGame(){

        BorderPane setGame = new BorderPane();
        setGame.setPrefSize(1200, 750);

        Label user = new Label("User");
        Label computer = new Label("Computer");

        VBox vert1 = vertNum();
        VBox vert2 = vertNum();

        HBox hor1 = horNum();
        HBox hor2 = horNum();

        Label row = new Label("Row");
        Label column = new Label("Column");
        Label direction = new Label("Direction");
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

            int y = Integer.parseInt(rows.getText());
            int x = Integer.parseInt(columns.getText());


            if (vertical.isSelected()) {
                arr[piece].setDirection("VERTICAL");

            } else if (horizontal.isSelected()) {
                arr[piece].setDirection("HORIZONTAL");
            }

            if(!humanBoard.setPiece(x, y, arr[piece].getDirection(), arr[piece])) {
                isOccupied.setText("Invalid Location");
            }
            else {
                isOccupied.setText("");
                piece++;
                if(piece < 4) {
                    pieceName.setText(arr[piece].getName());
                }
            }
            if(piece == 4){
                submit.setDisable(true);
                nextScene.setDisable(false);

            }
        });


        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        VBox vbox  = new VBox(40,hbox1, sidebyside, pieceName, input, nextScene, isOccupied);
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
        menu.setPrefSize(1200, 750);

        Image image = new Image("/menu.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(500);
        img.setFitWidth(650);

        Label welcome = new Label("Welcome!");
        VBox vbox = new VBox(40, welcome, startGame);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-image: url('/menu.png');" +
                "-fx-background-size: 500 650;" +
                "-fx-background-position: center center;");
        menu.setCenter(vbox);


        return menu;
    }


    public void start(Stage primaryStage) {

        Scene scene = new Scene(mainMenu());

        startGame.setOnAction( e -> primaryStage.getScene().setRoot(createSetGame()));
        nextScene.setOnAction( e -> primaryStage.getScene().setRoot(createGame()));

        primaryStage.setTitle("Food Battle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}

