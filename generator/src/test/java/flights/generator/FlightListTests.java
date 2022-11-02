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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import flights.generator.FlightRest.FlightRequest;
import flights.generator.FlightRest.FlightRequestList;
import flights.generator.Flights.Flight;

@SpringBootTest
class FlightListTests {
    
    FlightRequest flightRequest = new FlightRequest(LocalDate.now(), "Madrid", "Lisbon");

    private static Stream<Arguments> flightRequestArgs() {
        LocalDate date = LocalDate.now().plusDays(7);

        return Stream.of(
          Arguments.of("Madrid", "Lisbon", date),
          Arguments.of("Sevilla","Dublin", date),
          Arguments.of("Dublin","Lisbon", date)
        );
    }

    @Test
    void testFlightRequestListCreation() {
        FlightRequestList frl = new FlightRequestList();
        assertTrue(frl instanceof FlightRequestList);
        //assertTrue(frl)
    }

    @ParameterizedTest
    @MethodSource("flightRequestArgs")
    void testAddFlightRequestList(String origin, String destination, LocalDate date) {

        //assertTrue(actualMessage.contains(expectedMessage));
    }

   

}
