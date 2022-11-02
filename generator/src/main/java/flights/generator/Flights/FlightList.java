package flights.generator.Flights;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import flights.generator.FlightRest.FlightRequest;

public class FlightList {
	
	private LocalDate date;
	private String origin;
	private String destination;
	private double totalPrice;
	private Duration totalDuration;
	private boolean allowLuggage;
	private int totalLayover=0;
	private ArrayList<Flight> list= new ArrayList<Flight>();

	public FlightList(LocalDate date, String origin, String destination){
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		generateFlights();
		allowLuggage=checkAllowLuggage();
		totalPrice =getTotalPrice();
		totalDuration = getTotalDuration();
	}

	public ArrayList<Flight> generateFlights() {
		int numberOfFlights = (int)((Math.random() * 2) +1);
		addFlightsToList(numberOfFlights);
		return list;
	}
	
	private void addFlightsToList(int numberOfFlights){
		String connection2;
		String connection1;
		Flight baseFlight;
		Flight connectionFlight;
		ArrayList<String> conne;
		switch(numberOfFlights){
			case 1:
				list.add(createFlight(origin,destination));
				break;
			case 2:
				conne =generateConnections(1);
				connection1 = conne.get(0);
				baseFlight = createFlight(origin,connection1);
				list.add(baseFlight);
				list.add(createConnectionFlight(connection1,destination,baseFlight.getArrivalDateTime()));
				break;
			case 3:
				conne =generateConnections(2);
				connection1 = conne.get(0);
				connection2 = conne.get(1);
				baseFlight = createFlight(origin,connection1);
				connectionFlight = createConnectionFlight(connection1,connection2,baseFlight.getArrivalDateTime());
				list.add(baseFlight);
				list.add(connectionFlight);
				list.add(createConnectionFlight(connection2,destination,connectionFlight.getArrivalDateTime()));
				break;
		}
			
	}

	private ArrayList<String> generateConnections(int numberOfConnections) {
		ArrayList<String> connections = new ArrayList<String>();
		ArrayList<String> connectionsPlaces = addConnectionPlaces();
		for (int i=0; i<numberOfConnections;i++) {
			int placeToAddToConnections = (int)(Math.random() * connectionsPlaces.size());
			connections.add(connectionsPlaces.get(placeToAddToConnections));
			connectionsPlaces.remove(placeToAddToConnections);
		}
		return connections;
	}
	
	private ArrayList<String> addConnectionPlaces() {
		ArrayList<String> connectionsPlaces = addConnectionPlaces();
		
		connectionsPlaces.add("Dubai");
		connectionsPlaces.add("Cork");
		connectionsPlaces.add("Paris");
		connectionsPlaces.add("Berlin");
		connectionsPlaces.add("Porto");
		
		return connectionsPlaces;
	}

	private Flight createFlight(String origin,String destination) {
		Flight flight;
		flight = new Flight(origin, destination, date);
		return flight;
		
	}
	
	private Flight createConnectionFlight(String origin, String destination, LocalDateTime time) {
		Flight flight;
		int layover= (int) ((Math.random() * 10)+2);
		this.totalLayover += layover;
		flight = new ConnectingFlight(origin, destination, time.plusHours((long)layover));
		return flight;
	}

	private boolean checkAllowLuggage() {
		for(int i=0;i<list.size();i++) {
			if (list.get(i).luggageAllowed == false) {
				return false;
			}
		}
		return true;
	}
	
	private double getTotalPrice() {
		double totalPrice=0;
		for(int i=0;i<list.size();i++) {
			totalPrice=list.get(i).getPrice();
		}
		return totalPrice;
	}
	
	private Duration getTotalDuration() {
		//Duration
		for(int i=0;i<list.size();i++) {
			totalPrice=list.get(i).getPrice();
		}
		return null;
	}
	
	
	
	public ArrayList<Flight> getList() {
		return list;
	}
	
}
