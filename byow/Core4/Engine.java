package byow.Core4;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 90;
    public static final int HEIGHT = 50;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        boolean gameOver = false;
        boolean pressed = false;
        boolean moved;
        boolean light = false;
        char input;
        int pickedUp = 0;
        int stepsTaken = 0;
        String name = " Pokemon Trainer";

        MenuControl mc = new MenuControl(50, 50, name);
        mc.start();

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] lightTiles = new TETile[WIDTH][HEIGHT];
        TETile[][] darkTiles = new TETile[WIDTH][HEIGHT];
        WorldGeneration newWorld = new WorldGeneration(WIDTH, HEIGHT);
        newWorld.canvasFilledNothing(lightTiles);
        newWorld.createRooms(lightTiles);
        newWorld.drawHallway(lightTiles);
        newWorld.addAvatar(lightTiles);
        newWorld.createPickups(lightTiles);
        newWorld.darkenArea(lightTiles, darkTiles);
        newWorld.printBoard();
        ter.renderFrame(lightTiles);

        while (!gameOver) {
            if (pickedUp == Pickups.NUM_OF_PICKUP_TO_END) {
                gameOver = true;
            }
            while (!pressed) {
                if (StdDraw.hasNextKeyTyped()) {
                    input = StdDraw.nextKeyTyped();
                    pressed = true;
                    moved = newWorld.moveAvatar(lightTiles, darkTiles, input);
                    if (moved && light) {
                        ter.renderFrame(darkTiles);
                        newWorld.darkenArea(lightTiles, darkTiles);
                    }
                    if (moved && !light) {
                        ter.renderFrame(lightTiles);
                    }
                    if (Character.toUpperCase(input) == 'F' && light) {
                        light = false;
                        ter.renderFrame(lightTiles);
                    }
                    else if (Character.toUpperCase(input) == 'F' && !light) {
                        light = true;
                        ter.renderFrame(darkTiles);
                    }
                }
                int mX = (int)StdDraw.mouseX();
                int mY = (int)StdDraw.mouseY();
                mc.showNamesHUD(mX,mY,lightTiles, pickedUp, stepsTaken);
            }
            pickedUp = Avatar.getScore();
            stepsTaken = Avatar.getStepsTaken();
            pressed = false;
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, running both of these:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}

