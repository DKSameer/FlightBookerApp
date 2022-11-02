package flights.generator.Flights;

import java.time.LocalDate;
import java.util.ArrayList;

import flights.generator.FlightRest.FlightRequest;

public class FlightList {
	
	private LocalDate date;
	private String origin;
	private String destination;
	private ArrayList<Flight> list= new ArrayList<Flight>();

	public FlightList(LocalDate date, String origin, String destination){
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		generateFlights();
	}

	public ArrayList<Flight> generateFlights() {
		int numberOfFlights = (int)((Math.random() * 2) +1);
		addFlightsToList(numberOfFlights);
		return list;
	}
	
	private void addFlightsToList(int numberOfFlights){
		String connection2;
		String connection1;
		ArrayList<String> conne;
		switch(numberOfFlights){
			case 1:
				createFlight(origin,destination);
				break;
			case 2:
				conne =generateConnections(1);
				connection1 = conne.get(0);
				createFlight(origin,connection1);
				createFlight(connection1,destination);
				break;
			case 3:
				conne =generateConnections(2);
				connection1 = conne.get(0);
				connection2 = conne.get(1);
				createFlight(origin,connection1);
				createFlight(connection1,connection2);
				createFlight(connection2,destination);
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
		
		return null;
		
	}
}
