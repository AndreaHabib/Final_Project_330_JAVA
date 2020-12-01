import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class position extends Rectangle {

    private boolean guess;
    private boolean hitOrMiss;
    private boolean occupied;
    private String direction;
    private int x;
    private int y;
    private Board board;


    public position(int x, int y, Board board){
        super(40, 40);
        this.x= x;
        this.y =y;
        this.board = board;
        Color c1 = Color.web("#b5651d");
        Color c2 = Color.web("#cc9b50");
        if(y >= 8 || y < 2 || x < 2 || x >= 8) {
            setFill(c1);
        }
        else {
            setFill(c2);
        }
        setStroke(Color.BLACK);
        guess = false;
        hitOrMiss = false;
        occupied = false;
        direction = "UNDEF";
    }

    public boolean isGuess(){
        return guess;
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

    public void changeColor(int x, int y, int action){
        if(x == this.x && y == this.y){

            switch(action) {
                //hit
                case 0:
                    Image hit = new Image("/explosion.png");
                    setFill(new ImagePattern(hit));
                    break;
                //Miss
                case 1:
                    Image miss = new Image("/miss.png");
                    setFill(new ImagePattern(miss));
                    break;
                case 3:
                    Image carrot = new Image("/carrot.png");
                    setFill(new ImagePattern(carrot));
                    break;
                case 4:
                    Image potato = new Image("/potato.png");
                    setFill(new ImagePattern(potato));
                    break;
                case 5:
                    Image tomato = new Image("/tomato.png");
                    setFill(new ImagePattern(tomato));
                    break;
                case 6:
                    Image peas = new Image("/peas.png");
                    setFill(new ImagePattern(peas));
                    break;
                default:
                    setFill(Color.YELLOW);
            }
        }
    }
}
