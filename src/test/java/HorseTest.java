import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    public Horse horse;
    @BeforeEach
    public void init() {
        horse = new Horse("Test", 1, 2);
    }
    @Test
    void nullNameException() {
       IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse(null, 1,1));
        assertEquals("Name cannot be null.",e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"","   ","\t","\n"})
    void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Horse(name, 1,1));
        assertEquals("Name cannot be blank.",e.getMessage());
    }
    @Test
    void negativeArgumentException() {
        IllegalArgumentException second = assertThrows(IllegalArgumentException.class,() -> new Horse("Test", -1,1));
        assertEquals("Speed cannot be negative.",second.getMessage());
        IllegalArgumentException third = assertThrows(IllegalArgumentException.class,() -> new Horse("Test", 1,-1));
        assertEquals("Distance cannot be negative.",third.getMessage());
    }
    @Test
    void getName() {
        assertEquals("Test", horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(2, horse.getDistance());
    }
    @Test
    void zeroDistanceByDefault() {
        Horse horseNotDistance = new Horse("Test", 1);
        assertEquals(0, horseNotDistance.getDistance());
    }

    @Test
    void moveUsesGetRandom() {
    try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
        horse.move();
        mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.4,0.6,0.8})
    void moveSetDistance(double random) {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            double distance = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(distance, horse.getDistance());
        }
    }

}