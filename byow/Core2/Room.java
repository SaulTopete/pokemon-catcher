package byow.Core2;

import java.util.*;

public class Room {
    private Point<Integer, Integer> start;
    private int width;
    private int height;
    private int number;


    private boolean isVisited = false;

    public Room(Random random, int maxX, int maxY, int number) {
        int potentialX = RandomUtils.uniform(random, 0, maxX);
        int potentialY = RandomUtils.uniform(random, 0, maxY);

        this.start = new Point(potentialX, potentialY);
        this.width = RandomUtils.uniform(random, 6, 10);
        this.height = RandomUtils.uniform(random, 6, 10);
        this.number = number;
    }


    public int getMiddleX() {
        int startX = getX();
        return (startX + width / 2);
    }

    public int getMiddleY() {
        int startY = getY();
        return (startY + height / 2);

    }

    public int getX() {
        return start.getX();
    }

    public int getY() {
        return start.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getPoint() {
        return start;
    }

    public int getNumber() {
        return number;
    }

    public int connectRoom(Room room) {
        //calculate distance between this and room
        int distance;
        int x1 = this.getX();
        int y1 = this.getY();
        int x2 = room.getX();
        int y2 = room.getY();

        distance = (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

        return distance;

    }



}
