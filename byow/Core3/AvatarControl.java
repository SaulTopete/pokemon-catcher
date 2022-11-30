package byow.Core3;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Objects;

public class AvatarControl extends World {

    public AvatarControl(int maxX, int maxY, long seed, Point avatarPoint) {
        super(maxX, maxY, seed, avatarPoint);
    }

    public void upDownRightLeft(TETile[][] tiles, TETile[][] darkTiles,
                                int startX, int startY, int yy, int xx,
                                String teclado) {
        if (Objects.equals(teclado, "W")) {
            darkTiles[startX][startY] = Tileset.NOTHING;
            darkTiles[startX][yy] = Tileset.AVATAR;
            tiles[startX][startY] = Tileset.GRASS;
            tiles[startX][yy] = Tileset.AVATAR;
            avatarPoint.setY(yy);
            AVATARMOVES.add("W");
        }
        if (Objects.equals(teclado, "S")) {
            darkTiles[startX][startY] = Tileset.NOTHING;
            darkTiles[startX][yy] = Tileset.AVATAR;
            tiles[startX][startY] = Tileset.GRASS;
            tiles[startX][yy] = Tileset.AVATAR;
            avatarPoint.setY(yy);
            AVATARMOVES.add("S");
        }
        if (Objects.equals(teclado, "A")) {
            tiles[startX][startY] = Tileset.GRASS;
            tiles[xx][startY] = Tileset.AVATAR;
            darkTiles[startX][startY] = Tileset.NOTHING;
            darkTiles[xx][startY] = Tileset.AVATAR;
            avatarPoint.setX(xx);
            AVATARMOVES.add("A");
        }
        if (Objects.equals(teclado, "D")) {
            tiles[startX][startY] = Tileset.GRASS;
            tiles[startX + 1][startY] = Tileset.AVATAR;
            darkTiles[startX][startY] = Tileset.NOTHING;
            darkTiles[startX + 1][startY] = Tileset.AVATAR;
            avatarPoint.setX(startX + 1);
            AVATARMOVES.add("D");
        }
    }

    public void moveUpAlpha(TETile[][] tiles, TETile[][] darkTiles,
                            int startX, int startY, int yy){
        darkTiles[startX][startY] = Tileset.NOTHING;
        darkTiles[startX][yy] = Tileset.AVATAR;
        tiles[startX][startY] = Tileset.GRASS;
        tiles[startX][yy] = Tileset.AVATAR;
        avatarPoint.setY(yy);
        AVATARMOVES.add("W");
    }

    public void moveUp(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int xx = 0;
        int yy = startY + 1;
        boolean requerimiento = !outOfBounds(startX, yy);
        boolean requerimiento2 = tiles[startX][yy].description().equals("grass");
        boolean requerimiento3 = tiles[startX][yy].description().equals("unlocked door");
        if (requerimiento) {
            if (requerimiento2) {
//                darkTiles[startX][startY] = Tileset.NOTHING;
//                darkTiles[startX][yy] = Tileset.AVATAR;
//                tiles[startX][startY] = Tileset.GRASS;
//                tiles[startX][yy] = Tileset.AVATAR;
                moveUpAlpha(tiles, darkTiles, startX, startY, yy);

            }
            if (requerimiento3) {
                AVATARMOVES.add("W");
            }
        }
    }

    public void moveDownAlpha(TETile[][] tiles, TETile[][] darkTiles,
                              int startX, int startY, int yy) {
        darkTiles[startX][startY] = Tileset.NOTHING;
        darkTiles[startX][yy] = Tileset.AVATAR;
        tiles[startX][startY] = Tileset.GRASS;
        tiles[startX][yy] = Tileset.AVATAR;
        avatarPoint.setY(yy);
        AVATARMOVES.add("S");
    }

