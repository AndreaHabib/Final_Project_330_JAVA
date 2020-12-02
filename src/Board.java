/**
 *
 * Board
 * Interface class that has methods for the HumanBoard and ComputerBoard.
 *
 */
public interface Board{

    /**
     * Method to setPieces on the board for Human and Computer players.
     *
     * @param row
     * @param col
     * @param Dir
     * @param piece
     * @return boolean. returns true if setting the piece was successful, false if the user/computer attempted invalid or out of range location.
     */
    public boolean setPiece(int row, int col, String Dir, GamePieces piece);

    /**
     * Attack method, used by Human and Computer to attack other player's pieces.
     *
     * @param row
     * @param col
     * @param other
     * @return int. returns type of attack. 1 if hit, 2 if already guessed, 3 if missed, 0 if program failure.
     */
    public int attack(int row, int col, Board other);

    /**
     *Checks if the entered position is valid - only checks if user/computer is choosing an occupied grid. No exception handling.
     *
     * @param row
     * @param col
     * @return boolean. returns true if location is valid, else false
     */
    public boolean positionValid(int row, int col);

    /**
     * Checks if the entered position is within range. Throws exception if out of range.
     *
     * @param x
     * @param y
     * @return position. returns the specific grid at x and y.
     */
    public position isValidPosition(int x, int y);

    /**
     * Gets specific location on the grid. Also used to increment and decrement grids for game pieces.
     *
     * @param x
     * @param y
     * @return position. returns the specific grid at x and y.
     */
    public position getPosition(int x, int y);

    /**
     * Method to incrememnt the amount of pieces on both board.
     *
     * @param addPiece
     */
    public void setAddPiece(int addPiece);

    /**
     * Returns addPiece var. Used to decide the winner when addPiece is 0.
     *
     * @return
     */
    public int getAddPiece();

}
