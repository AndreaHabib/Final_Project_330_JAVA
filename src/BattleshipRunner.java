import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class BattleshipRunner extends Application {
    private Parent create(){
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 750);
        HumanBoard humanBoard = new HumanBoard();
        ComputerBoard computerBoard = new ComputerBoard();
        HBox hbox = new HBox(40, humanBoard, computerBoard);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);
        return root;
    }

    public void start(Stage primaryStage) {

        Scene scene = new Scene(create());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
