class Grass extends Tile {
    private int age;
    private int maxAge;

    Grass(int x, int y) {
        super(x, y);
        this.age = 0;
        this.maxAge = (int) (Math.random() * 5 + 5);
    }


}
