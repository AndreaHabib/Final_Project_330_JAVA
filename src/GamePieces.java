public abstract class GamePieces {

    private String direction;
    private int size;
    private int row;
    private int col;
    private String name;

    public GamePieces() {
        this.size = size;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "N/A";
    }

    public void setRows(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setDirection(String direction) {
        if(direction != "UNDEF" && direction != "VERTICAL" && direction != "HORIZONTAL") {
            throw new IllegalArgumentException("Direction is Invalid, must be UNDEF, VERTICAL, or HORIZONTAL");
        }
        this.direction = direction;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getSize() {
        return size;
    }

    public String getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectSet(){
        if(direction == "UNDEF"){
            return false;
        }
        else
            return true;

    }

    public boolean isPositionSet(){
        if(row == -1 || col == -1){
            return false;
        }
        else return true;
    }

    public String toString(){
        String s = "";
        s += "Food piece: " + getName() + " has position of ("+ getRow() + ", " + getCol() + ")";
        return s;
    }

}
