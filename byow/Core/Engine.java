package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

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
        char keyboardInput;
        String quitInput = "";
        int pickedUp = 0;
        int stepsTaken = 0;
        int seed = 0;
        String name = "";

        MenuControl mc = new MenuControl(50, 50, name);
        String teclado = mc.start();
        TERenderer ter = new TERenderer();
        SaveFile fs;
        LoadFile lf = new LoadFile();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] lightTiles = new TETile[WIDTH][HEIGHT];
        TETile[][] darkTiles = new TETile[WIDTH][HEIGHT];
        WorldGeneration newWorld = new WorldGeneration(WIDTH, HEIGHT, seed);

        if(teclado.equals("N")) {
            String myString = "";
            StdDraw.clear(StdDraw.ORANGE);
            StdDraw.ellipse(24.4, 23.7, 23.9, 23.3);
            StdDraw.line(0, this.HEIGHT - 27.5, this.WIDTH + 4,this.HEIGHT - 27.5);
            StdDraw.filledEllipse(this.WIDTH / 2 - 20, this.HEIGHT/2 - 2, 5.2, 4);
            StdDraw.text(this.WIDTH / 2 - 19.5, this.HEIGHT / 2 + 7, "Insert a Seed to begin.");
            StdDraw.text(this.WIDTH / 2 - 19.5, this.HEIGHT / 2 + 3, "Press S to finish.");
            StdDraw.setPenColor(Color.WHITE);

            StdDraw.show();
            mc.seedScreen(myString);
            StdDraw.show();

            newWorld.canvasFilledNothing(lightTiles);
            newWorld.createRooms(lightTiles);
            newWorld.drawHallway(lightTiles);
            newWorld.addAvatar(lightTiles);
            newWorld.createPickups(lightTiles);
        }
        else if(teclado.equals("T")){
            String myString = "";
            StdDraw.clear(StdDraw.ORANGE);
            StdDraw.ellipse(24.4, 23.7, 23.9, 23.3);
            StdDraw.line(0, this.HEIGHT - 27.5, this.WIDTH + 4,this.HEIGHT - 27.5);
            StdDraw.filledEllipse(this.WIDTH / 2 - 20, this.HEIGHT/2 - 2, 5.2, 4);
            StdDraw.text(this.WIDTH / 2 - 19.5, this.HEIGHT / 2 + 10, "Welcome Trainer");
            StdDraw.text(this.WIDTH / 2 - 19.5, this.HEIGHT / 2 + 8, "What's your name?");
            StdDraw.text(this.WIDTH / 2 - 19.5, this.HEIGHT / 2 + 6, "Press . to finish.");
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.show();

            name = mc.getNameTrainer(myString);
            StdDraw.show();

            newWorld.canvasFilledNothing(lightTiles);
            newWorld.createRooms(lightTiles);
            newWorld.drawHallway(lightTiles);
            newWorld.addAvatar(lightTiles);
            newWorld.createPickups(lightTiles);
        }
        else if (teclado.equals("L")){
            newWorld = new WorldGeneration(WIDTH, HEIGHT,
                    lf.loadRoomsAreaList(), lf.loadAvatarX(), lf.loadAvatarY(),
                    lf.loadPickupPosList(), lf.loadPickupsPos(), lf.loadEnvironmentPos());
            newWorld.canvasFilledNothing(lightTiles);
            newWorld.loadFloors(lightTiles);
            newWorld.loadAvatar(lightTiles);
            newWorld.loadPickups(lightTiles);
            newWorld.darkenArea(lightTiles, darkTiles);
        }
        else if (teclado.equals("Q")) {
            System.exit(0);
        }

        newWorld.darkenArea(lightTiles, darkTiles);
        newWorld.printBoard();
        ter.renderFrame(lightTiles);

        while (!gameOver) {
            if (pickedUp == Pickups.NUM_OF_PICKUP_TO_END) {
                gameOver = true;
            }
            while (!pressed) {
                if (StdDraw.hasNextKeyTyped()) {
                    keyboardInput = StdDraw.nextKeyTyped();
                    pressed = true;
                    moved = newWorld.moveAvatar(lightTiles, darkTiles, keyboardInput);
                    if (keyboardInput == ':' || Character.toUpperCase(keyboardInput) == 'Q') {
                        quitInput += Character.toUpperCase(keyboardInput);
                    } else {
                        if (moved && light) {
                            ter.renderFrame(darkTiles);
                            newWorld.darkenArea(lightTiles, darkTiles);
                        }
                        if (moved && !light) {
                            ter.renderFrame(lightTiles);
                        }
                        if (Character.toUpperCase(keyboardInput) == 'F' && light) {
                            light = false;
                            ter.renderFrame(lightTiles);
                        } else if (Character.toUpperCase(keyboardInput) == 'F' && !light) {
                            light = true;
                            ter.renderFrame(darkTiles);
                        }
                        quitInput = "";
                    }
                    if (quitInput.equals(":Q")) {
                        gameOver = true;
                        fs = new SaveFile();
                        fs.save(newWorld, newWorld.getAvatarX(), newWorld.getAvatarY(), newWorld.getPickupsList(), newWorld.getRanEnvListPos(), newWorld.getRanPickupListPos());
                        System.exit(0);
                    }
                }
                int mX = (int) StdDraw.mouseX();
                int mY = (int) StdDraw.mouseY();
                mc.showNamesHUD(mX, mY, lightTiles, pickedUp, stepsTaken, name);
            }
            pickedUp = Avatar.getScore();
            stepsTaken = Avatar.getStepsTaken();
            pressed = false;
        }
        System.exit(0);
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

