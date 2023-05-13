class Grass extends Tile {
    private final int maxAge;

    public int getMaxAge() {
        return maxAge;
    }

    Grass(int x, int y) {
        super(x, y);
        this.setAge(0);
        this.maxAge = (int) (Math.random() * 5 + 7);
    }

    public String getType() {
        return "grass";
    }

    public int giveHunger() {
        return (int) (-1.5 * this.getAge() * (this.getAge() - maxAge));
    }

}
