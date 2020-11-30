public interface Board{

    public boolean getIsEnemy();
    public boolean setPiece(int row, int col, String Dir, GamePieces piece);
    public int attack(int row, int col, Board other);
    public boolean positionValid(int row, int col);
    public position isValidPosition(int x, int y);
    public position getPosition(int x, int y);
    public void setAddPiece(int addPiece);
    public int getAddPiece();

}
