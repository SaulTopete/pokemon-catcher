package byow.Core4;

import byow.Core3.RandomUtils;
import byow.Core3.Room;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class MundoGenerator {
    protected int Width;
    protected int Height;
    //    protected long Seed;
    protected Random aleatorio;
    private boolean[][] roomArea;
    private ArrayList<RoomCoordinates> roomsList;
    protected PuntosCardinales<Integer, Integer> pc;

    //
    public MundoGenerator(int width, int height) {
        this.Width = width;
        this.Height = height;
        this.roomArea = new boolean[Width][Height];
        this.roomsList = new ArrayList<>();
        this.aleatorio = new Random();
//        this.Seed = seed;
    }

    public MundoGenerator(int width, int height, boolean[][] roomArea, PuntosCardinales pc) {
        this.Width = width;
        this.Height = height;
        this.roomArea = roomArea;
        this.aleatorio = new Random();
//        this.Seed = seed;
        this.pc = pc;
    }

    public void canvasFilledNothing(TETile[][] tiles) {
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++)
                tiles[x][y] = Tileset.MOUNTAIN;
        }
    }

    public int roomNumberAletorio() {
        return RandomUtils.uniform(aleatorio, 15, 25);
    }

    public boolean isOutofBound(int x, int y) {
        boolean verdad = false;
        if ((x < Width) && (x >= 0)) {
            verdad = true;
        }
        if ((y < Height) && (y >= 0)) {
            verdad = true;
        }
        return !verdad;
    }

    public boolean siFueVisitado(int x1, int x2, int y1, int y2) {
        boolean bl = roomArea[x1 + x2][y1 + y2];
        return bl;
    }

    private void createRoom(TETile[][] tiles) {
        int startingX = randomVal(Width);
        int startingY = randomVal(Height);
        int randomXDimm = randomRoomSize();
        int randomYDimm = randomRoomSize();
        int sizeX = randomRoomSize() + startingX;
        int sizeY = randomRoomSize() + startingY;
        boolean isValidPostion = isValidRoomPos(sizeX, sizeY);
        RoomCoordinates roomStuff = new RoomCoordinates(startingX, startingY, randomXDimm, randomYDimm);

        while (!isValidPostion) {
            roomStuff.setStartX(randomVal(Width));
            roomStuff.setStartY(randomVal(Height));
            roomStuff.setEndPointX(startingX + randomXDimm);
            roomStuff.setEndPointY(startingY + randomYDimm);
            isValidPostion = isValidRoomPos(roomStuff.getEndPointX(), roomStuff.getEndPointY());
            if (roomsList.size() > 0 && isValidPostion) {
                isValidPostion = isOverlapping(roomStuff);
            }
//            isValidPostion = isTouching(roomStuff);
        }

        for (int i = roomStuff.getStartX(); i < roomStuff.getEndPointX(); i += 1) {
            for (int j = roomStuff.getStartY(); j < roomStuff.getEndPointY(); j += 1) {
                tiles[i][j] = Tileset.FLOOR;
                roomArea[i][j] = true;
                roomsList.add(roomStuff);
//                createWalls(tiles, i, j, sizeX, sizeY );
            }
        }
    }

    private boolean isValidRoomPos(int xLength, int yLength) {
        return xLength < Width - 2 && yLength < Height - 2;
    }

    private boolean isOverlapping(RoomCoordinates futureBuiltRoom) {
        for (int x = futureBuiltRoom.getStartX(); x < futureBuiltRoom.getEndPointX(); x++) {
            for (int y = futureBuiltRoom.getStartY(); y < futureBuiltRoom.getEndPointY(); y++) {
                if (roomArea[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    //Need to edit so checking left, right, up, and down doesn't error
    private boolean isTouching(RoomCoordinates futureBuiltRoom) {
        for (int x = futureBuiltRoom.getStartX(); x < futureBuiltRoom.getEndPointX(); x++) {
            for (int y = futureBuiltRoom.getStartY(); y < futureBuiltRoom.getEndPointY(); y++) {
                if (roomArea[x][y + 3]) {
                    return false;
                }
                if (roomArea[x - 3][y]) {
                    return false;
                }
                if (roomArea[x + 3][y]) {
                    return false;
                }
                if (roomArea[x][y - 3]) {
                    return false;
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
        if (x == 0 & y == 0 & x == ancho - 1 &&  y == altura) {
            tiles[ancho + x][altura + y] = Tileset.WALL;
        }
    }

    private int randomVal(int dimmension) {
        return RandomUtils.uniform(aleatorio, 5, dimmension - 3);
    }

    private int randomRoomSize() {
        return RandomUtils.uniform(aleatorio, 4, 7);
    }

}

