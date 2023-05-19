class Grass extends NonMovable {

    Grass(int x, int y) {
        super(x, y, (int) (Math.random() * 6 + 7));
    }

    public int giveHunger() {
        return (int) (-1.2 * this.getAge() * (this.getAge() - this.getMaxAge())) + 5;
    }

}
