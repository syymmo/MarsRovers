package MarsRovers.models.field;

import MarsRovers.models.vehicle.Position;
import MarsRovers.models.vehicle.Rover;
import MarsRovers.repositories.RoversRepository;

import static MarsRovers.common.ExceptionMessages.INVALID_HEIGHT;
import static MarsRovers.common.ExceptionMessages.INVALID_WIDTH;

public class Plateau {
    public static int width;
    public static int height;
    private RoversRepository rovers;

    public Plateau(int width, int height, RoversRepository rovers) {
        setWidth(width);
        setHeight(height);
        this.rovers = rovers;
    }

    private void setWidth(int width) {
        if (width <= 0){
            throw new IllegalArgumentException(INVALID_WIDTH);
        }
        Plateau.width = width;
    }

    private void setHeight(int height) {
        if (height <= 0){
            throw new IllegalArgumentException(INVALID_HEIGHT);
        }
        Plateau.height = height;
    }

    public boolean isOccupied(Position position) {
        for (Rover rover : rovers.getModels()) {
            if (rover.checkPosition(position)){
                return true;
            }
        }
        return false;
    }
}
