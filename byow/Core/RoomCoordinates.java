package byow.Core;

public class RoomCoordinates {
    private int startX;
    private int startY;
    private final int dimmX;
    private int dimmY;
    private int endPointX;
    private int endPointY;

    public RoomCoordinates(int startX, int startY, int dimmX, int dimmY) {
        this.startX = startX;
        this.startY = startY;
        this.dimmX = dimmX;
        this.dimmY = dimmY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getMidDimmX() {
        return getStartX() + (dimmX/2);
    }

//    public void setDimmX(int dimmX) {
//        this.dimmX = dimmX;
//    }

    public int getMidDimmY() {
        return getStartY() + (dimmY/2);
    }

    public void setDimmY(int dimmY) {
        this.dimmY = dimmY;
    }

    public int getEndPointX() {
        return startX + dimmX;
    }

    public int getEndPointY() {
        return startY + dimmY;
    }

    public void setEndPointX(int value) {
        this.endPointX = value;
    }

    public void setEndPointY(int value) {
        this.endPointY = value;
    }
}
