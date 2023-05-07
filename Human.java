class Human extends Movable{

    int hunger;

    Human(int x, int y, int sight, int age, int hunger) {
        super(x, y, sight, age);
        this.hunger = hunger;
    }

    public void move(Tile[][] sight) {
        String bestChoice = "empty";
        int bestX = 0;
        int bestY = 0;
        double distance;
        double bestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < sight.length; i ++) {
            for (int j = 0; j < sight[i].length; j ++) {
                distance = Math.sqrt((Math.pow((-2 + j), 2)) + (Math.pow((-2 + i), 2)));
                if ((sight[i][j] instanceof Grass) && hunger > 5) {

                    if ((distance < bestDistance) && (bestChoice.equals("grass"))) {
                        bestDistance = distance;
                        bestX = this.getX() - 2 + j;
                        bestY = this.getY() - 2 + i;
                    }
                    bestChoice = "grass";

                } else if ((sight[i][j] instanceof Human) && ((i != 2) || (j != 2)) && (!bestChoice.equals("grass"))) {
                    bestChoice = "human";

                    if (distance < bestDistance) {
                        bestDistance = distance;
                        bestX = this.getX() - 2 + j;
                        bestY = this.getY() - 2 + i;
                    }
                }
            }
        }

        if (bestChoice.equals("empty")) {
            do {
                bestX = this.getX() - 2 + (int) (Math.random() * sight.length);
                bestY = this.getY() - 2 + (int) (Math.random() * sight.length);
            } while (sight[bestY + 2 + this.getY()][bestX + 2 + this.getX()] instanceof Zombie);
        }

        this.setX(bestX);
        this.setY(bestY);
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
}
