public abstract class GamePieces {

    protected String direction;
    protected int size;
    protected int row;
    protected int col;
    protected String name;

    public GamePieces() {
        this.size = size;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "N/A";
    }

    public GamePieces(String name) {
        this.size = size;
        row = -1;
        col = -1;
        direction = "UNDEF";
        this.name = name;
    }

    public void setDirection(String direction) {
        if(direction != "UNDEF" && direction != "VERTICAL" && direction != "HORIZONTAL") {
            throw new IllegalArgumentException("Direction is Invalid, must be UNDEF, VERTICAL, or HORIZONTAL");
        }
        this.direction = direction;
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

    public String toString(){
        String s = "";
        s += "Food piece: " + getName() + " has position of ("+ getRow() + ", " + getCol() + ")";
        return s;
    }

}
