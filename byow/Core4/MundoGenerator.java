package byow.Core4;

import byow.Core3.RandomUtils;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class MundoGenerator {
    protected static int Width;
    protected static int Height;
    //    protected long Seed;
    protected Random aleatorio;
    boolean[][] roomArea;
    protected PuntosCardinales<Integer, Integer> pc;

    //
    public MundoGenerator(int width, int height) {
        this.Width = width;
        this.Height = height;
        this.aleatorio = new Random();
//        this.Seed = seed;
        this.roomArea[width][height] = false;
    }

    public MundoGenerator(int width, int height, PuntosCardinales pc) {
        this.Width = width;
        this.Height = height;
        this.aleatorio = new Random();
//        this.Seed = seed;
        this.pc = pc;
        this.roomArea[width][height] = false;
    }

    public static void canvasFilledNothing(TETile[][] tiles) {
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++)
                tiles[x][y] = Tileset.MOUNTAIN;
        }
    }

    public int roomNumberAletorio(long seed) {
        return RandomUtils.uniform(aleatorio, 15, 25);
    }

    public boolean isOutofBound(int x, int y) {
        boolean verdad = false;
        if ((x < Width) && (x >= 0)) {
            verdad = true;
        }
        if ((y < Height) && (y >= 0)) {
            verdad = true;
        }
        return !verdad;
    }

    public boolean siFueVisitado(int x1, int x2, int y1, int y2) {
        boolean bl = roomArea[x1 + x2][y1 + y2];
        return bl;
    }

    public TETile[][] creacionRoom(TETile[][] tiles) {
        int startingX = randomVal(this.Width);
        int startingY = randomVal(this.Height);
        for (int x = startingX; x < randomRoomSize(); x += 1) {
            for (int y = startingY; y < randomRoomSize(); y += 1) {
//                roomArea[x][y] = true;
                tiles[x][y] = Tileset.FLOWER;
            }
        }
        return tiles;
    }

    private int randomVal(int dimmension) {
        return RandomUtils.uniform(aleatorio, 5, dimmension - 3);
    }

    private int randomRoomSize() {
        return RandomUtils.uniform(aleatorio, 4, 7);
    }

}

