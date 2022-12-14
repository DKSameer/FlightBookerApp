package flights.prices.PricesRest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/price")
@CrossOrigin(origins="*")
public class PricesController {
	
	public PricesController() {
		//PriceFactorsRepository = new PriceFactorsRepository();
	}
	
	@PostMapping
    public ResponseEntity getFinalPrice(@RequestBody PriceCalculator calculatePrice) throws URISyntaxException {//
		PriceCalculator finalPrice = new PriceCalculator(calculatePrice.getTotalPassengers(),calculatePrice.getKids(),calculatePrice.getBabies(),calculatePrice.getLuggage(),calculatePrice.getBasePrice());
        return ResponseEntity.created(new URI("/price")).body(finalPrice.getFinalPrice());
		//return ResponseEntity.created(new URI("/flights/day/" + dayFlight.getId())).body(dayFlight);
    }
	
	// @PostMapping
    // public ResponseEntity updateFlightPrices(@RequestBody PriceCalculator priceRequest) throws URISyntaxException {//
	// 	PriceCalculator savedPF = new PriceCalculator(flight.getDate(),flight.getOrigin(),flight.getDestination());
	// 	savedFlight.setId((long)(Math.random() * 1000));
	// 	flightRepository.addFlightRequest(savedFlight);
    //     return ResponseEntity.created(new URI("/flights/" + savedFlight.getId())).body(savedFlight);
    // }
	
	// @PostMapping("/day")
    // public ResponseEntity createFlightRequestDay(@RequestBody FlightRequest flight) throws URISyntaxException {//
	// 	FlightRequestListDay dayFlight = new FlightRequestListDay(flight);
	// 	flightDayRepository.addFlightRequestListDay(dayFlight);
    //     return ResponseEntity.created(new URI("/flights/day/" + dayFlight.getId())).body(dayFlight.getDayFlights());
    // }
}