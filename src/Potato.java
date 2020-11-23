public class Potato extends GamePieces{
    private String direction;
    private int size;
    private int row;
    private int col;
    private String name;

    public Potato() {
        this.size = 3;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "Potato";
    }
}
