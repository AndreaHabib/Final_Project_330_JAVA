public class Tomato extends GamePieces{
    private String direction;
    private int size;
    private int row;
    private int col;
    private String name;

    public Tomato() {
        this.size = 2;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "Tomato";
    }
}
