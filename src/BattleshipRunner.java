import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class BattleshipRunner extends Application {
    private Parent create(){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        Board humanBoard = new HumanBoard();
        Board computerBoard = new ComputerBoard();
        VBox vbox = new VBox(50, humanBoard, computerBoard);
        vbox.setAlignment(Pos.CENTER);
        root.setCenter(vbox);
        return root;
    }

    public void start(Stage primaryStage) {

        Scene scene = new Scene(create());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
