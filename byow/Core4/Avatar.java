package byow.Core4;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Avatar extends Icon {
    private static final TETile avatarTile = Tileset.AVATAR;

    public Avatar(int dimmX, int dimmY) {
        super(dimmX, dimmY, avatarTile);
    }

    public void move(TETile[][] tiles, char letter) {

    }

}
