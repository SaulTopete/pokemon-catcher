package byow.Core;

import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdAudio;

import java.awt.*;

public class Avatar extends Icon {
    public static final TETile AVATAR = new TETile('?', Color.CYAN, Color.WHITE, "trainer", ".\\images\\joshhug.jpg");
    public static final TETile OUTSIDE_1 = new TETile(':', Color.RED, Color.WHITE, "tree", ".\\images\\minecraft_grass.jpg");
    public static final TETile OUTSIDE_2 = new TETile('i', Color.BLUE, Color.WHITE, "grass", ".\\images\\stone.png");

    private static int score;

    private static int stepsTaken;

    public Avatar(int dimmX, int dimmY) {
        super(dimmX, dimmY, AVATAR);
        score = 0;
        stepsTaken = 0;
    }

    public boolean move(TETile[][] lightTiles, TETile[][] darkTiles, char letter, int x, int y, TETile pickups) {
        boolean moved = false;
        if (Character.toUpperCase(letter) == 'W') {
            if (lightTiles[x][y + 1] == WorldGeneration.FLOORS) {
                lightDarkenArea(lightTiles, darkTiles);
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x][y + 1] = AVATAR;
                darkTiles[x][y + 1] = lightTiles[x][y + 1];
                setPosY(y + 1);
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
            else if (lightTiles[x][y + 1] == pickups) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x][y + 1] = AVATAR;
                darkTiles[x][y] = AVATAR;
                setPosY(y + 1);
                score += 1;
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }

        }
        if (Character.toUpperCase(letter) == 'S') {
            if (lightTiles[x][y - 1] == WorldGeneration.FLOORS) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x][y - 1] = AVATAR;
                darkTiles[x][y - 1] = AVATAR;
                setPosY(y - 1);
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
            else if (lightTiles[x][y - 1] == pickups) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x][y - 1] = AVATAR;
                darkTiles[x][y - 1] = AVATAR;
                setPosY(y - 1);
                score += 1;
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
        }
        if (Character.toUpperCase(letter) == 'A') {
            if (lightTiles[x - 1][y] == WorldGeneration.FLOORS) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x - 1][y] = AVATAR;
                darkTiles[x - 1][y] = AVATAR;
                setPosX(x - 1);
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
            else if (lightTiles[x - 1][y] == pickups) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x - 1][y] = AVATAR;
                darkTiles[x - 1][y] = AVATAR;
                setPosX(x - 1);
                score += 1;
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
        }
        if (Character.toUpperCase(letter) == 'D') {
            if (lightTiles[x + 1][y] == WorldGeneration.FLOORS) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x + 1][y] = AVATAR;
                darkTiles[x + 1][y] = AVATAR;
                setPosX(x + 1);
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
            else if (lightTiles[x + 1][y] == pickups) {
                lightTiles[x][y] = WorldGeneration.FLOORS;
                darkTiles[x][y] = lightTiles[x][y];
                lightTiles[x + 1][y] = AVATAR;
                darkTiles[x + 1][y] = AVATAR;
                setPosX(x + 1);
                score += 1;
                stepsTaken += 1;
                lightDarkenArea(lightTiles, darkTiles);
                moved = true;
            }
        }
        System.out.println("Steps: " + stepsTaken + " | " + "Score: " + score);
        return moved;
    }

    public static int getScore() {
        return score;
    }

    public static int getStepsTaken() {
        return stepsTaken;
    }

    private void lightDarkenArea(TETile[][] lightTiles, TETile[][] darkTiles) {
        //left
        if (getPosX() - 1 >= 0) {
            darkTiles[getPosX() - 1][getPosY()] = lightTiles[getPosX() - 1][getPosY()];
        }
        //right
        if (getPosX() + 1 < Engine.WIDTH) {
            darkTiles[getPosX() + 1][getPosY()] = lightTiles[getPosX() + 1][getPosY()];
        }
        //bottom
        if (getPosY() - 1 > 0) {
            darkTiles[getPosX()][getPosY() - 1] = lightTiles[getPosX()][getPosY() - 1];
        }
        //top
        if (getPosY() + 1 < Engine.HEIGHT) {
            darkTiles[getPosX()][getPosY() + 1] = lightTiles[getPosX()][getPosY() + 1];
        }
        //top left
        if (getPosX() - 1 >= 0 && getPosY() + 1 < Engine.HEIGHT) {
            darkTiles[getPosX() - 1][getPosY() + 1] = lightTiles[getPosX() - 1][getPosY() + 1];
        }
        //bottom right
        if (getPosX() - 1 >= 0 && getPosY() - 1 >= 0) {
            darkTiles[getPosX() - 1][getPosY() - 1] = lightTiles[getPosX() - 1][getPosY() - 1];
        }
        //top right
        if (getPosX() + 1 < Engine.WIDTH && getPosY() + 1 < Engine.HEIGHT) {
            darkTiles[getPosX() + 1][getPosY() + 1] = lightTiles[getPosX() + 1][getPosY() + 1];
        }
        //bottom right
        if (getPosX() + 1 < Engine.WIDTH && getPosY() - 1 < Engine.HEIGHT) {
            darkTiles[getPosX() + 1][getPosY() - 1] = lightTiles[getPosX() - 1][getPosY() - 1];
        }
    }
}

