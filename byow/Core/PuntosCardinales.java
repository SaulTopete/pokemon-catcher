package byow.Core;

/**This code was inspired by:
 * @Source: https://teamfarce.github.io/Lorem-Ipsum/javadoc/me/lihq/game/models/Vector2Int.html
 * @Source: https://stackabuse.com/javas-object-methods-equals-object/*/


public class PuntosCardinales<Equis, Ygriega> {

    private Equis x;
    private Ygriega y;

    public PuntosCardinales(Equis xX, Ygriega yY) {
        x = xX;
        y = yY;
    }

    public Equis getX() {
        return x;
    }

    public Ygriega getY() {
        return y;
    }

    public void setX(Equis xX) {
        x= xX;
    }

    public void setY(Ygriega yY) {
        y = yY;
    }

    public boolean equals(Object objeto) {
        if (!(objeto instanceof PuntosCardinales)) {
            return false;
        }
        PuntosCardinales pc = (PuntosCardinales) objeto;
        return x.equals(pc.x) && y.equals(pc.y);
    }

}
