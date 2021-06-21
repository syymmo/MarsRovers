package MarsRovers.core;


import MarsRovers.enums.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static MarsRovers.common.ExceptionMessages.INVALID_COMMANDS_FORMAT;
import static MarsRovers.common.OutputMessages.SUCCESSFULLY_MISSION;

public class Engine implements Runnable {
    private ControllerImpl controller;
    private BufferedReader reader;

    public Engine() {
        this.controller = new ControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals(Command.Exit.name())) {
                    System.out.println(SUCCESSFULLY_MISSION);
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String[] input = reader.readLine().split("\\s");

        Command command = Command.valueOf(input[0]);

        String[] data = Arrays.stream(input).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case CreatePlateau:
                result = this.createPlateau(data);
                break;
            case AddRover:
                result = this.addRover(data);
                break;
            case ProcessCommands:
                result = this.processCommands();
                break;
            case Report:
                result = this.report();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }

        return result;
    }

    private String createPlateau(String[] data) {
        //•	CreatePlateau {width} {height}
        int width = Integer.parseInt(data[0]);
        int height = Integer.parseInt(data[1]);
        return controller.createPlateau(width, height);
    }

    private String addRover(String[] data) {
        //•	AddRover {coordinateX} {coordinateY} {Orientation} {Commands}
        if (data.length != 4){
            throw new IllegalArgumentException(INVALID_COMMANDS_FORMAT);
        }
        int coordinateX = Integer.parseInt(data[0]);
        int coordinateY = Integer.parseInt(data[1]);
        String orientation = data[2];
        String commands = data[3];
        return controller.addRover(coordinateX, coordinateY, orientation, commands);
    }

    private String processCommands() {
        //•	ProcessCommands
        return controller.processCommands();
    }

    private String report() {
        return controller.report();
    }
}