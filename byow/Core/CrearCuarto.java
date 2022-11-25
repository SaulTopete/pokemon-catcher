package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class CrearCuarto {
    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void createUp(int x1, int y1, int y2, TETile[][] tilesRandom) {
        int i = y1;
        while (i < y2) {
            tilesRandom[x1][i] = Tileset.FLOOR;

            if (tilesRandom[x1 - 1][i] == Tileset.WATER) {
                tilesRandom[x1 - 1][i] = Tileset.WALL;
            }
            if (tilesRandom[x1 + 1][i] == Tileset.WATER) {
                tilesRandom[x1 + 1][i] = Tileset.WALL;
            }
            i += 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void createDown(int x1, int y1, int y2, TETile[][] tilesRandom) {
        y2 = y2 - 1;
        int i = x1;
        while (i >= y2) {
            tilesRandom[x1][i] = Tileset.FLOOR;

            if ((tilesRandom[x1 - 1][i]) == Tileset.WATER) {
                tilesRandom[x1 - 1][i] = Tileset.WALL;
            }
            if (tilesRandom[x1 + 1][i] == Tileset.WATER) {
                tilesRandom[x1 + 1][i] = Tileset.WALL;
            }
            i += 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void writeLeft(int x1, int x2, int y2, TETile[][] tilesRandom) {
        int i = x1;
        while (i >= x2) {
            tilesRandom[i][y2] = Tileset.FLOOR;

            if (tilesRandom[i][y2 + 1] == Tileset.WATER) {
                tilesRandom[i][y2 + 1] = Tileset.WALL;
            }
            if (tilesRandom[i][y2 - 1] == Tileset.WATER) {
                tilesRandom[i][y2 - 1] = Tileset.WALL;
            }
            i -= 1;
        }
    }

    //CHANGE VARIABLE NAMES, DID NOT CHANGE TO KEEP CONSISTENT
    // might need to change the way this method looks, looks to similar to your friends'
    public static void writeRight(int x1, int x2, int y2, TETile[][] tilesRandom) {
        y2 = y2 - 1;
        int i = x1;
        while (i <= x2) {
            tilesRandom[i][y2] = Tileset.FLOOR;

            if (tilesRandom[i][y2 + 1] == Tileset.WATER) {
                tilesRandom[i][y2 + 1] = Tileset.WALL;
            }
            if (tilesRandom[i][y2 - 1] == Tileset.WATER) {
                tilesRandom[i][y2 - 1] = Tileset.WALL;
            }
            i -= 1;
        }
    }
}
