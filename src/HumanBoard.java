import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Cell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HumanBoard extends Parent implements Board{

    private VBox rows = new VBox();
    private final boolean isEnemy = false;
    public int numOfShips = 5;
    public final int x = 10;
    public final int y = 10;

    public HumanBoard(){

        for(int y = 0; y < this.y; y++){
            HBox row = new HBox();
            for(int x =0; x < this.x; x++){
                position p = new position(x, y, this);
                row.getChildren().add(p);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public position getPosition(int x, int y){
        return (position)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }
    @Override
    public boolean getIsEnemy() {
        return isEnemy;
    }
}

