package flights.generator.Flights;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import flights.generator.FlightRest.FlightRequest;

public class Filters {

	private String airline;
	private int scales;
	private Boolean luggage;
	private LocalDate date;
	private Map<String, String> filter;

	private ArrayList<FlightRequest> filteredFlights = new ArrayList<FlightRequest>();

	public Filters(Map<String, String> filter, ArrayList<FlightRequest> array) {
		this.filter = filter;
		setAllFilterCriteria();
		filteredFlights = filterByParameters(array);
	}

	public void setAllFilterCriteria(){
		setScalesFilter();
		setAirlineFilter();
		setLuggageFilter();
		setDateFilter();
	}
	
	public ArrayList<FlightRequest> filterByParameters(ArrayList<FlightRequest> inputArray) {
		ArrayList<FlightRequest> result = inputArray;

		boolean filterScales = (scales >= 0);
		boolean filterAirline = (airline != "");
		boolean filterLuggage = (luggage != null);
		boolean filterDate = (date != null);

		if (filterScales) {
			result = filterByScales(result);
		}
		if (filterAirline) {
			result = filterByAirline(result);
		}
		if (filterLuggage) {
			result = filterByLuggage(result);
		}
		if (filterDate) {
			result = filterByDate(result);
		}
		return result;
	}

	private ArrayList<FlightRequest> filterByScales(ArrayList<FlightRequest> inputArray) {
		ArrayList<FlightRequest> newArray = new ArrayList<FlightRequest>();
		for (int i = 0; i < inputArray.size(); i++) {
			if (inputArray.get(i).getFlightList().getScales() == scales) {
				newArray.add(inputArray.get(i));
			}
		}
		return newArray;
	}

	private ArrayList<FlightRequest> filterByAirline(ArrayList<FlightRequest> inputArray) {
		ArrayList<FlightRequest> newArray = new ArrayList<FlightRequest>();
		for (int i = 0; i < inputArray.size(); i++) {
			boolean hasAirline = false;
			ArrayList<Flight> flightList = inputArray.get(i).getFlightList().getList();
			for (int j = 0; j < flightList.size(); j++) {
				if (flightList.get(j).getAirline().equals(airline)) {
					hasAirline = true;
				}
			}
			if (hasAirline) {
				newArray.add(inputArray.get(i));
			}
		}
		return newArray;
	}

	private ArrayList<FlightRequest> filterByLuggage(ArrayList<FlightRequest> inputArray) {
		ArrayList<FlightRequest> newArray = new ArrayList<FlightRequest>();
		for (int i = 0; i < inputArray.size(); i++) {
			if (inputArray.get(i).getFlightList().isAllowLuggage() == luggage) {
				newArray.add(inputArray.get(i));
			}
		}
		return newArray;
	}

	private ArrayList<FlightRequest> filterByDate(ArrayList<FlightRequest> inputArray) {
		ArrayList<FlightRequest> newArray = new ArrayList<FlightRequest>();
		for (int i = 0; i < inputArray.size(); i++) {
			if (inputArray.get(i).getFlightList().getDate().compareTo(date) == 0) {
				newArray.add(inputArray.get(i));
			}
		}
		return newArray;
	}


	private void setScalesFilter() {
		int tempScales = -1;
		if (filter.get("scales") != null) {
			tempScales = Integer.parseInt(filter.get("scales"));
		}
		this.scales = tempScales;
	}

	private void setAirlineFilter() {
		String airlineTemp = "";
		if (filter.get("airline") != null) {
			airlineTemp = (filter.get("airline"));
		}
		this.airline = airlineTemp;
	}

	private void setLuggageFilter() {
		Boolean tempLuggage = null;
		if (filter.get("luggage") != null) {
			tempLuggage = Boolean.parseBoolean(filter.get("luggage"));
		}
		this.luggage = tempLuggage;
	}

	private void setDateFilter() {
		LocalDate tempDate = null;
		if (filter.get("date") != null) {
			tempDate = LocalDate.parse(filter.get("date"));
		}
		this.date = tempDate;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, String> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

	
}
