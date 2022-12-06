package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pickups extends Icon {


    private Random random = new Random();

    private int ran;
    public static final TETile DEFAULT = Tileset.TREE;
    public static final TETile LOPUNNY = new TETile('?', Color.CYAN, Color.WHITE, "lopunny", ".\\images\\lopunny.png");
    public static final TETile PIPLUP = new TETile('?', Color.CYAN, Color.WHITE, "piplup", ".\\images\\piplup.png");
    public static final TETile TRUBBISH = new TETile('?', Color.CYAN, Color.WHITE, "trubbish", ".\\images\\trubbish.png");
    public static final TETile SNORLAX = new TETile('?', Color.CYAN, Color.WHITE, "snorlax", ".\\images\\snorlax.png");

    public static final TETile[] LIST_PICKUPS = {LOPUNNY, PIPLUP, TRUBBISH, SNORLAX};

    private int posNum = RandomUtils.uniform(random, 0, LIST_PICKUPS.length);

    public final TETile PICKUP_ICON = LIST_PICKUPS[posNum];

    private ArrayList<RoomCoordinates> pickupsPositions = new ArrayList<>();

    public static final int NUM_OF_PICKUP_TO_END = 4;

    public Pickups(int dimmX, int dimmY) {
        super(dimmX, dimmY, DEFAULT);
        super.setIcon(PICKUP_ICON);
    }

    public void getRandomSpots(TETile[][] tiles) {
        RoomCoordinates rc;
        int count = 0;
        boolean set;
        while (count < NUM_OF_PICKUP_TO_END) {
            set = super.getRandomPos(tiles);
            rc = new RoomCoordinates(super.getPosX(), super.getPosY());
            if (set) {
                count += 1;
                pickupsPositions.add(rc);
            }
        }
    }

    public ArrayList<RoomCoordinates> getPickupPosList() {
        return pickupsPositions;
    }

    public void setPickupPosList(ArrayList<RoomCoordinates> list) {
        pickupsPositions = list;
    }

    public TETile getIcon() {
        return PICKUP_ICON;
    }

    public int getIconListPos() {
        return posNum;
    }

    public void setIconListPos(int value) {
        posNum = value;
    }
}
