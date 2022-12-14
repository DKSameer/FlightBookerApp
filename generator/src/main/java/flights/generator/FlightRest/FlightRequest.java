package flights.generator.FlightRest;


import java.time.LocalDate;

import flights.generator.Flights.FlightList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "flight")
public class FlightRequest {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private LocalDate date;
	private String origin;
	private String destination;
	private FlightList flightList;
	
	public FlightRequest() {
	}
	
	public FlightRequest(LocalDate date, String origin, String destination,boolean b) {
		super();
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		if (origin == null || destination == null ||date == null) {
            throw new IllegalArgumentException("FlightRequest constructor cannot contain null arguments");
        }
		flightList = new FlightList(date,origin,destination,b);
		//call generate flightlist
	}
	
	
	public FlightRequest(LocalDate date, String origin, String destination) {
		super();
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		if (origin == null || destination == null ||date == null) {
            throw new IllegalArgumentException("FlightRequest constructor cannot contain null arguments");
        }
		flightList = new FlightList(date,origin,destination);
		//call generate flightlist
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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public FlightList getFlightList() {
		return flightList;
	}

	public void setFlightList(FlightList flightList) {
		this.flightList = flightList;
	}

}
