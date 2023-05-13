public class MapDatabase {
    private Tile[][] map;

    MapDatabase(int length, int width, int humans, int zombies, int grass) {
        this.map = new Tile[length][width];

        for (int i = 0; i < map.length; i ++) {
            for (int j = 0; j < map[i].length; j ++) {
                map[i][j] = new Empty(j, i);
            }
        }
        int x;
        int y;
        do {
           x = (int) (Math.random() * (width));
           y = (int) (Math.random() * (length));
           if (map[y][x] instanceof Empty) {
               map[y][x] = new Grass(x, y);
               grass --;
           }
        } while (grass > 0);

        do {
            x = (int) (Math.random() * (width));
            y = (int) (Math.random() * (length));
            if (map[y][x] instanceof Empty) {
                map[y][x] = new Human(x, y);
                humans --;
            }
        } while (humans > 0);

        do {
            x = (int) (Math.random() * (width));
            y = (int) (Math.random() * (length));
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
                    if (Math.random() < 0.1) {
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
                                    System.out.println("IS OUT OF BOUNDS");
                                    sight[y][x] = null;
                                }
                            }
                        }
                        int[] changedCoords = ((Movable) map[i][j]).move(sight);
                        int targetX = changedCoords[0];
                        int targetY = changedCoords[1];

                        if (map[i][j] instanceof Human) {

                            if (map[targetY][targetX] instanceof Grass) {
                                ((Human) map[i][j]).setHunger(((Human) map[i][j]).getHunger() - ((Grass) map[targetY][targetX]).giveHunger());
                                map[targetY][targetX] = map[i][j];
                                map[i][j] = new Empty(j, i);
                            }

                        } else if (map[targetY][targetX] instanceof Human) {
                            zombify(targetX, targetY);

                        } else {
                            map[targetY][targetX] = map[i][j];
                            map[i][j] = new Empty(j, i);
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
    public void zombify(int x, int y) {
        map[y][x] = new Zombie(x, y, ((Movable) map[y][x]).getHealth());

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