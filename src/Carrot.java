public class Carrot extends GamePieces{
    private String direction;
    private int size;
    private int row;
    private int col;
    private String name;

    public Carrot() {
        this.size = 4;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "Carrot";
    }
}
