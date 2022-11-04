package flights.generator.FlightRest;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightRequestListWeek {
	private ArrayList<FlightRequest> dayFlights;
	private long id;
	
	public FlightRequestListWeek(FlightRequest flight) {
		//dayFlights = returnAllFlightsForDay(flight);
		dayFlights = returnAllFlightsForWeek(flight);
		id = (long) Math.floor((Math.random() * 1000)+1);
	}
	
	public FlightRequestListWeek(FlightRequest flight,boolean b) {
		//dayFlights = returnAllFlightsForDay(flight);
		dayFlights = FlightRequestListWeekAndBack(flight);
		id = (long) Math.floor((Math.random() * 1000)+1);
	}
//	public ArrayList<FlightRequest> returnAllFlightsForDay(FlightRequest flight){
//		ArrayList<FlightRequest> dayFlights = new ArrayList<FlightRequest>();
//		
//		int flightsOnTheDay = (int) Math.floor((Math.random() * 3)+2);
//		for (int i = 0; i<flightsOnTheDay;i++) {
//			dayFlights.add(createRandomFlight(flight,flight.getDate()));
//		}
//		return dayFlights;
//	}
	
	
	public ArrayList<FlightRequest> FlightRequestListWeekAndBack(FlightRequest flight) {
		ArrayList<FlightRequest> dayFlights = new ArrayList<FlightRequest>();
		// If date chosen is in the past, show results for the newest 7 days
		LocalDate chosenDate = flight.getDate();
		LocalDate today = LocalDate.now();
		if(today.isAfter(chosenDate)){
			chosenDate = today.plusDays(3);
		}
		for (int i=-3; i<4;i++) {
			int flightsOnTheDay = (int) Math.floor((Math.random() * 3)+2);
		for (int j = 0; j<flightsOnTheDay;j++) {
			dayFlights.add(createRandomFlight(flight,chosenDate.plusDays(i)));
			dayFlights.add(createRandomFlightBack(flight,chosenDate.plusDays(i+7)));
		}
		}
		return dayFlights;
	}

	public ArrayList<FlightRequest> returnAllFlightsForWeek(FlightRequest flight){
		ArrayList<FlightRequest> dayFlights = new ArrayList<FlightRequest>();
		// If date chosen is in the past, show results for the newest 7 days
		LocalDate chosenDate = flight.getDate();
		LocalDate today = LocalDate.now();
		if(today.isAfter(chosenDate)){
			chosenDate = today.plusDays(3);
		}
		for (int i=-3; i<4;i++) {
			int flightsOnTheDay = (int) Math.floor((Math.random() * 3)+2);
		for (int j = 0; j<flightsOnTheDay;j++) {
			dayFlights.add(createRandomFlight(flight,chosenDate.plusDays(i)));
		}
		}
		return dayFlights;
	}
	
	
	public FlightRequest createRandomFlight(FlightRequest flight,LocalDate date) {
		FlightRequest savedFlight = new FlightRequest(date,flight.getOrigin(),flight.getDestination());
		savedFlight.setId((long)(Math.random() * 10000));
		return savedFlight;
	}
	
	public FlightRequest createRandomFlightBack(FlightRequest flight,LocalDate date) {
		FlightRequest savedFlight = new FlightRequest(date,flight.getDestination(),flight.getOrigin());
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
