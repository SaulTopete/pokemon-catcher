package byow.Core3;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Random;

public class World {
    public int WIDTH;
    public int HEIGHT;
    public long SEED;
    public boolean[][] VISITED;
    public Random RANDOM;
    public LinkedList<Room> rooms = new LinkedList<>();
    public Point<Integer, Integer> avatarPoint;
    public static final LinkedList<String> AVATARMOVES = new LinkedList<>();
//    public int NUMFLASH = 3;
    public static final double LIGHTDIST = 2.5;
    //can't put any rooms or hallways in the top row since this where the hud is


    public World(int maxX, int maxY, long seed, Point avatarPoint) {
        this.avatarPoint = avatarPoint;
        this.RANDOM = new Random(seed);
        this.WIDTH = maxX;
        this.HEIGHT = maxY;
        this.SEED = seed;
        this.VISITED = new boolean[maxX][maxY];
    }


//    public int getFlash() {
//        return NUMFLASH;
//    }
//
//    public void useFlash() {
//        NUMFLASH = NUMFLASH - 1;
//    }

    /*
    @Source: Lab11
     */
    public void nothingFill(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.MOUNTAIN;
                VISITED[x][y] = false;
            }
        }
    }

    public int randomNumberRooms(long seed) {
        return RandomUtils.uniform(RANDOM, 15, 25);
    }

    //We have to put a source check
    private boolean boundsAndVISITED(Room r) {
        for (int i = r.getEquisX(); i < (r.getAncho() + r.getEquisX()); i++) {
            for (int j = r.getYgriegaY(); j < (r.getAltura() + r.getYgriegaY()); j++) {
                if (i >= WIDTH || j >= HEIGHT || VISITED[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void createRoomAlpha(int nuevoXx, int nuevoYy) {
        avatarPoint.setX(nuevoXx);
        avatarPoint.setY(nuevoYy);
    }

    public boolean siFueVisitado(int x1, int x2, int y1, int y2) {
       boolean bl =  VISITED[x1 + x2][y1 + y2];
       return bl;
    }


    public TETile[][] createRoom(TETile[][] tiles, Room r, int number, int roomNum) {

        int cero = 0;
        int uno = 1;
        boolean nuevoNumero = (number == roomNum - 1);
        boolean numeroCero = (number == 0);
        int equisPotencialComienzo = r.getEquisX();
        int ygriegaPotencialComienzo = r.getYgriegaY();

        for (int x = 0; x < r.getAncho(); x++) {
            for (int y = 0; y < r.getAltura(); y++) {
                siFueVisitado(equisPotencialComienzo, x, ygriegaPotencialComienzo, y);

                int nuevoXx = equisPotencialComienzo + x;
                int nuevoyy = ygriegaPotencialComienzo + y;
                boolean cdn = (x != cero && y != cero);
                boolean cdn2 = (x == uno && y == uno);


                if (cdn && (x != r.getAncho() - 1) && (y != r.getAltura() - 1)) {
                    tiles[nuevoXx][nuevoyy] = Tileset.GRASS;
                    if (cdn2 && numeroCero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.UNLOCKED_DOOR;
                    }
                    if (cdn2 && nuevoNumero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.AVATAR;

                        createRoomAlpha(nuevoXx, nuevoyy);
                    }
                } else {
                    tiles[nuevoXx][nuevoyy] = Tileset.TREE;
                }
            }
        }
        return tiles;
    }
    
    public void drawRooms(TETile[][] tiles, int roomNum) {

        int contadorInicial = 0;
        boolean cuartoValido = false;
        while (contadorInicial < roomNum) {
            while (!cuartoValido) {
                Room nuevoCuarto = new Room(RANDOM, WIDTH, HEIGHT, contadorInicial);
                if (boundsAndVISITED(nuevoCuarto)) {
                    cuartoValido = false;
                } else if (!boundsAndVISITED(nuevoCuarto)) {
                    createRoom(tiles, nuevoCuarto, contadorInicial, roomNum);
                    rooms.add(nuevoCuarto);
                    cuartoValido = true;
                }
            }
            contadorInicial++;
            cuartoValido = false;
        }
    }


    /**
     * This code is inspired by
     * @Source https://www.geeksforgeeks.org/different-ways-to-declare-and-initialize-2-d-array-in-java/
     */

    public boolean outOfBounds(int x, int y) {
        boolean verdad = false;
        if ((x < WIDTH) && (x >= 0 )) {
            verdad = true;
        }
        if ((y < HEIGHT) && (y >= 0)) {
            verdad = true;
        }
        return !verdad;
    }



    public void setAvatarPoint(TETile[][] finalWorldFrame, Point x) {

        int si = avatarPoint.getX();
        int siY = avatarPoint.getY();

        finalWorldFrame[si][siY] = Tileset.GRASS;
        avatarPoint = x;

        int si1 = avatarPoint.getX();
        int si2 = avatarPoint.getY();
        finalWorldFrame[si1][si2] = Tileset.AVATAR;

    }


    public void ponerOscuroTodoAlpha(TETile[][] brillanteTile, TETile[][] oscuroTile,
                                     int equis, int ygriega, int equis2, int ygriega2) {

        boolean requerimiento1 = !outOfBounds(equis + equis2, ygriega);
        boolean requerimiento2 = !outOfBounds(equis - equis2, ygriega);
        boolean requerimiento3 = !outOfBounds(equis, ygriega + ygriega2);
        boolean requerimiento4 = !outOfBounds(equis, ygriega - ygriega2);
        boolean requerimiento5 = !outOfBounds(equis + equis2, ygriega + ygriega2);
        boolean requerimiento6 = !outOfBounds(equis - equis2, ygriega - ygriega2);
        boolean requerimiento7 = !outOfBounds(equis + equis2, ygriega - ygriega2);
        boolean requerimiento8 = !outOfBounds(equis - equis2, ygriega + ygriega2);
        if (requerimiento1) {
            oscuroTile[equis + equis2][ygriega] = brillanteTile[equis + equis2][ygriega];
        }
        if (requerimiento2) {
            oscuroTile[Math.abs(equis - equis2)][ygriega] = brillanteTile[Math.abs(equis - equis2)][ygriega];
        }
        if (requerimiento3) {
            oscuroTile[equis][ygriega + ygriega2] = brillanteTile[equis][ygriega + ygriega2];
        }
        if (requerimiento4) {
            oscuroTile[equis][Math.abs(ygriega - ygriega2)] = brillanteTile[equis][Math.abs(ygriega - ygriega2)];
        }
        if (requerimiento5) {
            oscuroTile[equis + equis2][ygriega + ygriega2] = brillanteTile[equis + equis2][ygriega + ygriega2];
        }
        if (requerimiento6) {
            oscuroTile[Math.abs(equis - equis2)][Math.abs(ygriega - ygriega2)] = brillanteTile[Math.abs(equis - equis2)][Math.abs(ygriega - ygriega2)];
        }
        if (requerimiento7) {
            oscuroTile[equis + equis2][Math.abs(ygriega - ygriega2)] = brillanteTile[equis + equis2][Math.abs(ygriega - ygriega2)];
        }
        if (requerimiento8) {
            oscuroTile[Math.abs(equis - equis2)][ygriega + ygriega2] = brillanteTile[Math.abs(equis - equis2)][ygriega + ygriega2];
        }
    }


    public void makeDark(TETile[][] lightTiles, TETile[][] darkTiles) {


        for (int a = 0; a < WIDTH; a += 1) {
            for (int b = 0; b < HEIGHT; b += 1) {

                // Make sure gold disappears when avatar reaches it
                if (darkTiles[a][b] != Tileset.AVATAR) {
                    darkTiles[a][b] = Tileset.NOTHING;
                }
            }
        }


        for (int x = 1; x < WIDTH; x += 1) {
            for (int y = 1; y < HEIGHT; y += 1) {
                if (darkTiles[x][y] == Tileset.AVATAR) {
                    for (int c = 1; c < LIGHTDIST; c++) {
                        for (int d = 1; d < LIGHTDIST; d++) {
                            ponerOscuroTodoAlpha(lightTiles,darkTiles,x,y,c,d);
                        }
                    }
                }
            }
        }

    }
}















