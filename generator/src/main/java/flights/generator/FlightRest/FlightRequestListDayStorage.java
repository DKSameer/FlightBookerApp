package flights.generator.FlightRest;

import java.util.ArrayList;

public class FlightRequestListDayStorage {
	
private ArrayList<FlightRequestListWeek> requests= new ArrayList<FlightRequestListWeek>();
	
	public void addFlightRequestListDay(FlightRequestListWeek fr) {
		requests.add(fr);
	}

	public ArrayList<FlightRequestListWeek> getRequests() {
		return requests;
	}
	
	public FlightRequestListWeek getFlightRequestDay(long id) {
		for (int i=0;i<requests.size();i++) {
			FlightRequestListWeek req = requests.get(i);
			if (req.getId()== id) {
				return req;
			}
		}
		return null;
	}
	
	public int size(){
		return requests.size();
	}

	public void setRequests(ArrayList<FlightRequestListWeek> requests) {
		this.requests = requests;
	}
	
	

}
