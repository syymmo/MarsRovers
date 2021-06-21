package MarsRovers.models.vehicle;

import MarsRovers.enums.Orientation;
import MarsRovers.enums.RoverCommand;
import MarsRovers.models.field.Plateau;

import java.util.ArrayDeque;

import static MarsRovers.common.ExceptionMessages.*;

public class Rover {
    private static int roversCount = 0;

    private String name;
    private ArrayDeque<Character> commands;
    private Plateau plateau;
    private Position position;
    private Orientation orientation;


    public Rover(Plateau plateau, int coordinateX, int coordinateY, String orientation, String commands) {
        this.name = "Rover#" + roversCount++;
        this.commands = new ArrayDeque<>();
        this.plateau = plateau;
        this.orientation = validateOrientation(orientation);
        uploadCommands(commands);
        landRover(coordinateX, coordinateY);
    }

    private Orientation validateOrientation(String ori) {
        if (!Orientation.validate(ori)){
            throw new IllegalArgumentException(String.format(INVALID_ORIENTATION, name));
        }
        return Orientation.valueOf(ori);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private void uploadCommands(String commandsToAdd) {
        ArrayDeque<Character> validCommands = new ArrayDeque<>();

        for (char cmd : commandsToAdd.toCharArray()) {
            if (!RoverCommand.contains(String.valueOf(cmd))){
                throw new IllegalArgumentException(INVALID_COMMAND);
            }
            validCommands.offer(cmd);
        }
        //If the loop is completed successfully, we add all cmd to 'commands'
        commands = validCommands;
    }

    private void landRover(int coordinateX, int coordinateY) {
        Position positionToBegin = new Position(coordinateX, coordinateY);

        //Checking if the position of the MarsRovers.models.vehicle.Rover are inside the MarsRovers.models.field.Plateau
        if (positionToBegin.isOutsidePlateau()) {
            throw new IllegalArgumentException(INVALID_POSITION_ROVER);
        }

        //Checking if there is another rover at that position
        if (plateau.isOccupied(positionToBegin)) {
            throw new IllegalArgumentException(ALREADY_OCCUPIED);
        }

        this.position = positionToBegin;
    }

    public boolean checkPosition(Position other) {
        return this.position.getX() == other.getX()
                && this.position.getY() == other.getY();
    }

    public void executeCommands() {
        while (!commands.isEmpty()){
            Character command = commands.poll();
            switch (command) {
                case 'L':
                case 'R':
                    rotate(command);
                    break;
                case 'M':
                    move();
                    break;
                default:
                    throw new IllegalArgumentException(INVALID_COMMAND);
            }
        }
    }

    private void move() {
        Position newPosition = this.position.changePosition(orientation);

        if (newPosition.isOutsidePlateau()){
            throw new IllegalArgumentException(CANNOT_GO_OUTSIDE_PLATEAU);
        }
        if (plateau.isOccupied(newPosition)){
            throw new IllegalArgumentException(String.format(ALREADY_OCCUPIED, newPosition.getX(), newPosition.getY()));
        }
        position = newPosition;
    }

    private void rotate(Character command) {
        if (command.equals('L')) {
            switch (orientation) {
                case N -> orientation = Orientation.W;
                case W -> orientation = Orientation.S;
                case S -> orientation = Orientation.E;
                case E -> orientation = Orientation.N;
            }
        } else {
            switch (orientation) {
                case N:
                    orientation = Orientation.E;
                    break;
                case W:
                    orientation = Orientation.N;
                    break;
                case S:
                    orientation = Orientation.W;
                    break;
                case E:
                    orientation = Orientation.S;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%n-  %s%n-- Location %d %d %s",
                this.name,
                this.position.getX(),
                this.position.getY(),
                this.orientation.name());
    }
}
