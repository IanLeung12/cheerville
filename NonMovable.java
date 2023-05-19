abstract class NonMovable extends Tile{

    private final int maxAge;

    NonMovable(int x, int y, int maxAge) {
        super(x, y);
        this.maxAge = maxAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

}
