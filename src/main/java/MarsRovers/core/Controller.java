package MarsRovers.core;

public interface Controller {
    String createPlateau(int width, int height);

    String addRover(int x, int y, String orientation, String commands);

    String processCommands();

    String report();
}
