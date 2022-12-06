package byow.Core;

import edu.princeton.cs.algs4.Out;

import java.util.ArrayList;
/*
    Order of saving:
        - Entire Boolean board
        - Specific avatar pos
        - Icon pos in list
        - Where pickups are on map
        - What environment is made of
 */

public class SaveFile {

    private Out out;
    public static final String FILENAME = ".\\save\\save.txt";

    public SaveFile() {
        out = new Out(FILENAME);
    }


    public void save(WorldGeneration worldGeneration, int avatarX, int avatarY, ArrayList<RoomCoordinates> list, int ranEnv, int ranPickup) {
        saveTiles(worldGeneration.getRoomArea());
        saveAvatarPos(avatarX, avatarY);
        savePickupsIcon(ranPickup);
        savePickupsPos(list);
        saveEnvironmentIcon(ranEnv);

//        this.wg = worldGeneration;
//        this.aX = avatarX;
//        this.aY = avatarY;
//        this.list = list;
//        this.ranEnvPos = ranEnv;
//        this.ranPickupPosns = ranPickup;
    }

    private void saveTiles(boolean[][] lightTiles) {
        for (int i = 0; i < Engine.HEIGHT; i++) {
            for (int j = 0; j < Engine.WIDTH; j++) {
                if (lightTiles[j][i]) {
                    out.print(1);
                } else {
                    out.print(0);
                }
                out.print(" ");
            }
        }
    }


    private void saveAvatarPos(int avatarX, int avatarY) {
        out.println();
        out.print(avatarX);
        out.print(" ");
        out.print(avatarY);
    }

    private void savePickupsIcon(int pos) {
        out.println();
        out.print(pos);
    }

    private void savePickupsPos(ArrayList<RoomCoordinates> list) {
        out.println();
        for (int i = 0; i < Pickups.NUM_OF_PICKUP_TO_END; i++) {
            out.print(list.get(i).getStartX());
            out.print(" ");
            out.print(list.get(i).getStartY());
            out.print(" ");
        }
    }

    private void saveEnvironmentIcon(int pos) {
        out.println();
        out.print(pos);
    }
}
