public abstract class Player {

    protected GamePieces carrot;
    protected GamePieces pea;
    protected GamePieces potato;
    protected GamePieces tomato;

    public Player() {
        carrot = null;
        potato = null;
        pea = null;
        tomato = null;
    }

    public Player(GamePieces pea, GamePieces carrot, GamePieces potato, GamePieces tomato) {
        this.pea = pea;
        this.carrot = carrot;
        this.potato = potato;
        this.tomato = tomato;
    }


}
