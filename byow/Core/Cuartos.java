package byow.Core;

import java.util.Random;

public class Cuartos {

    private int ancho;
    private int altura;
    private int numero;

    private boolean fueVisitado = false;
    private PuntosCardinales<Integer, Integer> comienzo;

    public Cuartos(int mX, int mY, int numero, Random rd) {
        int equisPotencial = RandomUtils.uniform(rd, 0, mX);
        int yGriegaPotencial = RandomUtils.uniform(rd, 0, mY);

        this.comienzo = new PuntosCardinales<>(equisPotencial, yGriegaPotencial);
        this.ancho = RandomUtils.uniform(rd, 6, 10);
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

    public int coneccionDeCuartos(Cuartos cuarto) {
//        int distance;
        int equis = this.getEquisX();
        int ygriega = this.getYgriegaY();
        int equis2 = cuarto.getEquisX();
        int ygriega2 = cuarto.getYgriegaY();

//        distance = (int) Math.sqrt((ygriega2 - ygriega) * (ygriega2 - ygriega) + (equis2 - equis) * (equis2 - equis));

        return formulaDistancia(equis, equis2, ygriega, ygriega2);
//        return distance;
    }
}