public class HumanPlayer extends Player {

    public HumanPlayer(GamePieces pea, GamePieces carrot, GamePieces potato, GamePieces tomato) {
        super(pea, carrot, potato, tomato);
    }

    @Override
    public String toString() {
        String s = pea.getName() + " " + carrot.getName() + " " + potato.getName() + " " + tomato.getName();
        return s;
    }
}
