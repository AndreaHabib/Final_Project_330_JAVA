import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class position extends Rectangle {

    private boolean guess;
    private boolean hitOrMiss;
    private boolean occupied;
    private int length;
    private String direction;
    private int x;
    private int y;
    private Board board;


    public position(int x, int y, Board board){
        super(40, 40);
        this.x= x;
        this.y =y;
        this.board = board;
        if(board.getIsEnemy() == false){
            setFill(Color.RED);
        }
        else{
            setFill(Color.BLUE);
        }
        setStroke(Color.BLACK);
        guess = false;
        hitOrMiss = false;
        occupied = false;
        length = -1;
        direction = "UNDEF";
    }

    public boolean checkHitOrMiss(){
        return hitOrMiss;
    }

    public boolean isGuess(){
        return guess;
    }

    public int getLength() {
        return length;
    }

    public String getDirection() {
        return direction;
    }

    public boolean getOccupied(){
        return occupied;
    }

    public void setGuess(boolean guess) {
        this.guess = guess;
    }

    public void setHitOrMiss(boolean hitOrMiss) {
        this.hitOrMiss = hitOrMiss;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void changeColor(int x, int y, int action){
        if(x == this.x && y == this.y){

            switch(action) {
                //hit
                case 0:
                    setFill(Color.RED);
                    break;
                //Miss
                case 1:
                    setFill(Color.BLACK);
                    break;
                default:
                    setFill(Color.YELLOW);

            }
        }
    }
}
