package flights.generator.Flights;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class FlightList {
	
	private LocalDate date;
	private String origin;
	private String destination;
	private double totalPrice;
	private Duration totalDuration;
	private boolean allowLuggage;
	private int totalLayover=0;
	private int scales=0;
	private int numberOfFlights=0;
	private ArrayList<Flight> list= new ArrayList<Flight>();

	public FlightList(LocalDate date, String origin, String destination){
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		generateFlights();
		allowLuggage=checkAllowLuggage();
		totalPrice = calculateTotalPrice();
		totalDuration = calculateTotalDuration();
		scales= numberOfFlights-1;
	}

	public FlightList(LocalDate date2, String origin2, String destination2, boolean b) {
		this.date = date2;
		this.origin = origin2;
		this.destination = destination2;
		generateFlightsAndBack();
		allowLuggage=checkAllowLuggage();
		totalPrice = calculateTotalPrice();
		totalDuration = calculateTotalDuration();
		scales= numberOfFlights-1;
	}

	public ArrayList<Flight> generateFlights() {
		numberOfFlights = (int)((Math.random() * 3) +1);
		addFlightsToList(numberOfFlights);
		return list;
	}
	
	public ArrayList<Flight> generateFlightsAndBack() {
		numberOfFlights = (int)((Math.random() * 3) +1);
		addFlightsToList(numberOfFlights);
		String origin = this.origin;
		String destination = this.destination;
		this.destination= origin;
		this.origin= destination;
		date= date.plusDays((int) Math.floor((Math.random() * 8)+2));
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
		ArrayList<String> connectionsPlaces = new ArrayList<String>();
		
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
			if (list.get(i).getLuggageAllowed() == false) {
				return false;
			}
		}
		return true;
	}
	
	private double calculateTotalPrice() {
		double totalPrice=0;
		for(int i=0;i<list.size();i++) {
			totalPrice+=list.get(i).getPrice();
		}
		return totalPrice;
	}
	
	private Duration calculateTotalDuration() {
		//Duration
		// Update
		LocalDateTime departure =  list.get(0).getDepartureDateTime();
		LocalDateTime arrival = list.get(list.size()-1).getArrivalDateTime();
		Duration duration = Duration.between(departure, arrival);
		return duration;
	}
	
	
	
	public ArrayList<Flight> getList() {
		return list;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isAllowLuggage() {
		return allowLuggage;
	}

	public void setAllowLuggage(boolean allowLuggage) {
		this.allowLuggage = allowLuggage;
	}

	public int getTotalLayover() {
		return totalLayover;
	}

	public void setTotalLayover(int totalLayover) {
		this.totalLayover = totalLayover;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalDuration(Duration totalDuration) {
		this.totalDuration = totalDuration;
	}

	public void setList(ArrayList<Flight> list) {
		this.list = list;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Duration getTotalDuration() {
		return totalDuration;
	}

	public int getScales() {
		return scales;
	}

	public void setScales(int scales) {
		this.scales = scales;
	}

	public int getNumberOfFlights() {
		return numberOfFlights;
	}

	public void setNumberOfFlights(int numberOfFlights) {
		this.numberOfFlights = numberOfFlights;
	}
	
}
