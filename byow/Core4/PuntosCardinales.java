package byow.Core4;

/** Inspired by:
 * @Source: https://teamfarce.github.io/Lorem-Ipsum/javadoc/me/lihq/game/models/Vector2Int.html
 * @Source: https://stackabuse.com/javas-object-methods-equals-object/*/

public class PuntosCardinales<Equis, Ygriega> {

    private Equis x;
    private Ygriega y;

    public PuntosCardinales(Equis x, Ygriega y) {
        this.x = x;
        this.y = y;
    }

    public Equis getX() {
        return x;
    }

    public Ygriega getY() {
        return y;
    }

    public void setX(Equis x) {
        this.x = x;
    }

    public void setY(Ygriega y) {
        this.y = y;
    }

    public boolean equals(Object objeto) {
        if (!(objeto instanceof PuntosCardinales)) {
            return false;
        }
        PuntosCardinales pc = (PuntosCardinales) objeto;
        return x.equals(pc.x)
                && y.equals(pc.y);
    }
}

