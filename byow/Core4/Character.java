package byow.Core4;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class Character {

    private Random random;

    private int posX, posY;

    private final int dimmX;
    private final int dimmY;

    public Character(int dimmX, int dimmY, TETile[][] tiles) {
        this.dimmX = dimmX;
        this.dimmY = dimmY;
        random = new Random();
        posX = RandomUtils.uniform(random, 0, this.dimmX - 3);
        posY = RandomUtils.uniform(random, 0, this.dimmY - 3);
    }

    public void getRandomPos(TETile[][] tiles) {
        while (tiles[posX][posY] != Tileset.FLOOR){
            this.posX = RandomUtils.uniform(random, 0, this.dimmX - 3);
            this.posY = RandomUtils.uniform(random, 0, this.dimmY - 3);
        }
        setPosition(tiles, this.posX, this.posY);
    }

    private void setPosition(TETile[][] tiles, int posX, int posY) {
        tiles[posX][posY] = Tileset.AVATAR;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
