package byow.Core;

import java.util.Random;

public class Cuartos {

    private int ancho;
    private int altura;
    private int numero;

    private PuntosCardinales<Integer, Integer> comienzo;
    private boolean fueVisitado = false;

    public Cuartos(Random rd, int mX, int mY, int numero) {
        int EquisPotencial = RandomUtils.uniform(rd, 0, mX);
        int yGriegaPotencial = RandomUtils.uniform(rd, 0, mY);

        this.comienzo = new PuntosCardinales<>(EquisPotencial, yGriegaPotencial);
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

    public int getNumero() {
        return numero;
    }

    public PuntosCardinales getPartida() {
        return comienzo;
    }

    public int getEquis() {
        return comienzo.getX();
    }

    public int getYgriegaY() {
        return comienzo.getY();
    }

    //cambia este metodo
    public int connectRoom(Cuartos cuarto) {
        int distancia;
        int x1 = this.getEquis();
        int y1 = this.getYgriegaY();
        int x2 = cuarto.getEquis();
        int y2 = cuarto.getYgriegaY();

        //distance equation method
        distancia = (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        return distancia;
    }
}
