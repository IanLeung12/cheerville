abstract class Tile {
    private int x;
    private int y;

    private int age;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void age() {
        this.age ++;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
