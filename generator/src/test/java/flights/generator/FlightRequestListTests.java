package flights.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class FlightRequestListTests {

    // FlightRequest flightRequest = new FlightRequest(LocalDate.now(), "Madrid",
    // "Lisbon");

    private static Stream<Arguments> flightRequestArgs() {
        LocalDate date = LocalDate.now().plusDays(7);

        return Stream.of(
                Arguments.of("Madrid", "Lisbon", date),
                Arguments.of("Sevilla", "Dublin", date.plusDays(1)),
                Arguments.of("Dublin", "Lisbon", date.plusDays(2)));
    }

    private static Stream<Arguments> flightRequestArgsList() {
        String[] origins = { "Madrid", "Lisbon", "Sevilla", "Dublin" };
        String[] destinations = { "Lisbon", "Sevilla", "Dublin", "Madrid" };

        List<LocalDate> dates = new ArrayList<>();
        LocalDate date = LocalDate.now().plusDays(7);
        dates.add(date);
        dates.add(date.plusDays(1));
        dates.add(date.plusDays(2));
        dates.add(date.plusDays(3));

        return Stream.of(Arguments.of(origins, destinations, dates));
    }

    @Test
    void testFlightRequestListCreation() {
        FlightRequestList frl = new FlightRequestList();
        assertTrue(frl instanceof FlightRequestList);
        // assertTrue(frl)
    }

    @ParameterizedTest
    @MethodSource("flightRequestArgs")
    void testAddFlightRequestList(String origin, String destination, LocalDate date) {
        FlightRequestList frl = new FlightRequestList();
        int oldSize = frl.size();
        FlightRequest flightRequest = new FlightRequest(date, origin, destination);
        frl.addFlightRequest(flightRequest);
        FlightRequest added = frl.getAllRequests().get(oldSize);

        assertAll("Check flightRequest added correctly",
                () -> assertEquals(frl.size(), oldSize + 1),
                () -> assertEquals(added.getDestination(), destination),
                () -> assertEquals(added.getOrigin(), origin));
    }

    @ParameterizedTest
    @MethodSource("flightRequestArgsList")
    void testAddMultipleFlightRequestList(String[] origins, String[] destinations, List<LocalDate> datesList) {
        FlightRequestList frl = new FlightRequestList();
        int numArgs = origins.length;

        boolean allAddedCorrectly = false;

        for (int i = 0; i < numArgs; i++) {
            int oldSize = frl.size();
            String origin = origins[i];
            String destination = destinations[i];
            FlightRequest flightRequest = new FlightRequest(datesList.get(i), origins[i], destinations[i]);
            frl.addFlightRequest(flightRequest);

            FlightRequest added = frl.getAllRequests().get(oldSize);
            allAddedCorrectly = (frl.size() == oldSize + 1) &&
                    added.getOrigin().equals(origin) &&
                    added.getDestination().equals(destination);
                    //System.out.println("Iteration: "+ (i+1));
        }
        assertTrue(allAddedCorrectly);
    }

}
