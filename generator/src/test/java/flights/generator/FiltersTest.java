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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import flights.generator.FlightRest.FlightRequestListWeek;
import flights.generator.Flights.Filters;
import flights.generator.Flights.Flight;

@SpringBootTest
class FiltersTests {

    @Test
    void testFilterScales() {
        Map<String, String> filterOptions = new HashMap<>();
		filterOptions.put("scales", "2");

        FlightRequest flight = new FlightRequest(LocalDate.now(), "Madrid", "Rome");
        FlightRequestListWeek dayFlight = new FlightRequestListWeek(flight);

        Filters flightFilters = new Filters(filterOptions, dayFlight.getDayFlights());
        ArrayList<FlightRequest> temp = flightFilters.getFilteredFlights();
        boolean meetsCriteria = false;
        //temp.forEach((fr) -> meetsCriteria = fr.getFlightList().getScales()==dayFlight.getScales());
    
    }

    @Test
    void testFilterAirline() {
        Map<String, String> filterOptions = new HashMap<>();
		filterOptions.put("airline", "Emirates");

        FlightRequest flight = new FlightRequest(LocalDate.now(), "Madrid", "Rome");
        FlightRequestListWeek dayFlight = new FlightRequestListWeek(flight);

        Filters flightFilters = new Filters(filterOptions, dayFlight.getDayFlights());
    
    }

    @Test
    void testFilterLuggageFalse() {
        Map<String, String> filterOptions = new HashMap<>();
		filterOptions.put("luggage", "false");

        FlightRequest flight = new FlightRequest(LocalDate.now(), "Madrid", "Rome");
        FlightRequestListWeek dayFlight = new FlightRequestListWeek(flight);

        Filters flightFilters = new Filters(filterOptions, dayFlight.getDayFlights());
    
    }
    
    @Test
    void testFilterLuggageTrue() {
        Map<String, String> filterOptions = new HashMap<>();
		filterOptions.put("luggage", "true");

        FlightRequest flight = new FlightRequest(LocalDate.now(), "Madrid", "Rome");
        FlightRequestListWeek dayFlight = new FlightRequestListWeek(flight);

        Filters flightFilters = new Filters(filterOptions, dayFlight.getDayFlights());
    
    }

}
