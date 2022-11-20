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
        this.ancho = RandomUtils.uniform(rd, 8, 12);
        this.altura = RandomUtils.uniform(rd, 8, 12);
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
        return (startX + ancho / 2);
    }

    public int getMiddleY() {
        int startY = getYgriegaY();
        return (startY + altura / 2);

    }

    public int formulaDistancia(int x, int x1, int y, int y1) {
        int distancia = (int) Math.sqrt((y1 - y) *  (y1 * y) + (x1 - x) * (x1 - x));
        return distancia;
    }

    public int coneccionDeCuartos(Cuartos cuarto) {
        int equis = this.getEquisX();
        int ygriega = this.getYgriegaY();
        int equis2 = this.getEquisX();
        int ygriega2 = this.getYgriegaY();

        return formulaDistancia(equis, equis2, ygriega, ygriega2);
    }
}