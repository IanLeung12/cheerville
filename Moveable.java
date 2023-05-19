abstract class Movable extends Tile{

    private int sight;

    private int health;

    private boolean moved;

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    Movable(int x, int y, int sight) {
        super(x, y);
        this.sight = sight;
        this.health = 100;
        this.moved = false;
    }

    Movable(int x, int y, int sight, int health) {
        super(x, y);
        this.sight = sight;
        this.health = 100;
    }

    abstract public int[] move(Tile[][] sight);

    public int getSight() {
        return sight;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
