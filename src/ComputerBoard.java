//all imports
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * ComputerBoard
 *
 * Extends Parent class and implements board interface
 * Generates board for computer board.
 *
 */
public class ComputerBoard extends Parent implements Board{

    //Declaration and initialization of variables
    private VBox rows = new VBox();
    public final int x = 10;
    public final int y = 10;
    public int addPiece = 0; //num of pieces on board
    public boolean cheatOnOff = false; //Allow players to see computer board

    //Default constructor that creates HBoxes, VBoxes, and instantiates position class.
    //Position objects are placed into HBoxes and those HBoxes are placed in VBoxes, creating our grid
    public ComputerBoard(){
        for(int y = 0; y < this.y; y++){
            HBox row = new HBox();
            for(int x =0; x < this.x; x++){
                position p = new position(x, y, this);
                row.getChildren().add(p);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public void setCheatOnOff(boolean cheatOnOff) {
        this.cheatOnOff = cheatOnOff;
    }

    /**
     * Method to return the position object when given the coordinates to locate a specific grid position
     *
     * @param x
     * @param y
     * @return position object, allows you to manipulate individual grid position
     */
    public position getPosition(int x, int y){
        return (position)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    /**
     * Method that check whether a position is within bounds of our play area grid
     *
     * @param x
     * @param y
     * @return position object, allows you to manipulate individual grid position
     * @throws IndexOutOfBoundsException when x and y are out of bounds of our play area grid
     */
    public position isValidPosition(int x, int y) throws IndexOutOfBoundsException {
        return (position) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    /**
     * Method to set game pieces on board for computer player
     *
     * @param row
     * @param col
     * @param Dir
     * @param piece
     * @return boolean. If placement is sucessful return true, else return false
     * @throws IndexOutOfBoundsException when x and y are out of bounds of our play area grid
     */
    public boolean setPiece(int row, int col, String Dir, GamePieces piece) throws IndexOutOfBoundsException{

        int size = piece.getSize(); //Getting size of game piece
        int mode = 0; //Setting mode depending on exception

        int pieceName = 0;
        //Getting name of game piece
        //only if cheats are on
        if(cheatOnOff == true) {
            if (piece.getName() == "Carrot") {
                pieceName = 3;
            } else if (piece.getName() == "Potato") {
                pieceName = 4;
            } else if (piece.getName() == "Tomato") {
                pieceName = 5;
            } else if (piece.getName() == "Pea") {
                pieceName = 6;
            }
        }

        //Checking if game piece will fit in default orientation (down and right)
        //If game piece will not fit set to other orientation (up and left)
        try {
            this.isValidPosition(row + size, col + size); //checking if game piece will go out of bounds
        } catch (IndexOutOfBoundsException e){
            if(row >= 10 || col >= 10 || row < 0 || col < 0) {
                throw new IndexOutOfBoundsException("Invalid Location");
            }
            else if (row + size >= 9 && col + size >= 9 || (row + size >= 9 && col + size <= 9 && piece.getDirection() != "VERTICAL") || (row + size <= 9 && col + size >= 9 && piece.getDirection() != "HORIZONTAL")){
                mode = 1;
            }
        }

        if(mode == 0) { //Mode 0 is default orientation for pieces, down for vertical and right for horizontal
            if (Dir == "VERTICAL") { //checking conditions for vertical placement
                for (int i = 0; i < size; i++) {
                    if (!this.positionValid(row, col + i)) {
                        return false;
                    }
                }
                for (int i = 0; i < size; i++) { //if conditions are met use get position to set color and set occupation
                    if(cheatOnOff) {
                        this.getPosition(row, col + i).changeColor(row, col + i, pieceName);
                    }
                    this.getPosition(row, col + i).setOccupied(true);
                    this.addPiece++;
                }
            } else if (Dir == "HORIZONTAL") { //checking condition for horizontal placement
                for (int i = 0; i < size; i++) {
                    if (!this.positionValid(row + i, col)) {
                        return false;
                    }
                }
                for (int i = 0; i < size; i++) { //if conditions are met use get position to set color and occupation
                    if(cheatOnOff) {
                        this.getPosition(row + i, col).changeColor(row + i, col, pieceName);
                    }
                    this.getPosition(row + i, col).setOccupied(true);
                    this.addPiece++;
                }

            }
        }
        else if(mode == 1) { //Mode 1 is alternate mode if game piece doesnt fit in default
                             //Up for vertical, right for horizontal

            if (Dir == "VERTICAL") { //checking conditions for vertical placement
                for (int i = size-1; i >= 0; i--) {
                    if (!this.positionValid(row, col - i)) {
                        return false;
                    }
                }
                for (int i = size-1; i >= 0; i--) { //if conditions are met use get position to set color and occupation
                    if(cheatOnOff) {
                        this.getPosition(row, col - i).changeColor(row, col - i, pieceName);
                    }
                    this.getPosition(row, col - i).setOccupied(true);
                    this.addPiece++;
                }
            } else if (Dir == "HORIZONTAL") { // checking condition for horizontal placement
                for (int i = size-1; i >= 0; i--) {
                    if (!this.positionValid(row - i, col)) {
                        return false;
                    }
                }
                for (int i = size-1; i >= 0; i--) { //if conditions are met use get position to set color and occupation
                    if(cheatOnOff) {
                        this.getPosition(row - i, col).changeColor(row - i, col, pieceName);
                    }
                    this.getPosition(row - i, col).setOccupied(true);
                    this.addPiece++;
                }

            }

        }


        return true;
    }

    /**
     * Method to check if selected position is valid or not
     *
     * @param row
     * @param col
     * @return boolean. Return true is valid, else false
     */
    public boolean positionValid(int row, int col){
        if(this.getPosition(row, col).getOccupied()){
            return false;
        }
        else if((row >= 10 || col >= 10) && (row < 0 || col < 0)) {
            throw new IllegalArgumentException("Invalid Row and Column!");
        }
        else {
            return true;
        }
    }

    /**
     * Method that allows human player to attack computer player board
     *
     * @param row
     * @param col
     * @param other
     * @return integer value depending on success of attack, 1 for hit, 2 already guessed, 3 for miss
     * @throws IndexOutOfBoundsException if x and y are not valid locations on our play area grid
     */
    @Override
    public int attack(int row, int col, Board other) throws IndexOutOfBoundsException {
        try { //checking if position is valid on our board
            other.positionValid(row, col);
        } catch (IndexOutOfBoundsException e){
            if(row >= 10 || col >= 10 || row < 0 || col < 0) {
                throw new IndexOutOfBoundsException("Invalid Location");
            }
        }
        //checking if grid position is occupied and guessed already, if not set hit
        if(other.getPosition(row, col).getOccupied() && !other.getPosition(row, col).isGuess()) {
            other.getPosition(row, col).setGuess(true);
            other.getPosition(row, col).setHitOrMiss(true);
            other.getPosition(row, col).changeColor(row, col, 0);
            other.setAddPiece(other.getAddPiece()-1); //removing 1 from win condition
            return 1;
        }
        //checking if position was already guessed
        else if(other.getPosition(row, col).isGuess()){
            return 2;

        }
        //checking if position is occupied, if so miss is set
        else if(!other.getPosition(row, col).getOccupied()) {
            other.getPosition(row, col).setGuess(true);
            other.getPosition(row, col).setHitOrMiss(false);
            other.getPosition(row, col).changeColor(row, col, 1);
            return 3;
        }

        return 0;

    }

    /**
     * Method to return win condition
     *
     * @return int, value of addPieces variable
     */
    public int getAddPiece() {
        return addPiece;
    }

    /**
     * Method to set value of addPieces variable
     *
     * @param addPiece
     */
    public void setAddPiece(int addPiece) {
        this.addPiece = addPiece;
    }

}

