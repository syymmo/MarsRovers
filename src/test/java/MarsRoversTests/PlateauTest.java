package MarsRoversTests;

import MarsRovers.models.field.Plateau;
import MarsRovers.repositories.RoversRepository;
import org.junit.Test;

public class PlateauTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowException_WhenWidthLessOrEqualToZero(){
        new Plateau(0, 5, new RoversRepository());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_ShouldThrowException_WhenHeightLessOrEqualToZero(){
        new Plateau(5, -1, new RoversRepository());
    }

    @Test
    public void testConstructor_ShouldCreatePlateau_WhenValidWidthHeight(){
        new Plateau(5, 5, new RoversRepository());
    }
}
