package byow.Core;


import byow.Core2.World;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.LinkedList;

public class CrearCuarto extends World {


    public CrearCuarto(int maxX, int maxY, long seed, PuntosCardinales avatarPoint) {
        super(maxX, maxY, seed, avatarPoint);
    }

    public void moveUp(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int yy = startY + 1;
        boolean requerimiento = !outOfBounds(startX, yy);
        boolean requerimiento2 = tiles[startX][yy].description().equals("floor");
        boolean requerimiento3 = tiles[startX][yy].description().equals("gold");
        if (requerimiento) {
            if (requerimiento2) {
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX][yy] = Tileset.AVATAR;
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX][yy] = Tileset.AVATAR;
                avatarPoint.setY(yy);
                AVATARMOVES.add("W");
            }
            if (requerimiento3) {
                AVATARMOVES.add("W");
            }
        }
    }

    public void moveDown(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int yy = startY - 1;
        int yy2 = startY + 1;
        boolean requerimiento = !outOfBounds(startX, yy);
        boolean requerimiento2 = tiles[startX][yy].description().equals("floor");
        boolean requerimiento3 = tiles[startX][yy2].description().equals("gold");
        if (requerimiento) {
            if (requerimiento2) {
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX][yy] = Tileset.AVATAR;
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX][yy] = Tileset.AVATAR;
                avatarPoint.setY(yy);
                AVATARMOVES.add("S");
            }
            if (requerimiento3) {
                AVATARMOVES.add("S");
            }
        }
    }

    public void moveLeft(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int xx = startX - 1;
        boolean requerimiento = !outOfBounds(xx, startY);
        boolean requerimiento2 = tiles[xx][startY].description().equals("floor");
        boolean requerimiento3 = tiles[xx][startY].description().equals("gold");
        if (requerimiento) {
            if (requerimiento2) {
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[xx][startY] = Tileset.AVATAR;
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[xx][startY] = Tileset.AVATAR;
                avatarPoint.setX(xx);
                AVATARMOVES.add("A");
            }
            if (requerimiento3) {
                AVATARMOVES.add("A");
            }
        }
    }

    public void moveRight(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {

        int xx = startX + 1;
        boolean requerimiento = !outOfBounds(xx, startY);
        boolean requerimiento2 = tiles[xx][startY].description().equals("floor");
        boolean requerimiento3 = tiles[xx][startY].description().equals("gold");
        if (requerimiento) {
            if (requerimiento2) {
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[xx][startY] = Tileset.AVATAR;
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[xx][startY] = Tileset.AVATAR;
                avatarPoint.setX(xx);
                AVATARMOVES.add("D");
            }
            if (requerimiento3) {
                AVATARMOVES.add("D");
            }
        }
    }


    public ArrayList<String> getAvatarMoves() {
        return AVATARMOVES;
    }

    public void arribaOabajo(TETile[][] tiles, TETile[][] tilesOscuros, char teclado) {
        boolean tecladoW = teclado == 'W' || teclado == 'w';
        boolean tecladoS = teclado == 'S' || teclado == 's';
        if (tecladoW) {
            moveUp(avatarPoint.getX(), avatarPoint.getY(), tiles, tilesOscuros);
        } else if (tecladoS) {
            moveDown(avatarPoint.getX(), avatarPoint.getY(), tiles, tilesOscuros);
        }
    }

    public void derechaOizquierda(TETile[][] tiles, TETile[][] tilesOscuros, char teclado) {
        boolean tecladoA = teclado == 'A' || teclado == 'a';
        boolean tecladoD = teclado == 'D' || teclado == 'd';
        if (tecladoA) {
            moveLeft(avatarPoint.getX(), avatarPoint.getY(), tiles, tilesOscuros);
        } else if (tecladoD) {
            moveRight(avatarPoint.getX(), avatarPoint.getY(), tiles, tilesOscuros);
        }
    }

    public void moveAvatar(char c, TETile[][] tiles, TETile[][] darkTiles) {

        boolean tecladoWS = (c == 'W' || c == 'w') || (c == 'S' || c == 's');
        boolean tecladoAD = (c == 'A' || c == 'a') || (c == 'D' || c == 'd');

        if (tecladoWS) {
            arribaOabajo(tiles, darkTiles, c);
            return;
        }
        if (tecladoAD) {
            derechaOizquierda(tiles, darkTiles, c);
            return;
        }
        if (c == ' ') {
            return;
        }
    }

}
