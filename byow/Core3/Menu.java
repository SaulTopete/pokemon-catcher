package byow.Core3;

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
        Font font = new Font("Helvetica", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.orange);
        StdDraw.enableDoubleBuffering();
    }

    public void initialScreen() {
        StdDraw.clear(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(width, height, this.width, this.height/4 + 1);
        StdDraw.filledRectangle(0, 0, this.width, this.height/4);
        Font font = new Font("Monospaced", Font.ITALIC, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.width / 2, this.height / 2 + 7, "Underwater Treasure Hunter");

        Font secondFont = new Font("Courier", Font.PLAIN, 25);
        StdDraw.setFont(secondFont);
        StdDraw.text(this.width / 2, this.height / 2 + 1, "Let's Play! (N)");
        StdDraw.text(this.width / 2, this.height / 2 - 1, "Hey! What's Your Name? (T)");
        StdDraw.text(this.width / 2, this.height / 2 - 3.5, "(*_*)");
        StdDraw.text(this.width / 2, this.height / 2 - 6, "Let's Continue (L)");
        StdDraw.text(this.width / 2, this.height / 2 - 9, "Le't play Another Time (Q)");

        StdDraw.show();
    }

    public boolean esN(char teclado){
        boolean si = teclado== 'N';
        return si;
    }
    public boolean esT(char teclado){
        boolean si = teclado == 'T';
        return si;
    }
    public boolean esL(char teclado){
        boolean si = teclado == 'L';
        return si;
    }
    public boolean esQ(char teclado){
        boolean si = teclado == 'Q';
        return si;
    }

    public String start() {
        initialScreen();
        InputSource inputSource = new KeyboardInputSource();
        boolean hastaQueEstoSeaFalso = true;
        while (true) {
            try {
                char esteclado = inputSource.getNextKey();
                boolean teclado1 = esN(esteclado);
                boolean teclado2 = esT(esteclado);
                boolean teclaro3 = esL(esteclado);
                boolean teclado4 = esQ(esteclado);

                if (teclado1) {
                    return String.valueOf(esteclado);
                }
                if (teclado2) {
                    return String.valueOf(esteclado);
                }
                if (teclaro3) {
                    return String.valueOf(esteclado);
                }
                if (teclado4) {
                    return String.valueOf(esteclado);
                }
            }
            catch (Exception e){
                continue;
            }
        }
    }
}
