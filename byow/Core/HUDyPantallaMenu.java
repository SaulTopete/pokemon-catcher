package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class HUDyPantallaMenu {

    private int ANCHO;
    private int ALTURA;
    private String PALABRA;

    public HUDyPantallaMenu(int ANCHO, int ALTURA, String PALABRA){
        this.ANCHO = ANCHO;
        this.ALTURA = ALTURA;
        this.PALABRA = PALABRA;

        Font fuente = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fuente);
        StdDraw.setXscale(0, this.ANCHO);
        StdDraw.setYscale(0, this.ALTURA);
        StdDraw.clear(Color.CYAN);
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(this.ANCHO * 16, this.ALTURA * 16);
    }

//    public void inicioDeLaPantallaAlpha(){
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Underwater Treasure Hunter");
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2, "New Game (N)");
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 3, "New Game and Give Name (T)");
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 6, "Load Game (L)");
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 9, "Quit (Q)");
//    }

    public void inicioDeLaPantalla() {

        StdDraw.clear(StdDraw.CYAN);
        Font fuente = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(fuente);
//        inicioDeLaPantallaAlpha();
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Underwater Treasure Hunter");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2, "New Game (N)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 3, "New Game and Give Name (T)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 6, "Load Game (L)");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 9, "Quit (Q)");
        StdDraw.show();
    }

//    public String inicioBetha (char teclado) {
//        String retornando = new String();
//        if (teclado == 'N') {
//            retornando = String.valueOf(teclado);
//        }
//        if (teclado == 'T') {
//            retornando = String.valueOf(teclado);
//        }
//        if (teclado == 'L') {
//            retornando = String.valueOf(teclado);
//        }
//        if (teclado == 'Q') {
//            retornando = String.valueOf(teclado);
//        }
//        return retornando;
//    }

//    public String inicioAlpha (InputSource ips1) {
//        boolean hastaQueSeaFalso = true;
//        String st = null;
//        while (hastaQueSeaFalso) {
//            char tecladoClave = ips1.getNextKey();
//            st = inicioBetha(tecladoClave);
//        }
//        return st;
//    }

    public String inicio () {
        inicioDeLaPantalla();
//        InputSource ips = new KeyboardInputSource();
//        return inicioAlpha(ips);
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

//    public void estudioDePantallaAlpha(String ese, String te, String u2) {
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, ese);
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, te);
//        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 1, u2);
//    }

    public void estudioDePantalla() {
        String s = "You are a diver exploring a sunken ship. ";
        String t = "You can only lose your flashlight 3 times.";
        String u = "You have to find the treasure. Good luck!";


        StdDraw.clear(StdDraw.CYAN);
        Font fuente = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(fuente);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, s);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, t);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 - 1, u);

//        estudioDePantallaAlpha(s, t, u);
        StdDraw.show();
        StdDraw.pause(5000);
    }

    public void pantallaNombreAlpha(){
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter your diver's name, ");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 4, "of less than 10 characters.");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 1, "Press 0 to finish.");
        StdDraw.text(this.ANCHO / 2 - 10, this.ALTURA / 2 - 3, "Scuba");
    }

    public String pantallaNombreBetha(InputSource ips) {
        String miComentario = "";
        int contador = 0;
        while (true) {
//            space += 0.75;

            String c = String.valueOf(ips.getNextKey());
            if (!c.equals(" ")) {
                contador += 1;
                if (c.equals("0") || contador == 10) {
                    break;
                }
                miComentario = miComentario + c;
                StdDraw.text(this.ANCHO / 2 - 5 + contador, this.ALTURA / 2 - 3, c);
                StdDraw.show();

            }
        }
        return miComentario;
    }

    public String pantallaNombre() {
        String nuevoS;
        StdDraw.clear(StdDraw.CYAN);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);

        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter your diver's name, ");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 4, "of less than 10 characters.");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 1, "Press 0 to finish.");
        StdDraw.text(this.ANCHO / 2 - 10, this.ALTURA / 2 - 3, "Scuba");
//        pantallaNombreAlpha();
        StdDraw.show();

        InputSource is = new KeyboardInputSource();
        nuevoS = pantallaNombreBetha(is);
        StdDraw.pause(750);
        StdDraw.show();
        return nuevoS;
    }

    public void numeroDelMundoPantallaAlpha(){
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter a seed.");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, "Press S to finish.");
    }

    public String numeroDelMundoPantallaBetha(InputSource ips){
        String miComentario = "";
        int contador = 0;

        while (true) {
//            space += 0.75;

            String c = String.valueOf(ips.getNextKey());
            if (!c.equals(" ")) {
                contador += 1;
                if (c.equals("S")) {
                    break;
                }
                miComentario = miComentario + c;
                StdDraw.text(this.ANCHO / 2 - 5 + contador, this.ALTURA / 2 - 1, c);
                StdDraw.show();
            }
        }
        return miComentario;
    }

    public String numeroDelMundoPantalla() {

        String nuevoS;
        StdDraw.clear(StdDraw.CYAN);
        Font fuente = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(fuente);
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 7, "Please enter a seed.");
        StdDraw.text(this.ANCHO / 2, this.ALTURA / 2 + 3, "Press S to finish.");
//        numeroDelMundoPantallaAlpha();
        StdDraw.show();

        InputSource is = new KeyboardInputSource();
        nuevoS = numeroDelMundoPantallaBetha(is);
        StdDraw.pause(750);
        StdDraw.show();
        return nuevoS;
    }

    public String actualizacionDelHUDalpha(int cordenadaX, int cordenadaY,
                                           TETile[][] tiles) {
        String elTitulo;
        boolean requerimiento = cordenadaX >= 0;
        boolean requerimiento2 = cordenadaY >= 0;
        boolean requerimiento3 = cordenadaX < ANCHO;
        boolean requerimiento4 = cordenadaY < ALTURA;
        String posibleTitulo = tiles[cordenadaX][cordenadaY].description();
        if ((requerimiento && requerimiento3) &&
                (requerimiento2 && requerimiento4)) {
            elTitulo = posibleTitulo;
        } else {
            elTitulo = "";
        }
        return elTitulo;
    }

    public void actualizacionDelHUDbetha() {
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(10, ALTURA - 1, "Name: Scuba " + PALABRA);
    }

    public void actualizacionDelHUD(int cordenadaX, int cordenadaY,
                                    TETile[][] tiles) {
        String mt;
        mt = actualizacionDelHUDalpha(cordenadaX, cordenadaY, tiles);

        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(10, ALTURA - 1, "Name: Scuba " + PALABRA);
//        actualizacionDelHUDbetha();
        StdDraw.text(ANCHO / 2, ALTURA - 1, "Location: " + mt);
        StdDraw.show();
    }
}
