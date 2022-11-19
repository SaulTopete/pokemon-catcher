package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Screen {

    /** add seed and name */

    private int width;
    private int height;

    public Screen(int width, int height) {
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



    public void loreScreen() {
        String s = "You are a diver exploring a sunken ship. ";
        String t = "You can only lose your flashlight 3 times.";
        String u = "You have to find the treasure. Good luck!";

        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.width / 2, this.height / 2 + 7, s);
        StdDraw.text(this.width / 2, this.height / 2 + 3, t);
        StdDraw.text(this.width / 2, this.height / 2 - 1, u);

        StdDraw.show();
        StdDraw.pause(5000);
    }

    public String nameScreen() {
        String myString = "";

        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.width / 2, this.height / 2 + 7, "Please enter your diver's name, ");

        StdDraw.text(this.width / 2, this.height / 2 + 4, "of less than 10 characters.");

        StdDraw.text(this.width / 2, this.height / 2 + 1, "Press 0 to finish.");
        StdDraw.text(this.width / 2 - 10, this.height / 2 - 3, "Scuba");

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
                StdDraw.text(this.width / 2 - 5 + count, this.height / 2 - 3, c);
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

        StdDraw.text(this.width / 2, this.height / 2 + 7, "Please enter a seed.");
        StdDraw.text(this.width / 2, this.height / 2 + 3, "Press S to finish.");

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
                StdDraw.text(this.width / 2 - 5 + count, this.height / 2 - 1, c);
                StdDraw.show();

            }
        }
        StdDraw.pause(750);
        StdDraw.show();
        return myString;




    }






}
