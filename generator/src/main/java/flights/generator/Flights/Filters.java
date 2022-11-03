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
	
	private ArrayList<FlightRequest> filteredFlights = new ArrayList<FlightRequest>();
	
	public Filters(Map<String, String> filter,ArrayList<FlightRequest> array) {
		scales = filterScales(filter);
		airline = filterAirline(filter);
		luggage = filterLuggage(filter);
		//filteredFlights=array;
		filteredFlights=filterByParameters(array,filter);
	}
	
	public ArrayList<FlightRequest> filterByParameters(ArrayList<FlightRequest> Array,Map<String, String> filterOptions){
		ArrayList<FlightRequest> result1 = new ArrayList<FlightRequest>();
		ArrayList<FlightRequest> result2 = new ArrayList<FlightRequest>();
		ArrayList<FlightRequest> result3 = new ArrayList<FlightRequest>();
		boolean filterScales = (scales>=0);
		boolean filterAirline = (airline != "");
		if (filterScales) {
			for(int i = 0; i < Array.size(); i++) {
				if(Array.get(i).getFlightList().getScales() == scales) {
					result1.add(Array.get(i));
				}
			}
		}else {
			result1=Array;
		}
		
		if (filterAirline) {
			for(int i = 0; i < result1.size(); i++) {
				boolean hasAirline=false;
				ArrayList<Flight> flightList = result1.get(i).getFlightList().getList();
				for(int j=0;j< flightList.size();j++) {
					if (flightList.get(j).getAirline().equals(airline)) {
						hasAirline=true;
					}
					
				}
				if (hasAirline) {
						result2.add(result1.get(i));
				}
				
			}
		}else {
			result2=result1;
		}
		System.out.println("luggage value "+luggage);
		if (luggage !=null) {
			System.out.println("Not Null");
			for(int i = 0; i < result2.size(); i++) {
				if(result2.get(i).getFlightList().isAllowLuggage() == luggage) {
					result3.add(result2.get(i));
					System.out.println("Adding "+result2.get(i).getFlightList().isAllowLuggage());
				}
			}
		}else {
			result3=result2;
		}
//		if (airline != "") {
//			Array.stream()
//			.filter(x -> !x.getFlightList().getList().get(0).getAirline().contains(airline))
//			.forEach(x -> result.remove(x));
//		}
//		if (luggage !=null) {
//			Array.stream()
//			.filter(x -> x.getFlightList().isAllowLuggage()!= luggage)
//			.forEach(x -> result.remove(x));
//		}
		//filteredFlights = result;
		return result3;


	}
	
	
	public int filterScales(Map<String, String> filter){
		int numberOfScales=-1;
		//System.out.println(filter);
		if(filter.get("scales") != null) {
			numberOfScales = Integer.parseInt(filter.get("scales"));
		}
		return numberOfScales;
	}
	
	public String filterAirline(Map<String, String> filter){
		String airline="";
		if(filter.get("airline") != null) {
			airline = (filter.get("airline"));
		}
		return airline;
	}
	
	public Boolean filterLuggage(Map<String, String> filter){
		Boolean luggage = null;
		if(filter.get("luggage") != null) {
			luggage = Boolean.parseBoolean(filter.get("luggage"));
		}
		return luggage;
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


	public ArrayList<FlightRequest> getFilteredFlights() {
		return filteredFlights;
	}


	public void setFilteredFlights(ArrayList<FlightRequest> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}
}
