package MarsRovers.core;

import MarsRovers.models.field.Plateau;
import MarsRovers.models.vehicle.Rover;
import MarsRovers.repositories.RoversRepository;

import static MarsRovers.common.ExceptionMessages.*;
import static MarsRovers.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Plateau plateau;
    private RoversRepository rovers;


    public ControllerImpl() {
        this.rovers = new RoversRepository();
    }

    @Override
    public String createPlateau(int width, int height) {
        //Checking if there is Plateau with valid size.
        if (Plateau.width != 0 && Plateau.height != 0){
            throw new IllegalArgumentException(PLATEAU_EXISTS);
        }

        //If Plateau is not created yet, we create an instance with corresponding width and height.
        this.plateau = new Plateau(width, height, rovers);

        return String.format(SUCCESSFULLY_CREATED_PLATEAU, width, height);
    }

    @Override
    public String addRover(int x, int y, String orientation, String commands){
        if (plateau == null){
            throw new NullPointerException(PLATEAU_NOT_EXISTS);
        }

        Rover rover = new Rover(plateau, x, y, orientation, commands);

        this.rovers.add(rover);

        return String.format(SUCCESSFULLY_ADDED_ROVER, rover.getName());
    }

    @Override
    public String processCommands() {
        if (rovers.getModels().isEmpty()){
            throw new IllegalArgumentException(CANNOT_FIND_ROVERS);
        }
        rovers.getModels()
                .forEach(Rover::executeCommands);
        return SUCCESSFULLY_PROCESSED_COMMANDS;
    }

    @Override
    public String report() {
        if (rovers.getModels().isEmpty()){
            throw new IllegalArgumentException(CANNOT_FIND_ROVERS);
        }

        StringBuilder out = new StringBuilder();
        for (Rover rover : rovers.getModels()) {
            out.append(rover.toString());
        }

        return out.toString();
    }
}


