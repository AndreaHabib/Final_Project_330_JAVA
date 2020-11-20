import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public abstract class Board {
    protected final int x = 10;
    protected final int y = 10;

    BattleshipRunner.MyNode[][] board = new BattleshipRunner.MyNode[x][y];

    public Board() {

    }

    public abstract void printBoard();

}