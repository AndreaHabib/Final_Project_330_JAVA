import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * Position
 * Provides logic for each individual grid position on our boards.
 * Each grid position is a rectangle, so we extend rectangle class
 *
 */
public class position extends Rectangle {

    //Variable Declaration
    private boolean guess;
    private boolean hitOrMiss;
    private boolean occupied;
    private int x;
    private int y;
    private Board board;

    //Initializing each position of the board
    public position(int x, int y, Board board){
        super(40, 40);
        this.x= x;
        this.y =y;
        this.board = board;
        Color c1 = Color.web("#b5651d");
        Color c2 = Color.web("#cc9b50");
        if(y >= 8 || y < 2 || x < 2 || x >= 8) { //Setting Design for our board
            setFill(c1);
        }
        else {
            setFill(c2);
        }
        setStroke(Color.BLACK);
        guess = false;
        hitOrMiss = false;
        occupied = false;
    }

    /**
     * Method to return if position on grid has been guessed or not guessed.
     *
     * @return boolean. If position is already guessed return true, if not return false
     */
    public boolean isGuess(){
        return guess;
    }

    /**
     * Method to return if position on grid is occupied by a game piece.
     *
     * @return boolean. Return true if piece of ship is located on grid position, return false if empty.
     */
    public boolean getOccupied(){
        return occupied;
    }

    /**
     * Method to set the guessed state of the position on the grid.
     *
     * @param guess
     */
    public void setGuess(boolean guess) {
        this.guess = guess;
    }

    /**
     * Method to set hitOrMiss state of the position on the grid.
     *
     * @param hitOrMiss
     */
    public void setHitOrMiss(boolean hitOrMiss) {
        this.hitOrMiss = hitOrMiss;
    }

    public boolean getHitOrMiss() {return this.hitOrMiss; }

    /**
     * Method to set the occupation state of the position on the grid.
     *
     * @param occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Method to Change the image of grid position depending on the action performed by the user/computer.
     * Also to initially set the image of the game piece when user/computer setting game pieces on board
     *
     * @param x
     * @param y
     * @param action
     */
    public void changeColor(int x, int y, int action){

        if(x == this.x && y == this.y){

            switch(action) { //Switch state to determine what image to display based on action input
                //hit->change image to explosion
                case 0:
                    Image hit = new Image("/explosion.png");
                    setFill(new ImagePattern(hit));

                    break;
                //Miss->change image to X
                case 1:
                    Image miss = new Image("/miss.png");
                    setFill(new ImagePattern(miss));
                    break;
                //Setting carrot game piece
                case 3:
                    Image carrot = new Image("/carrot.png");
                    setFill(new ImagePattern(carrot));
                    break;
                //Setting potato game piece
                case 4:
                    Image potato = new Image("/potato.png");
                    setFill(new ImagePattern(potato));
                    break;
                //Setting tomato game piece
                case 5:
                    Image tomato = new Image("/tomato.png");
                    setFill(new ImagePattern(tomato));
                    break;
                //Setting pea game piece
                case 6:
                    Image peas = new Image("/peas.png");
                    setFill(new ImagePattern(peas));
                    break;
                //If action not recognized set yellow for debugging
                default:
                    setFill(Color.YELLOW);
            }
        }
    }

    @Override
    public String toString() {
        return "position{" +
                "guess=" + guess +
                ", hitOrMiss=" + hitOrMiss +
                ", occupied=" + occupied +
                ", x=" + x +
                ", y=" + y +
                ", board=" + board +
                '}';
    }
}
