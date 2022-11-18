package byow.Core;

import java.awt.*;
 /*
 @Source:
 @Source:
  */

public class PuntosCardinales<Equis, Igriega> {
    private int x, y;

    public PuntosCardinales(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object objeto) {
        if (!(objeto instanceof Point)) {
            return false;
        }
        PuntosCardinales pc = (PuntosCardinales) objeto;
        return x == (pc.x) && y == (pc.y);
    }
}
