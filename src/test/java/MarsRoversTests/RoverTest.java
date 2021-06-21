package MarsRoversTests;

import MarsRovers.core.Controller;
import MarsRovers.core.ControllerImpl;
import MarsRovers.enums.Orientation;
import MarsRovers.models.field.Plateau;
import MarsRovers.models.vehicle.Position;
import MarsRovers.models.vehicle.Rover;
import MarsRovers.repositories.RoversRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoverTest {
    private Plateau plateau;

    @Before
    public void setUp(){
        plateau = new Plateau(5, 5, new RoversRepository());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowException_WhenNotValidOrientation() {
        new Rover(plateau, 3, 3, "Q", "MMRL");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowException_WhenNotValidCommands() {
        new Rover(plateau, 3, 3, "N", "MQRML");
    }

    @Test
    public void testCheckPosition_ShouldReturnFalse_WhenDiffPositions(){
        Rover rover = new Rover(plateau, 3, 3, "N", "MLRML");
        boolean result = rover.checkPosition(new Position(1, 1));
        assertFalse(result);
    }

    @Test
    public void testCheckPosition_ShouldReturnTrue_WhenSamePositions(){
        Rover rover = new Rover(plateau, 3, 3, "N", "MLRML");
        boolean result = rover.checkPosition(new Position(3, 3));
        assertTrue(result);
    }

    @Test
    public void testExecuteCommands_ShouldCallRotate_WhenCommandL(){
        Rover rover = new Rover(plateau, 3, 3, "N", "L");

        rover.executeCommands();

        Orientation actual = rover.getOrientation();
        Orientation expected = Orientation.W;

        assertEquals(expected, actual);
    }

    @Test
    public void testExecuteCommands_ShouldCallRotate_WhenCommandR(){
        Rover rover = new Rover(plateau, 3, 3, "N", "R");

        rover.executeCommands();

        Orientation actual = rover.getOrientation();
        Orientation expected = Orientation.E;

        assertEquals(expected, actual);
    }

    @Test
    public void testExecuteCommands_ShouldCallMove_WhenCommandM(){
        Rover rover = new Rover(plateau, 3, 3, "N", "M");

        rover.executeCommands();

        int actualX = rover.getPosition().getX();
        int actualY = rover.getPosition().getY();

        int expectedX = 3;
        int expectedY = 4;

        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteCommands_ShouldThrowException_WhenCommandMAndPositionOccupied(){
        Controller controller = new ControllerImpl();
        controller.createPlateau(5, 5);
        controller.addRover(3, 4, "W", "L");
        Rover rover = new Rover(null, 3, 3, "N", "M");

        rover.executeCommands();

    }
}
