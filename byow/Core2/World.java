package byow.Core2;

import byow.Core.Cuartos;
import byow.Core.PuntosCardinales;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class World  {
    public int WIDTH;
    public int HEIGHT;
    public long SEED;
    public boolean[][] VISITED;
    public Random RANDOM;
    public ArrayList<Room> rooms = new ArrayList<>();
    public PuntosCardinales<Integer, Integer> avatarPoint;
    public static final ArrayList<String> AVATARMOVES = new ArrayList<>();
    public static int LIGHTDIST = 3;
    //can't put any rooms or hallways in the top row since this where the hud is


    public World(int maxX, int maxY, long seed, PuntosCardinales avatarPoint) {
        this.avatarPoint = avatarPoint;
        this.RANDOM = new Random(seed);
        this.WIDTH = maxX;
        this.HEIGHT = maxY;
        this.SEED = seed;
        this.VISITED = new boolean[maxX][maxY];
    }


    public void nothingFill(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.WATER;
                VISITED[x][y] = false;
            }
        }
    }

    public int randomNumberRooms(long seed) {
        int roomNum = RandomUtils.uniform(RANDOM, 10, 20);
        return roomNum;
    }

    //We have to think how to rewrite this method
    private boolean boundsAndVISITED(Cuartos r) {
        int potentialX = r.getEquisX();
        int potentialY = r.getYgriegaY();
        int roomWidth = r.getAncho();
        int roomHeight = r.getAltura();
        for (int i = potentialX; i < (roomWidth + potentialX); i++) {
            for (int j = potentialY; j < (roomHeight + potentialY); j++) {
                if (i >= WIDTH || j >= HEIGHT || VISITED[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public TETile[][] createRoom(TETile[][] tiles, Cuartos r, int number, int roomNum) {

        int cero = 0;
        int uno = 1;
        boolean nuevoNumero = (number == roomNum - 1);
        boolean numeroCero = (number == 0);
        int anchoDelCuarto = r.getAncho();
        int alturaDelCuarto = r.getAltura();
        int equisPotencialComienzo = r.getEquisX();
        int ygriegaPotencialComienzo = r.getYgriegaY();
        for (int x = 0; x < anchoDelCuarto; x++) {
            for (int y = 0; y < alturaDelCuarto; y++) {
                VISITED[equisPotencialComienzo + x][ygriegaPotencialComienzo + y] = true;
                int nuevoXx = equisPotencialComienzo + x;
                int nuevoyy = ygriegaPotencialComienzo + y;
                int wcondicional1 = anchoDelCuarto - 1;
                int hcondicional2 = alturaDelCuarto - 1;
                boolean cdn = (x != cero && y != cero);
                boolean cdn2 = (x == uno && y == uno);
                if (cdn && (x != wcondicional1) && (y != hcondicional2)) {
                    tiles[nuevoXx][nuevoyy] = Tileset.FLOOR;
                    if (cdn2 && numeroCero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.SAND;
                    }
                    if (cdn2 && nuevoNumero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.AVATAR;
                        //Let's use the new file for the avatar methods for this part
                        //We need to set the avatar points.
                        avatarPoint.setX(nuevoXx);
                        avatarPoint.setY(nuevoyy);
                    }
                } else {
                    tiles[nuevoXx][nuevoyy] = Tileset.WALL;
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
                Cuartos nuevoCuarto = new Cuartos(WIDTH, HEIGHT, contadorInicial, RANDOM);
                if (boundsAndVISITED(nuevoCuarto)) {
                    cuartoValido = false;
                } else if (!boundsAndVISITED(nuevoCuarto)) {
                    createRoom(tiles, nuevoCuarto, contadorInicial, roomNum);
//                    rooms.add(nuevoCuarto);
                    cuartoValido = true;
                }
            }
            contadorInicial++;
            cuartoValido = false;
        }
    }

//    private ArrayList grafiquito(int roomNum) {
//        int[][] grafico = new int[roomNum][roomNum];
//        grafico = Graph.connectAll(roomNum, rooms, grafico);
//        ArrayList losBordes = Graph.primMST(roomNum, grafico);
//        return losBordes;
//    }
//
//    //We can Also change the for loop with a while, but I think with this for loop
//    //it looks more different
//    public void drawHallways(TETile[][] tiles, int roomNum) {
//
//        ArrayList losBordes2 = grafiquito(roomNum);
//        for (Object losBorde : losBordes2) {
//            PuntosCardinales misPuntos = (PuntosCardinales) losBorde;
//            int identificacionCuarto1 = (int) misPuntos.getX();
//            int identificacionCuarto2 = (int) misPuntos.getY();
//            Cuartos cuartoUno = rooms.get(identificacionCuarto1);
//            Cuartos cuartoDos = rooms.get(identificacionCuarto2);
//            //Here we should call the method roomConnect that uses writeEast,writeWest,
//            //writeNorth,writeSouth, this will connect cuartoUno y CuartoDos
//            losCuartosSeConectan(tiles, cuartoUno, cuartoDos);
////            contador ++;
//        }
//
//    }

    public void limpiadorAlpha(int x, int y, TETile[][] rt) {
        int nuevox = x + 1;
        int nuevox2 = x - 1;
        int nuevoy = y + 1;
        int nuevoy2 = y - 1;
        boolean uno = outOfBounds(nuevox, y);
        boolean dos = outOfBounds(nuevox2, y);
        boolean tres = outOfBounds(x, nuevoy);
        boolean cuatro = outOfBounds(x, nuevoy2);
        if (!uno && rt[nuevox][y] == Tileset.WATER) {
            rt[nuevox][y] = Tileset.WALL;
        }
        if (!dos && rt[nuevox2][y] == Tileset.WATER) {
            rt[nuevox2][y] = Tileset.WALL;
        }
        if (!tres && rt[x][nuevoy] == Tileset.WATER) {
            rt[x][nuevoy] = Tileset.WALL;
        }
        if (!cuatro && rt[x][nuevoy2] == Tileset.WATER) {
            rt[x][nuevoy2] = Tileset.WALL;
        }
    }

    public void limpiadorBetha(int x, int y, TETile[][] rt) {
        int nuevox = x + 1;
        int nuevox2 = x - 1;
        int nuevoy = y + 1;
        int nuevoy2 = y - 1;
        boolean uno = outOfBounds(nuevox2, nuevoy);
        boolean dos = outOfBounds(nuevox, nuevoy);
        boolean tres = outOfBounds(nuevox, nuevoy2);
        boolean cuatro = outOfBounds(nuevox2, nuevoy2);
        if (!uno && rt[nuevox2][nuevoy] == Tileset.WATER) {
            rt[nuevox2][nuevoy] = Tileset.WALL;
        }
        if (!dos && rt[nuevox][nuevoy] == Tileset.WATER) {
            rt[nuevox][nuevoy] = Tileset.WALL;
        }
        if (!tres && rt[nuevox][nuevoy2] == Tileset.WATER) {
            rt[nuevox][nuevoy2] = Tileset.WALL;
        }
        if (!cuatro && rt[nuevox2][nuevoy2] == Tileset.WATER) {
            rt[nuevox2][nuevoy2] = Tileset.WALL;
        }
    }

    public void cleaner(TETile[][] randomTiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (randomTiles[x][y] == Tileset.FLOOR) {
                    limpiadorAlpha(x, y, randomTiles);
                    limpiadorBetha(x, y, randomTiles);
                }
            }
        }
    }

    public boolean outOfBounds(int x, int y) {

//        boolean retornoSies = false;
//        boolean xFueraDeLimites = x > WIDTH && x <= 0;
//        boolean yFueraDeLimites = y > HEIGHT && y <= 0;
//        if (xFueraDeLimites || yFueraDeLimites) {
//            retornoSies = true;
//        }
//        return retornoSies;
        boolean intBoundX = (x < WIDTH) && (x >= 0);
        boolean intBoundY = (y < HEIGHT) && (y >= 0);
        return !intBoundX || !intBoundY;
    }

    //This method needs to be reviewed and see what we can change
    public void setAvatarPoint(TETile[][] finalWorldFrame, PuntosCardinales x) {

        finalWorldFrame[avatarPoint.getX()][avatarPoint.getY()] = Tileset.FLOOR;
        avatarPoint = x;
        finalWorldFrame[avatarPoint.getX()][avatarPoint.getY()] = Tileset.AVATAR;

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
                    oscuroTile[equis - equis2][ygriega] = brillanteTile[equis - equis2][ygriega];
                }
                if (requerimiento3) {
                    oscuroTile[equis][ygriega + ygriega2] = brillanteTile[equis][ygriega + ygriega2];
                }
                if (requerimiento4) {
                    oscuroTile[equis][ygriega - ygriega2] = brillanteTile[equis][ygriega - ygriega2];
                }
                if (requerimiento5) {
                    oscuroTile[equis + equis2][ygriega + ygriega2] = brillanteTile[equis + equis2][ygriega + ygriega2];
                }
                if (requerimiento6) {
                    oscuroTile[equis - equis2][ygriega - ygriega2] = brillanteTile[equis - equis2][ygriega - ygriega2];
                }
                if (requerimiento7) {
                    oscuroTile[equis + equis2][ygriega - ygriega2] = brillanteTile[equis + equis2][ygriega - ygriega2];
                }
                if (requerimiento8) {
                    oscuroTile[equis - equis2][ygriega + ygriega2] = brillanteTile[equis - equis2][ygriega + ygriega2];
                }
            }

        public void makeDark (TETile[][]lightTiles, TETile[][]darkTiles){
//            for (int a = 0; a < WIDTH; a += 1) {
//                for (int b = 0; b < HEIGHT; b += 1) {
//
//                    // Make sure gold disappears when avatar reaches it
//                    if (darkTiles[a][b] != Tileset.AVATAR) {
//                        darkTiles[a][b] = Tileset.NOTHING;
//                    }
//                }
//
//
//                for (int x = 1; x < WIDTH; x += 1) {
//                    for (int y = 1; y < HEIGHT; y += 1) {
//                        // Make sure gold disappears when avatar reaches it
//                /*if (lightTiles[x][y] == Tileset.GOLD) {
//                    darkTiles[x][y] = Tileset.GOLD;
//                }*/
//                        if (darkTiles[x][y] == Tileset.AVATAR) {
//                            for (int c = 1; c < LIGHTDIST; c++) {
//                                for (int d = 1; d < LIGHTDIST; d++) {
//                                    ponerOscuroTodoAlpha(lightTiles,darkTiles,x,y,c,d);
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
            for (int a = 0; a < WIDTH; a += 1) {
                for (int b = 0; b < HEIGHT; b += 1) {

                    // Make sure gold disappears when avatar reaches it
                    if (darkTiles[a][b] != Tileset.AVATAR) {
                        darkTiles[a][b] = Tileset.NOTHING;
                    }
                }


                for (int x = 0; x < WIDTH; x += 1) {
                    for (int y = 0; y < HEIGHT; y += 1) {
                        // Make sure gold disappears when avatar reaches it
                /*if (lightTiles[x][y] == Tileset.GOLD) {
                    darkTiles[x][y] = Tileset.GOLD;
                }*/
                        if (darkTiles[x][y] == Tileset.AVATAR) {
                            for (int c = 0; c < LIGHTDIST; c++) {
                                for (int d = 0; d < LIGHTDIST; d++) {
                                    if (!outOfBounds(x + c, y)) {
                                        darkTiles[x + c][y] = lightTiles[x + c][y];
                                    }
                                    if (!outOfBounds(x - c, y)) {
                                        darkTiles[x - c][y] = lightTiles[x - c][y];
                                    }
                                    if (!outOfBounds(x, y + d)) {
                                        darkTiles[x][y + d] = lightTiles[x][y + d];
                                    }
                                    if (!outOfBounds(x, y - d)) {
                                        darkTiles[x][Math.abs(y - d)] = lightTiles[x][Math.abs(y - d)];
                                    }
                                    if (!outOfBounds(x + c, y + d)) {
                                        darkTiles[x + c][y + d] = lightTiles[x + c][y + d];
                                    }
                                    if (!outOfBounds(x - c, y - d)) {
                                        darkTiles[x - c][y - d] = lightTiles[x - c][y - d];
                                    }
                                    if (!outOfBounds(x + c, y - d)) {
                                        darkTiles[x + c][y - d] = lightTiles[x + c][y - d];
                                    }
                                    if (!outOfBounds(x - c, y + d)) {
                                        darkTiles[x - c][y + d] = lightTiles[x - c][y + d];
                                    }
                                }
                            }
                        }
                    }
                }

            }
//        }
        }

    public static void posicionNorte(int equis1, int ygriega1, int ygriega2,
                                     TETile[][] tilesRandom) {

        int i = ygriega1;
        while (i < ygriega2) {
            boolean chi = tilesRandom[equis1 - 1][i] == Tileset.WATER;
            boolean chi2 = tilesRandom[equis1 + 1][i] == Tileset.WATER;
            tilesRandom[equis1][i] = Tileset.FLOOR;

            if (chi) {
                tilesRandom[equis1 - 1][i] = Tileset.WALL;
            }
            if (chi2) {
                tilesRandom[equis1 + 1][i] = Tileset.WALL;
            }
            i += 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void posicionSur(int equis1, int ygriega1, int ygriega2, TETile[][] tilesRandom) {
        equis1 = equis1 + 1;
        int i = ygriega1;
        while (i >= ygriega2) {
            boolean chi = (tilesRandom[equis1 - 1][i]) == Tileset.WATER;
            boolean chi2 = tilesRandom[equis1 + 1][i] == Tileset.WATER;
            tilesRandom[equis1][i] = Tileset.FLOOR;

            if (chi) {
                tilesRandom[equis1 - 1][i] = Tileset.WALL;
            }
            if (chi2) {
                tilesRandom[equis1 + 1][i] = Tileset.WALL;
            }
            i -= 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void posicionOeste(int equis1, int equis2, int ygriega1, TETile[][] tilesRandom) {
        int i = equis1;
        while (i >= equis2) {
            boolean chi = tilesRandom[i][ygriega1 + 1] == Tileset.WATER;
            boolean chi2 = tilesRandom[i][ygriega1 - 1] == Tileset.WATER;
            tilesRandom[i][ygriega1] = Tileset.FLOOR;

            if (chi) {
                tilesRandom[i][ygriega1 + 1] = Tileset.WALL;
            }
            if (chi2) {
                tilesRandom[i][ygriega1 - 1] = Tileset.WALL;
            }
            i -= 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void posicionEste(int equis1, int equis2, int ygriega1, TETile[][] tilesRandom) {
        ygriega1 = ygriega1 - 1;
        int i = equis1;
        while (i <= equis2) {
            boolean chi = tilesRandom[i][ygriega1 + 1] == Tileset.WATER;
            boolean chi2 = tilesRandom[i][ygriega1 - 1] == Tileset.WATER;
            tilesRandom[i][ygriega1] = Tileset.FLOOR;

            if (chi) {
                tilesRandom[i][ygriega1 + 1] = Tileset.WALL;
            }
            if (chi2) {
                tilesRandom[i][ygriega1 - 1] = Tileset.WALL;
            }
            i += 1;
        }
    }

    private static void losCuartosConectanAlpha(TETile[][] rtiles, int alph1, int alph2,
                                                int alph3, int alph4) {
        boolean si = alph2 < alph4;

        if (si) {posicionNorte(alph1, alph2, alph4, rtiles);}
        else {posicionSur(alph1, alph2, alph4, rtiles);}
    }

    private static void losCuartosConectanBetha(TETile[][] rtiles, int alph1, int alph2,
                                                int alph3, int alph4) {
        boolean si = alph1 < alph3;
        if (si) {posicionEste(alph1, alph3, alph4, rtiles);}
        else {posicionOeste(alph1,alph3, alph4, rtiles);}
    }

    private static void losCuartosConectanTetha(TETile[][] rtiles, int alph1, int alph2,
                                                int alph3, int alph4) {
        boolean si1 = alph1 < alph3;
        boolean si2 = alph1 > alph2;
        boolean si3 = alph2 < alph4;
        boolean si4 = alph2 > alph4;

        if (si1 && si3) {posicionNorte(alph1, alph2, alph4, rtiles);
            posicionEste(alph1, alph3, alph4, rtiles);}
        else if (si2 && si3) {posicionNorte(alph1, alph2, alph4, rtiles);
            posicionOeste(alph1,alph3, alph4, rtiles);}
        else if (si2 && si4) {posicionSur(alph1, alph2, alph4, rtiles);
            posicionOeste(alph1,alph3, alph4, rtiles);}
        else {posicionSur(alph1, alph2, alph4, rtiles);
            posicionOeste(alph1,alph3, alph4, rtiles);}
    }

    public static void losCuartosSeConectan(TETile[][] rtiles, Cuartos cuarto1, Cuartos cuarto2) {
        int cuarto1Mediox = cuarto1.getMiddleX();
        int cuerato2Mediox =cuarto2.getMiddleX();

        int cuarto1Medioy = cuarto1.getMiddleY();
        int cuarto2Medioy = cuarto2.getMiddleY();
        boolean comprobar = (cuarto1Mediox == cuerato2Mediox);
        boolean comprobar2 =cuarto1Medioy == cuarto2Medioy;
        if(comprobar) {
            losCuartosConectanAlpha(rtiles, cuarto1Mediox, cuarto1Medioy,
                    cuerato2Mediox, cuarto2Medioy);
        } else if (comprobar2) {
            losCuartosConectanBetha(rtiles, cuarto1Mediox, cuarto1Medioy,
                    cuerato2Mediox, cuarto2Medioy);
        } else {
            losCuartosConectanTetha(rtiles, cuarto1Mediox, cuarto1Medioy,
                    cuerato2Mediox, cuarto2Medioy);
        }
    }
    }
















