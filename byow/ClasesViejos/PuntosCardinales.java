package byow.ClasesViejos;

/** Inspired by:
 * @Source: https://teamfarce.github.io/Lorem-Ipsum/javadoc/me/lihq/game/models/Vector2Int.html
 * @Source: https://stackabuse.com/javas-object-methods-equals-object/*/

public class PuntosCardinales<Equis, Ygriega> {

    private int x;
    private int y;

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
        if (!(objeto instanceof PuntosCardinales)) {
            return false;
        }
        PuntosCardinales pc = (PuntosCardinales) objeto;
        return x == (pc.x)
                && y == (pc.y);
    }
}

