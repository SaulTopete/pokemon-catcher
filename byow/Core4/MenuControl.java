package byow.Core4;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class MenuControl {

    private int Width;
    private int Height;

    public MenuControl(int Width, int Height) {
        this.Width = Width;
        this.Height = Height;
        StdDraw.setCanvasSize(this.Width * 16, this.Height * 16);
        Font font = new Font("Helvetica", Font.PLAIN, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.Width);
        StdDraw.setYscale(0, this.Height);
        StdDraw.clear(Color.orange);
        StdDraw.enableDoubleBuffering();
    }

    public void initialScreen() {
        StdDraw.clear(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(Width, Height, this.Width, this.Height/4 + 1);
        StdDraw.filledRectangle(0, 0, this.Width, this.Height/4);
        Font font = new Font("Monospaced", Font.ITALIC, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.Width / 2, this.Height / 2 + 7, "Title");

        Font secondFont = new Font("Courier", Font.PLAIN, 25);
        StdDraw.setFont(secondFont);
        StdDraw.text(this.Width / 2, this.Height / 2 + 1, "Let's Play! (N)");
        StdDraw.text(this.Width / 2, this.Height / 2 - 1, "Hey! What's Your Name? (T)");
        StdDraw.text(this.Width / 2, this.Height / 2 - 3.5, "(☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜)");
        StdDraw.text(this.Width / 2, this.Height / 2 - 6, "Let's Continue (L)");
        StdDraw.text(this.Width / 2, this.Height / 2 - 9, "Le't play Another Time (Q)");

        StdDraw.show();
    }

    private boolean esN(char teclado){
        boolean si = teclado== 'N';
        return si;
    }
    private boolean esT(char teclado){
        boolean si = teclado == 'T';
        return si;
    }
    private boolean esL(char teclado){
        boolean si = teclado == 'L';
        return si;
    }
    private boolean esQ(char teclado){
        boolean si = teclado == 'Q';
        return si;
    }

    public String start() {
        initialScreen();
        InputSource inputSource = new KeyboardInputSource();
        while (true) {
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
    }
}
