import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class BattleshipRunner extends Application {

    public void start(Stage primaryStage) {

        Image b = createImage(Color.BLACK);
        Image w = createImage(Color.WHITE);


        Image[][] grid = {
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b},
                {b,b,b,b,b,b,b,b,b,b}

        };

        GridPane gridPane = new GridPane();

        // for visualizing the different squares:
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setStyle("-fx-background-color: grey;");

        for (int y = 0 ; y < grid.length ; y++) {
            for (int x = 0 ; x < grid[y].length ; x++) {
                ImageView imageView = new ImageView(grid[y][x]);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                gridPane.add(imageView, x, y);
            }
        }
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Image createImage(Color color) {
        WritableImage image = new WritableImage(1, 1);
        image.getPixelWriter().setColor(0, 0, color);
        return image ;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
