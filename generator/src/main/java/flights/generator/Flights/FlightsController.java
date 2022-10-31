package flights.generator.Flights;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/destination")
public class FlightsController {
	
	private final FlightRepository flightRepository;
	
    public FlightsController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    
	public static int value=1;
	
	@GetMapping("/getdest/{origin}")
    public String getDestination(@PathVariable int origin) {
		
		Destinations dest = new Destinations();
        return dest.returnRandomDestinations(origin).toString();
    }
	
	@GetMapping("/flights/{id}")
    public String getFlight(@PathVariable long id) {
        return ""+id;
    }
	
	@PostMapping
    public ResponseEntity createFlight(@RequestBody FlightRequest flight) throws URISyntaxException {
		FlightRequest savedFlight = flightRepository.save(flight);
        return ResponseEntity.created(new URI("/flights/" + savedFlight.getId())).body(savedFlight);
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
