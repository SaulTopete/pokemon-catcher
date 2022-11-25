package byow.Core;

public enum directionMovement {
    NORTH(1), SOUTH(1), EAST(1), WEST(1);

    int pos;

    directionMovement(int pos) {
        this.pos = pos;
    }
}
