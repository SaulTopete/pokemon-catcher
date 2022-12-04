package byow.Core4;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Pickups extends Icon {

    public static final TETile PICKUPS = Tileset.FLOWER;

    public static final int NUM_OF_PICKUP_TO_END = 4;

    public Pickups(int dimmX, int dimmY) {
        super(dimmX, dimmY, PICKUPS);

    }

    public void getRandomSpots(TETile[][] tiles) {
        int count = 0;
        boolean set;
        while (count < NUM_OF_PICKUP_TO_END) {
            set = super.getRandomPos(tiles);
            if (set) {
                count += 1;
            }
        }
    }
}
