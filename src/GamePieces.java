public abstract class GamePieces { //abstract

    //all variables used
    protected String direction;
    protected int size;
    protected int row;
    protected int col;
    protected String name;

    //default constructor
    public GamePieces() {
        this.size = size;
        row = -1;
        col = -1;
        direction = "UNDEF";
        name = "N/A";
    }

    //parameterized constructor
    public GamePieces(String name) {
        this.size = size;
        row = -1;
        col = -1;
        direction = "UNDEF";
        this.name = name;
    }

    /**
     * Sets the direction of the specified piece
     *
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * get Column
     *
     * @return col. column
     */
    public int getCol() {
        return col;
    }

    /**
     * get Row
     *
     * @return row, Row
     */
    public int getRow() {
        return row;
    }

    /**
     * Size of the piece
     *
     * @return size, size of the piece
     */
    public int getSize() {
        return size;
    }

    /**
     * get Direction
     *
     * @return direction, direction of the piece
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Name of the piece
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Print coordinates, testing purposes
     *
     * @return string, coordinates
     */
    public String toString(){
        String s = "";
        s += "Food piece: " + getName() + " has position of ("+ getRow() + ", " + getCol() + ")";
        return s;
    }

}
