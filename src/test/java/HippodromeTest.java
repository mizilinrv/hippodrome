import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void nullNameException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Hippodrome(null));
        assertEquals("Horses cannot be null.",e.getMessage());
    }
    void emptyListException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",e.getMessage());
    }
    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <=30; i++) {
            horses.add(new Horse("Test", i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void verifyMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <=50 ; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for(Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("horse1", 1,1);
        Horse horse2 = new Horse("horse2", 1,2);
        Horse horse3 = new Horse("horse3", 1,3);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));
        assertSame(horse3, hippodrome.getWinner());
    }
}