import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BattleshipRunner extends Application {

    public void start(Stage stage) {
        try {
            Label label1 = new Label("Hello");
            Label label2 = new Label("World");


            stage.setScene(stage.getScene());
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 400, 400);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
