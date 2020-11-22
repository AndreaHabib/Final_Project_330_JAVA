import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class Board extends Parent {
    private VBox rows = new VBox();
    private boolean isEmemey = false;
    public int numOfShips = 5;
    public final int x = 10;
    public final int y = 10;

    public Board(){
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


}
