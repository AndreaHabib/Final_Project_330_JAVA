import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class BattleshipRunner extends Application {
    private Parent createGame(){
        GamePieces pea = new Pea();
        GamePieces carrot = new Carrot();
        GamePieces potato = new Potato();
        GamePieces tomato = new Tomato();

        Player humanPlayer = new HumanPlayer(pea, carrot, potato, tomato);
        Player computerPlayer = new ComputerPlayer(pea, carrot, potato, tomato);

        //System.out.println(humanPlayer);

        BorderPane game = new BorderPane();
        game.setPrefSize(1000, 750);

        HumanBoard humanBoard = new HumanBoard();
        ComputerBoard computerBoard = new ComputerBoard();

        Label user = new Label("User");
        Label computer = new Label("Computer");

        Label row = new Label("Row");
        Label column = new Label("Column");
        Button submit = new Button("Submit");
        final Text text = new Text();

        TextField rows = new TextField();
        TextField columns = new TextField();


        HBox hbox1 = new HBox(400, user, computer);
        HBox hbox2 = new HBox(40, humanBoard, computerBoard);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        HBox input1 = new HBox(10, row, rows);
        HBox input2 = new HBox(10, column, columns);
        HBox input = new HBox(50, input1, input2, submit);

        submit.setOnAction(e -> {
            int x = Integer.parseInt(rows.getText());
            int y = Integer.parseInt(columns.getText());
            humanBoard.getPosition(x, y).changeColor(x, y);
        });

        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);

        VBox vbox  = new VBox(40, hbox1, hbox2, input );
        vbox.setAlignment(Pos.CENTER);
        game.setCenter(vbox);
        return game;
    }

    private Parent createSetGame(){
        BorderPane setGame = new BorderPane();
        setGame.setPrefSize(1000, 750);



        HumanBoard humanBoard = new HumanBoard();
        ComputerBoard computerBoard = new ComputerBoard();

        Label user = new Label("User");
        Label computer = new Label("Computer");


        Label row = new Label("Row");
        Label column = new Label("Column");
        Label direction = new Label("Direction");
        Button submit = new Button("Submit");
        final Text text = new Text();

        TextField rows = new TextField();
        TextField columns = new TextField();
        TextField directions = new TextField();

        HBox hbox1 = new HBox(400, user, computer);
        HBox hbox2 = new HBox(40, humanBoard, computerBoard);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);

        HBox input1 = new HBox(10, row, rows);
        HBox input2 = new HBox(10, column, columns);
        HBox input3 = new HBox(10, direction, directions);
        HBox input = new HBox(50, input1, input2, input3, submit);

        submit.setOnAction(e -> {
            int x = Integer.parseInt(rows.getText());
            int y = Integer.parseInt(columns.getText());
            String direct = directions.getText();
            //set pieces logic
            humanBoard.getPosition(x, y).changeColor(x, y);
        });


        input1.setAlignment(Pos.CENTER);
        input2.setAlignment(Pos.CENTER);
        input.setAlignment(Pos.CENTER);


        VBox vbox  = new VBox(40, hbox1, hbox2, input );
        vbox.setAlignment(Pos.CENTER);
        setGame.setCenter(vbox);
        return setGame;
    }

    public void start(Stage primaryStage) {

        Scene scene = new Scene(createGame());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}

