import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ComputerBoard extends Parent implements Board{

    private VBox rows = new VBox();
    private final boolean isEnemy = true;
    public int numOfShips = 4;
    public final int x = 10;
    public final int y = 10;

    public ComputerBoard(){
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

    public position isValidPosition(int x, int y) throws IndexOutOfBoundsException {
        return (position) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    public boolean setPiece(int row, int col, String Dir, GamePieces piece){
        int size = piece.getSize();
        int mode = 0;

        try {
            this.isValidPosition(row+size, col+size);
        } catch (IndexOutOfBoundsException e){
            mode = 1;
        }

        if(mode == 0) {
            if (Dir == "VERTICAL") {
                for (int i = 0; i < size; i++) {
                    if (!this.positionValid(row, col + i, piece)) {
                        return false;
                    }
                }
                for (int i = 0; i < size; i++) {
                    this.getPosition(row, col + i).changeColor(row, col + i, 3);
                    this.getPosition(row, col + i).setOccupied(true);
                }
            } else if (Dir == "HORIZONTAL") {
                for (int i = 0; i < size; i++) {
                    if (!this.positionValid(row + i, col, piece)) {
                        return false;
                    }
                }
                for (int i = 0; i < size; i++) {
                    this.getPosition(row + i, col).changeColor(row + i, col, 3);
                    this.getPosition(row + i, col).setOccupied(true);
                }

            }
        }
        else if(mode == 1) {

            if (Dir == "VERTICAL") {
                for (int i = size-1; i >= 0; i--) {
                    if (!this.positionValid(row, col - i, piece)) {
                        return false;
                    }
                }
                for (int i = size-1; i >= 0; i--) {
                    this.getPosition(row, col - i).changeColor(row, col - i, 3);
                    this.getPosition(row, col - i).setOccupied(true);
                }
            } else if (Dir == "HORIZONTAL") {
                for (int i = size-1; i >= 0; i--) {
                    if (!this.positionValid(row - i, col, piece)) {
                        return false;
                    }
                }
                for (int i = size-1; i >= 0; i--) {
                    this.getPosition(row - i, col).changeColor(row - i, col, 3);
                    this.getPosition(row - i, col).setOccupied(true);
                }

            }

        }

        return true;

    }

    public boolean positionValid(int row, int col, GamePieces piece){
        if(this.getPosition(row, col).getOccupied()){
            return false;
        }
        else if((row >= 10 || col >= 10) && (row < 0 || col < 0)) {
            throw new IllegalArgumentException("Invalid Row and Column!");
        }
        else {
            return true;
        }
    }

    @Override
    public boolean getIsEnemy() {
        return isEnemy;
    }
}

