package flights.generator.Flights;

import java.util.ArrayList;
import java.util.List;

public class Destinations {
private List<String> origins =new ArrayList<String>();
private List<String> destinations =new ArrayList<String>();

public static final int SAO_PAULO = 0;
public static final int SEVILLA = 1;
public static final int MADRID = 2;
public static final int DUBLIN = 3;
public static final int LISBON = 4;
	
public Destinations(){
	origins.add("Sao Paulo");
	origins.add("Sevilla");
	origins.add("Madrid");
	origins.add("Dublin");
	origins.add("Lisbon");
	
	destinations.add("Sao Paulo");
	destinations.add("Sevilla");
	destinations.add("Madrid");
	destinations.add("Dublin");
	destinations.add("Lisbon");
	destinations.add("Rome");
}

public List<String> getOrigins() {
	return origins;
}

public void setOrigins(List<String> origins) {
	this.origins = origins;
}

public List<String> getDestinations() {
	return destinations;
}

public List<String> returnRandomDestinations(int currentcity){
	List<String> destinationRandom =new ArrayList<String>();
	int randomAmountToRemove = (int)(Math.random() * (destinations.size()-1));
	destinationRandom=destinations;
	destinationRandom.remove(currentcity);
	for (int i=0;i<randomAmountToRemove;i++) {
		int randomElementToRemove = (int)(Math.random() * destinationRandom.size());
		destinationRandom.remove(randomElementToRemove);
	}
	return destinationRandom;
}

public void setDestinations(List<String> destinations) {
	this.destinations = destinations;
}

}
