package flights.generator.FlightRest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flights.generator.Flights.Filters;





@RestController
@RequestMapping("/destination")
@CrossOrigin(origins="*")
public class FlightsController {
	
	private final FlightRequestList flightRepository;
	private final FlightRequestListDayStorage flightDayRepository;
	private long lastFlightRequestId = 0;
	
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
    public FlightRequestListWeek getFlightDay(@PathVariable long id) {
        return flightDayRepository.getFlightRequestDay(id);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/filter")
	@ResponseBody
	public ArrayList<FlightRequest> applyFilters(@PathVariable long id,
			@RequestParam(required = false) Map<String, String> scales,
			@RequestParam(required = false) Map<String, String> luggage,
			@RequestParam(required = false) Map<String, String> airline) {
		
		Map<String, String> filters = new HashMap<String, String>();
		
		if (luggage !=null) filters.putAll(luggage);
		if (scales!=null) filters.putAll(scales);
		if (airline!=null) filters.putAll(airline);
		
		FlightRequestListWeek dayFlight = flightDayRepository.getFlightRequestDay(id);
		Filters filteredFlights = new Filters(filters,dayFlight.getDayFlights());
		
		 return filteredFlights.getFilteredFlights();
	}
	
	
//	@PostMapping
//    public ResponseEntity createFlightRequest(@RequestBody FlightRequest flight) throws URISyntaxException {//
//		FlightRequest savedFlight = new FlightRequest(flight.getDate(),flight.getOrigin(),flight.getDestination());
//		savedFlight.setId(++lastFlightRequestId);
//		//savedFlight.setId((long)(Math.random() * 1000));
//		flightRepository.addFlightRequest(savedFlight);
//        return ResponseEntity.created(new URI("/flights/" + savedFlight.getId())).body(savedFlight);
//    }
	
	//Body needed for this post is : {"date" : "2022-04-23", "origin" : "Sao Paulo","destination" : "Madrid"}
	
	@PostMapping("/day")
    public ResponseEntity createFlightRequestDay(@RequestBody FlightRequest flight) throws URISyntaxException {//
		FlightRequestListWeek dayFlight = new FlightRequestListWeek(flight);
		flightDayRepository.addFlightRequestListDay(dayFlight);
		return ResponseEntity.created(new URI("/destination/day/" + dayFlight.getId())).body(dayFlight);
    }
	
	public long getLastFlightRequestId() {
		return lastFlightRequestId;
	}

	public FlightRequestList getFlightRepository() {
		return flightRepository;
	}

	public FlightRequestListDayStorage getFlightDayRepository() {
		return flightDayRepository;
	}

}
