package flights.generator.FlightRest;

import java.util.ArrayList;

public class FlightRequestListDayStorage {
	
private ArrayList<FlightRequestListDay> requests= new ArrayList<FlightRequestListDay>();
	
	public void addFlightRequestListDay(FlightRequestListDay fr) {
		requests.add(fr);
	}

	public ArrayList<FlightRequestListDay> getRequests() {
		return requests;
	}
	
	public FlightRequestListDay getFlightRequestDay(long id) {
		for (int i=0;i<requests.size();i++) {
			FlightRequestListDay req = requests.get(i);
			if (req.getId()== id) {
				return req;
			}
		}
		return null;
	}
	
	public int size(){
		return requests.size();
	}

	public void setRequests(ArrayList<FlightRequestListDay> requests) {
		this.requests = requests;
	}
	
	

}
