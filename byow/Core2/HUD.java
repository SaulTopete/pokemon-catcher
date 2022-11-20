package byow.Core2;

import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class HUD {
    private String name;
    private int width;
    private int height;


    public HUD(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void updateHUD(World newWorld, int xCoord, int yCoord, TETile[][] tiles) {
        String myTile = "";
        if ((xCoord >= 0 && xCoord < width) && (yCoord >= 0 && yCoord < height)) {
            myTile = tiles[xCoord][yCoord].description();
        } else {
            myTile = "";
        }

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(10, height - 1, "Name: Scuba " + name);
        StdDraw.text(width / 2, height - 1, "Location: " + myTile);
        //StdDraw.text(width - 18, height - 1, "Flashlights Remaining: " + newWorld.getFlash());
        StdDraw.show();
    }
}
