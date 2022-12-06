package byow.Core;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class LoadFile {
    In in;
    private boolean[][] roomsListLoad;

    public LoadFile() {
        in = new In(SaveFile.FILENAME);
        roomsListLoad = new boolean[Engine.WIDTH][Engine.HEIGHT];
    }

    public boolean[][] loadRoomsAreaList() {
        for (int i = 0; i < Engine.HEIGHT; i++) {
            for (int j = 0; j < Engine.WIDTH; j++) {
                if (in.readInt() == 0) {
                    roomsListLoad[j][i] = false;
                }
                else {
                    roomsListLoad[j][i] = true;
                }
            }
        }
        return roomsListLoad;
    }

    public int loadAvatarX() {
        return in.readInt();
    }

    public int loadAvatarY() {
        return in.readInt();
    }

    public int loadPickupPosList() {
        return in.readInt();
    }

    public ArrayList<RoomCoordinates> loadPickupsPos() {
        ArrayList<RoomCoordinates> list = new ArrayList<>();
        RoomCoordinates rc = new RoomCoordinates();
        for (int i = 0; i < Pickups.NUM_OF_PICKUP_TO_END; i++) {
            rc.setStartX(in.readInt());
            rc.setStartY(in.readInt());
            list.add(rc);
        }
        return list;
    }

    public int loadEnvironmentPos() {
        return in.readInt();
    }
}
