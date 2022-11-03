package flights.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;
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
import flights.generator.FlightRest.FlightsController;
import flights.generator.Flights.Flight;

@SpringBootTest
class FlightsControllerTests {

    private static Stream<Arguments> flightRequestStream() {
        FlightRequest temp1 = new FlightRequest(LocalDate.now(), "Madrid", "Rome");
        FlightRequest temp2 = new FlightRequest(LocalDate.now().plusDays(1), "Rome", "Dublin");
        FlightRequest temp3 = new FlightRequest(LocalDate.now().plusDays(2), "Dublin", "Madrid");
        return Stream.of(
                Arguments.of(temp1),
                Arguments.of(temp2),
                Arguments.of(temp3));
    }

    // Should create an object of the FlightsController class
    @Test
    void testFlightControllerCreation() {
        FlightsController flc = new FlightsController();
        assertTrue(flc instanceof FlightsController);
    }

    // GET: Should return a list of destinations in a string
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    void testGetDestination(int origin) {
        FlightsController flc = new FlightsController();
        List<String> response = flc.getDestination(origin);
        assertAll("Check if response is iterable of destinations(Strings)",
                () -> assertNotNull(response),
                () -> assertTrue(response instanceof Iterable<String>));
    }

    //POST: Should return a URI with the new
    @ParameterizedTest
    @MethodSource("flightRequestStream")
    void testCreateFlightRequest(FlightRequest inputFlightRequest) throws URISyntaxException  {//
        FlightsController flc = new FlightsController();
        flc.createFlightRequest(inputFlightRequest);
        FlightRequest saved = flc.getFlightRepository().getFlightRequest(flc.getLastFlightRequestId());
        assertAll("Check flightRequest created correctly",
                () -> assertTrue(inputFlightRequest.getDestination().equals(saved.getDestination())),
                () -> assertTrue(inputFlightRequest.getOrigin().equals(saved.getOrigin())));
    }

    // @ParameterizedTest
    // @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    // void testGetFlight(Long id) {
    //     FlightsController flc = new FlightsController();
    //     List<String> destinations = flc.getDestination(origin);
    //     ArrayList<FlightRequest> temp = flc.getFlightRepository().getAllRequests();
    //     FlightRequest flightRequest = temp.get(temp.size()-1);
    //     assertAll("Check flightRequest added correctly",
    //             () -> assertNotNull(destinations),
    //             () -> assertTrue(destinations instanceof Iterable<String>));
    // }

}
