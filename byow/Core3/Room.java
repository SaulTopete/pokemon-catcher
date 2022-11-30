package byow.Core3;


import byow.Core.RandomUtils;

import java.util.*;

public class Room {

    private int ancho;
    private int altura;
    private int numero;

//    private boolean fueVisitado = false;
    private Point<Integer, Integer> comienzo;

    public Room( Random rd, int mX, int mY, int numero) {
        int equisPotencial = byow.Core.RandomUtils.uniform(rd, 0, mX);
        int yGriegaPotencial = byow.Core.RandomUtils.uniform(rd, 0, mY);

        this.comienzo = new Point<>(equisPotencial, yGriegaPotencial);
        this.ancho = byow.Core.RandomUtils.uniform(rd, 6, 10);
        this.altura = RandomUtils.uniform(rd, 6, 10);
        this.numero = numero;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAltura() {
        return altura;
    }

    public int getEquisX() {
        return comienzo.getX();
    }

    public int getYgriegaY() {
        return  comienzo.getY();
    }

    public int getMiddleX() {
        int startX = getEquisX();
        int mitadX = startX + ancho / 2;
        return mitadX;
    }

    public int getMiddleY() {
        int startY = getYgriegaY();
        int mitadY = startY + altura / 2;
        return mitadY;

    }

    public int formulaDistancia(int x, int x1, int y, int y1) {
        int distancia = (int) Math.sqrt((y1 - y) *  (y1 * y) + (x1 - x) * (x1 - x));
        return distancia;
    }

    public int coneccionDeCuartos(Room cuarto) {

        return formulaDistancia(this.getEquisX(), cuarto.getEquisX()
                , this.getYgriegaY(), cuarto.getYgriegaY());
    }

}
