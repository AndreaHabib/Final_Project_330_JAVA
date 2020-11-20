import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class ComputerBoard extends Board {

    public ComputerBoard(Group root, double sceneWidth, double sceneHeight) {

        double gridWidth = 50;
        double gridHeight = 50;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                // create node
                BattleshipRunner.MyNode node = new BattleshipRunner.MyNode("CItem " + i + "/" + j, i * gridWidth, j * gridHeight, gridWidth, gridHeight);

                // add node to group
                root.getChildren().add(node);

                board[i][j] = node;

            }
        }
    }

    public void printBoard() {

    }

}
