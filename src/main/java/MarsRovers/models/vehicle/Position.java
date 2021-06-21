package MarsRovers.models.vehicle;

import MarsRovers.enums.Orientation;
import MarsRovers.models.field.Plateau;

import static MarsRovers.common.ExceptionMessages.INVALID_COORDINATE_X;
import static MarsRovers.common.ExceptionMessages.INVALID_COORDINATE_Y;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        if (x < 0 || x > Plateau.width) {
            throw new IllegalArgumentException(INVALID_COORDINATE_X);
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0 || y > Plateau.height){
            throw new IllegalArgumentException(INVALID_COORDINATE_Y);
        }
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInsidePlateau() {
        return x >= 0 && x <= Plateau.width
                && y >= 0 && y <= Plateau.height;
    }

    public boolean isOutsidePlateau(){
        return !isInsidePlateau();
    }

    public Position changePosition(Orientation orientation){
        return switch (orientation) {
            case N -> new Position(x, y + 1);
            case W -> new Position(x - 1, y);
            case S -> new Position(x, y - 1);
            case E -> new Position(x + 1, y);
        };
    }

    @Override
    public String toString() {
        return "MarsRovers.models.vehicle.Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
