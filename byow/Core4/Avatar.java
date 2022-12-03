package byow.Core4;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Avatar extends Icon {
    private static final TETile AVATAR = Tileset.AVATAR;

    public Avatar(int dimmX, int dimmY) {
        super(dimmX, dimmY, AVATAR);
    }

    public void move(TETile[][] tiles, char letter, int x, int y) {
        if (Character.toUpperCase(letter) == 'W') {
            if (tiles[x][y + 1] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y + 1] = AVATAR;
                setPosY(y + 1);
                System.out.println("Pressed W");
            }
        }
        if (Character.toUpperCase(letter) == 'S') {
            if (tiles[x][y - 1] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y - 1] = AVATAR;
                setPosY(y - 1);
                System.out.println("Pressed S");
            }
        }
        if (Character.toUpperCase(letter) == 'A') {
            if (tiles[x - 1][y] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x - 1][y] = AVATAR;
                setPosX(x - 1);
                System.out.println("Pressed A");
            }
        }
        if (Character.toUpperCase(letter) == 'D') {
            if (tiles[x + 1][y] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x + 1][y] = AVATAR;
                setPosX(x + 1);
                System.out.println("Pressed D");
            }
        }
    }

}
