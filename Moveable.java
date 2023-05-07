abstract class Movable extends Tile{

    private int sight;
    private int age;

    Movable(int x, int y, int sight, int age) {
        super(x, y);
        this.sight = sight;
        this.age = age;
    }

    abstract void move(Tile[][] sight);

    public int getSight() {
        return sight;
    }

    public void setSight(int sight) {
        this.sight = sight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
