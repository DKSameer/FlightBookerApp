package flights.generator.Flights;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import flights.generator.FlightRest.FlightRequest;

public class Filters {
	private String airline;
	private int scales;
	private Boolean luggage;
	
	private static ArrayList<FlightRequest> filteredFlights = new ArrayList<FlightRequest>();
	
	public Filters(Map<String, String> filter,ArrayList<FlightRequest> array) {
		scales = filterScales(filter);
		airline = filterAirline(filter);
		luggage = filterLuggage(filter);
		filteredFlights=array;
		filterByParameters(array,filter);
	}
	
	public ArrayList<FlightRequest> filterByParameters(ArrayList<FlightRequest> Array,Map<String, String> filterOptions){
		
		if (scales>=0) {
			Array.stream()
			.filter(x -> x.getFlightList().getScales()!= scales)
			.forEach(x -> filteredFlights.remove(x));
		}
		if (airline != "") {
			Array.stream()
			.filter(x -> !x.getFlightList().getList().contains(airline))
			.forEach(x -> filteredFlights.remove(x));
		}
		if (luggage !=null) {
			Array.stream()
			.filter(x -> x.getFlightList().isAllowLuggage()!= luggage)
			.forEach(x -> filteredFlights.remove(x));
		}
		return filteredFlights;


	}
	
	
	public static int filterScales(Map<String, String> filter){
		int numberOfScales=-1;
	
		Map<String, String> scales = filter.entrySet().stream().filter(x -> x.getKey()=="scales").collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		for (Map.Entry<String, String> entry : scales.entrySet()) {
		    numberOfScales=Integer.parseInt(entry.getValue());
		}
		return numberOfScales;
	}
	
	public static String filterAirline(Map<String, String> filter){
		String airline="";
	
		Map<String, String> scales = filter.entrySet().stream().filter(x -> x.getKey()=="airline").collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		for (Map.Entry<String, String> entry : scales.entrySet()) {
			airline=entry.getValue();
		}
		return airline;
	}
	
	public static Boolean filterLuggage(Map<String, String> filter){
		boolean luggage=false;
	
		Map<String, String> scales = filter.entrySet().stream().filter(x -> x.getKey()=="luggage").collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		for (Map.Entry<String, String> entry : scales.entrySet()) {
			luggage=Boolean.parseBoolean(entry.getValue());
			return luggage;
		}
		return null;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public int getScales() {
		return scales;
	}


	public void setScales(int scales) {
		this.scales = scales;
	}


	public Boolean getLuggage() {
		return luggage;
	}


	public void setLuggage(Boolean luggage) {
		this.luggage = luggage;
	}


	public static ArrayList<FlightRequest> getFilteredFlights() {
		return filteredFlights;
	}


	public static void setFilteredFlights(ArrayList<FlightRequest> filteredFlights) {
		Filters.filteredFlights = filteredFlights;
	}
}
