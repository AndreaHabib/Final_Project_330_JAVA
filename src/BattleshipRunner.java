import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class BattleshipRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        double sceneWidth = 1024;
        double sceneHeight = 768;

        Group root1 = new Group();
        Group root2 = new Group();

        Board humanboard = new HumanBoard(root1, sceneWidth, sceneHeight);
        SubScene scene1= new SubScene(root1,sceneWidth,sceneHeight);

        Board computerboard = new ComputerBoard(root2, sceneWidth, sceneHeight);
        SubScene scene2= new SubScene(root2,sceneWidth,sceneHeight);

        VBox root = new VBox();
        root.getChildren().addAll(scene1,scene2);
        Scene mainScene = new Scene(root,sceneWidth,sceneHeight);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class MyNode extends StackPane {

        public MyNode( String name, double x, double y, double width, double height) {

            // create rectangle
            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);

            // create label
            Label label = new Label( name);

            // set position
            setTranslateX( x);
            setTranslateY( y);

            getChildren().addAll( rectangle, label);

        }

    }

}