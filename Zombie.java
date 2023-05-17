import java.util.ArrayList;
class Zombie extends Movable{
    Zombie(int x, int y) {
        super(x, y, 1);
    }

    Zombie(int x, int y, int health) {
        super(x, y, 1, health);
    }

    public int[] move(Tile[][] sight) {
        int choice;
        ArrayList<ArrayList<Integer>> humans = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < sight.length; i ++) {
            for (int j = 0; j < sight[i].length; j++) {
                if (sight[i][j] instanceof Human) {
                    humans.add(new ArrayList<Integer>());
                    humans.get(humans.size() - 1).add(this.getX() - 1 + j);
                    humans.get(humans.size() - 1).add(this.getY() - 1 + i);

                }
            }
        }

        if (humans.size() == 0) {
            int x;
            int y;
            int counter = 0;
            do {
                x = (int) (Math.random() * sight.length);
                y = (int) (Math.random() * sight.length);
                counter ++;
            } while (!(sight[y][x] instanceof Empty) && !(sight[y][x] instanceof Grass) && (counter < 12));

            if (counter >= 12) {
                x = 1;
                y = 1;
            }
            this.setX(this.getX() + x - 1);
            this.setY(this.getY() + y - 1);
            return new int[]{this.getX(), this.getY()};

        } else {
            choice = (int) (Math.random() * (humans.size() - 1));
            return new int[] {humans.get(choice).get(0), humans.get(choice).get(1)};
        }

    }

    public String getType() {
        return "zombie";
    }

    public void age(){
        this.setHealth(this.getHealth() - 5);
    }

}
