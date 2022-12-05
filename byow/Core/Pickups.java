package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;

public class Pickups extends Icon {

    public static final TETile PICKUPS = Tileset.FLOWER;

    public static final TETile LOPUNNY = new TETile('?', Color.CYAN, Color.WHITE, "trainer", ".\\images\\lopunny.png");
    public static final TETile PIPLUP = new TETile('?', Color.CYAN, Color.WHITE, "trainer", ".\\images\\piplup.png");
    public static final TETile TRUBBISH = new TETile('?', Color.CYAN, Color.WHITE, "trainer", ".\\images\\trubbish.png");
    public static final TETile SNORLAX = new TETile('?', Color.CYAN, Color.WHITE, "trainer", ".\\images\\snorlax.png");

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
