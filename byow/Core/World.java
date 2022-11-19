package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.Random;

public class World {
    private int WIDTH;
    private int HEIGHT;
    private long SEED;
    private boolean[][] VISITED;
    private Random RANDOM;
    private ArrayList<Room> rooms = new ArrayList<>();
    private Point<Integer, Integer> avatarPoint;
    private static final ArrayList<String> AVATARMOVES = new ArrayList<>();
    private int NUMFLASH = 3;
    private static final int LIGHTDIST = 3;
    //can't put any rooms or hallways in the top row since this where the hud is


    public World(int maxX, int maxY, long seed, Point avatarPoint) {
        this.avatarPoint = avatarPoint;
        this.RANDOM = new Random(seed);
        this.WIDTH = maxX;
        this.HEIGHT = maxY;
        this.SEED = seed;
        this.VISITED = new boolean[maxX][maxY];
    }


    public int getFlash() {
        return NUMFLASH;
    }

    public void useFlash() {
        NUMFLASH = NUMFLASH - 1;
    }

    public void nothingFill(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.WATER;
                VISITED[x][y] = false;
            }
        }
    }

    public int randomNumberRooms(long seed) {
        int roomNum = RandomUtils.uniform(RANDOM, 10, 20);
        return roomNum;
    }

    private boolean boundsAndVISITED(Room r) {
        int potentialX = r.getX();
        int potentialY = r.getY();
        int roomWidth = r.getWidth();
        int roomHeight = r.getHeight();
        for (int i = potentialX; i < (roomWidth + potentialX); i++) {
            for (int j = potentialY; j < (roomHeight + potentialY); j++) {
                if (i >= WIDTH || j >= HEIGHT || VISITED[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public TETile[][] createRoom(TETile[][] tiles, Room r, int number, int roomNum) {
        int startX = r.getX();
        int startY = r.getY();
        int width = r.getWidth();
        int height = r.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                VISITED[startX + i][startY + j] = true;
                if ((i != 0) && (j != 0) && (j != height - 1) && (i != width - 1)) {
                    tiles[startX + i][startY + j] = Tileset.FLOOR;
                    if (i == 1 && j == 1 && number == 0) {
                        tiles[startX + i][startY + j] = Tileset.SAND;
                    }

                    if (i == 1 && j == 1 && number == roomNum - 1) {
                        tiles[startX + i][startY + j] = Tileset.AVATAR;
                        //update point class position
                        avatarPoint.setX(startX + i);
                        avatarPoint.setY(startY + j);

                    }

                } else {
                    tiles[startX + i][startY + j] = Tileset.WALL;
                }
            }
        }
        return tiles;
    }

    public void drawRooms(TETile[][] tiles, int roomNum) {
        boolean validRoom = false;
        for (int i = 0; i < roomNum; i++) {
            while (!validRoom) {
                Room newRoom = new Room(RANDOM, WIDTH, HEIGHT, i);
                if (!boundsAndVISITED(newRoom)) {
                    createRoom(tiles, newRoom, i, roomNum);
                    rooms.add(newRoom);
                    validRoom = true;
                }
            }
            validRoom = false;

        }
    }

    public void drawHallways(TETile[][] tiles, int roomNum) {
        int[][] graph = new int[roomNum][roomNum];
        graph = Graph.connectAll(roomNum, rooms, graph);
        ArrayList myEdges = Graph.primMST(roomNum, graph);

        for (int i = 0; i < myEdges.size(); i++) {
            Point myPoint = (Point) myEdges.get(i);
            int room1ID = (int) myPoint.getX();
            int room2ID = (int) myPoint.getY();
            Room room1 = rooms.get(room1ID);
            Room room2 = rooms.get(room2ID);
            roomConnect(tiles, room1, room2);


        }
    }


    private static void writeNorth(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {

            randomTiles[x1][i] = Tileset.FLOOR;

            if (randomTiles[x1 - 1][i] == Tileset.WATER) {
                randomTiles[x1 - 1][i] = Tileset.WALL;
            }
            if (randomTiles[x1 + 1][i] == Tileset.WATER) {
                randomTiles[x1 + 1][i] = Tileset.WALL;
            }

        }

    }

    private static void writeSouth(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        x1 = x1 + 1;
        for (int i = y1; i >= y2; i--) {

            randomTiles[x1][i] = Tileset.FLOOR;

            if (randomTiles[x1 - 1][i] == Tileset.WATER) {
                randomTiles[x1 - 1][i] = Tileset.WALL;
            }
            if (randomTiles[x1 + 1][i] == Tileset.WATER) {
                randomTiles[x1 + 1][i] = Tileset.WALL;
            }

        }

    }

    private static void writeWest(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        for (int i = x1; i >= x2; i--) {

            randomTiles[i][y2] = Tileset.FLOOR;

            if (randomTiles[i][y2 + 1] == Tileset.WATER) {
                randomTiles[i][y2 + 1] = Tileset.WALL;
            }
            if (randomTiles[i][y2 - 1] == Tileset.WATER) {
                randomTiles[i][y2 - 1] = Tileset.WALL;
            }


        }
    }

    private static void writeEast(TETile[][] randomTiles, int x1, int y1, int x2, int y2) {
        y2 = y2 - 1;
        for (int i = x1; i <= x2; i++) {
            randomTiles[i][y2] = Tileset.FLOOR;

            if (randomTiles[i][y2 + 1] == Tileset.WATER) {
                randomTiles[i][y2 + 1] = Tileset.WALL;
            }
            if (randomTiles[i][y2 - 1] == Tileset.WATER) {
                randomTiles[i][y2 - 1] = Tileset.WALL;
            }

        }
    }

    private static void roomConnect(TETile[][] randomTiles, Room room1, Room room2) {
        int oMiddleX = room1.getMiddleX();
        int oMiddleY = room1.getMiddleY();

        int rMiddleX = room2.getMiddleX();
        int rMiddleY = room2.getMiddleY();
        if (oMiddleX == rMiddleX) {

            if (oMiddleY < rMiddleY) {
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else {
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            }
        } else if (oMiddleY == rMiddleY) {
            if (oMiddleX < rMiddleX) {
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else {
                writeWest(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                // original is to the right, going west
            }

        } else {
            if (oMiddleX < rMiddleX && oMiddleY < rMiddleY) {
                //NE
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            } else if (oMiddleX > rMiddleX && oMiddleY < rMiddleY) {
                //NW
                writeNorth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeWest(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);

            } else if (oMiddleX > rMiddleX && oMiddleY > rMiddleY) {
                //SW
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeWest(randomTiles, oMiddleX, rMiddleY, rMiddleX, rMiddleY);

            } else {
                //SE
                writeSouth(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
                writeEast(randomTiles, oMiddleX, oMiddleY, rMiddleX, rMiddleY);
            }


        }


    }


    public void cleaner(TETile[][] randomTiles) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (randomTiles[i][j] == Tileset.FLOOR) {
                    if (!outOfBounds(i + 1, j) && randomTiles[i + 1][j] == Tileset.WATER) {
                        randomTiles[i + 1][j] = Tileset.WALL;

                    }
                    if (!outOfBounds(i - 1, j) && randomTiles[i - 1][j] == Tileset.WATER) {
                        randomTiles[i - 1][j] = Tileset.WALL;

                    }
                    if (!outOfBounds(i, j + 1) && randomTiles[i][j + 1] == Tileset.WATER) {
                        randomTiles[i][j + 1] = Tileset.WALL;

                    }
                    if (!outOfBounds(i, j - 1) && randomTiles[i][j - 1] == Tileset.WATER) {
                        randomTiles[i][j - 1] = Tileset.WALL;

                    }

                    //NEW

                    if (!outOfBounds(i - 1, j + 1) && randomTiles[i - 1][j + 1] == Tileset.WATER) {
                        randomTiles[i - 1][j + 1] = Tileset.WALL;

                    }

                    if (!outOfBounds(i + 1, j + 1) && randomTiles[i + 1][j + 1] == Tileset.WATER) {
                        randomTiles[i + 1][j + 1] = Tileset.WALL;

                    }

                    if (!outOfBounds(i + 1, j - 1) && randomTiles[i + 1][j - 1] == Tileset.WATER) {
                        randomTiles[i + 1][j - 1] = Tileset.WALL;

                    }

                    if (!outOfBounds(i - 1, j - 1) && randomTiles[i - 1][j - 1] == Tileset.WATER) {
                        randomTiles[i - 1][j - 1] = Tileset.WALL;

                    }
                }
            }

        }

    }


    public boolean outOfBounds(int x, int y) {
        boolean intBoundX = (x < WIDTH) && (x >= 0);
        boolean intBoundY = (y < HEIGHT) && (y >= 0);
        return !intBoundX || !intBoundY;
    }


    public void moveUp(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {
        if (!outOfBounds(startX, startY + 1)) {
            if (tiles[startX][startY + 1].description().equals("floor")) {
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX][startY + 1] = Tileset.AVATAR;
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX][startY + 1] = Tileset.AVATAR;
                avatarPoint.setY(startY + 1);
                AVATARMOVES.add("W");
            }

            if (tiles[startX][startY + 1].description().equals("gold")) {
                AVATARMOVES.add("W");
                //end game

            }
        }
    }

    public void moveDown(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {
        if (!outOfBounds(startX, startY - 1)) {
            if (tiles[startX][startY - 1].description().equals("floor")) {
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX][startY - 1] = Tileset.AVATAR;
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX][startY - 1] = Tileset.AVATAR;
                avatarPoint.setY(startY - 1);
                AVATARMOVES.add("S");

            }

            if (tiles[startX][startY + 1].description().equals("gold")) {
                AVATARMOVES.add("S");
                //end game

            }
        }

        //we will want to return current position?
    }


    public void moveLeft(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {
        if (!outOfBounds(startX - 1, startY)) {
            if (tiles[startX - 1][startY].description().equals("floor")) {
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX - 1][startY] = Tileset.AVATAR;
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX - 1][startY] = Tileset.AVATAR;
                avatarPoint.setX(startX - 1);
                AVATARMOVES.add("A");

            }

            if (tiles[startX - 1][startY].description().equals("gold")) {
                AVATARMOVES.add("A");
                //end game

            }
        }

        //we will want to return current position?
    }

    public void moveRight(int startX, int startY, TETile[][] tiles, TETile[][] darkTiles) {
        if (!outOfBounds(startX + 1, startY)) {


            if (tiles[startX + 1][startY].description().equals("floor")) {
                tiles[startX][startY] = Tileset.FLOOR;
                tiles[startX + 1][startY] = Tileset.AVATAR;
                darkTiles[startX][startY] = Tileset.NOTHING;
                darkTiles[startX + 1][startY] = Tileset.AVATAR;
                avatarPoint.setX(startX + 1);
                AVATARMOVES.add("D");

            }

            if (tiles[startX + 1][startY].description().equals("gold")) {
                //end game

                AVATARMOVES.add("D");


            }
        }

        //we will want to return current position?
    }


    public ArrayList<String> getAvatarMoves() {
        return AVATARMOVES;
    }

    public void moveAvatar(char c, TETile[][] tiles, TETile[][] darkTiles) {
        if (c == 'W' || c == 'w') {
            moveUp(avatarPoint.getX(), avatarPoint.getY(), tiles, darkTiles);   //up

            return;
        }
        if (c == 'A' || c == 'a') { //left
            moveLeft(avatarPoint.getX(), avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (c == 'D' || c == 'd') { //right
            moveRight(avatarPoint.getX(), avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (c == 'S' || c == 's') { //down
            moveDown(avatarPoint.getX(), avatarPoint.getY(), tiles, darkTiles);

            return;
        }
        if (c == ' ') {
            return;
        }

    }

    public void setAvatarPoint(TETile[][] finalWorldFrame, Point x) {

        finalWorldFrame[avatarPoint.getX()][avatarPoint.getY()] = Tileset.FLOOR;
        avatarPoint = x;

        finalWorldFrame[avatarPoint.getX()][avatarPoint.getY()] = Tileset.AVATAR;

    }



    public void makeDark(TETile[][] lightTiles, TETile[][] darkTiles) {
        for (int a = 0; a < WIDTH; a += 1) {
            for (int b = 0; b < HEIGHT; b += 1) {

                // Make sure gold disappears when avatar reaches it
                if (darkTiles[a][b] != Tileset.AVATAR) {
                    darkTiles[a][b] = Tileset.NOTHING;
                }
            }


            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    // Make sure gold disappears when avatar reaches it
                /*if (lightTiles[x][y] == Tileset.GOLD) {
                    darkTiles[x][y] = Tileset.GOLD;
                }*/
                    if (darkTiles[x][y] == Tileset.AVATAR) {
                        for (int c = 0; c < LIGHTDIST; c++) {
                            for (int d = 0; d < LIGHTDIST; d++) {
                                if (!outOfBounds(x + c, y)) {
                                    darkTiles[x + c][y] = lightTiles[x + c][y];
                                }
                                if (!outOfBounds(x - c, y)) {
                                    darkTiles[x - c][y] = lightTiles[x - c][y];
                                }
                                if (!outOfBounds(x, y + d)) {
                                    darkTiles[x][y + d] = lightTiles[x][y + d];
                                }
                                if (!outOfBounds(x, y - d)) {
                                    darkTiles[x][y - d] = lightTiles[x][y - d];
                                }
                                if (!outOfBounds(x + c, y + d)) {
                                    darkTiles[x + c][y + d] = lightTiles[x + c][y + d];
                                }
                                if (!outOfBounds(x - c, y - d)) {
                                    darkTiles[x - c][y - d] = lightTiles[x - c][y - d];
                                }
                                if (!outOfBounds(x + c, y - d)) {
                                    darkTiles[x + c][y - d] = lightTiles[x + c][y - d];
                                }
                                if (!outOfBounds(x - c, y + d)) {
                                    darkTiles[x - c][y + d] = lightTiles[x - c][y + d];
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}















