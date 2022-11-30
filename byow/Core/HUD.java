package byow.Core;

import byow.Core2.World;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

public class HUD {

    private String nombre;
    private int ANCHO;
    private int ALTURA;


    public HUD(int ANCHO, int ALTURA, String nombre) {
        this.ANCHO = ANCHO;
        this.ALTURA = ALTURA;
        this.nombre = nombre;
    }

    public void updateHUD(World newWorld, int xCoord, int yCoord, TETile[][] tiles) {
        String myTile = "";
        if ((xCoord >= 0 && xCoord < ANCHO) && (yCoord >= 0 && yCoord < ALTURA)) {
            myTile = tiles[xCoord][yCoord].description();
        } else {
            myTile = "";
        }

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(10, ALTURA - 1, "Name: Scuba " + nombre);
        StdDraw.text(ANCHO / 2, ALTURA - 1, "Location: " + myTile);
        //StdDraw.text(width - 18, height - 1, "Flashlights Remaining: " + newWorld.getFlash());
        StdDraw.show();
    }
}
