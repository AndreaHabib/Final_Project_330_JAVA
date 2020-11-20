public class position {

    private boolean guess;
    private boolean hitOrMiss;
    private boolean occupied;
    private int length;
    private String direction;


    position(){
        guess = false;
        hitOrMiss = false;
        occupied = false;
        length = -1;
        direction = "UNDEF";
    }

    public boolean checkHitOrMiss(){
        return hitOrMiss;
    }

    public boolean isGuess(){
        return guess;
    }

    public int getLength() {
        return length;
    }

    public String getDirection() {
        return direction;
    }

    public boolean getOccupied(){
        return occupied;
    }

    public void setGuess(boolean guess) {
        this.guess = guess;
    }

    public void setHitOrMiss(boolean hitOrMiss) {
        this.hitOrMiss = hitOrMiss;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
