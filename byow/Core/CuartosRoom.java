package byow.Core;

import java.util.Random;

public class CuartosRoom {
    private PuntosCardinales<Integer, Integer> comienzo;
    private int width;
    private int height;
    private int numero;
    private boolean fueVisitado = false;

    public CuartosRoom(Random rd, int mX, int mY, int numero){
        int EquisPotencial = RandomUtils.uniform(rd, 0, mX);
        int YgriegaPotencial = RandomUtils.uniform(rd, 0, mY);

        this.comienzo = new PuntosCardinales<>(EquisPotencial, YgriegaPotencial);
        this.width = RandomUtils.uniform(rd, 8, 12);
        this.height = RandomUtils.uniform(rd, 8, 12);
        this.numero = numero;
    }
}
