
class Human extends Movable {

    private int hunger;

    private final String gender;

    Human(int x, int y) {
        super(x, y, 2);
        this.hunger = 0;
        if (Math.random() >= 0.5) {
            this.gender = "female";
        } else {
            this.gender = "male";
        }
    }

    public int[] move(Tile[][] sight) {
        String bestChoice = "empty";
        int bestX = 0;
        int bestY = 0;
        double distance;
        double bestDistance = Integer.MAX_VALUE;
        int bestPlant = 0;
        int radius = this.getSight();
        for (int i = 0; i < sight.length; i ++) {
            for (int j = 0; j < sight[i].length; j++) {
                if ((sight[i][j] instanceof Grass) && hunger > 50) {
                    int plantValue = ((Grass) sight[i][j]).giveHunger();
                    if (plantValue > bestPlant) {
                        bestPlant = plantValue;
                        bestX = j - radius;
                        bestY = i - radius;
                    }
                    bestChoice = "grass";

                } else if ((sight[i][j] instanceof Human) && (!bestChoice.equals("grass"))) {
                    if (!((Human) sight[i][j]).getGender().equals(this.gender)) {
                        distance = Math.sqrt((Math.pow((j - radius), 2)) + (Math.pow((i - radius), 2)));
                        bestChoice = "human";
                        if (distance < bestDistance) {
                            bestDistance = distance;
                            bestX = j - radius;
                            bestY = i - radius;
                        }
                    }
                }
            }
        }

        if (bestChoice.equals("empty")) {
            int counter = 0;
            do {
                bestX = (int) (Math.random() * sight.length);
                bestY = (int) (Math.random() * sight.length);
                counter++;
            } while ((sight[bestY][bestX] instanceof Zombie) || (sight[bestY][bestX] == null));
            System.out.println(sight[bestY][bestX]);
            bestX -= radius;
            bestY -= radius;

        } else if (bestChoice.equals("grass")) {
            hunger -= bestPlant;
        }




        this.setX(this.getX() + bestX);
        this.setY(this.getY() + bestY);
        return new int[]{this.getX(), this.getY()};
    }

    public void age() {
        super.age();
        hunger += 10;
        if (hunger > 75) {
            this.setHealth(this.getHealth() - (hunger - 75));
        }

        if (this.getAge() > 12) {
            this.setHealth(this.getHealth() - (int) (Math.random() * 10));
        }


    }

    public String getType() {
        return "human";
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public String getGender() {
        return gender;
    }


}
