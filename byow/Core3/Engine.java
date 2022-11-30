package byow.Core3;
import java.io.*;
import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Engine {
    TERenderer ter = new TERenderer();
    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;

    public Point getAvatarPos(TETile[][] tiles) {
        Point avatarPoint = new Point(0, 0);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (tiles[i][j] == Tileset.AVATAR) {
                    avatarPoint = new Point(i, j);
                }
            }
        }
        return avatarPoint;
    }
    public void interactAfterLoad(TETile[][] loadedWorld, String seedString, String nameString,
                                  boolean darkParameter) {
        // Find avatar location
        Point avatarPoint = getAvatarPos(loadedWorld);
        long myLong = Long.parseLong(seedString);
        AvatarControl newWorld = new AvatarControl(WIDTH, HEIGHT, myLong, avatarPoint);
        TETile[][] darkWorldFrame = TETile.copyOf(loadedWorld);
        newWorld.makeDark(loadedWorld, darkWorldFrame);
        HUD hud = new HUD(WIDTH, HEIGHT, nameString);
        int mouseX;
        int mouseY;
        LinkedList<String> prevMoves;
        boolean game = true;
        TETile[][] tempWorld = TETile.copyOf(darkWorldFrame);
        boolean dark;
        if (darkParameter) {
            dark = true;
            ter.renderFrame(darkWorldFrame);
        } else {
            dark = false;
            ter.renderFrame(loadedWorld);
        }
        while (game) { //while the game is being played
            mouseX = (int) StdDraw.mouseX();
            mouseY = (int) StdDraw.mouseY();
            hud.updateHUD(newWorld, mouseX, mouseY, loadedWorld);
            InputSource inputSource = new KeyboardInputSource();
            char f = inputSource.getNextKey();
            if (f == 'F') {
                if (dark) {
                    tempWorld = loadedWorld;
                    dark = false;
                } else {
                    tempWorld = darkWorldFrame;
                    dark = true;
                }
                ter.renderFrame(tempWorld);
            } else if (f == ':') {
                char q = inputSource.getNextKey();
                if (q == 'Q') {
                    // Save and quit
                    break;
                }
            } else {
                newWorld.moveAvatar(f, loadedWorld, darkWorldFrame);
                newWorld.makeDark(loadedWorld, darkWorldFrame);
                if (dark) {
                    tempWorld = darkWorldFrame;
                } else {
                    tempWorld = loadedWorld;
                }
                ter.renderFrame(tempWorld);
            }

            mouseX = (int) StdDraw.mouseX();
            mouseY = (int) StdDraw.mouseY();
            hud.updateHUD(newWorld, mouseX, mouseY, loadedWorld);
        }
        prevMoves = newWorld.getAvatarMoves();
        prevMoves.add(0, "N");
        prevMoves.add(1, seedString);
        prevMoves.add(2, "S");
        prevMoves.add(":Q");
        String finalSave = "";
        for (int e = 0; e < prevMoves.size(); e++) {
            finalSave = finalSave + prevMoves.get(e);

        }
        String state = "light";
        if (dark) {
            state = "dark";
        }
        saveCurrent(finalSave, nameString, seedString, avatarPoint, state);
        System.exit(0);
    }

    public static void saveCurrent(String game, String name, String seed, Point pos, String dark) {
        String savedGame = "SavedGame.txt";
        String savedName = "SavedName.txt";
        String savedSeed = "SavedSeed.txt";
        String savedPos = "SavedPos.txt";
        String savedDark = "SavedDark.txt";

        try {
            // Game saved
            Writer writerG = new FileWriter(savedGame);
            writerG.write(game);
            writerG.close();
            // Name saved
            if (name != null) {
                Writer writerN = new FileWriter(savedName);
                writerN.write(name);
                writerN.close();
            }
            // Seed saved
            Writer writerS = new FileWriter(savedSeed);
            writerS.write(seed);
            writerS.close();
            // Position saved
            Writer writerP = new FileWriter(savedPos);
            String posStr = pos.toString();
            writerP.write(posStr);
            writerP.close();
            // Saving if the world is dark
            if (dark != null) {
                Writer writerD = new FileWriter(savedDark);
                writerD.write(dark);
                writerD.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getGame() {
        String gameString = "";
        try {
            File myObj = new File("SavedGame.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                gameString += myReader.nextLine(); //getName string and seedString as well
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        return gameString;
    }

    public static String getName() {
        String nameString = "";
        try {
            File myObj = new File("SavedName.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                nameString += myReader.nextLine(); //getName string and seedString as well
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
        }
        return nameString;
    }
    public String getSeed() {
        String seedString = "";
        try {
            File myObj = new File("SavedSeed.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                seedString += myReader.nextLine(); //getName string and seedString as well
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        return seedString;
    }
    public Point getPos() {
        String posString = "";
        try {
            File myObj = new File("SavedPos.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                posString += myReader.nextLine(); //getName string and seedString as well
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        String[] pos = posString.split(",");
        int avatarX = Integer.parseInt(pos[0]);
        int avatarY = Integer.parseInt(pos[1]);
        return new Point(avatarX, avatarY);
    }

    public static boolean getDark() {
        String darkString = "";
        try {
            File myObj = new File("SavedDark.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                darkString += myReader.nextLine(); //getName string and seedString as well
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
        return darkString.equals("dark");
    }

    public void initWorld(AvatarControl thisWorld, TETile[][] tiles, long seed) {
        int roomNum = thisWorld.randomNumberRooms(seed);
        thisWorld.nothingFill(tiles);
        thisWorld.drawRooms(tiles, roomNum);
        thisWorld.drawHallways(tiles, roomNum);
//        thisWorld.cleaner(tiles);
    }
    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        Menu menu = new Menu(40, 40); //display main menu
        String c = menu.start();
        if (c.equals("N") || c.equals("T")) { // New world options (name or no name)
            Screen screen = new Screen(40, 40); // Start screen
            screen.loreScreen();
            String nameString = "";
            if (c.equals("T")) {
                nameString = screen.nameScreen();
            }
            String seedString = screen.seedScreen();
            Point avatarPoint = new Point(0, 0);
            long myLong = Long.parseLong(seedString);
            AvatarControl newWorld = new AvatarControl(WIDTH, HEIGHT, myLong, avatarPoint);
            TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
            initWorld(newWorld, finalWorldFrame, myLong); // Creating world and tiles
            ter.initialize(WIDTH, HEIGHT);
            TETile[][] darkWorldFrame = TETile.copyOf(finalWorldFrame);
            TETile[][] tempWorldFrame = TETile.copyOf(darkWorldFrame);
            newWorld.makeDark(finalWorldFrame, darkWorldFrame); // Toggle light
            ter.renderFrame(darkWorldFrame);
            LinkedList<String> prevMoves;
            HUD hud = new HUD(WIDTH, HEIGHT, nameString);
            int mouseX; int mouseY; boolean dark = true;
            while (true) { //while the game is being played
                mouseX = (int) StdDraw.mouseX(); mouseY = (int) StdDraw.mouseY();
                hud.updateHUD(newWorld, mouseX, mouseY, finalWorldFrame);
                InputSource inputSource = new KeyboardInputSource();
                char f = inputSource.getNextKey();
                if (f == 'F') {
                    if (dark) {
                        tempWorldFrame = finalWorldFrame;
                        dark = false;
                    } else {
                        tempWorldFrame = darkWorldFrame;
                        dark = true;
                    }
                    ter.renderFrame(tempWorldFrame);
                } else if (f == ':') {
                    if (inputSource.getNextKey() == 'Q') {
                        break; // Save and quit done below
                    }
                } else {
                    newWorld.moveAvatar(f, finalWorldFrame, darkWorldFrame);
                    newWorld.makeDark(finalWorldFrame, darkWorldFrame);
                    if (dark) {
                        tempWorldFrame = darkWorldFrame;
                    } else {
                        tempWorldFrame = finalWorldFrame;
                    }
                    ter.renderFrame(tempWorldFrame);
                }
                mouseX = (int) StdDraw.mouseX();
                mouseY = (int) StdDraw.mouseY();
                hud.updateHUD(newWorld, mouseX, mouseY, finalWorldFrame);
            }
            prevMoves = newWorld.getAvatarMoves();
            String finalSave = "";
            for (int a = 0; a < prevMoves.size(); a++) {
                finalSave = finalSave + prevMoves.get(a);
            }
            String state = "light";
            if (dark) {
                state = "dark";
            }
            saveCurrent(finalSave, nameString, seedString, getAvatarPos(tempWorldFrame), state);
            System.exit(0);
        }
        if (c.equals("L")) { // Load option
            String data = getGame();
            String seedString = getSeed();
            data = "N" + seedString + "S" + data;
            TETile[][] loadWorld = interactWithInputString(data); //load world is a light world
            ter.initialize(WIDTH, HEIGHT);
            interactAfterLoad(loadWorld, seedString, getName(), getDark());
        }
        if (c.equals("Q")) {
            System.exit(0);
        }
    }

    public void setUp(ArrayList<String> temp, String seedString) {
        temp.add(0, "N");
        temp.add(1, seedString);
        temp.add(2, "S");
        temp.add(":Q");
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
     * In other words, both of these calls:
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
        //no menu should be displayed and nothing is drawn to string
        String command = input.substring(0, 1);
        // N: new world created
        if (command.equals("N") || command.equals("n")) {
            //ter.initialize(WIDTH, HEIGHT);
            int seedIndex = 0;
            int moveIndex = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if ((c == 'S' || c == 's') && seedIndex == 0) {
                    seedIndex = i;
                }
                if (c == ':' && moveIndex == 0) {
                    if (input.charAt(i + 1) == 'q' || input.charAt(i + 1) == 'Q') {
                        moveIndex = i;
                    }
                }
                if (i == input.length() - 1 && moveIndex == 0) {
                    moveIndex = i + 1;
                }
            }
            String seed = input.substring(1, seedIndex);
            String moves = "";
            // No movement commands
            if (seedIndex != moveIndex) {
                moves = input.substring(seedIndex + 1, moveIndex);
            }
            long myLong = Long.parseLong(seed);
            TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
            Point avatarPoint = new Point(0, 0);
            AvatarControl newWorld = new AvatarControl(WIDTH, HEIGHT, myLong, avatarPoint);
            newWorld.nothingFill(finalWorldFrame);
            int roomNum = newWorld.randomNumberRooms(myLong);
            newWorld.drawRooms(finalWorldFrame, roomNum);
            newWorld.drawHallways(finalWorldFrame, roomNum);
//            newWorld.cleaner(finalWorldFrame);
            //ter.renderFrame(finalWorldFrame);
            for (char a : moves.toCharArray()) {
                TETile[][] darkWorldFrame = TETile.copyOf(finalWorldFrame);
                newWorld.moveAvatar(a, finalWorldFrame, darkWorldFrame);
            }
            Point avatarPos = getAvatarPos(finalWorldFrame);
            String finalSave = moves;
            String seedString = seed;
            saveCurrent(finalSave, null, seedString, avatarPos, null);
            //ter.renderFrame(finalWorldFrame);
            return finalWorldFrame;
        } else if (command.equals("L") || command.equals("l")) { // L: load world
            String moves = input.substring(1);
            String seedString = getSeed();
            Point avatarPos = getPos();
            long myLong = Long.parseLong(seedString);
            TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
            //ter.initialize(WIDTH, HEIGHT);
            AvatarControl newWorld = new AvatarControl(WIDTH, HEIGHT, myLong, new Point(0, 0));
            newWorld.nothingFill(finalWorldFrame);
            int roomNum = newWorld.randomNumberRooms(myLong);
            newWorld.drawRooms(finalWorldFrame, roomNum);
            newWorld.drawHallways(finalWorldFrame, roomNum);
//            newWorld.cleaner(finalWorldFrame);
            newWorld.setAvatarPoint(finalWorldFrame, avatarPos);
            for (char a : moves.toCharArray()) {
                TETile[][] newWorldFrame = TETile.copyOf(finalWorldFrame);
                newWorld.moveAvatar(a, finalWorldFrame, newWorldFrame);
            }
            Point avatarPoint = getAvatarPos(finalWorldFrame);
            saveCurrent(input, null, seedString, avatarPoint, null);
            //ter.renderFrame(finalWorldFrame);
            return finalWorldFrame;
        }
        return null;
    }
}

