import java.util.ArrayList;

public class MapDatabase {
    private Tile[][] map;

    private final double grassGrowRate;

    boolean rapid;
    MapDatabase(int sidelength, int humans, int zombies, double grassGrowRate, boolean rapid) {
        this.map = new Tile[sidelength][sidelength];

        this.rapid = rapid;

        this.grassGrowRate = grassGrowRate;

        for (int i = 0; i < map.length; i ++) {
            for (int j = 0; j < map[i].length; j ++) {
                map[i][j] = new Empty(j, i);
            }
        }
        int x;
        int y;

        do {
            x = (int) (Math.random() * (sidelength));
            y = (int) (Math.random() * (sidelength));
            if (map[y][x] instanceof Empty) {
                map[y][x] = new Human(x, y, rapid);
                humans --;
            }
        } while (humans > 0);

        do {
            x = (int) (Math.random() * (sidelength));
            y = (int) (Math.random() * (sidelength));
            if (map[y][x] instanceof Empty) {
                map[y][x] = new Zombie(x, y);
                zombies --;
            }
        } while (zombies > 0);
    }

    public void growGrass() {
        for (int i = 0; i < map.length; i ++) {
            for (int j = 0; j < map.length; j ++) {
                if (map[i][j] instanceof Empty) {
                    if (Math.random() <= grassGrowRate) {
                        map[i][j] = new Grass(j, i);
                    }
                }
            }
        }
    }

    public void moveAll() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                map[i][j].age();
                if (map[i][j] instanceof Movable) {
                    if (((Movable) map[i][j]).getHealth() <= 0) {
                        map[i][j] = new Empty(j, i);
                    } else if (!((Movable) map[i][j]).isMoved()){
                        ((Movable) map[i][j]).setMoved(true);
                        int sightDistance = ((Movable) map[i][j]).getSight();
                        Tile[][] sight = new Tile[sightDistance * 2 + 1][sightDistance * 2 + 1];
                        for (int y = 0; y < sight.length; y++) {
                            for (int x = 0; x < sight[y].length; x++) {
                                try {
                                    if ((x == sightDistance) && (y == sightDistance)) {
                                        sight[x][y] = null;
                                    } else {
                                        sight[y][x] = map[i - sightDistance + y][j - sightDistance + x];
                                    }
                                } catch (Exception e) {
                                    sight[y][x] = null;
                                }
                            }
                        }

                        int[] changedCoords = ((Movable) map[i][j]).move(sight);
                        int targetX = changedCoords[0];
                        int targetY = changedCoords[1];

                        if ((targetX != j) || (targetY != i)){
                            if (map[i][j] instanceof Human) {

                                if (map[targetY][targetX] instanceof Human) {
                                    breed((Human) map[targetY][targetX], (Human) map[i][j]);
                                } else {
                                    map[targetY][targetX] = map[i][j];
                                    map[i][j] = new Empty(j, i);
                                    if (((Human) map[targetY][targetX]).getTimePregnant() >= 2) {
                                        birth((Human) (map[targetY][targetX]));
                                    }
                                }

                            } else if (map[targetY][targetX] instanceof Human) {
                                zombify(targetX, targetY, (Zombie) map[i][j]);

                            } else {
                                map[targetY][targetX] = map[i][j];
                                map[i][j] = new Empty(j, i);
                            }

                        }


                    }
                } else if (map[i][j] instanceof Grass) {
                    if (map[i][j].getAge() > ((Grass) map[i][j]).getMaxAge()) {
                        map[i][j] = new Empty(j, i);
                    }
                }
            }
        }

        resetMoved();
    }

    public void breed(Human person1, Human person2) {
        Human female;
        if (person1.getGender().equals("female")) {
            female = person1;
        } else {
            female = person2;
        }

        female.setPregnant(true);


    }

    public void birth(Human mother) {
        ArrayList<Tile> babySpots = new ArrayList<Tile>();

        for (int i = mother.getY() - 1; i <= mother.getY() + 1; i ++) {
            for (int j = mother.getX() - 1; j <= mother.getX() + 1; j ++) {
                if ((i < map.length) && (i > 0) && (j < map[0].length) && (j > 0)) {
                    if ((map[i][j] instanceof Empty) || (map[i][j] instanceof Grass)) {
                        babySpots.add(map[i][j]);
                    }
                }
            }
        }

        if (babySpots.size() > 0) {
            Tile spot = babySpots.get((int) (Math.random() * babySpots.size()));
            map[spot.getY()][spot.getX()] = new Human(spot.getX(), spot.getY(), rapid);
            mother.setPregnant(false);
            mother.setTimePregnant(0);
        }
    }

    public void zombify(int x, int y, Zombie zombie) {
        if (((Human) map[y][x]).getHealth() <= zombie.getHealth()) {
            if (zombie.getHealth() < 100) {
                zombie.setHealth(zombie.getHealth() + ((Human) map[y][x]).getHealth());
            }
            map[y][x] = new Empty(x, y);

        } else {
            map[y][x] = new Zombie(x, y, ((Movable) map[y][x]).getHealth());
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public void resetMoved() {
        for (Tile[] row: map) {
            for (Tile tile: row) {
                if (tile instanceof Movable) {
                    ((Movable) tile).setMoved(false);
                }
            }
        }
    }
}