    public void moveDown(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int xx = 0;
        int yy = startY - 1;
        int yy2 = startY + 1;
        boolean requerimiento = !outOfBounds(startX, yy);
        boolean requerimiento2 = tiles[startX][yy].description().equals("grass");
        boolean requerimiento3 = tiles[startX][yy2].description().equals("unlocked door");
        if (requerimiento) {
            if (requerimiento2) {
                moveDownAlpha(tiles, darkTiles, startX, startY, yy);
            }
            if (requerimiento3) {
                AVATARMOVES.add("S");
            }
        }
    }

    public void moveLeftAlpha(TETile[][] tiles, TETile[][] darkTiles,
                              int startX, int startY, int xx) {
        tiles[startX][startY] = Tileset.GRASS;
        tiles[xx][startY] = Tileset.AVATAR;
        darkTiles[startX][startY] = Tileset.NOTHING;
        darkTiles[xx][startY] = Tileset.AVATAR;
        avatarPoint.setX(xx);
        AVATARMOVES.add("A");
    }

    public void moveLeft(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int yy = 0;
        int xx = startX - 1;
        boolean requerimiento = !outOfBounds(xx, startY);
        boolean requerimiento2 = tiles[xx][startY].description().equals("grass");
        boolean requerimiento3 = tiles[xx][startY].description().equals("unlocked door");
        if (requerimiento) {
            if (requerimiento2) {
                moveLeftAlpha(tiles, darkTiles, startX, startY, xx);

            }
            if (requerimiento3) {
                AVATARMOVES.add("A");
            }
        }
    }

    public void moveRightAlpha(TETile[][] tiles, TETile[][] darkTiles,
                               int startX, int startY) {
        tiles[startX][startY] = Tileset.GRASS;
        tiles[startX + 1][startY] = Tileset.AVATAR;
        darkTiles[startX][startY] = Tileset.NOTHING;
        darkTiles[startX + 1][startY] = Tileset.AVATAR;
        avatarPoint.setX(startX + 1);
        AVATARMOVES.add("D");
    }

