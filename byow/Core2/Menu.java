package byow.Core2;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Menu {
    //N for “new world”, L for “load world”, and Q for quit
    //N -> enter random seed
    //L -> load previously saved world (should be able to save types of world,
    //maybe similar to what we do to have world that is hidden and one that is shown)
    //Q -> quit and save

    private int width;
    private int height;

    public Menu(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.CYAN);
        StdDraw.enableDoubleBuffering();
    }

    public void initialScreen() {
        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.width / 2, this.height / 2 + 7, "Underwater Treasure Hunter");
        StdDraw.text(this.width / 2, this.height / 2, "New Game (N)");
        StdDraw.text(this.width / 2, this.height / 2 - 3, "New Game and Give Name (T)");
        StdDraw.text(this.width / 2, this.height / 2 - 6, "Load Game (L)");
        StdDraw.text(this.width / 2, this.height / 2 - 9, "Quit (Q)");

        StdDraw.show();
    }


    public String start() {
        initialScreen();
        InputSource inputSource = new KeyboardInputSource();
        while (true) {
            char c = inputSource.getNextKey();
            if (c == 'N') {
                return String.valueOf(c);
            }
            if (c == 'T') {
                return String.valueOf(c);
            }
            if (c == 'L') {
                return String.valueOf(c);
            }
            if (c == 'Q') {
                return String.valueOf(c);
            }
        }
    }
}
