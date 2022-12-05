package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class Icon {

    private final Random random;

    private int posX, posY;

    private int DIM_X = 0;
    private int DIM_Y = 0;

    private TETile icon;

    public Icon(int dimmX, int dimmY, TETile icon) {
        this.DIM_X = dimmX;
        this.DIM_Y = dimmY;
        random = new Random();
        posX = RandomUtils.uniform(random, 0, this.DIM_X - 3);
        posY = RandomUtils.uniform(random, 0, this.DIM_Y - 3);
        this.icon = icon;
    }

    public boolean getRandomPos(TETile[][] tiles) {
        while (tiles[posX][posY] != Tileset.FLOOR){
            this.posX = RandomUtils.uniform(random, 0, this.DIM_X - 3);
            this.posY = RandomUtils.uniform(random, 0, this.DIM_Y - 3);
        }
        setPosition(tiles, this.posX, this.posY, this.icon);
        return true;
    }

    private void setPosition(TETile[][] tiles, int posX, int posY, TETile icon) {
        tiles[posX][posY] = icon;
        setPosX(posX);
        setPosY(posY);
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

    public void setIcon(TETile icon) {
        this.icon = icon;
    }
}
