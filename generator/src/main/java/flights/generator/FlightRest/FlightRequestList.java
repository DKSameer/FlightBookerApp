package flights.generator.FlightRest;

import java.util.ArrayList;

public class FlightRequestList {

	private ArrayList<FlightRequest> requests= new ArrayList<FlightRequest>();
	
	public void addFlightRequest(FlightRequest fr) {
		requests.add(fr);
	}

	public ArrayList<FlightRequest> getRequests() {
		return requests;
	}
	
	public FlightRequest getFlightRequest(long id) {
		for (int i=0;i<requests.size();i++) {
			FlightRequest req = requests.get(i);
			if (req.getId()== id) {
				return req;
			}
		}
		return null;
	}
	
}
