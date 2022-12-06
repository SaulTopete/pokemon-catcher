package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class WorldGeneration {
    private int width;
    private int height;

    private final static String WEST_EAST = "westeast";
    private final static String SOUTH_NORTH = "southnorth";
    private long Seed;
    private Random random;
    private boolean[][] lightArea;

    private ArrayList<RoomCoordinates> roomsList;

    private Avatar avatar;

    private Pickups pickups;

    public static final TETile FLOORS = Tileset.FLOOR;
    public static final TETile WALLS = Tileset.WALL;
    public static final TETile OUTSIDE_DEFAULT = Tileset.SAND;

    private static final TETile[] LIST_ENVI = {Avatar.OUTSIDE_1, Avatar.OUTSIDE_2};

    public static final TETile DARKNESS = Tileset.NOTHING;

    private final int ranEnvPos;

    public WorldGeneration(int width, int height, boolean[][] roomsArea, int avatarX,
                           int avatarY, int pickupPosList, ArrayList<RoomCoordinates> pickupPos,
                           int envPos) {
        this.width = width;
        this.height = height;
        this.lightArea = roomsArea;
        this.avatar = new Avatar(width, height);
        this.pickups = new Pickups(Engine.WIDTH, Engine.HEIGHT);
        setAvatarX(avatarX);
        setAvatarY(avatarY);
        pickups.setIconListPos(pickupPosList);
        pickups.setPickupPosList(pickupPos);
        ranEnvPos = envPos;
    }

    public WorldGeneration(int width, int height, int seed) {
        this.width = width;
        this.height = height;
        this.lightArea = new boolean[this.width][this.height];
        this.roomsList = new ArrayList<>();
        this.random = new Random();
        this.avatar = new Avatar(Engine.WIDTH, Engine.HEIGHT);
        this.pickups = new Pickups(Engine.WIDTH, Engine.HEIGHT);
        this.Seed = seed;
        ranEnvPos = RandomUtils.uniform(random, 0, LIST_ENVI.length);
    }

    public void canvasFilledNothing(TETile[][] tiles) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++)
                tiles[x][y] = LIST_ENVI[ranEnvPos];
        }
    }

    public int randomRoomNumber() {
        return RandomUtils.uniform(random, 15, 20);
    }

    private void createRoom(TETile[][] tiles) {
        int startPointX = randomVal(width);
        int startPointY = randomVal(height);
        int randomXDimm = randomRoomSize();
        int randomYDimm = randomRoomSize();
        RoomCoordinates roomStuff = new RoomCoordinates(startPointX, startPointY, randomXDimm, randomYDimm);
        boolean isValidPosition = isValidRoomPos(roomStuff);

        while (!isValidPosition) {
            roomStuff.setStartX(randomVal(width));
            roomStuff.setStartY(randomVal(height));
            randomXDimm = randomRoomSize();
            randomYDimm = randomRoomSize();
            roomStuff.setEndPointX(roomStuff.getStartX() + randomXDimm);
            roomStuff.setEndPointY(roomStuff.getStartY() + randomYDimm);

            isValidPosition = isValidRoomPos(roomStuff);
        }
        fillRoomTiles(roomStuff, tiles);
        createWalls(tiles, roomStuff.getStartX(), roomStuff.getStartY(), roomStuff.getEndPointX(), roomStuff.getEndPointY());
    }

    public void addAvatar(TETile[][] tiles) {
        avatar.getRandomPos(tiles);
    }

    public int getAvatarX() {
        return avatar.getPosX();
    }

    public int getAvatarY() {
        return avatar.getPosY();
    }

    public void setAvatarX(int val) {
        avatar.setPosX(val);
    }

    public void setAvatarY(int val) {
        avatar.setPosY(val);
    }

    public ArrayList<RoomCoordinates> getPickupsList() {
        return pickups.getPickupPosList();
    }

    public int getRanEnvListPos() {
        return ranEnvPos;
    }

    public int getRanPickupListPos() {
        return pickups.getIconListPos();
    }

    public void createPickups(TETile[][] tiles) {
        pickups.getRandomSpots(tiles);
    }

    public boolean moveAvatar(TETile[][] lightTiles, TETile[][] darkTiles, char letter) {
       return avatar.move(lightTiles, darkTiles, letter, avatar.getPosX(), avatar.getPosY(), pickups.getIcon());
    }

    private void fillRoomTiles(RoomCoordinates room, TETile[][] tiles) {
        for (int i = room.getStartX(); i <= room.getEndPointX(); i += 1) {
            for (int j = room.getStartY(); j <= room.getEndPointY(); j += 1) {
                tiles[i][j] = FLOORS;
                lightArea[i][j] = true;
            }
        }
        roomsList.add(room);
    }

    private boolean isValidRoomPos(RoomCoordinates futureRoomBuilt) {
        if (futureRoomBuilt.getEndPointX() < width - 3 && futureRoomBuilt.getEndPointY() < height - 3) {
            return !isOverlapping(futureRoomBuilt);
        }
        return false;
    }

    private boolean isOverlapping(RoomCoordinates futureBuiltRoom) {
        for (int x = futureBuiltRoom.getStartX(); x <= futureBuiltRoom.getEndPointX(); x++) {
            for (int y = futureBuiltRoom.getStartY(); y <= futureBuiltRoom.getEndPointY(); y++) {
                if (lightArea[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void createRooms(TETile[][] tiles) {
        int randomNumRooms = randomRoomNumber();
        for (int i = 0; i < randomNumRooms; i++) {
            createRoom(tiles);
        }
    }

    private void createWalls(TETile[][] tiles, int startX, int startY, int endX, int endY) {
        for (int i = startY; i <= endY; i++) {
            tiles[startX][i] = WALLS;
        }
        for (int i = startX; i <= endX; i++) {
            tiles[i][startY] = WALLS;
        }
        for (int i = startX; i <= endX; i++) {
            tiles[i][endY] = WALLS;
        }
        for (int i = startY; i <= endY; i++) {
            tiles[endX][i] = WALLS;
        }
    }

    private int randomVal(int dimension) {
        return RandomUtils.uniform(random, 5, dimension - 3);
    }

    private int randomRoomSize() {
        return RandomUtils.uniform(random, 5, 12);
    }

    public void printBoard() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (lightArea[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

    public void drawHallway(TETile[][] tiles) {
        for (int pos = 0; pos < roomsList.size() - 1; pos++) {
            int rm1MidX = roomsList.get(pos).getMidDimmX();
            int rm1MidY = roomsList.get(pos).getMidDimmY();
            int rm2MidX = roomsList.get(pos + 1).getMidDimmX();
            int rm2MidY = roomsList.get(pos + 1).getMidDimmY();
            int rmMinX, rmMinY, rmMaxX, rmMaxY;

            rmMinX = Math.min(rm1MidX, rm2MidX);
            rmMinY = Math.min(rm1MidY, rm2MidY);
            rmMaxX = Math.max(rm1MidX, rm2MidX);
            rmMaxY = Math.max(rm1MidY, rm2MidY);

            if (rmMinX == rm2MidX) {
                for (int i = rmMinX; i <= rmMaxX; i++) {
                    tiles[i][rm1MidY] = FLOORS;
                    lightArea[i][rm1MidY] = true;
                    createHallwayWalls(tiles, i, rm1MidY, WEST_EAST);
                }
            } else {
                for (int i = rmMinX; i <= rmMaxX; i++) {
                    tiles[i][rm2MidY] = FLOORS;
                    lightArea[i][rm2MidY] = true;
                    createHallwayWalls(tiles, i, rm2MidY, WEST_EAST);
                }
            }

            if (rmMinY == rm2MidY) {
                for (int i = rmMinY; i <= rmMaxY; i++) {
                    tiles[rmMinX][i] = FLOORS;
                    lightArea[rmMinX][i] = true;
                    createHallwayWalls(tiles, rmMinX, i, SOUTH_NORTH);
                }
            } else {
                for (int i = rmMinY; i <= rmMaxY; i++) {
                    tiles[rmMinX][i] = FLOORS;
                    lightArea[rmMinX][i] = true;
                    createHallwayWalls(tiles, rmMinX, i, SOUTH_NORTH);
                }
            }
        }
    }

    private void createHallwayWalls(TETile[][] tiles, int currX, int currY, String direction) {
        if (Objects.equals(direction, WEST_EAST)) {
            if (tiles[currX][currY + 1] == LIST_ENVI[ranEnvPos]) {
                tiles[currX][currY + 1] = WALLS;
            }
            if (tiles[currX][currY - 1] == LIST_ENVI[ranEnvPos]) {
                tiles[currX][currY - 1] = WALLS;
            }
        } else {
            if (tiles[currX + 1][currY] == LIST_ENVI[ranEnvPos]) {
                tiles[currX + 1][currY] = WALLS;
            }
            if (tiles[currX - 1][currY] == LIST_ENVI[ranEnvPos]) {
                tiles[currX - 1][currY] = WALLS;
            }
        }
    }

    public void darkenArea(TETile[][] lightTiles, TETile[][] darkTiles) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (lightTiles[x][y] == Avatar.AVATAR) {
                    darkTiles[x][y] = Avatar.AVATAR;
                }
                else {
                    darkTiles[x][y] = DARKNESS;
                }
            }
        }
    }

    public boolean[][] getRoomArea() {
        return lightArea;
    }
}

