package MarsRoversTests;

import MarsRovers.core.Controller;
import MarsRovers.core.ControllerImpl;
import MarsRovers.models.field.Plateau;
import MarsRovers.models.vehicle.Rover;
import MarsRovers.repositories.RoversRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ControllerImplTest {
//    private static final int VALID_PLATEAU_WIDTH = 10;
//    private static final int VALID_PLATEAU_HEIGHT = 10;
//
//    private static final int VALID_ROVER_X = 3;
//    private static final int VALID_ROVER_Y = 3;
//    private static final String VALID_ROVER_ORIENTATION = "N";
//    private static final String VALID_ROVER_COMMAND = "RRMML";

    private Controller controller;
    private Plateau plateau;
    private RoversRepository rovers;

    @Before
    public void setUp(){
        controller = new ControllerImpl();
//        rovers = new RoversRepository();
//        plateau = new Plateau(3, 3, rovers);
    }

    //Testing createPlateau(width, height)
    @Test
    public void testCreatePlateau_ShouldCreatePlateau_WhenValidWidthHeight(){
        String actualResult = controller.createPlateau(5, 5);
        String expectedResult = String.format("Successfully created Plateau with width: %d, height: %d.",
                5, 5);

        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlateau_ShouldThrowException_WhenPlateauExist(){
        //We create a Plateau
        controller.createPlateau(5, 5);
        //When we try to create again, exception is thrown
        controller.createPlateau(4, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlateau_ShouldThrowException_WhenWidthLessOrEqualToZero(){
        controller.createPlateau(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlateau_ShouldThrowException_WhenHeightLessOrEqualToZero(){
        controller.createPlateau(5, -1);
    }

    //Testing addRover(x, y, orientation, commands)
    @Test(expected = NullPointerException.class)
    public void testAddRover_ShouldThrowException_WhenPlateaNotExist(){
        controller.addRover(2, 2, "N", "MRLMR");
    }
}
