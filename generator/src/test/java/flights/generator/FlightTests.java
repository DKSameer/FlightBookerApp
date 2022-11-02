package flights.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import flights.generator.Flights.Flight;

@SpringBootTest
class FlightTests {

    private static Stream<Arguments> nullFlightCreationArgs() {
        LocalDate date = LocalDate.now().plusDays(7);
        return Stream.of(
          Arguments.of(null, "Madrid", date),
          Arguments.of("Sao Paulo",null, date),
          Arguments.of("Sao Paulo","Madrid", null)
        );
    }

    private static Stream<Arguments> normalFlightCreationArgs() {
        LocalDate dateTemp = LocalDate.now().plusDays(7);
        List<LocalDate> dates = new ArrayList<LocalDate>();
        for(int i = 0; i<10; i++){
            dates.add(dateTemp.plusDays(i).plusMonths(i).plusWeeks(i));
        }
        return Stream.of(
          Arguments.of("Sao Paulo", "Sevilla", dates.get(0)),
          Arguments.of("Sao Paulo", "Madrid", dates.get(1)),
          Arguments.of("Sao Paulo", "Dublin", dates.get(2)),
          Arguments.of("Sao Paulo", "Lisbon", dates.get(3)),

          Arguments.of("Sevilla", "Madrid", dates.get(4)),
          Arguments.of("Sevilla", "Dublin", dates.get(5)),
          Arguments.of("Sevilla", "Lisbon", dates.get(6)),

          Arguments.of("Madrid", "Dublin", dates.get(7)),
          Arguments.of("Madrid", "Lisbon", dates.get(8)),

          Arguments.of("Dublin", "Lisbon", dates.get(9))
        );
    }

    @ParameterizedTest
    @MethodSource("nullFlightCreationArgs")
    void testFlightCreationNullArgs(String origin, String destination, LocalDate date) {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
             new Flight(origin, destination, date);
        });
    
        String expectedMessage = "Flight constructor cannot contain null arguments";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("normalFlightCreationArgs")
    void testFlightCreationNormalArgs(String origin, String destination, LocalDate date) {

        Flight flight = new Flight(origin, destination, date);
        assertTrue(flight instanceof Flight);
    }

    

}
