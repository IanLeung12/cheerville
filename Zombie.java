import java.util.ArrayList;
class Zombie extends Movable{
    Zombie(int x, int y, int sight, int age) {
        super(x, y, sight, age);
    }

    public void move(Tile[][] sight) {
        int choice;
        ArrayList<ArrayList<Integer>> humans = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < sight.length; i ++) {
            for (int j = 0; j < sight[i].length; j++) {
                if (sight[i][j] instanceof Human) {
                    humans.add(new ArrayList<Integer>());
                    humans.get(humans.size() - 1).add(this.getX() - 2 + j);
                    humans.get(humans.size() - 1).add(this.getY() - 2 + i);

                }
            }
        }

        if (humans.size() == 0) {
            this.setX(this.getX() - 2 + (int) (Math.random() * sight.length));
            this.setY(this.getY() - 2 + (int) (Math.random() * sight.length));

        } else {
            choice = (int) (Math.random() * (humans.size() - 1));
            this.setX(humans.get(choice).get(0));
            this.setX(humans.get(choice).get(1));
        }
    }

}
