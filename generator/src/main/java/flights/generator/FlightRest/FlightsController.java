package flights.generator.FlightRest;


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
@RequestMapping("/destination")
@CrossOrigin(origins="*")
public class FlightsController {
	
	private final FlightRequestList flightRepository;
	private final FlightRequestListDayStorage flightDayRepository;
	
	public FlightsController() {
		flightRepository = new FlightRequestList();
		flightDayRepository = new FlightRequestListDayStorage();
	}
    
	public static int value=1;
	
	@GetMapping("/getdest/{origin}")
    public List<String> getDestination(@PathVariable int origin) {
		
		RestDestinations dest = new RestDestinations();
        return dest.initializeDestinations(origin);
    }
	
	@GetMapping("/flights/{id}")
    public FlightRequest getFlight(@PathVariable long id) {
        return flightRepository.getFlightRequest(id);
    }
	
	@GetMapping("/day/{id}")
    public FlightRequestListDay getFlightDay(@PathVariable long id) {
        return flightDayRepository.getFlightRequestDay(id);
    }
	
//	@GetMapping("/flights/{id}")
//    public FlightRequest getFlightInfo(@PathVariable long id) {
//        return flightRepository.getFlightRequest(id).;
//    }
	
	@PostMapping
    public ResponseEntity createFlightRequest(@RequestBody FlightRequest flight) throws URISyntaxException {//
		FlightRequest savedFlight = new FlightRequest(flight.getDate(),flight.getOrigin(),flight.getDestination());
		savedFlight.setId((long)(Math.random() * 1000));
		flightRepository.addFlightRequest(savedFlight);
        return ResponseEntity.created(new URI("/flights/" + savedFlight.getId())).body(savedFlight);
    }
	
	@PostMapping("/day")
    public ResponseEntity createFlightRequestDay(@RequestBody FlightRequest flight) throws URISyntaxException {//
		FlightRequestListDay dayFlight = new FlightRequestListDay(flight);
		flightDayRepository.addFlightRequestListDay(dayFlight);
        return ResponseEntity.created(new URI("/flights/day/" + dayFlight.getId())).body(dayFlight.getDayFlights());
    }
	
//	@GetMapping("/{origin}")
//    public String getClients(@PathVariable int origin) {
//		
//		Destinations dest = new Destinations();
//        return dest.returnRandomDestinations(origin).toString();
//    }

//	 @PostMapping("/login")
//	    public boolean tryToLogin(@RequestBody Client client) throws URISyntaxException {
//	    	if (client.getPassword() == "bootcamp2" && client.getUsername() == "solera@solera.com") {
//	    		return true;
//	    	}
//	        return false;
//	    }
}
