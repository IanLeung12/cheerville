
class Human extends Movable {

    private int hunger;

    private String gender;

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
        for (int i = 0; i < sight.length; i ++) {
            for (int j = 0; j < sight[i].length; j++) {
                if ((sight[i][j] instanceof Grass) && hunger > 50) {
                    int plantValue = ((Grass) sight[i][j]).giveHunger();
                    if (plantValue > bestPlant) {
                        bestPlant = plantValue;
                        bestX = j - 2;
                        bestY = i - 2;
                    }
                    bestChoice = "grass";

                } else if ((sight[i][j] instanceof Human) && ((i != 2) || (j != 2)) && (!bestChoice.equals("grass"))) {
                    if (!((Human) sight[i][j]).getGender().equals(this.gender)) {
                        distance = Math.sqrt((Math.pow((-2 + j), 2)) + (Math.pow((-2 + i), 2)));
                        bestChoice = "human";
                        if (distance < bestDistance) {
                            bestDistance = distance;
                            bestX = j - 2;
                            bestY = i - 2;
                        }
                    }
                }
            }
        }

        if (bestChoice.equals("empty")) {
            do {
                bestX = (int) (Math.random() * sight.length);
                bestY = (int) (Math.random() * sight.length);

            } while ((sight[bestY][bestX] instanceof Zombie) || (sight[bestY][bestX] == null));
        } else if (bestChoice.equals("grass")) {
            hunger -= bestPlant;
        }



        if (this.getX() - 2 + bestX > 99 || this.getY() - 2 + bestY > 99 || this.getX() - 2 + bestX < 0 || this.getY() - 2 + bestY < 0) {
            for (Tile[] row: sight) {
                for (Tile tile: row) {
                    try {
                        System.out.print(tile.getType() + " ");
                    } catch (Exception e) {
                        System.out.print("Null ");
                    }

                }
                System.out.println();
            }
            System.out.println(bestChoice + " " +  this.getX() + " " + this.getY());
            System.out.println(bestX + " " + bestY);
            System.out.println((this.getX() + bestX) + " " + (this.getY() + bestY));

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

        /*if (this.getAge() > 12) {
            this.setHealth(this.getHealth() - (int) (Math.random() * 10));
        }*/


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

    public void setGender(String gender) {
        this.gender = gender;
    }

}
