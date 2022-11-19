package byow.Core;

/** Inspired by:
 * @Source: https://stackoverflow.com/questions/4777622/creating-a-list-of-pairs-in-java*/

public class Point<X, Y> {
    private X x;
    private Y y;

    public Point(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return x.equals(p.x) && y.equals(p.y);
    }

    /**
     * Inspired by: https://stackoverflow.com/questions/2066917
     *              /overriding-equals-hashcode-in-sub-classes-considering-super-fields
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + (this.x != null ? this.x.hashCode() : 0);
        hash = 89 * hash + (this.y != null ? this.y.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
