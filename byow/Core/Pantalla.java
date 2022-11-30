package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Pantalla {

    private int ANCHO;
    private int ALTURA;

    public Pantalla(int ANCHO, int ALTURA) {
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



    public void loreScreen() {
        String s = "You are a diver exploring a sunken ship. ";
        String t = "You can only lose your flashlight 3 times.";
        String u = "You have to find the treasure. Good luck!";

        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, s);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, t);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 1, u);

        StdDraw.show();
        StdDraw.pause(5000);
    }

    public String nameScreen() {
        String myString = "";

        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter your diver's name, ");

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 4, "of less than 10 characters.");

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 1, "Press 0 to finish.");
        StdDraw.text(this.ANCHO / 2 - 10, this.ALTURA / 2 - 3, "Scuba");

        StdDraw.show();
        int count = 0;
        InputSource inputSource = new KeyboardInputSource();
        int space = 1;

        while (true) {
            space += 0.75;

            String c = String.valueOf(inputSource.getNextKey());
            if (!c.equals(" ")) {
                count += 1;
                if (c.equals("0") || count == 10) {
                    break;
                }
                myString = myString + c;
                StdDraw.text(this.ANCHO / 2 - 5 + count, this.ALTURA / 2 - 3, c);
                StdDraw.show();

            }
        }
        StdDraw.pause(750);
        StdDraw.show();
        return myString;
    }

    public String seedScreen() {
        String myString = "";
        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter a seed.");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, "Press S to finish.");

        StdDraw.show();
        int count = 0;
        InputSource inputSource = new KeyboardInputSource();
        int space = 1;

        while (true) {
            space += 0.75;

            String c = String.valueOf(inputSource.getNextKey());
            if (!c.equals(" ")) {
                count += 1;
                if (c.equals("S")) {
                    break;
                }
                myString = myString + c;
                StdDraw.text(this.ANCHO / 2 - 5 + count, this.ALTURA / 2 - 1, c);
                StdDraw.show();

            }
        }
        StdDraw.pause(750);
        StdDraw.show();
        return myString;




    }
}
