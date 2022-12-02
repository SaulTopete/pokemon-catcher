package byow.Core4;

import byow.Core4.RandomUtils;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class MundoGenerator {
    protected int width;
    protected int height;
    //    protected long Seed;
    protected Random random;
    private final boolean[][] roomArea;
    private ArrayList<RoomCoordinates> roomsList;
    protected PuntosCardinales<Integer, Integer> pc;

    private HashMap graph;

    //
    public MundoGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.roomArea = new boolean[this.width][this.height];
        this.roomsList = new ArrayList<>();
        this.random = new Random();
        graph = new HashMap(roomsList);
//        this.Seed = seed;
    }

    public MundoGenerator(int width, int height, boolean[][] roomArea, PuntosCardinales pc) {
        this.width = width;
        this.height = height;
        this.roomArea = roomArea;
        this.random = new Random();
//        this.Seed = seed;
        this.pc = pc;
    }

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
        boolean tooClose = false;

        while (!isValidPosition) {
            roomStuff.setStartX(randomVal(width));
            roomStuff.setStartY(randomVal(height));
            randomXDimm = randomRoomSize();
            randomYDimm = randomRoomSize();
            roomStuff.setEndPointX(roomStuff.getStartX() + randomXDimm);
            roomStuff.setEndPointY(roomStuff.getStartY() + randomYDimm);

            isValidPosition = isValidRoomPos(roomStuff);
//            tooClose = isTouching(roomStuff);
        }
        fillRoomTiles(roomStuff, tiles);
        createWalls(tiles, roomStuff.getStartX(), roomStuff.getStartY(), roomStuff.getEndPointX(), roomStuff.getEndPointY());
        graph.connectRooms(roomsList);
        graph.printHashMap(roomsList);
    }

    private void fillRoomTiles(RoomCoordinates room, TETile[][] tiles) {
        for (int i = room.getStartX(); i <= room.getEndPointX(); i += 1) {
            for (int j = room.getStartY(); j <= room.getEndPointY(); j += 1) {
                tiles[i][j] = Tileset.FLOOR;
                roomArea[i][j] = true;
                roomsList.add(room);
            }
        }
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

    //Need to edit so checking left, right, up, and down doesn't error
    private boolean isTouching(RoomCoordinates futureBuiltRoom) {
        for (int x = futureBuiltRoom.getStartX(); x < futureBuiltRoom.getEndPointX(); x++) {
            for (int y = futureBuiltRoom.getStartY(); y < futureBuiltRoom.getEndPointY(); y++) {
                if (y + 3 < height) {
                    if (roomArea[x][y + 3]) {
                        return false;
                    }
                }
                if (x - 3 > 0) {
                    if (roomArea[x - 3][y]) {
                        return false;
                    }
                }
                if (x + 3 < width) {
                    if (roomArea[x + 3][y]) {
                        return false;
                    }
                }
                if (y - 3 > 0) {
                    if (roomArea[x][y - 3]) {
                        return false;
                    }
                }
            }
        }
        return true;
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

    public void drawHallway() {

    }
}

