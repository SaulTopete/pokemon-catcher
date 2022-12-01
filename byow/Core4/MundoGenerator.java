package byow.Core4;

import byow.Core3.RandomUtils;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class MundoGenerator {
    protected int width;
    protected int height;
    //    protected long Seed;
    protected Random random;
    private boolean[][] roomArea;
    private ArrayList<RoomCoordinates> roomsList;
    protected PuntosCardinales<Integer, Integer> pc;

    //
    public MundoGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.roomArea = new boolean[this.width][this.height];
        this.roomsList = new ArrayList<>();
        this.random = new Random();
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
                tiles[x][y] = Tileset.MOUNTAIN;
        }
    }

    public int roomNumberAletorio() {
        return RandomUtils.uniform(random, 15, 25);
    }

    public boolean isOutofBound(int x, int y) {
        boolean verdad = false;
        if ((x < width) && (x >= 0)) {
            verdad = true;
        }
        if ((y < height) && (y >= 0)) {
            verdad = true;
        }
        return !verdad;
    }

    public boolean siFueVisitado(int x1, int x2, int y1, int y2) {
        boolean bl = roomArea[x1 + x2][y1 + y2];
        return bl;
    }

    private void createRoom(TETile[][] tiles) {
        int startingX = randomVal(width);
        int startingY = randomVal(height);
        int randomXDimm = randomRoomSize();
        int randomYDimm = randomRoomSize();
        int sizeX = randomXDimm + startingX;
        int sizeY = randomYDimm + startingY;
        boolean isValidPosition = isValidRoomPos(sizeX, sizeY);
        boolean overlapped = true;
        boolean isTouching = false;
        RoomCoordinates roomStuff = new RoomCoordinates(startingX, startingY, randomXDimm, randomYDimm);

        while (!isValidPosition && overlapped) {
            roomStuff.setStartX(randomVal(width));
            roomStuff.setStartY(randomVal(height));
            roomStuff.setEndPointX(roomStuff.getStartX() + randomXDimm);
            roomStuff.setEndPointY(roomStuff.getStartY() + randomYDimm);
            isValidPosition = isValidRoomPos(roomStuff.getEndPointX(), roomStuff.getEndPointY());
            if (isValidPosition && roomsList.size() > 0) {
                overlapped = isOverlapping(roomStuff);
            }
        }

        for (int i = roomStuff.getStartX(); i < roomStuff.getEndPointX(); i += 1) {
            for (int j = roomStuff.getStartY(); j < roomStuff.getEndPointY(); j += 1) {
                tiles[i][j] = Tileset.FLOWER;
                roomArea[i][j] = true;
                roomsList.add(roomStuff);
//                createWalls(tiles, i, j, sizeX, sizeY );
            }
        }
    }

    private boolean isValidRoomPos(int xLength, int yLength) {
        return xLength < width - 2 && yLength < height - 2;
    }

    private boolean isOverlapping(RoomCoordinates futureBuiltRoom) {
        for (int x = futureBuiltRoom.getStartX(); x < futureBuiltRoom.getEndPointX(); x++) {
            for (int y = futureBuiltRoom.getStartY(); y < futureBuiltRoom.getEndPointY(); y++) {
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
        for (int i = 0; i < 20; i++) {
            createRoom(tiles);
        }
    }


    private void createWalls(TETile[][] tiles, int x, int y, int ancho, int altura) {
        if (x == 0 & y == 0 & x == ancho - 1 && y == altura) {
            tiles[ancho + x][altura + y] = Tileset.WALL;
        }
    }

    private int randomVal(int dimmension) {
        return RandomUtils.uniform(random, 5, dimmension - 3);
    }

    private int randomRoomSize() {
        return RandomUtils.uniform(random, 4, 7);
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
}

