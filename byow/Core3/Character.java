/*
package byow.Core3;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TETile;

public class Character {
    private TETile[][] tiles = null;
    private int startX = 0;
    private int startY = 0;
    //private TETile[][] tiles;



    public Character(int startX, int startY, TETile[][] tiles) {
        this.startX = startX;
        this.startY = startY;
        this.tiles = tiles;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public TETile[][] getTiles() {
        return tiles;
    }

    public void updateX(int x) {
        this.startX = x;

    }

    public void updateY(int y) {
        this.startY = y;

    }

    public void updateTiles(TETile[][] tiles) {
        this.tiles = tiles;

    }


    */
/* public void moveUp() {
        if (tiles[startX][startY + 1].description().equals("floor")) {
            //we can move up
            System.out.println("reached");

        }

        if (tiles[startX][startY + 1].description().equals("gold")) {
            //end game

        }

        //we will want to return current position?
    }



    public String moveCharacter() { //we need the characters current coords

        InputSource inputSource = new KeyboardInputSource();

        while (true) {
            char c = inputSource.getNextKey();
            if (c == 'W') {
                moveUp();   //up
                return String.valueOf(c);
            }
            if (c == 'A') { //left
                return String.valueOf(c);
            }
            if (c == 'S') { //down
                return String.valueOf(c);
            }
            if (c == 'D') { //right
                return String.valueOf(c);
            }
        }
    }*//*







        //up, left, down, and right using the W, A, S, and D

        //based on keyboard commands, the avatar will move
        //they cannot go out of bounds or out of the hallways
        //they should be placed at a location (last room perhaps)

        //later will make copy of tileset to function as blotted out area

}
*/
