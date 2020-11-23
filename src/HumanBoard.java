import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HumanBoard extends Parent implements Board{

    private VBox rows = new VBox();
    private final boolean isEmemy = false;
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

    @Override
    public boolean getIsEnemy() {
        return isEmemy;
    }
}

