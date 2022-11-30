package byow.Core2;

import byow.Core.PuntosCardinales;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

//public class AvatarControl2  extends World{
//
//
//    public AvatarControl2(int maxX, int maxY, long seed, PuntosCardinales avatarPoint) {
//        super(maxX, maxY, seed, avatarPoint);
//    }
//
//    public void ponerOscuroTodoAlpha(TETile[][] brillanteTile, TETile[][] oscuroTile,
//                                     int equis, int ygriega, int equis2, int ygriega2) {
//
//        boolean requerimiento1 = !outOfBounds(equis + equis2, ygriega);
//        boolean requerimiento2 = !outOfBounds(equis - equis2, ygriega);
//        boolean requerimiento3 = !outOfBounds(equis, ygriega + ygriega2);
//        boolean requerimiento4 = !outOfBounds(equis, ygriega - ygriega2);
//        boolean requerimiento5 = !outOfBounds(equis + equis2, ygriega + ygriega2);
//        boolean requerimiento6 = !outOfBounds(equis - equis2, ygriega - ygriega2);
//        boolean requerimiento7 = !outOfBounds(equis + equis2, ygriega - ygriega2);
//        boolean requerimiento8 = !outOfBounds(equis - equis2, ygriega + ygriega2);
//        if (requerimiento1) {
//            oscuroTile[equis + equis2][ygriega] = brillanteTile[equis + equis2][ygriega];
//        }
//        if (requerimiento2) {
//            oscuroTile[equis - equis2][ygriega] = brillanteTile[equis - equis2][ygriega];
//        }
//        if (requerimiento3) {
//            oscuroTile[equis][ygriega + ygriega2] = brillanteTile[equis][ygriega + ygriega2];
//        }
//        if (requerimiento4) {
//            oscuroTile[equis][ygriega - ygriega2] = brillanteTile[equis][ygriega - ygriega2];
//        }
//        if (requerimiento5) {
//            oscuroTile[equis + equis2][ygriega + ygriega2] = brillanteTile[equis + equis2][ygriega + ygriega2];
//        }
//        if (requerimiento6) {
//            oscuroTile[equis - equis2][ygriega - ygriega2] = brillanteTile[equis - equis2][ygriega - ygriega2];
//        }
//        if (requerimiento7) {
//            oscuroTile[equis + equis2][ygriega - ygriega2] = brillanteTile[equis + equis2][ygriega - ygriega2];
//        }
//        if (requerimiento8) {
//            oscuroTile[equis - equis2][ygriega + ygriega2] = brillanteTile[equis - equis2][ygriega + ygriega2];
//        }
//    }
//
//    public void makeDark (TETile[][]lightTiles, TETile[][]darkTiles){
//        for (int a = 0; a < WIDTH; a += 1) {
//            for (int b = 0; b < HEIGHT; b += 1) {
//
//                // Make sure gold disappears when avatar reaches it
//                if (darkTiles[a][b] != Tileset.AVATAR) {
//                    darkTiles[a][b] = Tileset.NOTHING;
//                }
//            }
//
//
//            for (int x = 0; x < WIDTH; x += 1) {
//                for (int y = 0; y < HEIGHT; y += 1) {
//                    // Make sure gold disappears when avatar reaches it
//                /*if (lightTiles[x][y] == Tileset.GOLD) {
//                    darkTiles[x][y] = Tileset.GOLD;
//                }*/
//                    if (darkTiles[x][y] == Tileset.AVATAR) {
//                        for (int c = 0; c < LIGHTDIST; c++) {
//                            for (int d = 0; d < LIGHTDIST; d++) {
//                                ponerOscuroTodoAlpha(lightTiles,darkTiles,x,y,c,d);
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}
