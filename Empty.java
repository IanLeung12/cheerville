public class Empty extends Tile {

    Empty(int x, int y) {
        super(x, y);
    }

    public String getType() {
        return "empty";
    }

    public void age() {}

}
