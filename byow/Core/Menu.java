package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Menu {

    private int ANCHO;
    private int ALTURA;

    public Menu(int ANCHO, int ALTURA) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.ANCHO = ANCHO;
        this.ALTURA = ALTURA;
        StdDraw.setCanvasSize(this.ANCHO * 16, this.ALTURA * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.ANCHO);
        StdDraw.setYscale(0, this.ALTURA);
        StdDraw.clear(Color.CYAN);
        StdDraw.enableDoubleBuffering();
    }

    public void initialScreen() {
        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Underwater Treasure Hunter");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2, "New Game (N)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 3, "New Game and Give Name (T)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 6, "Load Game (L)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 9, "Quit (Q)");

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
