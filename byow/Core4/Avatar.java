package byow.Core4;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;

public class Avatar extends Icon {
    private static final TETile AVATAR = new TETile('?', Color.CYAN, Color.WHITE, "snorlax", ".\\images\\snorlax.png");;

    private static int score;

    private static int stepsTaken;

    public Avatar(int dimmX, int dimmY) {
        super(dimmX, dimmY, AVATAR);
        score = 0;
        stepsTaken = 0;
    }

    public void move(TETile[][] tiles, char letter, int x, int y, TETile pickups) {
        if (Character.toUpperCase(letter) == 'W') {
            if (tiles[x][y + 1] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y + 1] = AVATAR;
                setPosY(y + 1);
                stepsTaken += 1;
            }
            else if (tiles[x][y + 1] == pickups) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y + 1] = AVATAR;
                setPosY(y + 1);
                score += 1;
                stepsTaken += 1;
            }
        }
        if (Character.toUpperCase(letter) == 'S') {
            if (tiles[x][y - 1] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y - 1] = AVATAR;
                setPosY(y - 1);
                stepsTaken += 1;
            }
            else if (tiles[x][y - 1] == pickups) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x][y - 1] = AVATAR;
                setPosY(y - 1);
                score += 1;
                stepsTaken += 1;
            }
        }
        if (Character.toUpperCase(letter) == 'A') {
            if (tiles[x - 1][y] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x - 1][y] = AVATAR;
                setPosX(x - 1);
                stepsTaken += 1;
            }
            else if (tiles[x - 1][y] == pickups) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x - 1][y] = AVATAR;
                setPosX(x - 1);
                score += 1;
                stepsTaken += 1;
            }
        }
        if (Character.toUpperCase(letter) == 'D') {
            if (tiles[x + 1][y] == WorldGeneration.FLOORS) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x + 1][y] = AVATAR;
                setPosX(x + 1);
                stepsTaken += 1;
            }
            else if (tiles[x + 1][y] == pickups) {
                tiles[x][y] = WorldGeneration.FLOORS;
                tiles[x + 1][y] = AVATAR;
                setPosX(x + 1);
                score += 1;
                stepsTaken += 1;
            }
        }
        System.out.println("Steps: " + stepsTaken + " | " + "Score: " + score);

    }

    public static int getScore() {
        return score;
    }

    public static int getStepsTaken() {
        return stepsTaken;
    }
}

