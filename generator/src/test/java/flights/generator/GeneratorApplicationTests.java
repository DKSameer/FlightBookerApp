package flights.generator;




import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import flights.generator.Flights.Destinations;


@SpringBootTest
class GeneratorApplicationTests {
	
	public static final int SAO_PAULO = 0;
	public static final int SEVILLA = 1;
	public static final int MADRID = 2;
	public static final int DUBLIN = 3;
	public static final int LISBON = 4;
	
	LocalDate date = LocalDate.of(2020, 10, 31);
	LocalTime time = LocalTime.of(10,45);
	boolean luggageAllowed = false;
	int Baseprice;
	String airline;
	
	@Test
	void testRandomDestinations() {
		Destinations dest = new Destinations();
		System.out.println(dest.returnRandomDestinations(SAO_PAULO).toString());
		assertTrue(dest.returnRandomDestinations(SAO_PAULO).size() >=1);
	}
	
	
//	@ParameterizedTest
//	@ValueSource(booleans = {true,false})
//	void testFlightLuggage(boolean luggageAvailable) {
//		Flight flight = new Flight(date,time,luggageAvailable,750,"Delta");
//		assertEquals(flight.getLuggageAllowed(), luggageAvailable);
//	}
//	
//	@ParameterizedTest
//	@ValueSource(doubles = {100.00,200.00,999.99})
//	void testFlightPrice(double price) {
//		Flight flight = new Flight(date,time,luggageAllowed,price,"Delta");
//		assertEquals(flight.getPrice(), price);
//	}
//	
//	@ParameterizedTest
//	@ValueSource(strings = {"Delta","Ryanair","Turkish Airlines"})
//	void testFlightAirline(double price) {
//		Flight flight = new Flight(date,time,luggageAllowed,price,"Delta");
//		assertEquals(flight.getPrice(), price);
//	}
}
