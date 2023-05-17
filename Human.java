import java.sql.SQLOutput;

class Human extends Movable {

    private int hunger;

    private final String gender;

    private boolean pregnant = false;

    private int timePregnant = 0;

    boolean rapid;

    Human(int x, int y) {
        super(x, y, 2);
        this.hunger = 0;
        if (Math.random() >= 0.5) {
            this.gender = "female";
        } else {
            this.gender = "male";
        }
        this.rapid = rapid;
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
                    if (getConsent((Human) (sight[i][j]))) {
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
            } while (!(sight[bestY][bestX] instanceof Empty) && !(sight[bestY][bestX] instanceof Grass) && (counter < 12));
            if (counter >= 12) {
                bestX = 0;
                bestY = 0;
            } else {
                bestX -= radius;
                bestY -= radius;
            }

        } else if (bestChoice.equals("grass")) {
            this.hunger -= bestPlant;
        }




        if (bestChoice.equals("human")) {
            return new int[] {this.getX() + bestX, this.getY() + bestY};
        } else {
            this.setX(this.getX() + bestX);
            this.setY(this.getY() + bestY);
            return new int[]{this.getX(), this.getY()};
        }
    }

    public void age() {
        super.age();
        this.hunger += 10;
        if (this.hunger > 75) {
            this.setHealth(this.getHealth() - (this.hunger - 75));
        }

        if (this.getAge() > 15) {
            this.setHealth(this.getHealth() - (int) (Math.random() * 10));
        }

        if (pregnant) {
            this.timePregnant ++;
        }


    }

    public boolean getConsent(Human partner) {
        return (!(partner.getGender().equals(this.gender) && ((rapid) || (this.getAge() > 4)) && (partner.getAge() > 4) && !(this.pregnant) && !(partner.isPregnant()) && (partner.getHunger() <= 50)));
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

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public int getTimePregnant() {
        return timePregnant;
    }

    public void setTimePregnant(int timePregnant) {
        this.timePregnant = timePregnant;
    }
}
