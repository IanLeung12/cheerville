public class Ground extends Tile {

    Ground(int x, int y) {
        super(x, y);
    }

    public String getType() {
        return "empty";
    }

    public void age() {}

}
