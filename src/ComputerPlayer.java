public class ComputerPlayer extends Player{
    public ComputerPlayer(GamePieces pea, GamePieces carrot, GamePieces potato, GamePieces tomato) {
        super(pea, carrot, potato, tomato);
    }

    public String toString() {
        String s = pea.getName() + " " + carrot.getName() + " " + potato.getName() + " " + tomato.getName();
        return s;
    }
}
