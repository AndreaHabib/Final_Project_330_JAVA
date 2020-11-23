public class Pea extends GamePieces{
    private String direction;
    private int size;
    private int row;
    private int col;
    private String name;

    public Pea() {
        this.size = 1;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "Pea";
    }
}
