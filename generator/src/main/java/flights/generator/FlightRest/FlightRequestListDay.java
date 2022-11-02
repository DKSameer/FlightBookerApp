package flights.generator.FlightRest;

import java.util.ArrayList;

public class FlightRequestListDay {
	private ArrayList<FlightRequest> dayFlights;
	private long id;
	
	public FlightRequestListDay(FlightRequest flight) {
		dayFlights = returnAllFlightsForDay(flight);
		id = (long) Math.floor((Math.random() * 1000)+1);
	}
	
	public ArrayList<FlightRequest> returnAllFlightsForDay(FlightRequest flight){
		ArrayList<FlightRequest> dayFlights = new ArrayList<FlightRequest>();
		
		int flightsOnTheDay = (int) Math.floor((Math.random() * 3)+2);
		for (int i = 0; i<flightsOnTheDay;i++) {
			dayFlights.add(createRandomFlight(flight));
		}
		return dayFlights;
	}
	
	public FlightRequest createRandomFlight(FlightRequest flight) {
		FlightRequest savedFlight = new FlightRequest(flight.getDate(),flight.getOrigin(),flight.getDestination());
		savedFlight.setId((long)(Math.random() * 10000));
		return savedFlight;
	}
	
	
	public ArrayList<FlightRequest> getDayFlights() {
		return dayFlights;
	}
	public void setDayFlights(ArrayList<FlightRequest> dayFlights) {
		this.dayFlights = dayFlights;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
