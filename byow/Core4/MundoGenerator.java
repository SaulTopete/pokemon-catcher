package byow.Core4;

import byow.Core4.RandomUtils;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MundoGenerator {
    private int width;
    private int height;

    private final static String WEST_EAST = "westeast";
    private final static String SOUTH_NORTH = "southnorth";
    //    protected long Seed;
    private Random random;
    private final boolean[][] roomArea;
    private ArrayList<RoomCoordinates> roomsList;

    public MundoGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.roomArea = new boolean[this.width][this.height];
        this.roomsList = new ArrayList<>();
        this.random = new Random();
//        this.Seed = seed;
    }

//    public MundoGenerator(int width, int height, boolean[][] roomArea, PuntosCardinales pc) {
//        this.width = width;
//        this.height = height;
//        this.roomArea = roomArea;
//        this.random = new Random();
////        this.Seed = seed;
//        this.pc = pc;
//    }

    public void canvasFilledNothing(TETile[][] tiles) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++)
                tiles[x][y] = Tileset.WATER;
        }
    }

    public int roomNumberAletorio() {
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

    private void fillRoomTiles(RoomCoordinates room, TETile[][] tiles) {
        for (int i = room.getStartX(); i <= room.getEndPointX(); i += 1) {
            for (int j = room.getStartY(); j <= room.getEndPointY(); j += 1) {
                tiles[i][j] = Tileset.FLOOR;
                roomArea[i][j] = true;
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
                if (roomArea[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void createRooms(TETile[][] tiles) {
        int randomNumRooms = roomNumberAletorio();
        for (int i = 0; i < randomNumRooms; i++) {
            createRoom(tiles);
        }
    }

    private void createWalls(TETile[][] tiles, int startX, int startY, int endX, int endY) {
        for (int i = startY; i <= endY; i++) {
            tiles[startX][i] = Tileset.WALL;
        }

        for (int i = startX; i <= endX; i++) {
            tiles[i][startY] = Tileset.WALL;
        }
        for (int i = startX; i <= endX; i++) {
            tiles[i][endY] = Tileset.WALL;
        }
        for (int i = startY; i <= endY; i++) {
            tiles[endX][i] = Tileset.WALL;
        }
    }

    private int randomVal(int dimension) {
        return RandomUtils.uniform(random, 5, dimension - 3);
    }

    private int randomRoomSize() {
        return RandomUtils.uniform(random, 5, 12);
    }

    public void printBoard() {
        for (int j = height - 1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                if (roomArea[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

    public void drawHallway(TETile[][] tiles) {
        /*
        1. Get the data structure knowing which rooms to connect to what
        2. Create boolean for each room to see if it connected to other room
        3. Loop through the data structure for each room
        4. if the boolean is false, connect the rooms and change the tiles
        5. Change that specific boolean for the connection to true, so that
           another connection doesn't happen for those rooms
        6. Repeat steps 3-5
         */

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
                    tiles[i][rm1MidY] = Tileset.FLOOR;
                    roomArea[i][rm1MidY] = true;
                    createHallwayWalls(tiles, i, rm1MidY, WEST_EAST);
                }
            } else {
                for (int i = rmMinX; i <= rmMaxX; i++) {
                    tiles[i][rm2MidY] = Tileset.FLOOR;
                    roomArea[i][rm2MidY] = true;
                    createHallwayWalls(tiles, i, rm2MidY, WEST_EAST);
                }
            }

            if (rmMinY == rm2MidY) {
                for (int i = rmMinY; i <= rmMaxY; i++) {
                    tiles[rmMinX][i] = Tileset.FLOOR;
                    roomArea[rmMinX][i] = true;
                    createHallwayWalls(tiles, rmMinX, i, SOUTH_NORTH);
                }
            } else {
                for (int i = rmMinY; i <= rmMaxY; i++) {
                    tiles[rmMinX][i] = Tileset.FLOOR;
                    roomArea[rmMinX][i] = true;
                    createHallwayWalls(tiles, rmMinX, i, SOUTH_NORTH);
                }
            }
        }
    }

    private void createHallwayWalls(TETile[][] tiles, int currX, int currY, String direction) {
        if (Objects.equals(direction, WEST_EAST)) {
            if (tiles[currX][currY + 1] == Tileset.WATER) {
                tiles[currX][currY + 1] = Tileset.WALL;
            }
            if (tiles[currX][currY - 1] == Tileset.WATER) {
                tiles[currX][currY - 1] = Tileset.WALL;
            }
        }
        else {
            if (tiles[currX + 1][currY] == Tileset.WATER) {
                tiles[currX + 1][currY] = Tileset.WALL;
            }
            if (tiles[currX - 1][currY] == Tileset.WATER) {
                tiles[currX - 1][currY] = Tileset.WALL;
            }
        }
    }
}