    public void moveRight(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {
        int xx = 0;
        int yy = 0;
        if (!outOfBounds(startX + 1, startY)) {


            if (tiles[startX + 1][startY].description().equals("grass")) {
                moveRightAlpha(tiles, darkTiles, startX, startY);


            }

            if (tiles[startX + 1][startY].description().equals("unlocked door")) {
                //end game

                AVATARMOVES.add("D");


            }
        }

        //we will want to return current position?
    }


    public LinkedList<String> getAvatarMoves() {
        return AVATARMOVES;
    }

    public void moveAvatar(char c, TETile[][] tiles, TETile[][] darkTiles) {
        boolean chi = c == 'W' || c == 'w';
        boolean chi2 = c == 'A' || c == 'a';
        boolean chi3 = c == 'D' || c == 'd';
        boolean chi4 = c == 'S' || c == 's';

        if (chi) {
            moveUp(avatarPoint.getX(),
                    avatarPoint.getY(), tiles, darkTiles);   //up

            return;
        }
        if (chi2) { //left
            moveLeft(avatarPoint.getX(),
                    avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (chi3) { //right
            moveRight(avatarPoint.getX(),
                    avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (chi4) { //down
            moveDown(avatarPoint.getX(),
                    avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (c == ' ') {
            return;
        }

    }

    public static void northSouthEastWest(TETile[][] randomTiles, int x1, int y1,
                                          int x2, int y2, int i) {
        //North
        boolean chi = randomTiles[x1 - 1][i] == Tileset.MOUNTAIN;
        boolean chi2 = randomTiles[x1 + 1][i] == Tileset.MOUNTAIN;
        //South
        boolean chi3 = (randomTiles[x1 - 1][i]) == Tileset.MOUNTAIN;
        boolean chi4 = randomTiles[x1 + 1][i] == Tileset.MOUNTAIN;
        //West
        boolean chi5 = randomTiles[i][y2 + 1] == Tileset.MOUNTAIN;
        boolean chi6 = randomTiles[i][y2 - 1] == Tileset.MOUNTAIN;
        //East
        boolean chi7 = randomTiles[i][y2 + 1] == Tileset.MOUNTAIN;
        boolean chi8 = randomTiles[i][y2 - 1] == Tileset.MOUNTAIN;

        //N
        if (chi) {
            randomTiles[(x1 - 1)][i] = Tileset.TREE;
        }
        if (chi2) {
            randomTiles[x1 + 1][i] = Tileset.TREE;
        }
        //S
        if (chi3) {
            randomTiles[x1 - 1][i] = Tileset.TREE;
        }
        if (chi4) {
            randomTiles[x1 + 1][i] = Tileset.TREE;
        }
        //W
        if (chi5) {
            randomTiles[i][y2 + 1] = Tileset.TREE;
        }
        if (chi6) {
            randomTiles[i][y2 - 1] = Tileset.TREE;
        }
        //E
        if (chi7) {
            randomTiles[i][y2 + 1] = Tileset.TREE;
        }
        if (chi8) {
            randomTiles[i][y2 - 1] = Tileset.TREE;
        }

    }

    public static boolean writeNorthAlpha(TETile[][] tiles, int i, int x1) {
        boolean shi = tiles[x1 - 1][i] == Tileset.MOUNTAIN;
        return shi;
    }

    public static boolean writeNorthBetha(TETile[][] tiles, int i, int x1) {
        boolean shi2 = tiles[x1 + 1][i] == Tileset.MOUNTAIN;
        return shi2;
    }

    private static void writeNorth(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {

            randomTiles[x1][i] = Tileset.GRASS;
            boolean chi = writeNorthAlpha(randomTiles, i, x1);
            boolean chi2 = writeNorthBetha(randomTiles, i, x1);

            if (chi) {
                randomTiles[x1 - 1][i] = Tileset.TREE;
            }
            if (chi2) {
                randomTiles[x1 + 1][i] = Tileset.TREE;
            }

        }

    }

    public static boolean writeSouthAlpha(TETile[][] tiles, int i, int x1) {
        boolean shi = (tiles[x1 - 1][i]) == Tileset.MOUNTAIN;
        return shi;
    }

    public static boolean writeSouthBetha(TETile[][] tiles, int i, int x1) {
        boolean shi2 = tiles[x1 + 1][i] == Tileset.MOUNTAIN;
        return shi2;
    }

    private static void writeSouth(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        x1 = x1 + 1;
        for (int i = y1; i >= y2; i--) {

            randomTiles[x1][i] = Tileset.GRASS;
            boolean chi = writeSouthAlpha(randomTiles, i, x1);
            boolean chi2 = writeSouthBetha(randomTiles, i,x1);

            if (chi) {
                randomTiles[x1 - 1][i] = Tileset.TREE;
            }
            if (chi2) {
                randomTiles[x1 + 1][i] = Tileset.TREE;
            }

        }
    }


    public static boolean writeWestAlpha(TETile[][] tiles, int i, int y2) {
        boolean chi = tiles[i][y2 + 1] == Tileset.MOUNTAIN;
        return chi;
    }
    public static boolean writeWestBetha(TETile[][] tiles, int i, int y2) {
        boolean chi2 = tiles[i][y2 - 1] == Tileset.MOUNTAIN;
        return chi2;
    }

    private static void writeWest(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        for (int i = x1; i >= x2; i--) {

            randomTiles[i][y2] = Tileset.GRASS;

            boolean shi = writeWestAlpha(randomTiles,i, y2);
            boolean shi2 = writeWestBetha(randomTiles,i,y2);

            if (shi) {
                randomTiles[i][y2 + 1] = Tileset.TREE;
            }
            if (shi2) {
                randomTiles[i][y2 - 1] = Tileset.TREE;
            }


        }

    }

    public static boolean writeEastAlpha(TETile[][] tiles, int i, int y2) {
        boolean shi = tiles[i][y2 + 1] == Tileset.MOUNTAIN;
        return shi;
    }

    public static boolean writeEastBetha(TETile[][] tiles, int i, int y2) {
        boolean shi2 = tiles[i][y2 - 1] == Tileset.MOUNTAIN;
        return shi2;
    }

    private static void writeEast(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        y2 = y2 - 1;
        for (int i = x1; i <= x2; i++) {
            randomTiles[i][y2] = Tileset.GRASS;
            boolean chi = writeEastAlpha(randomTiles, i, y2);
            boolean chi2 = writeEastBetha(randomTiles, i, y2);

            if (chi) {
                randomTiles[i][y2 + 1] = Tileset.TREE;
            }
            if (chi2) {
                randomTiles[i][y2 - 1] = Tileset.TREE;
            }

        }

    }

    private static boolean losCuartosConectanAlpha(int oMiddleX, int rMiddleX) {

        boolean si = (oMiddleX == rMiddleX);
        return si;
    }

    private static boolean losCuartosConectanBetha(int oMiddleY, int rMiddleY) {
        boolean si2 = (oMiddleY < rMiddleY);
        return si2;
    }

    private static boolean losCuartosConectanTetha(int oMiddleY, int rMiddleY) {

        boolean si3 = (oMiddleY == rMiddleY);
        return si3;
    }

    private static boolean losCuartosConectanOmega(int oMiddleX, int rMiddleX){
       boolean si4 = oMiddleX < rMiddleX;
       return si4;
    }

    private static void roomConnect(TETile[][] randomTiles, Room room1, Room room2) {
        int oMiddleX = room1.getMiddleX();
        int oMiddleY = room1.getMiddleY();

        int rMiddleX = room2.getMiddleX();
        int rMiddleY = room2.getMiddleY();
        boolean b1 = losCuartosConectanAlpha(oMiddleX, rMiddleX);
        boolean b2 = losCuartosConectanBetha(oMiddleY, rMiddleY);
        boolean b3 = losCuartosConectanTetha(oMiddleY, rMiddleY);
        boolean b4 = losCuartosConectanOmega(oMiddleX, rMiddleX);
        boolean b5 = oMiddleX < rMiddleX && oMiddleY < rMiddleY;
        boolean b6 = oMiddleX > rMiddleX && oMiddleY < rMiddleY;
        boolean b7 = oMiddleX > rMiddleX && oMiddleY > rMiddleY;
        if (b1) {

            if (b2) {
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else {
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            }
        } else if (b3) {
            if (b4) {
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else {
                writeWest(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                // original is to the right, going west
            }

        } else {
            if (oMiddleX < rMiddleX && oMiddleY < rMiddleY) {
                //NE
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            } else if (oMiddleX > rMiddleX && oMiddleY < rMiddleY) {
                //NW
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeWest(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else if (oMiddleX > rMiddleX && oMiddleY > rMiddleY) {
                //SW
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeWest(randomTiles, oMiddleX, rMiddleY, rMiddleX, rMiddleY);

            } else {
                //SE
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            }


        }
    }

    public void limpiadorAlpha(int x, int y, TETile[][] rt) {
        int nuevox = x + 1;
        int nuevox2 = x - 1;
        int nuevoy = y + 1;
        int nuevoy2 = y - 1;
        boolean uno = outOfBounds(nuevox, y);
        boolean dos = outOfBounds(nuevox2, y);
        boolean tres = outOfBounds(x, nuevoy);
        boolean cuatro = outOfBounds(x, nuevoy2);
        if (!uno && rt[nuevox][y] == Tileset.MOUNTAIN) {
            rt[nuevox][y] = Tileset.TREE;
        }
        if (!dos && rt[nuevox2][y] == Tileset.MOUNTAIN) {
            rt[nuevox2][y] = Tileset.TREE;
        }
        if (!tres && rt[x][nuevoy] == Tileset.MOUNTAIN) {
            rt[x][nuevoy] = Tileset.TREE;
        }
        if (!cuatro && rt[x][nuevoy2] == Tileset.MOUNTAIN) {
            rt[x][nuevoy2] = Tileset.TREE;
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
        if (!uno && rt[nuevox2][nuevoy] == Tileset.MOUNTAIN) {
            rt[nuevox2][nuevoy] = Tileset.TREE;
        }
        if (!dos && rt[nuevox][nuevoy] == Tileset.MOUNTAIN) {
            rt[nuevox][nuevoy] = Tileset.TREE;
        }
        if (!tres && rt[nuevox][nuevoy2] == Tileset.MOUNTAIN) {
            rt[nuevox][nuevoy2] = Tileset.TREE;
        }
        if (!cuatro && rt[nuevox2][nuevoy2] == Tileset.MOUNTAIN) {
            rt[nuevox2][nuevoy2] = Tileset.TREE;
        }
    }

    /**
     * This was inspired by
     * @Source: https://www.freecodecamp.org/news/how-to-make-your-own-procedural-dungeon-map-generator-using-the-random-walk-algorithm-e0085c8aa9a/
     */

//    public void cleaner(TETile[][] randomTiles) {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
////                if (randomTiles[i][j] == Tileset.GRASS) {
////
////                }
//                boolean c1 = randomTiles[i][j] == Tileset.GRASS;
//
//                if (c1) {
//                    boolean c2 = !outOfBounds(i + 1, j) && randomTiles[i + 1][j] == Tileset.MOUNTAIN;
//                    boolean c3 = !outOfBounds(i - 1, j) && randomTiles[i - 1][j] == Tileset.MOUNTAIN;
//                    boolean c4 = !outOfBounds(i, j + 1) && randomTiles[i][j + 1] == Tileset.MOUNTAIN;
//                    boolean c5 = !outOfBounds(i, j - 1) && randomTiles[i][j - 1] == Tileset.MOUNTAIN;
//                    boolean c6 = !outOfBounds(i - 1, j + 1) && randomTiles[i - 1][j + 1] == Tileset.MOUNTAIN;
//                    boolean c7 = !outOfBounds(i + 1, j + 1) && randomTiles[i + 1][j + 1] == Tileset.MOUNTAIN;
//                    boolean c8 = !outOfBounds(i + 1, j - 1) && randomTiles[i + 1][j - 1] == Tileset.MOUNTAIN;
//                    boolean c9 = !outOfBounds(i - 1, j - 1) && randomTiles[i - 1][j - 1] == Tileset.MOUNTAIN;
//                    if (c2) {
//                        randomTiles[i + 1][j] = Tileset.TREE;
//
//                    }
//                    if (c3) {
//                        randomTiles[Math.abs(i - 1)][j] = Tileset.TREE;
//
//                    }
//                    if (c4) {
//                        randomTiles[i][j + 1] = Tileset.TREE;
//
//                    }
//                    if (c5) {
//                        randomTiles[i][Math.abs(j - 1)] = Tileset.TREE;
//
//                    }
//
//                    //NEW
//
//                    if (c6) {
//                        randomTiles[Math.abs(i - 1)][j + 1] = Tileset.TREE;
//
//                    }
//
//                    if (c7) {
//                        randomTiles[i + 1][j + 1] = Tileset.TREE;
//
//                    }
//
//                    if (c8) {
//                        randomTiles[i + 1][Math.abs(j - 1)] = Tileset.TREE;
//
//                    }
//
//                    if (c9) {
//                        randomTiles[Math.abs(i - 1)][Math.abs(j - 1)] = Tileset.TREE;
//
//                    }
//                }
//            }
//
//        }
//    }

    private LinkedList grafiquito(int roomNum) {
        int[][] grafico = new int[roomNum][roomNum];
        grafico = Graph.connectAll(roomNum, rooms, grafico);
        LinkedList losBordes = Graph.primMST(roomNum, grafico);
        return losBordes;
    }

    public void drawHallways(TETile[][] tiles, int roomNum) {

        LinkedList losBordes2 = grafiquito(roomNum);
        for (Object losBorde : losBordes2) {
            Point misPuntos = (Point) losBorde;
            int identificacionCuarto1 = (int) misPuntos.getX();
            int identificacionCuarto2 = (int) misPuntos.getY();
            Room cuartoUno = rooms.get(identificacionCuarto1);
            Room cuartoDos = rooms.get(identificacionCuarto2);
            roomConnect(tiles, cuartoUno, cuartoDos);
        }
    }
}
